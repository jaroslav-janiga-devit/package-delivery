package delivery.pack;

import delivery.pack.data.Package;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class PackageManagerTest {

    @Test
    public void testAddPackage(){
        String packageItem = "2.24 00678";
        PackageManager packageManager = new PackageManager();
        packageManager.addPackage(packageItem);
        assertEquals(1,packageManager.getPackages().size());

        Package p = packageManager.getPackages().get("00678").getPackages().get(0);
        assertEquals("00678",p.getPostalCode());
        assertEquals(new Double(2.24),p.getWeight());
    }

    @Test
    public void testAddPackageWhenFeesProvided(){
        String packageItem = "2.24 00678";
        PackageManager packageManager = new PackageManager();

        packageManager.addPackage(packageItem);
        assertEquals(1,packageManager.getPackages().size());

        Package p = packageManager.getPackages().get("00678").getPackages().get(0);
        assertEquals("00678",p.getPostalCode());
        assertEquals(new Double(2.24),p.getWeight());
    }
}
