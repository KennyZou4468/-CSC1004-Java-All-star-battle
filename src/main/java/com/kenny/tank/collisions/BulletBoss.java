package com.kenny.tank.collisions;

import com.almasb.fxgl.core.util.LazyValue;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.EffectComponent;
import com.almasb.fxgl.dsl.components.HealthIntComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import com.kenny.tank.Gametype;
import com.kenny.tank.effect.GODEffect;
import com.kenny.tank.ui.FailedScene;
import com.kenny.tank.ui.SuccessScene;
import javafx.util.Duration;

import static com.almasb.fxgl.dsl.FXGL.*;

public class BulletBoss extends CollisionHandler {
              public BulletBoss(){
                  super(Gametype.BUllet,Gametype.BOSS);
              }

    @Override
    protected void onCollisionBegin(Entity bullet, Entity boss) {
        Gametype b1 = bullet.getObject("ownerType");
        LazyValue<SuccessScene> successSceneLazyValue=new LazyValue<>(SuccessScene::new);
        HealthIntComponent HP = boss.getComponent(HealthIntComponent.class);
        if(b1.equals(Gametype.ENEMY)){
            HP.restore(1);
        }else {
            HP.damage(1);
            spawn("explode", boss.getCenter()
                    .subtract(80, 100));
            if (HP.isZero()) {
                boss.removeFromWorld();
                spawn("explode", boss.getCenter()
                        .subtract(80, 100));
                FXGL.getSceneService().pushSubScene(successSceneLazyValue.get());
               /* getip("score").addListener((ob, ov, nv) -> {
                    if (nv.intValue() > 0) {
                        FXGL.getSceneService().pushSubScene(successSceneLazyValue.get());
                        ;
                    }
                });*/
            }
        }
            bullet.removeFromWorld();
    }

}
