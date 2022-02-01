package service;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import entity.PriceInput;
import entity.Product;
import entity.ProductInput;
import entity.Underlying;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class CSVHelper {

    public static CSVReader readCSVFile(String fileName, int skipedLines) throws FileNotFoundException {
        return new CSVReaderBuilder(new FileReader(fileName))
                .withSkipLines(skipedLines)
                .build();
    }

    public static List<PriceInput> fromCSVToPricePOJO(CSVReader reader) throws IOException, CsvException {
        return reader
                .readAll()
                .stream()
                .map(x -> new PriceInput(x[0], x[1], new Underlying(x[2], x[3], Float.valueOf(x[4]))))
                .toList();
    }

    public static List<ProductInput> fromCSVToProductPOJO(CSVReader reader) throws IOException, CsvException {
        return reader.readAll()
                .stream()
                .map(x -> new ProductInput(x[0], x[1], Integer.valueOf(x[2]))).toList();
    }


    public static void writeIntoCSVFile(String fileName, Map<String, Double> results) throws IOException {
        CSVWriter writer = new CSVWriter(new FileWriter(fileName));
        for (Map.Entry<String, Double>entry : results.entrySet()) {
            String[] line = new String[]{entry.getKey(), String.valueOf(entry.getValue())};
            writer.writeNext(line);
        }
        writer.close();
    }
}
