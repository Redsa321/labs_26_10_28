package library;

import java.util.ArrayList;
import java.util.List;

// GRASP Pure Fabrication: handles storage outside the domain classes
// Keeps storage concerns separate from library operations
public class InMemoryReaderRepository implements ReaderRepository{

    private final List<Reader> readers = new ArrayList<>();

    @Override
    public List<Reader> getReaders() {
        return List.copyOf(readers);
    }

    @Override
    public void saveReader(Reader reader) {
        readers.add(reader);
    }
}
