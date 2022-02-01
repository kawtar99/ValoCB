package entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import service.Forex;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    Underlying u1;
    Underlying u2;

    @BeforeEach
    public void setUp(){
        u1 = Mockito.mock(Underlying.class);
        u2 = Mockito.mock(Underlying.class);

        Mockito.when(u1.getPrice()).thenReturn(100f);
        Mockito.when(u2.getPrice()).thenReturn(20f);
        Mockito.when(u1.getCurrency()).thenReturn("EUR");
        Mockito.when(u2.getCurrency()).thenReturn("USD");
    }

    @Test
    void getPrice() {
        Product p = new Product("P1", Arrays.asList(u1, u2));
        Double result = p.getPrice();
        Double expectedResult = 100f * 1d + 20f * 0.5d;

        assertEquals(result, expectedResult);
    }
}