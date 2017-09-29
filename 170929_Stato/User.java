public class User implements Observer {

	private String article;
	private Observable blog; // the observable object

	public void setObservable(Observable blog) {
		this.blog = blog;
		article = "No New State!";
	}

	@Override
	public void update() {
		System.out.println("State change reported by Subject.");
		article = (String) blog.getUpdate();
	}

	public void getStatus(Thread tn) {
		System.out.println(tn.getState());
	}
}
