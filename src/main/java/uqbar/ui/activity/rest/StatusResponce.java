package uqbar.ui.activity.rest;

import com.google.api.client.util.Key;

public class StatusResponce {
	@Key private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
