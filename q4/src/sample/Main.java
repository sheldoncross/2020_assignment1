package sample;

import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.regex.Pattern;


public class Main extends Application {
    //Pane for graph
    Pane pane = new Pane();

    //Create bar chart
    CategoryAxis xAxis = new CategoryAxis();
    NumberAxis yAxis = new NumberAxis();
    BarChart<String, Number> graph = new BarChart<>(xAxis, yAxis);

    //File name text field & label
    TextField textField = new TextField();
    Label filenameLabel = new Label("Filename:", textField);

    //Reader for file
    BufferedReader reader;

    //Store letter values and number of occurrences
    HashMap<String, Integer> letterValues = new HashMap<>();

    //View button
    Button viewButton = new Button("View");

    //Box for label, text field and button
    HBox viewBox = new HBox(filenameLabel, viewButton);

    @Override
    public void start(Stage primaryStage) throws Exception {
        //Root layout
        VBox root = new VBox();

        //Styling
        graph.setCategoryGap(1);
        graph.setBarGap(1);
        filenameLabel.setContentDisplay(ContentDisplay.RIGHT);

        //On click action for view button
        viewButton.setOnAction(e-> {
            try{
                //Update the graph based on the file name
                update();
            }catch (java.io.FileNotFoundException f){}
        });

        root.getChildren().addAll(pane, viewBox);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void update() throws FileNotFoundException {
        //Data for graph
        XYChart.Series series = new XYChart.Series();

        //Read file based on text field
        File file = new File(textField.getText());
        FileReader fileReader = new FileReader(file);
        reader =  new BufferedReader(fileReader);


        //Read all characters and store all characters that belong to the file until no characters left
        try {
            while (reader.ready()){
                char next = (char)reader.read();
                if(!letterValues.containsKey(Character.toString(next).toUpperCase()) && Pattern.matches("[a-zA-z]", String.valueOf(next))){
                    letterValues.put(Character.toString(next).toUpperCase(), 1);
                }else if(Pattern.matches("[a-zA-z]", String.valueOf(next))){
                    letterValues.put(Character.toString(next).toUpperCase(), letterValues.get(Character.toString(next).toUpperCase()) + 1);
                }
            }
        }catch (java.io.IOException e){}

        for(String  item: letterValues.keySet()){
            series.getData().add(new XYChart.Data(item, letterValues.get(item)));
        }
        pane.getChildren().removeAll(graph);
        pane.getChildren().add(graph);
        graph.getData().addAll(series);


    }

    public static void main(String[] args){launch(args);}
}