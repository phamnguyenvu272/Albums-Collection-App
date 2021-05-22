/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package numberlist.objectlist;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author 955278275
 */
public class ComplexTest {
    
    Complex myComplex ;
    Complex complex2;
    
    public ComplexTest() {
    }
    
    @Before
    public void setUp() {
    }

    /**
     * Test of getReal method, of class Complex.
     */
    @Test
    public void testGetReal() {
    }

    /**
     * Test of getImaginary method, of class Complex.
     */
    @Test
    public void testGetImaginary() {
    }

    /**
     * Test of add method, of class Complex.
     */
    @Test
    public void testAdd() {
       myComplex = new Complex(0, -12); 
       complex2 = new Complex(12, 5);
       myComplex = myComplex.add(complex2);
       assertEquals(myComplex.toString(),"12.0 - 7.0i" );
       
    }

    /**
     * Test of subtract method, of class Complex.
     */
    @Test
    public void testSubtract() {
        myComplex = new Complex(0, -12); 
       complex2 = new Complex(12, 5);
       myComplex = myComplex.subtract(complex2);
       assertEquals(myComplex.toString(),"-12.0 - 17.0i" );
    }

    /**
     * Test of toString method, of class Complex.
     */
    @Test
    public void testToString() {
        String complexString = "";
        myComplex = new Complex(1.2, 2.3);
        char[] arr = myComplex.toString().toCharArray();
        for(int i = 0; i < arr.length; i++){
             complexString += arr[i]; 
        }
        System.out.println(complexString);
        assertEquals(complexString, "1.2 + 2.3i");
        
    }
    @Test
    public void testDeepCopy(){
        Complex myComplex = new Complex(-10,  10);
        Complex newComplex = myComplex.deepCopy();
        
        assertEquals(myComplex.getReal(), newComplex.getReal(),0.00000001);
        assertNotEquals(myComplex,newComplex);
        System.out.println("deep copy test " + myComplex.equals(newComplex));
             
    }
}
