package entity;

public class PriceInput {

    private String portfolioName;
    private String productName;
    private Underlying underlying;

    public PriceInput(String portfolioName, String productName, Underlying underlying) {
        this.portfolioName = portfolioName;
        this.productName = productName;
        this.underlying = underlying;
    }

    public String getPortfolioName() {
        return portfolioName;
    }

    public String getProductName() {
        return productName;
    }

    public Underlying getUnderlying() {
        return underlying;
    }
}
