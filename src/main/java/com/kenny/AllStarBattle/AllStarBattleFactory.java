package com.kenny.AllStarBattle;

import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.EffectComponent;
import com.almasb.fxgl.dsl.components.ExpireCleanComponent;
import com.almasb.fxgl.dsl.components.HealthIntComponent;
import com.almasb.fxgl.dsl.components.ProjectileComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import com.almasb.fxgl.ui.ProgressBar;
import com.kenny.AllStarBattle.components.*;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;


//此类用于存储所有可能出现的Entity，不一一阐述。
public class AllStarBattleFactory implements EntityFactory {
    // 坦克敌人
    @Spawns("enemy1")
         public Entity enemy1(SpawnData data){
        return FXGL.entityBuilder(data)
                //设置外观和大小
                .type(Gametype.ENEMY)
                .with(new EffectComponent())
                .with(new TankComponent())
                .with(new EnemyComponent())
                .viewWithBBox("Enemytank.png")
                .with(new CollidableComponent(true))
                .with(new LevelComponent())
                .build();
    }
    //克苏鲁敌人
    @Spawns("enemy02")
    public Entity enemy2(SpawnData data){
        return FXGL.entityBuilder(data)
                //设置外观和大小
                .type(Gametype.ENEMYEYE)
                .with(new EffectComponent())
                .with(new TankComponent())
                .with(new EnemyComponent())
                .viewWithBBox("element/Enemy2.png")
                .with(new CollidableComponent(true))
                .with(new LevelComponent())
                .build();
    }
    @Spawns("enemy01")
    public Entity sharpeye(SpawnData data){
        return FXGL.entityBuilder(data)
                //设置外观和大小
                .type(Gametype.SHARPEYE)
                .with(new EffectComponent())
                .with(new TankComponent())
                .with(new EnemyComponent())
                .viewWithBBox("element/Enemy1.png")
                .with(new CollidableComponent(true))
                .with(new LevelComponent())
                .build();
    }

    @Spawns("people")
           public Entity people(SpawnData data) {
        HealthIntComponent HPComponent=new HealthIntComponent(8);
        HPComponent.setValue(8);
        ProgressBar hp=new ProgressBar(false);
        hp.setLabelVisible(false);
        hp.maxValueProperty().bind(HPComponent.maxValueProperty());
        hp.currentValueProperty().bind(HPComponent.valueProperty());
        hp.setWidth(60);
        hp.setTranslateY(30);
        hp.setFill(Color.LIGHTGREEN);
        hp.currentValueProperty().addListener((ob,ov,nv)->{
            if(nv.intValue()<3){
                hp.setFill(Color.RED);
            } else if (nv.intValue()<5) {
                hp.setFill(Color.YELLOW);
            }else{
                hp.setFill(Color.LIGHTGREEN);
            }

        });
        return FXGL.entityBuilder(data)
                //设置外观和大小
                .type(Gametype.PEOPLE)
                .with(new EffectComponent())
                .with(new TankComponent())
                .with(new NewPeopleComponent())
                .with(new CollidableComponent(true))
                .view(hp)
                .with(HPComponent)
                .with(new LevelComponent())
                .zIndex(1)
                .build();
    }

    @Spawns("egg")
    public Entity egg(SpawnData data) {
        return FXGL.entityBuilder(data)
                //设置外观和大小
                .type(Gametype.EGG)
                .viewWithBBox("Egg.png")
                .with(new CollidableComponent(true))
                .with(new EggComponent())
                .build();
    }
    @Spawns("Chicken")
    public Entity newChicken(SpawnData data) {
        return FXGL.entityBuilder(data)
                //设置外观和大小
                .type(Gametype.CHICKEN)
                .with(new EffectComponent())
                .with(new TankComponent())
                .with(new ChickenComponent())
                .with(new CollidableComponent(true))
                .build();
    }
    @Spawns("ChickenEnemy")
    public Entity newChickenEnemy(SpawnData data) {
        return FXGL.entityBuilder(data)
                //设置外观和大小
                .type(Gametype.CHICKENENEMY)
                .with(new EffectComponent())
                .with(new TankComponent())
                .with(new ChickenEnemyComponent())
                .with(new CollidableComponent(true))
                .build();
    }
    @Spawns("Boss1")
    public Entity newBoss(SpawnData data){
        HealthIntComponent HPComponent=new HealthIntComponent(13);
        HPComponent.setValue(13);
        ProgressBar hp=new ProgressBar(false);
        hp.setLabelVisible(false);
        hp.maxValueProperty().bind(HPComponent.maxValueProperty());
        hp.currentValueProperty().bind(HPComponent.valueProperty());
        hp.setWidth(288);
        hp.setTranslateY(0);
        hp.setFill(Color.LIGHTGREEN);
        hp.currentValueProperty().addListener((ob,ov,nv)->{
            if(nv.intValue()<3){
                hp.setFill(Color.RED);
            } else if (nv.intValue()<7) {
                hp.setFill(Color.YELLOW);
            }else{
                hp.setFill(Color.LIGHTGREEN);
            }

        }
        );
        return FXGL.entityBuilder(data)
                //设置外观和大小
                .type(Gametype.BOSS)
                .viewWithBBox("BossTank.png")
                .with(new CollidableComponent(true))
                .view(hp)
                .with(HPComponent)
                .build();
    }
             @Spawns("Tank")
        public Entity player2(SpawnData data){
                 HealthIntComponent HPComponent=new HealthIntComponent(6);
                 HPComponent.setValue(6);
                 ProgressBar hp=new ProgressBar(false);
                 hp.setLabelVisible(false);
                 hp.maxValueProperty().bind(HPComponent.maxValueProperty());
                 hp.currentValueProperty().bind(HPComponent.valueProperty());
                 hp.setWidth(128);
                 hp.setTranslateY(130);
                 hp.setFill(Color.LIGHTGREEN);
                 hp.currentValueProperty().addListener((ob,ov,nv)->{
                     if(nv.intValue()<3){
                         hp.setFill(Color.RED);
                     } else if (nv.intValue()<5) {
                         hp.setFill(Color.YELLOW);
                     }else{
                         hp.setFill(Color.LIGHTGREEN);
                     }

                 });

            return FXGL.entityBuilder(data)
                    //设置外观和大小
                    .type(Gametype.PLAYER2)
                    .with(new EffectComponent())
                    .with(new TankComponent())
                    .viewWithBBox("element/Tank.png")
                    .with(new CollidableComponent(true))
                    .view(hp)
                    .with(HPComponent)
                    .with(new LevelComponent())
                    .zIndex(1)
                    .build();
    }





    @Spawns("Caseblock")
    public Entity newCaseblock(SpawnData data){
            return FXGL.entityBuilder(data)
                    .type(Gametype.CASEBLOCK)
                    .bbox(BoundingShape.box(64,64))
                    .collidable()
                    .neverUpdated()
                    .build();
        }
    @Spawns("Luckyblock")
    public Entity newLuckyblock(SpawnData data){
        return FXGL.entityBuilder(data)
                .type(Gametype.LUCKYBLOCK)
                .bbox(BoundingShape.box(64,64))
                .collidable()
                .build();
    }
    @Spawns("Stoneblock")
    public Entity newStoneblock(SpawnData data){
        HealthIntComponent HPComponent=new HealthIntComponent(3);
        HPComponent.setValue(3);
        return FXGL.entityBuilder(data)
                .type(Gametype.STONEBLOCK)
                .bbox(BoundingShape.box(64,64))
                .collidable()
                .with(HPComponent)
                .neverUpdated()
                .build();
    }

    @Spawns("Unbreakablewall")
    public Entity newUnbreakablewall(SpawnData data){
        HealthIntComponent HPComponent=new HealthIntComponent(3);
        HPComponent.setValue(3);
        return FXGL.entityBuilder(data)
                .type(Gametype.UNBREAKABLEWALL)
                .bbox(BoundingShape.box(64,64))
                .collidable()
                .with(HPComponent)
                .neverUpdated()
                .build();
    }
    @Spawns("Wall")
    public Entity newWall(SpawnData data){
        HealthIntComponent HPComponent=new HealthIntComponent(2);
        HPComponent.setValue(2);
        return FXGL.entityBuilder(data)
                .type(Gametype.WALL)
                .bbox(BoundingShape.box(64,64))
                .with(HPComponent)
                .collidable()
                .build();
    }
    @Spawns("Bullet")
    public Entity newBullet(SpawnData data){
        return FXGL.entityBuilder(data)
                .type(Gametype.BUllet)
                .viewWithBBox("element/Bullet.png")
                .collidable()
                .build();
    }
    @Spawns("Green")
    public Entity newGreen(SpawnData data){
        return FXGL.entityBuilder(data)
                .type(Gametype.Green)
                .bbox(BoundingShape.box(64,64))
                .collidable()
                .zIndex(10)
                .neverUpdated()
                .build();
    }
    @Spawns("border")
    public Entity newborder(SpawnData data){
        int width=data.<Integer>get("width");
        int heitht=data.<Integer>get("height");
        return FXGL.entityBuilder(data)
                .type(Gametype.BORDER)
                .viewWithBBox(new Rectangle(width,heitht, Color.WHITE))
                .collidable()
                .neverUpdated()
                .build();
    }
    @Spawns("bullet")
    public Entity newbullet(SpawnData data){
        FXGL.play("boom.wav");
        Point2D direction=data.get("dir");
        CollidableComponent collidableComponent=new CollidableComponent(true);
        collidableComponent.addIgnoredType(data.<Gametype>get("ownerType"));
        return FXGL.entityBuilder(data)
                .type(Gametype.BUllet)
                .viewWithBBox("element/Bullet.png")
                .with(new ProjectileComponent(direction,Config.SpeedBullet))
                .zIndex(0)
                .with(collidableComponent)
                .build();
    }
    @Spawns("Good")
    public Entity good(SpawnData data){
        FXGL.play("JI (1).wav");
        Point2D direction=data.get("dir");
        CollidableComponent collidableComponent=new CollidableComponent(true);
        collidableComponent.addIgnoredType(data.<Gametype>get("ownerType"));
        return FXGL.entityBuilder(data)
                .type(Gametype.BUllet)
                .viewWithBBox("element/Good.png")
                .with(new ProjectileComponent(direction,Config.SpeedBullet))
                .zIndex(0)
                .with(collidableComponent)
                .build();
    }
    @Spawns("Enemybullet")
    public Entity newEnemybullet(SpawnData data){
        Point2D direction=data.get("dir");
        CollidableComponent collidableComponent=new CollidableComponent(true);
        collidableComponent.addIgnoredType(data.<Gametype>get("ownerType"));
        return FXGL.entityBuilder(data)
                .type(Gametype.BUllet)
                .viewWithBBox("element/Enemybullet.png")
                .with(new ProjectileComponent(direction,Config.Speedenemybullet))
                .with(collidableComponent)
                .zIndex(0)
                .build();
    }

    @Spawns("explode")
    public Entity newexplode(SpawnData data){
        AnimationChannel ac=new AnimationChannel(FXGL.image("boom.png"), Duration.seconds(0.4),4);
        AnimatedTexture at=new AnimatedTexture(ac);
        return FXGL.entityBuilder(data)
                .view(at.play())
                .with(new ExpireCleanComponent(Duration.seconds(0.4)))
                .zIndex(1)
                .build();
    }
    @Spawns("smallexplode")
    public Entity newsmallexplode(SpawnData data){
        AnimationChannel ac=new AnimationChannel(FXGL.image("SmallBoom.png"), Duration.seconds(0.4),4);
        AnimatedTexture at=new AnimatedTexture(ac);
        return FXGL.entityBuilder(data)
                .view(at.play())
                .with(new ExpireCleanComponent(Duration.seconds(0.4)))
                .zIndex(1)
                .build();
    }
    @Spawns("Props")
    public Entity newProps(SpawnData data){
       PropsType propsType= FXGLMath.random(PropsType.values()).get();
       data.put("propsType",propsType);
       return FXGL.entityBuilder(data)
                .type(Gametype.PROPS)
                .viewWithBBox("Props/"+ propsType +".png")
               .with(new ExpireCleanComponent(Duration.seconds(15)))
                .collidable()
                .zIndex(12)
               // FXGL.runOnce(animatedTexture::loop,Duration.seconds(3));
                .build();
            }
    @Spawns("Props2")
    public Entity props2(SpawnData data){
         PropsType_forHiddenLevel propsType= FXGLMath.random(PropsType_forHiddenLevel.values()).get();
        data.put("props2Type",propsType);
        return FXGL.entityBuilder(data)
                .type(Gametype.PROPS)
                .viewWithBBox("Props/"+propsType.toString()+".png")
                .with(new ExpireCleanComponent(Duration.seconds(15)))
                .collidable()
                .zIndex(12)
                // FXGL.runOnce(animatedTexture::loop,Duration.seconds(3));
                .build();
    }


}

