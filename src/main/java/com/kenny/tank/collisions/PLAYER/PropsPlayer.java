package com.kenny.tank.collisions.PLAYER;

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
//玩家获得道具
    public PropsPlayer() {
        super(Gametype.PROPS,Gametype.PLAYER2);
    }

    @Override
    protected void onCollisionBegin(Entity props, Entity player) {
        //道具和坦克的碰撞
        PropsType p1=props.getObject("propsType");
        switch (p1){
            //坦克子弹升级
            case UPGRADE -> player.getComponent(LevelComponent.class).upgrade();
            case LUCKYBLOCK -> {
                //幸运方块，可能生成敌人并且降级，或者升级并回血
                if(FXGLMath.random(0,1)<0.5){
                    FXGL.spawn("enemy1",720,720);
                    player.getComponent(LevelComponent.class).damageFully();
                }else {
                    player.getComponent(LevelComponent.class).upgrade();
                    player.getComponent(HealthIntComponent.class).restoreFully();
                }
            }
            case HEART -> {
                //恢复一点血量
                player.getComponent(HealthIntComponent.class).restore(1);;
            }
            case PORTAL -> {
                //穿墙道具，只有在小于3点血才能生效
                int hp= player.getComponent(HealthIntComponent.class).getValue();
                if(hp<3) {
                    player.getComponent(EffectComponent.class).startEffect(new PortalEffect());
                }
            }
            case GOD -> {
                //无敌
                   player.getComponent(EffectComponent.class).startEffect(new GODEffect());
            }
        }
        props.removeFromWorld();
    }

}
