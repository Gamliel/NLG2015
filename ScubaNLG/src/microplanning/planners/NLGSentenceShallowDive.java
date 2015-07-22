package microplanning.planners;

import microplanning.generators.ShallowDive;
import simplenlg.phrasespec.SPhraseSpec;

public class NLGSentenceShallowDive extends NLGSentence {
	
	private double depth;
	
	public NLGSentenceShallowDive(double depth) {
		this.depth = depth;
	}

	public SPhraseSpec getSentence() {
		if (depth<=9.6){
			ShallowDive generator = new ShallowDive(true);
			return generator.microplan();
		}
		//else
		ShallowDive generator = new ShallowDive(false);
		return generator.microplan();
	}
	
	@Override
	public boolean canGenerate() {
		return depth <= 12;
	}
}
