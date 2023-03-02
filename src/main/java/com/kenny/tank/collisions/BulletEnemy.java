package com.kenny.tank.collisions;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import com.kenny.tank.Gametype;

import static com.almasb.fxgl.dsl.FXGL.spawn;

public class BulletEnemy extends CollisionHandler {
              public BulletEnemy(){
                  super(Gametype.BUllet,Gametype.ENEMY);
              }

    @Override
    protected void onCollisionBegin(Entity bullet, Entity enemy) {
        spawn("explode",enemy.getCenter()
                .subtract(80,100));
        bullet.removeFromWorld();
        enemy.removeFromWorld();
    }

}
