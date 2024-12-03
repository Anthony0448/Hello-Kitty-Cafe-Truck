import java.util.List;

// A class to pass the information from the JSON with the store data
public class StoreData {
    private StoreInfo store_info;
    private List<Product> product_info;

    public StoreInfo getStore_info() {
        return store_info;
    }

    public void setStore_info(StoreInfo store_info) {
        this.store_info = store_info;
    }

    public List<Product> getProduct_info() {
        return product_info;
    }

    public void setProduct_info(List<Product> product_info) {
        this.product_info = product_info;
    }
}