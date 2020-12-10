package com;

import org.apache.log4j.Logger;

import java.sql.Date;
import java.sql.*;
import java.util.ArrayList;

public class OrderDao implements Dao<Order> {
    private static final Logger logger = Logger.getLogger(OrderDao.class);

    private Connection connection;

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public OrderDao() {
        this.connection = DataBase.getConnection();
    }

    public ArrayList<Order> selectAll() {
        ArrayList<Order> orders = new ArrayList<Order>();
        Statement statement = null;
        try {
            statement = this.connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM orders");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int clientId = resultSet.getInt("clientId");
                int seatsCount = resultSet.getInt("seatsCount");
                String hotelRoomType = resultSet.getString("hotelRoomType");
                String passportHash = resultSet.getString("passportHash");
                Date arrivalDate = resultSet.getDate("arrivalDate");
                Date departureDate = resultSet.getDate("departureDate");
                Order order = new Order(id, clientId, seatsCount, hotelRoomType, passportHash, arrivalDate, departureDate );
                orders.add(order);
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
        return orders;
    }

    public Order selectOneById(int id) {
        Order order = null;
        PreparedStatement preparedStatement = null;
        try {
            String sql = "SELECT * FROM orders WHERE id = ?";
            preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int clientId = resultSet.getInt("clientId");
                int seatsCount = resultSet.getInt("seatsCount");
                String hotelRoomType = resultSet.getString("hotelRoomType");
                String passportHash = resultSet.getString("passportHash");
                Date arrivalDate = resultSet.getDate("arrivalDate");
                Date departureDate = resultSet.getDate("departureDate");
                order = new Order(id, clientId, seatsCount, hotelRoomType, passportHash, arrivalDate, departureDate);
            }

        } catch (Exception ex) {
            this.logger.error(ex);
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (Exception ex) {
                }
            }
        }
        return order;
    }

    public int insert(Order order) {
        PreparedStatement preparedStatement = null;
        int result = 0;
        try {
            String sql = "INSERT INTO orders (clientId, seatsCount, hotelRoomType, passportHash, arrivalDate, departureDate) VALUES ( ?, ?, ?, ?, ?, ?)";
            preparedStatement = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, order.getClientId());
            preparedStatement.setInt(2, order.getSeatsCount());
            preparedStatement.setString(3, order.getHotelRoomType());
            preparedStatement.setString(4, order.getPassportHash());
            preparedStatement.setDate(5, java.sql.Date(order.getArrivalDate())); java.sql.Date
            preparedStatement.setDate(6, java.sql.Date(order.getDepartureDate()));
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

    public int update(Order order) {
        PreparedStatement preparedStatement = null;
        int result = 0;
        try {
            String sql = "UPDATE orders SET clientId = ?, seatsCount = ?, hotelRoomType = ?, passportHash = ?, arrivalDate = ?, departureDate = ?  WHERE id = ?";
            preparedStatement = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, order.getClientId());
            preparedStatement.setInt(2, order.getSeatsCount());
            preparedStatement.setString(3, order.getHotelRoomType());
            preparedStatement.setString(4, order.getPassportHash());
            preparedStatement.setDate(5, java.sql.Date(order.getArrivalDate()));
            preparedStatement.setDate(6, java.sql.Date(order.getDepartureDate()));
            preparedStatement.setInt(7, order.getId());
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
            String sql = "DELETE FROM orders WHERE id = ?";
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
