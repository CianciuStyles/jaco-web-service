package it.uniroma1.dis.jaco.model;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;

@XmlRootElement
public class CompositionResponse {
	@XmlElement(name = "queue_number")
	String queueNumber;
	@XmlElement
	ComputedComposition computedComposition;

	public CompositionResponse() {
	}

	public CompositionResponse(String queueNumber) {
		this.queueNumber = queueNumber;
		this.computedComposition = null;
	}

	public CompositionResponse(ComputedComposition cC) {
		this.queueNumber = "0";
		this.computedComposition = cC;
	}

}
