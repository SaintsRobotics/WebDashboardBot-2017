package com.saintsrobotics.util.task;

public class Time{
    private static long startTime;
    private static long lastTime;
    public static void start(){
        startTime = System.currentTimeMillis();
        lastTime = System.currentTimeMillis();
    }
    public void update(){
        lastTime = System.currentTimeMillis();
    }
    public static double deltaSeconds(){
        return (double)deltaMillis()/1000.0;
    }
    public static long deltaMillis(){
        return System.currentTimeMillis() - lastTime;
    }
    public static long totalMillis(){
        return System.currentTimeMillis() - startTime;
    }
    public static double totalSeconds(){
        return (double)totalMillis()/1000.0;
    }
}