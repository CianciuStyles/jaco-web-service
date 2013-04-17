package it.uniroma1.dis.jaco.model;

import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ComputedComposition {
	@XmlElement
	boolean isRealizable;
	@XmlElement(name = "targetState")
	List<TargetState> targetStates;

	public ComputedComposition() {
	}

	public ComputedComposition(boolean isRealizable) {
		this.isRealizable = isRealizable;
		this.targetStates = new LinkedList<TargetState>();
	}

	public void addTargetState(TargetState ts) {
		this.targetStates.add(ts);
	}
}
