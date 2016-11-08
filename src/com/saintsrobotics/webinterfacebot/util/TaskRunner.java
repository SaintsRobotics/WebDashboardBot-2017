package com.saintsrobotics.webinterfacebot.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import edu.wpi.first.wpilibj.DriverStation;


public class TaskRunner{
	public List<Task> tasks;
	private boolean disabled = false;
	public TaskRunner(Task[] tasks){
		this.tasks = new LinkedList<Task>();
		this.tasks.addAll(Arrays.asList(tasks));
	}
	public void run() {
		if(disabled) return;
		Iterator<Task> list = tasks.iterator();
        while(list.hasNext()){
            Task task = list.next();
            if(task.iterator == null) task.iterator = task.iterator();
            if(task.waiter != null && task.waiter.getAsBoolean())
                if(task.iterator.hasNext())
                	try{
                    	task.waiter=task.iterator.next();
                	}catch(MotorLockedException e){
                		DriverStation.reportError("Task " + task.getClass().getName() + " Failed Because of Motor Collision!", false);
                	}
                else{
                    list.remove();
                }
        }
	}
	public void disable(){
		this.disabled = true;
	}
}