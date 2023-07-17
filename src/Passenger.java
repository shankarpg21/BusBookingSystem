import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
public class Passenger{
	static Scanner sc=new Scanner(System.in);
	static Ticketbooking ticket=new Ticketbooking();
	static HashMap<String,String> available_buses=new HashMap<>();
	int b_id=0;
	Connection con=Sql.getConnection();
	public boolean search(String date,String src,String dest) {
		try {
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("select * from bus_info where src="+"'"+src+"'"+"and dest='"+dest+"' and date='"+date+"' and availability>0");
			while(rs.next()) {
				b_id=rs.getInt("bus_id");
				available_buses.put(rs.getString("bus_name"), rs.getString("arrival_time"));
			}
			if(available_buses.size()>0)return true;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	public boolean search(String date,String src,String dest,String opt) {
		try {
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("select * from bus_info where src="+"'"+src+"'"+"and dest='"+dest+"' and date='"+date+"' and availability>0 and class="+opt);
			while(rs.next()) {
				b_id=rs.getInt("bus_id");
				available_buses.put(rs.getString("bus_name"), rs.getString("arrival_time"));
			}
			if(available_buses.size()>0)return true;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	public void busbooking(String username) {
		System.out.println("Enter the date to travel:");
		System.out.println("Represent date in dd/mm/yyyy proper format");
		String date=sc.nextLine();
		System.out.println("Enter the departure point:");
		String src=sc.nextLine();
		System.out.println("Enter the destination point:");
		String dest=sc.nextLine();
		if(search(date,src,dest)) {
			System.out.println("Filter buses based on classes:");
			System.out.println("1.AC ClASS");
			System.out.println("2.SLEEPER CLASS");
			System.out.println("3.SEMI-SLEEPER CLASS");
			System.out.println("4.SEATER CLASS");
			String opt=sc.nextLine();
			HashMap<String,String> hm=new HashMap<>();
			hm.put("1", "AC CLASS");
			hm.put("2","SLEEPER CLASS");
			hm.put("3","SEMI-SLEEPER CLASS");
			hm.put("4", "SEATER CLASS");
			int k;
			if(search(date,src,dest,hm.get(opt))) {
				k=1;
				System.out.println("Enter 1 to book ticket");	
			}
			else {
				k=2;
				System.out.println("No buses based on your selected class");			
			}
			switch(k) {
			case 1:
				ticket.book(username,b_id,available_buses);
				available_buses.clear();
				break;
			default:
				System.out.println("Cancelling the process");
				System.exit(0);
				break;
			}
		}
		else {
			System.out.println("No buses are available on that date");
		}
	}
	public void view(String username)
	{
		try {
			Statement stmt=con.createStatement();
			String che="";
			ResultSet rs=stmt.executeQuery("select username,e.bus_name,b.bus_id,src,dest,arrival_time,dest_time,date from user_info e inner join bus_info b on e.bus_id=b.bus_id where username="+"'"+username+"'");
			while(rs.next())
			{
				System.out.println("*****Ticket*****");
				System.out.println("Name:"+rs.getString("username"));
				System.out.println("Bus_name:"+rs.getString("bus_name"));
				System.out.println("Bus id:"+rs.getInt("bus_id"));
				System.out.println("From "+rs.getString("src")+" to "+rs.getString("dest"));
				System.out.println("Departure time:"+rs.getString("arrival_time"));
				System.out.println("Destination time:"+rs.getString("dest_time"));
				System.out.println("Date:"+rs.getString("date"));
				System.out.println("*****");
				return;
			}
			rs=stmt.executeQuery("select Booking_status from user_info where username='"+username+"'");
			if(rs.next()) {
			che=rs.getString("Booking_status");
			}
			if(che.equals("cancelled")) System.out.println("Buses is cancelled by administration due to some issues");
			else System.out.println("No bookings made by you");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
