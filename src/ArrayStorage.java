import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        Arrays.fill(storage,null);
    }

    void save(Resume r) {
        if (r != null) {
            for (int i = 0; i < storage.length; i++) {
                if (storage[i] == null) {
                    storage[i] = r;
                    break;
                }
            }
        }
    }

    Resume get(String uuid) {
        if (uuid != null) {
            for (int i = 0; i < storage.length; i++) {
                if (storage[i] != null && storage[i].toString().equals(uuid)) {
                    return storage[i];
                }
            }
        }
            return null;
    }

    void delete(String uuid) {
        if (uuid != null) {
            for (int i = 0; i < storage.length; i++) {
                if (storage[i] != null && storage[i].toString().equals(uuid)) {
                    System.arraycopy(storage, i + 1, storage, i, storage.length - i - 1);
                    storage[storage.length - 1] = null;
                }
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        int s = size();
        if (s > 0) {
            Resume[] allElements = new Resume[s];
            allElements = Arrays.copyOfRange(storage, 0, s);
            return allElements;
        }
        return new Resume[0];
    }

    int size() {
        for (int i = 0; i < storage.length; i++) {
           if (storage[i] == null) {
               return i;
           }
        }
        return storage.length;
    }
}
