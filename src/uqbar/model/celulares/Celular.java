package uqbar.model.celulares;

import java.io.Serializable;

import uqbar.exception.UserException;


public class Celular implements Serializable{
	public final int MAX_NUMERO = 1000;

	private Integer id;
	private Integer numero;
	private String nombre;
	private ModeloCelular modeloCelular;
	private Boolean recibeResumenCuenta = false;

	public Celular() {
	}

	public Celular(String nombre, Integer numero) {
		this.nombre = nombre;
		this.numero = numero;
	}

	public Celular(String nombre, Integer numero, ModeloCelular modeloCelular, boolean recibeResumenCuenta) {
		this.nombre = nombre;
		this.numero = numero;
		this.modeloCelular = modeloCelular;
		this.recibeResumenCuenta = recibeResumenCuenta;
	}

	// ********************************************************
	// ** Validacion
	// ********************************************************

	/**
	 * Valida que el celular esté correctamente cargado
	 */
	public void validar() {
		if (!this.ingresoNumero()) {
			throw new UserException("Debe ingresar número");
		}
		if (this.numero.intValue() <= this.MAX_NUMERO) {
			throw new UserException("El número debe ser mayor a " + this.MAX_NUMERO);
		}
		if (!this.ingresoNombre()) {
			throw new UserException("Debe ingresar nombre");
		}
		if (this.modeloCelular == null) {
			throw new UserException("Debe ingresar un modelo de celular");
		}
	}

	public boolean ingresoNombre() {
		return this.nombre != null && !this.nombre.trim().equals("");
	}

	public boolean ingresoNumero() {
		return this.numero != null;
	}

	// ********************************************************
	// ** Getters y setters
	// ********************************************************

	public Integer getNumero() {
		return this.numero;
	}

	public void setNumero(Integer numero) {
		if (numero < 10000000) {
			throw new UserException("El número de celular es muy corto");
		}
		this.numero = numero;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ModeloCelular getModeloCelular() {
		return this.modeloCelular;
	}

	public void setModeloCelular(ModeloCelular modeloCelular) {
		this.modeloCelular = modeloCelular;
	}

	public void setRecibeResumenCuenta(Boolean recibeResumenCuenta) {
		this.recibeResumenCuenta = recibeResumenCuenta;
	}

	public Boolean getRecibeResumenCuenta() {
		return this.recibeResumenCuenta;
	}

	// ********************************************************
	// ** Misceláneos
	// ********************************************************
	

	@Override
	public String toString() {
		StringBuffer result = new StringBuffer();
		if (this.ingresoNombre()) {
			result.append(this.nombre);
		}
		else {
			result.append("Celular sin nombre");
		}
		if (this.modeloCelular != null) {
			result.append(" - " + this.modeloCelular);
		}
		else {
			result.append(" - sin modelo");
		}
		if (this.ingresoNumero()) {
			result.append(" - " + this.numero);
		}
		else {
			result.append(" - sin número");
		}
		if (this.recibeResumenCuenta) {
			result.append(" - recibe resumen de cuenta");
		}
		else {
			result.append(" - no recibe resumen de cuenta");
		}
		return result.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + MAX_NUMERO;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Celular other = (Celular) obj;
		if (MAX_NUMERO != other.MAX_NUMERO)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
