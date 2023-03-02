package com.kenny.tank.collisions;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import com.kenny.tank.Gametype;

import static com.almasb.fxgl.dsl.FXGL.spawn;

public class BulletBullet extends CollisionHandler {
              public BulletBullet(){
                  super(Gametype.BUllet,Gametype.BUllet);
              }

    @Override
    protected void onCollisionBegin(Entity bullet1, Entity bullet2) {
        Gametype b1 = bullet1.getObject("ownerType");
        Gametype b2 = bullet2.getObject("ownerType");
        if (b1 != b2) {
            bullet1.removeFromWorld();
            bullet2.removeFromWorld();
        }

    }
}
