import java.util.Scanner;
public class Main {
	static User user=new User();
	static Admin admin=new Admin();
	public static void main(String args[]) {
		Scanner sc=new Scanner(System.in);
		boolean end=true;
		System.out.println("********");
		System.out.println("Important: All inputs are case sensitive need to be accurate for doing operations");
		System.out.println("********");
		while(end) {
			System.out.println("Enter the person who you are");
			System.out.println("Enter 1 for User");
			System.out.println("Enter 2 for Admin");
			System.out.println("Enter any number to quit the application");
			int key=sc.nextInt();
			switch(key)
			{
			case 1:
				System.out.println("Welcome user");
				System.out.println("Enter 1 for Login");
				System.out.println("Enter 2 for Signup");
				int ch=sc.nextInt();
				String y=sc.nextLine();
				switch(ch) {
				case 1:
					user.login();
					break;
				case 2:
					user.signup();
					break;
				default:
					System.out.println("Invalid option");
					break;
				}
				break;
			case 2:
				System.out.println("Welcome Admin");
				System.out.println("Enter 1 to login");
				System.out.println("Enter any number to quit the process");
				int ch1=sc.nextInt();
				switch(ch1) {
				case 1:
					admin.login();
					break;
				default:
					break;
				}
				break;
			default:
				System.out.println("Application closed");
				end=false;
				break;
		}
	}
	
}
}
