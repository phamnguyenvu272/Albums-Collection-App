package numberlist.objectlist;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * This class builds a complex number, and allows the manipulation of that
 * number. Implements Copiable.
 *
 * @author Cameron Ufland
 *
 * @version 1.1 05/06/2018
 */
public final class Complex implements Copiable {

    private double real = 0; //the real number component of the complex number
    private double imaginary = 0; //the imaginary component of the complex number

    public Complex() {

    }

    // Constructor that takes a value for the real component, and imaginary one
    public Complex(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;

    }

    /**
     * This method retrieves the value of the real data field and returns it to
     * the user.
     *
     * @return real the value of the real component of the complex number.
     */
    public double getReal() {
        return real;
    }

    /**
     * This method retrieves the value of the imaginary data field and returns
     * it to the user.
     *
     * @return imaginary the value of the imaginary component of the complex
     * number.
     */
    public double getImaginary() {
        return imaginary;

    }

    /**
     * This method allows the user to add complex numbers together. This method
     * adds the current components of this object to another object and creates
     * a new Complex object without changing the information in either of the
     * parent Complex objects..
     *
     * @param other the Complex object to be added to the Complex object calling
     * the add method.
     * @return a new Complex Object with the values of the two parent objects
     * added together.
     */
    public Complex add(Complex other) {
        double tempReal = real + other.getReal();// to hold the new real value
        double tempImaginary = imaginary + other.getImaginary();// to hold the new imaginary value
        return new Complex(tempReal, tempImaginary);
    }

    /**
     * This method allows the user to subtract complex numbers. This method
     * subtracts the current components of this object from another object and
     * creates a new Complex object without changing the information in either
     * of the parent Complex objects.
     *
     * @param other the Complex object to be subtracted from the Complex object
     * calling the subtract method.
     * @return a new Complex Object with the values of the two parent objects
     * subtracted from each other.
     */
    public Complex subtract(Complex other) {
        double tempReal = real - other.getReal(); // to hold the new real value
        double tempImaginary = imaginary - other.getImaginary();// to hold the new imaginary value
        return new Complex(tempReal, tempImaginary);
    }

    /**
     * This method converts the Complex object to a string representation to be
     * displayed
     *
     * @return the contents of the Complex object converted to a string
     * displaying the real number component added or subtracted from the
     * imaginary component of the complex number, rounded to four significant
     * figures.
     */
    @Override
    public String toString() {
        String output = "0.0";
        BigDecimal bd = new BigDecimal(real);
        bd = bd.round(new MathContext(4));
        real = bd.doubleValue();
        BigDecimal bd2 = new BigDecimal(imaginary);
        bd2 = bd2.round(new MathContext(4));
        imaginary = bd2.doubleValue();
        if (imaginary == 0 && real == 0) {
            return output;
        } else if (imaginary == 0) {
            output = String.valueOf(real);

        } else if (real == 0) {
            output = String.valueOf(imaginary) + 'i';
        } else {
            if (imaginary < 0) {
                output = String.valueOf(real) + " - " + String.valueOf(imaginary * -1) + 'i';
            } else {
                output = String.valueOf(real) + " + " + String.valueOf(imaginary) + 'i';
            }
        }
        return output;
    }

    /**
     * This method copies the data in the Complex object and returns a new
     * complex object with the same data field values.
     *
     * @return Complex a new complex object with the same data values as the
     * Complex object that was copied.
     */
    public Complex deepCopy() {
        return new Complex(real, imaginary);
    }

//    @Override
//    public int compareTo(Complex other){
//        if(real == other.real){
//            return (int) (imaginary - other.imaginary);
//        }
//        else{
//            return (int) (real - other.real);
//        }
//    }
}
