package com.kenny.tank.collisions;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import com.kenny.tank.Gametype;

import static com.almasb.fxgl.dsl.FXGL.spawn;

public class BulletWall extends CollisionHandler {
              public BulletWall(){
                  super(Gametype.BUllet,Gametype.WALL);
              }

    @Override
    protected void onCollisionBegin(Entity bullet, Entity wall) {
        spawn("smallexplode",wall.getCenter()
                .subtract(35,80));
        bullet.removeFromWorld();
        wall.removeFromWorld();
    }

}
