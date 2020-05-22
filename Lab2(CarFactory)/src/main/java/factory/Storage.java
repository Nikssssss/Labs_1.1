package factory;

import factory.details.Detail;
import observers.Observable;

import java.util.Stack;
import java.util.logging.Logger;

public class Storage<T extends Detail> extends Observable {
    private Integer storageSize;
    private Integer numberOfDetails = 0;
    private Stack<T> storage = new Stack<>();
    private static Logger LOGGER = Logger.getLogger(Storage.class.getName());

    public Storage(Integer storageSize){
        this.storageSize = storageSize;
    }

    public synchronized void addDetail(T detail) throws InterruptedException {
        while (numberOfDetails > storageSize) {
            wait();
        }
        storage.push(detail);
        numberOfDetails++;
        notify();
    }

    public synchronized T getDetail() throws InterruptedException {
        while (numberOfDetails == 0){
            wait();
        }
        numberOfDetails--;
        notify();
        T returnedDetail = storage.pop();
        notifyObservers();
        return returnedDetail;
    }

    public synchronized Integer getNumberOfDetails() {
        return numberOfDetails;
    }

    public synchronized Integer getStorageSize() {
        return storageSize;
    }
}
