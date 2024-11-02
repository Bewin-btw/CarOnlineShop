// файл: /models/Car.java
package models;

public class Car {
    private int id;
    private String model;
    private double price;
    private boolean sunroof;
    private boolean sportPackage;
    private boolean vip;
    private boolean managerApproved;
    private boolean seasonalDiscountAvailable;

    public Car(int id, String model, double price) {
        this.id = id;
        this.model = model;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public double getPrice() {
        return price;
    }

    public void setSunroof(boolean sunroof) {
        this.sunroof = sunroof;
    }

    public void setSportPackage(boolean sportPackage) {
        this.sportPackage = sportPackage;
    }

    public boolean isVip() {
        return vip;
    }

    public void setVip(boolean vip) {
        this.vip = vip;
    }

    public boolean isManagerApproved() {
        return managerApproved;
    }

    public void setManagerApproved(boolean managerApproved) {
        this.managerApproved = managerApproved;
    }

    public boolean isSeasonalDiscountAvailable() {
        return seasonalDiscountAvailable;
    }

    public void setSeasonalDiscountAvailable(boolean seasonalDiscountAvailable) {
        this.seasonalDiscountAvailable = seasonalDiscountAvailable;
    }

    public String getDescription() {
        StringBuilder description = new StringBuilder(model + " ($" + price + ")");
        if (sunroof) description.append(", with Sunroof");
        if (sportPackage) description.append(", with Sport Package");
        return description.toString();
    }

    @Override
    public String toString() {
        return getDescription();
    }
}
