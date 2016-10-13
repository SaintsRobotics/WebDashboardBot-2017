package com.saintsrobotics.webinterfacebot.test;

import java.util.Iterator;
import java.util.function.BooleanSupplier;
import java.util.function.IntSupplier;

import com.saintsrobotics.webinterfacebot.util.Task;
import com.saintsrobotics.webinterfacebot.util.TaskRunner;
import com.zoominfo.util.yieldreturn.Generator;

public class Tester{
	public static void main(String[] args){
		Generator<IntSupplier> gen = new Generator<IntSupplier>(){
			public void run(){
				yield(()->{return 8;});
				yield(()->{return 9;});
			}
		};
		Iterator<IntSupplier> it = gen.iterator();
		while(it.hasNext()){
			System.out.println(it.next().getAsInt());
		}
		Task[] tasks = {new Task(){
        	public void run(){
        		System.out.println("It's working!");
        		yield(()->{System.out.println("Returning True");return true;});
        		System.out.println("Never say die");
        		yield(()->{System.out.println("Returning False");return false;});
        		System.out.println("No man shall see this line. But I am gnome ann.");
        	}
        }
    	};
		Iterator<BooleanSupplier> i = tasks[0].iterator();
		while(i.hasNext()){
			System.out.println(i.next().getAsBoolean());
		}
        TaskRunner runner = new TaskRunner(tasks);
        log("runner created");
        while(true){
        	runner.run();
        }
	}
	private static void log(String arg){
		System.out.println(arg);
	}
}