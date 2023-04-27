package com.kenny.AllStarBattle.collisions.PEOPLE;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.EffectComponent;
import com.almasb.fxgl.dsl.components.HealthIntComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import com.kenny.AllStarBattle.Gametype;
import com.kenny.AllStarBattle.effect.GODEffect;
import com.kenny.AllStarBattle.ui.FailedScene;
import com.kenny.AllStarBattle.ui.Main_menu;

import static com.almasb.fxgl.dsl.FXGL.spawn;

public class PlayerSharpEye extends CollisionHandler {
    private Main_menu mainMenu=new Main_menu();
    //恶魔之眼和玩家的碰撞
              public PlayerSharpEye(){
                  super(Gametype.SHARPEYE,Gametype.PEOPLE);
              }

    @Override
    protected void onCollisionBegin(Entity sharpeye, Entity PLAYER2) {
                  //检查是否有无敌效果，你是否为爱坤。若是，则无敌。
        boolean a = PLAYER2.getComponent(EffectComponent.class).hasEffect(GODEffect.class);
        if (!a&& !mainMenu.IsIkun()) {
            //减少血量
            HealthIntComponent HP = PLAYER2.getComponent(HealthIntComponent.class);
            HP.damage(1);
            if (HP.isZero()) {
                PLAYER2.removeFromWorld();
                FXGL.getSceneService().pushSubScene(new FailedScene());
            }
        }
    }

}
