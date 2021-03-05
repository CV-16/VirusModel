package com.uestc.virus.common;

/**
 * 城市描述对象
 *
 * @ClassName: City
 * @Description: 城市描述对象
 * @author: Bruce Young
 * @date: 2020年02月02日 17:48
 */
public class City {
    private double centerX;
    private double centerY;

    public City(double centerX, double centerY) {
        this.centerX = centerX;
        this.centerY = centerY;
    }

    public double getCenterX() {
        return centerX;
    }

    public void setCenterX(int centerX) {
        this.centerX = centerX;
    }

    public double getCenterY() {
        return centerY;
    }

    public void setCenterY(int centerY) {
        this.centerY = centerY;
    }
}
