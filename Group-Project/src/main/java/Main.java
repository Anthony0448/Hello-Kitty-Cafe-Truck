public class Main {
    public static void main(String[] args) {
        new Frame1();
        new Frame2();

/*        try {
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