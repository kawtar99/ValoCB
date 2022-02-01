package service;

import entity.Data;
import entity.Product;
import entity.Underlying;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class DataTreatmentTest {


    @Test
    public void testDataPrices(){

        Data mockData = Mockito.mock(Data.class);

        List<Data> data = new ArrayList<>();
        data.add(mockData);
        Mockito.when(mockData.getName()).thenReturn("PTF1");
        Mockito.when(mockData.evaluateAmount()).thenReturn(100.0d);

        Map<String, Double> results = DataTreatment.getDataPrices(data);

        assertEquals(results.size(), 1);
        assertTrue(results.keySet().contains("PTF1"));
        assertEquals(results.get("PTF1"), 100);
    }
}
