package pl.djLab.csv2xml;

public class ObjEntities {
    private String id;
    private String name;

    public ObjEntities(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
