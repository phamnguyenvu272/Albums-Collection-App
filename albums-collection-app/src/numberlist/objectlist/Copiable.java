package numberlist.objectlist;

/**
 * This interface lets the implementing class do a deep copy of the contents of
 * that object.
 *
 * @author Cameron Ufland
 * @version 1.0 05/06/2018
 */
public interface Copiable {

    /**
     * This method creates a deep copy of the object that calls it. It copies
     * the contents and creates a new object.
     *
     * @return Copiable returns a copied version of the object that called the
     * method.
     */
    public Copiable deepCopy();
}
