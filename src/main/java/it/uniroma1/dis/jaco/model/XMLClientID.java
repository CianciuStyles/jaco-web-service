package it.uniroma1.dis.jaco.model;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

@XmlRootElement(name = "client_id")
public class XMLClientID {
	@XmlValue
	public String clientID;

	public XMLClientID() {

	}

	public XMLClientID(String clientID) {
		this.clientID = clientID;
	}
}
