package com.kenny.tank;

import javafx.geometry.Point2D;
import javafx.util.Duration;

public interface Config {
    int SpeedTank=380;
    int SpeedBullet=750;
    int Speedenemybullet=1000;
    Duration Tankshoot= Duration.seconds(1.5);
    Duration peoplegood= Duration.seconds(1);
    Duration enemy2shooot=Duration.seconds(0.75);
    Duration spawnegg=Duration.seconds(5);
    Duration eggbreak=Duration.seconds(6);

    int Tank_Max_Level=1;
    int Tank_Min_Level=0;

    Point2D[] Spawnenemy=new Point2D[]{
            new Point2D(1604,825),
            new Point2D(540,408),
            new Point2D(1021,1050),
            new Point2D(925,96),
    };
    Point2D[] Spawnchicken=new Point2D[]{
            new Point2D(1917,380),
            new Point2D(775,641),
            new Point2D(191,1020),
            new Point2D(125,381),
    };

    int Max_Level=2;

}
