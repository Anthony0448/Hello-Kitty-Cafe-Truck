public class Main {
    public static void main(String[] args){
        new Frame1();
        new Frame2();

        JsonToObject jsonToObject = new JsonToObject();

        System.out.println("Store Name: " + jsonToObject.storeInfo.getStore_name());
        System.out.println("Phone Number: " + jsonToObject.storeInfo.getPhone_number());
        System.out.println("City: " + jsonToObject.storeInfo.getCity());
        System.out.println("State: " + jsonToObject.storeInfo.getState());
        System.out.println("Tax Percentage: " + jsonToObject.storeInfo.getTax_percentage());

        for (Product product : jsonToObject.listOfProducts) {
            System.out.println("Product Name: " + product.getProductName());
            System.out.println("Product Code: " + product.getProductCode());
            System.out.println("Price: " + product.getPrice());
            System.out.println("Description: " + product.getDescription());
            System.out.println("---------------");
        }
    }
}