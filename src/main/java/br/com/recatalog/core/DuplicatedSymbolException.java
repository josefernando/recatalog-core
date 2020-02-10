package br.com.recatalog.core;

public class DuplicatedSymbolException extends Exception { 
	private static final long serialVersionUID = 1234567L;
    public DuplicatedSymbolException(String errorMessage) {
        super(errorMessage);
    }
}
