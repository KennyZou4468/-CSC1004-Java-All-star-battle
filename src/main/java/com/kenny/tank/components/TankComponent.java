package com.kenny.tank.components;

import com.almasb.fxgl.entity.component.Component;
import com.kenny.tank.Config;

public class TankComponent extends Component {

    private boolean ismoving2=false;
    private double distance;

    @Override
    public void onUpdate(double tpf) {
        ismoving2=false;
        distance=tpf * Config.SpeedTank;
    }

    public  void moveUp(){
        if(ismoving2){
            return;
        }
        ismoving2=true;
        entity.setRotation(270);
        entity.translate(0,-distance);

    }
    public  void moveDown(){
        if(ismoving2){
            return;
        }
        ismoving2=true;
        entity.setRotation(90);
        entity.translate(0,distance);

    }
    public  void moveRight(){
        if(ismoving2){
            return;
        }
        ismoving2=true;
        entity.setRotation(0);
        entity.translate(distance,0);

    }
    public  void moveLeft(){
        if(ismoving2){
            return;
        }
        ismoving2=true;
        entity.setRotation(180);
        entity.translate(-distance,0);

    }
    public  void shoot(){


    }

}
