package it.uniroma1.dis.jaco.model;

import javax.xml.bind.annotation.XmlAttribute;
//import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "transition")
public class TargetTransition implements Comparable {
	@XmlAttribute
	String invoke;
	@XmlAttribute
	String action;

	// @XmlElement(name = "target")
	// String targetNode;

	public TargetTransition() {
	}

	/*
	 * public TargetTransition(String inv, String act, String tar) { this.invoke
	 * = inv; this.action = act; this.targetNode = tar; }
	 */

	public TargetTransition(String inv, String act) {
		this.invoke = inv;
		this.action = act;
		// this.targetNode = "";
	}

	/*
	 * public void setTargetNode(String targetNode) { this.targetNode =
	 * targetNode; }
	 */

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof TargetTransition))
			return false;

		TargetTransition tt = (TargetTransition) obj;
		return this.action.equals(tt.action) && this.invoke.equals(tt.invoke);
	}

	@Override
	public int hashCode() {
		return this.action.hashCode() + this.invoke.hashCode();
	}

	@Override
	public int compareTo(Object arg0) {
		if (!(arg0 instanceof TargetTransition))
			throw new ClassCastException();
		TargetTransition t0 = (TargetTransition) arg0;

		int compareActions = this.action.compareTo(t0.action);
		if (compareActions != 0)
			return compareActions;
		else
			return this.invoke.compareTo(t0.invoke);
	}
}
