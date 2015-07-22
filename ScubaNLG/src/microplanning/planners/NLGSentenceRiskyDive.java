package microplanning.planners;

import analytics.DiveletFeatures;
import microplanning.generators.RiskyDive;
import simplenlg.phrasespec.SPhraseSpec;

public class NLGSentenceRiskyDive extends NLGSentence{
	
	private DiveletFeatures firstDiveletFeatures;
	private DiveletFeatures secondDiveletFeatures; 
	
	public NLGSentenceRiskyDive(DiveletFeatures firstDiveletFeature, DiveletFeatures secondDiveletFeatures) {
		this.firstDiveletFeatures = firstDiveletFeature;
		this.secondDiveletFeatures = secondDiveletFeatures;
	}

	public SPhraseSpec getSentence() {
		if (hasReallyExceeded())
			return RiskyDive.microplan(true);
		else
			return RiskyDive.microplan(false);
	}
	
	private boolean hasReallyExceeded() {
		return (firstDiveletFeatures.getBottomTime() / (firstDiveletFeatures.getBottomTime()-firstDiveletFeatures.getExcessBottomTime())) > 1 ||
				(secondDiveletFeatures.getBottomTime() / (secondDiveletFeatures.getBottomTime()-secondDiveletFeatures.getExcessBottomTime())) > 1;
	}

	private boolean hasExceeded() {
		return firstDiveletFeatures.getExcessBottomTime() > 0 ||
				firstDiveletFeatures.getExcessDiveDepth() > 0 ||
				secondDiveletFeatures.getExcessBottomTime() > 0 ||
				secondDiveletFeatures.getExcessDiveDepth() > 0;
	}
	
	public boolean canGenerate(){
		return hasExceeded();
	}

}
