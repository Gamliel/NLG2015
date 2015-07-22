package microplanning.planners;

import simplenlg.phrasespec.SPhraseSpec;

public abstract class NLGSentence {
	
	public abstract SPhraseSpec getSentence();
	
	public abstract boolean canPlan();
}
