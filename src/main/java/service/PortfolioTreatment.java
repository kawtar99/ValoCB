package service;

import entity.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PortfolioTreatment extends DataTreatment{

    public static Map<String, List<PriceInput>> fromInputToPortfolioMapping(List<PriceInput> priceInputs){
        return priceInputs.stream()
                .collect(Collectors.groupingBy(x -> x.getPortfolioName()));
    }

    public static Map<String, Map<String, List<Underlying>>> fromPortfolioToPortfolioProductMapping(Map<String, List<PriceInput>> pricesByPortfolioName){
        return pricesByPortfolioName.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getValue()
                                .stream()
                                .collect(Collectors.groupingBy(
                                        PriceInput::getProductName,
                                        Collectors.mapping(
                                                PriceInput::getUnderlying,
                                                Collectors.toList()
                                        )
                                ))
                ));
    }

    public static Map<String, List<Product>> getProductsByPortfolio(Map<String, Map<String, List<Underlying>>> pricesByPortfolioByProduct){
        return pricesByPortfolioByProduct.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getValue()
                                .entrySet()
                                .stream()
                                .map(x -> new Product(x.getKey(), x.getValue()))
                                .collect(Collectors.toList())
                ));
    }



    public static List<Data> getPortfolios(Map<String, List<Product>> productsByPortfolio, Map<String, Integer> productQuantities){
        return productsByPortfolio.entrySet()
                .stream()
                .map(x -> new Data(x.getKey(),
                        x.getValue().stream()
                                .collect(Collectors.toMap(
                                        e -> e,
                                        e -> productQuantities.get(e.getName())
                                ))
                )).toList();
    }



    public static List<Product> getAllProducts(Map<String, List<Product>> productsByPortfolio){
        return productsByPortfolio.values().stream().flatMap(x -> x.stream()).collect(Collectors.toList());
    }

}
