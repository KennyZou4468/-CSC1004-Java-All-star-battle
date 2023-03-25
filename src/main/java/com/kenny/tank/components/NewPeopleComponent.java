package com.kenny.tank.components;

import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import com.almasb.fxgl.time.LocalTimer;
import com.kenny.tank.Config;
import com.kenny.tank.Dir;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;

public class NewPeopleComponent extends Component {
// 人组件，进行序列帧的播放
    private TankComponent tankComponent;
    private Dir peopledir=Dir.DOWN;
    private AnimationChannel acUP,acDOWN,acLEFT,acRIGHT;
    private AnimatedTexture at;
    private static final int speed=150;
    private LocalTimer peopletimer;

    public Dir getchickenmovedir(){
        return peopledir;
    }


    @Override
    public void onAdded() {
         entity.getViewComponent().addChild(at);
         entity.getBoundingBoxComponent().addHitBox(new HitBox(BoundingShape.box(204/6,168/4)));
        peopletimer=FXGL.newLocalTimer();
    }
    public  void moveUp(){
        if(at.getAnimationChannel()!=acUP){
            at.loopAnimationChannel(acUP);
        }
        tankComponent.moveUp1();
        peopledir=Dir.UP;
    }
    public  void moveDown(){
        if(at.getAnimationChannel()!=acDOWN){
            at.loopAnimationChannel(acDOWN);
        }
        tankComponent.moveDown1();
        peopledir=Dir.DOWN;
    }
    public  void moveRight(){
        if(at.getAnimationChannel()!=acRIGHT){
            at.loopAnimationChannel(acRIGHT);
        }
        tankComponent.moveRight1();
        peopledir=Dir.RIGHT;
    }
    public  void moveLeft(){
        if(at.getAnimationChannel()!=acLEFT){
            at.loopAnimationChannel(acLEFT);
        }
        tankComponent.moveLeft1();
        peopledir=Dir.LEFT;
    }
    public  void shoot(){
        if(peopletimer.elapsed(Config.peoplegood)){
            FXGL.spawn("Good",new SpawnData(
                            entity.getCenter().subtract(18,36/2.0)
                    ).put("dir",peopledir.getVector())
                            .put("ownerType",entity.getType())
                            .put("level",entity.getComponent(LevelComponent.class).getValue())
            );
            peopletimer.capture();
        }
    }



    public NewPeopleComponent(){
// 拆分序列帧图
        acUP=new AnimationChannel(FXGL.image("people.png"),
                6,204/6,168/4, Duration.seconds(0.7),18,23);

        acDOWN=new AnimationChannel(FXGL.image("people.png"),
                6,204/6,168/4, Duration.seconds(0.7),0,5);

        acLEFT=new AnimationChannel(FXGL.image("people.png"),
                6,204/6,168/4, Duration.seconds(0.7),6,11);

        acRIGHT=new AnimationChannel(FXGL.image("people.png"),
                6,204/6,168/4, Duration.seconds(0.7),12,17);
        at=new AnimatedTexture(acDOWN);
    }

}
