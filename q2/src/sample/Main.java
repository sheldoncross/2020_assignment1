package sample;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import javax.swing.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Question 2");
        //Root layout
        GridPane layout = new GridPane();

        //Text fields for all inputs & calculate button
        TextField investmentAmount = new TextField();
        TextField years = new TextField();
        TextField annualInterest = new TextField();
        TextField futureValue = new TextField();
        Button calculateButton = new Button("Calculate");

        //Set the position of all items
        layout.add(new Label("Investment Amount"), 0, 0);
        layout.add(investmentAmount, 1, 0);
        layout.add(new Label("Years"), 0, 1);
        layout.add(years, 1, 1);
        layout.add(new Label("Annual Interest Rate"), 0, 2);
        layout.add(annualInterest, 1, 2);
        layout.add(new Label("Future Value"), 0, 3);
        layout.add(futureValue, 1, 3);
        layout.add(calculateButton, 1, 4);

        //Set on click action to calculate values for future value and display it
        calculateButton.setOnMouseClicked((event) -> {
            double investmentAmountInt = Double.parseDouble(investmentAmount.getText());
            double monthlyInterestRate = Double.parseDouble(annualInterest.getText())/12;
            double futureValueInt = investmentAmountInt * Math.pow((1 + monthlyInterestRate),
                                            (Double.parseDouble(years.getText())*12));
            long answer = Math.round(futureValueInt);

            futureValue.setText(Long.toString(answer));
        });

        Scene scene = new Scene(layout, 800, 800);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
