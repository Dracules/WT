package com;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class BillDao {
    private static final Logger logger = Logger.getLogger(BillDao.class);

    private Connection connection;

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public BillDao() {
        this.connection = DataBase.getConnection();
    }

    public ArrayList<Bill> selectAll() {
        ArrayList<Bill> billList = new ArrayList<Bill>();
        Statement statement = null;
        try {
            statement = this.connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM bills");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int orderId = resultSet.getInt("orderId");
                int clientId = resultSet.getInt("clientId");
                int hotelRoomId = resultSet.getInt("hotelRoomId");
                int price =  resultSet.getInt("price");
                Bill bill = new Bill(id, orderId, clientId, hotelRoomId, price);
                billList.add(bill);
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
        return billList;
    }

    public Bill selectOneById(int id) {
        Bill bill = null;
        PreparedStatement preparedStatement = null;
        try {
            String sql = "SELECT * FROM bills WHERE id = ?";
            preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int orderId = resultSet.getInt("orderId");
                int clientId = resultSet.getInt("clientId");
                int hotelRoomId = resultSet.getInt("hotelRoomId");
                int price =  resultSet.getInt("price");
                bill = new Bill(id, orderId, clientId, hotelRoomId, price);
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
        return bill;
    }

    public int insert(Bill bill) {
        PreparedStatement preparedStatement = null;
        int result = 0;
        try {
            String sql = "INSERT INTO bills (orderId, clientId, hotelRoomId, price) VALUES ( ?, ?, ?, ?)";
            preparedStatement = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, bill.getOrderId());
            preparedStatement.setInt(2, bill.getClientId());
            preparedStatement.setInt(3, bill.getHotelRoomId());
            preparedStatement.setInt(4, bill.getPrice());
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

    public int update(Bill bill) {
        PreparedStatement preparedStatement = null;
        int result = 0;
        try {
            String sql = "UPDATE bills SET orderId = ?, clientId = ?, hotelRoomId = ?, price = ? WHERE id = ?";
            preparedStatement = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, bill.getOrderId());
            preparedStatement.setInt(2, bill.getClientId());
            preparedStatement.setInt(3, bill.getHotelRoomId());
            preparedStatement.setInt(4, bill.getPrice());
            preparedStatement.setInt(5, bill.getId());
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
            String sql = "DELETE FROM bills WHERE id = ?";
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
