package service;

import entity.Data;
import entity.Product;
import entity.ProductInput;
import exception.ProductNotFountException;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ClientTreatment extends DataTreatment{

    public static Map<String, List<ProductInput>> getProductInputsByClient(List<ProductInput> productInputs){
        return productInputs
                .stream()
                .collect(
                        Collectors.groupingBy(ProductInput::getClientName));
    }


    public static Map<String, Integer> getProductsQuantity(List<ProductInput> productInputs){
        return productInputs.stream().collect(
                Collectors.groupingBy(ProductInput::getProductName, Collectors.summingInt(ProductInput::getQuantity)));
    }


    public static Map<String, Map<String, Integer>> getProductQuantitiesByClient(List<ProductInput> productInputs){
        return productInputs.stream()
                .collect(Collectors.groupingBy(
                        ProductInput::getClientName,
                        Collectors.groupingBy(
                                ProductInput::getProductName,
                                Collectors.mapping(
                                        ProductInput::getQuantity,
                                        Collectors.summingInt(total -> total)
                                )
                        )


                ));
    }

    public static Map<String, List<Product>> getProductsByClient(Map<String, List<ProductInput>> productInputsByClient, List<Product> products){
        return productInputsByClient.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getValue()
                                .stream()
                                .filter(x -> containsName(products, x.getProductName()))
                                .map(x -> getProductByName(products, x.getProductName()))
                                .collect(Collectors.toList())

                ));
    }


    public static List<Data> getClients(Map<String, List<Product>> clientProducts, Map<String, Map<String, Integer>> productQuantitiesByClient){
        return clientProducts.entrySet()
                .stream()
                .map(x -> new Data(x.getKey(),
                        x.getValue().stream()
                                .collect(Collectors.toMap(
                                        e -> e,
                                        e -> productQuantitiesByClient.get(x.getKey()).get(e.getName())
                                ))
                ))
                .collect(Collectors.toList());
    }

    public static boolean containsName(List<Product> products, String productName){
        return products.stream().anyMatch(o -> o.getName().equals(productName));
    }

    public static Product getProductByName(List<Product> products, String productName){

        return products.stream().filter(o -> o.getName().equals(productName))
                .findFirst()
                .orElseThrow(() -> new ProductNotFountException("Product "+ productName +" is not found."));
    }
}
