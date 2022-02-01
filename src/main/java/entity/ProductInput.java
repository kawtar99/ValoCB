package entity;

public class ProductInput {

    private String productName;
    private String clientName;
    private Integer quantity;

    public ProductInput(String productName, String clientName, Integer quantity) {
        this.productName = productName;
        this.clientName = clientName;
        this.quantity = quantity;
    }

    public String getProductName() {
        return productName;
    }

    public String getClientName() {
        return clientName;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
