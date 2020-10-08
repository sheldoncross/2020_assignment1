package sample;

import java.util.Random;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;


public class Main extends Application {

    //Generate three random points on the circle
    Random rand = new Random();
    int random1 = rand.nextInt(360) + 1;
    int random2 = rand.nextInt(360) + 1;
    int random3 = rand.nextInt(360) + 1;

    //Create three circles for points
    Circle point1 = new Circle();
    Circle point2 = new Circle();
    Circle point3 = new Circle();

    //Create three lines to connect each point
    Line line1 = new Line();
    Line line2 = new Line();
    Line line3 = new Line();

    //Create the text for angles displayed
    Text textA = new Text();
    Text textB = new Text();
    Text textC = new Text();

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Root layout
        Group root = new Group();
        root.minWidth(650);
        root.minHeight(480);

        //Create the main circle
        Circle circle = new Circle();
        circle.setRadius(150);
        circle.setCenterX(320);
        circle.setCenterY(240);
        circle.setStroke(Color.BLACK);
        circle.setFill(Color.WHITE);

        //Create the three random points with random x & y values constrained to the main circle
        point1.setRadius(5);
        point1.setFill(Color.RED);
        point1.setCenterX(circle.getCenterX() + circle.getRadius() * Math.cos(random1 * Math.PI / 180F));
        point1.setCenterY(circle.getCenterY() + circle.getRadius() * Math.sin(random1 * Math.PI / 180F));

        point2.setRadius(5);
        point2.setFill(Color.RED);
        point2.setCenterX(circle.getCenterX() + circle.getRadius() * Math.cos(random2 * Math.PI / 180F));
        point2.setCenterY(circle.getCenterY() + circle.getRadius() * Math.sin(random2 * Math.PI / 180F));

        point3.setRadius(5);
        point3.setFill(Color.RED);
        point3.setCenterX(circle.getCenterX() + circle.getRadius() * Math.cos(random3 * Math.PI / 180F));
        point3.setCenterY(circle.getCenterY() + circle.getRadius() * Math.sin(random3 * Math.PI / 180F));

        root.getChildren().addAll(circle, point1, point2, point3, line1, line2, line3, textA, textB, textC);

        //Draw the connecting lines and calculate the original angles
        draw(root, circle);

        //Set the action on mouse drag to update the points position
        point1.setOnMouseDragged(e -> {
            point1.setCenterX(e.getX());
            point1.setCenterY(e.getY());
            //Update the lines drawn along with the angles
            draw(root, circle);
        });

        //Set the action on mouse drag to update the points position
        point2.setOnMouseDragged(e -> {
            point2.setCenterX(e.getX());
            point2.setCenterY(e.getY());
            //Update the lines drawn along with the angles
            draw(root, circle);
        });

        //Set the action on mouse drag to update the points position
        point3.setOnMouseDragged(e -> {
            point3.setCenterX(e.getX());
            point3.setCenterY(e.getY());
            //Update the lines drawn along with the angles
            draw(root, circle);
        });

        Scene scene = new Scene(root);
        primaryStage.setTitle("Question 3");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void draw(Group pane, Circle circle) {
        //Set the starting and ending points of each line based on the existing points
        line1.setStartY(point1.getCenterY());
        line1.setStartX(point1.getCenterX());
        line1.setEndY(point2.getCenterY());
        line1.setEndX(point2.getCenterX());

        line2.setStartX(point2.getCenterX());
        line2.setStartY(point2.getCenterY());
        line2.setEndX(point3.getCenterX());
        line2.setEndY(point3.getCenterY());

        line3.setStartX(point3.getCenterX());
        line3.setStartY(point3.getCenterY());
        line3.setEndX(point1.getCenterX());
        line3.setEndY(point1.getCenterY());

        //Get the length of each line
        double a = new Point2D(point2.getCenterX(), point2.getCenterY()).distance(point3.getCenterX(),
                point3.getCenterY());
        double b = new Point2D(point1.getCenterX(), point1.getCenterY()).distance(point3.getCenterX(),
                point3.getCenterY());
        double c = new Point2D(point1.getCenterX(), point1.getCenterY()).distance(point2.getCenterX(),
                point2.getCenterY());

        //Calculate the angle based on the lengths
        double angleA = Math.acos((a * a - b * b - c * c) / (-2 * b * c));
        double angleB = Math.acos((b * b - a * a - c * c) / (-2 * a * c));
        double angleC = Math.acos((c * c - b * b - a * a) / (-2 * a * b));

        //Update the degrees based on the calculations and update the text position depending on position
        textA.setText(String.format("%.1f", Math.toDegrees(angleA)));
        if(point1.getCenterY() > 240){
            textA.setY(point1.getCenterY() + 25);
        }else {
            textA.setY(point1.getCenterY() - 25);
        }
        if (point1.getCenterX() > 320) {
            textA.setX(point1.getCenterX() + 25);
        } else {
            textA.setX(point1.getCenterX() - 25);
        }
        textB.setText(String.format("%.1f", Math.toDegrees(angleB)));
        if(point2.getCenterY() > 240){
            textB.setY(point2.getCenterY() + 25);
        }else {
            textB.setY(point2.getCenterY() - 25);
        }
        if (point2.getCenterX() > 320) {
            textB.setX(point2.getCenterX() + 25);
        } else {
            textB.setX(point2.getCenterX() - 25);
        }
        textC.setText(String.format("%.1f", Math.toDegrees(angleC)));
        if(point3.getCenterY() > 240){
            textC.setY(point3.getCenterY() + 25);
        }else {
            textC.setY(point3.getCenterY() - 25);
        }
        if (point3.getCenterX() > 320) {
            textC.setX(point3.getCenterX() + 25);
        } else {
            textC.setX(point3.getCenterX() - 25);
        }
    }

    public static void main(String[] args) {launch(args);}
}
