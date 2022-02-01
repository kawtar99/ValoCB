import com.opencsv.CSVReader;
import entity.*;
import service.CSVHelper;
import service.ClientTreatment;
import service.PortfolioTreatment;

import java.util.*;
import java.util.stream.Collectors;

public class ValoCBApplication {

    public static void main(String[] args) throws Exception{
        System.out.println("Hello Server!");

        // Reading Prices.csv
        CSVReader pricesReader = CSVHelper.readCSVFile(System.getProperty("user.dir") +"\\src\\main\\resources\\Prices.csv", 4);
        List<PriceInput> priceInputs = CSVHelper.fromCSVToPricePOJO(pricesReader);

        // Reading Product.csv
        CSVReader productsReader = CSVHelper.readCSVFile(System.getProperty("user.dir") +"\\src\\main\\resources\\Product.csv", 5);
        List<ProductInput> productInputs = CSVHelper.fromCSVToProductPOJO(productsReader);


        // Getting the prices by portfolio Name
        Map<String, List<PriceInput>> pricesByPortfolioName = PortfolioTreatment.fromInputToPortfolioMapping(priceInputs);

        // Getting the prices by portfolio name AND product name
        Map<String, Map<String, List<Underlying>>> pricesByPortfolioByProduct = PortfolioTreatment.fromPortfolioToPortfolioProductMapping(pricesByPortfolioName);

        // Getting the list of products by portfolio name
        Map<String, List<Product>> productsByPortfolio = PortfolioTreatment.getProductsByPortfolio(pricesByPortfolioByProduct);

        // Getting products quantity
        Map<String, Integer> productQuantities = ClientTreatment.getProductsQuantity(productInputs);

        // Getting list of portfolios to calculate their prices
        List<Data> portfolios = PortfolioTreatment.getPortfolios(productsByPortfolio, productQuantities);

        // Calculate portfolio prices
        Map<String, Double> portfolioPrices = PortfolioTreatment.getDataPrices(portfolios);

        // Writing results to Reporting-portfolio.csv file
        CSVHelper.writeIntoCSVFile("C:\\Users\\Kawta\\IdeaProjects\\valoCb\\src\\main\\resources\\Reporting-portfolio.csv", portfolioPrices);
        // TODO : maybe refactor getCapital() and evaluatePortfolio()

        List<Product> products = PortfolioTreatment.getAllProducts(productsByPortfolio);
        Map<String, List<ProductInput>> productInputsByClient = ClientTreatment.getProductInputsByClient(productInputs);

        // Search for the corresponding Product entities
        Map<String, List<Product>> clientProducts = ClientTreatment.getProductsByClient(productInputsByClient, products);

        // Getting Product quantity by Client
        // This step is not necessary for portfolio since a product can only be in one portfolio
        // So the total quantity of a product is equal to the total quantity of a product by portfolio
        Map<String, Map<String, Integer>> productQuantitiesByClient = ClientTreatment.getProductQuantitiesByClient(productInputs);


        List<Data> clients = ClientTreatment.getClients(clientProducts, productQuantitiesByClient);

        // Calculating results and writing it to file Reporting-client.csv
        Map<String, Double> clientCapital = ClientTreatment.getDataPrices(clients);
        CSVHelper.writeIntoCSVFile("C:\\Users\\Kawta\\IdeaProjects\\valoCb\\src\\main\\resources\\Reporting-client.csv", clientCapital);
    }

}
