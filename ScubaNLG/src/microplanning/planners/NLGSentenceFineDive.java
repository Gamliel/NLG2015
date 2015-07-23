package microplanning.planners;

import analytics.DiveletFeatures;
import microplanning.generators.FineDive;
import microplanning.generators.Generator;
import simplenlg.phrasespec.SPhraseSpec;

public class NLGSentenceFineDive extends NLGSentence {

	private DiveletFeatures diveletFeature;
	
	public NLGSentenceFineDive(DiveletFeatures diveletFeature) {
		this.diveletFeature = diveletFeature;
	}

	@Override
	public SPhraseSpec getSentence() {
		Generator generator = new FineDive();
		return generator.generate();
	}

	@Override
	public boolean canPlan() {
		return diveletFeature != null &&
				diveletFeature.getExcessAscentSpeed() >=0 &&
				diveletFeature.getExcessBottomTime() >=0 &&
				diveletFeature.getExcessDiveDepth() >= 0;
	}

}
