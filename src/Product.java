public class Product {
    private String product_name;
    private int product_code;
    private double price;
    private String description;

    public Product(String product_name, int product_code, double price, String description) {
        this.product_name = product_name;
        this.product_code = product_code;
        this.price = price;
        this.description = description;
    }

    public int getProductCode() {
        return product_code;
    }

    public void setProductCode(int productCode) {
        this.product_code = productCode;
    }

    public String getProductName() {
        return product_name;
    }

    public void setProductName(String productName) {
        this.product_name = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
