package com.kenny.tank.collisions.PEOPLE;

import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.dsl.components.EffectComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import com.kenny.tank.Gametype;
import com.kenny.tank.effect.GODEffect;
import javafx.geometry.Rectangle2D;

import static com.almasb.fxgl.dsl.FXGL.inc;
import static com.almasb.fxgl.dsl.FXGL.spawn;

public class BulletEnemyEye extends CollisionHandler {
    //子弹和敌人的碰撞
              public BulletEnemyEye(){
                  super(Gametype.BUllet,Gametype.ENEMYEYE);
              }

    @Override
    protected void onCollisionBegin(Entity bullet, Entity enemyeye) {
        //当碰撞时产生爆炸并且移除敌人（可能产生友伤）
        spawn("explode", enemyeye.getCenter()
                    .subtract(80, 100));
            bullet.removeFromWorld();
            enemyeye.removeFromWorld();
        }
}
