package numberlist.objectlist;

import numberlist.InvalidIndexException;

/**
 * This class represents the list ADT with all its basic methods.
 *
 * @author Cameron Ufland
 * @version 1.0 05/06/2018
 */
abstract class NumericList {

    int count;

    /**
     * This method adds an object to the list at a specified location in that
     * list. The items at and after the specified location will be moved up to
     * make room for the new object. This is an abstract method is must be
     * implemented by its subclasses.
     *
     * @param obj the object to be added at the specified location in the list
     * @param index the location where the value will be added
     */
    abstract void add(int index, Copiable obj) throws InvalidIndexException;

    ;

    /**
     * This method adds an object to the end of the list by calling the specific
     * subclasses add method and using count as the index. It then returns the
     * total number of elements in the list.
     *
     * @param obj the object to be added to the list
     * @return count the location where the value was added.
     */
    public int add(Copiable obj) {
        int location = -1;
        try {
            location = this.count;
            add(this.count, obj);
            return location;
        } catch (InvalidIndexException iie) {
            return location;
        }
    }

    /**
     * This method sets of a specified specified location in the list, returning
     * the value that was replaced. This is an abstract method, it must be
     * implemented by its subclasses.
     *
     * @param obj the new object for the specified location in the list
     * @param index the location where the value will be set
     */
    abstract Copiable set(int index, Copiable obj) throws InvalidIndexException;

    /**
     * This method will remove an object at a specific index, and return the
     * removed object.This is an abstract method is must be implemented by its
     * subclasses.
     *
     * @param index the specific element to be removed
     * @return either the Copiable object at the index requested or null.
     */
    abstract Copiable removeAt(int index) throws InvalidIndexException;

    /**
     * This method will remove an object by searching for the object in the list
     * and taking it off the list. This is an abstract method is must be
     * implemented by its subclasses.
     *
     * @param obj the specific object to be removed
     */
    abstract void remove(Copiable obj);

    /**
     * This method will get an object from the list by going to the specified
     * index, and returning the object associated with it.This is an abstract
     * method is must be implemented by its subclasses.
     *
     * @param index the specific element in the list to be retrieved.
     * @return Copiable returns the object at the requested index.
     */
    abstract Copiable get(int index) throws InvalidIndexException;

    ;

    /**
     * This method will get the index of an object and return it to the user.
     * This is an abstract method is must be implemented by its subclasses.
     *
     * @param obj the object with the position on the list in question.
     * @return int returns the index associated with the parameter object.
     */
    abstract int indexOf(Copiable obj);

    /**
     * This method returns the value of the count data field.
     *
     * @return int returns the value of the count data field.
     */
    public int getCount() {
        return count;
    }

}
