public abstract class MotorSet{
    private int[] motors;
    private boolean enabled = false;
    
    public void enable(){
        Field[] fields = motors.getClass().getDeclaredFields();
        motors.motors = new int[fields.length];
        try{
        	for(int i = 0; i < fields.length; i++){
                motors.motors[i] = ((Motor)fields[i].getObject(motors)).getPin();
                Robot.motors.lock(motors.motors[i]);
        	}
        }catch(Exception e){
                System.out.println("this shouldn't happen");
        }
        motors.enabled = true;
    }
    public void disable(){
        for(int i : motors){
            Robot.motors.unlock(i);
            this.enabled = false;
        }
    }
}