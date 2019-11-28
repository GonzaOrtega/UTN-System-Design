package controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import domain.Prenda;

public class GuardarropaInformation {
	private String nombre;
	private List<Prenda> prendas;
	private List<Set<Prenda>> listaPrendasView;
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Prenda> getPrendas() {
		return prendas;
	}

	public void setPrendas(List<Prenda> prendas) {
		this.prendas = prendas;
	}

	public List<Set<Prenda>> getListaPrendasView() {
		return listaPrendasView;
	}

	public void setListaPrendasView(List<Set<Prenda>> listaPrendasView) {
		this.listaPrendasView = listaPrendasView;
	}

	public GuardarropaInformation(String name,List<Prenda> setPrendas) {
		this.nombre = name;
		this.prendas = setPrendas;
		listaPrendasView = new ArrayList<Set<Prenda>>();
		Set<Prenda> set = new HashSet<Prenda>();
		for(int i=0;i<prendas.size();i++) {
			if(set.size()<3) {
				set.add(this.prendas.get(i));
			}
			else {
				listaPrendasView.add(set);
				set = new HashSet<Prenda>();
				set.add(this.prendas.get(i));
				}
		}
		if(set.size() > 0) {
			listaPrendasView.add(set);
		}
	}
}
