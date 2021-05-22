package numberlist.objectlist;

import numberlist.InvalidIndexException;

/**
 * This class creates an expandable list of objects using nodes and the Link
 * list Data structure to create an array like list.
 *
 * @author Cameron Ufland
 *
 * @version 1.0 05/06/2018
 *
 */
public class NumericLinkedList extends NumericList implements Copiable {

    private Node firstNode;

    /**
     * This is the default constructor for the LinkedList
     */
    public NumericLinkedList() {

    }

    /**
     * This method adds an object to the list at a specified location in that
     * list. The items at the location specified will be moved out of the way to
     * make room for the new node. It then rewires the nodes to put the newest
     * node in the right sequence.
     *
     * @param obj the object to be added at the specified location in the list
     * @param index the location where the value will be added
     * @throws numberlist.InvalidIndexException
     */
    @Override
    public void add(int index, Copiable obj) throws InvalidIndexException {
        if (index > count || index < 0) {
            throw new InvalidIndexException(0, count, index);
        }
        Node newNode = new Node(obj);

        if (index == 0) {
            newNode.setNext(firstNode);
            firstNode = newNode;
        } else {
            Node currentNode = firstNode;
            for (int i = 0; i < index - 1; i++) {
                currentNode = currentNode.getNext();
            }
            newNode.setNext(currentNode.getNext());
            currentNode.setNext(newNode);
        }
        count++;
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
        Node currentNode = firstNode;
        Copiable temp;
        if (index < 0 || index >= count) {
            throw new InvalidIndexException(0, count - 1, index);
        } else {
            for (int i = 0; i < index; i++) {
                currentNode = currentNode.getNext();
            }
            temp = currentNode.getValue();
            currentNode.setValue(obj);
            return temp;
        }
    }

    /**
     * This method removes an object at a specified location in the list.The
     * nodes will be rewired to skip the undesired item, and it will be garbage
     * collected. If the requested index is greater than count or less than zero
     * it returns null.
     *
     * @param index the specific element to be removed
     * @return either the Copiable object at the index requested or null.
     * @throws numberlist.InvalidIndexException
     */
    @Override
    public Copiable removeAt(int index) throws InvalidIndexException {
        Copiable temp;
        if (index >= count || index < 0) {
            throw new InvalidIndexException(0, count - 1, index);
        }
        Node currentNode = firstNode;
        if (index == 0) {
            temp = firstNode.getValue();
            firstNode = firstNode.getNext();
            return temp;
        } else {
            for (int i = 0; i < index - 1; i++) {
                currentNode = currentNode.getNext();
            }
            temp = currentNode.getNext().getValue();
            currentNode.setNext(currentNode.getNext().getNext());
            count--;
            return temp;
        }

    }

    /**
     * This method removes an object in the list by searching the nodes for that
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
            //should not throw an exception
        }
    }

    /**
     * This method goes to the specified node via a for loop and retrieves the
     * object contained therein.
     *
     * @param index the specific element to be retrieved
     * @return the Copiable object at the requested location in the array
     * @throws numberlist.InvalidIndexException
     */
    @Override
    public Copiable get(int index) throws InvalidIndexException {
        if (index >= count || index < 0) {
            throw new InvalidIndexException(0, count - 1, index);
        }
        Node currentNode = firstNode;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.getNext();
        }
        return currentNode.getValue();
    }

    /**
     * This method goes to the first occurrence of the requested object and
     * returns the location in the array of where it was found.
     *
     * @param obj the Copiable object that is being requested.
     * @return index returns the location of the first occurrence of that object
     */
    @Override
    public int indexOf(Copiable obj) {
        Node currentNode = firstNode;
        int index = -1;

        for (int i = 0; i < count; i++) {
            if (obj != null && obj.toString().equals(currentNode.getValue().toString())) {
                return i;
            } else {
                currentNode = currentNode.getNext();
            }

        }
        return index;
    }

    /**
     * This method converts the values in each node to strings and displays them
     * in sequential order, much like how an array would be displayed.
     *
     *
     * @return the contents of the list converted to a string displaying the
     * objects (calling their toString() method) of the elements of the array in
     * sequential order
     */
    @Override
    public String toString() {
        StringBuffer output = new StringBuffer("[ ");
        try {
            if (count > 0) {
                for (int i = 0; i < count; i++) {
                    if (i == 0) {
                        output.append(get(i));
                    } else {
                        output.append(", ");
                        output.append(get(i));
                    }
                }
            }
        } catch (InvalidIndexException iie) {
            //should not throw exception
        }
        output.append("]");
        return output.toString();
    }

    /**
     * This method creates a deepCopy of the list. It creates a new instance of
     * the list and adds a copy of each element by calling that objects deep
     * copy method.
     *
     * @return NumericLinkedList returns the new copied list to the user.
     */
    @Override
    public NumericLinkedList deepCopy() {
        NumericLinkedList copyList = new NumericLinkedList();
        Node currentNode = firstNode;

        for (int i = 0; i < count; i++) {
            try {
                copyList.add(i, currentNode.getValue().deepCopy());
                currentNode = currentNode.getNext();
            } catch (InvalidIndexException iie) {
                //should not throw error
            }
        }
        return copyList;
    }

}
