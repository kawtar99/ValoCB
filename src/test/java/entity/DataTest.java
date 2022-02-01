package entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class DataTest {

    Product p1;
    Product p2;
    Product p3;

    @BeforeEach
    public void setUp(){
        p1 = Mockito.mock(Product.class);
        p2 = Mockito.mock(Product.class);
        p3 = Mockito.mock(Product.class);

        Mockito.when(p1.getPrice()).thenReturn(30d);
        Mockito.when(p2.getPrice()).thenReturn(20d);
        Mockito.when(p3.getPrice()).thenReturn(10d);
    }

    @Test
    void evaluateAmount() {
        Map<Product, Integer> products = new HashMap<>();
        products.put(p1, 10);
        products.put(p2, 50);
        products.put(p3, 5);

        Data portfolio = new Data("PTF", products);

        Double result = portfolio.evaluateAmount();
        Double expectedResult = 10d*30 + 20d*50 + 10d* 5;

        assertEquals(result, expectedResult);
    }

    @Test
    void evaluateAmountNewClient() {
        Map<Product, Integer> products = new HashMap<>();

        Data newClient = new Data("C3", products);

        Double result = newClient.evaluateAmount();

        assertEquals(result, 0d);
    }
}