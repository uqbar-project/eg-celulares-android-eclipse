package uqbar.ui.activity.model;

import java.util.List;

import com.google.api.client.util.Key;

public class ListadoCelulares{
	@Key
	private List<Celular> list;

	public List<Celular> getList() {
		return list;
	}

	public void setList(List<Celular> list) {
		this.list = list;
	}

}
