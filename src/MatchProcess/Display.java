package MatchProcess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.eclipse.core.resources.IResource;

import marker.GetResource;

public class Display {
	public void show() {


		Connection conn = null;
		ArrayList<Integer> list = new ArrayList();
		ArrayList<Integer> list2 = new ArrayList();
		ArrayList<Integer> list3 = new ArrayList();
		int cnt = 0;
		int tmp;
		int r;
		String success;

		//dbのディレクトリ取得
		String projectName = GetResource.getProjectname();
		IResource resouece = GetResource.getResource(projectName);
		String dbPath= GetResource.getFile(resouece);


	    try {
	        conn = DriverManager.getConnection("jdbc:sqlite:" + dbPath);

	    	String succ ="select *  from Success ";
	  	      try (PreparedStatement tmt = conn.prepareStatement(succ);) {

		  	      PreparedStatement p = conn.prepareStatement(succ);
		  	      ResultSet re5 = p.executeQuery();
		  	       success = re5.getString("FeatureName");
	  	        }



	    	String sql ="select *  from ErrorSize order by id";
	  	      try (PreparedStatement pstmt = conn.prepareStatement(sql);) {

		  	      PreparedStatement p = conn.prepareStatement(sql);
		  	      ResultSet re = p.executeQuery();
		  	       r = re.getInt("size");
		  	       while(re.next()) {
		  	    	   if(r > re.getInt("size")) {
		  	    		   r = re.getInt("size");
		  	    		   list.clear();
		  	    		   list.add(re.getInt("id"));
		  	    	   }
		  	    	   else if(r == re.getInt("size")) {
		  	    		   list.add(re.getInt("id"));
		  	    	   }

		  	    	 }
	  	        }
	  	     if(success != null) {
	  	    	 System.out.println("no PatternError");
	  	     }
	  	     else {
		  	     System.out.println("����");
		  	     int c = 1;
		  	     for(int i=0;i<list.size();i++) {
		 	        String s ="select Error.id,Error.result,Fix.message from Error,Fix where Error.fix == Fix.id AND Error.id =" + list.get(i);
		 	         PreparedStatement p1 = conn.prepareStatement(s);
		             try (ResultSet rs = p1.executeQuery()) {
		            	 System.out.println("error number: " + c);
		 	        	while (rs.next()) {

		 	                System.out.format("id�F%s", rs.getInt("id") + " ");

		 	                System.out.format(" %s", rs.getString("result") + " ");
		 	               System.out.format("   %s", rs.getString("message") + " ");
		 	                System.out.println();
		 	        	}
		 	        	}
		             c++;
		             System.out.println("------------------------------------------------------------------");
		 	        }
	  	     }

	    } catch (SQLException se) {
	        System.out.println(se.getMessage());
	    } finally {
	        if (conn != null) {
	            try {
	                conn.close();
	            } catch (SQLException e) {
	            }
	        }
	    }
		}


}
