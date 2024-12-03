import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.InputStream;
import java.util.List;

public class JsonToObject {
    public JsonToObject() {
        try {
            // Create ObjectMapper
            ObjectMapper objectMapper = new ObjectMapper();

            // Load JSON file from resources folder
            InputStream inputStream = Main.class.getClassLoader().getResourceAsStream("Json.json");
            if (inputStream == null) {
                throw new IllegalArgumentException("Where did Jason go?!");
            }

            StoreData storeData = objectMapper.readValue(inputStream, StoreData.class);

            // Extract the storeInfo object from storeData
            StoreInfo storeInfo = storeData.getStore_info();

            System.out.println("Store Name: " + storeInfo.getStore_name());
            System.out.println("Phone Number: " + storeInfo.getPhone_number());
            System.out.println("City: " + storeInfo.getCity());
            System.out.println("State: " + storeInfo.getState());
            System.out.println("Tax Percentage: " + storeInfo.getTax_percentage());

            // Access product information
            List<Product> products = storeData.getProduct_info();
            for (Product product : products) {
                System.out.println("Product Name: " + product.getProductName());
                System.out.println("Product Code: " + product.getProductCode());
                System.out.println("Price: " + product.getPrice());
                System.out.println("Description: " + product.getDescription());
                System.out.println("---------------");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
