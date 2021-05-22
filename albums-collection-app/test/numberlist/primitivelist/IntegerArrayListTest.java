/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package numberlist.primitivelist;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import numberlist.InvalidIndexException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Cameron
 */
public class IntegerArrayListTest {

    IntegerArrayList L1;

    public IntegerArrayListTest() {
        L1 = new IntegerArrayList();
    }

    @Before
    public void setUp() {
    }

    /**
     * Test of add method, of class IntegerArrayList.
     */
    @Test
    public void testAdd1() {
        boolean caught = false;
        try {
            int i = 1;
            L1.add(0, 300);
            while (i <= 13) {

                    L1.add(i, i * i);
                    i++;
            }

            L1.add(5, 20);
            assertEquals(300, L1.get(0));

            assertEquals(121, L1.get(12));
            assertEquals(20, L1.get(5));
            assertEquals(25, L1.get(6));
        } catch (InvalidIndexException iie) {
            System.out.println(iie.toString());
            caught = true;
            assertEquals(caught, true);
        }
    }

    @Test
    public void testAdd2() {
        boolean caught = false;
        try {
            for (int i = 0; i <= 13; i++) {
                L1.add(i, i * i);
            }
            int index = L1.add(250);

            assertEquals(index, 14);
        } catch (InvalidIndexException iie) {
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
                L1.add(i * i);
            }
            int index = L1.add(250);
            assertEquals(L1.get(5), 25);
            assertEquals(index, 14);
        } catch (InvalidIndexException iie) {
            System.out.println(iie.toString());
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
                    L1.add(5);
                } else {
                    L1.add(i * 3);
                }
            }
            L1.removeAll(5);
            //make sure it moves everything back one
            assertEquals(L1.get(2), 15);
            //ensure no 5s exist in the list
            
        } catch (InvalidIndexException iie) {
            assertEquals(L1.getCount(), 7);
            System.out.println(iie.toString());
            caught = true;
            assertEquals(caught, true);
        } finally{
            
           assertEquals(L1.indexOf(5), -1);
        }
    }

    /**
     * Test of lastIndexOf method, of class IntegerArrayList.
     */
    @Test
    public void testLastIndexOf() {
        for (int i = 0; i <= 13; i++) {
            if (i % 2 == 0) {
                L1.add(5);
            } else {
                L1.add(i * 3);
            }
        }

        assertEquals(L1.lastIndexOf(5), 12);
    }

    @Test
    public void testAdd() {
        boolean caught = false;
        try {
            int i = 1;
            L1.add(0, 300);
            while (i <= 13) {

                L1.add(i, i * i);
                i++;
            }

            L1.add(5, 20);
            assertEquals(300, L1.get(0));
            assertEquals(121, L1.get(12));
            assertEquals(20, L1.get(5));
            assertEquals(25, L1.get(6));
            System.out.println(L1.toString());
        } catch (InvalidIndexException iie) {
            System.out.println(iie.toString());
            caught = true;
            assertEquals(caught, true);
        }
    }

    @Test
    public void testAddHigh() {
        boolean caught = false;
        try {
            int i = 1;
            L1.add(0, 300);
            while (i <= 13) {

                L1.add(i, i * i);
                i++;
            }

            L1.add(L1.getCount() + 1, 20);
        } catch (InvalidIndexException iie) {
            System.out.println("add high " + iie.toString());
            caught = true;
            assertEquals(caught, true);
        }
    }

    @Test
    public void testAddlow() {
        boolean caught = false;
        try {
            int i = 1;
            L1.add(0, 300);
            while (i <= 13) {

                L1.add(i, i * i);
                i++;
            }

            L1.add(-1, 20);
        } catch (InvalidIndexException iie) {
            System.out.println("add low " + iie.toString());
            caught = true;
            assertEquals(caught, true);
        }
    }

    @Test
    public void testAddzero() {
        boolean caught = false;
        try {
            int i = 1;
            L1.add(0, 300);
            while (i <= 13) {

                L1.add(i, i * i);
                i++;
            }

            L1.add(0, 20);
            assertEquals(L1.get(0), 20);
        } catch (InvalidIndexException iie) {
            System.out.println("add zero " + iie.toString());
            caught = true;
            assertEquals(caught, true);
        }
    }

    /**
     * Test of removeAt method, of class LongArrayList.
     */
    @Test
    public void testRemoveAt() {
        boolean caught = false;
        try {
            int i = 1;
            long value = 0;
            L1.add(0, 300);
            while (i <= 13) {

                L1.add(i, i * i);
                i++;
            }

            L1.add(5, 20);
            value = L1.removeAt(14);
            assertEquals(169, value);
            value = L1.removeAt(15);
            assertEquals(Long.MIN_VALUE, value);
            value = L1.removeAt(-5);
            assertEquals(Long.MIN_VALUE, value);
        } catch (InvalidIndexException iie) {
            System.out.println(iie.toString());
            caught = true;
            assertEquals(caught, true);
        }
    }
    @Test
    public void testRemoveAtLow() {
        boolean caught = false;
        try {
            int i = 1;
            L1.add(0, 300);
            while (i <= 13) {

                L1.add(i, i * i);
                i++;
            }

            L1.add(5, 20);
            L1.removeAt(-1); 
        } catch (InvalidIndexException iie) {
            System.out.println(iie.toString());
            caught = true;
            assertEquals(caught, true);
        }
    }
    
    @Test
    public void testRemoveAtHigh() {
        boolean caught = false;
        try {
            int i = 1;
            L1.add(0, 300);
            while (i <= 13) {

                L1.add(i, i * i);
                i++;
            }

            L1.add(5, 20);
            L1.removeAt(15); 
        } catch (InvalidIndexException iie) {
            System.out.println(iie.toString());
            caught = true;
            assertEquals(caught, true);
        }
    }

    /**
     * Test of remove method, of class LongArrayList.
     */
    @Test
    public void testRemove() {
        boolean caught = false;
        try {
            int i = 1;
            long value = 0;
            L1.add(0, 300);
            while (i <= 13) {

                L1.add(i, i * i);
                i++;
            }
            L1.remove(300);
            value = L1.get(0);
            assertEquals(1, value);
        } catch (InvalidIndexException iie) {
            System.out.println(iie.toString());
            caught = true;
            assertEquals(caught, true);
        }
    }

    @Test
    public void testRemove1() {
        boolean caught = false;
        try {
            Random myRand = new Random();
            for (int i = 0; i < 15; i++) {
                L1.add(i, myRand.nextLong());
            }
            long testValue = L1.get(5);
            long removeValue = L1.get(4);

            L1.remove(removeValue);
            assertEquals(testValue, L1.get(4));
        } catch (InvalidIndexException iie) {
            System.out.println(iie.toString());
            caught = true;
            assertEquals(caught, true);
        }
    }

    /**
     * Test of get method, of class LongArrayList.
     */
    @Test
    public void testGetLow() {
        boolean caught = false;
        try {
            Random myRand = new Random();
            for (int i = 0; i < 15; i++) {

                L1.add(i, myRand.nextLong());
            }
            long testValue = L1.get(5);
            long removeValue = L1.get(4);
            L1.get(-1);
        } catch (InvalidIndexException iie) {
            System.out.println(iie.toString());
            caught = true;
            assertEquals(caught, true);
        }
    }

    @Test
    public void testGetLowIndex() {
        boolean caught = false;
        try {
            Random myRand = new Random();
            for (int i = 0; i < 15; i++) {

                L1.add(i, myRand.nextLong());
            }
            L1.get(-1);
        } catch (InvalidIndexException iie) {
            System.out.println(iie.toString());
            caught = true;
            assertEquals(caught, true);
        }
    }

    @Test
    public void testGetHigh() {
        boolean caught = false;
        try {
            Random myRand = new Random();
            for (int i = 0; i < 15; i++) {

                L1.add(i, myRand.nextLong());
            }
            long testValue = L1.get(5);
            long removeValue = L1.get(4);
            L1.get(L1.getCount());
        } catch (InvalidIndexException iie) {
            System.out.println(iie.toString());
            caught = true;
            assertEquals(caught, true);
        }
    }

    /**
     * Test of indexOf method, of class LongArrayList.
     */
//    @Test
//    public void testIndexOf() {
//    }
//
//    /**
//     * Test of getCount method, of class LongArrayList.
//     */
//    @Test
//    public void testGetCount() {
//    }

    /**
     * Test of toString method, of class LongArrayList.
     */
    @Test
    public void testToString() {
        boolean caught = false;
        try {
            int i = 1;
            long value = 0;
            L1.add(0, 300);
            while (i <= 13) {

                L1.add(i, i * i);
                i++;
            }

            L1.add(5, 20);
            System.out.println(L1.toString());
            assertEquals(L1.get(5), 20);
        } catch (InvalidIndexException iie) {
            System.out.println(iie.toString());
            caught = true;
            assertEquals(caught, true);
        }
    }

}
