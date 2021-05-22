package numberlist.objectlist;

/**
 * This class creates a node that is attached to a value to link similar values
 * together, without using an array.
 *
 * @author Cameron
 *
 * @version 1.0 04/24/2018
 */
class Node {

    private Node nextNode; //holds the nextNode in the link chain

    private Copiable obj;//the object that is associated with this link in the chain

    //constructor
    public Node(Copiable obj) {
        this.obj = obj;
    }

    /**
     * This method returns the object associated with the node that called the
     * method.
     *
     * @return Copiable object the value associated with the specific node.
     */
    public Copiable getValue() {
        return obj;
    }

    /**
     * This method sets the value of the node.
     *
     * @param Copiable object the value that will be associated with the node
     * that called the method.
     */
    public void setValue(Copiable obj) {
        this.obj = obj;
    }

    /**
     * This method returns the node that comes after the node that called it in
     * sequential order.
     *
     * @return Node the node that follows the caller node in the LinkList
     */
    public Node getNext() {
        return nextNode;
    }

    /**
     * This method sets the node after the caller node in the list.
     *
     * @param Node nextNode the node that will become the following node after
     * the caller node.
     */
    public void setNext(Node nextNode) {
        this.nextNode = nextNode;
    }

}
