package com;

import java.util.Date;

public class Order {
    private int id;
    private int clientId;
    private int seatsCount;
    private String hotelRoomType;
    private String passportHash;
    private Date arrivalDate;
    private Date departureDate;

    public Order(int id, int clientId, int seatsCount, String hotelRoomType, String passportHash, Date arrivalDate, Date departureDate) {
        this.id = id;
        this.clientId = clientId;
        this.seatsCount = seatsCount;
        this.hotelRoomType = hotelRoomType;
        this.passportHash = passportHash;
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
    }

    public Order(int clientId, int seatsCount, String hotelRoomType, String passportHash, Date arrivalDate, Date departureDate) {
        this.clientId = clientId;
        this.seatsCount = seatsCount;
        this.hotelRoomType = hotelRoomType;
        this.passportHash = passportHash;
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
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

    public String getPassportHash() { return passportHash; }

    public void setPassportHash(String passportHash) {this.passportHash = passportHash;}

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate)
    {
        this.departureDate = departureDate;
    }
}
