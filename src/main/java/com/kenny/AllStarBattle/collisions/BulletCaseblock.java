package com.kenny.AllStarBattle.collisions;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import com.kenny.AllStarBattle.Gametype;

import static com.almasb.fxgl.dsl.FXGL.spawn;

public class BulletCaseblock extends CollisionHandler {
    //子弹和箱子的碰撞
              public BulletCaseblock(){
                  super(Gametype.BUllet,Gametype.CASEBLOCK);
              }

    @Override
    protected void onCollisionBegin(Entity bullet, Entity caseblock) {
                  //最容易打破的方块
                      caseblock.removeFromWorld();
        spawn("smallexplode",caseblock.getCenter()
                .subtract(35,80));
        bullet.removeFromWorld();
    }

}
