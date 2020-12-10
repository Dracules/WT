package com;

public class Bill {
    private int id;
    private int orderId;
    private int clientId;
    private int hotelRoomId;
    private int price;

    public Bill(int id, int orderId, int clientId, int hotelRoomId, int price) {
        this.id = id;
        this.orderId = orderId;
        this.clientId = clientId;
        this.hotelRoomId = hotelRoomId;
        this.price = price;
    }

    public Bill(int orderId, int clientId, int hotelRoomId, int price) {
        this.orderId = orderId;
        this.clientId = clientId;
        this.hotelRoomId = hotelRoomId;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getHotelRoomId() {
        return hotelRoomId;
    }

    public void setHotelRoomId(int hotelRoomId) {
        this.hotelRoomId = hotelRoomId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
