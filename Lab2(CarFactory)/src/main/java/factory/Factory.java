package factory;

import collaborators.Dealer;
import collaborators.Supplier;
import factory.details.Accessory;
import factory.details.Body;
import factory.details.Car;
import factory.details.Engine;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Factory {
    private Properties properties;
    private Storage<Engine> engineStorage;
    private Storage<Body> bodyStorage;
    private Storage<Accessory> accessoryStorage;
    private Storage<Car> carStorage;
    private Supplier<Engine> engineSupplier;
    private Supplier<Body> bodySupplier;
    private ArrayList<Supplier<Accessory>> accessorySuppliers;
    private ArrayList<Dealer> dealers;
    private ThreadPoolExecutor workers;
    private StorageController controller;

    public Factory() throws IOException{
        properties = new Properties();
        InputStream input = this.getClass().getClassLoader().getResourceAsStream("config.properties");
        properties.load(input);
        engineStorage = new Storage<>(Integer.parseInt(properties.getProperty("EngineStorageSize")));
        bodyStorage = new Storage<>(Integer.parseInt(properties.getProperty("BodyStorageSize")));
        accessoryStorage = new Storage<>(Integer.parseInt(properties.getProperty("AccessoryStorageSize")));
        carStorage = new Storage<>(Integer.parseInt(properties.getProperty("CarStorageSize")));
        engineSupplier = new Supplier<>(engineStorage, Engine.class, Integer.parseInt(properties.getProperty("SupplierDelay")));
        bodySupplier = new Supplier<>(bodyStorage, Body.class, Integer.parseInt(properties.getProperty("SupplierDelay")));
        accessorySuppliers = new ArrayList<>();
        int supplierDelay = Integer.parseInt(properties.getProperty("SupplierDelay"));
        int numberOfAccessorySuppliers = Integer.parseInt(properties.getProperty("AccessorySuppliers"));
        for (int i = 0; i < numberOfAccessorySuppliers; i++){
            accessorySuppliers.add(new Supplier<Accessory>(accessoryStorage, Accessory.class, supplierDelay));
        }
        dealers = new ArrayList<>();
        int dealerDelay = Integer.parseInt(properties.getProperty("DealerDelay"));
        int numberOfDealers = Integer.parseInt(properties.getProperty("Dealers"));
        for (int i = 0; i < numberOfDealers; i++){
            dealers.add(new Dealer(carStorage, dealerDelay, i));
        }
        workers = (ThreadPoolExecutor) Executors.newFixedThreadPool(Integer.parseInt(properties.getProperty("Workers")));
        controller = new StorageController(carStorage, engineStorage, bodyStorage, accessoryStorage, workers, dealers);

        carStorage.addObserver(controller);
    }

    public void beginWork(){
        engineSupplier.start();
        bodySupplier.start();
        for (Supplier<Accessory> supplier : accessorySuppliers){
            supplier.start();
        }
        controller.init();
        for (Dealer dealer : dealers){
            dealer.start();
        }
    }

    public void endWork(){
        try {
            workers.shutdownNow();
            workers.awaitTermination(5, TimeUnit.SECONDS);
            for (Dealer dealer : dealers) {
                dealer.interrupt();
                dealer.join();
            }
            engineSupplier.interrupt();
            engineSupplier.join();
            bodySupplier.interrupt();
            bodySupplier.join();
            for (Supplier<Accessory> supplier : accessorySuppliers) {
                supplier.interrupt();
                supplier.join();
            }
        }
        catch (InterruptedException ignored){}
    }
}
