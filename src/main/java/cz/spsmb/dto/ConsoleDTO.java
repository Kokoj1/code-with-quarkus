package cz.spsmb.dto;

public class ConsoleDTO {
    String brand;
    String type;


    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "JokeDTO{" +
                ", brand='" + brand + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
