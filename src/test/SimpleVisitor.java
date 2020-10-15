package test;


import java.awt.List;
import java.util.ArrayList;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.AnnotationTypeMemberDeclaration;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.BlockComment;
import org.eclipse.jdt.core.dom.CastExpression;
import org.eclipse.jdt.core.dom.CharacterLiteral;
import org.eclipse.jdt.core.dom.ClassInstanceCreation;
import org.eclipse.jdt.core.dom.ConstructorInvocation;
import org.eclipse.jdt.core.dom.EnumConstantDeclaration;
import org.eclipse.jdt.core.dom.ExpressionMethodReference;
import org.eclipse.jdt.core.dom.ExpressionStatement;
import org.eclipse.jdt.core.dom.FieldAccess;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.Initializer;
import org.eclipse.jdt.core.dom.IntersectionType;
import org.eclipse.jdt.core.dom.LabeledStatement;
import org.eclipse.jdt.core.dom.MemberValuePair;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.Modifier;
import org.eclipse.jdt.core.dom.Modifier.ModifierKeyword;
import org.eclipse.jdt.core.dom.ModuleDeclaration;
import org.eclipse.jdt.core.dom.Name;
import org.eclipse.jdt.core.dom.NameQualifiedType;
import org.eclipse.jdt.core.dom.ParameterizedType;
import org.eclipse.jdt.core.dom.PostfixExpression;
import org.eclipse.jdt.core.dom.PrefixExpression;
import org.eclipse.jdt.core.dom.PrimitiveType;
import org.eclipse.jdt.core.dom.ProvidesDirective;
import org.eclipse.jdt.core.dom.QualifiedType;
import org.eclipse.jdt.core.dom.RequiresDirective;
import org.eclipse.jdt.core.dom.ReturnStatement;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.SimpleType;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.SuperFieldAccess;
import org.eclipse.jdt.core.dom.SuperMethodInvocation;
import org.eclipse.jdt.core.dom.SuperMethodReference;
import org.eclipse.jdt.core.dom.Type;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.TypeParameter;
import org.eclipse.jdt.core.dom.UnionType;
import org.eclipse.jdt.core.dom.UsesDirective;
import org.eclipse.jdt.core.dom.VariableDeclarationExpression;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.eclipse.jdt.core.dom.VariableDeclarationStatement;

public class SimpleVisitor extends ASTVisitor{
	private static final TypeDeclaration SimpleType = null;
	public  boolean ss;
	private int i =  0;
	private boolean s1 ;
	private boolean s2;
	private String filename;
	private Type supperName;
	private String ClassName;
	private String st;
	private boolean flag;
	private boolean flag2;
	private Type Block;

	ArrayList<SaveData> Data = new ArrayList<SaveData>();
	
	SaveData SD = new SaveData();

	public SimpleVisitor(String filename) {
		this.filename = filename;
		int index = filename.indexOf(".");
		 st = filename.substring(0,index );

		
		System.out.println("filename12: " + st);
		SD.setAbstractName(st);

		Data.add(SD);
		
	}
	
	
	public SaveData getSD() {
		return SD;
	}
//	 void report(
//             Kind kind,
//             String message,
//             ASTNode node,
//             int offset,
//             int length) {
//         MyProblem prom = new MyProblem(kind, message, filename);
//         int start = node.getStartPosition() + offset;
//         int end = start + length;
//         prom.setSourceStart(start);
//         prom.setSourceEnd(end - 1);
////         prom.setSourceLineNumber(root.getLineNumber(start));
//         problems.add(prom);
//     }
    public boolean visit(TypeDeclaration node) {
        // クラス名を変更する
        setClassName(node , st);
        // 親クラスを変更する
        setSuperClass(node, "java.applet.Applet");
        // インターフェースを変更する
//        setSuperInterfaces(node, "java.io.Serializable", "PiyoPiyo");
        
        
        return super.visit(node);
    }
    public boolean visit(SimpleName node) {
    	
    	System.out.println("SimpleName: " + node.getIdentifier());
    	
    	 return super.visit(node);
    	 
    }

    
    public boolean visit(NameQualifiedType node) {
    	System.out.println("TypeParameter: " + node.getName());
//    	System.out.println("TypeParameter2: " + node.modifiers());
    	return super.visit(node);
    }



	public boolean visit(Modifier node) {

		i++;
		String st = "hey";
		if(node.isAbstract() == true /*&& filename.equals(this.supperName)*/ ) {
			SD.setAbstractBool(node.isAbstract());
//			report(
//	                Kind.ERROR,
//	                "errorメソッドを発見",
//	                name,
//	                0,
//	                name.getLength());
//			imp1.Register("AbstractF", node.isAbstract(), 1,imp1);
//			imp1.View();
						
		}
		System.out.println(" ");
		System.out.println("Modifier"+ i + ":" + node.isAbstract());
		System.out.println("ALL Date"+ i + ":" + node.getKeyword());
//		System.out.println("ALL Date2-"+ i + ":" + node.getKeyword().toKeyword(filename));
//		System.out.println("Inheritance Date"+ i + ":" + node.getRoot());
		System.out.println(" ");
		return super.visit(node);
	}
    private void setSuperClass(TypeDeclaration node, String superClassName) {
//        AST ast = node.getAST();
//        Name name = ast.newName(superClassName);
//        Type superClassType = ast.newSimpleType(name);
        
        this.supperName = node.getSuperclassType();
        Object s = node.getSuperclassType();
        
//        System.out.println("SuperClass: " + supperName);
//        if(s !=null)	SD.setConcreteName(s); /*this.flag2 = true;*/
//        else			SD.setConcreteName("fal");
        System.out.println("test: " + node.superInterfaceTypes());
		try {
			if(s != null) SD.setExtendName(s);
//			else throw new NullPointerException();
			if(node.superInterfaceTypes() != null) SD.setImplementName(node.superInterfaceTypes());
		}catch(NullPointerException e) {
			e.printStackTrace();
		}

        
    }
    private void setClassName(TypeDeclaration node , String string) {
    	
        AST ast = node.getAST();
//        Name name = ast.newName(string);
        SimpleName simplename = ast.newSimpleName(string);
//        node.setName(simplename);
       
//        Object s = simplename.getFullyQualifiedName();
//        ASTNode s;
//        System.out.println("test: " + s);
        
		try {
			if(node.getName() != null) SD.setClassName(node.getName());
//			else throw new NullPointerException();
		}catch(NullPointerException e) {
			e.printStackTrace();
		}
        
        
    }
//    public boolean visit(MethodDeclaration node) {
//        SimpleName name = node.getName();
//        if (name.getIdentifier().startsWith("error")) {
//            report(
//                Kind.ERROR,
//                "errorメソッドを発見",
//                name,
//                0,
//                name.getLength());
//        }
//        return true;
//    }
//	@SuppressWarnings("deprecation")
	public boolean visit(MethodDeclaration node) {
//		this.flag = true;

		System.out.println("constructor:" + "  " + node.toString());
		System.out.println("constructor2:" + "  " + node.modifiers());
		System.out.println("constructor3:" + "  " + node.getName().getIdentifier());
		System.out.println("constructor4:" + "  " + node.parameters());
		System.out.println("constructor5:" + "  " + node.getReceiverQualifier());
		System.out.println("constructor6:" + "  " + node.getReceiverType());
		System.out.println("constructor7:" + "  " + node.getReturnType2());
//		List list = new List() {};
		SD.setMethodModifier(node.modifiers());
		SD.setMethodName(node.getName().getIdentifier());
		try {
			if(node.getReturnType2() != null && node.parameters() != null &&node.getName().getIdentifier() != null && node.modifiers() != null) 
				SD.setAggregationMethodName(node.getReturnType2(),node.parameters(),node.getName().getIdentifier(),node.modifiers());
//			else throw new NullPointerException();
//			else SD.setAggregationMethodName(,(java.util.List) list);
		}catch(NullPointerException e) {
			e.printStackTrace();
		}
		
		this.ClassName = node.getName().getIdentifier();
		System.out.println("constructor8:" + "  " + node.properties());
		System.out.println("constructor9:" + "  " + node.resolveBinding());
		System.out.println("constructor10:" + "  " + node.structuralPropertiesForType());
		System.out.println("constructor11:" + "  " + node.thrownExceptionTypes());
		System.out.println("constructor12:" + "  " + node.typeParameters());
		return super.visit(node);
	}
	
//    public boolean visit(SimpleType node) {
//    	Name name = node.getName();
////    	if(/*flag != true &&*/supperName != null &&supperName.toString() != name.toString()) 
//    	if(flag2 == true) this.flag2 = false;
//    	else{
//    		
//    			SD.setAggregationVariableName(name);
//    		
//    	
//    	System.out.println("SimpleType: " + node.getName());
//    	
//    	}
////    	if(Block != null)
////    	System.out.println("test: " + Block.getNodeType());
//    	return super.visit(node);
//    }
    
    public boolean visit(ExpressionStatement node) {
    	System.out.println("ExpressionStatement1: " + node.getExpression());
    	
//    	System.out.println("ExpressionStatement2: " + node);
    	SD.setMethodExpression(node.getExpression());
    	return super.visit(node);
    }
    

    
    //this,super
    public boolean visit(FieldAccess node) {
    	System.out.println("FieldAccess1: " + node.getExpression());
    	System.out.println("FieldAccess2: " + node.getName());
//    	System.out.println("FieldAccess3: " + node.propertyDescriptors(0));
//    	System.out.println("FieldAccess4: " + node.resolveFieldBinding());
    	return super.visit(node);
    }
    public boolean visit(FieldDeclaration node) {
    	System.out.println("FieldDeclaration1: " + node.fragments());
    	Type type = node.getType();
    	int Start = node.getStartPosition();
    	int End = Start + type.getLength() - 1;
    	
    	System.out.println("FieldDeclaration1 - Start: " + Start + " End: " + End );
    	
    	System.out.println("FieldDeclaration2: " + node.getType());
    	SD.setAggregationVariableName(node.getType() , node.fragments());
    	SD.setSourceStart(Start);
    	SD.setSourceEnd(End);
    	return super.visit(node);
    }
    public boolean visit(SingleVariableDeclaration node) {
    	System.out.println("SingleVariableDeclaration1: " + node.getType());
    	System.out.println("SingleVariableDeclaration2: " + node.getModifiers());
    	System.out.println("SingleVariableDeclaration3: " + node.isVarargs());
    	System.out.println("SingleVariableDeclaration4: " + node.modifiers());
    	return super.visit(node);
    }
    public boolean visit(RequiresDirective node) {
    	System.out.println("RequiresDirective1: " + node.getName());
    	System.out.println("RequiresDirective2: " + node.modifiers());
    	return super.visit(node);
    }
    
	public boolean visit(VariableDeclarationExpression node) {
		System.out.println("variable " + node.getModifiers());
		
		System.out.println("variable-type " + node.getType());
		System.out.println("variable-type2 " + node.modifiers());
		return super.visit(node);
	}
	
	public boolean visit(VariableDeclarationFragment node) {
//		System.out.println("variable-type3 " + node.propertyDescriptors(1));
		
		return super.visit(node);
	}
	public boolean visit(CastExpression node) {
		System.out.println("CastExpression1: " + node.getExpression());
		System.out.println("CastExpression2: " + node.getType());
		
		
		return super.visit(node);
	}
	
	public boolean visit(ReturnStatement node) {
		System.out.println("ReturnStatement: " + node.getExpression());
		try {
			if(node.getExpression() != null) SD.setReturnDate(node.getExpression());;
//			else throw new NullPointerException();
		}catch(NullPointerException e) {
			e.printStackTrace();
		}
		
		
		return super.visit(node);
	}

	
	public boolean visit(VariableDeclarationStatement node) {
		System.out.println("variable-type4 " + node.getType());
//		System.out.println("variable-type5 " + node.modifiers());
		System.out.println("variable-type6 " + node.fragments());
		
		SD.setAggregationMethodVariableName(node.getType(), node.fragments());
//		java.util.List list = node.fragments();
//		if(list.toString().contains("new")) System.out.println("Instance - succes");
		
		return super.visit(node);
	}

	public boolean visit(Block node) {
		System.out.println("Block:" + "  " + node.getParent());
		System.out.println("Block1:" + "  " + node);
		
		SD.setBlock(node.getParent());
		SD.setInBlock(node);
//		AST ast = node.getAST();
//		int index = node.toString().indexOf("{");
//		int indexEnd = node.toString().indexOf("}");
//		String st = node.toString().substring(index+1,indexEnd );
//		Name name = ast.newSimpleName("st");
//		this.Block = ast.newSimpleType(name);
//		System.out.println("Block in Type: " + node.statements());
//		this.Block = (List) node.statements();
		
		return super.visit(node);
	}

//	public boolean visit(ExpressionMethodReference node) {
//		System.out.println("Block2:" + node.toString());
//		
//		System.out.println("ExpressionMethodReference1:" + node.getExpression());
//		System.out.println("ExpressionMethodReference2:" + node.getName());
//		System.out.println("ExpressionMethodReference3:" + node.typeArguments());
//		return super.visit(node);
//	}
	
//	public void endvisit(SuperMethodReference node) {
//		System.out.println("SupperName Data: " + node.typeArguments());
//		System.out.println("SupperName Data1: " + node);
//	}
//	public void endvisit( Modifier node) {
//		this.s2 = node.isAbstract();
//		System.out.println("supper:" + "  " + node.	isAbstract());
//		System.out.println("supper1:" + "  " + node.isModifier());
//		System.out.println("supper2:" + "  " + node.toString());
//		
//	}
	
//	public boolean visit(SingleVariableDeclaration node) {
//		Type type = node.getType();
//		ITypeBinding bind = type.resolveBinding();
////		String fullyQualifiedClassName = bind.getQualifiedName();
////		IType type = (IType) bind.getJavaElement();
//
//		System.out.println("test11:" + bind.getQualifiedName());
//		return super.visit(node);
//	}
	

}
