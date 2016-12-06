public class Time{
    private static long startTime;
    private static long lastTime;
    public static void start(){
        startTime = System.currentTimemillis();
        lastTime = System.currentTimemillis();
    }
    private static void update(){
        lastTime = System.currentTimemillis();
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