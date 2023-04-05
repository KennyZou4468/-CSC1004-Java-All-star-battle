package com.kenny.tank.ui;

import com.almasb.fxgl.app.scene.StartupScene;
import javafx.scene.shape.Rectangle;
import org.w3c.dom.Text;

public class StartScene extends StartupScene {
    public StartScene(int appWidth, int appHeight) {
        //启动场景
        super(appWidth, appHeight);
        Rectangle rectangle=new Rectangle(appWidth,appHeight);
        getContentRoot().getChildren().add(rectangle);
    }
}
