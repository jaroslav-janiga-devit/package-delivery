package delivery.pack;

import com.google.common.collect.Maps;
import com.google.common.collect.RangeMap;
import delivery.pack.data.Package;
import delivery.pack.data.PackageGroup;
import delivery.pack.exception.UserNotificationException;
import delivery.pack.util.Verifier;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * package manager responsible for adding packages
 * contains all necessary logic to add and print packages
 */
public class PackageManager {
    private Map<String, PackageGroup> packages;

    // key = weight range, value = fee value
    private RangeMap<Double, Double> fees;

    public PackageManager() {
        this.packages = Maps.newHashMap();
        this.fees = null;
    }

    public void addPackage(String inputLine) {
        if (Verifier.checkPackageInput(inputLine)) {
            String[] packageInfo = inputLine.split(" ");
            Double weight = Double.parseDouble(packageInfo[0]);
            String postalCode = packageInfo[1];

            Package newOne = new Package(postalCode, weight);
            Double fee = computeFee(newOne);

            this.packages
                    .computeIfAbsent(postalCode, (key) -> new PackageGroup())
                    .addPackage(newOne, fee);

        } else {
            throw new UserNotificationException("Wrong package format. Expected format: " + Verifier.PACKAGE_FORMAT_DEFINITION);
        }
    }

    public void printPackages() {
        if (fees == null) {
            packages.values()
                    .stream()
                    .sorted(Comparator.comparingDouble(PackageGroup::getWeight)
                            .reversed())
                    .forEach((pg) -> System.out.println(pg.printWithoutFee()));
        } else {
            packages.values()
                    .stream()
                    .sorted(Comparator.comparingDouble(PackageGroup::getWeight)
                            .reversed())
                    .forEach((pg) -> System.out.println(pg.printWithFee()));
        }

    }

    public void addPackages(List<String> packages){
        packages.forEach(this::addPackage);
    }

    private Double computeFee(Package item) {
        if (fees == null) return 0.0;
        Double fee = fees.get(item.getWeight());
        return fee == null ? 0.0 : fee;
    }

    public void setFees(RangeMap<Double, Double> fees) {
        this.fees = fees;
    }

    public Map<String, PackageGroup> getPackages() {
        return packages;
    }

    public RangeMap<Double, Double> getFees() {
        return fees;
    }
}
