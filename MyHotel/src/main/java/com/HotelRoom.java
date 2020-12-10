package com;

public class HotelRoom {
    private int id;
    private int seatsCount;
    private String hotelRoomType;
    private boolean isAvailable;

    public HotelRoom(int id, int seatsCount, String hotelRoomType, boolean isAvailable){
        this.id = id;
        this.seatsCount = seatsCount;
        this.hotelRoomType = hotelRoomType;
        this.isAvailable = isAvailable;
    }

    public HotelRoom(int seatsCount, String hotelRoomType, boolean isAvailable) {
        this.seatsCount = seatsCount;
        this.hotelRoomType = hotelRoomType;
        this.isAvailable = isAvailable;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSeatsCount() {
        return seatsCount;
    }

    public void setSeatsCount(int seatsCount) {
        this.seatsCount = seatsCount;
    }

    public String getHotelRoomType() {
        return hotelRoomType;
    }

    public void setHotelRoomType(String hotelRoomType) {
        this.hotelRoomType = hotelRoomType;
    }

    public boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
}
