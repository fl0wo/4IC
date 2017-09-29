public class Observer implements Runnable{
    private Thread observed;
    private String lastState;

    public Observer(Thread observed){
        this.observed = observed;
        this.lastState = "";
    }

    @Override
    public void run() {
        while(!lastState.equals("TERMINATED")){
            String state = observed.getState().name();
            if(!lastState.equals(state)){
                System.out.println("[OBSERVER]" + observed.getId() + " changed state from " + lastState + " to " + state);
                lastState = state;
            }
        }
        /*while(!lastState.equals("TERMINATED")){
            while(!lastState.equals(String state = observed.getState().name()))
            if(!lastState.equals(state)){
                System.out.println("[OBSERVER]" + observed.getId() + " changed state from " + lastState + " to " + state);
                lastState = state;
            }
        }*/
    }
}
