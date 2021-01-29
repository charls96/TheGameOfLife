import java.time.LocalTime;
import java.util.Scanner;

public class JLife {

	public final static int MAX_DATA = 10;
	private static final int MAX_ATTEMPTS = 3;

	private static User[] UsersData = new User[MAX_DATA];
	private static Session[] SessionsData = new Session[MAX_DATA];

	private static Session session; 	
	private static User user;
	
	public static void main(String[] args) {			
		loadIntegratedUsers();		
		if (Login()) {
			session = new Session(user);
			System.out.println("Sessión iniciada...");
			
			// Mostrar demo simulación
			
			session.setEndTime(LocalTime.now().toString());
			SessionsData[0] = session;
			System.out.println(session);
			
			System.exit(0);
		}
	}

	private static boolean Login() {
		Scanner keyboard = new Scanner(System.in);
		int attempts = MAX_ATTEMPTS;

		do {
			// Pide usuario y contraseña.
			System.out.print("Usuario: ");
			String id = keyboard.nextLine();
			System.out.print("Clave de acceso: ");
			String password = keyboard.nextLine();

			// Busca en el almacén el usuario coincidente con las credenciales.
			user = UsersData[0];
	
			if (user != null 
					&& user.getPassword().equals(password)) {
				return true;
			} 
			else {
				attempts--;
				System.out.print("Credenciales incorrectas: ");
				System.out.println("Quedan " + attempts + " intentos... ");
			} 
		} while (attempts > 0);

		return false;
	}
	
	private static void loadIntegratedUsers() {
		UsersData[0] = new User("00000000T"
				,"Admin"
				,"Admin Admin"
				,"La Iglesia, 0, 30012, Patiño"
				,"admin@gmail.com"
				,"2000-01-14"
				,"2021-01-14"
				,"Miau#00", 
				"REGISTERED"
				);
		UsersData[1] = new User("00000001R"
				,"Guest"
				,"Guest Guest"
				,"La Iglesia, 0, 30012, Patiño"
				,"guest@gmail.com"
				,"2000-01-14"
				,"2021-01-14"
				,"Miau#00", 
				"REGISTERED"
				);
	}

	
}
