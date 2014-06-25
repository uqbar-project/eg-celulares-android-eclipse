package uqbar.ui.activity.model;
import java.io.Serializable;
import java.math.BigDecimal;

import com.google.api.client.util.Key;


public class ModeloCelular  implements Serializable{
	
	@Key private String descripcion;
	@Key private BigDecimal costo;
	
	public ModeloCelular() {
	}
	
	public ModeloCelular(String descripcion, double costo) {
		this.descripcion = descripcion;
		this.costo = new BigDecimal(costo);
	}
	
	public String getDescripcion() {
		return this.descripcion;
	}
	
	public String getDescripcionEntera() {
		return this.getDescripcion() + " ($ " + this.getCosto() + ")";
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public BigDecimal getCosto() {
		return this.costo;
	}
	
	public void setCosto(BigDecimal costo) {
		this.costo = costo;
	}
	
	@Override
	public String toString() {
		return this.getDescripcionEntera();
	}

}
