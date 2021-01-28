public class JLife {

	public final static int MAX_DATA = 10;

	public static User[] UsersData = new User[MAX_DATA];
	public static Session[] SessionsData = new Session[MAX_DATA];

	public static void main(String[] args) {

		User userTest = new User("00000001R"
				,"Luis"
				,"Roca Mora"
				,"Roncal, 10, 30130, Murcia"
				,"luis@gmail.com"
				,"2000-10-12"
				,"2020-10-12"
				,"Miau#12", 
				"REGISTERED"
				);
		
		System.out.println(userTest);
		
		
		loadIntegratedUsers();

		Session session = new Session(null, );	

		if (isLoginOk()) {
		
		}
		
	}

	private static void loadIntegratedUsers() {
		
		
	}

	private static boolean isLoginOk() {


		return false;
	}



}
