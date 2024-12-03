import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class JsonToObject {
    public List<Product> products;
    public StoreInfo storeInfo;

    public JsonToObject() {
        try {
            // Create ObjectMapper
            ObjectMapper objectMapper = new ObjectMapper();


            // Load JSON file from resources folder
            InputStream inputStream = Main.class.getClassLoader().getResourceAsStream("Json.json");
            if (inputStream == null) {
                throw new IllegalArgumentException("Where did Jason go?!");
            }

            Map<String, Object> data = objectMapper.readValue(inputStream, new TypeReference<Map<String, Object>>() {});

            storeInfo = objectMapper.convertValue(data.get("store_info"), StoreInfo.class);
            // Add each product object to a class list
            products = objectMapper.convertValue(data.get("product_info"), new TypeReference<List<Product>>() {});

            System.out.println("Store Name: " + storeInfo.getStore_name());
            System.out.println("Phone Number: " + storeInfo.getPhone_number());
            System.out.println("City: " + storeInfo.getCity());
            System.out.println("State: " + storeInfo.getState());
            System.out.println("Tax Percentage: " + storeInfo.getTax_percentage());

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
