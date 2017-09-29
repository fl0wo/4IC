package testdatadownload;
class Helper implements HelperInterface{
    private Thread tn;
    private String name;
    public Helper(Thread currentThread, String name){
	this.tn = currentThread;
	this.name = name;
    }
    @Override
    public void run(){
	if(tn.isAlive())
	System.out.println(name+"(Helper thread): "+tn.getState());
    }
}