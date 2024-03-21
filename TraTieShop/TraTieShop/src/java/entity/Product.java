/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author nqtie
 */
public class Product {

    private int ProductId;
    private String ProductName;
    private int CategoryID;
    private String Description;
    private double UnitPrice;
    private int UnitinStock;
    private double Discount;
    private int status;
    private String Image;

    public Product() {
    }

    public Product(int ProductId, String ProductName, int CategoryID, String Description, double UnitPrice, int UnitinStock, double Discount, int status, String Image) {
        this.ProductId = ProductId;
        this.ProductName = ProductName;
        this.CategoryID = CategoryID;
        this.Description = Description;
        this.UnitPrice = UnitPrice;
        this.UnitinStock = UnitinStock;
        this.Discount = Discount;
        this.status = status;
        this.Image = Image;
    }

    public int getProductId() {
        return ProductId;
    }

    public void setProductId(int ProductId) {
        this.ProductId = ProductId;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String ProductName) {
        this.ProductName = ProductName;
    }

    public int getCategoryID() {
        return CategoryID;
    }

    public void setCategoryID(int CategoryID) {
        this.CategoryID = CategoryID;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public double getUnitPrice() {
        return UnitPrice;
    }

    public void setUnitPrice(double UnitPrice) {
        this.UnitPrice = UnitPrice;
    }

    public int getUnitinStock() {
        return UnitinStock;
    }

    public void setUnitinStock(int UnitinStock) {
        this.UnitinStock = UnitinStock;
    }

    public double getDiscount() {
        return Discount;
    }

    public void setDiscount(double Discount) {
        this.Discount = Discount;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

    @Override
    public String toString() {
        return "Product{" + "ProductId=" + ProductId + ", ProductName=" + ProductName + ", CategoryID=" + CategoryID + ", Description=" + Description + ", UnitPrice=" + UnitPrice + ", UnitinStock=" + UnitinStock + ", Discount=" + Discount + ", status=" + status + ", Image=" + Image + '}';
    }
    
}
