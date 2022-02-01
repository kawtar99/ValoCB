package entity;

import com.opencsv.exceptions.CsvException;

import java.io.IOException;
import java.util.Map;

public class Data {

    protected String name;
    protected Map<Product, Integer> products;

    public Data(String name, Map<Product, Integer> products) {
        this.name = name;
        this.products = products;
    }

    public String getName() {
        return name;
    }

    public Double evaluateAmount() {
        return this.products.entrySet()
                .stream()
                .mapToDouble(x ->  x.getKey().getPrice() * x.getValue()) // calculate price of total products
                .sum();
    }
}
