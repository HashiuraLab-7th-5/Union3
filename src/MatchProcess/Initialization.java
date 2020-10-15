package MatchProcess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;

import org.eclipse.core.resources.IResource;

import marker.GetResource;

public class Initialization {

	public void initaialization(){

		Connection conn = null;

		//dbのディレクトリ取得
		String projectName = GetResource.getProjectname();
		IResource resouece = GetResource.getResource(projectName);
		String dbPath= GetResource.getFile(resouece);


	    try {
	        conn = DriverManager.getConnection("jdbc:sqlite:" + dbPath);

	        String sw ="DELETE from Error";
	        Statement sst = conn.createStatement();
	        sst.executeUpdate(sw);
	        sst.close();

	        String se ="DELETE from ErrorSize";
	        Statement ssst = conn.createStatement();
	        ssst.executeUpdate(se);
	        ssst.close();



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
