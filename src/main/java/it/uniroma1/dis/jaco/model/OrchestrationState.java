package it.uniroma1.dis.jaco.model;

import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class OrchestrationState {
	@XmlElementWrapper(name = "communityState")
	@XmlElement
	List<BehaviorState> behaviorState;

	int id;

	public OrchestrationState() {
	}

	public OrchestrationState(int id) {
		this.behaviorState = new LinkedList<BehaviorState>();
		this.id = id;
	}

	public void addBehaviorState(String behaviorName, String behaviorState) {
		this.behaviorState.add(new BehaviorState(behaviorName, behaviorState));
	}

}
