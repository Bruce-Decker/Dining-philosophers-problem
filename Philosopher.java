import java.util.*;
import java.io.*;
public class Philosopher implements Runnable  {
	enum Philosopher_Action {
		Thinking, 
		Hungary,
		Eating
	}
	private Chopstick left_stick;
	private Chopstick right_stick;
	private int philosopher_id;
	private String philosopher_name;
	private long start_time = 0;
	private long end_time = 0 ;
	private int philosopher_num;
	public ArrayList<String> philosopher_list;
	public Philosopher(int philosopher_id, Chopstick left_stick, Chopstick right_stick, int philosopher_num, ArrayList<String> philosopher_list) {
		this.philosopher_id = philosopher_id;
		this.left_stick = left_stick;
		this.right_stick = right_stick;
		this.philosopher_num = philosopher_num;
		this.philosopher_list = philosopher_list;
	}
	Philosopher_Action current_action = Philosopher_Action.Hungary;
	public void run() {
		try {   
		       System.out.println(Thread.currentThread().getName() + ": Thinking");
		       philosopher_list.add(Thread.currentThread().getName() + ": Thinking");
		       
		       Thread.sleep((int) (Math.random() * 100));
		       synchronized (left_stick) {
			        System.out.println(Thread.currentThread().getName() + " Picked up the left chopstick");
			        philosopher_list.add(Thread.currentThread().getName() + " Picked up the left chopstick");
			        
			        Thread.sleep((int) (Math.random() * 100));
			        synchronized(right_stick) {
				         System.out.println((Thread.currentThread().getName() + " Pick up the right chopstick and start eating"));
				         philosopher_list.add((Thread.currentThread().getName() + " Pick up the right chopstick and start eating"));
				         
				         Thread.sleep((int) (Math.random() * 100));
				         System.out.println(Thread.currentThread().getName() + " Put down the right chopstick");
				         philosopher_list.add(Thread.currentThread().getName() + " Put down the right chopstick");
				         
				         Thread.sleep((int) (Math.random() * 100));
			        }
			        System.out.println((Thread.currentThread().getName() + " Put down the left chopstick. Back to thinking"));
			        philosopher_list.add((Thread.currentThread().getName() + " Put down the left chopstick. Back to thinking"));
			        
			        Thread.sleep((int) (Math.random() * 100));	   
		  }
		  
		} catch(Exception e) {
			Thread.currentThread().interrupt();
			return;
		}
	}
	public void setPhilosopher_name(String philosopher_name ) {
		this.philosopher_name = philosopher_name;
	}
	
	public String getPhilosopher_name() {
		return philosopher_name;
	}
	
	public int getPhilosopher_id() {
		return philosopher_id;
	}
	
	public Chopstick getLeftChopStick() {
		return left_stick;
	}
	
	public Chopstick getRightChopStick() {
		return right_stick;
	}
}
