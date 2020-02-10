package br.com.recatalog.core.visualbasic6;

import br.com.recatalog.core.Language;
import br.com.recatalog.core.OnlyScope;
import br.com.recatalog.util.PropertyList;

public class GlobalScope extends ScopeSymbol implements OnlyScope{

	public GlobalScope(PropertyList properties) {
		super(properties);
	}
	
	public static void main(String[] args) {
		PropertyList prop = new PropertyList();
		prop.addProperty("NAME", "GLOBAL");
		
		Language language = new LanguageVb6();
		prop.addProperty("LANGUAGE", language);
		GlobalScope gs = new GlobalScope(prop);
		System.err.println(gs);
		System.err.println(gs.toString());
	}
}