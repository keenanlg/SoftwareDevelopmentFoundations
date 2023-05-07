package cpsc2150.MyVector;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * A list implementation of {@link IVector}.
 * </p>
 * 
 * @invariant 0 <= myVector.size() <= MAX_LENGTH
 * 
 * @correspondence self = [myVector represented as a sequence of values]
 */
public class ListVector<T> extends AbsVector<T> implements IVector<T> {

    // ===========================================================
    // Member Fields
    // ===========================================================

    /**
     * <p>
     * this time store the vector in a list. myVector.get(0) is the front of the vector
     * </p>
     */
    private final List<T> myVector;

    // ===========================================================
    // Constructors
    // ===========================================================

    /**
     * <p>
     * This creates a new list-based vector that is initially empty.
     * </p>
     *
     * @pre None
     *
     * @post myVector = [a value list of MAX_LENGTH]
     */
    public ListVector() {
        myVector = new ArrayList<>();
    }

    // ===========================================================
    // Public Methods
    // ===========================================================

    @Override
    public void addElement(T val) {
        myVector.add(val);
    }

    @Override
    public T removeElement() {
        return myVector.remove(0);
    }

    @Override
    public boolean contains(T val) {
        return myVector.contains(val);
    }

    @Override
    public T get(int pos) {
        return myVector.get(pos - 1);
    }

    @Override
    public int length() {
        return myVector.size();
    }

    @Override
    public void clear() {
        myVector.clear();
    }

}