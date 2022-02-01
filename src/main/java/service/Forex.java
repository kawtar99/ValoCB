package service;

import com.opencsv.exceptions.CsvException;
import exception.InvalidForexException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.MissingFormatWidthException;

public class Forex {

    static String forexFile = "C:\\Users\\Kawta\\IdeaProjects\\valoCb\\src\\main\\resources\\Forex.csv";
    static List<String[]> forexMatrix;

    static {
        try {
            forexMatrix = CSVHelper.readCSVFile(forexFile, 5).readAll();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvException e) {
            e.printStackTrace();
        }
    }


    public static Double evaluate(String currency) {
        if ("EUR".equals(currency)){
            return 1.0d;
        }

        if (!forexMatrix.stream().flatMap(x -> Arrays.stream(x)).toList().contains(currency)){
            throw new InvalidForexException("Invalid Currency : " + currency);
        }

        for (String[] line : forexMatrix){
            if (currency.equals(line[0])){
                return Double.valueOf(line[2].replaceAll(",", "."));
            }
            if (currency.equals(line[1])){
                return 1.0d/Double.valueOf(line[2].replaceAll(",", "."));
            }
        }
        return 1.0d;
    }
}
