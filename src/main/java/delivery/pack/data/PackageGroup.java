package delivery.pack.data;

import com.google.common.collect.Lists;
import com.google.common.collect.RangeMap;

import java.util.List;

public class PackageGroup {
    private List<Package> packages;
    private Double weight;
    private Double totalFee;

    public PackageGroup() {
        packages = Lists.newLinkedList();
        weight = 0.0;
        totalFee = 0.0;
    }

    public void addPackage(Package item, Double fee) {
        packages.add(item);
        weight += item.getWeight();
        totalFee += fee != null ? fee : 0.0;
    }

    public Double getWeight() {
        return weight;
    }

    public String getPostalCode() {
        return this.packages.get(0).getPostalCode();
    }

    public String printWithFee() {
        return String.format("%s %.2f", this.printWithoutFee(), totalFee);
    }

    public String printWithoutFee() {
        return String.format("%s %.3f", this.getPostalCode(), weight);
    }

    public List<Package> getPackages() {
        return packages;
    }
}
