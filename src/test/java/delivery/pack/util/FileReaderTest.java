package delivery.pack.util;

import delivery.pack.PackageManager;
import delivery.pack.exception.UserNotificationException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.containsString;

/**
 * this class contains simple unit tests for checking whether the file reading works properly
 */
public class FileReaderTest {

    @Test
    public void readPackagesTestSuccess(){
        PackageManager packageManager = new PackageManager();
        FileReader.readPackagesFromFile(new String[]{"src/test/resources/packages.txt"},packageManager);
        assertEquals(3,packageManager.getPackages().size());
    }

    @Test
    public void readPackagesTestFailNoFileProvided(){
        PackageManager packageManager = new PackageManager();
        FileReader.readPackagesFromFile(new String[]{},packageManager);
        assertEquals(0,packageManager.getPackages().size());
    }

    /**
     * asserting exception that says the file does not exists
     * there is a typo in the file name
     */
    @Test
    public void readPackagesTestFail(){
        PackageManager packageManager = new PackageManager();
        UserNotificationException userNotificationException = assertThrows(UserNotificationException.class, () -> {
            FileReader.readPackagesFromFile(new String[]{"src/test/resources/packags.txt"}, packageManager);
        });

        assertThat(userNotificationException.getDescription(),containsString("File cannot be read because: "));
    }

    @Test
    public void readFeesTestSuccess(){
        PackageManager packageManager = new PackageManager();
        FileReader.readFeesFromFile(new String[]{"","src/test/resources/fees.txt"},packageManager);
        assertEquals(7,packageManager.getFees().asMapOfRanges().size());
    }
}
