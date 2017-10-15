package task;


import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author FLORIAN.SABANI
 */
public class Clock {
	static long tAttesa; 
	ArrayList<Thread> c;

	public Clock() {
		c = new ArrayList<>();
		tAttesa = 1;
	}
	
	public void addThread(Thread a){
		c.add(a);
	}
	
	public void removeThread(Thread b){
		c.remove(b);
	}
	
	public void aggiorna(){
		System.out.println(Arrays.toString(c.toArray()));
	}
	 
	
}
