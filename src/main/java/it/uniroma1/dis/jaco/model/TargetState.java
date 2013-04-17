package it.uniroma1.dis.jaco.model;

import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import it.uniroma1.dis.jaco.model.PossibleState;

@XmlRootElement
public class TargetState {
	@XmlAttribute
	String stateName;
	/*
	 * @XmlElementWrapper(name = "communityStates")
	 * 
	 * @XmlElement List<CommunityState> communityState;
	 * 
	 * @XmlElementWrapper(name = "transitions")
	 * 
	 * @XmlElement //List<TargetTransition> transition;
	 * SortedSet<TargetTransition> transition;
	 */

	@XmlElementWrapper(name = "possibleStates")
	@XmlElement(name = "possibleState")
	List<PossibleState> possibleStates;

	public TargetState() {
	}

	/*
	 * public TargetState(String stateName) { this.stateName = stateName;
	 * this.communityState = new LinkedList<CommunityState>(); //this.transition
	 * = new LinkedList<TargetTransition>(); this.transition = new
	 * TreeSet<TargetTransition>(); }
	 */

	public TargetState(String stateName) {
		this.stateName = stateName;
		this.possibleStates = new LinkedList<PossibleState>();
	}

	/*
	 * public void addTransition(TargetTransition ts) { if(
	 * !this.transition.contains(ts) ) this.transition.add(ts); }
	 * 
	 * public void addCommunityState(CommunityState cs) {
	 * //this.communityState.add(cs); if( !this.communityState.contains(cs) )
	 * this.communityState.add(cs); }
	 */
	/*
	 * public void addTransition(TargetTransition ts) {
	 * this.transitions.add(ts); }
	 */
	public String getName() {
		return this.stateName;
	}

	/*
	 * public TargetTransition getTargetTransition() { return this.transition; }
	 */
	public void addPossibleState(PossibleState ps) {
		if (!this.possibleStates.contains(ps))
			this.possibleStates.add(ps);
	}

	public PossibleState contains(CommunityState cs) {
		for (PossibleState ps : possibleStates)
			if (ps.getCommunityState().equals(cs))
				return ps;

		return null;
	}
}
