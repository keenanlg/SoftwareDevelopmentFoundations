package cpsc2150.MyVector;

/**
 * <p>
 * An array implementation of {@link IVector}.
 * </p>
 *
 * @invariant 0 <= myVector.length <= MAX_LENGTH
 *
 * @correspondence self = [myVector represented as a sequence of values]
 */
public class ArrayVector<T> extends AbsVector<T> implements IVector<T> {

    // ===========================================================
    // Member Fields
    // ===========================================================

    /**
     * <p>
     * this time store the vector in an array. myVector[0] is the front of the vector
     * </p>
     */
    private final T[] myVector;
    private int arrayLength;

    // ===========================================================
    // Constructors
    // ===========================================================

    /**
     * <p>
     * This creates a new array-based vector that is initially empty.
     * </p>
     *
     * @pre None
     *
     * @post myVector = [a value array of MAX_LENGTH]
     */
    public ArrayVector() {
        myVector = (T[]) new Object[MAX_LENGTH];
        arrayLength = 0;
    }

    // ===========================================================
    // Public Methods
    // ===========================================================

    @Override
    public void addElement(T val) {
        myVector[arrayLength] = val;
        arrayLength++;
    }

    @Override
    public T removeElement() {
        T value = myVector[0];
        for (int i = 0; i < arrayLength - 1; i++) {
            myVector[i] = myVector[i + 1];
        }
        arrayLength--;
        return value;
    }

    @Override
    public boolean contains(T val) {
        for (int i = 0; i < arrayLength; i++) {
            if (myVector[i] == val) {
                return true;
            }
        }
        return false;
    }

    @Override
    public T get(int pos) {
        T value = null;
        for (int i = 0; i <= pos; i++) {
            value = myVector[i];
        }
        return value;
    }

    @Override
    public int length() {
        return arrayLength;
    }

    @Override
    public void clear() {
        arrayLength= 0;
    }
}
