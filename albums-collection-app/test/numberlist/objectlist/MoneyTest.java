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
public class MoneyTest {

    Money myMoney;

    public MoneyTest() {
        myMoney = new Money(100, (byte) 10);
    }

    @Before
    public void setUp() {
    }

    /**
     * Test of getdollars method, of class Money.
     */
    @Test
    public void testGetdollarsnegCents() {
        myMoney = new Money(500, (byte) -100);
        System.out.println("Get Dollars with neg cents: " + myMoney.getDollars());
        assertEquals(499, myMoney.getDollars());
    }

    @Test
    public void testGetdollarsNeg() {
        myMoney = new Money(-300, (byte) -100);
        System.out.println("Get Dollars with neg: " + myMoney.getDollars());
        assertEquals(-301, myMoney.getDollars());
    }

    @Test
    public void testGetdollarsPos() {
        myMoney = new Money(7154, (byte) 120);
        System.out.println("Get Dollars with cents > 100: " + myMoney.toString());
        assertEquals(7155, myMoney.getDollars());
    }

    /**
     * Test of getcents method, of class Money.
     */
    @Test
    public void testGetcents() {
    }

    /**
     * Test of add method, of class Money.
     */
    @Test
    public void testAdd() {
        Money money2 = new Money(12, (byte) 5);
        myMoney = myMoney.add(money2);
        assertEquals(myMoney.toString(), "$112.15");
    }

    @Test
    public void testAddNegCents() {
        myMoney = new Money(-20, (byte) -99);
        Money money2 = new Money(18, (byte) -45);
        myMoney = myMoney.add(money2);
        System.out.println("addNeg" + myMoney.toString());
        assertEquals(myMoney.getCents(), -44);
        assertEquals(myMoney.getDollars(), -3);
    }

    @Test
    public void testAddNetPosCents() {
        myMoney = new Money(-20, (byte) -34);
        Money money2 = new Money(-10, (byte) 45);
        myMoney = myMoney.add(money2);
        System.out.println("net positive cents " + myMoney.toString());
        assertEquals(myMoney.getCents(), -89);
        assertEquals(myMoney.getDollars(), -29);
    }
    @Test
    public void testAddNetNegCents() {
        myMoney = new Money(10, (byte) 17);
        Money money2 = new Money(10, (byte) -95 );
        myMoney = myMoney.add(money2);
        System.out.println("net negative cents" + myMoney.toString());
        assertEquals(myMoney.getCents(), 22);
        assertEquals(myMoney.getDollars(), 19);
    }
    @Test
    public void testAddNetNeg() {
        myMoney = new Money(-100, (byte) -90);
        Money money2 = new Money(10, (byte) -95 );
        myMoney = myMoney.add(money2);
        System.out.println("net negative cents" + myMoney.toString());
        assertEquals(myMoney.getCents(), -85);
        assertEquals(myMoney.getDollars(), -91);
    }

    /**
     * Test of subtract method, of class Money.
     */
    @Test
    public void testSubtract() {
        Money money2 = new Money(-115, (byte) -5);
        myMoney = myMoney.subtract(money2);
        assertEquals(myMoney.toString(), "$215.15");
    }
    
     @Test
    public void testSubtractNeg() {
        Money money2 = new Money(115, (byte) 5);
        myMoney = myMoney.subtract(money2);
        assertEquals(myMoney.toString(), "-$14.95");
    }

    /**
     * Test of toString method, of class Money.
     */
    @Test
    public void testToString() {
        Money myMoney = new Money(-10, (byte) 10);
        String output = myMoney.toString();
        assertEquals(output, "-$9.90");

    }
    
    @Test
    public void testDeepCopy(){
        Money myMoney = new Money(-10, (byte) 10);
        Money newMoney = myMoney.deepCopy();
        
        assertEquals(myMoney.getDollars(), newMoney.getDollars());
        assertNotEquals(myMoney,newMoney);
        System.out.println("deep copy test " + myMoney.equals(newMoney));
             
    }
}
