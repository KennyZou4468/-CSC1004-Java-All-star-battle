package com.kenny.tank.collisions.PLAYER;

import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.dsl.components.EffectComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import com.kenny.tank.Gametype;
import com.kenny.tank.effect.GODEffect;
import javafx.geometry.Rectangle2D;

import static com.almasb.fxgl.dsl.FXGL.inc;
import static com.almasb.fxgl.dsl.FXGL.spawn;

public class BulletEnemy extends CollisionHandler {
    //子弹和敌人的碰撞
              public BulletEnemy(){
                  super(Gametype.BUllet,Gametype.ENEMY);
              }

    @Override
    protected void onCollisionBegin(Entity bullet, Entity enemy) {
                  //检查是否有无敌效果
                  boolean a=enemy.getComponent(EffectComponent.class).hasEffect(GODEffect.class);
       if(a){
           bullet.removeFromWorld();
           return;
       }
       else {spawn("explode", enemy.getCenter()
               .subtract(80, 100));
           bullet.removeFromWorld();
           enemy.removeFromWorld();
            //得分加一
           inc("score",1);
           spawn("Props",
                   FXGLMath.randomPoint(new Rectangle2D(
                           0, 0, 32 * 64 - 100, 18 * 64 - 100
                   )));}

    }

}
