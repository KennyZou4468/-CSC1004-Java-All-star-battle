package com.kenny.tank.ui;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.scene.SubScene;
import com.almasb.fxgl.time.LocalTimer;
import com.kenny.tank.AllStarBattleApp;
import com.kenny.tank.Config;
import javafx.animation.PauseTransition;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class SuccesschickenScene extends SubScene {
    private LocalTimer localTimer;
    private final  PauseTransition pt;
    private int score=FXGL.geti("score");

    public SuccesschickenScene(){
        //成功在隐藏关卡存活场景
        Text text=new Text("你一共击杀了 "+score+"个鸡和鸡蛋");
        text.setFill(Color.BLACK);
        text.setFont(Font.font(35));
        StackPane pane=new StackPane(text);
        pane.setPrefSize(FXGL.getAppWidth(),FXGL.getAppHeight());
        pane.setStyle("-fx-background-color:gray");
        getContentRoot().getChildren().add(pane);
        pt=new PauseTransition(Duration.seconds(1.5));
        pt.setOnFinished(event->{
                FXGL.getNotificationService().pushNotification("恭喜你击败了坤坤！");
                FXGL.getGameController().gotoMainMenu();
                }
        );
    }

    @Override
    public void onCreate() {
        pt.play();
    }

}
