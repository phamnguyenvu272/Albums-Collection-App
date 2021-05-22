package numberlist;

/**
 * This class extends Exception and is called when an index is out of bounds. 
 * It sends a message to the user with the index sent, and the bounds it must 
 * fall between.
 *
 * @author Cameron Ufland
 *
 * @version 1.0 05/27/2018
 */
public class InvalidIndexException extends Exception {
    private int minIndex;
    private int maxIndex;
    private int indexUsed;
    
     /**
     * constructor for the InvalidIndexException
     * 
     * @param minIndex the lower bound of the index range
     * @param maxIndex the upper bound of the index range
     * @param indexUsed the index that was sent 
     */
    public InvalidIndexException(int minIndex, int maxIndex, int indexUsed){
        super(indexUsed + " is out of bounds: [" + minIndex + ", " + maxIndex + "]");
        this.minIndex = minIndex;
        this.maxIndex = maxIndex;
        this.indexUsed = indexUsed;
    }
    /**
     * This method retrieves the value of the minIndex data field and returns it
     * to the user.
     *
     * @return minIndex the value of the lower bound of the index range.
     */
    public int getMinIndex(){
        return minIndex;
    }
    /**
     * This method retrieves the value of the maxIndex data field and returns it
     * to the user.
     *
     * @return maxIndex the value of the upper bound of the index range.
     */
    public int getMaxIndex(){
        return maxIndex;
    }
    /**
     * This method retrieves the value of the indexUsed data field and returns it
     * to the user.
     *
     * @return indexUsed the value sent by the user
     */
    public int getIndexUsed(){
        return indexUsed;
    }
}
