package delivery.pack.data;

public class Fee {

    public Fee(Double weight, Double fee) {
        this.weight = weight;
        this.fee = fee;
    }

    private Double weight;
    private Double fee;

    public Double getWeight() {
        return weight;
    }

    public Double getFee() {
        return fee;
    }
}
