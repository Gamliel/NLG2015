package microplanning.planners;

import analytics.DiveletFeatures;
import microplanning.generators.DiveType;
import microplanning.generators.FineAscentRate;
import simplenlg.phrasespec.SPhraseSpec;

public class NLGSentenceFineAscentRate extends NLGSentence{
	private DiveletFeatures diveletFeatures; 
	private DiveType type;
	
	public NLGSentenceFineAscentRate(DiveletFeatures diveletFeatures, DiveType type) {
		this.diveletFeatures = diveletFeatures;
		this.type = type;
	}

	@Override
	public SPhraseSpec getSentence() {
		FineAscentRate generator = new FineAscentRate(type);
		return generator.generate();
	}

	@Override
	public boolean canPlan() {
		return diveletFeatures.getExcessAscentSpeed() <= 0;
	}

}
