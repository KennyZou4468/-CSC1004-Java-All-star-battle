package com.kenny.tank.collisions.PEOPLE;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.EffectComponent;
import com.almasb.fxgl.dsl.components.HealthIntComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import com.kenny.tank.Gametype;
import com.kenny.tank.effect.GODEffect;
import com.kenny.tank.ui.FailedScene;

import static com.almasb.fxgl.dsl.FXGL.spawn;

public class PlayerSharpEye extends CollisionHandler {
    //恶魔之眼和玩家的碰撞
              public PlayerSharpEye(){
                  super(Gametype.SHARPEYE,Gametype.PLAYER2);
              }

    @Override
    protected void onCollisionBegin(Entity sharpeye, Entity PLAYER2) {
                  //检查是否有无敌效果
        boolean a = PLAYER2.getComponent(EffectComponent.class).hasEffect(GODEffect.class);
        if (!a) {
            //减少血量
            HealthIntComponent HP = PLAYER2.getComponent(HealthIntComponent.class);
            HP.damage(1);
            if (HP.isZero()) {
                PLAYER2.removeFromWorld();
                spawn("explode", PLAYER2.getCenter()
                        .subtract(80, 100));
                PLAYER2.removeFromWorld();
                FXGL.getSceneService().pushSubScene(new FailedScene());
            }
        }
    }

}
