# DataDownloader
###### Why is my connection so slow?!

This program simulates the download of a content from the internet with a thread to establish and disestablish a network connection and another one which sleeps for a few seconds simulating the download of a file.

The goal is to evaluate the various states that the thread passes through, and this is done by another thread in the class **Watcher.java** which contains the following for loop:

```java
for(;;){
   if(!t1.getState().equals(s1)) {
      s1 = t1.getState();
      System.out.println("\t" + t1.getName() + " is " + s1);
   }
   if(!t2.getState().equals(s2)){
      s2 = t2.getState();
      System.out.println("\t" + t2.getName() + " is " + s2);
   }
   try{
      TimeUnit.MILLISECONDS.sleep(10);
   } catch (InterruptedException e) {
      System.out.println("\tI'm no more needed");
      break;
   }
}
```

Every 10ms it checks if the states have changed and eventually prints them.

> What is real?
> How do you define 'real'?
> If you're talking about what you can feel, what you can smell, what you can taste and see, then 'real' is simply electrical signals interpreted by your brain.

*Morpheus, "The Matrix"*
