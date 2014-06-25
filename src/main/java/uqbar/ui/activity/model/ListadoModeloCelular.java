package uqbar.ui.activity.model;

import java.io.Serializable;
import java.util.List;

import com.google.api.client.util.Key;

public class ListadoModeloCelular implements Serializable{
	@Key
	private List<ModeloCelular> list;

	public List<ModeloCelular> getList() {
		return list;
	}

	public void setList(List<ModeloCelular> list) {
		this.list = list;
	}

}
