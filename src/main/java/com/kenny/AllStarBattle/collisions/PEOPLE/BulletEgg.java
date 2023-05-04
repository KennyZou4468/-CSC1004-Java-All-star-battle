package com.kenny.AllStarBattle.collisions.PEOPLE;

import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import com.kenny.AllStarBattle.Gametype;
import javafx.geometry.Rectangle2D;

import static com.almasb.fxgl.dsl.FXGL.inc;
import static com.almasb.fxgl.dsl.FXGL.spawn;

public class BulletEgg extends CollisionHandler {
    //子弹和蛋的碰撞
              public BulletEgg(){
                  super(Gametype.BUllet,Gametype.EGG);
              }

    @Override
    protected void onCollisionBegin(Entity bullet, Entity egg) {
        //获取子弹的发出者类型，判断后当碰撞时产生爆炸并且移除蛋
        Gametype a=bullet.getObject("ownerType");
                  if(a.equals(Gametype.PLAYER2)||a.equals(Gametype.PEOPLE)) {
                      spawn("explode", egg.getCenter()
                              .subtract(80, 100));
                    bullet.removeFromWorld();
           egg.removeFromWorld();
            //得分加一
           inc("kill",1);
           spawn("Props2",
                   FXGLMath.randomPoint(new Rectangle2D(
                           0, 0, 32 * 64 - 100, 18 * 64 - 100
                   )));}
                  else {
                      bullet.removeFromWorld();
                  }
    }

}
