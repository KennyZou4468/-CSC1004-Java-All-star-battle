package com.kenny.tank.collisions;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import com.kenny.tank.Gametype;

import static com.almasb.fxgl.dsl.FXGL.spawn;

public class BulletUnbreakablewall extends CollisionHandler {
              public BulletUnbreakablewall(){
                  super(Gametype.BUllet,Gametype.UNBREAKABLEWALL);
              }

    @Override
    protected void onCollisionBegin(Entity bullet, Entity unbreakablewall) {
        spawn("smallexplode",unbreakablewall.getCenter()
                .subtract(35,80));
        bullet.removeFromWorld();
    }

}
