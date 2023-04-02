package com.kenny.tank.components;

import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.core.util.LazyValue;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityGroup;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.time.LocalTimer;
import com.kenny.tank.Config;
import com.kenny.tank.Dir;
import com.kenny.tank.Gametype;

import java.util.List;

public class EnemyComponent extends Component {
// 敌人组件，移动继承至坦克组件
    private  TankComponent tankComponent;
    private LocalTimer EnemyTimer;
    private Dir move_enemydir=Dir.RIGHT;

    public Dir getMove_enemydir() {
        return move_enemydir;
    }

    @Override
    public void onUpdate(double tpf) {
        //随时获取方向，并且随机移动
        Dir enemymove =tankComponent.getMoveDir2();
        if(FXGLMath.randomBoolean(0.015)){
            enemymove=FXGLMath.random(Dir.values()).get();
        }
        switch (enemymove){
            case UP -> tankComponent.moveUp();
            case DOWN -> tankComponent.moveDown();
            case RIGHT -> tankComponent.moveRight();
            case LEFT -> tankComponent.moveLeft();
            default -> {}
        }
        if(!entity.isType(Gametype.SHARPEYE)) {
            if (FXGLMath.randomBoolean(0.04)) {
                enemyshoot();
            }
        }
    }

    @Override
    public void onAdded() {
        EnemyTimer=FXGL.newLocalTimer();
    }

    private LazyValue<EntityGroup>entityGroupLazyValue=new LazyValue<>(()->FXGL.getGameWorld().getGroup(
            Gametype.PLAYER2,
            Gametype.BUllet
    ));
     public void enemyshoot(){
         if(EnemyTimer.elapsed(Config.enemy2shooot)){
          FXGL.spawn("Enemybullet",new SpawnData(
                  entity.getCenter().subtract(6,14/2.0)
          ).put("dir",tankComponent.getMoveDir2().getVector())
                  //传入实体类型
                          .put("ownerType",entity.getType())
                  //传入等级
                          .put("level",entity.getComponent(LevelComponent.class).getValue())
          );
          EnemyTimer.capture();
         }
     }



}
