
package com.kenny.tank.collisions.PLAYER;

import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.core.util.LazyValue;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.EffectComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import com.kenny.tank.Gametype;
import com.kenny.tank.effect.GODEffect;
import com.kenny.tank.ui.EggScene;
import com.kenny.tank.ui.SuccessScene;
import javafx.geometry.Rectangle2D;

import static com.almasb.fxgl.dsl.FXGL.inc;
import static com.almasb.fxgl.dsl.FXGL.spawn;

public class BulletChicken extends CollisionHandler {
    //子弹和特殊敌人“鸡”的碰撞
              public BulletChicken(){
                  super(Gametype.BUllet,Gametype.CHICKEN);
              }

    @Override
    protected void onCollisionBegin(Entity bullet, Entity chicken) {
        Gametype a = bullet.getObject("ownerType");
        if (a.equals(Gametype.PLAYER2)) {
            LazyValue<EggScene> eggSceneLazyValue = new LazyValue<>(EggScene::new);
            spawn("explode", chicken.getCenter()
                    .subtract(80, 100));
            bullet.removeFromWorld();
            chicken.removeFromWorld();
            //得分加一
            inc("score", 1);
            //触发隐藏关卡的条件：不击杀任何一辆坦克的情况下杀死鸡，才能触发隐藏关卡
            if (FXGL.geti("score") <2) {
                FXGL.getSceneService().pushSubScene(eggSceneLazyValue.get());
            }
        }
        else {
            bullet.removeFromWorld();
        }
    }
}
