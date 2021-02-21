package delivery.pack.util;

import delivery.pack.PackageManager;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Printer which is set to write actual package state every minute
 */
public class StatePrinter implements Runnable{

    private PackageManager packageManager;
    private Thread worker;
    private AtomicBoolean control;

    public StatePrinter(PackageManager packageManager) {
        this.packageManager = packageManager;
        this.control = new AtomicBoolean(true);
    }

    public void start () {
        this.worker = new Thread(this);
        worker.start();
    }

    public void stop(){
        this.control.set(false);
    }

    @Override
    public void run() {
        while (control.get()){
            try {
                Thread.sleep(60000);
                packageManager.printPackages();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
