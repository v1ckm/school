/**
 * Objective: Create a tree
 * Algorithm: Use a recursive function to render a tree
 * Input and Output: Specify recursion size with input and display tree with output
 * Created by: Matthew Vick
 * Date: 04/30/2022
 * Version: 1
 */

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {

        // Create input in order to allow user to specify order
        HBox hBox = new HBox(10);
        TextField tfOrder = new TextField();
        tfOrder.setPrefColumnCount(4);
        tfOrder.setAlignment(Pos.BOTTOM_RIGHT);
        hBox.getChildren().addAll(new Label("Enter an order: "), tfOrder);
        hBox.setAlignment(Pos.CENTER);

        // Create TreePane to draw tree
        TreePane pane = new TreePane();
        tfOrder.setOnAction(e -> {
            pane.setDepth(Integer.parseInt(tfOrder.getText()));
        });

        // Allow hBox to be placed at bottom of border and TreePane at center
        BorderPane borderPane = new BorderPane();
        borderPane.setBottom(hBox);
        borderPane.setCenter(pane);

        Scene scene = new Scene(borderPane, 200, 210);
        primaryStage.setTitle("Render Tree");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // TreePane class which will draw the tree
    static class TreePane extends Pane {
        private int depth = 0;
        private double angleFactor = Math.PI / 5;
        private double sizeFactor = 0.58;

        public void setDepth(int depth) {
            this.depth = depth;
            paint();
        }

        public void paint() {
            // Remove lines
            getChildren().clear();
            paintBranch(depth, getWidth() / 2, getHeight() - 10, getHeight() / 3, Math.PI / 2);
        }

        public void paintBranch(int depth, double x1, double y1, double length, double angle) {
            if (depth >= 0) {
                // Calculate endpoints
                double x2 = x1 + Math.cos(angle) * length;
                double y2 = y1 - Math.sin(angle) * length;
                getChildren().add(new Line(x1, y1, x2, y2));

                // Create left branch
                paintBranch(depth - 1, x2, y2, length * sizeFactor, angle + angleFactor);
                // Create right branch
                paintBranch(depth - 1, x2, y2, length * sizeFactor, angle - angleFactor);
            }
        }


    }

    public static void main(String[] args) {
        launch(args);
    }
}
