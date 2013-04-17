package it.uniroma1.dis.jaco.model;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CommunityState {

	List<BehaviorState> behaviorState;

	public CommunityState() {
		this.behaviorState = new LinkedList<BehaviorState>();
	}

	public void addBehaviorState(String behaviorName, String behaviorState) {
		this.behaviorState.add(new BehaviorState(behaviorName, behaviorState));
	}

	@XmlElement(name = "behaviorState")
	public List<BehaviorState> getBehaviorStates() {
		return this.behaviorState;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof CommunityState))
			return false;

		CommunityState other = (CommunityState) obj;
		List<BehaviorState> thisList = new LinkedList<BehaviorState>();
		thisList.addAll(this.behaviorState);
		Collections.sort(thisList);
		// System.out.println(thisList.size());

		List<BehaviorState> otherList = new LinkedList<BehaviorState>();
		otherList.addAll(other.getBehaviorStates());
		Collections.sort(otherList);
		// System.out.println(otherList.size());

		int length = thisList.size();
		if (length == 0)
			return true;

		for (int i = 0; i < length; i++)
			if (!(thisList.get(i).equals(otherList.get(i))))
				return false;

		return true;
	}
}
