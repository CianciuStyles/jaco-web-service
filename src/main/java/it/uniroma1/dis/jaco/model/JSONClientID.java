package it.uniroma1.dis.jaco.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class JSONClientID {
	@XmlElement(name = "client_id")
	public String clientID;

	public JSONClientID() {

	}

	public JSONClientID(String clientID) {
		this.clientID = clientID;
	}
}
