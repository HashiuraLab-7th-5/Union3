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

public class Inheritance {
	private static ArrayList<SaveData> sd;
	public Inheritance(ArrayList<SaveData> sd) {
		this.sd = sd;
	}

	@SuppressWarnings("unlikely-arg-type")
	public static void InheritanceEvaluation() {
		int count = 1;
		Connection conn = null;
		//dbのディレクトリ取得
		String projectName = GetResource.getProjectname();
		IResource resouece = GetResource.getResource(projectName);
		String dbPath= GetResource.getFile(resouece);


	    try {
	        conn = DriverManager.getConnection("jdbc:sqlite:" + dbPath);


	        String s ="DELETE from Inheritance";
	        Statement stmt = conn.createStatement();
	        stmt.executeUpdate(s);
	        stmt.close();

//	        s ="insert into Inheritance(id,abstract,concrete,start,end) VALUES(?,?,?,?,?)";
	        String sql ="insert into FeatureExraction(id,FeatureName,Sender,Sendee) VALUES(?,?,?,?)";
	        try (PreparedStatement pstmt = conn.prepareStatement(sql);) {

	    		for(int i = 0;i<sd.size();i++) {
	    			for(int j=0;j<sd.size();j++) {
	    				if(sd.get(i).getClassName().equals(sd.get(j).getExtendName()) && sd.get(j).getExtendName() != null ) {

	    					pstmt.setInt(1, count);
	    					pstmt.setString(2, "Inheritance");
	    					pstmt.setString(3, sd.get(j).getClassName().toString());
	    					pstmt.setString(4, sd.get(i).getClassName().toString());



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

//	    			System.out.println("test2: " + sd.get(i).getImplementName());
	    			for(int j=0;j<sd.size();j++) {
//	    				for(int z=0;z<sd.get(j).getImplementName().size();z++) {
	    				if(sd.get(j).getImplementName().toString().contains(sd.get(i).getClassName()) && sd.get(j).getImplementName() !=null) {

	    					pstmt.setInt(1, count);
	    					pstmt.setString(2, "Inheritance");
	    					pstmt.setString(3, sd.get(j).getClassName().toString());
	    					pstmt.setString(4, sd.get(i).getClassName().toString());


//	    					pstmt.setInt(1, count);
//	    					pstmt.setString(2, sd.get(i).getClassName().toString());
//	    					pstmt.setString(3, sd.get(j).getClassName().toString());
//	    					pstmt.setInt(4, sd.get(j).getSourceStart());
//	    					pstmt.setInt(5, sd.get(j).getSourceEnd());
//	    					count++;
	    					pstmt.executeUpdate();
//	    					System.out.println("succes");
//	    				}
	    				}
//	    				else
//	    					 bindir.add(new File(sd.get(i).getAbstractName(),1,sd.get(j).getClassName().toString(),2,false));

	    			}
	    		}
//	         sql = "select * from Inheritance";
//	         PreparedStatement p = conn.prepareStatement(sql);
//	            try (ResultSet rs = p.executeQuery()) {
//	                while (rs.next()) {
//	                    System.out.format("id�F%s", rs.getInt("id") + " ");
//	                    System.out.format("abstract�F%s", rs.getString("abstract") + " ");
//	                    System.out.format("concrete�F%s", rs.getString("concrete") + " ");
//	                    System.out.format("start�F%s", rs.getInt("start") + " ");
//	                    System.out.format("end�F%s", rs.getInt("end"));
//	                    System.out.println();
//	                }
//	            }
	        }

//	        try (PreparedStatement pstmt = conn.prepareStatement(s);) {
//
//	    		for(int i = 0;i<sd.size();i++) {
//	    			for(int j=0;j<sd.size();j++) {
//	    				if(sd.get(i).getClassName().equals(sd.get(j).getExtendName()) && sd.get(j).getExtendName() != null ) {
//
//	    					pstmt.setInt(1, count);
//	    					pstmt.setString(2, sd.get(i).getClassName().toString());
//	    					pstmt.setString(3, sd.get(j).getClassName().toString());
//	    					pstmt.setInt(4, sd.get(j).getSourceStart());
//	    					pstmt.setInt(5, sd.get(j).getSourceEnd());
//	    					count++;
//	    					pstmt.executeUpdate();
//	    				}
//    			}
//	    			for(int j=0;j<sd.size();j++) {
////	    				for(int z=0;z<sd.get(j).getImplementName().size();z++) {
//	    				if(sd.get(j).getImplementName().toString().contains(sd.get(i).getClassName()) && sd.get(j).getImplementName() !=null) {
//
//	    					pstmt.setInt(1, count);
//	    					pstmt.setString(2, sd.get(i).getClassName().toString());
//	    					pstmt.setString(3, sd.get(j).getClassName().toString());
//	    					pstmt.setInt(4, sd.get(j).getSourceStart());
//	    					pstmt.setInt(5, sd.get(j).getSourceEnd());
//	    					count++;
//	    					pstmt.executeUpdate();
//
////	    				}
//	    				}
//
//
//	    			}
//	    		}
//	        }



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
