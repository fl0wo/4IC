import java.util.concurrent.TimeUnit;

class StateWatch extends Thread {
	private State oldState;
    private Thread t;
    
    StateWatch(Thread t, String name){
        super(name);
        this.t = t;
    }
    
    @Override
    public void run() {
        while(oldState!= State.TERMINATED){
			if(oldState!=t.getState()){
				oldState=t.getState();
				System.out.print(t.getName()+" state: "+t.getState()+"\n");
			}
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}		
    }
    
}
