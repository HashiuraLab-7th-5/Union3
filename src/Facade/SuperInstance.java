package Facade;

import java.util.ArrayList;

import org.eclipse.core.resources.IResource;

import marker.GetResource;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import test.SaveData;

public class SuperInstance {
	private static ArrayList<SaveData> sd;


	public SuperInstance(ArrayList<SaveData> sd) {
		this.sd = sd;
	}
	public static void SubInstanceEvaluation() {
		ArrayList<String> abst = new ArrayList<String>();
		ArrayList<String> con = new ArrayList<String>();
		int count = 3;
		Connection conn = null;

		//dbのディレクトリ取得
		String projectName = GetResource.getProjectname();
		IResource resouece = GetResource.getResource(projectName);
		String dbPath= GetResource.getFile(resouece);


	    try {
	        conn = DriverManager.getConnection("jdbc:sqlite:" + dbPath);

//	        String sql ="DELETE from SuperInstance";
//	        Statement stmt = conn.createStatement();
//	        stmt.executeUpdate(sql);
//	        stmt.close();

//	        String s = "select * from Inheritance";
//	        PreparedStatement p = conn.prepareStatement(s);
//	        ResultSet re = p.executeQuery();
//	        while(re.next()) {
//	        	abst.add(re.getString("abstract"));
//	        	con.add(re.getString("concrete"));
//	        }

	        String s = "select * from FeatureExraction";
	        PreparedStatement p = conn.prepareStatement(s);
	        ResultSet re = p.executeQuery();
	        while(re.next()) {
	        	if(re.getString("FeatureName").equals("Inheritance")) {
	        	abst.add(re.getString("Sendee"));
	        	con.add(re.getString("Sender"));
	        	}
	        }


//	        System.out.println("test10: " + con);
//	        System.out.println("test11: " + abst);
//	        System.out.println("test2: " + sd.get(1).getConcreteName().toString());


//	        sql ="insert into SuperInstance(id,base,factory,start,end) VALUES(?,?,?,?,?)";
	        String sql ="insert into FeatureExraction(id,FeatureName,Sender,Sendee) VALUES(?,?,?,?)";
	        try (PreparedStatement pstmt = conn.prepareStatement(sql);) {

	    		for(int i = 0;i<sd.size();i++) {
	    			for(int j=0;j<sd.get(i).getReturnDate().size();j++) {
	    				if(con.size() >= 2)
	    				for(int z=0;z<con.size();z++) {

	    				if(sd.get(i).getClassName().toString().equals(con.get(z))&&
	    						sd.get(i).getReturnDate().get(j).toString().contains("new")&&
	    						sd.get(i).getReturnDate().get(j).toString().contains(con.get(z+1))) {

	    					pstmt.setInt(1, count);
	    					pstmt.setString(2, "SuperInstance");
//	    					pstmt.setString(3, sd.get(i).getClassName().toString());
//	    					pstmt.setString(4, sd.get(j).getClassName().toString());

	    					pstmt.setString(3, con.get(z).toString());
	    					pstmt.setString(4, con.get(z+1).toString());


//	    					pstmt.setInt(1, count);
//	    					pstmt.setString(2, sd.get(i).getClassName().toString());
//	    					pstmt.setString(3, sd.get(j).getClassName().toString());
//	    					pstmt.setInt(4, sd.get(j).getSourceStart());
//	    					pstmt.setInt(5, sd.get(j).getSourceEnd());
//	    					count++;
	    					pstmt.executeUpdate();
	    				}
//	    				else
//	    					 bindir.add(new File(sd.get(i).getAbstractName(),1,sd.get(j).getClassName().toString(),2,false));
	    				}
	    			}
	    			if(abst.equals(sd.get(i).getAggregationMethodName())) {
	    				for(int j=0;j<sd.get(i).getAggregationMethodVariableName().size();j++) {
	    					if(con.size() >= 2)
	    	    				for(int z=0;z<con.size();z++) {
	    					if(sd.get(i).getAggregationMethodVariableName().get(j).equals(con.get(z))&&
	    							sd.get(i).getAggregationMethodVariableName().get(j+1).toString().contains("new")&&
	    							sd.get(i).getAggregationMethodVariableName().get(j+1).toString().contains((con.get(z+1)))) {

		    					pstmt.setInt(1, count);
		    					pstmt.setString(2, "SuperInstance");
//		    					pstmt.setString(3, sd.get(i).getClassName().toString());
//		    					pstmt.setString(4, sd.get(j).getClassName().toString());

		    					pstmt.setString(3, con.get(z).toString());
		    					pstmt.setString(4, con.get(z+1).toString());


//	    						pstmt.setInt(1, count);
//		    					pstmt.setString(2, sd.get(i).getClassName().toString());
//		    					pstmt.setString(3, sd.get(j).getClassName().toString());
//		    					pstmt.setInt(4, sd.get(j).getSourceStart());
//		    					pstmt.setInt(5, sd.get(j).getSourceEnd());
//		    					count++;
		    					pstmt.executeUpdate();
	    					}
	    	    				}
	    				}
	    			}
	    			else {
	    				for(int j=0;j<sd.get(i).getAggregationMethodVariableName().size();j++) {
	    					if(con.size() >= 2)
	    	    				for(int z=0;z<con.size();z++) {
	    					if(sd.get(i).getAggregationMethodVariableName().get(j).equals(abst)&&
	    							sd.get(i).getAggregationMethodVariableName().get(j+1).toString().contains("new")&&
	    							sd.get(i).getAggregationMethodVariableName().get(j+1).toString().contains(con.get(z))) {

		    					pstmt.setInt(1, count);
		    					pstmt.setString(2, "SuperInstance");
//		    					pstmt.setString(3, sd.get(i).getClassName().toString());
//		    					pstmt.setString(4, sd.get(j).getClassName().toString());

		    					pstmt.setString(3, con.get(z).toString());
		    					pstmt.setString(4, con.get(z+1).toString());


//	    						pstmt.setInt(1, count);
//		    					pstmt.setString(2, sd.get(i).getAbstractName().toString());
//		    					pstmt.setString(3, sd.get(j).getClassName().toString());
//		    					pstmt.setInt(4, sd.get(j).getSourceStart());
//		    					pstmt.setInt(5, sd.get(j).getSourceEnd());
//		    					count++;
		    					pstmt.executeUpdate();
	    					}
	    	    				}
	    				}
	    			}
	    		}
//	         sql = "select * from SuperInstance";
//	         PreparedStatement pp = conn.prepareStatement(sql);
//	            try (ResultSet rs = pp.executeQuery()) {
//	                while (rs.next()) {
//	                    System.out.format("id�F%s ", rs.getInt("id"));
//	                    System.out.format("base�F%s ", rs.getString("base"));
//	                    System.out.format("factory�F%s ", rs.getString("factory"));
//	                    System.out.format("start�F%s ", rs.getInt("start"));
//	                    System.out.format("end�F%s ", rs.getInt("end"));
//	                    System.out.println();
//	                }
//	            }
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
