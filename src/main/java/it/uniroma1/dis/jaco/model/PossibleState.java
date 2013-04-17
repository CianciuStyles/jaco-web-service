package it.uniroma1.dis.jaco.model;

import java.util.LinkedList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElementWrapper;

@XmlRootElement(name = "possibleState")
public class PossibleState {

	CommunityState communityState;
	SortedSet<TargetTransition> transitions;

	public PossibleState() {
		this.communityState = null;
		this.transitions = new TreeSet<TargetTransition>();
	}

	public PossibleState(CommunityState cs) {
		this.communityState = cs;
		this.transitions = new TreeSet<TargetTransition>();
	}

	@XmlElement
	public CommunityState getCommunityState() {
		return this.communityState;
	}

	@XmlElementWrapper(name = "transitions")
	@XmlElement(name = "transition")
	public SortedSet<TargetTransition> getTransitions() {
		return this.transitions;
	}

	public void setCommunityState(CommunityState cs) {
		this.communityState = cs;
	}

	public void addTransition(TargetTransition tt) {
		this.transitions.add(tt);
	}
}
