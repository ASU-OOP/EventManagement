package Models.MiscObjects;

public class Category {
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category(String name) {
        setName(name);
    }
}
