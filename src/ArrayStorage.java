import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int actualSize = 0;

    public void clear() {
        Arrays.fill(storage,null);
        actualSize = 0;
    }

    private int getMatchIndex(String uuid) {
        for (int i = 0; i < actualSize; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    public void save(Resume r) {
        int matchIndex = getMatchIndex(r.getUuid());
        if (matchIndex != -1) {
            System.out.println("This resume is already in storage");
            return;
        }
        if (actualSize == storage.length) {
            System.out.println("Storage at full capacity");
            return;
        }
        storage[actualSize] = r;
        actualSize++;
    }

    public void update(Resume r) {
        int matchIndex = getMatchIndex(r.getUuid());
        if(matchIndex == -1) {
            System.out.println("No such resume in storage");
            return;
        }
        storage[matchIndex] = r;
    }

    public Resume get(String uuid) {
        int matchIndex = getMatchIndex(uuid);
        if (matchIndex == -1) {
            return null;
        }
        return storage[matchIndex];
    }

    public void delete(String uuid) {
        int matchIndex = getMatchIndex(uuid);
        if (matchIndex == -1) {
            System.out.println("No such resume in storage");
            return;
        }
        storage[matchIndex] = storage[actualSize -1];
        storage[actualSize - 1] = null;
        actualSize--;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        if (actualSize > 0) {
            Resume[] allElements = new Resume[actualSize];
            allElements = Arrays.copyOfRange(storage, 0, actualSize);
            return allElements;
        }
        return new Resume[0];
    }

    public int size() {
        return actualSize;
    }
}
