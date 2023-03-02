package com.kenny.tank.collisions;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import com.kenny.tank.Gametype;

public class BulletBorder extends CollisionHandler {
              public BulletBorder(){
                  super(Gametype.BUllet,Gametype.BORDER);
              }

    @Override
    protected void onCollisionBegin(Entity bullet, Entity border) {
        bullet.removeFromWorld();
    }
}
