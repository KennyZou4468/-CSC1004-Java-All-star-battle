package com.kenny.AllStarBattle.components;

import com.almasb.fxgl.dsl.components.RechargeableIntComponent;
import com.kenny.AllStarBattle.Config;

public class LevelComponent extends RechargeableIntComponent {
    //子弹等级组件

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
