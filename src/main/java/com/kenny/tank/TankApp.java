package com.kenny.tank;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.ExpireCleanComponent;
import com.almasb.fxgl.dsl.components.OffscreenCleanComponent;
import com.almasb.fxgl.dsl.components.ProjectileComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.time.LocalTimer;
import com.kenny.tank.components.TankComponent;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

//基础设置
public class TankApp extends GameApplication {
    private Entity player1;
    private Entity player2;
    private Entity bullet;
    //private  Entity tankEntity;
    //private Entity creeperEntity;
    private  Entity enemy1;
    private boolean ismoving1;
    private boolean ismoving2;

    private Dir dir1;
    enum Dir{
        UP,DOWN,LEFT,RIGHT
    }

    private LocalTimer shoot_time;
    private  Duration shootbreak=Duration.seconds(3.5);
    @Override
    protected void initSettings(GameSettings gameSettings) {
        gameSettings.setWidth(256*8);
        gameSettings.setHeight(256*5);
        gameSettings.setTitle("TANK");
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
    //分数， 名字



    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put("Score",0);
        vars.put("Name","");
    }



    //创建坦克实体用画布
    @Override
    protected void initGame() {
        //创建游戏实体工厂类
        FXGL.getGameWorld().addEntityFactory(new TankFactory());
        enemy1=FXGL.spawn("enemy",100,50);
        player1=FXGL.spawn("creper");
        player2=FXGL.spawn("Tank");
        Point2D center_tank=player2.getCenter();
        //player2.setRotationOrigin(new Point2D(144,144));



        FXGL.getip("Score").addListener((ob,ov,nv)->{
            if(nv.intValue()==10){
                FXGL.getNotificationService().pushNotification("GOOD");
            }
        });


        shoot_time=FXGL.newLocalTimer();
        creatEnemy();
    }



    private void creatEnemy(){
        FXGL.entityBuilder()
                .type(Gametype.ENEMY)
                .at(FXGLMath.random(256,256*2-10),FXGLMath.random(256,256*2-10))
                .viewWithBBox(new Rectangle(60,60,Color.BLUE))
                .collidable()
                .buildAndAttach();

    }



    //重写方法获取用户输入.进行移动

    @Override
    protected void initInput() {
        FXGL.onKey(KeyCode.UP, () -> {
            TankComponent player1Component = player2.getComponent(TankComponent.class);
            player1Component.moveUp();

        });
        FXGL.onKey(KeyCode.DOWN, () -> {
            TankComponent player1Component = player2.getComponent(TankComponent.class);
            player1Component.moveDown();

        });
        FXGL.onKey(KeyCode.LEFT, () -> {
            TankComponent player1Component = player2.getComponent(TankComponent.class);
            player1Component.moveLeft();

        });
        FXGL.onKey(KeyCode.RIGHT, () -> {
            TankComponent player1Component = player2.getComponent(TankComponent.class);
            player1Component.moveRight();

        });
        FXGL.onKey(KeyCode.SPACE, () -> {
            TankComponent player1Component = player2.getComponent(TankComponent.class);
            player1Component.shoot();

        });


       /* FXGL.getInput().addAction(new UserAction("player1 move up") {
            @Override
            protected void onAction() {
                if (ismoving1) {
                    return;
                }
                dir1 = Dir.UP;
                ismoving1 = true;
                player2.translateY(-2);
                player2.setRotation(270);
            }
        }, KeyCode.UP);
        FXGL.getInput().addAction(new UserAction("player1 move down") {
            @Override
            protected void onAction() {
                if (ismoving1) {
                    return;
                }
                dir1 = Dir.DOWN;
                ismoving1 = true;
                player2.translateY(+2);
                player2.setRotation(90);
            }
        }, KeyCode.DOWN);
        FXGL.getInput().addAction(new UserAction("player1 move left") {
            @Override
            protected void onAction() {
                if (ismoving1) {
                    return;
                }
                dir1 = Dir.LEFT;
                ismoving1 = true;
                player2.translateX(-2);
                player2.setRotation(180);
            }
        }, KeyCode.LEFT);
        FXGL.getInput().addAction(new UserAction("player1 move right") {
            @Override
            protected void onAction() {
                if (ismoving1) {
                    return;
                }
                dir1 = Dir.RIGHT;
                ismoving1 = true;
                player2.translateX(+2);
                player2.setRotation(0);
            }
        }, KeyCode.RIGHT);
*/

  /*      FXGL.getInput().addAction(new UserAction("SHOOT") {
            @Override
            protected void onAction() {
                if (!shoot_time.elapsed(shootbreak)) {
                    return;
                }
                FXGL.play("boom.wav");
                shoot_time.capture();
                Point2D u;
                if (dir1 == Dir.UP) {
                    u = new Point2D(0, -1);
                } else if (dir1 == Dir.DOWN) {
                    u = new Point2D(0, 1);
                } else if (dir1 == Dir.LEFT) {
                    u = new Point2D(-1, 0);
                } else {
                    u = new Point2D(1, 0);
                }

                Entity bullet = FXGL.entityBuilder()
                        .type(Gametype.BUllet)
                        .at(player2.getCenter().getX(), player2.getCenter().getY())
                        .viewWithBBox(new Rectangle(20, 20))
                        .with(new OffscreenCleanComponent())
                        .with(new ProjectileComponent(u, 600))
                        .collidable()
                        .buildAndAttach();
            }
        }, KeyCode.SPACE);



*/



    }

    //物理世界



    @Override
    protected void initPhysics() {
        FXGL.getPhysicsWorld().addCollisionHandler(new CollisionHandler
                (Gametype.BUllet,Gametype.ENEMY) {
            @Override
            protected void onCollisionBegin(Entity bullet, Entity enemy) {
               FXGL.inc("Score",1);

                bullet.removeFromWorld();
                Point2D center= enemy.getCenter();
                enemy.removeFromWorld();

                //爆炸效果
                Circle circle=new Circle(10,Color.RED);
                Entity boom= FXGL.entityBuilder()
                        .at(center)
                        .with(new ExpireCleanComponent(Duration.seconds(0.3)))
                        .view(circle)
                .buildAndAttach();

                ScaleTransition s= new ScaleTransition(Duration.seconds(0.3),circle);
                s.setToX(10);
                s.setToY(10);
                FadeTransition t =new FadeTransition(Duration.seconds(0.3),circle);
                t.setToValue(0);
                ParallelTransition p =new ParallelTransition(s,t);
                p.play();
                creatEnemy();
            }
        });
    }

    @Override
    protected void initUI() {
        Text text=FXGL.getUIFactoryService()
                .newText(FXGL.getip("Score").asString("Score:%d"));
        text.setLayoutX(30);
        text.setLayoutY(30);
        text.setFill(Color.BLACK);
        FXGL.addUINode(text);
    }

    //启动游戏
    public static void main(String[] args) {
        launch(args);
    }
}
