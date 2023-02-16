package com.kenny.tank;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.ProjectileComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.physics.BoundingShape;
import com.kenny.tank.components.TankComponent;

public class TankFactory implements EntityFactory {
    @Spawns("enemy")
         public Entity enemy1(SpawnData data){
             return FXGL.entityBuilder(data)
                     //设置外观和大小
                     .type(Gametype.ENEMY)
                     .with( new TankComponent())
                     .viewWithBBox("new.png")
                     .with(new CollidableComponent(true))
                     .build();
    }
    @Spawns("creper")
           public Entity player1(SpawnData data) {
        return FXGL.entityBuilder(data)
                //设置外观和大小
                .type(Gametype.PLAYER1)
                .with(new TankComponent())
                .viewWithBBox("CrepeerFinal.png")
                .with(new CollidableComponent(true))
                .build();
    }
             @Spawns("Tank")
        public Entity player2(SpawnData data){
            return FXGL.entityBuilder(data)
                    //设置外观和大小
                    .type(Gametype.PLAYER2)
                    .with(new TankComponent())
                    .viewWithBBox("TankFinal.png")
                    .with(new CollidableComponent(true))
                    .build();
    }





    @Spawns("Caseblock")
    public Entity newCaseblock(SpawnData data){
            return FXGL.entityBuilder(data)
                    .type(Gametype.CASEBLOCK)
                    .viewWithBBox("element/Case-block.png")
                    .collidable()
                    .build();
        }
    @Spawns("Luckyblock")
    public Entity newLuckyblock(SpawnData data){
        return FXGL.entityBuilder(data)
                .type(Gametype.LUCKYBLOCK)
                .viewWithBBox("element/Lucky-block.png")
                .collidable()
                .build();
    }
    @Spawns("Stoneblock")
    public Entity newStoneblock(SpawnData data){
        return FXGL.entityBuilder(data)
                .type(Gametype.STONEBLOCK)
                .viewWithBBox("element/Stone-block.png")
                .collidable()
                .build();
    }
    @Spawns("Unbreakablewall")
    public Entity newUnbreakablewall(SpawnData data){
        return FXGL.entityBuilder(data)
                .type(Gametype.UNBREAKABLEWALL)
                .viewWithBBox("element/Unbreakable-wall.png")
                .collidable()
                .build();
    }
    @Spawns("Wall")
    public Entity newWall(SpawnData data){
        return FXGL.entityBuilder(data)
                .type(Gametype.WALL)
                .viewWithBBox("element/Wall.png")
                .collidable()
                .build();
    }


}
