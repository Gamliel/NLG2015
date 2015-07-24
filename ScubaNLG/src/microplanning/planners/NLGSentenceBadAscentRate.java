package microplanning.planners;

import analytics.DiveletFeatures;
import microplanning.generators.BadAscentRate;
import microplanning.generators.DiveType;
import simplenlg.phrasespec.SPhraseSpec;

public class NLGSentenceBadAscentRate extends NLGSentence{
	private DiveletFeatures diveletFeatures; 
	private DiveType type;
	
	public NLGSentenceBadAscentRate(DiveletFeatures diveletFeatures, DiveType type) {
		this.diveletFeatures = diveletFeatures;
		this.type = type;
	}

	@Override
	public SPhraseSpec getSentence() {
		BadAscentRate generator = new BadAscentRate(type);
		return generator.generate();
	}

	@Override
	public boolean canPlan() {
		return diveletFeatures.getExcessAscentSpeed() > 0;
	}
}
