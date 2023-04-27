package com.kenny.AllStarBattle.collisions;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import com.kenny.AllStarBattle.Gametype;

public class BulletBorder extends CollisionHandler {
    //子弹和边界的碰撞，直接移除
              public BulletBorder(){
                  super(Gametype.BUllet,Gametype.BORDER);
              }

    @Override
    protected void onCollisionBegin(Entity bullet, Entity border) {
        bullet.removeFromWorld();
    }
}
