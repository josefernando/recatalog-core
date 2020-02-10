package br.com.recatalog.core.visualbasic6;

import javax.naming.OperationNotSupportedException;

import org.antlr.v4.runtime.ParserRuleContext;

import br.com.recatalog.core.Symbol;
import br.com.recatalog.core.SymbolFactory;
import br.com.recatalog.util.BicamSystem;
import br.com.recatalog.util.PropertyList;

public class SymbolFactoryVisualBasic6  implements SymbolFactory{
	public static final String APPLICATION = "APPLICATION";
	public static final String ALIAS = "ALIAS";
	public static final String CLASS = "CLASS";
	public static final String CURSOR = "CURSOR";
	public static final String FUNCTION = "FUNCTION";
	public static final String FIELD = "FIELD";


	public static final String PRIMITIVE_TYPE = "PRIMITIVE_TYPE";
	public static final String LIBRARY = "LIBRARY";
	
	public static final String DATABASE = "DATABASE";
	public static final String MODULE = "MODULE";
	public static final String MODULE_FRM = "MODULE_FRM";
	public static final String MODULE_BAS = "MODULE_BAS";
	public static final String MODULE_CLS = "MODULE_CLS";


	public static final String TRANSACTION = "TRANSACTION";
	public static final String INDEX = "INDEX";
	public static final String SERVER = "SERVER";
	public static final String COLUMN = "COLUMN";
	public static final String TABLE = "TABLE";
	public static final String USERDB = "USERDB";
	public static final String COMPILATION_UNIT = "COMPILATION_UNIT";
	public static final String COMPILATION_UNIT_CLASS = "COMPILATION_UNIT_CLASS";

	public static final String BUILTIN = "BUILTIN";
	public static final String PRE_DEFINED = "PRE_DEFINED";
	public static final String REFERENCE = "REFERENCE";
	public static final String VARIABLE = "VARIABLE";
	public static final String LABEL = "LABEL";
	public static final String IMPLICIT = "IMPLICIT";

	public static final String MEMBER_INFER = "MEMBER_INFER";
	public static final String GUI = "GUI";
	public static final String GUI_ATTRIBUTE = "GUI_ATTRIBUTE";
	public static final String GUI_PROPERTY = "GUI_PROPERTY";
	
	public static final String TYPE = "TYPE";
	public static final String ENUM = "ENUM";
	public static final String METHOD = "METHOD";
	public static final String STRUCTURE = "STRUCTURE";
	public static final String STORED_PROCEDURE = "STORED_PROCEDURE";
	public static final String GLOBAL_SCOPE = "GLOBAL_SCOPE";
	public static final String LOCAL_SCOPE = "LOCAL_SCOPE";
	public static final String VISIBILITY_SCOPE = "VISIBILITY_SCOPE";
	
	public Symbol getSymbol(PropertyList _properties){
		String symType = (String)_properties.getProperty("SYMBOL_TYPE").toString();
		switch (symType){
	        case PRIMITIVE_TYPE:
	    	return createPrimitiveTypeSymbol(_properties);	
	        case CLASS:
	    	return createClassSymbol(_properties);
	        case VARIABLE:
	    	return createVariableSymbol(_properties);
	        case FUNCTION:
	    	return createFunctionSymbol(_properties);	    	
	        case LIBRARY:
	    	return createLibrarySymbol(_properties);	
	        case IMPLICIT:
	    	return createImplicitSymbol(_properties);
			case MODULE:
				return createModuleSymbol(_properties);
			case MODULE_FRM:
				return createModuleFrmSymbol(_properties);
			case MODULE_BAS:
				return createModuleBasSymbol(_properties);
			case MODULE_CLS:
				return createModuleClsSymbol(_properties);				
			case BUILTIN:
				return createBuiltinSymbol(_properties);
			case TYPE:
				return createTypeSymbol(_properties);
			case ENUM:
				return createEnumSymbol(_properties);
			case GUI:
				return createGuiSymbol(_properties);
			case GUI_ATTRIBUTE:
				return createGuiAttributeSymbol(_properties);	
			case GUI_PROPERTY:
				return createGuiPropertySymbol(_properties);				
			case METHOD:
				return createMethodSymbol(_properties);
			case LABEL:
				return createLabelSymbol(_properties);				
			case STORED_PROCEDURE:
				return createStoredProcedureSymbol(_properties);				
			case GLOBAL_SCOPE: // è tratadp como symbol...
				return createGlobalScope(_properties);				
			case VISIBILITY_SCOPE: // è tratadp como symbol...
				return createVisibilityScope(_properties);				
		default:
			throw new RuntimeException("** Error*** - Invalid Symbol type to create: "  
					+ ((ParserRuleContext)_properties.getProperty("CONTEXT")).getClass().getSimpleName()
					+ " SymbolType: " + symType);			
		}
	}
	
	private Symbol createModuleSymbol(PropertyList _properties){
		ModuleSymbol sym = new ModuleSymbol(_properties);
		return sym;
	}
	
	private Symbol createModuleFrmSymbol(PropertyList _properties){
		ModuleFrmSymbol sym = new ModuleFrmSymbol(_properties);
		return sym;
	}
	
	private Symbol createModuleBasSymbol(PropertyList _properties){
		ModuleBasSymbol sym = new ModuleBasSymbol(_properties);
		return sym;
	}
	
	private Symbol createModuleClsSymbol(PropertyList _properties){
		ModuleClsSymbol sym = new ModuleClsSymbol(_properties);
		return sym;
	}	
//	private Symbol createCompilationUnitSymbol(PropertyList _properties){
//		CompilationUnitSymbol sym = new CompilationUnitSymbol(_properties);
//		return sym;
//	}
//	
//	private Symbol createCompilationUnitClassSymbol(PropertyList _properties){
//		CompilationUnitClassSymbol sym = new CompilationUnitClassSymbol(_properties);
//		return sym;
//	}	
	
	private Symbol createServerSymbol(PropertyList _properties){
		try {
			throw new OperationNotSupportedException();
		} catch (OperationNotSupportedException e) {
			BicamSystem.printLog("ERROR", "SYMBOL NOT DEFINED", e);
		}
		return null;	}
	
	private Symbol createCursorSymbol(PropertyList _properties){
		try {
			throw new OperationNotSupportedException();
		} catch (OperationNotSupportedException e) {
			BicamSystem.printLog("ERROR", "SYMBOL NOT DEFINED", e);
		}
		return null;
	}	
	
	private Symbol createClassSymbol(PropertyList _properties){
		ClassSymbol sym = new ClassSymbol(_properties);
		return sym;
	}
	
	private Symbol createVariableSymbol(PropertyList _properties){
		VariableSymbol sym = new VariableSymbol(_properties);
		return sym;
	}
	
	private Symbol createFunctionSymbol(PropertyList _properties){
		FunctionSymbol sym = new FunctionSymbol(_properties);
		return sym;
	}	
	
	private Symbol createLibrarySymbol(PropertyList _properties){
		LibrarySymbol sym = new LibrarySymbol(_properties);
		return sym;
	}
	
	private Symbol createPrimitiveTypeSymbol(PropertyList _properties){
		PrimitiveTypeSymbol sym = new PrimitiveTypeSymbol(_properties);
		return sym;
	}	
	
	private Symbol createDatabaseSymbol(PropertyList _properties){
		try {
			throw new OperationNotSupportedException();
		} catch (OperationNotSupportedException e) {
			BicamSystem.printLog("ERROR", "SYMBOL NOT DEFINED", e);
		}
		return null;
	}
	
	
	private Symbol createTransactionSymbol(PropertyList _properties){
		try {
			throw new OperationNotSupportedException();
		} catch (OperationNotSupportedException e) {
			BicamSystem.printLog("ERROR", "SYMBOL NOT DEFINED", e);
		}
		return null;
	}
	
	private Symbol createIndexSymbol(PropertyList _properties){
		try {
			throw new OperationNotSupportedException();
		} catch (OperationNotSupportedException e) {
			BicamSystem.printLog("ERROR", "SYMBOL NOT DEFINED", e);
		}
		return null; 
	}	
	
	private Symbol createTableSymbol(PropertyList _properties){
		try {
			throw new OperationNotSupportedException();
		} catch (OperationNotSupportedException e) {
			BicamSystem.printLog("ERROR", "SYMBOL NOT DEFINED", e);
		}
		return null;
	}
	
	private Symbol createColumnSymbol(PropertyList _properties){
		try {
			throw new OperationNotSupportedException();
		} catch (OperationNotSupportedException e) {
			BicamSystem.printLog("ERROR", "SYMBOL NOT DEFINED", e);
		}
		return null;
	}	
	
	private Symbol createUserSymbol(PropertyList _properties){
		try {
			throw new OperationNotSupportedException();
		} catch (OperationNotSupportedException e) {
			BicamSystem.printLog("ERROR", "SYMBOL NOT DEFINED", e);
		}
		return null;
	}	
	
	public static Symbol createBuiltinSymbol(PropertyList _properties){
		BuiltinSymbol sym = new BuiltinSymbol(_properties);
		return sym;
	}
	
	public static Symbol createMethodSymbol(PropertyList _properties){
		MethodSymbol sym = new MethodSymbol(_properties);
		return sym;
	}
	
	public static Symbol createGuiSymbol(PropertyList _properties){
		GuiSymbol sym = new GuiSymbol(_properties);
		return sym;
	}
	
	public static Symbol createImplicitSymbol(PropertyList _properties){
		ImplicitSymbol sym = new ImplicitSymbol(_properties);
		return sym;
	}	
	
	public static Symbol createGuiAttributeSymbol(PropertyList _properties){
		GuiAttributeSymbol sym = new GuiAttributeSymbol(_properties);
		return sym;
	}
	
	public static Symbol createGuiPropertySymbol(PropertyList _properties){
		GuiPropertySymbol sym = new GuiPropertySymbol(_properties);
		return sym;
	}
	
	public static Symbol createLabelSymbol(PropertyList _properties){
		LabelSymbol sym = new LabelSymbol(_properties);
		return sym;
	}	
	
	
	public static Symbol createTypeSymbol(PropertyList _properties){
		TypeSymbol sym = new TypeSymbol(_properties);
		return sym;
	}
	
	public static Symbol createEnumSymbol(PropertyList _properties){
		EnumSymbol sym = new EnumSymbol(_properties);
		return sym;
	}	
//	
//	public static Symbol createLabelSymbol(PropertyList _properties){
//		LabelSymbol sym = new LabelSymbol(_properties);
//		return sym;
//	}
//	
//	public static Symbol createStructureSymbol(PropertyList _properties){
//		StructureSymbol sym = new StructureSymbol(_properties);
//		return sym;
//	}
	
	public static Symbol createStoredProcedureSymbol(PropertyList _properties){
		try {
			throw new OperationNotSupportedException();
		} catch (OperationNotSupportedException e) {
			BicamSystem.printLog("ERROR", "SYMBOL NOT DEFINED", e);
		}
		return null;
	}
	
	public static Symbol createGlobalScope(PropertyList _properties){
		try {
			throw new OperationNotSupportedException();
		} catch (OperationNotSupportedException e) {
			BicamSystem.printLog("ERROR", "SYMBOL NOT DEFINED", e);
		}
		return null;
	}	
	
//	public static Symbol createLocalScope(PropertyList _properties){
//		LocalScope sym = new LocalScope(_properties);
//		return sym;
//	}
	
	public static Symbol createVisibilityScope(PropertyList _properties){
		try {
			throw new OperationNotSupportedException();
		} catch (OperationNotSupportedException e) {
			BicamSystem.printLog("ERROR", "SYMBOL NOT DEFINED", e);
		}
		return null;
	}
}