package br.com.recatalog.core;

import br.com.recatalog.util.PropertyList;


public interface SymbolFactory {
	public abstract Symbol getSymbol(PropertyList _properties);

//	public abstract SymbolFactory getInstance();
//	public static final String VB6 = "VB6";
//	public static final String SYBASE = "SYBASE";
//	public static final String SQLSERVER = "SQLSERVER";
//	
//	public abstract Symbol getSymbol(PropertyList properties);
//	
//	public static SymbolFactory getInstance(String symbolType) {
//		switch(symbolType) {
//			case VB6 :
//				return new Vb6SymbolFactory();
//			case SYBASE : 
//				try {
//					throw new OperationNotSupportedException("SYBASE");
//				} catch (OperationNotSupportedException e) {
//					e.printStackTrace();
//				}
//			case SQLSERVER:
//			try {
//				throw new OperationNotSupportedException("SQLSERVER");
//			} catch (OperationNotSupportedException e) {
//				e.printStackTrace();
//			}
//			default :
//			try {
//				throw new OperationNotSupportedException("other");
//			} catch (OperationNotSupportedException e) {
//				e.printStackTrace();
//			}
//		}
//		return null;
//	}
}