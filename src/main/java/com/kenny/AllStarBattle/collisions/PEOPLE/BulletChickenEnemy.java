
package com.kenny.AllStarBattle.collisions.PEOPLE;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import com.kenny.AllStarBattle.Gametype;

import static com.almasb.fxgl.dsl.FXGL.inc;
import static com.almasb.fxgl.dsl.FXGL.spawn;

public class BulletChickenEnemy extends CollisionHandler {
    //隐藏关卡中子弹和敌人的碰撞
              public BulletChickenEnemy(){
                  super(Gametype.BUllet,Gametype.CHICKENENEMY);
              }

    @Override
    protected void onCollisionBegin(Entity bullet, Entity chickenenemy) {
                  //获取子弹的发出者类型，判断后当碰撞时产生爆炸并且移除敌人
        Gametype a = bullet.getObject("ownerType");
        if (a.equals(Gametype.PLAYER2)||a.equals(Gametype.PEOPLE)) {
            spawn("explode", chickenenemy.getCenter()
                    .subtract(80, 100));
            bullet.removeFromWorld();
            chickenenemy.removeFromWorld();
            inc("kill",1);
        }
        else {
            bullet.removeFromWorld();
        }
    }
}
