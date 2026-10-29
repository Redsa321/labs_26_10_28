package library;

public class Reader {

    private final int id;
    private final String name;

    public Reader(int id, String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Reader name must not be blank");
        }
        this.id = id;
        this.name = name.strip() ;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
