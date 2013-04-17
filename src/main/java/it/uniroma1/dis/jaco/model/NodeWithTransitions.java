package it.uniroma1.dis.jaco.model;

import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAttribute;

@XmlRootElement
public class NodeWithTransitions {

	private String nodeName;
	private List<Transition> transitions;

	public NodeWithTransitions(String nodeName) {
		this.nodeName = nodeName;
		this.transitions = new LinkedList<Transition>();
	}

	public NodeWithTransitions() {

	}

	public NodeWithTransitions(String nodeName, List<Transition> transitions) {
		this.nodeName = nodeName;
		this.transitions = transitions;
	}

	@XmlAttribute(name = "node")
	public String getNodeName() {
		return this.nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	@XmlElement(name = "transition")
	public List<Transition> getTransitions() {
		return this.transitions;
	}

	public void setTransitions(List<Transition> transitions) {
		this.transitions = transitions;
	}
}
