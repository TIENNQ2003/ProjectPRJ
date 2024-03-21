/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author nqtie
 */
public class Cart {

    private int UserID;
    private int ProductID;
    private int Quantity;
    private String dateInsert;

    public Cart() {
    }

    public Cart(int UserID, int ProductID, int Quantity, String dateInsert) {
        this.UserID = UserID;
        this.ProductID = ProductID;
        this.Quantity = Quantity;
        this.dateInsert = dateInsert;
    }

    public Cart(int ProductID, int Quantity) {
        this.ProductID = ProductID;
        this.Quantity = Quantity;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int UserID) {
        this.UserID = UserID;
    }

    public int getProductID() {
        return ProductID;
    }

    public void setProductID(int ProductID) {
        this.ProductID = ProductID;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

    public String getDateInsert() {
        return dateInsert;
    }

    public void setDateInsert(String dateInsert) {
        this.dateInsert = dateInsert;
    }

    @Override
    public String toString() {
        return "Cart{" + "UserID=" + UserID + ", ProductID=" + ProductID + ", Quantity=" + Quantity + '}';
    }
}
