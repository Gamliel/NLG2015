package microplanning.planners;

import microplanning.generators.QuickSuccession;
import simplenlg.phrasespec.SPhraseSpec;

public class NLGSentenceQuickSuccession extends NLGSentence{

	@Override
	public SPhraseSpec getSentence() {
		return new QuickSuccession().generate();
	}

	@Override
	public boolean canPlan() {
		return true;
	}

}
