package database;


import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.Map;

import org.eclipse.core.resources.IFile;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.runtime.CoreException;


import marker.GetResource;
import property.ViolationProperty;


public class SQLiteSample extends ClassLoader{

	private InputStream inputStream = null;
	private String databaseName = "union_test";
	private String dbPath;
    private Connection conn = null;
    private Statement statement = null;



	public void conectSample() throws IOException {

		//データベース接続のためのディレクトリを取得
		String projectName = GetResource.getProjectname();
		IResource resouece = GetResource.getResource(projectName);
		dbPath = GetResource.getFile(resouece);

	}

	//接続をしてテーブルの中身を取得
	public ArrayList<Map<String,String>> getTable(String tableName) {
		String[] kolum = {"id","errorcode","classname","start","end"}; //�@union_test
		ArrayList<Map<String,String>> tableData = new ArrayList<Map<String,String>>();	//�ۊǂ��Ă������


		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e1) {
			// TODO �����������ꂽ catch �u���b�N
			e1.printStackTrace();
		}

        try {
        	System.out.println("SQLiteSampledbのパス" + dbPath);
			conn = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
            statement = conn.createStatement();


            String sql = "select * from " + databaseName + ";";
            ResultSet rs = statement.executeQuery(sql);

            //Map�̂̕������₷������
            int columCount = rs.getMetaData().getColumnCount();	//�e�[�u���̗�̐�
            while(rs.next()) {

            	Map<String,String> data = new HashMap<String,String>();
            	for(int num = 0; num < columCount; num++) {
            		data.put(kolum[num],rs.getString(kolum[num]));
            	}
            	tableData.add(data);

            }

        } catch (SQLException se) {
            System.out.println(se.getMessage());
            System.out.println("SQLException");

        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                	System.out.println("SQLException");
                }
            }
        }

        return tableData;
	}


	protected String findLibraty(String libname) {

		return super.findLibrary(libname);

	}

}

