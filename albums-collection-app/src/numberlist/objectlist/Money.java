package numberlist.objectlist;

/**
 * This class builds a money value by taking in cents and dollars, and allows
 * the manipulation of that value.
 *
 * @author Cameron Ufland
 *
 * @version 1.0 04/15/2018
 */
public final class Money implements Copiable, Comparable <Money> {

    private long dollars = 0;
    private byte cents = 0;

    public Money() {

    }

    // Constructor that takes a value for the dollars, and the cents
    public Money(long dollars, byte cents) {
        long total = dollars * 100 + cents;
        this.dollars = total / 100;
        this.cents = (byte) (total % 100);
    }

    /**
     * This method retrieves the value of the dollars data field and returns it
     * to the user.
     *
     * @return dollars the value of dollars of the money.
     */
    public long getDollars() {

        return dollars;
    }

    /**
     * This method retrieves the value of the cents data field and returns it to
     * the user.
     *
     * @return cents the value of cents of the money.
     */
    public byte getCents() {
        return cents;

    }

    /**
     * This method allows the user to add Money Objects together. This method
     * adds the current components of this object to another object and creates
     * a new Money object without changing the information in either of the
     * parent Money objects.
     *
     * @param other the Money object to be added to the Money object calling the
     * add method.
     * @return a new Money Object with the values of the two parent objects
     * added together.
     */
    public Money add(Money other) {
        long tempTotal = (dollars + other.getDollars()) * 100 + (cents + other.getCents());
        return new Money(tempTotal / 100, (byte) (tempTotal % 100));
    }

    /**
     * This method allows the user to subtract Money Objects. This method
     * subtracts the current components of this object from another object and
     * creates a new Money object without changing the information in either of
     * the parent Money objects.
     *
     * @param other the Money object to be added to the Money object calling the
     * add method.
     * @return a new Money Object with the values of the two parent objects
     * subtracted from each other.
     */
    public Money subtract(Money other) {

        long tempTotal = (dollars - other.getDollars()) * 100 + (cents - other.getCents());
        return new Money(tempTotal / 100, (byte) (tempTotal % 100));
    }

    /**
     * This method converts the Money object to a string representation to be
     * displayed. It formats the string to two decimal places
     *
     * @return the contents of the Money object converted to a string displaying
     * the object as a money value to two decimal places.
     */
    @Override
    public String toString() {
        String output = "$0.00";
        double total = (dollars * 100 + cents) / 100.0;
        if (total == 0) {
            return output;
        } else if (total < 0) {
            output = "-$" + String.format("%.2f", total * -1);;

        } else {
            output = "$" + String.format("%.2f", total);
        }
        return output;
    }

    /**
     * This method copies the data in the Money object and returns a new Money
     * object with the same data field values.
     *
     * @return Money a new Money object with the same data values as the Money
     * object that called the method.
     */
    @Override
    public Money deepCopy() {
        return new Money(dollars, cents);
    }

    @Override
    public int compareTo(Money other){
        if (dollars == other.dollars){
            return cents-other.cents;
        }
        else{
            return (int)dollars - (int)other.dollars;
        }
    }
}
