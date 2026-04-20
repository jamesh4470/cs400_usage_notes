// download 21.0.10 LTS SDK
// compile with:
// javac --module-path ./javafx/lib --add-modules javajx.controls javaFX.java
// take out the c from "javac" to run

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.geometry.Pos; 
import javafx.geometry.Insets;
import javafx.scene.input.KeyEvent;
import javafx.event.ActionEvent;
import javafx.application.Platform;
import javafx.scene.input.MouseEvent;

public class JavaFxApp extends Application {
    
    @Override
    public void start(Stage window) {
        System.out.println("app started");

        Label label = new Label("Search Text: ");
        // upon clicked, it will propagate up and print label clicked, borderpane clicked, scene clicked. 
        label.addEventHandler(MouseEvent.MOUSE_CLICKED,
                             (event) -> System.out.println("Label clicked"));
        TextField searchText = new TextField();
        searchText.addEventHandler(KeyEvent.KEY_TYPED, 
                                   (event) -> System.out.println("Key typed: " + event.getCharacter()));
        Button searchButton = new Button("Start Search");
        searchButton.addEventHandler(ActionEvent.ACTION, 
                                    (event) -> System.out.println("Search started: " + searchText.getText()));
        Button closeButton = new Button("Close");
        closeButton.addEventHandler(ActionEvent.ACTION,
                                   (event) -> Platform.exit());

        // button.setLayoutY(25); // set y pos

        // Group group = new Group(label, button);
        BorderPane bp = new BorderPane();
        bp.setCenter(searchText);
        bp.setLeft(label);
        bp.setAlignment(label, Pos.CENTER); // aligns label at center
        bp.setMargin(label, new Insets(5, 5, 5, 5)); // top right bottom left
        bp.setMargin(searchText, new Insets(5, 5, 5, 0); 
        // with event.consume(), no more propagating up to scene. 
        bp.addEventHandler(MouseEvent.MOUSE_CLICKED,
                          (event) -> {System.out.println("BorderPane clicked"); event.consume();});
        
        HBox buttons = new HBox(60); // 60 pixels between elements
        buttons.getChildren().add(searchButton);
        buttons.getChildren().add(closeButton);
        bp.setBottom(buttons);
        buttons.setAlignment(Pos.CENTER); // align buttons to center
        bp.setMargin(buttons, new Insets(5, 5, 5, 5));
            
        // Scene scene = new Scene(group, 800, 600); // 800px wide, 600px high
        Scene scene = new Scene(bp);
        scene.addEventHandler(MouseEvent.MOUSE_CLICKED,
                          (event) -> System.out.println("Scene clicked"));


        window.setScene(scene);
        window.setTitle("java fx app");
        window.show();
    }

    public static void main(String[] args) {
        Application.launch();
    }
}
