package com.kenny.tank.collisions.PEOPLE;

import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.EffectComponent;
import com.almasb.fxgl.dsl.components.HealthIntComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import com.kenny.tank.Gametype;
import com.kenny.tank.PropsType;
import com.kenny.tank.PropsType_forHiddenLevel;
import com.kenny.tank.components.LevelComponent;
import com.kenny.tank.effect.GODEffect;
import com.kenny.tank.effect.PortalEffect;

public class PropsPlayer_forPeople extends CollisionHandler {

    public PropsPlayer_forPeople() {
        super(Gametype.PROPS,Gametype.PEOPLE);
    }

    @Override
    protected void onCollisionBegin(Entity props, Entity player) {
        //主人物获得道具
        PropsType_forHiddenLevel p1=props.getObject("props2Type");
        switch (p1){
            case LUCKYBLOCK-> {
                //幸运方块，可能生成敌人并且降级，或者升级并回血
                if(FXGLMath.random(0,1)<0.5){
                    FXGL.spawn("enemy01",720,720);
                    player.getComponent(LevelComponent.class).damageFully();
                }else {
                    player.getComponent(HealthIntComponent.class).restoreFully();
                }
            }
            case HEART -> {
                //恢复一点血量
                player.getComponent(HealthIntComponent.class).restore(1);;
            }
            case GOD -> {
                //无敌
                   player.getComponent(EffectComponent.class).startEffect(new GODEffect());
            }
        }
        props.removeFromWorld();
    }

}
