package com;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class HotelRoomDao implements Dao<HotelRoom> {
    private static final Logger logger = Logger.getLogger(HotelRoomDao.class);

    private Connection connection;

    public HotelRoomDao() {
        this.connection = DataBase.getConnection();
    }

    public ArrayList<HotelRoom> selectAll() {
        ArrayList<HotelRoom> hotelRoomsList = new ArrayList<HotelRoom>();
        Statement statement = null;
        try {
            statement = this.connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM hotelRooms");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int seatsCount = resultSet.getInt("seatsCount");
                String hotelRoomType = resultSet.getString("hotelRoomType");
                boolean isAvailable = resultSet.getBoolean("isAvailable");
                HotelRoom hotelRoom = new HotelRoom(id, seatsCount, hotelRoomType, isAvailable);
                hotelRoomsList.add(hotelRoom);
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
        return hotelRoomsList;
    }

    public HotelRoom selectOneById(int id) {
        HotelRoom hotelRoom = null;
        PreparedStatement preparedStatement = null;
        try {
            String sql = "SELECT * FROM hotelRooms WHERE id = ?";
            preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String hotelRoomType = resultSet.getString("hotelRoomType");
                int seatsCount = resultSet.getInt("seatsCount");
                boolean isAvailable = resultSet.getBoolean("isAvailable");
                hotelRoom = new HotelRoom(id, seatsCount, hotelRoomType, isAvailable);
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
        return hotelRoom;
    }

    public int insert(HotelRoom hotelRoom) {
        PreparedStatement preparedStatement = null;
        int result = 0;
        try {
            String sql = "INSERT INTO hotelRooms (seatsCount, hotelRoomType, isAvailable) VALUES (?, ?, ?)";
            preparedStatement = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, hotelRoom.getSeatsCount());
            preparedStatement.setString(2, hotelRoom.getHotelRoomType());
            preparedStatement.setBoolean(3,hotelRoom.getIsAvailable());
            preparedStatement.executeUpdate();
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

    public int update(HotelRoom hotelRoom) {
        PreparedStatement preparedStatement = null;
        int result = 0;
        try {
            String sql = "UPDATE hotelRooms SET seatsCount = ?, hotelRoomType = ?, isAvailable = ? WHERE id = ?";
            preparedStatement = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, hotelRoom.getSeatsCount());
            preparedStatement.setString(2, hotelRoom.getHotelRoomType());
            preparedStatement.setBoolean(3,hotelRoom.getIsAvailable());
            preparedStatement.setInt(4, hotelRoom.getId());
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
            String sql = "DELETE FROM hotelRooms WHERE id = ?";
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
