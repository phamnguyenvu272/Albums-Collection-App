/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package numberlist.objectlist;

import numberlist.InvalidIndexException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author 955278275
 */
public class NumericLinkedListTest {

    NumericLinkedList myList;

    public NumericLinkedListTest() {
        myList = new NumericLinkedList();
        boolean caught = false;
        try {
            myList = new NumericLinkedList();
            for (int i = 0; i < 10; i++) {
                myList.add(i, new Complex(-1 * i, i * i));
            }
        } catch (InvalidIndexException iie) {
            System.out.println("before anything" + iie.toString());
        }
    }

    @Before
    public void setUp() {
    }

    /**
     * Test of add method, of class NumericLinkedList.
     */
    @Test
    public void testAddtoEnd() {
        boolean caught = false;
        try {
            myList.add(myList.getCount() + 1, new Complex(5, 5));
            assertEquals(myList.get(4).toString(), "-4.0 - 16.0i");
        } catch (InvalidIndexException iie) {
            System.out.println(iie.toString());
            caught = true;
            assertEquals(caught, true);
        }
    }

    @Test
    public void testAddlowIndex() {
        boolean caught = false;
        try {
            myList.add(-1, new Complex(5, 5));
            assertEquals(myList.get(0).toString(), "0.0");
        } catch (InvalidIndexException iie) {
            System.out.println(iie.toString());
            caught = true;
            assertEquals(caught, true);
        }
    }

    @Test
    public void testAddNoIndex() {
        boolean caught = false;

        assertEquals(myList.add(new Complex(5, 5)), myList.getCount() - 1);
        System.out.println("Add no index" + myList.toString());
    }

    @Test
    public void testAddtoStart() {
        boolean caught = false;

        try {

            assertEquals(myList.get(4).toString(), "-4.0 + 16.0i");
        } catch (InvalidIndexException ex) {
            System.out.println(ex.toString());
            caught = true;
            assertEquals(caught, true);
        }
    }

    @Test
    public void testAddMiddle() {
        boolean caught = false;
        myList = new NumericLinkedList();
        try {

            for (int i = 0; i < 10; i++) {
                myList.add(i, new Complex(-1 * i, i * -4));
            }
            System.out.println("add2" + myList.toString());
            myList.add(5, new Complex(20, 19));
            System.out.println("add2" + myList.toString());
            assertEquals(myList.get(5).toString(), "20.0 + 19.0i");

        } catch (InvalidIndexException ex) {
            System.out.println(ex.toString());
            caught = true;
            assertEquals(caught, true);
        }

    }

    /**
     * Test of removeAt method, of class NumericLinkedList.
     */
    @Test
    public void testRemoveAt() {
        boolean caught = false;
        try {
            assertEquals(myList.removeAt(5).toString(), "-5.0 + 25.0i");

            assertEquals(myList.get(5).toString(), "-6.0 + 36.0i");
        } catch (InvalidIndexException ex) {
            System.out.println(ex.toString());
            caught = true;
            assertEquals(caught, true);
        }
    }

    @Test
    public void testRemoveAtFirst() {
        boolean caught = false;
        try {
            assertEquals(myList.removeAt(0).toString(), "0.0");
        } catch (InvalidIndexException ex) {
            System.out.println(ex.toString());
            caught = true;
            assertEquals(caught, true);
        }

    }

    @Test
    public void testRemoveAtMiddle() {
        boolean caught = false;
        try {
            System.out.println("atMiddle " + myList.toString());
            assertEquals(myList.removeAt(myList.getCount() / 2).toString(), "-5.0 + 25.0i");
            System.out.println("atMiddle " + myList.toString());
        } catch (InvalidIndexException ex) {
            System.out.println(ex.toString());
            caught = true;
            assertEquals(caught, true);
        }
    }

    @Test
    public void testRemovenegOne() {
        boolean caught = false;
        try {
            System.out.println("atLast " + myList.toString());
            assertEquals(myList.removeAt(myList.getCount() - 1).toString(), "-9.0 + 81.0i");
            //out of bounds tests
            myList.removeAt(-1);
        } catch (InvalidIndexException ex) {
            System.out.println(ex.toString());
            caught = true;
            assertEquals(caught, true);
        }
    }

    @Test
    public void testRemoveplusOne() {
        boolean caught = false;
        try {
            System.out.println("atLast " + myList.toString());
            assertEquals(myList.removeAt(myList.getCount() - 1).toString(), "-9.0 + 81.0i");
            //out of bounds tests
            myList.removeAt(myList.getCount());
        } catch (InvalidIndexException ex) {
            System.out.println(ex.toString());
            caught = true;
            assertEquals(caught, true);
        }
    }

    /**
     * Test of remove method, of class NumericLinkedList.
     */
    @Test
    public void testRemove() {
        boolean caught = false;
        try {
            Complex testComplex = new Complex(-1, 1);
            myList.remove(myList.get(0));
            assertEquals(myList.get(0).toString(), testComplex.toString());
        } catch (InvalidIndexException ex) {
            System.out.println(ex.toString());
            caught = true;
            assertEquals(caught, true);
        }

    }

    @Test
    public void testRemoveFirst() {
        boolean caught = false;
        try {
            Complex testComplex = (Complex) myList.get(1);
            myList.remove(myList.get(0));
            assertEquals(myList.get(0).toString(), testComplex.toString());
        } catch (InvalidIndexException ex) {
            System.out.println(ex.toString());
            caught = true;
            assertEquals(caught, true);
        }

    }

    @Test
    public void testRemoveMiddle() {
        boolean caught = false;
        try {
            Complex testComplex = (Complex) myList.get((myList.getCount() / 2) + 1);
            System.out.println("Middle " + myList.toString());
            myList.remove(myList.get(myList.getCount() / 2));
            assertEquals(myList.get(5).toString(), testComplex.toString());
        } catch (InvalidIndexException ex) {
            System.out.println(ex.toString());
            caught = true;
            assertEquals(caught, true);
        }

    }

    @Test
    public void testRemoveLast() {
        boolean caught = false;
        try {
            System.out.println("remove last: " + myList.toString());
            myList.remove(myList.get(myList.getCount() - 1));
            System.out.println("remove last: " + myList.toString());
            assertEquals(myList.get(myList.getCount() - 1).toString(), myList.get(myList.getCount() - 1).toString());
            System.out.println("remove last: " + myList.toString());
        } catch (InvalidIndexException ex) {
            System.out.println(ex.toString());
            caught = true;
            assertEquals(caught, true);
        }

    }

    @Test
    public void testRemoveAtLastplusone() {
        boolean caught = false;
        try {
            System.out.println("removeAt last + 1: " + myList.toString());
            myList.removeAt(myList.getCount());
        } catch (InvalidIndexException ex) {
            System.out.println(ex.toString());
            caught = true;
            assertEquals(caught, true);
        }

    }

    @Test
    public void testRemoveNonExistant() {
        boolean caught = false;
        try {
            Complex testComplex = new Complex(45, 265);
            myList.remove(testComplex);
            assertEquals(myList.get(myList.getCount() - 1).toString(), "-9.0 + 81.0i");
        } catch (InvalidIndexException ex) {
            System.out.println(ex.toString());
            caught = true;
            assertEquals(caught, true);
        }

    }

    @Test
    public void testRemoveNoll() {
        boolean caught = false;
        try {
            myList.remove(null);
            assertEquals(myList.get(myList.getCount() - 1).toString(), "-9.0 + 81.0i");
        } catch (InvalidIndexException ex) {
            System.out.println(ex.toString());
            caught = true;
            assertEquals(caught, true);
        }

    }

    /**
     * Test of get method, of class NumericLinkedList.
     */
    @Test
    public void testGet() {
        boolean caught = false;
        try {
            assertEquals(myList.get(4).toString(), "-4.0 + 16.0i");
        } catch (InvalidIndexException ex) {
            System.out.println(ex.toString());
            caught = true;
            assertEquals(caught, true);
        }
    }

    /**
     * Test of indexOf method, of class NumericLinkedList.
     */
    @Test
    public void testIndexOf() {

        Complex newish = new Complex(-4.0, 16.0);

        int index = myList.indexOf(newish);
        assertEquals(index, 4);
        assertEquals(myList.indexOf(null), -1);
    }

    /**
     * Test of getCount method, of class NumericLinkedList.
     */
    @Test
    public void testGetCount() {
        assertEquals(myList.getCount(), 10);
    }

    /**
     * Test of toString method, of class NumericLinkedList.
     */
    @Test
    public void testToString() {
        boolean caught = false;
        try {
            myList.add(0, new Complex(-2, -4));
            assertEquals(myList.toString(), myList.toString());
        } catch (InvalidIndexException ex) {
            System.out.println(ex.toString());
            caught = true;
            assertEquals(caught, true);
        }
    }

    @Test
    public void testDeepCopy() {
        NumericLinkedList otherList;
        otherList = myList.deepCopy();
        System.out.println("Deep copy MyList" + myList.toString());
        System.out.println("DeepCopy otherList" + otherList.toString());
        assertEquals(myList.toString(), otherList.toString());
        assertNotEquals(myList, otherList);
    }

    @Test
    public void testSettoEnd() {
        boolean caught = false;
        try {
            myList.set(myList.getCount(), new Complex(5, 5));
        } catch (InvalidIndexException iie) {
            System.out.println("set" + iie.toString());
            caught = true;
            assertEquals(caught, true);
        }
    }

    @Test
    public void testSettbegin() {
        boolean caught = false;
        try {
            myList.set(-1, new Complex(5, 5));
        } catch (InvalidIndexException iie) {
            System.out.println("set -1" + iie.toString());
            caught = true;
            assertEquals(caught, true);
        }
    }
 @Test
    public void testSetmiddle() {
        boolean caught = false;
        try {
            
            Copiable temp = myList.set(myList.getCount() / 2, new Complex(5, 5));
            assertEquals(temp.toString(), "-5.0 + 25.0i");
        } catch (InvalidIndexException iie) {
            System.out.println("set middle" + iie.toString());
            caught = true;
            assertEquals(caught, true);
        }
    }
   @Test  
    public void testSetzero() {
        boolean caught = false;
        try {
            Copiable temp = myList.set(0, new Complex(5, 5));
            assertEquals(temp.toString(), "0.0");
        } catch (InvalidIndexException iie) {
            System.out.println("set zero " + iie.toString());
            caught = true;
            assertEquals(caught, true);
        }
    }
}
