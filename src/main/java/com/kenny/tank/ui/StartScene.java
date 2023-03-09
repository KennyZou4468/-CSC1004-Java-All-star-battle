package com.kenny.tank.ui;

import com.almasb.fxgl.app.scene.StartupScene;
import javafx.scene.shape.Rectangle;

public class StartScene extends StartupScene {

    public StartScene(int appWidth, int appHeight) {
        super(appWidth, appHeight);
        Rectangle rectangle=new Rectangle(appWidth,appHeight);
        getContentRoot().getChildren().add(rectangle);
    }
}
