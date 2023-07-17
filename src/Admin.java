import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
public class Admin extends Sql{
	Scanner sc=new Scanner(System.in);
	String adminname="";
	BusAdmin bus=new BusAdmin();
	public static boolean check(String adminname,String password)
	{
		try {
			Connection con=Sql.getConnection();
			Statement stmt=con.createStatement();
	    	ResultSet rs=stmt.executeQuery("select * from admin_info where adminname='"+adminname+"'");
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
	public void login(){
			boolean flag=false;
			System.out.println("Enter admin name:");
			adminname=sc.nextLine();
			System.out.println("Enter the password");
			String password=sc.nextLine();
			if(check(adminname,password)) {
			System.out.println("Login successful");
			flag=true;
			}
			else {
				System.out.println("Invalid credentials,try again");
			}
			while(flag) {
			System.out.println("Enter 1 to add buses:");
			System.out.println("Enter 2 to remove the buses:");
			System.out.println("Enter 3 to see the bookings of buses");
			System.out.println("Enter 4 to logout");
			int ch=sc.nextInt();
			switch(ch) {
			case 1:
				System.out.println("Adding Buses");
				bus.addbuses();
				break;
			case 2:
				System.out.println("Removing Buses");
				bus.removebus();
				break;
			case 3:
				System.out.println("Booking Buses");
				bus.bookings();
				break;
			case 4:
				System.out.println("Logout successful");
				flag=false;
				break;
			default:
				System.out.println("Invalid");
				break;
			}
			} 
	}
}
