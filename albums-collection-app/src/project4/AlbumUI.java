package project4;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import numberlist.InvalidIndexException;
import numberlist.objectlist.Money;

/**
 *
 *
 * @author Best Group Ever
 * @version 2.0 6/11/18
 *
 */
public class AlbumUI extends Application {

    private final AlbumCollection album = new AlbumCollection();
    private final String albumFile = "src/project4/albums.txt";
    private final Label displayMsg = new Label("");
    private final TextField artistField = new TextField();
    private final TextField titleField = new TextField();
    private final ComboBox genreField = new ComboBox();
    private final TextField yearField = new TextField();
    private final TextField valueField = new TextField();
    private final String defaultCBO = "Select an Option";

    private BorderPane rootPane;
    private VBox topPane;
    private GridPane centerPane;
    private ScrollPane leftPane;
    private HBox bottomPane;
    private VBox box, footer;
    private MenuBar menuBar;
    private Menu fileMenu, sortMenu, graphMenu;
    private MenuItem addMenu, clearMenu, deleteMenu, quitMenu;

    private Button add, clear, delete, quit, newBtn;
    private Label artist, title, genre, year, value, image;
    private RadioButton genreRadio, artistRadio, decadeRadio;
    private ToggleGroup radioGroup;
    private int GblIndex = -1;

    /**
     *
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage) {
        
        Scene scene = new Scene(getRootPane());
        album.loadCollection(albumFile);
        refresh();
        displayMsg.setFont(Font.font("Verdana", FontWeight.SEMI_BOLD, 12));
        primaryStage.setOnCloseRequest(e -> album.saveCollection(albumFile));
        setStage(primaryStage);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     *
     * @param primaryStage
     */
    private void setStage(Stage primaryStage) {
        
        primaryStage.setTitle("Music Album Collection");
        primaryStage.centerOnScreen();
        primaryStage.setHeight(600);
        primaryStage.setWidth(625);
        primaryStage.setMinHeight(600);
        primaryStage.setMinWidth(625);
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/images/album_icon.png")));
    }

    /**
     *
     * @return
     */
    public BorderPane getRootPane() {

        rootPane = new BorderPane();
        rootPane.setTop(getTop());
        rootPane.setLeft(getLeft());
        rootPane.setCenter(getCenter());
        rootPane.setBottom(getBottom());
        rootPane.setBackground(new Background(new BackgroundFill(Color.BEIGE,
                CornerRadii.EMPTY, Insets.EMPTY)));
        rootPane.setOnMouseClicked(e -> displayMsg.setText(""));
        return rootPane;
    }

    /**
     *
     * @return
     */
    public VBox getTop() {

        topPane = new VBox();
        topPane.setAlignment(Pos.CENTER);
        topPane.setBackground(new Background(new BackgroundFill(Color.BEIGE,
                CornerRadii.EMPTY, Insets.EMPTY)));
        topPane.setSpacing(10);
        topPane.getChildren().addAll(getMenu(), displayMsg);
        
        return topPane;
    }

    /**
     *
     * @return
     */
    public ScrollPane getLeft() {

        box = new VBox();
        box.setBackground(new Background(new BackgroundFill(Color.GRAY,
                CornerRadii.EMPTY, Insets.EMPTY)));
        leftPane = new ScrollPane();
        leftPane.setContent(box);
        leftPane.setMinWidth(250);
        leftPane.setMaxWidth(250);
        BorderPane.setMargin(leftPane, new Insets(10, 10, 10, 10));
        
        return leftPane;
    }

    /**
     *
     * @return
     */
    public GridPane getCenter() {
        
        centerPane = new GridPane();
        BorderPane.setMargin(centerPane, new Insets(10, 10, 10, 10));
        centerPane.setBackground(new Background(new BackgroundFill(Color.PALEGREEN,
                CornerRadii.EMPTY, Insets.EMPTY)));
        centerPane.setAlignment(Pos.CENTER);
        centerPane.setMinWidth(270);
        centerPane.setVgap(2);
        
        artist = new Label("Artist Name: ");
        title = new Label("Title Name: ");
        genre = new Label("Genre: ");
        year = new Label("Release Year: ");
        value = new Label("Album Value: ");
        image = new Label();

        centerPane.add(image, 0, 1, 2, 1);
        centerPane.add(artist, 0, 6);
        centerPane.add(artistField, 1, 6);
        centerPane.add(title, 0, 7);
        centerPane.add(titleField, 1, 7);
        centerPane.add(genre, 0, 8);
        centerPane.add(genreField, 1, 8);
        centerPane.add(year, 0, 10);
        centerPane.add(yearField, 1, 10);
        centerPane.add(value, 0, 11);
        centerPane.add(valueField, 1, 11);
        genreField.getItems().addAll("Classical", "Hip-Hop", "Latin",
                "Metal", "Pop", "Rap", "Rock", "Other");
        genreField.setValue(defaultCBO);
        genreField.setMaxWidth(175);
        genreField.setMinWidth(175);
        
        return centerPane;
    }

    /**
     *
     * @return
     */
    public VBox getBottom() {
        footer = new VBox();
        bottomPane = new HBox();
        bottomPane.setAlignment(Pos.CENTER);
        footer.setAlignment(Pos.CENTER);
        footer.setSpacing(10);

        add = new Button("Add");
        add.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(add, Priority.ALWAYS);
        add.setDefaultButton(true);
        add.setOnAction(e -> {
            if (GblIndex < 0) {
                getInput();
            } else {
                add();
            }
        });

        delete = new Button("Delete");
        delete.setDisable(true);
        delete.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(delete, Priority.ALWAYS);
        delete.setOnAction(e -> delete());

        quit = new Button("Quit");
        quit.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(quit, Priority.ALWAYS);
        quit.setOnAction(e -> {
            album.saveCollection(albumFile);
            System.exit(0);
        });

        clear = new Button("Clear");
        clear.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(clear, Priority.ALWAYS);
        clear.setOnAction(e -> clear());


        Label copyright = new Label("Copyright 2018 ACVM Studios - All Rights Reserved");
        copyright.setFont(Font.font("Verdana", 10));
        bottomPane.getChildren().addAll(add, clear, delete, quit);
        footer.getChildren().addAll(bottomPane, copyright);
        BorderPane.setMargin(footer, new Insets(0, 10, 10, 10));
        
        return footer;
    }


    /**
     *
     * @return
     */
    public ImageView getImage() {
        
        Image logo = new Image(getClass().getResourceAsStream("/images/album_stock.png"));
        ImageView imageV = new ImageView(logo);
        imageV.setFitWidth(250);
        imageV.setFitHeight(250);
        image.setGraphic(imageV);
        
        return imageV;
    }

    /**
     *
     * @return
     */
    public ImageView getGenreImage() {
        
        try {
            String genreArt = "/images/" + genreField.getValue().toString() + ".jpg";
            Image albumImage = new Image(getClass().getResourceAsStream(genreArt));
            ImageView imageV = new ImageView(albumImage);
            imageV.setFitWidth(250);
            imageV.setFitHeight(250);
            image.setGraphic(imageV);
            return imageV;
        } catch (NullPointerException | IllegalArgumentException npe) {
            displayMsg.setText("Unable to update image at this time.");
            return new ImageView("album_stock.png");
        }
    }

    /**
     *
     * @return
     */
    public MenuBar getMenu() {

        fileMenu = new Menu("File");
        sortMenu = new Menu("Sort");
        graphMenu = new Menu("Graph");

        MenuItem graphItem = new MenuItem("Graph");
        graphItem.setAccelerator(new KeyCodeCombination(KeyCode.G, 
                KeyCombination.CONTROL_DOWN));
        graphItem.setOnAction(e -> getGraphStage());
        SeparatorMenuItem separator = new SeparatorMenuItem();

        menuBar = new MenuBar(fileMenu, sortMenu, graphMenu);
        menuBar.useSystemMenuBarProperty();
        menuBar.setMaxWidth(Double.MAX_VALUE);

        addMenu = new MenuItem("Add");
        addMenu.setAccelerator(new KeyCodeCombination(KeyCode.A, 
                KeyCombination.CONTROL_DOWN));
        addMenu.setOnAction(e -> add.fire());

        clearMenu = new MenuItem("Clear");
        clearMenu.setAccelerator(new KeyCodeCombination(KeyCode.L, 
                KeyCombination.CONTROL_DOWN));
        clearMenu.setOnAction(e -> clear.fire());

        deleteMenu = new MenuItem("Delete");
        deleteMenu.setAccelerator(new KeyCodeCombination(KeyCode.D, 
                KeyCombination.CONTROL_DOWN));
        deleteMenu.setOnAction(e -> delete.fire());

        quitMenu = new MenuItem("Quit");
        quitMenu.setAccelerator(new KeyCodeCombination(KeyCode.Q, 
                KeyCombination.CONTROL_DOWN));
        quitMenu.setOnAction(e -> quit.fire());

        MenuItem sortArtist = new MenuItem("By Artist");
        sortArtist.setOnAction(e -> {
            album.sortByArtist();
            displayMsg.setText("Sorted successfully by artist.");
            refresh();
        });
        MenuItem sortTitle = new MenuItem("By Title");
        sortTitle.setOnAction(e -> {
            album.sortByTitle();
            displayMsg.setText("Sorted successfully by album title.");
            refresh();
        });
        MenuItem sortGenre = new MenuItem("By Genre");
        sortGenre.setOnAction(e -> {
            album.sortByGenre();
            displayMsg.setText("Sorted successfully by genre.");
            refresh();
        });
        MenuItem sortYear = new MenuItem("By Year");
        sortYear.setOnAction(e -> {
            album.sortByYear();
            displayMsg.setText("Sorted successfully by release year.");
            refresh();
        });
        MenuItem sortValue = new MenuItem("By Price");
        sortValue.setOnAction(e -> {
            album.sortByPrice();
            displayMsg.setText("Sorted successfully by album value.");
            refresh();
        });
        fileMenu.getItems().addAll(addMenu, clearMenu,
                deleteMenu, separator, quitMenu);
        sortMenu.getItems().addAll(sortArtist, sortTitle, sortGenre,
                sortYear, sortValue);
        graphMenu.getItems().add(graphItem);
        
        return menuBar;
    }

    /**
     * this method get the inputs in the TextFields, add them into the album
     * collection list to make a new album object. Then, it calls the
     * addElements() method and send the index where we want to add the inputs
     * in to the list as a parameter for the addElements().
     *
     */
    public void getInput() {
        
        String userSelection = String.valueOf(genreField.getValue());
        if (userSelection.equalsIgnoreCase(defaultCBO)
                | !isValidString(valueField) | !isValidString(titleField)
                | !isValidString(yearField) | !isValidString(artistField)) {
            displayMsg.setText("Please fill out all the required fields.");
            artistField.requestFocus();

        } else if ((!isValidNumber(valueField)) | !isValidNumber(yearField)) {
            displayMsg.setText("Please enter only positive numeric values.");
        } else {
            double value = (Double.valueOf(valueField.getText()) * 100);

            album.addAlbum(artistField.getText(), titleField.getText(),
                    genreField.getValue().toString(),
                    Integer.valueOf(yearField.getText()),
                    new Money((long) (value / 100), (byte) (value % 100)));
            add();
        }
    }

    /**
     * create a event handler for each button made and set the album's elements
     * to the text fields for update or delete.
     *
     * @param button
     * @return
     */
    public int setInput(Button button) {
        int index = Integer.valueOf(button.getId());
        button.setOnAction(e -> {
            try {
                artistField.setText(album.getArtist(index));
                titleField.setText(album.getTitle(index));
                genreField.setValue(album.getGenre(index));
                yearField.setText(String.valueOf(album.getYear(index)));

                //currently returning dollars and cents individually due to 
                //toString method adding $ which caused errors when editing and
                //sending that value back to collection for storage.
                
                //try saving as string here and in update remove $s from string
                //before adding the new money object.
                valueField.setText(album.getPrice(index).getDollars()
                        + "." + album.getPrice(index).getCents());
                
                add.setText("Update");
                addMenu.setText("Update");
                addMenu.setAccelerator(new KeyCodeCombination(KeyCode.U, 
                        KeyCombination.CONTROL_DOWN));
                clear.setDisable(false);
                delete.setDisable(false);
                clearMenu.setDisable(false);
                deleteMenu.setDisable(false);
                GblIndex = index;
                displayMsg.setText("");
                getGenreImage();
            } catch (InvalidIndexException ex) {

            }
        });
        return index;
    }

    /**
     * Create a new button, add elements to the title of the buttons, put it in
     * the Vbox and set an ID for it, which is the index parallel to those of
     * the elements added to the album collection. Then, it calls setInput,
     * sending the button just created as parameter.
     */
    public void add() {

        int index = album.getCount() - 1;
        String display;
        if (GblIndex > -1) {
            add.setText("Update");
            addMenu.setText("Update");
            addMenu.setAccelerator(new KeyCodeCombination(KeyCode.U, 
                    KeyCombination.CONTROL_DOWN));
            update();
        } else {
            try {
                display = album.getArtist(index).trim() + " - "
                        + album.getTitle(index).trim()
                        + " (" + album.getYear(index) + ") ";
            } catch (InvalidIndexException ex) {
                display = "Unable to add Album";
            }
            newBtn = new Button(display);

            //set id for each button made
            newBtn.setId(String.valueOf(index));
            newBtn.setMinWidth(leftPane.getMinWidth() - 2);
            newBtn.setMaxWidth(leftPane.getMaxWidth() - 2);
            //newBtn.setAlignment(Pos.CENTER_LEFT);
            //newBtn.setTextAlignment(TextAlignment.LEFT);
            displayMsg.setText(titleField.getText() + " successfully added.");
            leftPane.setVvalue(leftPane.getVmax());
            album.saveCollection(albumFile);
            box.getChildren().add(newBtn);
            setInput(newBtn);
            clear();
        }

    }

    /**
     * sets all fields to empty/default, enables and disables buttons, and hides
     * any active menus. Called by addButton and refresh which are called
 by everything else.
     *
     */
    public void clear() {

        genreField.setValue("Select an option");
        artistField.clear();
        titleField.clear();
        yearField.clear();
        valueField.clear();
        add.setText("Add");
        addMenu.setText("Add");
        addMenu.setAccelerator(new KeyCodeCombination(KeyCode.A, 
                KeyCombination.CONTROL_DOWN));
        add.setDisable(false);
        clear.setDisable(true);
        delete.setDisable(true);
        clearMenu.setDisable(true);
        deleteMenu.setDisable(true);
        sortMenu.hide();
        fileMenu.hide();
        graphMenu.hide();
        GblIndex = -1;
        getImage();
        
    }

    /**
     *
     */
    //does not currently check for if fields are empty I don't think.
    public void delete() {
        try {
            album.deleteAlbumAt(GblIndex);
            box.getChildren().remove(GblIndex);
            displayMsg.setText(titleField.getText() + " successfully removed.");
            refresh();
        } catch (InvalidIndexException ex) {
            displayMsg.setText("There was an error. Unable to delete album");
        }
    }

    /**
     *
     */
    //does not currently check if fields are empty I don't think.
    public void update() {
        try {
            double value = (Double.valueOf(valueField.getText()) * 100);

            album.updateAlbum(GblIndex, artistField.getText(), titleField.getText(),
                    genreField.getValue().toString(),
                    Integer.valueOf(yearField.getText()),
                    new Money((long) (value / 100), (byte) (value % 100)));
            displayMsg.setText(titleField.getText() + " successfully updated.");
            refresh();
        } catch (InvalidIndexException ex) {
            displayMsg.setText("There was an error. Unable to update album.");
        }

    }

    /**
     * Cycles through the AlbumCollection array and populates buttons with
     * title, artist, and year.
     */
    private void refresh() {

        if (GblIndex == 0) {
            clear();
        } else {

            box.getChildren().clear();

            for (int i = 0; i < album.getCount(); i++) {
                String display;
                try {
                    display = album.getArtist(i) + ": " + album.getTitle(i)
                            + " (" + album.getYear(i) + ")";
                } catch (InvalidIndexException ex) {
                    display = "Unknown Album";
                }
                newBtn = new Button(display);

                //set id for each button made
                newBtn.setId(String.valueOf(i));
                newBtn.setMinWidth(leftPane.getMinWidth() - 2);
                newBtn.setMaxWidth(leftPane.getMaxWidth() - 2);
                //newBtn.setAlignment(Pos.CENTER_LEFT);
                //newBtn.setTextAlignment(TextAlignment.LEFT);
                leftPane.setVvalue(leftPane.getVmin());
                album.saveCollection(albumFile);
                box.getChildren().add(newBtn);
                setInput(newBtn);
                clear();
            }
        }
    }

    /**
     *
     * @param text
     * @return
     */
    public boolean isValidString(TextField text) {
        if (text.getText().trim().isEmpty()) {
            displayMsg.setText("Please ensure "
                    + text + " is properly filled out.");
            text.requestFocus();
            text.clear();
            return false;

        } else {
            return true;
        }
    }

    /**
     *
     * @param value
     * @return
     */
    public boolean isValidNumber(TextField value) {
        try {

            double val = Double.parseDouble((value.getText()));
            if (val < 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            value.requestFocus();
            value.clear();
            return false;
        }
        return true;
    }

    /**
     *
     * @return
     */
    public BarChart getGenreChart() {
        double classicalPrice = 0.0;
        double hiphopPrice = 0.0;
        double latinoPrice = 0.0;
        double metalPrice = 0.0;
        double popPrice = 0.0;
        double rapPrice = 0.0;
        double rockPrice = 0.0;
        double otherPrice = 0.0;

        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        BarChart barChart = new BarChart(xAxis, yAxis);
        XYChart.Series dataSeries = new XYChart.Series();

        xAxis.setLabel("Genre");
        yAxis.setLabel("Value ($)");
        barChart.setTitle("Album Collection Value by Genre");

        dataSeries.setName("Album's value");
        for (int i = 0; i < album.getCount(); i++) {
            try {
                double albumPrice = (100 * album.getPrice(i).getDollars() 
                        + album.getPrice(i).getCents());
                if ((album.getGenre(i)).equals("Classical")) {
                    classicalPrice += albumPrice;
                } else if (album.getGenre(i).equals("Hip-Hop")) {
                    hiphopPrice += albumPrice;
                } else if (album.getGenre(i).equals("Latino")) {
                    latinoPrice += albumPrice;
                } else if (album.getGenre(i).equals("Metal")) {
                    metalPrice += albumPrice;
                } else if (album.getGenre(i).equals("Pop")) {
                    popPrice += albumPrice;
                } else if (album.getGenre(i).equals("Rap")) {
                    rapPrice += albumPrice;
                } else if (album.getGenre(i).equals("Rock")) {
                    rockPrice += albumPrice;
                } else if (album.getGenre(i).equals("Other")) {
                    otherPrice += albumPrice;
                }
            } catch (InvalidIndexException ex) {
            }
        }
        dataSeries.getData().addAll(new XYChart.Data("Classical", classicalPrice / 100),
                new XYChart.Data("Hip-Hop", hiphopPrice / 100),
                new XYChart.Data("Latino", latinoPrice / 100), 
                new XYChart.Data("Metal", metalPrice / 100),
                new XYChart.Data("Pop", popPrice / 100),
                new XYChart.Data("Rap", rapPrice / 100),
                new XYChart.Data("Rock", rockPrice / 100),
                new XYChart.Data("Other", otherPrice / 100));

        barChart.getData().add(dataSeries);
        return barChart;
    }

    /**
     *
     * @return
     */
    public BarChart getArtistChart() {
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        BarChart barChart = new BarChart(xAxis, yAxis);
        XYChart.Series dataSeries = new XYChart.Series();
        xAxis.setLabel("Artist Name");
        yAxis.setLabel("Value ($)");
        barChart.setTitle("Album Collection Value by Artist");
        dataSeries.setName("Album Value");

        for (int i = 0; i < album.getCount(); i++) {

            try {
                double thePrice = ((100 * album.getPrice(i).getDollars()
                        + album.getPrice(i).getCents()) / 100);
                dataSeries.getData().add(new XYChart.Data(album.getArtist(i),
                        thePrice));
            } catch (InvalidIndexException ex) {
            }
        }
        barChart.getData().add(dataSeries);

        return barChart;
    }

    /**
     *
     * @return
     */
    public BarChart getDecadesChart() {
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        BarChart barChart = new BarChart(xAxis, yAxis);
        
        ArrayList<Integer> listBefore60s = new ArrayList<>();
        ArrayList<Integer> list60s70s = new ArrayList<>();
        ArrayList<Integer> list80s90s = new ArrayList<>();
        ArrayList<Integer> listAfter2000s = new ArrayList<>();
        
        xAxis.setLabel("Year");
        yAxis.setLabel("Value ($)");
        barChart.setTitle("Album Collection Value by Year");

        XYChart.Series dataSerie1 = new XYChart.Series();
        dataSerie1.setName("Classical");
        XYChart.Series dataSerie2 = new XYChart.Series();
        dataSerie2.setName("Hip-Hop");
        XYChart.Series dataSerie3 = new XYChart.Series();
        dataSerie3.setName("Latino");
        XYChart.Series dataSerie4 = new XYChart.Series();
        dataSerie4.setName("Metal");
        XYChart.Series dataSerie5 = new XYChart.Series();
        dataSerie5.setName("Pop");
        XYChart.Series dataSerie6 = new XYChart.Series();
        dataSerie6.setName("Rap");
        XYChart.Series dataSerie7 = new XYChart.Series();
        dataSerie7.setName("Rock");
        XYChart.Series dataSerie8 = new XYChart.Series();
        dataSerie8.setName("Other");
        
        
        for (int i = 0; i < album.getCount(); i++) {
            try {
                    if ((album.getYear(i)) < 1960) {
                        listBefore60s.add(i);
                    } else if (1960 <= (album.getYear(i)) && (album.getYear(i)) < 1980) {
                        list60s70s.add(i);
                    } else if (1980 < (album.getYear(i)) && (album.getYear(i)) < 2000) {
                        list80s90s.add(i);
                    } else if (2000 < (album.getYear(i)) && (album.getYear(i)) <= 2020) {
                        listAfter2000s.add(i);
                    }
            } catch (InvalidIndexException ex) {
            }
        }
        
        for (int i = 0; i < listBefore60s.size(); i++) {
            int index = listBefore60s.get(i);
            try {
                double thePrice = ((100 * album.getPrice(index).getDollars() + album.getPrice(index).getCents()) / 100);
                
                switch (album.getGenre(index)) {
                    case "Classical":
                        dataSerie1.getData().add(new XYChart.Data("Before 1960s", thePrice));
                        break;
                    case "Hip-Hop":
                        dataSerie2.getData().add(new XYChart.Data("Before 1960s", thePrice));
                        break;
                    case "Latino":
                        dataSerie3.getData().add(new XYChart.Data("Before 1960s", thePrice));
                        break;
                    case "Metal":
                        dataSerie4.getData().add(new XYChart.Data("Before 1960s", thePrice));
                        break;
                    case "Pop":
                        dataSerie5.getData().add(new XYChart.Data("Before 1960s", thePrice));
                        break;
                    case "Rap":
                        dataSerie6.getData().add(new XYChart.Data("Before 1960s", thePrice));
                        break;
                    case "Rock":
                        dataSerie7.getData().add(new XYChart.Data("Before 1960s", thePrice));
                        break;
                    case "Other":
                        dataSerie8.getData().add(new XYChart.Data("Before 1960s", thePrice));
                        break;
                    default:
                        break;
                }
            } catch (InvalidIndexException ex) {
            }
        }
        
        for (int i = 0; i < list60s70s.size(); i++) {
            int index = list60s70s.get(i);
            try {
                double thePrice = ((100 * album.getPrice(index).getDollars() + album.getPrice(index).getCents()) / 100);
                
                switch (album.getGenre(index)) {
                    case "Classical":
                        dataSerie1.getData().add(new XYChart.Data("1960s - 1970s", thePrice));
                        break;
                    case "Hip-Hop":
                        dataSerie2.getData().add(new XYChart.Data("1960s - 1970s", thePrice));
                        break;
                    case "Latino":
                        dataSerie3.getData().add(new XYChart.Data("1960s - 1970s", thePrice));
                        break;
                    case "Metal":
                        dataSerie4.getData().add(new XYChart.Data("1960s - 1970s", thePrice));
                        break;
                    case "Pop":
                        dataSerie5.getData().add(new XYChart.Data("1960s - 1970s", thePrice));
                        break;
                    case "Rap":
                        dataSerie6.getData().add(new XYChart.Data("1960s - 1970s", thePrice));
                        break;
                    case "Rock":
                        dataSerie7.getData().add(new XYChart.Data("1960s - 1970s", thePrice));
                        break;
                    case "Other":
                        dataSerie8.getData().add(new XYChart.Data("1960s - 1970s", thePrice));
                        break;
                    default:
                        break;
                }
            } catch (InvalidIndexException ex) {
            }
        }
        
        for (int i = 0; i < list80s90s.size(); i++) {
            int index = list80s90s.get(i);
            try {
                double thePrice = ((100 * album.getPrice(index).getDollars() + album.getPrice(index).getCents()) / 100);
                
                switch (album.getGenre(index)) {
                    case "Classical":
                        dataSerie1.getData().add(new XYChart.Data("1980s - 1990s", thePrice));
                        break;
                    case "Hip-Hop":
                        dataSerie2.getData().add(new XYChart.Data("1980s - 1990s", thePrice));
                        break;
                    case "Latino":
                        dataSerie3.getData().add(new XYChart.Data("1980s - 1990s", thePrice));
                        break;
                    case "Metal":
                        dataSerie4.getData().add(new XYChart.Data("1980s - 1990s", thePrice));
                        break;
                    case "Pop":
                        dataSerie5.getData().add(new XYChart.Data("1980s - 1990s", thePrice));
                        break;
                    case "Rap":
                        dataSerie6.getData().add(new XYChart.Data("1980s - 1990s", thePrice));
                        break;
                    case "Rock":
                        dataSerie7.getData().add(new XYChart.Data("1980s - 1990s", thePrice));
                        break;
                    case "Other":
                        dataSerie8.getData().add(new XYChart.Data("1980s - 1990s", thePrice));
                        break;
                    default:
                        break;
                }
            } catch (InvalidIndexException ex) {
            }
        }
        
        for (int i = 0; i < listAfter2000s.size(); i++) {
            int index = listAfter2000s.get(i);
            try {
                double thePrice = ((100 * album.getPrice(index).getDollars() 
                        + album.getPrice(index).getCents()) / 100);
                
                switch (album.getGenre(index)) {
                    case "Classical":
                        dataSerie1.getData().add(new XYChart.Data("After 2000s", thePrice));
                        break;
                    case "Hip-Hop":
                        dataSerie2.getData().add(new XYChart.Data("After 2000s", thePrice));
                        break;
                    case "Latino":
                        dataSerie3.getData().add(new XYChart.Data("After 2000s", thePrice));
                        break;
                    case "Metal":
                        dataSerie4.getData().add(new XYChart.Data("After 2000s", thePrice));
                        break;
                    case "Pop":
                        dataSerie5.getData().add(new XYChart.Data("After 2000s", thePrice));
                        break;
                    case "Rap":
                        dataSerie6.getData().add(new XYChart.Data("After 2000s", thePrice));
                        break;
                    case "Rock":
                        dataSerie7.getData().add(new XYChart.Data("After 2000s", thePrice));
                        break;
                    case "Other":
                        dataSerie8.getData().add(new XYChart.Data("After 2000s", thePrice));
                        break;
                    default:
                        break;
                }
            } catch (InvalidIndexException ex) {
            }
        }
        barChart.getData().addAll(dataSerie1, dataSerie2, dataSerie3, dataSerie4,
                dataSerie5, dataSerie6, dataSerie7, dataSerie8);
        return barChart;
    }

    /**
     *
     */
    public void getGraphStage() {
        //TilePane doesn't resize the graph when resize the stage but mess the
        //position up, BorderPane anchor the button, VBox is ok
        HBox hbox = new HBox();
        hbox.setSpacing(10);
        VBox flowPane = new VBox();
        Scene graphScene = new Scene(flowPane, 200, 100);
        Stage graphStage = new Stage();
        radioGroup = new ToggleGroup();
        decadeRadio = new RadioButton("Decade");
        genreRadio = new RadioButton("Genre");
        artistRadio = new RadioButton("Artist");
        
        Button btnCloseAve = new Button("Close");
        btnCloseAve.setOnAction(e -> graphStage.close());
        btnCloseAve.setDefaultButton(true);
        graphStage.setTitle("Album Collection Value");
        graphStage.centerOnScreen();
        graphStage.setWidth(550.0);
        graphStage.setHeight(550.0);
        graphStage.setX(400);
        graphStage.setY(500);
        genreRadio.setToggleGroup(radioGroup);
        artistRadio.setToggleGroup(radioGroup);
        decadeRadio.setToggleGroup(radioGroup);
        decadeRadio.setSelected(true);
        
        radioGroup.selectedToggleProperty().addListener(
                (ObservableValue<? extends Toggle> ov, Toggle old_toggle,
                        Toggle new_Toggle) -> {
                    if (radioGroup.getSelectedToggle() == genreRadio) {
                        flowPane.getChildren().remove(1);
                        flowPane.getChildren().add(1, getGenreChart());

                    } else if (radioGroup.getSelectedToggle() == artistRadio) {
                        flowPane.getChildren().remove(1);
                        flowPane.getChildren().add(1, getArtistChart());

                    } else if (radioGroup.getSelectedToggle() == decadeRadio) {
                        flowPane.getChildren().remove(1);
                        flowPane.getChildren().add(1, getDecadesChart());

                    }
                });
        hbox.getChildren().addAll(decadeRadio, genreRadio, artistRadio);
        hbox.setAlignment(Pos.CENTER);
        flowPane.setSpacing(5);
        flowPane.getChildren().addAll(hbox, getDecadesChart(), btnCloseAve);
        flowPane.setAlignment(Pos.CENTER);
        graphStage.centerOnScreen();

        graphStage.setScene(graphScene);
        graphStage.show();
    }
}
