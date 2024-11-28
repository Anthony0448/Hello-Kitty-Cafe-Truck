public class StoreInfo {
    private String store_name;
    private String phone_number;
    private String state;
    private String city;
    private double tax_percentage;

    public StoreInfo(String store_name, String phone_number, String state, String city, double tax_percentage) {
        this.store_name = store_name;
        this.phone_number = phone_number;
        this.city = city;
        this.state = state;
        this.tax_percentage = tax_percentage;
    }

    public String getStore_name() {
        return store_name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public double getTax_percentage() {
        return tax_percentage;
    }
}
