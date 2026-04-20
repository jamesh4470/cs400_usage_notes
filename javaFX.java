// download 21.0.10 LTS SDK
// compile with:
// javac --module-path ./javafx/lib --add-modules javajx.controls javaFX.java
// take out the c from "javac" to run

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;

public class JavaFxApp extends Application {
    
    @Override
    public void start(Stage window) {
        System.out.println("app started");

        Label label = new Label("my label");
        Button button = new Button("my button");

        button.setLayoutY(25); // set y pos
		
		Circle circle = new Circle(200, 200, 20); // x, y, radius
		Polygon triangle = new Polygon(100, 150, 180, 90, 35, 80); // 3 points

        Group group = new Group(label, button, circle, triangle);
        Scene scene = new Scene(group, 800, 600); // 800px wide, 600px high

        window.setScene(scene);
        window.setTitle("java fx app");
        window.show();
    }

    public static void main(String[] args) {
        Application.launch();
    }
}
