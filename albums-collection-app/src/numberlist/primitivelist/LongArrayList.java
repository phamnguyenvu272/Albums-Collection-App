package numberlist.primitivelist;

import numberlist.InvalidIndexException;

/**
 * This class creates an expandable list of integer values.
 *
 * @author Cameron Ufland
 *
 * @version 1.0 04/15/2018
 */
class LongArrayList {

    private long[] list = new long[10]; //array to hold added values
    private int count = 0; //number of filled elements

    /**
     * This is the default constructor for the LongArrayList
     */
    public LongArrayList() {
    }

    /**
     * This method adds a long value to the list at a specified location in that
     * list. If the added value increases count to match the the length of the
     * array then it will create a new array twice the size and save the values
     * into that. The array will move the elements up one to make room for the
     * new value.
     *
     * @param value the value to be added at the specified location in the list
     * @param index the location where the value will be added
     * @throws numberlist.InvalidIndexException
     */
    public void add(int index, long value) throws InvalidIndexException {

        if (index < 0 || index > count) {
            throw new InvalidIndexException(0, count, index);
        } else {

            count++;
            if (count == list.length) {
                long[] temp = list;
                list = new long[count * 2];
                for (int i = 0; i < temp.length; i++) {
                    list[i] = temp[i];
                }
            }
            if (index < count - 1) {
                for (int i = count - 1; i >= index; i--) {
                    list[i + 1] = list[i];
                }
            }
            list[index] = value;
        }

    }

    /**
     * This method goes to the specified index of the list and sets the value
     * contained therein.
     *
     * @param index the specific element who's value will be set
     * @param value the value that the element will be changed to
     * @return the value that was replaced
     * @throws numberlist.InvalidIndexException
     */
    public long set(int index, long value) throws InvalidIndexException {

        if (index < 0 || index > count-1) {
            throw new InvalidIndexException(0, count-1, index);
        } else {
            long temp = list[index];
            list[index] = value;
            return temp;
        }

    }

    /**
     * This method removes a long value at a specified location in that list.The
     * array will move the elements down one to account for the removed value.
     * If a value is greater than count or less than zero it returns an
     * indicator value.
     *
     * @param index the specific element to be removed
     * @return either the value at the index requested or an indicator value
     * indicating the selected index is out of range.
     * @throws numberlist.InvalidIndexException
     */
    public long removeAt(int index) throws InvalidIndexException {
        if (index > count - 1 || index < 0) {
            throw new InvalidIndexException(0, count - 1, index);
        } else {
            long temp = list[index];
            for (int i = index; i < count; i++) {
                list[i] = list[i + 1];
            }
            count--;
            return temp;
        }
    }

    /**
     * This method removes a long value in the list by searching the array for
     * that value and calling the removeAt method at the first occurrence of
     * that value
     *
     * @param value the value who's first occurrence will be removed
     */
    public void remove(long value) {
        long temp = -1;
        int i = 0;
        try {
            while (temp == -1 && i < count) {
                if (list[i] == value) {
                    temp = i;
                    removeAt(i);
                }
                i++;
            }
        } catch (InvalidIndexException iie) {
            // No errors should happen
        }
    }

    /**
     * This method goes to the specified index of the array and retrieves the
     * value contained therein.
     *
     * @param index the specific element to be retrieved
     * @return the value at the requested location in the array
     * @throws numberlist.InvalidIndexException
     */
    public long get(int index) throws InvalidIndexException {
        if (index > count - 1 || index < 0) {
            throw new InvalidIndexException(0, count - 1, index);
        } else {
            return list[index];
        }
    }

    /**
     * This method goes to the first occurrence of the requested value and
     * returns the location in the array of where it was found.
     *
     * @param value the value that is being searched for.
     * @return index returns the location of the first occurrence of that value
     */
    public int indexOf(long value) {
        int index = -1;
        for (int i = 0; i < count; i++) {
            if (list[i] == value) {
                index = i;
                break;
            } else {
                index = -1;
            }
        }
        return index;
    }

    /**
     * This method retrieves the value of the count data field.
     *
     * @return count returns the number of elements that are filled in the array
     */
    public int getCount() {
        return count;
    }

    /**
     * This method converts the array to a string representation to be displayed
     *
     * @return the contents of the array converted to a string displaying the
     * values of the elements of the array in sequential order
     */
    @Override
    public String toString() {
        StringBuffer out = new StringBuffer("[ ");
        if (count > 0) {
            for (int i = 0; i < count - 1; i++) {
                out.append(list[i]).append(", ");
            }
            out.append(list[count - 1]).append(" ]");
        } else {
            out.append("]");
        }
        return out.toString();
    }

}
