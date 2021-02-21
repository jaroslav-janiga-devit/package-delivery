package delivery.pack.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Verifier {

    public static final String PACKAGE_FORMAT_DEFINITION = "<weight: positive number, >0, maximal 3 decimal places, . (dot) as decimal><separator><space><postal code: fixed 5 digits>";
    private static final Pattern PACKAGE_PATTERN = Pattern.compile("^[0-9]{1,13}(.[0-9]{1,3})? [\\d]{5}$");
    private static final Pattern FEE_PATTERN = Pattern.compile("^[0-9]{1,13}(.[0-9]{1,3})? [0-9]{1,13}(.[0-9]{1,3})?$");

    private Verifier() {
    }

    public static boolean checkPackageInput(String input) {
        Matcher matcher = PACKAGE_PATTERN.matcher(input);
        return matcher.matches();
    }

    public static boolean checkFeeInput(String input) {
        Matcher matcher = FEE_PATTERN.matcher(input);
        return matcher.matches();
    }

}
