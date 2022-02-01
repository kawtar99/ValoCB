package service;

import entity.Data;
import entity.PriceInput;
import entity.Product;
import entity.Underlying;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class PortfolioTreatmentTest {

    PriceInput priceInput1 = Mockito.mock(PriceInput.class);
    PriceInput priceInput2 = Mockito.mock(PriceInput.class);
    List<PriceInput> inputs = new ArrayList<>();

    @BeforeEach
    public void setUp(){

        inputs.add(priceInput1);
        inputs.add(priceInput2);
    }

    @Test
    void fromInputToPortfolioMappingWithTwoPortfolios() {



        Mockito.when(priceInput1.getPortfolioName()).thenReturn("PTF1");
        Mockito.when(priceInput2.getPortfolioName()).thenReturn("PTF2");

        Map<String, List<PriceInput>> result = PortfolioTreatment.fromInputToPortfolioMapping(inputs);

        assertEquals(result.size(), 2);
    }

    @Test
    void fromInputToPortfolioMappingWithOnePortfolios() {


        Mockito.when(priceInput1.getPortfolioName()).thenReturn("PTF1");
        Mockito.when(priceInput2.getPortfolioName()).thenReturn("PTF1");

        Map<String, List<PriceInput>> result = PortfolioTreatment.fromInputToPortfolioMapping(inputs);

        assertEquals(result.size(), 1);
    }

    @Test
    void fromPortfolioToPortfolioProductMapping() {

        Underlying u1 = Mockito.mock(Underlying.class);
        Underlying u2 = Mockito.mock(Underlying.class);

        Mockito.when(priceInput1.getPortfolioName()).thenReturn("PTF1");
        Mockito.when(priceInput2.getPortfolioName()).thenReturn("PTF2");
        Mockito.when(priceInput1.getUnderlying()).thenReturn(u1);
        Mockito.when(priceInput2.getUnderlying()).thenReturn(u2);
        Mockito.when(priceInput1.getProductName()).thenReturn("P1");
        Mockito.when(priceInput2.getProductName()).thenReturn("P2");

        Map<String, List<PriceInput>> input = new HashMap<>();
        input.put("PTF1", Arrays.asList(priceInput1));
        input.put("PTF2", Arrays.asList(priceInput2));

        Map<String, Map<String, List<Underlying>>> result = PortfolioTreatment.fromPortfolioToPortfolioProductMapping(input);
        assertEquals(result.size(), 2);
        assertTrue(result.keySet().contains("PTF1"));
        assertTrue(result.keySet().contains("PTF2"));
        assertEquals(result.get("PTF1").size(), 1);
        assertEquals(result.get("PTF2").size(), 1);
        assertTrue(result.get("PTF1").keySet().contains("P1"));
        assertTrue(result.get("PTF2").keySet().contains("P2"));
    }

    @Test
    void getProductsByPortfolio() {

        Underlying u1 = Mockito.mock(Underlying.class);
        Underlying u2 = Mockito.mock(Underlying.class);

        Map<String, Map<String, List<Underlying>>> input = new HashMap<>();
        Map<String, List<Underlying>> nestedMap = new HashMap<>();
        nestedMap.put("P1", Arrays.asList(u1));
        nestedMap.put("P2", Arrays.asList(u2));
        input.put("PTF1",  nestedMap);

        Map<String, List<Product>> result = PortfolioTreatment.getProductsByPortfolio(input);
        assertEquals(result.size(), 1);
        assertEquals(result.get("PTF1").size(), nestedMap.size());
    }


    @Test
    void getPortfolios() {
        Product p01 = Mockito.mock(Product.class);
        Product p02 = Mockito.mock(Product.class);
        Product p11 = Mockito.mock(Product.class);
        Product p12 = Mockito.mock(Product.class);

        Mockito.when(p01.getName()).thenReturn("P01");
        Mockito.when(p02.getName()).thenReturn("P02");
        Mockito.when(p11.getName()).thenReturn("P11");
        Mockito.when(p12.getName()).thenReturn("P12");

        Map<String, List<Product>> productsByPortfolio = new HashMap<>();
        productsByPortfolio.put("PTF1", Arrays.asList(p01, p02));
        productsByPortfolio.put("PTF2", Arrays.asList(p11, p12));

        Map<String, Integer> productQuantities = new HashMap<>();
        productQuantities.put("P01", 1);
        productQuantities.put("P02", 1);
        productQuantities.put("P11", 1);
        productQuantities.put("P12", 1);
        List<Data> result = PortfolioTreatment.getPortfolios(productsByPortfolio, productQuantities);

        assertEquals(result.size(), 2);
    }

    @Test
    void getAllProducts() {
        Product p01 = Mockito.mock(Product.class);
        Product p02 = Mockito.mock(Product.class);

        Mockito.when(p01.getName()).thenReturn("P01");
        Mockito.when(p02.getName()).thenReturn("P02");

        Map<String, List<Product>> productsByPortfolio = new HashMap<>();
        productsByPortfolio.put("PTF1", Arrays.asList(p01, p02));

        List<Product> result = PortfolioTreatment.getAllProducts(productsByPortfolio);

        assertEquals(result.size(), 2);

    }
}