package uqbar.model.celulares;

import java.io.Serializable;
import java.util.List;

import uqbar.ui.celulares.dao.RepositorioCelulares;

public class BuscadorCelular implements Serializable {
	private Integer numero;
	private String nombre;
	private List<Celular> resultados;
	private Celular celularSeleccionado;

	// ********************************************************
	// ** Acciones
	// ********************************************************

	public void search() {
		this.resultados = RepositorioCelulares.getInstance().search(this.numero, this.nombre);
	}

	public void clear() {
		this.nombre = "";
		this.numero = null;
	}

	public void eliminarCelularSeleccionado() {
		RepositorioCelulares.getInstance().delete(this.getCelularSeleccionado());
		this.search();
		this.celularSeleccionado = null;
	}

	// ********************************************************
	// ** Accessors
	// ********************************************************

	public Integer getNumero() {
		return this.numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Celular getCelularSeleccionado() {
		return this.celularSeleccionado;
	}

	public void setCelularSeleccionado(Celular celularSeleccionado) {
		this.celularSeleccionado = celularSeleccionado;
	}

	public List<Celular> getResultados() {
		return this.resultados;
	}

	public void setResultados(List<Celular> resultados) {
		this.resultados = resultados;
	}
}
