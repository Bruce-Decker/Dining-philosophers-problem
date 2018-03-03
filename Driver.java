import java.io.*;
import java.util.*;
public class Driver {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		Scanner output = new Scanner(System.in);
		int philosopher_num = 1;
		long start_time = 0;	
		Driver drive_1 = new Driver();	
	    ArrayList<String> name_list = new ArrayList<String>();
		ArrayList<String> philosopher_list = new ArrayList<String>();
		int user_input = 0;
		do {
			try {
				System.out.println("Which option would you like?");
				System.out.println("Option 1: Input values manually.");
				System.out.println("Option 2: Acquire values from a text file");
				user_input = input.nextInt();
				
			} catch(Exception e) {
				System.out.println("Please enter a valid value");
			}
			input.nextLine();
		} while (user_input != 1 && user_input !=2);
		
		if (user_input == 1) {
			drive_1.option_1(name_list, philosopher_list, input,philosopher_num, start_time, drive_1);
		} else {
			drive_1.option_2(output, philosopher_num, name_list, start_time, philosopher_list, drive_1);
		}
				
	}
	
	public void option_1(ArrayList<String> name_list,ArrayList<String> philosopher_list, Scanner input, int philosopher_num, long start_time, Driver drive_1) {				
          do {
				   try {
		               System.out.println("How many philosophers would you like to add. Please enter a number that is greater or equal to two");
		               philosopher_num = input.nextInt();
				   } catch(Exception e) {
					   System.out.println("Please enter an integer");
					   input.nextLine();
				   }
			   } while (philosopher_num < 2);  
			
			Philosopher[] philosophers = new Philosopher[philosopher_num];
			Chopstick [] chopsticks = new Chopstick[philosopher_num];
			Thread[] threads = new Thread[philosopher_num];
	
		
		       input.nextLine();
		for (int i = 0; i < philosopher_num; i++) {
		
		  if (i == 0) {
			 System.out.println("Please add one philosopher");
			 name_list.add(input.nextLine());
			 //name_list.add("");
		  } else {
			  System.out.println("Please add another philosopher");
			  name_list.add(input.nextLine());
			  //name_list.add("");
		  }
		}
		for (int chop_num = 0; chop_num < philosopher_num; chop_num++) {
			chopsticks[chop_num] = new Chopstick(chop_num);
		}			
		drive_1.philosopher_thread(philosopher_num,philosopher_list, name_list, chopsticks, philosophers, threads,start_time);
	 Iterator<String> iterator = philosopher_list.iterator();			 
	 try {
	 	FileWriter file_writer = new FileWriter("Philosopher List.txt");
	 	PrintWriter p_writer = new PrintWriter(file_writer);	   
	 	while (iterator.hasNext()) {
	 		p_writer.println(iterator.next());
	 	}
	 	p_writer.close();
	 } catch (IOException e) {
	 	System.out.println("Error");
	 }
	}
	
	public void option_2(Scanner output, int philosopher_num, ArrayList<String> name_list, long start_time, ArrayList<String> philosopher_list, Driver drive_1) {
		try {
		    output = new Scanner(new File("/Users/brucedecker/Desktop/Philosophers.txt"));
		} catch(Exception e) {
			System.out.println("cannot find a file");     
		}
		int count = 0;
	    while(output.hasNextLine()) {
	    	name_list.add(output.nextLine());
			count++;
	    }
	    Philosopher[] philosophers = new Philosopher[count];
		Chopstick [] chopsticks = new Chopstick[count];
		Thread[] threads = new Thread[count];
		
	    for (int chop_num = 0; chop_num < count; chop_num++) {
			chopsticks[chop_num] = new Chopstick(chop_num);
		}
	    
	    drive_1.philosopher_thread(count,philosopher_list, name_list, chopsticks, philosophers, threads,start_time);
	}
	
	public void philosopher_thread(int philosopher_num, ArrayList<String> philosopher_list, ArrayList<String> name_list, Chopstick [] chopsticks, Philosopher[] philosophers, Thread[] threads, long start_time) {
		 Iterator<String> itr = name_list.iterator();
			int i = 0;
			
			while (itr.hasNext()) {
				Chopstick left_stick = chopsticks[i];
				Chopstick right_stick = chopsticks[(i+1) % chopsticks.length];
				if (i != philosophers.length - 1) {
				   philosophers[i] = new Philosopher(i, left_stick, right_stick, philosopher_num, philosopher_list);
				} else {
					philosophers[i] = new Philosopher(i, right_stick, left_stick, philosopher_num, philosopher_list);
				}
				philosophers[i].setPhilosopher_name(itr.next()); 
				threads[i] = new Thread(philosophers[i], philosophers[i].getPhilosopher_name() + " Philosopher " + (i+1));
				if (start_time == 0) {
					start_time = System.nanoTime();
					//System.out.println("The starting time is " + start_time);
				}
				threads[i].start();
				i++;
			}
			 try {
				   for (int j = 0; j < philosopher_num; j++) {
				 	  threads[j].join();
				   }
				   System.out.printf("Total elapsed time is %.4f seconds", ((System.nanoTime() - start_time)) / Math.pow(10, 9));
				 } catch(InterruptedException e) {
				 	e.printStackTrace();
				 }
	       }

}
