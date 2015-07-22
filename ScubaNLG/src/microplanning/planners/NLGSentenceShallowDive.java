package microplanning.planners;

import microplanning.generators.ReallyShallowDive;
import microplanning.generators.ShallowDive;
import simplenlg.phrasespec.SPhraseSpec;

public class NLGSentenceShallowDive extends NLGSentence {
	
	private double depth;
	
	public NLGSentenceShallowDive(double depth) {
		this.depth = depth;
	}

	public SPhraseSpec getSentence() {
		if (depth<=9.6){
			ReallyShallowDive generator = new ReallyShallowDive();
			return generator.generate();
		}
		//else
		ShallowDive generator = new ShallowDive();
		return generator.generate();
	}
	
	@Override
	public boolean canPlan() {
		return depth <= 12;
	}
}
