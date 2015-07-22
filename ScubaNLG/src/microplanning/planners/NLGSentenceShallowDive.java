package microplanning.planners;

import microplanning.generators.ShallowDive;
import simplenlg.phrasespec.SPhraseSpec;

public class NLGSentenceShallowDive extends NLGSentence {
	
	private double depth;
	
	public NLGSentenceShallowDive(double depth) {
		this.depth = depth;
	}

	public SPhraseSpec getSentence() {
		ShallowDive generator = new ShallowDive();
		if (depth<=9.6)
			return generator.microplan(true);
		return generator.microplan(false);
	}
	
	@Override
	public boolean canGenerate() {
		return depth <= 12;
	}
}
