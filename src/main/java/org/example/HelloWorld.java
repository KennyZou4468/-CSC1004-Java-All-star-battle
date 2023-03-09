package org.example;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class HelloWorld extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Button n1=new Button("Hello world");
        stage.setHeight(500);
        stage.setWidth(500);
        Pane root=new Pane();
        root.getChildren().add(n1);
        Scene  scene=new Scene(root);
        stage.setScene(scene);
        stage.setTitle("test");
        stage.show();
        n1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("Hello");
                Text a=new Text("Hello");
                root.getChildren().add(a);
            }
        });

    }

    public static void main(String[] args) {
        launch(args);
    }

}
