package factory;

import collaborators.Dealer;
import factory.details.Accessory;
import factory.details.Body;
import factory.details.Car;
import factory.details.Engine;
import observers.Observer;

import java.util.ArrayList;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StorageController implements Observer {
    private Storage<Car> carStorage;
    private ThreadPoolExecutor workers;
    private Storage<Engine> engineStorage;
    private Storage<Body> bodyStorage;
    private Storage<Accessory> accessoryStorage;
    private ArrayList<Dealer> dealers;
    private static Logger LOGGER = Logger.getLogger(StorageController.class.getName());

    public StorageController(Storage<Car> carStorage, Storage<Engine> engineStorage, Storage<Body> bodyStorage, Storage<Accessory> accessoryStorage, ThreadPoolExecutor workers, ArrayList<Dealer> dealers) {
        this.carStorage = carStorage;
        this.engineStorage = engineStorage;
        this.bodyStorage = bodyStorage;
        this.accessoryStorage = accessoryStorage;
        this.workers = workers;
        this.dealers = dealers;
    }

    @Override
    public void update() {
        long numberOfActiveTasks = (workers.getTaskCount() - workers.getCompletedTaskCount()) + workers.getQueue().size();
        if ((carStorage.getNumberOfDetails() + numberOfActiveTasks) < 0.1 * carStorage.getStorageSize()){
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

    public void init(){
        for (int i = 0; i < dealers.size(); i++) {
            LOGGER.log(Level.INFO, "Controller has sent request to workers (initialize)");
            workers.execute(new Task());
        }
    }
}
