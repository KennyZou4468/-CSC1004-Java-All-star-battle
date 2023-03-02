package com.kenny.tank.collisions;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import com.kenny.tank.Gametype;

import static com.almasb.fxgl.dsl.FXGL.spawn;

public class BulletCaseblock extends CollisionHandler {
              public BulletCaseblock(){
                  super(Gametype.BUllet,Gametype.CASEBLOCK);
              }

    @Override
    protected void onCollisionBegin(Entity bullet, Entity caseblock) {
        spawn("smallexplode",caseblock.getCenter()
                .subtract(35,80));
        bullet.removeFromWorld();
        caseblock.removeFromWorld();
    }

}
