package br.com.recatalog.core.visualbasic6;

import br.com.recatalog.core.Language;
import br.com.recatalog.core.OnlyScope;
import br.com.recatalog.util.PropertyList;

public class BuiltinScope extends ScopeSymbol implements OnlyScope{

	public BuiltinScope(PropertyList properties) {
		super(properties);
	}
	
	public static void main(String[] args) {
		PropertyList prop = new PropertyList();
		prop.addProperty("NAME", "BUILTIN");
		
		Language language = new LanguageVb6();
		prop.addProperty("LANGUAGE", language);
		BuiltinScope gs = new BuiltinScope(prop);
		System.err.println(gs.toString());
	}
}