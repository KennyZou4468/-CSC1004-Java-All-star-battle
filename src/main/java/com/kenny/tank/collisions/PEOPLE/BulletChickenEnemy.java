
package com.kenny.tank.collisions.PEOPLE;

import com.almasb.fxgl.core.util.LazyValue;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import com.kenny.tank.Gametype;
import com.kenny.tank.ui.EggScene;

import static com.almasb.fxgl.dsl.FXGL.inc;
import static com.almasb.fxgl.dsl.FXGL.spawn;

public class BulletChickenEnemy extends CollisionHandler {
    //子弹和敌人 的碰撞
              public BulletChickenEnemy(){
                  super(Gametype.BUllet,Gametype.CHICKENENEMY);
              }

    @Override
    protected void onCollisionBegin(Entity bullet, Entity chickenenemy) {
        Gametype a = bullet.getObject("ownerType");
        if (a.equals(Gametype.PLAYER2)||a.equals(Gametype.PEOPLE)) {
            spawn("explode", chickenenemy.getCenter()
                    .subtract(80, 100));
            bullet.removeFromWorld();
            chickenenemy.removeFromWorld();
        }
        else {
            bullet.removeFromWorld();
        }
    }
}
