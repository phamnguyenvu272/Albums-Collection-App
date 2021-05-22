package numberlist.primitivelist;

import numberlist.InvalidIndexException;

/**
 * This class creates an expandable list of double values, it is composed of a
 * LongArrayList,it converts the double values to longs and vice versa and calls
 * the methods of the LongArrayList.
 *
 * @author Cameron Ufland
 *
 * @version 1.0 04/15/2018
 */
class DoubleArrayList {

    // array list to add double values 
    private LongArrayList list = new LongArrayList();

    /**
     * This method adds a double value to the list at a specified location in
     * that list by calling the LongArrayList.add() method and passing it a
     * boxed double value.
     *
     * @param value the value to be added at the specified location in the list
     * @param index the location where the value will be added
     * @throws numberlist.InvalidIndexException
     */
    public void add(int index, double value) throws InvalidIndexException {
        if (index < 0 || index > list.getCount()) {
            throw new InvalidIndexException(0, list.getCount(), index);
        } else {
            list.add(index, Double.doubleToRawLongBits(value));
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
    public double set(int index, double value) throws InvalidIndexException {
        if (index < 0 || index > list.getCount()) {
            throw new InvalidIndexException(0, list.getCount(), index);
        } else {
            double temp = Double.longBitsToDouble(list.get(index));
            list.set(index, Double.doubleToRawLongBits(value));
            return temp;
        }
    }

    /**
     * This method removes a double value at a specified location in that
     * list.The array will move the elements down one to account for the removed
     * value. If a value is greater than count or less than zero it returns an
     * indicator value. It calls the LongArrayList removeAt() method and
     * converts the returned value to a double.
     *
     * @param index the specific element to be removed
     * @return either the value at the index requested or an indicator value
     * indicating the selected index is out of range.
     * @throws numberlist.InvalidIndexException
     */
    public double removeAt(int index) throws InvalidIndexException {
        if (index > list.getCount() - 1 || index < 0) {
            throw new InvalidIndexException(0, list.getCount() - 1, index);
        } else {
            return Double.longBitsToDouble(list.removeAt(index));
        }
    }

    /**
     * This method removes a Double value in the list by searching the array for
     * that value and calling the removeAt method at the first occurrence of
     * that value by calling the LongArrayList remove() method
     *
     * @param value the value who's first occurrence will be removed
     */
    public void remove(double value) {
        list.remove(Double.doubleToRawLongBits(value));
    }

    /**
     * This method goes to the specified index of the array and retrieves the
     * value contained therein by calling the LongArrayList get() method, and
     * converting the returned long to a double.
     *
     * @param index the specific element to be retrieved
     * @return the value at the requested location in the array
     * @throws numberlist.InvalidIndexException
     */
    public double get(int index) throws InvalidIndexException {
        if (index > list.getCount() - 1 || index < 0) {
            throw new InvalidIndexException(0, list.getCount(), index);
        } else {
            double box = Double.longBitsToDouble(list.get(index));
            return Double.longBitsToDouble(list.get(index));
        }
    }

    /**
     * This method goes to the first occurrence of the requested value and
     * returns the location in the array of where it was found by calling the
     * LongArrayList indexOf() method, and converting the requested double to a
     * boxed long.
     *
     * @param value the value that is being searched for.
     * @return index returns the location of the first occurrence of that value
     */
    public int indexOf(double value) {
        long box = Double.doubleToRawLongBits(value);
        return list.indexOf(box);

    }

    /**
     * This method retrieves the value of the count data field by calling the
     * LongArrayList getCount() method
     *
     * @return count returns the number of elements that are filled in the array
     */
    public int getCount() {
        return list.getCount();
    }

    /**
     * This method converts the array to a string representation to be displayed
     * by calling the LongArrayList.toString() method
     *
     * @return the contents of the array converted to a string displaying the
     * values of the elements of the array in sequential order
     */
    public String toString() {
        StringBuffer out = new StringBuffer("[ ");
        try {
            if (list.getCount() > 0) {
                for (int i = 0; i < list.getCount() - 1; i++) {
                    double temp = Double.longBitsToDouble(list.get(i));
                    out.append(temp).append(", ");
                }
                out.append(list.getCount() - 1).append("]");
            } else {
                out.append("]");
            }
        } catch (InvalidIndexException iie) {
            //should not throw exception 
        }
        return out.toString();
    }
}
