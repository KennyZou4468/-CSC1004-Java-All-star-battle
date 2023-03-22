package com.kenny.tank.ui;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.scene.SubScene;
import com.kenny.tank.Config;
import com.kenny.tank.TankApp;
import javafx.animation.PauseTransition;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class EggScene extends SubScene {
    private final  PauseTransition pt;
    private int score=FXGL.geti("score");
    public EggScene(){
        Text text=new Text("你击杀了鸡！！等待坤坤的惩罚吧！");
        text.setFill(Color.WHITE);
        text.setFont(Font.font(35));
        StackPane pane=new StackPane(text);
        pane.setPrefSize(FXGL.getAppWidth(),FXGL.getAppHeight());
        pane.setStyle("-fx-background-color:black");
        getContentRoot().getChildren().add(pane);
        pt=new PauseTransition(Duration.seconds(2));
        pt.setOnFinished(event->{
            FXGL.getSceneService().popSubScene();
            FXGL.<TankApp>getAppCast().StartHiddenLevel();
        } );
    }

    @Override
    public void onCreate() {
        pt.play();
    }

}
