package com.kenny.tank.ui;

import com.almasb.fxgl.core.util.LazyValue;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.scene.Scene;
import com.almasb.fxgl.scene.SubScene;
import com.almasb.fxgl.time.LocalTimer;
import com.kenny.tank.AllStarBattleApp;
import com.kenny.tank.Gametype;
import javafx.animation.PauseTransition;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class EggScene extends SubScene {
    private final  PauseTransition pt;
    private Main_menu mainMenu=new Main_menu();
    public EggScene(){
        Text text=new Text("你竟然击杀坤哥最爱的鸡！！等待坤坤的惩罚吧！");
        text.setY(0);
        text.setFill(Color.WHITE);
        text.setFont(Font.font(35));
        StackPane pane=new StackPane(text);
        Text newtext=new Text("但是鉴于你输入了我家坤坤正确的生日，坤坤决定给予你一些超能力在惩罚中");
        if(mainMenu.IsIkun()){
            newtext.setY(120);
            newtext.setFill(Color.YELLOW);
            newtext.setFont(Font.font(35));
            pane=new StackPane(text,newtext);
        }
        pane.setPrefSize(FXGL.getAppWidth(),FXGL.getAppHeight());
        pane.setStyle("-fx-background-color:black");
        getContentRoot().getChildren().add(pane);
        pt=new PauseTransition(Duration.seconds(5));
        pt.setOnFinished(event->{
            FXGL.getSceneService().popSubScene();
            FXGL.<AllStarBattleApp>getAppCast().StartHiddenLevel();

        } );
    }

    @Override
    public void onCreate() {
        pt.play();
        FXGL.play("chicken.mp3");
    }
}
