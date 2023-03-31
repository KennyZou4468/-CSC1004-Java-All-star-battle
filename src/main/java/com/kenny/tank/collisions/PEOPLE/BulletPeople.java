package com.kenny.tank.collisions.PEOPLE;

import com.almasb.fxgl.app.scene.FXGLMenu;
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

public class BulletPeople extends CollisionHandler {
    //子弹和玩家的碰撞
    private Main_menu mainMenu=new Main_menu();
              public BulletPeople(){
                  super(Gametype.BUllet,Gametype.PEOPLE);
              }


    @Override
    protected void onCollisionBegin(Entity bullet, Entity people) {
      /*  if(mainMenu.IsIkun()){
            System.out.println(6);
        }else {
            System.out.println(8);
        }*/

                  //检查是否有无敌效果
        boolean a = people.getComponent(EffectComponent.class).hasEffect(GODEffect.class);
        if (a|| mainMenu.IsIkun()) {
            bullet.removeFromWorld();

        } else {
            //减少血量
            HealthIntComponent HP = people.getComponent(HealthIntComponent.class);
            HP.damage(1);
            spawn("explode", people.getCenter()
                    .subtract(80, 100));
            if (HP.isZero()) {
                people.removeFromWorld();
                spawn("explode", people.getCenter()
                        .subtract(80, 100));
                FXGL.getSceneService().pushSubScene(new FailedScene());
            }
            bullet.removeFromWorld();
        }
    }

}
