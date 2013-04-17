package it.uniroma1.dis.jaco.model;

import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Behavior {
	private String name;
	private String currentState;
	// private FiniteStateMachine finiteStateMachine;
	private List<NodeWithTransitions> finiteStateMachine;

	public Behavior() {
	}

	public Behavior(String name, String currentState) {
		this.name = name;
		this.currentState = currentState;
		this.finiteStateMachine = new LinkedList<NodeWithTransitions>();
	}

	public Behavior(String name, String currentState,
			List<NodeWithTransitions> finiteStateMachine) {
		this.name = name;
		this.currentState = currentState;
		this.finiteStateMachine = finiteStateMachine;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCurrentState() {
		return currentState;
	}

	public void setCurrentState(String currentState) {
		this.currentState = currentState;
	}

	@XmlElementWrapper(name = "finiteStateMachine")
	@XmlElement(name = "state")
	public List<NodeWithTransitions> getFiniteStateMachine() {
		return finiteStateMachine;
	}

	public void setFiniteStateMachine(
			List<NodeWithTransitions> finiteStateMachine) {
		this.finiteStateMachine = finiteStateMachine;
	}

}
