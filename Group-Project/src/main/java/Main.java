import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws IOException {
        //new Frame1();
        //new Frame2();


        new JsonToObject();

/*        // JSON test
        try {
            //String content = new String(Files.readAllBytes(Paths.get("src/main/resources/Json.json")));
            String content = new String(Files.readAllBytes(Paths.get(Main.class.getClassLoader().getResource("Json.json").toURI())));


            JSONObject jsonObject = new JSONObject(content);

            //JSONObject storeInfoJson = jsonObject.getJSONObject("product_info");
            JSONArray productInfoArray = jsonObject.getJSONArray("product_info");

            StoreInfo storeInfo = new StoreInfo();

            storeInfo.setStore_name("store_name");

            System.out.println(jsonObject.toString());

        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }*/
    }
}