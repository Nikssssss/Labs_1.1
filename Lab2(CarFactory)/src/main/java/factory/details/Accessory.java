package factory.details;

import java.util.UUID;

public class Accessory implements Detail{
    private String ID;

    public Accessory(){
        ID = UUID.randomUUID().toString();
    }

    @Override
    public String getID() {
        return ID;
    }
}
