package com.kenny.tank.ui;

import com.almasb.fxgl.app.scene.LoadingScene;
import com.almasb.fxgl.dsl.FXGL;
import javafx.scene.shape.Rectangle;

public class Loading_Scene extends LoadingScene {
    public Loading_Scene(){
        getContentRoot().getChildren().add(new Rectangle(FXGL.getAppWidth(),FXGL.getAppHeight()));
    }

}
