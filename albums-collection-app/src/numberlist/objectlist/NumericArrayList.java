package numberlist.objectlist;

import numberlist.InvalidIndexException;

/**
 * This class creates an expandable list of objects using the array data
 * structure.
 *
 * @author Cameron Ufland
 *
 * @version 1.1 05/06/2018
 */
public class NumericArrayList extends NumericList implements Copiable {

    private Copiable[] list = new Copiable[10];//array to hold added values

    /**
     * This is the default constructor for the ArrayList
     */
    public NumericArrayList() {
    }

    /**
     * This method adds an object to the list at a specified location in that
     * list. If the object increases count to match the the length of the array
     * then it will create a new array twice the size and save the objects into
     * the new array. The array will move the elements up one to make room for
     * the new value.
     *
     * @param obj the object to be added at the specified location in the list
     * @param index the location where the value will be added
     * @throws numberlist.InvalidIndexException
     */
    @Override
    public void add(int index, Copiable obj) throws InvalidIndexException {

        if (index < 0 || index > count) {
            throw new InvalidIndexException(0, count, index);
        } else {
            count++;
            if (count == list.length) {
                Copiable[] temp = list;
                list = new Copiable[count * 2];
                for (int i = 0; i < temp.length; i++) {
                    list[i] = temp[i];
                }
            }
            if (index < count - 1) {
                for (int i = count - 1; i >= index; i--) {
                    list[i + 1] = list[i];
                }
            }

            list[index] = obj;
        }
    }

    /**
     * This method goes to the specified index of the list and sets the value of
     * the object contained therein.
     *
     * @param index the specific element who's value will be set
     * @param obj the value that the element will be changed to
     * @return the object that was replaced
     * @throws numberlist.InvalidIndexException
     */
    @Override
    public Copiable set(int index, Copiable obj) throws InvalidIndexException {
        if (index >= count || index < 0) {
            throw new InvalidIndexException(0, count - 1, index);

        } else {
            Copiable temp = list[index];
            list[index] = obj;
            return temp;
        }
    }

    /**
     * This method removes an object at a specified location in that list.The
     * array will move the elements down one to account for the removed object.
     * If the requested index is greater than count or less than zero it returns
     * null.
     *
     * @param index the specific element to be removed
     * @return either the object at the index requested or null.
     * @throws numberlist.InvalidIndexException
     */
    @Override
    public Copiable removeAt(int index) throws InvalidIndexException {

        if (index > count - 1 || index < 0) {
            throw new InvalidIndexException(0, count - 1, index);
        } else {
            Copiable temp = list[index];
            for (int i = index; i < count; i++) {
                list[i] = list[i + 1];
            }
            count--;
            return temp;
        }
    }

    /**
     * This method removes an object in the list by searching the array for that
     * object and calling the removeAt method at the first occurrence of that
     * object
     *
     * @param obj the value who's first occurrence will be removed
     */
    @Override
    public void remove(Copiable obj) {
        try {
            removeAt(indexOf(obj));
        } catch (InvalidIndexException iie) {
            //should not throw error
        }
    }

    /**
     * This method goes to the specified index of the array and retrieves the
     * object contained therein.
     *
     * @param index the specific element to be retrieved
     * @return the object at the requested location in the array
     * @throws numberlist.InvalidIndexException
     */
    @Override
    public Copiable get(int index) throws InvalidIndexException {
        if (index > count - 1 || index < 0) {
            throw new InvalidIndexException(0, count - 1, index);
        } else {
            return list[index];
        }
    }

    /**
     * This method goes to the first occurrence of the requested object and
     * returns the location in the array of where it was found.
     *
     * @param obj the object that is being searched for.
     * @return index returns the location of the first occurrence of that object
     */
    @Override
    public int indexOf(Copiable obj) {
        int index = -1;
        for (int i = 0; i < count; i++) {
            if (obj != null && list[i].toString().equals(obj.toString())) {
                index = i;
                break;
            } else {
                index = -1;
            }
        }
        return index;
    }

    /**
     * This method converts the array to a string representation to be displayed
     *
     * @return the contents of the array converted to a string displaying the
     * objects (calling their toString() method) of the elements of the array in
     * sequential order
     */
    @Override
    public String toString() {
        StringBuffer out = new StringBuffer("[ ");
        if (count > 0) {
            for (int i = 0; i < count - 1; i++) {
                out.append(list[i].toString()).append(", ");
            }
            out.append(list[count - 1].toString()).append(" ]");
        } else {
            out.append("]");
        }
        return out.toString();
    }

    /**
     * This method creates a deepCopy of the Array list. It creates a new
     * instance of the list and adds a copy of each element, by calling that
     * objects deep copy method.
     *
     * @return NumericArrayList returns the new copied list to the user.
     */
    @Override
    public NumericArrayList deepCopy() {
        NumericArrayList copyList = new NumericArrayList();
        try {
            for (int i = 0; i < count; i++) {

                copyList.add(i, list[i].deepCopy());
            }
        } catch (InvalidIndexException iie) {
            //should not throw exception
        }
        return copyList;
    }

}
