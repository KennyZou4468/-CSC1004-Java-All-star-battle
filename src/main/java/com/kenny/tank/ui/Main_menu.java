package com.kenny.tank.ui;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.texture.Texture;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import org.jetbrains.annotations.NotNull;

public class Main_menu extends FXGLMenu {
    public Main_menu() {
        super(MenuType.MAIN_MENU);
       Texture texture= FXGL.texture("MainMenu.png");
        Button button1=new Button("Start Game");
        button1.getStyleClass().add("menu_button1");
        button1.setOnAction(event->{
            FXGL.getGameController().startNewGame();
        });
        Button button2=new Button("Exit");
        button2.getStyleClass().add("menu_button2");
        button2.setOnAction(event->{
            FXGL.getGameController().exit();
        });
        VBox vBox=new VBox(100,button1,button2);
        vBox.setLayoutX(1024);
        vBox.setLayoutY(500);

        getContentRoot().getChildren().addAll(texture,vBox);
    }
}
