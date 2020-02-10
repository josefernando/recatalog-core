package br.com.recatalog.core.visualbasic6;

import br.com.recatalog.core.Language;
import br.com.recatalog.core.Scope;
import br.com.recatalog.util.PropertyList;

public class VariableSymbol extends VariableStructure{

	public VariableSymbol(PropertyList properties) {
		super(properties);
	}
	
	public static void main(String[] args) {
		PropertyList propList = new PropertyList();
		propList.addProperty("NAME", "GLOBAL");
		Language lanVb6 = new LanguageVb6();
		propList.addProperty("LANGUAGE", lanVb6);

		Scope scope = new GlobalScope(propList);
		
		String name = "e_osg_aux";
		PropertyList prop = new PropertyList();
		prop.addProperty("NAME", name);
		prop.addProperty("SCOPE", scope);

		VariableSymbol varSym = new VariableSymbol(prop);
		
		System.out.println(varSym);
		System.out.println(varSym.toString());
	}
}