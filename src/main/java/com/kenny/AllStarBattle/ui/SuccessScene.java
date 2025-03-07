package com.kenny.AllStarBattle.ui;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.scene.SubScene;
import com.almasb.fxgl.time.LocalTimer;
import com.kenny.AllStarBattle.Config;
import com.kenny.AllStarBattle.AllStarBattleApp;
import javafx.animation.PauseTransition;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class SuccessScene extends SubScene {
    //普通场景获胜的ui
    private LocalTimer localTimer;
    private final  PauseTransition pt;
    private int score=FXGL.geti("score");

    public SuccessScene(){
        Text text=new Text("Score: "+score);
        text.setFill(Color.BLACK);
        text.setFont(Font.font(35));
        StackPane pane=new StackPane(text);
        pane.setPrefSize(FXGL.getAppWidth(),FXGL.getAppHeight());
        pane.setStyle("-fx-background-color:gray");
        getContentRoot().getChildren().add(pane);
        pt=new PauseTransition(Duration.seconds(1.5));
        pt.setOnFinished(event->{
            //检查是否到下一关
            if(FXGL.geti("level")< Config.Max_Level){
                FXGL.getSceneService().popSubScene();
                FXGL.inc("level",1);
                FXGL.<AllStarBattleApp>getAppCast().StartLevel();
            }else{
                FXGL.getNotificationService().pushNotification("YOU SUCCEED!");
                FXGL.getGameController().gotoMainMenu();
            }
        } );
    }

    @Override
    public void onCreate() {
        pt.play();
    }

}
