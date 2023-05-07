package cpsc2150.listDec;

import java.util.List;
import java.util.Random;

public interface IShuffleList<T> extends List<T> {
    Random rand = new Random();

    /**
     * Randomly picks two positions in a list and swaps them
     * swaps number of times
     * @pre A list exists
     * @param swaps the number of times the list has its values swapped
     * @post The list has been shuffled with swaps number of swaps
     */
    default void shuffle(int swaps) {
        for (int i = 0; i < swaps; i++) {
            this.swap(this.rand.nextInt(this.size()), this.rand.nextInt(this.size()));
        }
    }

    /**
     * Swaps two values in a list
     * @pre [A list exists] AND 0 <= i < |self| AND 0 <= j < |self| AND 2 <= |self|
     * @param i The value at the position number in the list
     * @param j The value at the position number in the list
     * @post [Two values in the list have been swapped] AND i = #i AND j = #j
     */
    default void swap(int i, int j) {
        T temp;
        temp = this.get(i);
        this.set(i, this.get(j));
        this.set(j, temp);
    }
}
