package com.kenny.tank.collisions;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.EffectComponent;
import com.almasb.fxgl.dsl.components.HealthIntComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import com.kenny.tank.Gametype;
import com.kenny.tank.effect.GODEffect;
import com.kenny.tank.ui.FailedScene;

import static com.almasb.fxgl.dsl.FXGL.spawn;

public class BulletPlayer extends CollisionHandler {
              public BulletPlayer(){
                  super(Gametype.BUllet,Gametype.PLAYER2);
              }

    @Override
    protected void onCollisionBegin(Entity bullet, Entity PLAYER2) {
        boolean a = PLAYER2.getComponent(EffectComponent.class).hasEffect(GODEffect.class);
        if (a) {
            bullet.removeFromWorld();
            return;
        } else {
            HealthIntComponent HP = PLAYER2.getComponent(HealthIntComponent.class);
            HP.damage(1);
            spawn("explode", PLAYER2.getCenter()
                    .subtract(80, 100));
            if(HP.getValue()<=3){

            }
            if (HP.isZero()) {
                PLAYER2.removeFromWorld();
                spawn("explode", PLAYER2.getCenter()
                        .subtract(80, 100));
                FXGL.getSceneService().pushSubScene(new FailedScene());
            }
            bullet.removeFromWorld();
        }
    }

}
