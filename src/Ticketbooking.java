import java.sql.Connection;
import java.sql.Statement;
import java.util.*;
public class Ticketbooking extends Passenger{
	static Scanner sc=new Scanner(System.in);
	public void book(String username,int b_id,HashMap<String,String> available_buses) {
		System.out.println("Show the available bus timings on the date:");
		available_buses.forEach((k,v)->System.out.println("Bus_name="+k+" Bus_time="+v));
		System.out.println("Enter the Bus_name to book");
		String name=sc.nextLine();
		try {
			Connection con=Sql.getConnection();
			Statement stmt=con.createStatement();
			stmt.executeUpdate("update bus_info set availability=availability-1 where bus_name="+"'"+name+"'");
		    stmt.executeUpdate("update user_info set bus_name="+"'"+name+"'"+" where username="+"'"+username+"'");
		    stmt.executeUpdate("update user_info set bus_id="+b_id+" where username="+"'"+username+"'");
		    stmt.executeUpdate("update user_info set Booking_status='Booked'");
			System.out.println("Booked succesfully");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
