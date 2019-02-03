package com.syj.javassist;

/**
 * @author shenyanjun1
 * @description ${description}
 * @create 2019-01-30 15:36
 */
public class Point {
    private int x;
    private int y;

    public Point(){}
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void move(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }

}
