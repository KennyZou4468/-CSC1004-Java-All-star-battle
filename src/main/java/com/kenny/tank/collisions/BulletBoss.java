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

public class BulletBoss extends CollisionHandler {
              public BulletBoss(){
                  super(Gametype.BUllet,Gametype.BOSS);
              }

    @Override
    protected void onCollisionBegin(Entity bullet, Entity boss) {
            HealthIntComponent HP = boss.getComponent(HealthIntComponent.class);
            HP.damage(1);
            spawn("explode", boss.getCenter()
                    .subtract(80, 100));
            if (HP.isZero()) {
                boss.removeFromWorld();
                spawn("explode", boss.getCenter()
                        .subtract(80, 100));
            }
            bullet.removeFromWorld();
    }

}
