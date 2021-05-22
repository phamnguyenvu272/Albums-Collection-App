/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package numberlist.primitivelist;

import java.util.logging.Level;
import java.util.logging.Logger;
import numberlist.InvalidIndexException;
import numberlist.objectlist.NumericArrayList;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Cameron
 */
public class RealArrayListTest {

    RealArrayList myList;

    public RealArrayListTest() {
        myList = new RealArrayList();
    }

    @Before
    public void setUp() {
    }

    /**
     * Test of add method, of class RealArrayList.
     */
    @Test
    public void testAdd1() {
        boolean caught = false;
        try {
            int i = 1;
            myList.add(0, 300.0);
            while (i <= 13) {
                
                myList.add(i, i * i * 1.0);
                i++;
            }
            
            myList.add(5, 20);
            assertEquals(300, myList.get(0), Math.pow(10, -5));
            
            assertEquals(121, myList.get(12), Math.pow(10, -5));
            assertEquals(20, myList.get(5), Math.pow(10, -5));
            assertEquals(25, myList.get(6), Math.pow(10, -5));
            
            myList.add(24, 5);
            int value = myList.getCount();
            assertEquals(value, 15);
        } catch (InvalidIndexException iie) {
            System.out.println("test add 1 " + iie.toString());
            caught = true;
            assertEquals(caught, true);
        }
    }
@Test
    public void testAdd2() {
        boolean caught = false;
        try{
        for (int i = 0; i <= 13; i++) {
            myList.add(i, i * i);
        }
        int index = myList.add(250);

        assertEquals(index, 14);
        }catch(InvalidIndexException iie) {
            System.out.println(iie.toString());
            caught = true;
            assertEquals(caught, true);
            
        }
    }
@Test
    public void testAdd3() {
        boolean caught = false;
        try {
            for (int i = 0; i <= 13; i++) {
                myList.add(i * i);
            }
            myList.add(-1, 200);
        } catch (InvalidIndexException iie) {
            System.out.println("add3"+iie.toString());
            caught = true;
            assertEquals(caught, true);
        }
    }

    @Test
    public void testAdd4() {
        boolean caught = false;
        try {
            for (int i = 0; i <= 13; i++) {
                myList.add(i * i);
            }
            myList.add(myList.getCount()+1, 200);
        } catch (InvalidIndexException iie) {
            System.out.println("add"+iie.toString());
            caught = true;
            assertEquals(caught, true);
        }
    }
    /**
     * Test of removeAll method, of class IntegerArrayList.
     */
    @Test
    public void testRemoveAll() {
        boolean caught = false;
        try {
            for (int i = 0; i <= 13; i++) {
                if (i % 2 == 0) {
                    myList.add(5.0);
                } else {
                    myList.add(i * 3.0);
                }
            }
            myList.removeAll(5.0);
            //make sure it moves everything back one
            assertEquals(myList.get(2), 15, Math.pow(10, -5));
            //if greater than count returns min value
            assertEquals(myList.get(10), Long.MIN_VALUE, Math.pow(10, -5));
            //ensure no 5s exist in the list
            assertEquals(myList.indexOf(5), -1);
            assertEquals(myList.getCount(), 7);
        } catch (InvalidIndexException iie) {
            System.out.println(iie.toString());
            caught = true;
            assertEquals(caught, true);
        }
    }

    /**
     * Test of lastIndexOf method, of class IntegerArrayList.
     */
    @Test
    public void testLastIndexOf() {
        for (int i = 0; i <= 13; i++) {
            if (i % 2 == 0) {
                myList.add(5);
            } else {
                myList.add(i * 3);
            }
        }

        assertEquals(myList.lastIndexOf(5), 12, 0.00001);
    }
}
