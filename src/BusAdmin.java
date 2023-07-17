import java.sql.Connection;
import java.sql.*;
import java.util.*;
public class BusAdmin{
	Scanner sc=new Scanner(System.in);
	Connection con=Sql.getConnection();
	public void addbuses()
	{
		System.out.println("Enter the Bus id");
		int b_id=sc.nextInt();
		String t=sc.nextLine();
		System.out.println("Enter the Bus name");
		String bus_name=sc.nextLine();
		System.out.println("Enter the departure point");
		String dep_stationname=sc.nextLine();
		System.out.println("Enter the destination point");
		String dest_name=sc.nextLine();
		System.out.println("Enter the date");
		System.out.println("Represent date in dd/mm/yyyy proper format");
		String date=sc.nextLine();
		System.out.println("Enter the bus departure time");
		System.out.println("Represent time in 24-hrs format");
		String time1=sc.nextLine();
		System.out.println("Enter the bus destination time");
		System.out.println("Represent time in 24-hrs format");
		String time2=sc.nextLine();
		System.out.println("Select classes:");
		System.out.println("1.AC ClASS");
		System.out.println("2.SLEEPER CLASS");
		System.out.println("3.SEMI-SLEEPER CLASS");
		System.out.println("4.SEATER CLASS");
		String op=sc.nextLine();
		HashMap<String,String> hm=new HashMap<>();
		hm.put("1", "AC CLASS");
		hm.put("2","SLEEPER CLASS");
		hm.put("3","SEMI-SLEEPER CLASS");
		hm.put("4", "SEATER CLASS");
		String str="insert into bus_info values("+b_id+","+"'"+bus_name+"'"+","+"'"+dep_stationname+"'"+","+"'"+dest_name+"'"+","+"'"+time1+"'"+","+"'"+date+"'"+",60,"+"'"+time2+"'"+",'"+hm.get(op)+"')";
		System.out.println(str);
		try{
			Statement stmt=con.createStatement();
			stmt.executeUpdate(str);
			System.out.println("Bus added succesfully");
			}
	    catch(Exception e) {
		  System.out.println(e);
	    }
	}
	public void removebus() {
		System.out.println("Enter the Bus id to delete:");
		int id=sc.nextInt();
		try{
			Statement stmt=con.createStatement();
			stmt.executeUpdate("delete from bus_info where bus_id="+id);
			stmt.executeUpdate("update user_info set Booking_status='cancelled' where bus_id="+id);
			System.out.println("Bus removed successfully");
			}
	    catch(Exception e) {
		  System.out.println(e);
	    }
	}
	public boolean check(int id,String time) {
		try{
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("select bus_id,arrival_time from bus_info where bus_id="+id+" and arrival_time='"+time+"'");
			while(rs.next()) {
				if(id==rs.getInt("id") && time.equals(rs.getString("arrival_time"))) {
					return true;
				}
			}
			}
	    catch(Exception e) {
		  System.out.println(e);
	    }
		return false;
	}
	public void bookings() {
		System.out.println("Enter the bus id:");
		int bus_id=sc.nextInt();
		String k=sc.nextLine();
		System.out.println("Enter the bus time");
		System.out.println("Represent time in 24 hrs proper format");
		String time=sc.nextLine();
		if(check(bus_id,time)) {
			System.out.println("The bookings of the particular bus are:");
			try{
				Statement stmt=con.createStatement();
				int fl=0;
				ResultSet rs=stmt.executeQuery("select username,arrival_time from user_info u,bus_info b where b.bus_name=u.bus_name and b.arrival_time='"+time+"'");
				while(rs.next()) {
					fl=1;
					System.out.println(rs.getString("username")+" "+rs.getString("arrival_time"));
				}
				if(fl==0) {
					System.out.println("No bookings on this particular bus");
				}
				
				}
		    catch(Exception e) {
			  System.out.println(e);
		    }
		}
		else {
			System.out.println("No buses availabale on that time");
		}
	}
}
