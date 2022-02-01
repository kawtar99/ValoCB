package entity;

import com.opencsv.exceptions.CsvException;
import service.Forex;

import java.io.IOException;
import java.util.List;

public class Product {

    private String name;
    private List<Underlying> underlyings;

    public Product(String name, List<Underlying> underlyings) {
        this.name = name;
        this.underlyings = underlyings;
    }

    public Double getPrice(){
        return underlyings.stream()
                .map( u -> {
                    return u.getPrice() * Forex.evaluate(u.getCurrency());
                })
                .reduce(0.0d, (a,b) -> a+b);
    }

    public String getName() {
        return name;
    }
}
