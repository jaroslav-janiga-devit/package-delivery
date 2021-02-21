package delivery.pack.util;

import com.google.common.collect.*;
import delivery.pack.PackageManager;
import delivery.pack.data.Fee;
import delivery.pack.exception.UserNotificationException;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class FileReader {

    private static List<String> readFromFile(String fileName) {
        try {
            return Files.readAllLines(Paths.get((new File(fileName)).getAbsolutePath()), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new UserNotificationException("File cannot be read because: " + e.getMessage());
        }
    }

    /**
     * reads packages in specified format {@link Verifier#PACKAGE_FORMAT_DEFINITION}
     *
     * @param args    command line arguments
     * @param manager package manager which will be filled up by packages
     */
    public static void readPackagesFromFile(String[] args, PackageManager manager) {
        if (args.length < 1) {
            System.out.println("Packages not provided.");
            return;
        }
        manager.addPackages(readFromFile(args[0]));
        System.out.println("Packages has been read from file.");
    }

    /**
     * reading all fees with specified range
     * example fees
     * 10 5.00
     * 5 2.50
     * 3 2.00
     * returned range map
     * interval(key) - value
     * <3, 5) - 2.00
     * <5,10) - 2.50
     * <10,infinity) - 5.00
     *
     * @param args command line arguments
     * @return
     */
    public static void readFeesFromFile(String[] args, PackageManager manager) {
        if (args.length < 2) {
            System.out.println("Fees not provided.");
            return;
        }
        try {
            ImmutableRangeMap.Builder feeMapBuilder = ImmutableRangeMap.builder();
            List<Fee> sortedFees = readFromFile(args[1])
                    .stream()
                    .map(FileReader::readFee)
                    .sorted(Comparator.comparingDouble(Fee::getWeight))
                    .collect(Collectors.toList());

            for (int i = 0; i < sortedFees.size(); i++) {
                Double lower = sortedFees.get(i).getWeight();
                Double upper = (i < sortedFees.size() - 1)
                        ? sortedFees.get(i + 1).getWeight()
                        : Double.MAX_VALUE;
                Range range = Range.range(lower,
                        BoundType.CLOSED,
                        upper,
                        BoundType.OPEN);
                feeMapBuilder.put(range, sortedFees.get(i).getFee());
            }

            manager.setFees(feeMapBuilder.build());
            System.out.println("Fees has been read from file.");
        } catch (UserNotificationException une) {
            System.out.println("Fees are not loaded because: " + une.getDescription());
        }

    }

    /**
     * reading fee based on the specified pattern
     *
     * @param line string containing the fee information to read
     * @return
     */
    private static Fee readFee(String line) {
        if (Verifier.checkFeeInput(line)) {
            String[] feeInfo = line.split(" ");
            Double weight = Double.parseDouble(feeInfo[0]);
            Double fee = Double.parseDouble(feeInfo[1]);
            return new Fee(weight, fee);
        } else {
            throw new UserNotificationException("Fee format is wrong");
        }
    }


}
