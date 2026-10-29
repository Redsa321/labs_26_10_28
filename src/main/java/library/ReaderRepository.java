package library;

import java.util.List;

public interface ReaderRepository {
    List<Reader> getReaders();

    void saveReader(Reader reader);
}
