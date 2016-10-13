package com.saintsrobotics.webinterfacebot.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;



public class TaskRunner{
	public LinkedList<Task> tasks;

	public TaskRunner(Task[] tasks){
		this.tasks = new LinkedList<Task>();
		this.tasks.addAll(Arrays.asList(tasks));
	}
	public void run(){
		Iterator<Task> list = tasks.iterator();
        while(list.hasNext()){
            Task task = list.next();
            if(task.iterator == null) task.iterator = task.iterator();
            if(task.waiter != null && task.waiter.getAsBoolean())
                if(task.iterator.hasNext())
                    task.waiter=task.iterator.next();
                else
                    list.remove();
        }
	}
}