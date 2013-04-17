package it.uniroma1.dis.jaco.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class BehaviorState implements Comparable {
	@XmlAttribute(name = "behavior")
	String behaviorName;
	@XmlAttribute(name = "state")
	String behaviorState;

	public BehaviorState() {
	}

	public BehaviorState(String behaviorName, String behaviorState) {
		this.behaviorName = behaviorName;
		this.behaviorState = behaviorState;
	}

	@Override
	public int compareTo(Object obj) {
		BehaviorState other = (BehaviorState) obj;

		if (this.behaviorName.compareTo(other.behaviorName) == 0) {
			return this.behaviorState.compareTo(other.behaviorState);
		} else
			return this.behaviorName.compareTo(other.behaviorName);
	}

	public boolean equals(Object obj) {
		if (obj == null)
			return false;

		BehaviorState other = (BehaviorState) obj;

		return this.behaviorName.equals(other.behaviorName)
				&& this.behaviorState.equals(other.behaviorState);
	}

	public int hashCode() {
		return behaviorName.hashCode() + behaviorState.hashCode();
	}
}
