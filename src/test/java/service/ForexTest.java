package service;


import exception.InvalidForexException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ForexTest {

    @BeforeEach
    public void setUp(){
        Forex.forexMatrix = new ArrayList<String[]>(3);
        Forex.forexMatrix.add(new String[]{"EUR", "CURRENCY1", "2"});
        Forex.forexMatrix.add(new String[]{"EUR", "CURRENCY2", "3"});
        Forex.forexMatrix.add(new String[]{"CURRENCY3", "EUR", "3"});
    }

    @Test
    public void testForexCurrency1(){
        Double forex = Forex.evaluate("CURRENCY1");
        assertEquals(forex, 0.5);
    }

    @Test
    public void testForexCurrency3(){
        Double forex = Forex.evaluate("CURRENCY3");
        assertEquals(forex, 3.0);
    }

    @Test
    public void testForexEUR(){
        Double forex = Forex.evaluate("EUR");
        assertEquals(forex, 1.0);
    }

    @Test
    public void testForexNotFount(){
        InvalidForexException thrown = assertThrows(
                InvalidForexException.class,
                () -> Forex.evaluate("CURRENCY4")
        );

        assertTrue(thrown.getMessage().contains("Invalid Currency : CURRENCY4"));
    }

}
