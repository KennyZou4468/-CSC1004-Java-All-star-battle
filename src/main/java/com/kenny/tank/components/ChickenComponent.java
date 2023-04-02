package com.kenny.tank.components;

import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import com.almasb.fxgl.time.LocalTimer;
import com.kenny.tank.Config;
import com.kenny.tank.Dir;
import javafx.util.Duration;

public class ChickenComponent extends Component {
// 小鸡组件，进行序列帧的播放
    //导入坦克组件进行移动
    private TankComponent tankComponent;
    private Dir chickenmovedir=Dir.DOWN;
    private AnimationChannel acUP,acDOWN,acLEFT,acRIGHT;
    private AnimatedTexture at;
    private static final int speed=150;
    private LocalTimer eggtimer;

    public Dir getchickenmovedir(){
        return chickenmovedir;
    }


    @Override
    public void onAdded() {
         entity.getViewComponent().addChild(at);
         entity.getBoundingBoxComponent().addHitBox(new HitBox(BoundingShape.box(144/3,256/4)));
        eggtimer=FXGL.newLocalTimer();
    }

    @Override
    public void onUpdate(double tpf) {
        //随机移动
         //distance=tpf*speed;
         if(FXGLMath.randomBoolean(0.015)){
             chickenmovedir=FXGLMath.random(Dir.values()).get();
         }
        switch (chickenmovedir){
            case UP ->
            {   if(at.getAnimationChannel()!=acUP){
                at.loopAnimationChannel(acUP);
            }
                tankComponent.moveUp1();}
            case DOWN ->
            {   if(at.getAnimationChannel()!=acDOWN){
                at.loopAnimationChannel(acDOWN);
            }
                tankComponent.moveDown1();}
            case RIGHT ->
            {   if(at.getAnimationChannel()!=acRIGHT){
                at.loopAnimationChannel(acRIGHT);
            }
                tankComponent.moveRight1();}
            case LEFT ->
            {   if(at.getAnimationChannel()!=acLEFT){
                at.loopAnimationChannel(acLEFT);
            }
                tankComponent.moveLeft1();}
            default -> {}
        }
    }
    public ChickenComponent(){
// 拆分序列帧图
        acUP=new AnimationChannel(FXGL.image("Chicken.png"),
                3,144/3,256/4, Duration.seconds(0.7),0,2);

        acDOWN=new AnimationChannel(FXGL.image("Chicken.png"),
                3,144/3,256/4, Duration.seconds(0.7),6,8);

        acLEFT=new AnimationChannel(FXGL.image("Chicken.png"),
                3,144/3,256/4, Duration.seconds(0.7),9,11);

        acRIGHT=new AnimationChannel(FXGL.image("Chicken.png"),
                3,144/3,256/4, Duration.seconds(0.7),3,5);
        at=new AnimatedTexture(acDOWN);
    }
}
