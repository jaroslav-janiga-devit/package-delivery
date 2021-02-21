package delivery.pack.data;

public class Package {
    private String postalCode;
    private Double weight;

    public Package(String postalCode, Double weight) {
        this.postalCode = postalCode;
        this.weight = weight;
    }

    public Double getWeight() {
        return this.weight;
    }

    public String getPostalCode(){
        return this.postalCode;
    }
}
