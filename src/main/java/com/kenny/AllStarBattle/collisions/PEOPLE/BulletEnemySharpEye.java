package com.kenny.AllStarBattle.collisions.PEOPLE;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import com.kenny.AllStarBattle.Gametype;

import static com.almasb.fxgl.dsl.FXGL.spawn;

public class BulletEnemySharpEye extends CollisionHandler {
    //子弹和敌人 的碰撞
              public BulletEnemySharpEye(){
                  super(Gametype.BUllet,Gametype.SHARPEYE);
              }

    @Override
    protected void onCollisionBegin(Entity bullet, Entity sharpeye) {
        //当碰撞时产生爆炸并且移除敌人（可能产生友伤）
            spawn("explode", sharpeye.getCenter()
                    .subtract(80, 100));
            bullet.removeFromWorld();
            sharpeye.removeFromWorld();
        }
}
