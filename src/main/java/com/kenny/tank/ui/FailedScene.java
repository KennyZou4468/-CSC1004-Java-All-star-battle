package com.kenny.tank.ui;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.scene.SubScene;
import com.almasb.fxgl.texture.Texture;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.scene.Parent;
import javafx.util.Duration;

public class FailedScene extends SubScene {
       private  TranslateTransition a;
       private Texture texture;
    public FailedScene() {
       texture= FXGL.texture("Suicide.png");
       texture.setLayoutX(32*64/2-texture.getWidth()/2);
       texture.setLayoutY(FXGL.getAppHeight());
       //texture.setLayoutY(18*64/2-texture.getHeight()/2);
         a=new TranslateTransition(Duration.seconds(2.5),texture);
        a.setFromY(0);
        a.setToY(-FXGL.getAppHeight());
        a.setOnFinished(event->{
            FXGL.getSceneService().popSubScene();
            texture.setTranslateY(0);
            FXGL.getGameController().gotoMainMenu();
        });
       getContentRoot().getChildren().add(texture);
    }

    @Override
    public void onCreate() {
        a.play();
    }
}
