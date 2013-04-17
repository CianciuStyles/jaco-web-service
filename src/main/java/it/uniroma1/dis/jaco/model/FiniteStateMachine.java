package it.uniroma1.dis.jaco.model;

import java.util.LinkedList;
import java.util.List;

public class FiniteStateMachine {
	private List<NodeWithTransitions> nodeWithTransitions;

	public FiniteStateMachine() {
		this.nodeWithTransitions = new LinkedList<NodeWithTransitions>();
	}

	public FiniteStateMachine(List<NodeWithTransitions> finiteStateMachine) {
		this.nodeWithTransitions = finiteStateMachine;
	}

	public List<NodeWithTransitions> getFiniteStateMachine() {
		return this.nodeWithTransitions;
	}

	public void setFiniteStateMachine(
			List<NodeWithTransitions> finiteStateMachine) {
		this.nodeWithTransitions = finiteStateMachine;
	}
}
