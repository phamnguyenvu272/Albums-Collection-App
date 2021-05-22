package numberlist.primitivelist;

import numberlist.InvalidIndexException;

/**
 * This class creates an expandable list of integer values, it inherits from the
 * LongArrayList, uses those methods for integer values instead, and adds
 * additional functionality.
 *
 * @author Cameron Ufland
 *
 * @version 1.0 04/15/2018
 */
public class IntegerArrayList extends LongArrayList {

    /**
     * This method adds an integer value to the end of the array list(not the
     * end of the array).
     *
     * @param value the value to be added to the end of the list
     * @return count returns the number of elements in the list
     */
    public int add(long value) {
        int count = getCount();
        try {
            add(count, value);
        } catch (InvalidIndexException iie) {
            //should not throw error
        }
        return count;
    }

    /**
     * This method removes each occurrence of a specified value throughout the
     * array
     *
     * @param value the value who's every occurrence will be removed from the
     * list
     */
    public void removeAll(long value) {
        int count = getCount();
        try {
            for (int i = count - 1; i > -1; i--) {
                if (get(i) == value) {
                    removeAt(i);
                }
            }
        } catch (InvalidIndexException iie) {
            //should not throw error;
        }

    }

    /**
     * This method searches starting from the back, for a specified value to
     * find its last occurrence.
     *
     * @param value the number that the user is trying to find the last
     * occurrence of
     * @return index returns the index of where the last occurrence is located
     */
    public int lastIndexOf(int value) {
        int index = -1;
        try {
            for (int i = getCount() - 1; i > 0; i--) {
                if (get(i) == value) {
                    index = i;
                    break;
                }
            }
        } catch (InvalidIndexException iie) {
            //should not throw error
        }
        return index;
    }
}
