package connplantsv1.connplantsv1;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class ShowMIISchJobList {

	public String displayMIISchJobs(List<MIISchJob> listMIISchJobs1) throws IOException{
		String result="";
		try{  
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection( "jdbc:mysql://connplantsdb:3306/smmtdb?user=root&password=VySU8WBweuVYNx3T&useSSL=false");  
			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("SELECT * FROM `SHOP_ORDER`");  
			while(rs.next()){  
				
			
				
				String site = rs.getString(1);
				String shoporder = rs.getString(2);
				String item = rs.getString(3);
				String qty_to_build = rs.getString(4);
				String qty_done = rs.getString(5);
				String qty_scrapped = rs.getString(6);
				String modified_dt = rs.getString(7);
				
				listMIISchJobs1.add(new MIISchJob(site, shoporder, item, qty_to_build, qty_done, qty_scrapped, modified_dt));
			}
			result = "SUCCESS";
			con.close();
			//return result;
		}
		catch(Exception e){
			e.printStackTrace();
			result = "ERROR";
		}
		//System.out.println(e);
		return result;

	}
}
