import java.util.reflection;
public abstract class MotorSet{
    private int[] motors;
    private boolean enabled = false;
    
    public void enable() throws MotorLockedException{
        Field[] fields = motors.getClass().getDeclaredFields();
        int[] motorsTemp = new int[fields.length];
        for(int i = 0; i < fields.length; i++){
            motorsTemp[i] = ((Motor)fields[i].getObject(motors)).getPin();
            if(Motors.locks[motorsTemp[i]]) throw new MotorLockedException();
        }
        for(int i : motorsTemp){
            Motors.lock(i);
        }
        this.motors = motorsTemp;
        motors.enabled = true;
    }
    public void disable(){
        for(int i : motors){
            Motors.unlock(i);
            this.enabled = false;
        }
    }
}