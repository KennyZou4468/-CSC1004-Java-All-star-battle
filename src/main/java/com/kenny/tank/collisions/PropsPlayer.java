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
import com.kenny.tank.components.TankComponent;
import com.kenny.tank.effect.GODEffect;
import com.kenny.tank.effect.PortalEffect;

public class PropsPlayer extends CollisionHandler {

    public PropsPlayer() {
        super(Gametype.PROPS,Gametype.PLAYER2);
    }

    @Override
    protected void onCollisionBegin(Entity props, Entity player) {
        PropsType p1=props.getObject("propsType");
        switch (p1){
            case UPGRADE -> player.getComponent(LevelComponent.class).upgrade();
            case LUCKYBLOCK -> {
                if(FXGLMath.random(0,1)<0.5){
                    FXGL.spawn("enemy1",720,720);
                    player.getComponent(LevelComponent.class).damageFully();
                }else {
                    player.getComponent(LevelComponent.class).upgrade();
                    player.getComponent(HealthIntComponent.class).restoreFully();
                }
            }
            case HEART -> {
                player.getComponent(HealthIntComponent.class).restore(1);;
            }
            case PORTAL -> {
                int hp= player.getComponent(HealthIntComponent.class).getValue();
                if(hp<3) {
                    player.getComponent(EffectComponent.class).startEffect(new PortalEffect());
                }
            }
            case GOD -> {
                   player.getComponent(EffectComponent.class).startEffect(new GODEffect());
            }
        }
        props.removeFromWorld();
    }

}
