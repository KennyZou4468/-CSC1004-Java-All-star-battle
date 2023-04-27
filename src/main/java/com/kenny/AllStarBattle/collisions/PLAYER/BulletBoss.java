package com.kenny.AllStarBattle.collisions.PLAYER;

import com.almasb.fxgl.core.util.LazyValue;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.HealthIntComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import com.kenny.AllStarBattle.Gametype;
import com.kenny.AllStarBattle.ui.SuccessScene;

import static com.almasb.fxgl.dsl.FXGL.*;

public class BulletBoss extends CollisionHandler {
    //子弹和BOSS坦克的碰撞
              public BulletBoss(){
                  super(Gametype.BUllet,Gametype.BOSS);
              }

    @Override
    protected void onCollisionBegin(Entity bullet, Entity boss) {
        Gametype b1 = bullet.getObject("ownerType");
        LazyValue<SuccessScene> successSceneLazyValue=new LazyValue<>(SuccessScene::new);
        HealthIntComponent HP = boss.getComponent(HealthIntComponent.class);
    //敌人子弹给BOSS回血
        if(b1.equals(Gametype.ENEMY)){
            HP.restore(1);
        }else {
            //玩家坦克造成伤害，如果BOSS没血了即胜利
            HP.damage(1);
            spawn("explode", boss.getCenter()
                    .subtract(80, 100));
            if (HP.isZero()) {
                boss.removeFromWorld();
                spawn("explode", boss.getCenter()
                        .subtract(80, 100));
                FXGL.getSceneService().pushSubScene(successSceneLazyValue.get());
            }
        }
            bullet.removeFromWorld();
    }

}
