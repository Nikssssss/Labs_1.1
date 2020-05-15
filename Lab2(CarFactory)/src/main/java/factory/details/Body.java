package factory.details;

import java.util.UUID;

public class Body implements Detail{
    private String ID;

    public Body(){
        ID = UUID.randomUUID().toString();
    }

    @Override
    public String getID() {
        return ID;
    }
}
