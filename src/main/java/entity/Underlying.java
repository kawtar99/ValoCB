package entity;

public class Underlying {

    private String name;
    private String currency;
    private Float price;

    public Underlying(String name, String currency, Float price) {
        this.name = name;
        this.currency = currency;
        this.price = price;
    }

    public float getPrice() {
        return price;
    }

    public String getCurrency() {
        return currency;
    }
}
