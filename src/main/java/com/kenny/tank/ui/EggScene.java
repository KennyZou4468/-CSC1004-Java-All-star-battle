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
        Text text=new Text("你这个小黑子竟然击杀坤哥最爱的鸡！！等待坤坤的惩罚吧！");
        text.setY(0);
        text.setFill(Color.RED);
        text.setFont(Font.font(35));
        StackPane pane=new StackPane(text);
        Text newtext=new Text("虽然你杀死了坤坤最爱的鸡，但是鉴于你输入了我家坤坤正确的生日，坤坤决定在惩罚中给予你一些超能力");
        if(mainMenu.IsIkun()){
            //如果输入的账号密码能证明你是ikun，则弹出不同的subscene
            newtext.setY(120);
            newtext.setFill(Color.YELLOW);
            newtext.setFont(Font.font(35));
            pane=new StackPane(newtext);
        }
        pane.setPrefSize(FXGL.getAppWidth(),FXGL.getAppHeight());
        pane.setStyle("-fx-background-color:black");
        getContentRoot().getChildren().add(pane);
        pt=new PauseTransition(Duration.seconds(5));
        pt.setOnFinished(event->{
            //启动隐藏关卡
            FXGL.getSceneService().popSubScene();
            FXGL.<AllStarBattleApp>getAppCast().StartHiddenLevel();

        } );
    }

    @Override
    public void onCreate() {
        pt.play();
        //播放坤giegie的《只因你太美》
        FXGL.play("chicken.mp3");
    }
}
