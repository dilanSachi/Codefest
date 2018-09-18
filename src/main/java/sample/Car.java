package sample;

public class Car {
    private int id;
    private String model;
    private String manufacturer;
    private int quantity;
    private String year;

    public Car() {
        super();
    }

    public Car(int id, String model,
               String manufacturer,
               int quantity, String year) {
        super();
        this.id = id;
        this.model = model;
        this.manufacturer = manufacturer;
        this.quantity = quantity;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}