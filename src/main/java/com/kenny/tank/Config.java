package com.kenny.tank;

import javafx.util.Duration;

public interface Config {
    int SpeedTank=380;
    int SpeedBullet=750;
    int Speedenemybullet=1000;
    Duration Tankshoot= Duration.seconds(2);
    Duration enemy2shooot=Duration.seconds(0.75);

}
