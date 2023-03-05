package com.kenny.tank.effect;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.Effect;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.texture.Texture;
import com.almasb.fxgl.time.LocalTimer;
import javafx.util.Duration;

public class PortalEffect extends Effect {
    private Texture texture;
    public PortalEffect() {
        super(Duration.seconds(10));
        texture=FXGL.texture("Props/PORTIONeffect.png");
    }

    @Override
    public void onStart( Entity entity) {
        texture.setTranslateX(entity.getWidth()/2.0-texture.getWidth()/2.0);
        texture.setTranslateY(entity.getHeight()/2.0-texture.getHeight()/2.0);
        entity.getViewComponent().addChild(texture);
   }

    @Override
    public void onEnd( Entity entity) {
        entity.getViewComponent().removeChild(texture);
    }

}
