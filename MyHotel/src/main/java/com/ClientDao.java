package com;

import org.apache.log4j.Logger;
import  java.util.ArrayList;
import java.sql.*;

public class ClientDao implements Dao<Client>{
    private static final Logger logger = Logger.getLogger(ClientDao.class);

    private Connection connection;

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public ClientDao() {
        this.connection = DataBase.getConnection();
    }

    public ArrayList<Client> selectAll() {
        ArrayList<Client> сlientList = new ArrayList<Client>();
        Statement statement = null;
        try {
            statement = this.connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM clients");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String surname = resultSet.getString("surname");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String passwordHash = resultSet.getString("passwordHash");
                Client client = new Client(id, surname, name, email, passwordHash);
                сlientList.add(client);
            }
        }
        catch (Exception ex) {
            this.logger.error(ex);
        }
        finally {
            if (statement != null) {
                try {
                    statement.close();
                }
                catch (Exception ex) {
                }
            }
        }
        return сlientList;
    }

    public Client selectOneById(int id) {
        Client client = null;
        PreparedStatement preparedStatement = null;
        try {
            String sql = "SELECT * FROM clients WHERE id = ?";
            preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String surname = resultSet.getString("surname");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String passwordHash = resultSet.getString("passwordHash");
                client = new Client(id, surname, name, email, passwordHash);
            }
        }
        catch (Exception ex) {
            this.logger.error(ex);
        }
        finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                }
                catch (Exception ex) {
                }
            }
        }
        return client;
    }

    public Client selectOneByEmail(String email) {
        Client client = null;
        PreparedStatement preparedStatement = null;
        try {
            String sql = "SELECT * FROM clients WHERE email = ?";
            preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String surname = resultSet.getString("surname");
                String name = resultSet.getString("name");
                String passwordHash = resultSet.getString("passwordHash");
                client = new Client(id, surname, name,  email, passwordHash);
            }
        }
        catch (Exception ex) {
            this.logger.error(ex);
        }
        finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                }
                catch (Exception ex) {
                }
            }
        }
        return client;
    }

    public int insert(Client client) {
        PreparedStatement preparedStatement = null;
        int result = 0;
        try {
            String sql = "INSERT INTO clients (name, surname, email, passwordHash) VALUES ( ?, ?, ?, ?)";
            preparedStatement = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, client.getSurname());
            preparedStatement.setString(2, client.getName());
            preparedStatement.setString(3, client.getEmail());
            preparedStatement.setString(4, client.getPasswordHash());
            preparedStatement.execute();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                result = resultSet.getInt(1);
            }
        }
        catch (Exception ex) {
            this.logger.error(ex);
        }
        finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                }
                catch (Exception ex) {
                }
            }
        }
        return result;
    }

    public int update(Client client) {
        PreparedStatement preparedStatement = null;
        int result = 0;
        try {
            String sql = "UPDATE clients SET surName = ?, name = ?, email = ?, passwordHash = ? WHERE id = ?";
            preparedStatement = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, client.getSurname());
            preparedStatement.setString(2, client.getName());
            preparedStatement.setString(3, client.getEmail());
            preparedStatement.setString(4, client.getPasswordHash());
            preparedStatement.setInt(5, client.getId());
            result = preparedStatement.executeUpdate();
        }
        catch (Exception ex) {
            this.logger.error(ex);
        }
        finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                }
                catch (Exception ex) {
                }
            }
        }
        return result;
    }

    public int deleteById(int id) {
        PreparedStatement preparedStatement = null;
        int result = 0;
        try {
            String sql = "DELETE FROM clients WHERE id = ?";
            preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            result = preparedStatement.executeUpdate();
        }
        catch (Exception ex) {
            this.logger.error(ex);
        }
        finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                }
                catch (Exception ex) {
                }
            }
        }
        return result;
    }
}
