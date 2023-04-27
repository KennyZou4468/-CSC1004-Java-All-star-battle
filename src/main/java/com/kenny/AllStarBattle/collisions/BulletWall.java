package com.kenny.AllStarBattle.collisions;

import com.almasb.fxgl.dsl.components.HealthIntComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import com.kenny.AllStarBattle.Gametype;

import static com.almasb.fxgl.dsl.FXGL.spawn;

public class BulletWall extends CollisionHandler {
    //子弹和墙的碰撞
              public BulletWall(){
                  super(Gametype.BUllet,Gametype.WALL);
              }

    @Override
    protected void onCollisionBegin(Entity bullet, Entity wall) {
                  //需要打多次才能摧毁，无需等级限制
        HealthIntComponent HP=wall.getComponent(HealthIntComponent.class);
        HP.damage(1);
        Gametype b1=bullet.getObject("ownerType");
        if(HP.isZero()){
            if(b1==Gametype.PLAYER2){
                wall.removeFromWorld();
            }
        }
        spawn("smallexplode",wall.getCenter()
                .subtract(35,80));
        bullet.removeFromWorld();

    }

}
