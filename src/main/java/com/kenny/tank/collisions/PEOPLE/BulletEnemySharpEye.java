package com.kenny.tank.collisions.PEOPLE;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import com.kenny.tank.Gametype;

import static com.almasb.fxgl.dsl.FXGL.spawn;

public class BulletEnemySharpEye extends CollisionHandler {
    //子弹和敌人 的碰撞
              public BulletEnemySharpEye(){
                  super(Gametype.BUllet,Gametype.SHARPEYE);
              }

    @Override
    protected void onCollisionBegin(Entity bullet, Entity sharpeye) {
            spawn("explode", sharpeye.getCenter()
                    .subtract(80, 100));
            bullet.removeFromWorld();
            sharpeye.removeFromWorld();
        }
}
