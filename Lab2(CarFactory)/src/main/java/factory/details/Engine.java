package factory.details;

import java.util.UUID;

public class Engine implements Detail{
    private String ID;

    public Engine(){
        ID = UUID.randomUUID().toString();
    }

    @Override
    public String getID() {
        return ID;
    }
}
