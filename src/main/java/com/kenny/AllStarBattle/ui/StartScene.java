package com.kenny.AllStarBattle.ui;

import com.almasb.fxgl.app.scene.StartupScene;
import javafx.scene.shape.Rectangle;

public class StartScene extends StartupScene {
    public StartScene(int appWidth, int appHeight) {
        //启动场景
        super(appWidth, appHeight);
        Rectangle rectangle=new Rectangle(appWidth,appHeight);
        getContentRoot().getChildren().add(rectangle);
    }
}
