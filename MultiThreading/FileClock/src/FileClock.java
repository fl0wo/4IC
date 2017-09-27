public class FileClock extends Thread {
    private int clockId;

    public FileClock(int clockId){
        this.clockId = clockId;
    }

    public int getClockId(){
        return clockId;
    }

    private int random(int min, int max) {
        int range = (max - min) + 1;
        return (int)(Math.random() * range) + min;
    }

    @Override
    public void run() {
        while(TestFileClock.threads.size() > 1) {
            try {
                sleep(random(500, 10000));
            } catch (InterruptedException e) {
                System.out.println("[" + clockId + "] Aaaahh!");
                return;
            }
            FileClock vittima = TestFileClock.threads.get(random(0, TestFileClock.threads.size()-1));
            if(vittima.getClockId() != clockId && vittima.isAlive()){
                System.out.println("[" + clockId + "] Muori " + vittima.getClockId() + "!!!");
                vittima.interrupt();
            }
        }
        System.out.println("[" + clockId + "] Ho vintooo!!!!!!!");
    }
}