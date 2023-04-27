package com.kenny.AllStarBattle.collisions.PLAYER;

import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.EffectComponent;
import com.almasb.fxgl.dsl.components.HealthIntComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import com.kenny.AllStarBattle.Gametype;
import com.kenny.AllStarBattle.PropsType;
import com.kenny.AllStarBattle.effect.GODEffect;
import com.kenny.AllStarBattle.effect.PortalEffect;
import com.kenny.AllStarBattle.ui.FailedScene;

import static com.almasb.fxgl.dsl.FXGL.spawn;

public class PropsEnemy extends CollisionHandler {
// 敌人获得道具

    public PropsEnemy() {
        super(Gametype.PROPS,Gametype.ENEMY);
    }

    @Override
    protected void onCollisionBegin(Entity props, Entity enemy) {
        PropsType p1=props.getObject("propsType");
        switch (p1){
            //升级道具，BOSS加血
            case UPGRADE -> {
             FXGL.getGameWorld().getEntitiesByType(Gametype.BOSS).forEach(boss->{
                 if(boss.isActive()){
                     HealthIntComponent hp=boss.getComponent(HealthIntComponent.class);
                     hp.restore(1);
                 }
             });
            }
            case LUCKYBLOCK -> {
                //幸运方块，可能产生敌人也可能给玩家恢复血量
                if(FXGLMath.random(0,1)<0.65){
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
//减少玩家血量（可能突然死亡）
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
// 变身穿墙坦克
                    enemy.getComponent(EffectComponent.class).startEffect(new PortalEffect());
            }
            case GOD -> {
//无敌效果
                   enemy.getComponent(EffectComponent.class).startEffect(new GODEffect());
            }
        }
        props.removeFromWorld();
    }
}
