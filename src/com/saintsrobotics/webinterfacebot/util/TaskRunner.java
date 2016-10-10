package com.saintsrobotics.webinterfacebot.util;

public class TaskRunner{
	public LinkedList<Task> tasks;

	public TaskRunner(Task[] tasks){
		this.tasks = new LinkedList<Task>(tasks)
	}
	public void run(){
		Iterator<Task> list = tasks.iterator();
        while(list.hasNext()){
            Task task = list.next();
            if(task.waiter != null && task.waiter.done())
                if(task.hasNext())
                    task.waiter=task.next()
                else
                    list.remove()
        }
	}
}