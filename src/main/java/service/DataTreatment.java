package service;

import entity.Data;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DataTreatment {

    public static Map<String, Double> getDataPrices(List<Data> data){
        return data.stream()
                .collect(Collectors.groupingBy(
                        Data::getName,
                        Collectors.mapping(
                                Data::evaluateAmount,
                                Collectors.summingDouble(total -> total)
                        )
                ));
    }
}
