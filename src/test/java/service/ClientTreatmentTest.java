package service;

import entity.Data;
import entity.Product;
import entity.ProductInput;
import exception.ProductNotFountException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ClientTreatmentTest {

    ProductInput pInput1;
    ProductInput pInput2;
    Product p1;
    Product p2;

    @BeforeEach
    public void setUp(){
        pInput1 = Mockito.mock(ProductInput.class);
        pInput2 = Mockito.mock(ProductInput.class);

        Mockito.when(pInput1.getProductName()).thenReturn("P1");
        Mockito.when(pInput2.getProductName()).thenReturn("P2");

        p1 = Mockito.mock(Product.class);
        p2 = Mockito.mock(Product.class);

        Mockito.when(p1.getName()).thenReturn("P1");
        Mockito.when(p2.getName()).thenReturn("P2");

    }

    @Test
    void getProductInputsByClient() {
        Mockito.when(pInput1.getClientName()).thenReturn("C1");
        Mockito.when(pInput2.getClientName()).thenReturn("C1");

        Map<String, List<ProductInput>> result = ClientTreatment.getProductInputsByClient(Arrays.asList(pInput1, pInput2));
        assertEquals(result.size(), 1);
        assertTrue(result.keySet().contains("C1"));
        assertEquals(result.get("C1").size(), 2);
    }

    @Test
    void getProductsQuantity() {

        Mockito.when(pInput1.getQuantity()).thenReturn(1);
        Mockito.when(pInput2.getQuantity()).thenReturn(22);

        Map<String, Integer> result = ClientTreatment.getProductsQuantity(Arrays.asList(pInput1, pInput2));
        assertEquals(result.size(), 2);
        assertEquals(result.get("P1"), 1);
        assertEquals(result.get("P2"), 22);
    }

    @Test
    void getProductQuantitiesByClient() {
        Mockito.when(pInput1.getClientName()).thenReturn("C1");
        Mockito.when(pInput2.getClientName()).thenReturn("C2");

        Map<String, Map<String, Integer>> result = ClientTreatment.getProductQuantitiesByClient(Arrays.asList(pInput1, pInput2));

        assertEquals(result.size(), 2);
        assertTrue(result.keySet().contains("C1"));
        assertTrue(result.keySet().contains("C2"));
    }

    @Test
    void getProductsByClient() {


        Map<String, List<ProductInput>> productsByClients = new HashMap<>();
        productsByClients.put("C1", Arrays.asList(pInput1));
        productsByClients.put("C2", Arrays.asList(pInput2));

        Map<String, List<Product>> result = ClientTreatment.getProductsByClient(productsByClients, Arrays.asList(p1,p2));
        assertEquals(result.size(), 2);
        assertTrue(result.keySet().contains("C1"));
        assertTrue(result.keySet().contains("C2"));
        assertEquals(result.get("C1").size(), 1);
        assertEquals(result.get("C2").size(), 1);
    }

    @Test
    void getClients() {
        Map<String, List<Product>> clientProducts = new HashMap<>();
        clientProducts.put("C1", Arrays.asList(p1, p2));

        Map<String, Integer> quantities = new HashMap<>();
        quantities.put("P1", 10);
        quantities.put("P2", 20);

        Map<String, Map<String, Integer>> productQuantitiesByClient = new HashMap<>();
        productQuantitiesByClient.put("C1", quantities);

        List<Data> result = ClientTreatment.getClients(clientProducts,productQuantitiesByClient);

        assertEquals(result.size(), 1);
        assertEquals(result.get(0).getName(), "C1");
    }

    @Test
    void containsNameTrue() {
        assertTrue(
                ClientTreatment.containsName(Arrays.asList(p1, p2), "P1")
        );
    }

    @Test
    void containsNameFalse() {
        assertFalse(
                ClientTreatment.containsName(Arrays.asList(p1, p2), "P4")
        );
    }


    @Test
    void getProductByName() {
        Product result = ClientTreatment.getProductByName(Arrays.asList(p1, p2), "P2");
        assertEquals(result.getName(), "P2");
    }

    @Test
    void getProductByNameNotFound() {
        ProductNotFountException thrown = assertThrows(
                ProductNotFountException.class,
                () -> ClientTreatment.getProductByName(Arrays.asList(p1, p2), "P3")
        );

        assertTrue(thrown.getMessage().contains("Product P3 is not found."));
    }
}