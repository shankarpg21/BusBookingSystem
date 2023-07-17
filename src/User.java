import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
public class User extends Sql{
	Scanner sc=new Scanner(System.in);
	Passenger passenger=new Passenger();
	String username="";
	public static boolean check(String username,String password)
	{
		try {
			Connection con=Sql.getConnection();
			Statement stmt=con.createStatement();
	    	ResultSet rs=stmt.executeQuery("select * from user_info where username='"+username+"'");
	    	String pw_from_table="";
		    while(rs.next()) {
		    	pw_from_table=rs.getString("password");
			    if(pw_from_table.equals(password)) {
			    	return true;
			    }
			    else{
			    	return false;
			    	}
			    }
		    }
		catch(Exception e) {
			System.out.println(e);
			}
		return false;
	}
	public static boolean not_available(String username,String password)
	{
		try {
			Connection con=Sql.getConnection();
			Statement stmt=con.createStatement();
	    	ResultSet rs=stmt.executeQuery("select * from user_info where username= ' "+username+"'");
		    if(rs.next()) {
			    return false;
	     	}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return true;
	}

	public void login(){
			boolean end=false;
			System.out.println("Enter username:");
			username=sc.nextLine();
			System.out.println("Enter the password");
			String password=sc.nextLine();
			if(check(username,password)) {
			System.out.println("Login successful");
			end=true;
			}
			else {
				System.out.println("Invalid credentials,Try again");
			}
			while(end) {
			System.out.println("Enter 1 to search buses");
			System.out.println("Enter 2 to view the bus tickets booked by user");
			System.out.println("Enter 3 to logout");
			int ch=sc.nextInt();
			switch(ch) {
			case 1:
				passenger.busbooking(username);
				break;
			case 2:
				passenger.view(username);
				break;
			case 3:
				System.out.println("Logout successful");
				end=false;
				break;
			default:
				System.out.println("Invalid");
				break;
			}
			} 
	}
	public void signup() {
			System.out.println("Welcome, Create an account to book the ticket");
			System.out.println("Enter username:");
			String username=sc.nextLine();
			System.out.println("Enter the password");
			String password=sc.nextLine();
			if(not_available(username,password)) {
				String ste="insert into user_info values("+"'"+username+"'"+","+"'"+password+"'"+",'Not Booked'"+",null"+",null)";
				try{
					Connection con=Sql.getConnection();
					Statement stmt=con.createStatement();
					stmt.executeUpdate(ste);
					System.out.println("Account created,Login account to book the ticket");
					login();
					}
			    catch(Exception e) {
				  System.out.println(e);
			    }
			}
			else {
				System.out.println("User name already exists");
			}
		}
}
