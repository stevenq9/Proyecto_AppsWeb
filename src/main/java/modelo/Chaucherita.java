package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Chaucherita implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private CatalogoDeMovimientos catalogoDeMovimientos;
	private CatalogoDeCuentas catalogoDeCuentas;

	
	public Chaucherita() {
		super();
	}

	public CatalogoDeMovimientos getCatalogoDeMovimientos() {
		return catalogoDeMovimientos;
	}

	public void setCatalogoDeMovimientos(CatalogoDeMovimientos catalogoDeMovimientos) {
		this.catalogoDeMovimientos = catalogoDeMovimientos;
	}

	public CatalogoDeCuentas getCatalogoDeCuentas() {
		return catalogoDeCuentas;
	}

	public void setCatalogoDeCuentas(CatalogoDeCuentas catalogoDeCuentas) {
		this.catalogoDeCuentas = catalogoDeCuentas;
	}
	
	
}
