package cpsc2150.MyDeque;

/**
 * @invariant deque <= 100
 */
public class ArrayDeque implements IDeque {

    // where the data is stored. myQ[0] is the front of the deque
    private Integer[] myQ;

    // tracks how many items are in the deque
    // also used to find the end of the deque
    private int myLength;

    // complete the class

    //Constructor
    /**
     * @post Created a deque of 100 elements in size
     */
    public ArrayDeque() {
        myQ = new Integer[100];
        myLength = 0;
    }

    public void enqueue(Integer x) {
        for (int i = myLength - 1; i >= 0; i--) {
            myQ[i - 1] = myQ[i];
        }
        myQ[myLength - 1] = x;
        myLength++;
    }

    public Integer dequeue() {
        Integer temp = myQ[0];
        myQ[0] = null;
        for (int i = 0; i < myLength; i++) {
            myQ[i] = myQ[i + 1];
        }
        myLength--;
        return temp;
    }

    public void inject(Integer x) {
        for (int i = 0; i < myLength; i++) {
            myQ[i + 1] = myQ[i];
        }
        myQ[0] = x;
        myLength++;
    }

    public Integer removeLast() {
        Integer temp = myQ[myLength - 1];
        myQ[myLength - 1] = null;
        for (int i = myLength - 1; i >= 0; i--) {
            myQ[i] = myQ[i - 1];
        }
        myLength--;
        return temp;
    }

    public int length() {
        return myLength;
    }

    public void clear() {
        for (int i = 0; i < myLength; i++) {
            myQ[i] = null;
        }
    }
}
