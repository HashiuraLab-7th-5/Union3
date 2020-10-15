package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.ArrayList;

import org.eclipse.core.resources.IResource;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.Name;
import org.eclipse.jdt.core.dom.Type;


import Facade.*;
import marker.GetResource;

public class FeatureEvaluation {
	private ArrayList<SaveData> sd = new ArrayList<SaveData>();
	private boolean MainFlag;
//	private SaveData data;


//	FeatureEvaluation(ArrayList<SaveData> data){
//		rootdir.add(bindir);
//		this.sd = data;
//	}

	public FeatureEvaluation() {
////		this.data = data;
//		sd.add(data);
	}
	public void setSaveInstance(SaveData SD) {
		sd.add(SD);
	}
	public void register() {

		Connection conn = null;
		//dbのディレクトリ取得
		String projectName = GetResource.getProjectname();
		IResource resouece = GetResource.getResource(projectName);
		String dbPath= GetResource.getFile(resouece);


	    try {
	        conn = DriverManager.getConnection("jdbc:sqlite:" + dbPath);

	        String sql ="DELETE from FeatureExraction";
	        Statement stmt = conn.createStatement();
	        stmt.executeUpdate(sql);
	        stmt.close();

			Inheritance IE = new Inheritance(sd);
			IE.InheritanceEvaluation();
			Aggregation AE = new Aggregation(sd);
			AE.AggregationEvaluation();
			SuperInstance SI = new SuperInstance(sd);
			SI.SubInstanceEvaluation();
	        Forwarding FD = new Forwarding(sd);
	        FD.ForwardingEvaluation();
	        OverRide OR = new OverRide(sd);
	        OR.OverRideEvaluation();
	        Instantiate IS = new  Instantiate(sd);
	        IS. InstantiateEvaluation();
	        WrapperObject wo = new WrapperObject(sd);
	        wo.WrapperObjectEvaluation();
//	        Normalization nz = new Normalization();
//	        nz.NormalizationEvaluation();


	         sql = "select distinct * from FeatureExraction";
	         PreparedStatement p = conn.prepareStatement(sql);
	            try (ResultSet rs = p.executeQuery()) {
	                while (rs.next()) {
	                    System.out.format("id�F%s", rs.getInt("id") + " ");
	                    System.out.format("FeatureName�F%s", rs.getString("FeatureName") + " ");
	                    System.out.format("Sender�F%s", rs.getString("Sender") + " ");
	                    System.out.format("Sendee�F%s", rs.getString("Sendee") + " ");
	                    System.out.println();
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
