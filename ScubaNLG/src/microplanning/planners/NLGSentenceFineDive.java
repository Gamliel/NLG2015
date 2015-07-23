package microplanning.planners;

import analytics.DiveletFeatures;
import microplanning.generators.DiveType;
import microplanning.generators.FineDive;
import microplanning.generators.Generator;
import simplenlg.phrasespec.SPhraseSpec;

public class NLGSentenceFineDive extends NLGSentence {

	private DiveletFeatures diveletFeature;
	private DiveType type;
	
	public NLGSentenceFineDive(DiveletFeatures diveletFeature, DiveType type) {
		this.diveletFeature = diveletFeature;
		this.type = type;
	}

	@Override
	public SPhraseSpec getSentence() {
		Generator generator = new FineDive(type);
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
