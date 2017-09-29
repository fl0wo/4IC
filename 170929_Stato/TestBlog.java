public class TestBlog {
    
	public static void main(String args[]) {
		Blog blog = new Blog();
		User user1 = new User();
		User user2 = new User();
		blog.registerObserver(user1);
		blog.registerObserver(user2);
		user1.setObservable(blog);
		user2.setObservable(blog);
	
		System.out.println(user1.getArticle());		
		blog.postNewArticle();
		System.out.println(user1.getArticle());
	}

}
