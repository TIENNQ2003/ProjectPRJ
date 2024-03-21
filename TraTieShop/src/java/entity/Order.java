/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author nqtie
 */
public class Order {

    private int OrderID;
    private int UserID;
    private String OrderDate;
    private String RequireDate;
    private String ShippedDate;
    private double TotalPrice;
    private int StatusID;
    private String DeliverAddress;
    private String Phone;
    private String ReceiverName;

    public Order() {
    }

    public Order(int OrderID, int UserID, String OrderDate, String RequireDate, String ShippedDate, double TotalPrice, int StatusID, String DeliverAddress, String Phone, String ReceiverName) {
        this.OrderID = OrderID;
        this.UserID = UserID;
        this.OrderDate = OrderDate;
        this.RequireDate = RequireDate;
        this.ShippedDate = ShippedDate;
        this.TotalPrice = TotalPrice;
        this.StatusID = StatusID;
        this.DeliverAddress = DeliverAddress;
        this.Phone = Phone;
        this.ReceiverName = ReceiverName;
    }

    public Order(int OrderID, int UserID, String OrderDate, double TotalPrice, int StatusID, String DeliverAddress, String Phone, String ReceiverName) {
        this.OrderID = OrderID;
        this.UserID = UserID;
        this.OrderDate = OrderDate;
        this.TotalPrice = TotalPrice;
        this.StatusID = StatusID;
        this.DeliverAddress = DeliverAddress;
        this.Phone = Phone;
        this.ReceiverName = ReceiverName;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public int getOrderID() {
        return OrderID;
    }

    public void setOrderID(int OrderID) {
        this.OrderID = OrderID;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int UserID) {
        this.UserID = UserID;
    }

    public String getOrderDate() {
        return OrderDate;
    }

    public void setOrderDate(String OrderDate) {
        this.OrderDate = OrderDate;
    }

    public String getRequireDate() {
        return RequireDate;
    }

    public void setRequireDate(String RequireDate) {
        this.RequireDate = RequireDate;
    }

    public String getShippedDate() {
        return ShippedDate;
    }

    public void setShippedDate(String ShippedDate) {
        this.ShippedDate = ShippedDate;
    }

    public double getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(double TotalPrice) {
        this.TotalPrice = TotalPrice;
    }

    public int getStatusID() {
        return StatusID;
    }

    public void setStatusID(int StatusID) {
        this.StatusID = StatusID;
    }

    public String getDeliverAddress() {
        return DeliverAddress;
    }

    public void setDeliverAddress(String DeliverAddress) {
        this.DeliverAddress = DeliverAddress;
    }

    public String getReceiverName() {
        return ReceiverName;
    }

    public void setReceiverName(String ReceiverName) {
        this.ReceiverName = ReceiverName;
    }

    @Override
    public String toString() {
        return "Order{" + "OrderID=" + OrderID + ", UserID=" + UserID + ", OrderDate=" + OrderDate + ", RequireDate=" + RequireDate + ", ShippedDate=" + ShippedDate + ", TotalPrice=" + TotalPrice + ", StatusID=" + StatusID + ", DeliverAddress=" + DeliverAddress + '}';
    }

}
