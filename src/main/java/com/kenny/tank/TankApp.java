package com.kenny.tank;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.UserAction;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import org.jetbrains.annotations.Nullable;

//基础设置
public class TankApp extends GameApplication {
    private  Entity tankEntity;
    private Entity creeperEntity;
    private  Entity peopleEntity;

    private boolean ismoving1;
    private boolean ismoving2;
    @Override
    protected void initSettings(GameSettings gameSettings) {
        gameSettings.setWidth(750);
        gameSettings.setHeight(800);
        gameSettings.setTitle("TANK");
        gameSettings.setVersion("0.1");
        gameSettings.setAppIcon("Crepeer.png");
    }
    //创建坦克实体并添加至游戏中
    @Override
    protected void initGame() {
        Canvas canvas=new Canvas(100,100);
        GraphicsContext g2d= canvas.getGraphicsContext2D();
        g2d.setFill(Color.web("#ffec03"));
        g2d.fillRect(0,0,80,40);

        tankEntity =FXGL.entityBuilder()
                .view(canvas)
                .build();
         tankEntity.setRotationOrigin(new Point2D(50,50));
        FXGL.getGameWorld().addEntity(tankEntity);

        creeperEntity=FXGL.entityBuilder()
                .view("Crepeer.png")
                .build();
        FXGL.getGameWorld().addEntity(creeperEntity);

        peopleEntity=FXGL.entityBuilder()
                .view("new.png")
                .build();
        FXGL.getGameWorld().addEntity(peopleEntity);
    }
    //重写方法获取用户输入.进行移动


    @Override
    protected void initInput() {
      FXGL.getInput().addAction(new UserAction("player1 move up") {
          @Override
          protected void onAction() {
              if(ismoving1){
                  return;
              }
              ismoving1=true;
              tankEntity.translateY(-5);
              tankEntity.setRotation(270);
          }
      }, KeyCode.UP);
        FXGL.getInput().addAction(new UserAction("player1 move down") {
            @Override
            protected void onAction() {
                if(ismoving1){
                    return;
                }
                ismoving1=true;
                tankEntity.translateY(+5);
                tankEntity.setRotation(90);
            }
        }, KeyCode.DOWN); FXGL.getInput().addAction(new UserAction("player1 move left") {
            @Override
            protected void onAction() {
                if(ismoving1){
                    return;
                }
                ismoving1=true;
                tankEntity.translateX(-5);
                tankEntity.setRotation(180);
            }
        }, KeyCode.LEFT); FXGL.getInput().addAction(new UserAction("player1 move right") {
            @Override
            protected void onAction() {
                if(ismoving1){
                    return;
                }
                ismoving1=true;
                tankEntity.translateX(+5);
                tankEntity.setRotation(0);
            }
        }, KeyCode.RIGHT);




        FXGL.getInput().addAction(new UserAction("player2 move up") {
            @Override
            protected void onAction() {
                if(ismoving2){
                    return;
                }
                ismoving2=true;
                creeperEntity.translateY(-5);
                creeperEntity.setRotation(0);
            }
        }, KeyCode.W);
        FXGL.getInput().addAction(new UserAction("player2 move down") {
            @Override
            protected void onAction() {
                if(ismoving2){
                    return;
                }
                ismoving2=true;
                creeperEntity.translateY(+5);
                creeperEntity.setRotation(0);
            }
        }, KeyCode.S); FXGL.getInput().addAction(new UserAction("player2 move left") {
            @Override
            protected void onAction() {
                if(ismoving2){
                    return;
                }
                ismoving2=true;
                creeperEntity.translateX(-5);
            }
        }, KeyCode.A); FXGL.getInput().addAction(new UserAction("player2 move right") {
            @Override
            protected void onAction() {
                if(ismoving2){
                    return;
                }
                ismoving2=true;
                creeperEntity.translateX(+5);
                creeperEntity.setRotation(0);
            }
        }, KeyCode.D);
    }

    //重写update方法


    @Override
    protected void onUpdate(double tpf) {
        ismoving1=false;
        ismoving2=false;
    }

    //启动游戏
    public static void main(String[] args) {
        launch(args);
    }
}
