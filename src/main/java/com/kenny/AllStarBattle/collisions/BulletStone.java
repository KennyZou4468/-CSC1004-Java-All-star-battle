package com.kenny.AllStarBattle.collisions;

import com.almasb.fxgl.dsl.components.HealthIntComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import com.kenny.AllStarBattle.Gametype;

import static com.almasb.fxgl.dsl.FXGL.spawn;

public class BulletStone extends CollisionHandler {
    //子弹和石头的碰撞
              public BulletStone(){
                  super(Gametype.BUllet,Gametype.STONEBLOCK);
              }

    @Override
    protected void onCollisionBegin(Entity bullet, Entity stone) {
                  //需打多次才能摧毁，无需升级
                  HealthIntComponent HP= stone.getComponent(HealthIntComponent.class);
                  HP.damage(1);
                  if(HP.isZero()){
                      stone.removeFromWorld();
                      spawn("explode",stone.getCenter()
                              .subtract(80,100));
                  }
        bullet.removeFromWorld();

    }

}
