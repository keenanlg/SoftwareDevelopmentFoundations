package cpsc2150.MyDeque;

public interface IDeque {
    /**
     * A deque containing integers.
     * A deque is a double-ended queue data structure that allows you
     * to insert and remove from both ends.
     * This deque is bounded by MAX_LENGTH
     */
    public interface IDeque {
        public static final int MAX_LENGTH = 100;

        // Adds x to the end of the deque
        public void enqueue(Integer x);

        //removes and returns the integer at the front of the deque
        public Integer dequeue();

        // Adds x to the front of the deque
        public void inject(Integer x);

        //removes and returns the integer at the end of the deque
        public Integer removeLast();

        //returns the number of integers in the deque
        public int length();

        //clears the entire deque
        public void clear();
    }
}
