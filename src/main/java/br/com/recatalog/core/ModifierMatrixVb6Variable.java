package br.com.recatalog.core;

public class ModifierMatrixVb6Variable {
	//matrix line: modifier
	public static final int NULL = 0;
	public static final int DIM = 1;
	public static final int PRIVATE = 2;
	public static final int PUBLIC = 3;
	public static final int GLOBAL = 4;

	//matrix column: enclosing scope
	public static final int FRM = 0;
	public static final int BAS = 1;
	public static final int CLS = 2;
	public static final int FORMAL_PARAMETER = 3;
	public static final int SUB = 4;
	public static final int FUNCTION = 4;
	public static final int TYPE = 5;
	public static final int ENUM = 6;
	
	//matrix result: resulting scope
	public static final String ERROR    = "ERROR";
	public static final String MODULE  = "MODULE";
	public static final String CURRENT = "CURRENT";
	public static final String PROJECT  = "PROJECT";
	
	public static final String[][] resultScope = new String[][] {
		/*              FRM       BAS      CLS      FORMPARAM   METHOD  TYPE  ENUM   */    
		/* null    */   {PROJECT,  PROJECT,  MODULE,  CURRENT,  MODULE,  MODULE,  MODULE},
		/* Dim     */   {CURRENT, CURRENT, CURRENT, CURRENT,  CURRENT, CURRENT, MODULE},
		/* Private */   {MODULE,  CURRENT, CURRENT, CURRENT,  CURRENT, CURRENT, MODULE},
		/* Public  */   {PROJECT,  PROJECT,  PROJECT,  CURRENT,  ERROR,    ERROR,    ERROR},
		/* Global  */   {PROJECT,  PROJECT,  PROJECT,  CURRENT,  ERROR,    ERROR,    ERROR}
	};
	
	public static void main(String[] args) {
		
		System.err.println(resultScope[DIM][FRM]);
		
	}	
}