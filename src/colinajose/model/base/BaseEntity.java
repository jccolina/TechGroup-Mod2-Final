package colinajose.model.base;

public abstract class BaseEntity {
    private String id;

    public BaseEntity(){
        this.id = java.util.UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
