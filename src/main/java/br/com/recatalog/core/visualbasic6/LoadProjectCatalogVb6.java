package br.com.recatalog.core.visualbasic6;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import br.com.recatalog.core.LoadProjectCatalog;
import br.com.recatalog.model.CatalogItem;
import br.com.recatalog.model.Project;
import br.com.recatalog.model.PropertyCatalog;
import br.com.recatalog.model.RunTimeDependency;

public class LoadProjectCatalogVb6  implements LoadProjectCatalog{

	Map<String,Map<String,Integer>> inventory;
	
	Project project;
	List<CatalogItem> itens;
	
	public static final String REFERENCE = "REFERENCE";
	public static final String OBJECT = "OBJECT";
	public static final String FORM = "FORM";
	public static final String MODULE = "MODULE";
	public static final String CLASS = "CLASS";
	public static final String NAME = "NAME";
	
	public LoadProjectCatalogVb6(Map<String,Map<String,Integer>> inventory) {
		this.inventory = inventory;
		project = new Project();
		project.setId("R1PAB001");
		project.setName("R1PAB001");
		project.setDtCreated(new Date());
		project.setDescription("project description");
		
		itens = new ArrayList<CatalogItem>();
		load();
	}
	
	private void load() {
//		Map<String,Map<String,Integer>> inventoryItem = inventory.getInventory();
		
//		inventoryItem.entrySet();
		
		for (Entry<String, Map<String, Integer>> entry : inventory.entrySet()) {
//		    System.out.println("key: " + entry.getKey());
//		    for(Entry e : entry.getValue().entrySet()) {
//		    	System.out.println("  value: " + e.getKey());
//		    }
			switch(entry.getKey().toUpperCase()) {
				case REFERENCE:
					break;
				case OBJECT:
					addObject(entry.getValue());
					break;
				case FORM:
					break;
				case MODULE:
					break;
				case CLASS:
					break;
				case NAME:
					setProjectName(entry);
					break;
				default:
			}
		}
	}
	
	public void addComponents() {
		
	}
	
	public void addResources() {
		
	}
	
	public void addProperties() {
		
	}
	
	public Project getProjet() {
		return project;
	}
	
	public List<CatalogItem> getItens() {
		return itens;
	}	
	
//	private void addReference(Entry<String,Map<String,Integer>> entry) {
//	    for(Entry<String,Integer> e : entry.getValue().entrySet()) {
//			String valueParts[] = e.getKey().split(";");
////			hklm => HKEY_LOCAL_MACHINE
//			String hklm = valueParts[0];
//			String referenceName = valueParts[valueParts.length-1].trim();
//			String name = referenceName.split("\\.")[0].trim();
//	    }	
//	}
	
	private void addObject(Map<String,Integer> map) {
	    for(Entry<String,Integer> e : map.entrySet()) {
			String valueParts[] = e.getKey().split(";");
//			hklm => HKEY_LOCAL_MACHINE
			String fullHklm = valueParts[0];
			String hklm = fullHklm.split("#")[0];
			String version = fullHklm.split("#")[1];
			String release = fullHklm.split("#")[2];

			String referenceName = valueParts[valueParts.length-1].trim();
			String name = referenceName.split("\\.")[0].trim();
			RunTimeDependency rtd = new RunTimeDependency();
			rtd.setName(name);
			rtd.setId(name);
			rtd.setDtCreated(new Date());
			rtd.setDescription("");
			rtd.setParent(project);
			
			new PropertyCatalog(rtd,"HKLM",hklm );
			new PropertyCatalog(rtd,"VERSION",version );
			new PropertyCatalog(rtd,"RELEASE",release );
			
			itens.add(rtd);
	    }	
	}
	
	private void setProjectName(Entry<String,Map<String,Integer>> entry) {
	    for(Entry<String,Integer> e : entry.getValue().entrySet()) {
	    	// "R1FAB001" => R1FAB001
	    	project.setName(e.getKey().replaceAll("\"", ""));
	    	project.setId(project.getName());
	    }		
	}
}