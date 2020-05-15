package collaborators;

import factory.Storage;
import factory.details.Detail;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Supplier<T extends Detail> extends Thread{
    private Storage<T> storage;
    private Class<T> detailType;
    private Integer delay;
    private static Logger LOGGER = Logger.getLogger(Supplier.class.getName());

    public Supplier(Storage<T> storage, Class<T> detailType, Integer delay){
        this.storage = storage;
        this.detailType = detailType;
        this.delay = delay;
    }

    public void supplyDetail() throws InterruptedException {
        T newDetail;
        try {
            newDetail = detailType.getDeclaredConstructor().newInstance();
        }
        catch (Exception ex){
            LOGGER.log(Level.WARNING, "Supplier {0} couldn't make a new {1}", new Object[]{this.getClass().getName(), detailType.getName()});
            return;
        }
        storage.addDetail(newDetail);
    }

    @Override
    public void run() {
        LOGGER.log(Level.INFO, "Thread {0} represented Supplier has begun to work", Thread.currentThread().getId());
        while (!this.isInterrupted()){
            try {
                supplyDetail();
            }
            catch (InterruptedException ex){
                LOGGER.log(Level.INFO, "Thread {0} represented Dealer has been interrupted while it was waiting", Thread.currentThread().getId());
                break;
            }
            try {
                sleep(delay);
            }
            catch (InterruptedException ex){
                LOGGER.log(Level.FINE, "Thread {0} represented Supplier has been interrupted while it was sleeping", Thread.currentThread().getId());
                break;
            }
        }
        LOGGER.log(Level.INFO, "Thread {0} represented Supplier has ended the work", Thread.currentThread().getId());
    }
}
