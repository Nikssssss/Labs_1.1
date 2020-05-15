package factory.details;

import java.util.UUID;

public class Car implements Detail {
    private String ID;
    private Engine engine;
    private Body body;
    private Accessory accessory;

    public Car(Engine engine, Body body, Accessory accessory){
        ID = UUID.randomUUID().toString();
        this.engine = engine;
        this.body = body;
        this.accessory = accessory;
    }

    @Override
    public String getID() {
        return ID;
    }

    public Accessory getAccessory() {
        return accessory;
    }

    public Engine getEngine() {
        return engine;
    }

    public Body getBody() {
        return body;
    }
}
