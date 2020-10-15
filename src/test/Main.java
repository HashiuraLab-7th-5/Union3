package test;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.apache.commons.io.FileUtils;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.ISourceRange;
import org.eclipse.jdt.core.ISourceReference;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.internal.core.nd.field.FieldList;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import MatchProcess.Display;
import SelectPattern.*;
import marker.GetResource;
import views.View;

public class Main {
	private String s1;
	private int lim;


    public void process() throws IOException, PartInitException {

    	View view = (View) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView("Union3.ErrorView");
		String directory = view.getDirectory();

		System.out.println("ディレクトリの選択" + directory);
    	File file = new File(directory);
    	File filelist[] = file.listFiles();

        final ASTParser parser = ASTParser.newParser(AST.JLS10);
        parser.setKind(ASTParser.K_COMPILATION_UNIT);

        FeatureEvaluation FE = new FeatureEvaluation();

        for(int i=0; i<filelist.length; i++) {
        parser.setSource(
                FileUtils.readFileToString(filelist[i], "UTF-8")
                        .toCharArray());
        parser.setResolveBindings(true);
        System.out.println("FileName:" + filelist[i].getName());

        final CompilationUnit node = (CompilationUnit) parser.createAST(new NullProgressMonitor());
        final SimpleVisitor visitor = new SimpleVisitor(filelist[i].getName());
        node.accept(visitor);
        FE.setSaveInstance(visitor.getSD());

        System.out.println("NodeTest: " +  node);

        }
        FE.register();

        // �I�������p�^�[��������ꏊ
        String test = view.getSelectPattern();
        System.out.println("Main select Pattern Name" + test);


        Map<Integer,Strategy> selectpattern = new HashMap<>();
        selectpattern.put(2, new SelectPattern_2());
        selectpattern.put(3, new SelectPattern_3());
        selectpattern.put(4, new SelectPattern_4());
        selectpattern.put(5, new SelectPattern_5());


        // Select Pattern save in DB
        Pattern_IO pio = new Pattern_IO();
        pio.p_write(test);


        SelectPattern_DB(test);
        Select s = new Select(selectpattern.get(lim));
        s.fileprocess(filelist);
        s.ncombination();
        s.provisionalnumber();

        Display dis = new Display();
        dis.show();

    }

    public void SelectPattern_DB(String pattern) {
		Connection conn = null;

		//dbのディレクトリ取得
		String projectName = GetResource.getProjectname();
		IResource resouece = GetResource.getResource(projectName);
		String dbPath= GetResource.getFile(resouece);


	    try {
	        conn = DriverManager.getConnection("jdbc:sqlite:" + dbPath);

	        String sql ="select * from PatternInformation";
	        PreparedStatement p = conn.prepareStatement(sql);
	        ResultSet re = p.executeQuery();
	        while(re.next()) {
	        	if(re.getString("DesignPattern").equals(pattern)) {
	        		this.lim=re.getInt("Lim");
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