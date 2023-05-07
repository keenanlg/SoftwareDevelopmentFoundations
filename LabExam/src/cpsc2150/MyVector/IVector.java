package cpsc2150.MyVector;

/**
 * <p>
 * A vector is abstractly a string of values that is bounded by MAX_LENGTH.
 * </p>
 *
 * Constraints: 0 <= length of self, |self| <= MAX_LENGTH
 *
 * Initialization ensures: Vector contains nothing, i.e., an empty string
 */
public interface IVector<T> {

    /** Maximum size of {@link IVector} */
    public static final int MAX_LENGTH = 100;

    /**
     * <p>
     * Adds element to the end of the vector.
     * </p>
     *
     * @param val
     *            Element to be added.
     *
     * @pre length of self, |self| < MAX_LENGTH
     *
     * @post self = [ val added to the end of #self ]
     */
    public void addElement(T val);

    /**
     * <p>
     * Removes and returns the element at the front of the vector.
     * </p>
     *
     * @return Element at the front of the vector
     *
     * @pre length of self, |self| > 0
     *
     * @post self = [ removeElement() removed from the front of #self ]
     */
    public T removeElement();

    /**
     * <p>
     * Checks to see if a value exists in the vector.
     * </p>
     *
     * @param val
     *            a value to check
     * 
     * @return whether val is in the vector
     * 
     * @pre None
     * 
     * @post contains iff [val is in self] AND self = #self
     */
    public boolean contains(T val);

    /**
     * <p>
     * Returns the element at the specified position in the vector, but doesn't remove it.
     * </p>
     *
     * @param pos
     *            A valid position in our vector.
     *
     * @return Element at the specified position in the vector
     *
     * @pre length of self, |self| > 0 AND 1 <= pos <= length()
     *
     * @post get = [ element stored in the pos position in vector ] AND self = #self
     */
    public T get(int pos);

    /**
     * <p>
     * Returns the number of values in the vector.
     * </p>
     *
     * @return The length of the vector.
     *
     * @pre None
     *
     * @post length = length of self, |self| AND self = #self
     */
    public int length();

    /**
     * <p>
     * Clears the entire vector.
     * </p>
     *
     * @pre None
     *
     * @post self contains nothing, i.e., an empty string
     */
    public void clear();

    /**
     * <p>
     * Replaces the value at the specified position in the vector.
     * </p>
     *
     * @param val
     *            Element to be replaced.
     * @param pos
     *            A valid position in our vector.
     *
     * @pre length of self, |self| > 0 AND 1 <= pos <= length()
     *
     * @post self = [ #self except val has been replaced at pos ] AND val = #val AND pos = #pos
     */
    default public void replace(T val, int pos) {
        // complete this code
        int firstLength = length();
        T[] test = (T[]) new Object[firstLength];
        boolean replaced = false;

        //Replacing value by removing and editing
        for (int i = 0; i < firstLength; i++) {
            if (i == pos - 1) {
                test[i] = val;
                replaced = true;
            }
            else {
                if (replaced) {
                    removeElement();
                }
                test[i] = removeElement();
            }
        }

        //Adding values back into the vector
        for (int i = 0; i < firstLength; i++) {
            addElement(test[i]);
        }
    }
}