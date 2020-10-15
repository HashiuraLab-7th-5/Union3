package Facade;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.eclipse.core.resources.IResource;

import marker.GetResource;

public class Normalization {
	public static void NormalizationEvaluation() {
		Connection conn = null;

		//dbのディレクトリ取得
		String projectName = GetResource.getProjectname();
		IResource resouece = GetResource.getResource(projectName);
		String dbPath= GetResource.getFile(resouece);


	    try {
	        conn = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
	        //"././Feature/test.sqlite3"
	        //G://SqlLite/test.sqlite3

	        String sql ="delete from FeatureExraction where (id,FeatureName,Sender,Sendee)IN("
	        		+ "select tmp1.id as id,tmp1.FeatureName as FeatureName,tmp1.Sender as Sender,tmp1.Sendee as Sendee"
	        		+ "from( select max(id) as id,Feature,Sender,Sendee from FeatureExraction group by id )as tmp1)";
	        Statement stmt = conn.createStatement();
	        stmt.executeUpdate(sql);
	        stmt.close();

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
