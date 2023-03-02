package com.kenny.tank;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.kenny.tank.collisions.*;
import com.kenny.tank.components.TankComponent;
import javafx.scene.input.KeyCode;

import java.util.Map;

import static com.almasb.fxgl.dsl.FXGL.*;
//基础设置
public class TankApp extends GameApplication {
    private Entity player2;

    @Override
    protected void initSettings(GameSettings gameSettings) {
        gameSettings.setWidth(32*64);
        gameSettings.setHeight(18*64);
        gameSettings.setTitle("All star battle");
        gameSettings.setVersion("0.1");
        gameSettings.setAppIcon("joker.jpg");
        gameSettings.setMainMenuEnabled(true);

    }
    //预先加载资源
    @Override
    protected void onPreInit() {
     //设置音乐
        FXGL.getSettings().setGlobalMusicVolume(0.5);
        FXGL.getSettings().setGlobalSoundVolume(0.8);


    }
    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put("Score",0);
        vars.put("Name","");
    }

    @Override
    protected void initGame() {
        //创建游戏实体工厂类
        getGameWorld().addEntityFactory(new TankFactory());
        setLevelFromMap("test.tmx");
        player2=FXGL.spawn("Tank",64,64);
        spawn("enemy1",720,720);
        spawn("enemy1",180,960);

    }
    //重写方法获取用户输入.进行移动
    @Override
    protected void initInput() {
        FXGL.onKey(KeyCode.UP, () -> {
            TankComponent player2Component = player2.getComponent(TankComponent.class);
            player2Component.moveUp();

        });
        FXGL.onKey(KeyCode.DOWN, () -> {
            TankComponent player2Component = player2.getComponent(TankComponent.class);
            player2Component.moveDown();

        });
        FXGL.onKey(KeyCode.LEFT, () -> {
            TankComponent player2Component = player2.getComponent(TankComponent.class);
            player2Component.moveLeft();

        });
        FXGL.onKey(KeyCode.RIGHT, () -> {
            TankComponent player2Component = player2.getComponent(TankComponent.class);
            player2Component.moveRight();

        });
        FXGL.onKey(KeyCode.SPACE, () -> {
            TankComponent player2Component = player2.getComponent(TankComponent.class);
            player2Component.shoot();
        });
    }

    @Override
    protected void initPhysics() {
        BulletEnemy handler=new BulletEnemy();
        getPhysicsWorld().addCollisionHandler(handler);
        getPhysicsWorld().addCollisionHandler(new BulletPlayer());
        getPhysicsWorld().addCollisionHandler(new BulletWall());
        getPhysicsWorld().addCollisionHandler(new BulletBullet());
        getPhysicsWorld().addCollisionHandler(new BulletBorder());
        getPhysicsWorld().addCollisionHandler(new BulletUnbreakablewall());
        getPhysicsWorld().addCollisionHandler(new BulletStone());
        getPhysicsWorld().addCollisionHandler(new BulletCaseblock());
    }

    //启动游戏
    public static void main(String[] args) {
        launch(args);
    }
}
