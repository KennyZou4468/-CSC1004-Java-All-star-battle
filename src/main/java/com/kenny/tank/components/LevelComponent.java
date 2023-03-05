package com.kenny.tank.components;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.RechargeableIntComponent;
import com.almasb.fxgl.time.LocalTimer;
import com.kenny.tank.Config;
import javafx.util.Duration;

public class LevelComponent extends RechargeableIntComponent {

    public LevelComponent() {
        super(Config.Tank_Max_Level,Config.Tank_Min_Level);
    }
    public void upgrade(){
        restore(1);
    }
    public void counter_upgrade(){
        damage(1);
    }

    public void Strongest(){
        restoreFully();
    }


}
