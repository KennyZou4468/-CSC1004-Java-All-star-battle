package com.kenny.tank.collisions;

import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.EffectComponent;
import com.almasb.fxgl.dsl.components.HealthIntComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import com.kenny.tank.Gametype;
import com.kenny.tank.PropsType;
import com.kenny.tank.components.LevelComponent;
import com.kenny.tank.effect.GODEffect;
import com.kenny.tank.effect.PortalEffect;
import com.kenny.tank.ui.FailedScene;

import static com.almasb.fxgl.dsl.FXGL.spawn;

public class PropsEnemy extends CollisionHandler {

    public PropsEnemy() {
        super(Gametype.PROPS,Gametype.ENEMY);
    }

    @Override
    protected void onCollisionBegin(Entity props, Entity enemy) {
        PropsType p1=props.getObject("propsType");
        switch (p1){
            case UPGRADE -> {
             FXGL.getGameWorld().getEntitiesByType(Gametype.BOSS).forEach(boss->{
                 if(boss.isActive()){
                     HealthIntComponent hp=boss.getComponent(HealthIntComponent.class);
                     hp.restore(1);
                 }
             });
            }
            case LUCKYBLOCK -> {
                if(FXGLMath.random(0,1)<0.5){
                    FXGL.spawn("enemy1",720,720);
                }else {
                    FXGL.getGameWorld().getEntitiesByType(Gametype.PLAYER2).forEach(player->{
                        if(player.isActive()){
                            HealthIntComponent hp=player.getComponent(HealthIntComponent.class);
                            hp.restore(1);
                        }
                    });
                }
            }
            case HEART -> {

                FXGL.getGameWorld().getEntitiesByType(Gametype.PLAYER2).forEach(player->{
                    if(player.isActive()){
                        HealthIntComponent hp=player.getComponent(HealthIntComponent.class);
                        hp.damage(1);
                        if (hp.isZero()) {
                            player.removeFromWorld();
                            spawn("explode", player.getCenter()
                                    .subtract(80, 100));
                            FXGL.getSceneService().pushSubScene(new FailedScene());
                        }
                    }
                });
            }
            case PORTAL -> {
                    enemy.getComponent(EffectComponent.class).startEffect(new PortalEffect());
            }
            case GOD -> {
                   enemy.getComponent(EffectComponent.class).startEffect(new GODEffect());
            }
        }
        props.removeFromWorld();
    }

}
