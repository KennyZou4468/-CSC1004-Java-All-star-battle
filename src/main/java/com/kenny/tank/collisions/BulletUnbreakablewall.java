package com.kenny.tank.collisions;

import com.almasb.fxgl.dsl.components.HealthIntComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import com.kenny.tank.Config;
import com.kenny.tank.Gametype;
import com.kenny.tank.components.LevelComponent;

import static com.almasb.fxgl.dsl.FXGL.spawn;

public class BulletUnbreakablewall extends CollisionHandler {
              public BulletUnbreakablewall(){
                  super(Gametype.BUllet,Gametype.UNBREAKABLEWALL);
              }

    @Override
    protected void onCollisionBegin(Entity bullet, Entity unbreakablewall) {
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
