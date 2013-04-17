package it.uniroma1.dis.jaco.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAttribute;

//import org.eclipse.persistence.oxm.annotations.XmlPath;

@XmlRootElement
// @XmlType(name = "transition", propOrder = {"destinationNode", "actionName"})
public class Transition {

	private String actionName;
	private List<String> destinationNodes;

	public Transition(String actionName, List<String> destinationNodes) {
		this.actionName = actionName;
		this.destinationNodes = destinationNodes;
	}

	public Transition() {
	}

	@XmlAttribute(name = "action")
	public String getActionName() {
		return this.actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	@XmlElement(name = "target")
	// @XmlPath("target/@state")
	public List<String> getDestinationNodes() {
		return this.destinationNodes;
	}

	public void setDestinationNodes(List<String> destinationNodes) {
		this.destinationNodes = destinationNodes;
	}
}
