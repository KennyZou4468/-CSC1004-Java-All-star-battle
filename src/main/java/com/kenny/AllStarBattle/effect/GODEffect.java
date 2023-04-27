package com.kenny.AllStarBattle.effect;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.Effect;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import javafx.util.Duration;

public class GODEffect extends Effect {
    //无敌效果
    private AnimatedTexture texture;
    public GODEffect() {
        super(Duration.seconds(12));
        texture=new AnimatedTexture(new AnimationChannel(
                FXGL.image("Props/GODeffect.png"),Duration.seconds(1),4));
    }

    @Override
    public void onStart( Entity entity) {
        //动画
        texture.setTranslateX(entity.getWidth()/2.0-texture.getFitWidth()/2.0);
        texture.setTranslateY(entity.getHeight()/2.0-texture.getFitHeight()/2.0);
        texture.loop();
        entity.getViewComponent().addChild(texture);
   }

    @Override
    public void onEnd( Entity entity) {
        texture.stop();
        entity.getViewComponent().removeChild(texture);
    }

}
