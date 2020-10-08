package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.util.Random;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Question 1");
        GridPane layout = new GridPane();
        Image[] images = new Image[58];

        for(int i = 1; i <= 54; i++){
            images[i] = new Image("file:images/" + i + ".png");
        }
        images[54] = new Image("file:images/b1fh.png");
        images[55] = new Image("file:images/b2fh.png");
        images[56] = new Image("file:images/b1fv.png");
        images[57] = new Image("file:images/backCard,png");

        Random rand = new Random();

        layout.add(new ImageView(images[rand.nextInt(55) + 1]), 0, 0);
        layout.add(new ImageView(images[rand.nextInt(55) + 1]), 1, 0);
        layout.add(new ImageView(images[rand.nextInt(55) + 1]), 2, 0);

        Scene scene = new Scene(layout, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
