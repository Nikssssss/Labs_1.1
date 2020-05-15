package factory;

import factory.details.Accessory;
import factory.details.Body;
import factory.details.Car;
import factory.details.Engine;
import observers.Observer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StorageController implements Observer {
    private Storage<Car> carStorage;
    private ExecutorService workers;
    private Storage<Engine> engineStorage;
    private Storage<Body> bodyStorage;
    private Storage<Accessory> accessoryStorage;
    private static Logger LOGGER = Logger.getLogger(StorageController.class.getName());

    public StorageController(Storage<Car> carStorage, Storage<Engine> engineStorage, Storage<Body> bodyStorage, Storage<Accessory> accessoryStorage, ThreadPoolExecutor workers){
        this.carStorage = carStorage;
        this.engineStorage = engineStorage;
        this.bodyStorage = bodyStorage;
        this.accessoryStorage = accessoryStorage;
        this.workers = workers;
    }

    @Override
    public void update() {
        if (carStorage.getNumberOfDetails() == 0){
            LOGGER.log(Level.INFO, "Controller has sent request to workers");
            workers.execute(new Task());
        }
    }

    private class Task implements Runnable{
        @Override
        public void run() {
            try {
                Engine newEngine = engineStorage.getDetail();
                Body newBody = bodyStorage.getDetail();
                Accessory newAccessory = accessoryStorage.getDetail();
                Car newCar = new Car(newEngine, newBody, newAccessory);
                carStorage.addDetail(newCar);
            }
            catch (InterruptedException ex){
                return;
            }
            LOGGER.log(Level.INFO, "Worker has made a new car");
        }
    }
}
