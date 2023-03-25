package com.kenny.tank.components;

import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.time.LocalTimer;
import com.kenny.tank.Config;

public class EggComponent extends Component {
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
        if (eggtimer.elapsed(Config.eggbreak)) {
            double a=FXGLMath.random(0,1);
            if(a<0.3) {
                FXGL.spawn("enemy02", entity.getCenter().getX() - 64, entity.getCenter().getY() - 64);
            } else{
                FXGL.spawn("enemy01", entity.getCenter().getX() - 64, entity.getCenter().getY() - 64);
            }
            entity.removeFromWorld();
        }

    }

}
