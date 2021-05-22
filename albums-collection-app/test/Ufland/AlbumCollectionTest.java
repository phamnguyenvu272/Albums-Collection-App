/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ufland;

import project4.AlbumCollection;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import numberlist.InvalidIndexException;
import numberlist.objectlist.Money;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author 955278275
 */
public class AlbumCollectionTest {

    AlbumCollection L1 = new AlbumCollection();

    public AlbumCollectionTest() {

    }

    @Before
    public void setUp() {
    }

    /**
     * Test of add method, of class AlbumCollection.
     */
    @Test
    public void testAdd() {
        for (int i = 0; i < 10; i++) {
            L1.addAlbum("Artist" + i, "title" + i, "genre" + i, 1990 + i, new Money(i + 2, (byte) (i + 10)));
        }
        assertEquals(L1.getArtist(1), "Artist1");

    }

    /**
     * Test of deleteAlbumAt method, of class AlbumCollection.
     */
    @Test
    public void testDeleteAlbumAt() {
        for (int i = 0; i < 10; i++) {
            L1.addAlbum("Artist" + i, "title" + i, "genre" + i, 1990 + i, new Money(i + 2, (byte) (i + 10)));
        }
        try {

            L1.deleteAlbumAt(3);
            assertEquals(L1.getArtist(3), "Artist4");
            assertEquals(L1.getTitle(3), "title4");
            assertEquals(L1.getGenre(3), "genre4");
            assertEquals(L1.getYear(3), 1994);
        } catch (InvalidIndexException ex) {
            Logger.getLogger(AlbumCollectionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of updateAlbum method, of class AlbumCollection.
     */
    @Test
    public void testUpdateAlbum() {
    }

    /**
     * Test of saveCollection method, of class AlbumCollection.
     */
    @Test
    public void testSaveCollection() {

    }

    /**
     * Test of addAlbum method, of class AlbumCollection.
     */
    @Test
    public void testAddAlbum() {
    }

    /**
     * Test of sortByArtist method, of class AlbumCollection.
     */
    @Test
    public void testSortByArtist() {
        Random rand = new Random();

        for (int i = 0; i < 10; i++) {
            L1.addAlbum("Artist" + rand.nextInt(10), "title" + i, "genre" + i, 1990 + i, new Money(i + 1, (byte) (i + 10)));
        }
        System.out.println("Testing sortByArtist before sorting: ");
        for (int i = 0; i < 10; i++) {
            System.out.println(L1.getArtist(i).toString());
        }
        L1.sortByArtist();
        System.out.println("List sorted by Artist: ");

        for (int i = 0; i < 10; i++) {
            System.out.println(L1.getArtist(i).toString());
        }
    }

    /**
     * Test of sortByYear method, of class AlbumCollection.
     */
    @Test
    public void testSortByYear() {
        Random rand = new Random();

        for (int i = 0; i < 10; i++) {
            L1.addAlbum("Artist" + i, "title" + i, "genre" + i, 1990 + rand.nextInt(10), new Money(i + 1, (byte) (i + 10)));
        }

        System.out.println("Test sortByYear before sorting: ");
        for (int i = 0; i < 10; i++) {
            try {
                System.out.println(L1.getYear(i));
            } catch (InvalidIndexException ex) {
                Logger.getLogger(AlbumCollectionTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        L1.sortByYear();
        System.out.println("List sorted by year: ");
        for (int i = 0; i < 10; i++) {
            try {
                System.out.println(L1.getYear(i));
            } catch (InvalidIndexException ex) {
                Logger.getLogger(AlbumCollectionTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Test of sortByPrice method, of class AlbumCollection.
     */
    @Test
    public void testSortByPrice() {

        Random rand = new Random();

        for (int i = 0; i < 10; i++) {
            L1.addAlbum("Artist" + i, "title" + i, "genre" + i, 1990 + i, new Money(i + rand.nextInt(10), (byte) (i + 10)));
        }
        System.out.println("Testing sortByPrice before sorting: ");
        for (int i = 0; i < 10; i++) {
            try {
                System.out.println(L1.getPrice(i).toString());
            } catch (InvalidIndexException ex) {
                Logger.getLogger(AlbumCollectionTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        L1.sortByPrice();
        System.out.println("List sorted by price: ");
        for (int i = 0; i < 10; i++) {
            try {
                System.out.println(L1.getPrice(i).toString());
            } catch (InvalidIndexException ex) {
                Logger.getLogger(AlbumCollectionTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Test of sortByGenre method, of class AlbumCollection.
     */
    @Test
    public void testSortByGenre() {

        Random rand = new Random();

        for (int i = 0; i < 10; i++) {
            L1.addAlbum("Artist" + i, "title" + i, "genre" + rand.nextInt(10), 1990 + i, new Money(i + i, (byte) (i + 10)));
        }
        System.out.println("Testing sortingByGenre: ");
        for (int i = 0; i < 10; i++) {
            System.out.println(L1.getGenre(i).toString());
        }
        L1.sortByGenre();
        System.out.println("List sorted by genre: ");
        for (int i = 0; i < 10; i++) {
            System.out.println(L1.getGenre(i).toString());
        }

    }

    /**
     * Test of sortByTitle method, of class AlbumCollection.
     */
    @Test
    public void testSortByTitle() {

        Random rand = new Random();

        for (int i = 0; i < 10; i++) {
            L1.addAlbum("Artist" + i, "title" + rand.nextInt(10), "genre" + i, 1990 + i, new Money(i + i, (byte) (i + 10)));
        }
        System.out.println("Testing sortingByTitle: ");
        for (int i = 0; i < 10; i++) {
            System.out.println(L1.getTitle(i).toString());
        }
        L1.sortByTitle();
        System.out.println("List sorted by title: ");
        for (int i = 0; i < 10; i++) {
            System.out.println(L1.getTitle(i).toString());
        }

    }

    @Test
    public void testGetTitle() {
        Random rand = new Random();

        for (int i = 0; i < 10; i++) {
            L1.addAlbum("Artist" + i, "title" + i, "genre" + i, 1990 + i, new Money(i + i, (byte) (i + 10)));
        }

        String test = L1.getTitle(8);

        assertTrue(test != L1.getTitle(8));
        assertTrue(test.equals(L1.getTitle(8)));

    }

    @Test
    public void testGetMoney() {

        try {
            for (int i = 0; i < 10; i++) {
                L1.addAlbum("Artist" + i, "title" + i, "genre" + i, 1990 + i, new Money(i + i, (byte) (i + 10)));
            }

            Money test = L1.getPrice(8);
            assertTrue(test != L1.getPrice(8));
            assertTrue(test.toString().equals(L1.getPrice(8).toString()));
        } catch (InvalidIndexException ex) {
            Logger.getLogger(AlbumCollectionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testGetAverageValue(){
     for (int i = 0; i < 10; i++) {
            L1.addAlbum("Artist" + i, "title" + i, "genre" + i, 1990 + i, new Money(1 + i , (byte) (i+5)));
        }
     
        System.out.println("Testing GetAverageValue. These are the Money values: ");
        for (int i = 0; i < 10; i++) {
         try {
             System.out.println(L1.getPrice(i).toString());

         } catch (InvalidIndexException ex) {
             Logger.getLogger(AlbumCollectionTest.class.getName()).log(Level.SEVERE, null, ex);
         }         
                     
        }
       double temp =  L1.getAverageValue();
       
        System.out.println("This is average: "+temp);
    }
}
