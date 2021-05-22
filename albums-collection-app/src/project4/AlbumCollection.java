package project4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Scanner;
import numberlist.InvalidIndexException;
import numberlist.objectlist.Copiable;
import numberlist.objectlist.Money;
import numberlist.objectlist.NumericArrayList;
import numberlist.primitivelist.IntegerArrayList;

public class AlbumCollection {

    private ArrayList<String> artist;
    private ArrayList<String> title;
    private IntegerArrayList year;
    private ArrayList<String> genre;
    private NumericArrayList price;
    String fileName = "src/Images/albums.txt";

    public AlbumCollection() {
        artist = new ArrayList<>();
        title = new ArrayList<>();
        year = new IntegerArrayList();
        genre = new ArrayList<>();
        price = new NumericArrayList();
    }

    public void addAlbum(String artist, String title, String genre, int year, Money price) {
        this.artist.add(artist);
        this.title.add(title);
        this.genre.add(genre);
        this.year.add(year);
        this.price.add(price);
    }

    public void deleteAlbumAt(int index) throws InvalidIndexException {
        try {
            artist.remove(index);
            title.remove(index);
            genre.remove(index);
            year.removeAt(index);
            price.removeAt(index);
        } catch (InvalidIndexException ex) {
            throw ex;
        }
    }

    public boolean updateAlbum(int index, String artist, String title,
            String genre, int year, Money price) throws InvalidIndexException {
        boolean isUpdated;
        try {
            this.artist.set(index, artist);
            this.title.set(index, title);
            this.genre.set(index, genre);
            this.year.set(index, year);
            this.price.set(index, price);
            isUpdated = true;
        } catch (InvalidIndexException ex) {
            isUpdated = false;
            throw ex;
        }
        return isUpdated;
    }

    public double getAverageValue() {
        double avg = 0.0;
        for (int i = 0; i < price.getCount(); i++) {
            try {
                avg += (((Money) price.get(i)).getDollars());
                avg += ((Money) price.get(i)).getCents() / 100.0;
            } catch (InvalidIndexException ex) {
                //shouldn't throw an exception
            }
        }
        avg /= price.getCount();
        avg = new BigDecimal(avg).round(new MathContext(2)).doubleValue();
        return avg;
    }


    public void sortByArtist() {
        for (int i = 1; i < artist.size(); i++) {
            for (int j = i; j > 0; j--) {
                if (artist.get(j).compareTo(artist.get(j - 1)) < 0) {
                    swap(artist, title, year, genre, price, j);
                }
            }
        }
    }

    public void sortByYear() {
        for (int i = 1; i < year.getCount(); i++) {
            for (int j = i; j > 0; j--) {
                try {
                    if (year.get(j) < year.get(j - 1)) {
                        swap(artist, title, year, genre, price, j);
                    }
                } catch (InvalidIndexException ex) {
                   
                }
            }
        }
    }

    public void sortByPrice() {
        for (int i = 1; i < price.getCount(); i++) {
            for (int j = i; j > 0; j--) {
                try {
                    if (((Money) price.get(j)).compareTo(((Money) price.get(j - 1))) < 0) { /// Double check on this one
                        swap(artist, title, year, genre, price, j);
                    }
                } catch (InvalidIndexException ex) {
                    //shouldn't throw an exception
                }
            }
        }
    }

    public void sortByGenre() {
        for (int i = 1; i < genre.size(); i++) {
            for (int j = i; j > 0; j--) {
                if (genre.get(j).compareTo(genre.get(j - 1)) < 0) {
                    swap(artist, title, year, genre, price, j);
                }
            }
        }
    }

    public void sortByTitle() {
        for (int i = 1; i < title.size(); i++) {
            for (int j = i; j > 0; j--) {
                if (title.get(j).compareTo(title.get(j - 1)) < 0) {
                    swap(artist, title, year, genre, price, j);
                }
            }
        }
    }

    public String getArtist(int index) {
        String temp = new String(artist.get(index));
        return temp;
    }

    public String getTitle(int index) {
        String temp = new String(title.get(index));
        return temp;
    }

    public int getYear(int index) throws InvalidIndexException {
        return (int) year.get(index);
    }

    public String getGenre(int index) {
        String temp = new String(genre.get(index));
        return temp;
    }

    public Money getPrice(int index) throws InvalidIndexException {
        return ((Money) price.get(index)).deepCopy();
    }

    private void swap(ArrayList<String> artist, ArrayList<String> title,
            IntegerArrayList year,
            ArrayList<String> genre, NumericArrayList price, int j) {
        try {
            String tempArtist = artist.get(j);
            String tempTitle = title.get(j);
            String tempGenre = genre.get(j);
            int tempYear = (int) year.get(j);
            Copiable tempPrice = price.get(j);
            artist.set(j, artist.get(j - 1));
            artist.set(j - 1, tempArtist);
            title.set(j, title.get(j - 1));
            title.set(j - 1, tempTitle);
            genre.set(j, genre.get(j - 1));
            genre.set(j - 1, tempGenre);
            year.set(j, year.get(j - 1));
            year.set(j - 1, tempYear);
            price.set(j, price.get(j - 1));
            price.set(j - 1, tempPrice);
        } catch (InvalidIndexException ex) {
            //shouldn't throw an exception
        }
    }

    public int getCount() {
        return artist.size();
    }

    public void createCollection(String name) {
        try {
            File myFile = new File(name);
            myFile.createNewFile();
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }


    public void loadCollection(String inputName) {
        File myFile = new File(inputName);
        if (myFile.exists()) {
            int index = 0;
            try (Scanner scanFile = new Scanner(myFile)) {
                while (scanFile.hasNext()) {
                    artist.add(scanFile.nextLine());
                    title.add(scanFile.nextLine());
                    genre.add(scanFile.nextLine());
                    year.add(Long.valueOf(scanFile.nextLine()));
                    price.add(new Money(Long.valueOf(scanFile.nextLine()),
                            Byte.valueOf(scanFile.nextLine())));
                }
            } catch (FileNotFoundException fnfe) {
                System.out.println(fnfe.getMessage());
            }
        } else {
            createCollection(inputName);
        }
    }

    /**
     * This method writes/overwrites data onto a txt file "students.txt" which
     * was previously created upon instantiating the array lists.
     */
    public void saveCollection(String fileName) {
        File myFile = new File(fileName);
        if (myFile.exists()) {
            {
                try (PrintWriter pw = new PrintWriter(new FileWriter(myFile))) {
                    for (int i = 0; i < artist.size(); i++) {
                        pw.println(artist.get(i));
                        pw.println(title.get(i));
                        pw.println(genre.get(i));
                        pw.println(year.get(i));
                        pw.println(((Money) price.get(i)).getDollars());
                        pw.println(((Money) price.get(i)).getCents());
                    }
                } catch (IOException ioe) {
                    System.out.println(ioe.getMessage());
                } catch (InvalidIndexException iie) {
                    System.out.println(iie.getMessage());
                }
            }
        }
    }
}
