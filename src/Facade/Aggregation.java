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

import test.FeatureEvaluation;
import test.SaveData;

public class Aggregation {
	private static ArrayList<SaveData> sd;
//	File file =new File("./AstAspect/Feature/Aggregation.txt");
//	FileWriter filewriter = new FileWriter(file);
	public Aggregation(ArrayList<SaveData> sd2) {
		this.sd = sd2;

	}
	public static void AggregationEvaluation() {
		String[] data = {"boolean","byte","shot","int","long","float","double","String","char"};

		ArrayList methoddata = new ArrayList();
		ArrayList methodname = new ArrayList();
		ArrayList block = new ArrayList();
		ArrayList AVN = new ArrayList();
		int count = 2;
		Connection conn = null;
		ArrayList MExS = new ArrayList();
		ArrayList MExE = new ArrayList();

		//dbのディレクトリ取得
		String projectName = GetResource.getProjectname();
		IResource resouece = GetResource.getResource(projectName);
		String dbPath= GetResource.getFile(resouece);


	    try {
	        conn = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
	        //"././Feature/test.sqlite3"
	        //G://SqlLite/test.sqlite3


//	        String sql ="DELETE from Aggregation";
//	        Statement stmt = conn.createStatement();
//	        stmt.executeUpdate(sql);
//	        stmt.close();

//	        sql ="insert into Aggregation(id,whole,part,start,end) VALUES(?,?,?,?,?)";
	        String sql ="insert into FeatureExraction(id,FeatureName,Sender,Sendee) VALUES(?,?,?,?)";
	        try (PreparedStatement pstmt = conn.prepareStatement(sql);) {




	    		//AggregationVariableName
	    		for(int i = 0;i<sd.size();i++) {

//	    			while(it.hasNext()) {
//	    			for(int j = 0;j<sd.get(i).getAggregationVariableName().size();j++) {
//	    				if(sd.get(i).getClassName().equals(sd.get(i).getAggregationVariableName().get(j))&&
//	    						!sd.get(i).getAggregationVariableName().get(j).toString().contains("new")) {

	    			for(int j = 0;j<sd.size();j++) {
	    				for(int z = 0;z<sd.get(i).getAggregationVariableName().size();z+=2) {

	    				if( (sd.get(i).getAggregationVariableName().get(z).equals(sd.get(j).getClassName().toString())&&
	    						!sd.get(i).getAggregationVariableName().get(z+1).toString().contains("new") )
	    						 ) {

	    					pstmt.setInt(1, count);
	    					pstmt.setString(2, "Aggregation");
	    					pstmt.setString(3, sd.get(i).getAbstractName().toString());
	    					pstmt.setString(4, sd.get(j).getAbstractName().toString());


//
//	    					pstmt.setInt(1, count);
//	    					pstmt.setString(2, sd.get(i).getAbstractName().toString());
//	    					pstmt.setString(3, sd.get(j).getAbstractName().toString());
//	    					pstmt.setInt(4, sd.get(j).getSourceStart());
//	    					pstmt.setInt(5, sd.get(j).getSourceEnd());
//	    					count++;
	    					pstmt.executeUpdate();


//	    					System.out.println("succes2 : " + sd.get(i).getClassName() + " " + sd.get(j).getAggregationVariableName());
	    				}
	    				}
	    				}
//	    			for(int j = 0;j<sd.get(i).getMethodExpression().size();j++) {
//	    				String filename = sd.get(i).getMethodExpression().get(j).toString();
//	    				int index = filename.indexOf("=");
//	    				int index2 = filename.indexOf(".");
//	    				int end = filename.length();
//	    				String sts = filename.substring(index2+1,index+1 );
//	    				String ste = filename.substring(index+1,end );
//	    				MExS.add(sts);
//	    				MExE.add(ste);
//	    				System.out.println("test20 : " + MExS);
//	    				System.out.println("test21 : " + MExE);
//	    			}

//	    			for(int j = 0;j<sd.get(i).getAggregationVariableName().size();j+=2) {
//    				String filename = sd.get(i).getAggregationVariableName().get(j).toString();
//    				if(filename.contains("[")) {
//    				int index = filename.indexOf("[");
//    				int index2 = filename.indexOf("<");
//    				int end = filename.length();
//    				String sts = filename.substring(0,index);
////    				String ste = filename.substring(index+1,end );
//    				MExS.add(sts);
////    				MExE.add(ste);
////    				System.out.println("test20 : " + MExS);
////    				System.out.println("test21 : " + MExE);
//    				}
//    			}
	    			//atode
	    			for(int j = 0;j<sd.size();j++) {
	    				for(int k=0;k<sd.get(i).getAggregationMethodName().size();k +=4) {
//	    					for(int z = 0;z<sd.get(i).getAggregationVariableName().size();z+=2) {
//	    						for(int n=0;n<sd.get(i).getAggregationMethodVariableName().size();n++) {
//	    					System.out.println("test210 : " + methodname);
//	    					System.out.println("test211 : " + sd.get(i).getAggregationMethodName());

//	    						if(!sd.get(i).getAggregationMethodName().get(k+2).toString().contains(methodname.toString())) {
	    				if(sd.get(i).getAggregationMethodName().get(k).equals(sd.get(j).getClassName().toString()) ||
	    						sd.get(i).getAggregationMethodName().get(k+1).toString().contains(sd.get(j).getClassName().toString()))  {
//	    					System.out.println("test210 : ");
//	    					for(int t=0;t<sd.size();t++) {

	    					methodname.add(sd.get(i).getAggregationMethodName().get(k+2));
//	    					System.out.println("test210 : " + methodname);
//	    					System.out.println("test210 : " + sd.get(i).getAggregationMethodName().get(k+2));
	    					String name = sd.get(i).getAggregationMethodName().get(k+1).toString();
	    					if(name.contains(sd.get(j).getClassName().toString())) {
//	    						int index = name.indexOf(sd.get(j).getClassName().toString());
	    						int index2 = sd.get(j).getClassName().length();
	    						int end = name.length();
	    						String sts = name.substring(index2+2,end-1);
	    						methoddata.add(sts);

//	    						System.out.println("test210 : " + sts);
	    					}
	    					else {
	    						for(int d=0;d<data.length;d++) {
	    							if(name.contains(data[d])) {
	    								int index = data[d].length();
	    								int end = name.length();
	    	    						String sts = name.substring(index+2,end-1);
//	    	    						System.out.println("test210 : " + sts);
	    	    						methoddata.add(sts);
	    							}
	    						}
	    					}

	    					for(int z = 0;z<sd.get(i).getAggregationVariableName().size();z+=2) {
	    						String s = sd.get(i).getAggregationVariableName().get(z+1).toString();
//	    						System.out.println("test209 : " + s);
	    						if(s.contains("=")) {
	    							int index = s.indexOf("=");
	    							String sts = s.substring(0,index);
	    							AVN.add(sts);
	    						}
	    						else
	    							AVN.add(s);
	    					}

//	    					System.out.println("test209 : " + sd.get(j).getClassName().toString());
	    					for(int w=0;w<sd.get(i).getBlock().size();w++) {
	    						String st = sd.get(i).getBlock().get(w).toString();
	    						if(st.contains("(")) {
	    							int index = st.indexOf("(");
	    							String sts = st.substring(0,index);
//	    							System.out.println("test208 : " + sts);
	    							if(sts.contains(sd.get(i).getAggregationMethodName().get(k+2).toString())) {

	    								for(int n=0;n<sd.get(i).getMethodExpression().size();n++) {
	    									if(sd.get(i).getBlock().get(w).toString().contains(sd.get(i).getMethodExpression().get(n).toString())) {
//	    										System.out.println("test209 : ");
	    										block.add(sd.get(i).getMethodExpression().get(n).toString());
	    				    					for(int u=0;u<block.size();u++) {
	    				    						for(int z = 0;z<AVN.size();z++) {
	    				    							for(int d=0;d<methoddata.size();d++) {

//	    				    					System.out.println("test208 : " + AVN.get(z));
//	    				    					System.out.println("test209 : " + methoddata);
	    				    					if((block.get(u).toString().contains("=")||block.get(u).toString().contains("add"))&&
	    				    							block.get(u).toString().contains(AVN.get(z).toString())&&
	    				    							block.get(u).toString().contains(methoddata.get(d).toString())) {

//	    				    						System.out.println("test220 : ");
	    					    					pstmt.setInt(1, count);
	    					    					pstmt.setString(2, "Aggregation");
	    					    					pstmt.setString(3, sd.get(i).getClassName().toString());
	    					    					pstmt.setString(4, sd.get(j).getClassName().toString());
	    					    					pstmt.executeUpdate();
//	    				    					}
	    				    					}
	    				    							}
	    				    					}
	    				    					}
	    									}
	    								}
	    							}

	    					}
	    					}

//	    					System.out.println("test210 : " + block);
//	    					for(int u=0;u<block.size();u++) {
//	    						for(int z = 0;z<sd.get(i).getAggregationVariableName().size();z+=2) {
//	    							for(int d=0;d<methoddata.size();d++) {
//
//
//	    					if((block.get(u).toString().contains("=")||block.get(u).toString().contains("add"))&&
//	    							block.get(u).toString().contains(sd.get(i).getAggregationVariableName().get(z+1).toString())&&
//	    							block.get(u).toString().contains(methoddata.get(d).toString())) {
//
//	    						System.out.println("test220 : ");
//		    					pstmt.setInt(1, count);
//		    					pstmt.setString(2, "Aggregation");
//		    					pstmt.setString(3, sd.get(i).getClassName().toString());
//		    					pstmt.setString(4, sd.get(j).getClassName().toString());
//		    					pstmt.executeUpdate();
////	    					}
//	    					}
//	    							}
//	    					}
//	    					}
	    				}
	    						}
	    						//&&
	    						/*(sd.get(i).getAggregationVariableName().get(z+1).toString().contains(sd.get(i).getAggregationMethodVariableName().get(n).toString())/*||
	    								sd.get(i).getAggregationMethodName().get(k+1).toString().contains(sd.get(i).getMethodExpression().get(n).toString()) ))
	    								|| MExS.contains(sd.get(i).getAggregationMethodVariableName().get(n).toString())*/


//	    					}
	    					}

//	    				}
	    			//kokomade
//	    				else
//	    				templete.add(new File(sd.get(i).getAbstractName(),1,sd.get(j).getAbstractName().toString(),2,false));
	    			}
	    			// AggregationMethodName
//	    			System.out.println("Size: " + sd.get(i).getClassName()+ " , " +sd.get(i).getAggregationMethodName().size());
//	    			for(int j=0;j<sd.size();j++) {
//	    				if(i!=j) {
//	    				for(int z=0;z<sd.get(j).getAggregationMethodName().size();z++) {
////	    					System.out.println("Data: " + sd.get(9).getClassName());
//
//	    					if(sd.get(i).getClassName() != null &&
//	    							sd.get(i).getClassName().equals(sd.get(j).getAggregationMethodName().get(z))/*||
//	    							sd.get(j).getAggregationMethodName().get(z).toString().contains(sd.get(i).getAbstractName().toString())*/) {
//
//
//		    					pstmt.setInt(1, count);
//		    					pstmt.setString(2, "Aggregation");
//		    					pstmt.setString(3, sd.get(j).getAbstractName().toString());
//		    					pstmt.setString(4, sd.get(i).getAbstractName().toString());
//
//
////		    					pstmt.setInt(1, count);
////		    					pstmt.setString(2, sd.get(i).getAbstractName().toString());
////		    					pstmt.setString(3, sd.get(j).getAbstractName().toString());
////		    					pstmt.setInt(4, sd.get(j).getSourceStart());
////		    					pstmt.setInt(5, sd.get(j).getSourceEnd());
////		    					count++;
//		    					pstmt.executeUpdate();
//
////	    						System.out.println("succes3 : " + sd.get(i).getClassName() + " " + sd.get(j).getClassName());
//
//	    					}
////	    					else
////	    						templete.add(new File(sd.get(i).getAbstractName(),1,sd.get(j).getAbstractName().toString(),2,false));
//
//	    				}
//	    				}
//	    			}
//
//	    			//AggregationMethodVariableName
//	    			for(int j=0;j<sd.size();j++) {
//	    				if(i!=j) {
//	    				for(int z=0;z<sd.get(j).getAggregationMethodVariableName().size();z++) {
//	    					if(sd.get(i).getClassName() != null &&
//	    							sd.get(i).getClassName().equals(sd.get(j).getAggregationMethodVariableName().get(z))&&
//	    							!sd.get(j).getAggregationMethodVariableName().get(z).toString().contains("new")&&
//	    							!sd.get(j).getMethodName().contains("main")) {
//
//
//		    					pstmt.setInt(1, count);
//		    					pstmt.setString(2, "Aggregation");
//		    					pstmt.setString(3, sd.get(j).getAbstractName().toString());
//		    					pstmt.setString(4, sd.get(i).getAbstractName().toString());
//
//
////		    					pstmt.setInt(1, count);
////		    					pstmt.setString(2, sd.get(i).getAbstractName().toString());
////		    					pstmt.setString(3, sd.get(j).getAbstractName().toString());
////		    					pstmt.setInt(4, sd.get(j).getSourceStart());
////		    					pstmt.setInt(5, sd.get(j).getSourceEnd());
////		    					count++;
//		    					pstmt.executeUpdate();
//
////	    						System.out.println("succes4 : " + sd.get(i).getClassName() + " " + sd.get(j).getClassName());
//
//
////	    					}
//	    					}
//	    	//				else
//	    	//					templete.add(new File(sd.get(i).getAbstractName(),1,sd.get(j).getAbstractName().toString(),2,false));
//	    				}
//	    				}
//	    			}



//	         sql = "select * from Aggregation";
//	         PreparedStatement p = conn.prepareStatement(sql);
//	            try (ResultSet rs = p.executeQuery()) {
//	                while (rs.next()) {
//	                    System.out.format("id�F%s", rs.getInt("id") + " ");
//	                    System.out.format("whole�F%s", rs.getString("whole") + " ");
//	                    System.out.format("part�F%s", rs.getString("part") + " ");
//	                    System.out.format("start�F%s", rs.getInt("start") + " ");
//	                    System.out.format("end�F%s", rs.getInt("end"));
//	                    System.out.println();
//	                }
//	            }
//	            pstmt.close();
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
