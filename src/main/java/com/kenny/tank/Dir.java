package com.kenny.tank;

import javafx.geometry.Point2D;

public enum Dir {
    //方向向量
    UP(new Point2D(0,-1)),
    DOWN(new Point2D(0,1)),
    LEFT(new Point2D(-1,0)),
    RIGHT(new Point2D(1,0));


    private Point2D vector;
    Dir(Point2D vector){
        this.vector=vector;
    }

    public Point2D getVector() {
        return vector;
    }
}
