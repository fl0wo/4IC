public class TestFileClock {
    public static void main(String[] args) {
        int nThread = 5;
        Thread[] threads = new Thread[nThread];
        for (int i=0; i<threads.length; i++)
            threads[i] = new Thread(new FileClock(threads, i));
        
        for(Thread thread:threads) {
            thread.start();
        }
    }
}