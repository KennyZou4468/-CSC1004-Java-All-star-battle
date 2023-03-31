package com.kenny.tank.components;

import com.almasb.fxgl.core.util.LazyValue;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.Effect;
import com.almasb.fxgl.dsl.components.EffectComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityGroup;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.time.LocalTimer;
import com.kenny.tank.Config;
import com.kenny.tank.Dir;
import com.kenny.tank.Gametype;
import com.kenny.tank.effect.PortalEffect;
import javafx.util.Duration;

import java.util.List;

public class TankComponent extends Component {

    private boolean ismoving2=false;
    private double distance;
    private Dir moveDir2=Dir.RIGHT;
    private LocalTimer TankTimer;
    private EffectComponent effectComponent;

    public Dir getMoveDir2() {
        return moveDir2;
    }

    @Override
    public void onAdded() {
        TankTimer=FXGL.newLocalTimer();
    }

    private LazyValue<EntityGroup>entityGroupLazyValue=new LazyValue<>(()->FXGL.getGameWorld().getGroup
            (Gametype.BORDER
            ,Gametype.CASEBLOCK
            ,Gametype.LUCKYBLOCK
            ,Gametype.STONEBLOCK
            ,Gametype.UNBREAKABLEWALL
            ,Gametype.WALL
            ,Gametype.PLAYER2
            ,Gametype.ENEMY
            ,Gametype.CHICKEN
            ,Gametype.CHICKENENEMY
            ,Gametype.EGG));
    private LazyValue<EntityGroup>chickenGroupLazyValue=new LazyValue<>(()->FXGL.getGameWorld().getGroup
            (Gametype.BORDER
                    ,Gametype.CASEBLOCK
                    ,Gametype.LUCKYBLOCK
                    ,Gametype.STONEBLOCK
                    ,Gametype.UNBREAKABLEWALL
                    ,Gametype.WALL
                    ,Gametype.PLAYER2));
    private LazyValue<EntityGroup>eyeGroupLazyValue=new LazyValue<>(()->FXGL.getGameWorld().getGroup
            (Gametype.BORDER
                   ));
    private LazyValue<EntityGroup>entityGroup_PortionLazyValue=new LazyValue<>(()->FXGL.getGameWorld().getGroup
            (Gametype.BORDER
                    ,Gametype.LUCKYBLOCK,Gametype.PLAYER2
                    ,Gametype.ENEMY));

    @Override
    public void onUpdate(double tpf) {
        ismoving2=false;
        distance=tpf * Config.SpeedTank;
    }

    public void move(){
        int len=(int) distance;
        boolean a= effectComponent.hasEffect(PortalEffect.class);
        List<Entity> blockList;
        if(entity.isType(Gametype.CHICKENENEMY)){
            blockList=chickenGroupLazyValue.get().getEntitiesCopy();
        } else if (entity.isType(Gametype.ENEMYEYE)||entity.isType(Gametype.SHARPEYE)) {
               blockList=eyeGroupLazyValue.get().getEntitiesCopy();
        }
        else {
            if (a) {
                blockList = entityGroup_PortionLazyValue.get().getEntitiesCopy();
            } else {
                blockList = entityGroupLazyValue.get().getEntitiesCopy();
            }
        }
        blockList.remove(entity);
        int size=blockList.size();
        boolean isCollision=false;
        for (int i = 0; i < len; i++) {
            entity.translate(moveDir2.getVector().getX(),moveDir2.getVector().getY());
           for(int j=0;j<size;j++){
              if( entity.isColliding(blockList.get(j))){
                  isCollision=true;
                  break;
              }
           }
           if(isCollision){
               entity.translate(-moveDir2.getVector().getX(),-moveDir2.getVector().getY());
               break;
           }
        }
    }

    public  void moveUp(){
        if(ismoving2){
            return;
        }
        ismoving2=true;
        entity.setRotation(-90);
        moveDir2=Dir.UP;
        move();

    }
    public  void moveDown(){
        if(ismoving2){
            return;
        }
        ismoving2=true;
        entity.setRotation(90);
        moveDir2=Dir.DOWN;
        move();


    }
    public  void moveRight(){
        if(ismoving2){
            return;
        }
        ismoving2=true;
        entity.setRotation(0);
        moveDir2=Dir.RIGHT;
        move();
    }
    public  void moveLeft(){
        if(ismoving2){
            return;
        }
        ismoving2=true;
        entity.setRotation(180);
        moveDir2=Dir.LEFT;
        move();

    }
    public  void moveUp1(){
        if(ismoving2){
            return;
        }
        ismoving2=true;
        moveDir2=Dir.UP;
        move();

    }
    public  void moveDown1(){
        if(ismoving2){
            return;
        }
        ismoving2=true;
        moveDir2=Dir.DOWN;
        move();


    }
    public  void moveRight1(){
        if(ismoving2){
            return;
        }
        ismoving2=true;
        moveDir2=Dir.RIGHT;
        move();


    }
    public  void moveLeft1(){
        if(ismoving2){
            return;
        }
        ismoving2=true;
        moveDir2=Dir.LEFT;
        move();
    }


    public  void shoot(){
    if(TankTimer.elapsed(Config.Tankshoot)){
        FXGL.spawn("bullet",new SpawnData(
                entity.getCenter().subtract(18,25/2.0)
        ).put("dir",moveDir2.getVector())
                        .put("ownerType",entity.getType())
                .put("level",entity.getComponent(LevelComponent.class).getValue())
        );
        TankTimer.capture();
        }
    }

}

