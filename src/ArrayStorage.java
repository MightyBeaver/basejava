import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int actualSize = 0;

    void clear() {
        Arrays.fill(storage,null);
        actualSize = 0;
    }

    void save(Resume r) {
            if(actualSize < storage.length && r!= null) {
                    storage[actualSize] = r;
                    actualSize++;
                }
    }

    Resume get(String uuid) {
        if (uuid != null) {
            for (int i = 0; i < actualSize; i++) {
                if (storage[i].toString().equals(uuid)) {
                    return storage[i];
                }
            }
        }
        return null;
    }

    void delete(String uuid) {
        if (uuid != null) {
            for (int i = 0; i < actualSize; i++) {
                if (storage[i].toString().equals(uuid)) {
                    System.arraycopy(storage, i + 1, storage, i, storage.length - i - 1);
                    storage[storage.length - 1] = null;
                    actualSize--;
                }
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        if (actualSize > 0) {
            Resume[] allElements = new Resume[actualSize];
            allElements = Arrays.copyOfRange(storage, 0, actualSize);
            return allElements;
        }
        return new Resume[0];
    }

    int size() {
        return actualSize;
    }
}
