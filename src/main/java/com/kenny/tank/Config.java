package com.kenny.tank;

import javafx.geometry.Point2D;
import javafx.util.Duration;

public interface Config {
    int SpeedTank=380;
    int SpeedBullet=750;
    int Speedenemybullet=1000;
    Duration Tankshoot= Duration.seconds(2);
    Duration enemy2shooot=Duration.seconds(0.75);

    int Tank_Max_Level=1;
    int Tank_Min_Level=0;

    Point2D[] Spawnenemy=new Point2D[]{
            new Point2D(1604,825),
            new Point2D(540,408),
            new Point2D(1021,1050),
            new Point2D(925,96),
    };


}
