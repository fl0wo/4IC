/**
 *
 * @author florian.sabani
 */
public class Max implements Runnable {
	int[] a;
	int inizio,fine,pos;
	Integer[] max;
	
	Max(int[] a, int inizio, int fine, Integer[] max,int pos) {
		this.a = a;
		this.inizio = inizio;
		this.fine = fine;
		this.max = max;
		this.pos = pos;
	}

	@Override
	public void run() {
		int max12 = a[inizio];
		for (int i = inizio+1; i < fine; i++) {
			if(a[i]>max12){
				max12 = a[i];
			}
		}
		max[pos] = max12;
		TrovaMax.finite.countDown();
	}
	
}
