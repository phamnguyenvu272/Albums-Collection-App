/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package numberlist.primitivelist;

import numberlist.InvalidIndexException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author 955278275
 */
public class DoubleArrayListTest {

    DoubleArrayList L1, myList, L2;

    public DoubleArrayListTest() {

            L1 = new DoubleArrayList();
            myList = new DoubleArrayList();
            L2 = new DoubleArrayList();
    }

    @Before
    public void setUp() {
    }

    /**
     * Test of add method, of class DoubleArrayList.
     */
    @Test
    public void testAdd() {
        boolean caught = false;
        try {

           
            int i = 1;
            L1.add(0, 300);
            while (i <= 13) {

                L1.add(i, 1 / (1.0 + (i)));
                i++;
            }
            L1.add(5, 20);
            double value = 300 - L1.get(0);
            System.out.println(String.valueOf(value));
            System.out.println(L1.get(4));
            assertEquals(0.2, L1.get(4), 0.001);
        } catch (InvalidIndexException ex) {
            System.out.println(ex.toString());
            caught = true;
            assertEquals(caught, true);
        }

    }

    /**
     * Test of removeAt method, of class DoubleArrayList.
     */
    @Test
    public void testRemoveAt() {
        boolean caught = false;
        try {
            int i = 1;
            double value = 0;
            L1.add(0, 300);
            while (i <= 13) {

                L1.add(i, i * i);
                i++;
            }

            L1.add(5, 20);
            value = L1.removeAt(14);
            assertEquals(169, value, .0000001);
        } catch (InvalidIndexException ex) {
            System.out.println(ex.toString());
            caught = true;
            assertEquals(caught, true);
        }
    }

    @Test
    public void testRemoveAtLast() {
        boolean caught = false;
        try {
            int i = 1;
            double value = 0;
            L1.add(0, 300);
            while (i <= 13) {

                L1.add(i, i * i);
                i++;
            }

            L1.add(5, 20);
            L1.removeAt(L1.getCount());
        } catch (InvalidIndexException iie) {
            System.out.println(iie.toString());
            caught = true;
            assertEquals(caught, true);
        }
    }

    @Test
    public void testRemoveAtLast2() {
        boolean caught = false;
        try {
            int i = 1;
            double value = 0;
            L1.add(0, 300);
            while (i <= 13) {

                L1.add(i, i * i);
                i++;
            }

            L1.add(5, 20);
            value = L1.removeAt(L1.getCount() + 1);
       } catch (InvalidIndexException iie) {
            System.out.println(iie.toString());
            caught = true;
            assertEquals(caught, true);
        }
    }

    /**
     * Test of remove method, of class DoubleArrayList.
     */
    @Test
    public void testRemoveAtbefore() {
        boolean caught = false;
        try {
            int i = 1;
            L1.add(0, 300);
            while (i <= 13) {

                L1.add(i, i * i);
                i++;
            }
            L1.remove(300);
            double value = L1.get(-1);
        } catch (InvalidIndexException iie) {
            System.out.println(iie.toString());
            caught = true;
            assertEquals(caught, true);
        }
    }

    /**
     * Test of get method, of class DoubleArrayList.
     */
    @Test
    public void testGetLow() {
        boolean caught = false;
        try {
            int i = 1;
            L1.add(0, 300);
            while (i <= 13) {

                L1.add(i, 1 / (1.0 + (i)));
                i++;
            }

            L1.add(5, 20);
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
            int i = 1;
            L1.add(0, 300);
            while (i <= 13) {

                L1.add(i, 1 / (1.0 + (i)));
                i++;
            }

            L1.add(5, 20);

            L1.get(L1.getCount());
        } catch (InvalidIndexException iie) {
            System.out.println(iie.toString());
            caught = true;
            assertEquals(caught, true);
        }
    }

    /**
     * Test of indexOf method, of class DoubleArrayList.
     */
    @Test
    public void testIndexOfOutofBoundsLow() {
        boolean caught = false;
        try {
            int i = 1;
            L1.add(0, 300);
            while (i <= 13) {

                L1.add(i, 1 / (1.0 + (i)));
                i++;
            }

            L1.add(5, 20);
            System.out.println(L1.toString());
            L1.get(-1);
        } catch (InvalidIndexException iie) {
            System.out.println(iie.toString());
            caught = true;
            assertEquals(caught, true);
        }
    }

    @Test
    public void testIndexOfOutofBoundsHigh() {
        boolean caught = false;
        try {
            int i = 1;
            L1.add(0, 300);
            while (i <= 13) {

                L1.add(i, 1 / (1.0 + (i)));
                i++;
            }

            L1.add(5, 20);
            System.out.println(L1.toString());
            L1.indexOf(L1.get(L1.getCount()));
        } catch (InvalidIndexException iie) {
            System.out.println(iie.toString());
            caught = true;
            assertEquals(caught, true);
        }
    }

    /**
     * Test of getCount method, of class DoubleArrayList.
     */
    @Test
    public void testGetCount() {
        boolean caught = false;
        try {
            int i = 1;
            L1.add(0, 300);
            while (i <= 13) {

                L1.add(i, 1 / (1.0 + (i)));
                i++;
            }

            L1.add(5, 20);
            assertEquals(L1.getCount(), 15);
        } catch (InvalidIndexException iie) {
            System.out.println(iie.toString());
            caught = true;
            assertEquals(caught, true);
        }
    }

    @Test
    public void testToString() {
        boolean caught = false;
        try {
            L1.add(0, 12.34);
            L1.add(1, 34.56);
            L1.add(2, 56.78);
            L1.add(3, 78.90);
            myList.add(0, 12.34);
            myList.add(1, 34.56);
            myList.add(2, 56.78);
            myList.add(3, 78.90);

            System.out.println(L2.toString());
            System.out.println(L1.toString());
            System.out.println(myList.toString());
            assertEquals(myList.toString(), L1.toString());
            assertEquals(L2.toString(), "[ ]");
        } catch (InvalidIndexException iie) {
            System.out.println(iie.toString());
            caught = true;
            assertEquals(caught, true);
        }
    }

}
