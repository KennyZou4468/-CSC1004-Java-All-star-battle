package com.kenny.AllStarBattle.ui;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.scene.SubScene;
import com.almasb.fxgl.texture.Texture;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;

public class FailedScene extends SubScene {
       private  TranslateTransition a;
       private Texture texture;
    public FailedScene() {
        //弹出死亡界面
       texture= FXGL.texture("Suicide.png");
       texture.setLayoutX(32*64/2-texture.getWidth()/2);
       texture.setLayoutY(FXGL.getAppHeight());
       //texture.setLayoutY(18*64/2-texture.getHeight()/2);
         a=new TranslateTransition(Duration.seconds(2.5),texture);
        a.setFromY(0);
        a.setToY(-FXGL.getAppHeight());
        a.setOnFinished(event->{
            //回到主菜单
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
