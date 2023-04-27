package com.kenny.AllStarBattle.collisions;

import com.almasb.fxgl.dsl.components.HealthIntComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import com.kenny.AllStarBattle.Config;
import com.kenny.AllStarBattle.Gametype;

import static com.almasb.fxgl.dsl.FXGL.spawn;

public class BulletUnbreakablewall extends CollisionHandler {
    //子弹和坚固的墙的碰撞
              public BulletUnbreakablewall(){
                  super(Gametype.BUllet,Gametype.UNBREAKABLEWALL);
              }

    @Override
    protected void onCollisionBegin(Entity bullet, Entity unbreakablewall) {
                  //需提升等级才能摧毁，同样需要打多次才能移除
        HealthIntComponent HP=unbreakablewall.getComponent(HealthIntComponent.class);
                  int level= bullet.getInt("level");
        spawn("smallexplode",unbreakablewall.getCenter()
                .subtract(35,80));
        bullet.removeFromWorld();
        if(level== Config.Tank_Max_Level){
            HP.damage(1);
            if(HP.isZero()){
                unbreakablewall.removeFromWorld();
            }
        }
    }
}
