package delivery.pack;

import delivery.pack.exception.UserNotificationException;
import delivery.pack.util.FileReader;
import delivery.pack.util.StatePrinter;

import java.util.Scanner;

public class App {
    private static final String QUIT_COMMAND = "quit";

    public static void main(String[] args) {
        PackageManager packageManager = initialize(args);
        runDelivery(packageManager);

    }

    private static PackageManager initialize(String[] args){
        PackageManager packageManager = new PackageManager();
        FileReader.readFeesFromFile(args,packageManager);
        FileReader.readPackagesFromFile(args,packageManager);
        return packageManager;
    }


    private static void runDelivery(PackageManager packageManager) {
        Scanner scanner = new Scanner(System.in);
        StatePrinter printer = new StatePrinter(packageManager);
        printer.start();

        while (true) {
            try{
                String input = scanner.nextLine();
                if (QUIT_COMMAND.equals(input)) {
                    break;
                }
                packageManager.addPackage(input);
            }catch (UserNotificationException une){
                System.out.println(une.getDescription());
            } catch (Exception e){
                System.out.println("Something is really bad");
            }
        }
        printer.stop();
        System.out.println("Shutting down the application.");
        System.out.println("Have a nice day.");
    }

}
