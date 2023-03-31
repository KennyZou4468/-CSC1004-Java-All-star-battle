package com.kenny.tank;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.LoadingScene;
import com.almasb.fxgl.app.scene.SceneFactory;
import com.almasb.fxgl.app.scene.StartupScene;
import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.core.util.LazyValue;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.kenny.tank.collisions.*;
import com.kenny.tank.collisions.PEOPLE.*;
import com.kenny.tank.collisions.PLAYER.*;
import com.kenny.tank.components.NewPeopleComponent;
import com.kenny.tank.components.TankComponent;
import com.kenny.tank.ui.*;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;

import java.util.List;
import java.util.Map;
import static com.almasb.fxgl.dsl.FXGL.*;
//基础设置
public class AllStarBattleApp extends GameApplication {
    private Entity player2;
    private Entity Boss1;
    public LazyValue<SuccessScene> successSceneLazyValue=new LazyValue<>(SuccessScene::new);

    @Override
    protected void initSettings(GameSettings gameSettings) {
        gameSettings.setMainMenuEnabled(true);
        gameSettings.setWidth(32*64);
        gameSettings.setHeight(18*64);
        gameSettings.setTitle("All star battle");
        gameSettings.setVersion("0.1");
        gameSettings.setAppIcon("joker.jpg");
        gameSettings.getCSSList().add("tank.css");
        gameSettings.setMainMenuEnabled(true);

        gameSettings.setSceneFactory(new SceneFactory(){
            @Override
            public StartupScene newStartup(int width, int height) {
                return new StartScene(width,height);
            }

            @Override
            public LoadingScene newLoadingScene() {
                return new Loading_Scene();
            }

            @Override
            public FXGLMenu newMainMenu() {
                return new Main_menu();
            }
        });

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
        vars.put("score",0);
        vars.put("Name","");
        vars.put("level",1);
    }
    public void StartLevel(){
        set("score",0);
        setLevelFromMap("test"+geti("level")+".tmx");
        player2=FXGL.spawn("Tank",1900,1012);
        //player2.getComponent(LevelComponent.class).Strongest();
        spawn("enemy1",1604,825);
        spawn("enemy1",540,408);
        spawn("enemy1",1021,1050);
        spawn("enemy1",925,96);
        spawn("Chicken",1785,117);
        Boss1 = spawn("Boss1", 40, 950);
        run(()->{
            Point2D b=FXGLMath.random(Config.Spawnenemy).get();
            List<Entity> en= getGameWorld().getEntitiesInRange(new Rectangle2D(b.getX(),b.getY(),88,88));
            List<Entity> entities=en.stream().filter(entity ->
                    entity.isType(Gametype.PLAYER2)||
                            entity.isType(Gametype.ENEMY)
                    ||entity.isType(Gametype.UNBREAKABLEWALL)
            ).toList();
            if(entities.isEmpty()){
                spawn("enemy1",b);
            }
        }, Duration.seconds(5),70);

    }
    public void StartHiddenLevel(){
        set("score",0);
        setLevelFromMap("Chicken.tmx");
        spawn("ChickenEnemy",125,381);
        player2=FXGL.spawn("people",1900,1012);
        run(()->{
            Point2D b=FXGLMath.random(Config.Spawnchicken).get();
            List<Entity> en= getGameWorld().getEntitiesInRange(new Rectangle2D(b.getX(),b.getY(),88,88));
            List<Entity> entities=en.stream().filter(entity ->
                    entity.isType(Gametype.PLAYER2)||
                            entity.isType(Gametype.CHICKENENEMY)
            ).toList();
            if(entities.isEmpty()){
                spawn("ChickenEnemy",b);
            }
        }, Duration.seconds(4),70);
        runOnce(()->{
            LazyValue<SuccesschickenScene> successchickenSceneLazyValue = new LazyValue<>(SuccesschickenScene::new);
            if (player2.isActive()) {
                getSceneService().pushSubScene(successchickenSceneLazyValue.get());
            }
            else {
                return;
            }
        },Duration.seconds(143));
    }

    @Override
    protected void initGame() {
        //创建游戏实体工厂类
        getGameWorld().addEntityFactory(new AllStarBattleFactory());
        getip("score").addListener((ob, ov, nv)->{
            if(nv.intValue()>=40){
                FXGL.runOnce(()->{
                    getSceneService().pushSubScene(successSceneLazyValue.get());
                },Duration.seconds(0.5));
            }
        });
        StartLevel();
    }
    //重写方法获取用户输入.进行移动
    @Override
    protected void initInput() {
            FXGL.onKey(KeyCode.UP, () -> {
                if(player2.isType(Gametype.PLAYER2)) {
                    TankComponent player2Component = player2.getComponent(TankComponent.class);
                    player2Component.moveUp();
                } else if (player2.isType(Gametype.PEOPLE)) {
                    NewPeopleComponent peopleComponent=player2.getComponent(NewPeopleComponent.class);
                    peopleComponent.moveUp();
                }
            });
            FXGL.onKey(KeyCode.DOWN, () -> {
                if(player2.isType(Gametype.PLAYER2)) {
                    TankComponent player2Component = player2.getComponent(TankComponent.class);
                    player2Component.moveDown();
                }else if (player2.isType(Gametype.PEOPLE)) {
                    NewPeopleComponent peopleComponent=player2.getComponent(NewPeopleComponent.class);
                    peopleComponent.moveDown();
                }
            });
            FXGL.onKey(KeyCode.LEFT, () -> {
                if(player2.isType(Gametype.PLAYER2)) {
                    TankComponent player2Component = player2.getComponent(TankComponent.class);
                    player2Component.moveLeft();
                    }else if (player2.isType(Gametype.PEOPLE)) {
                    NewPeopleComponent peopleComponent=player2.getComponent(NewPeopleComponent.class);
                    peopleComponent.moveLeft();
                }
            });
            FXGL.onKey(KeyCode.RIGHT, () -> {
                if(player2.isType(Gametype.PLAYER2)) {
                    TankComponent player2Component = player2.getComponent(TankComponent.class);
                    player2Component.moveRight();
                }else if (player2.isType(Gametype.PEOPLE)) {
                    NewPeopleComponent peopleComponent=player2.getComponent(NewPeopleComponent.class);
                    peopleComponent.moveRight();
                }
            });
            FXGL.onKey(KeyCode.SPACE, () -> {
                        if (player2.isType(Gametype.PLAYER2)) {
                            TankComponent player2Component = player2.getComponent(TankComponent.class);
                            player2Component.shoot();
                        } else if (player2.isType(Gametype.PEOPLE)) {
                            NewPeopleComponent peopleComponent = player2.getComponent(NewPeopleComponent.class);
                            peopleComponent.shoot();
                        }
                    }
            );
    }

    @Override
    protected void initPhysics() {
        BulletEnemy handler=new BulletEnemy();
        getPhysicsWorld().addCollisionHandler(handler);
        getPhysicsWorld().addCollisionHandler(new BulletPlayer());
        getPhysicsWorld().addCollisionHandler(new BulletBullet());
        getPhysicsWorld().addCollisionHandler(new BulletBorder());
        getPhysicsWorld().addCollisionHandler(new BulletWall());
        getPhysicsWorld().addCollisionHandler(new BulletUnbreakablewall());
        getPhysicsWorld().addCollisionHandler(new BulletStone());
        getPhysicsWorld().addCollisionHandler(new BulletCaseblock());
        getPhysicsWorld().addCollisionHandler(new PropsPlayer());
        getPhysicsWorld().addCollisionHandler(new BulletBoss());
        getPhysicsWorld().addCollisionHandler(new PropsEnemy());
        getPhysicsWorld().addCollisionHandler(new BulletChicken());
        getPhysicsWorld().addCollisionHandler(new BulletChickenEnemy());
        getPhysicsWorld().addCollisionHandler(new BulletEgg());
        getPhysicsWorld().addCollisionHandler(new BulletEnemyEye());
        getPhysicsWorld().addCollisionHandler(new PlayerSharpEye());
        getPhysicsWorld().addCollisionHandler(new BulletEnemySharpEye());
        getPhysicsWorld().addCollisionHandler(new BulletPeople());
        getPhysicsWorld().addCollisionHandler(new PropsEnemy_forPeople());
        getPhysicsWorld().addCollisionHandler(new PropsPlayer_forPeople());
    }

    //启动游戏
    public static void main(String[] args) {
        launch(args);
    }
}
