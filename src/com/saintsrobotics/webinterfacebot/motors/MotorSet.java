import java.util.reflection;
public abstract class MotorSet{
    private int[] motors;
    private boolean enabled = false;
    
    public void enable(){
        Field[] fields = motors.getClass().getDeclaredFields();
        int[] motorsTemp = new int[fields.length];
        try{
        	for(int i = 0; i < fields.length; i++){
                motorsTemp[i] = ((Motor)fields[i].getObject(motors)).getPin();
                Robot.motors.lock(motors.motors[i]);
        	}
        }catch(Exception e){
                System.out.println("this shouldn't happen");
        }
        this.motors = motorsTemp;
        motors.enabled = true;
    }
    public void disable(){
        for(int i : motors){
            Robot.motors.unlock(i);
            this.enabled = false;
        }
    }
}