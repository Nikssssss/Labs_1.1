package collaborators;

import factory.Storage;
import factory.details.Car;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Dealer extends Thread {
    private ArrayList<Car> garage = new ArrayList<>();
    private Storage<Car> carStorage;
    private Integer delay;
    private Integer numberInOrder;
    private static Logger LOGGER = Logger.getLogger(Dealer.class.getName());
    private static Calendar calendar = Calendar.getInstance();
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy 'at' HH:mm:ss z");

    public Dealer(Storage<Car> carStorage, Integer delay, Integer numberInOrder){
        this.carStorage = carStorage;
        this.delay = delay;
        this.numberInOrder = numberInOrder;
    }

    public void requestCar() throws InterruptedException {
        Car newCar = carStorage.getDetail();
        LOGGER.log(Level.INFO, "{0}: Dealer {1}: Auto <{2}> [Body: <{3}>, Engine: <{4}>, Accessory: <{5}>]", new Object[]{
                dateFormat.format(calendar.getTime()), numberInOrder, newCar.getID(), newCar.getBody().getID(), newCar.getEngine().getID(), newCar.getAccessory().getID()});
        garage.add(newCar);
    }

    public Integer getNumberOfCars(){
        return garage.size();
    }

    @Override
    public void run() {
        LOGGER.log(Level.INFO, "Thread {0} represented Dealer has begun to work", Thread.currentThread().getId());
        while (!this.isInterrupted()){
            try {
                requestCar();
            }
            catch (InterruptedException ex){
                LOGGER.log(Level.INFO, "Thread {0} represented Dealer has been interrupted while it was waiting", Thread.currentThread().getId());
                break;
            }
            try {
                sleep(delay);
            } catch (InterruptedException ex) {
                LOGGER.log(Level.FINE, "Thread {0} represented Dealer has been interrupted while it was sleeping", Thread.currentThread().getId());
                break;
            }
        }
        LOGGER.log(Level.INFO, "Thread {0} represented Dealer has ended to work", Thread.currentThread().getId());
    }
}
