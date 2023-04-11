package com.kenny.tank.collisions.PLAYER;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.EffectComponent;
import com.almasb.fxgl.dsl.components.HealthIntComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import com.kenny.tank.Gametype;
import com.kenny.tank.effect.GODEffect;
import com.kenny.tank.ui.FailedScene;
import com.kenny.tank.ui.Main_menu;

import static com.almasb.fxgl.dsl.FXGL.spawn;

public class BulletPlayer extends CollisionHandler {
    private Main_menu mainMenu=new Main_menu();
    //子弹和玩家的碰撞
              public BulletPlayer(){
                  super(Gametype.BUllet,Gametype.PLAYER2);
              }
    @Override
    protected void onCollisionBegin(Entity bullet, Entity PLAYER2) {

                  //检查是否有无敌效果
        boolean a = PLAYER2.getComponent(EffectComponent.class).hasEffect(GODEffect.class);
        if (a|| mainMenu.IsBigbrother()) {
            bullet.removeFromWorld();
            return;
        } else {
            //减少血量
            HealthIntComponent HP = PLAYER2.getComponent(HealthIntComponent.class);
            HP.damage(1);
            spawn("explode", PLAYER2.getCenter()
                    .subtract(80, 100));
            //没血则游戏失败
            if (HP.isZero()) {
                PLAYER2.removeFromWorld();
                spawn("explode", PLAYER2.getCenter()
                        .subtract(80, 100));
                FXGL.getSceneService().pushSubScene(new FailedScene());
            }
            bullet.removeFromWorld();
        }
    }

}
