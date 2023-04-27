package com.kenny.AllStarBattle.components;

import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.time.LocalTimer;
import com.kenny.AllStarBattle.Config;

public class EggComponent extends Component {
    //鸡蛋组件
        private LocalTimer eggtimer;

    @Override
    public void onAdded() {
        eggtimer= FXGL.newLocalTimer();
        eggtimer.capture();
    }

    @Override
    public void onUpdate(double tpf) {
      eggbreak();
    }
    public void eggbreak() {
        //蛋碎方法，产生克鲁苏敌人
        if (eggtimer.elapsed(Config.eggbreak)) {
            double a=FXGLMath.random(0,1);
            if(a<0.3) {
                FXGL.spawn("enemy02", entity.getCenter().getX() , entity.getCenter().getY());
            } else{
                FXGL.spawn("enemy01", entity.getCenter().getX() , entity.getCenter().getY());
            }
            entity.removeFromWorld();
        }

    }

}
