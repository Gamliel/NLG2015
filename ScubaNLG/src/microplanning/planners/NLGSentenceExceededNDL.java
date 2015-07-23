package microplanning.planners;

import analytics.DiveletFeatures;
import microplanning.generators.DiveType;
import microplanning.generators.ExceededNDL;
import simplenlg.phrasespec.SPhraseSpec;

public class NLGSentenceExceededNDL extends NLGSentence {
	private DiveletFeatures diveletFeatures;
	private DiveType type;
	
	public NLGSentenceExceededNDL(DiveletFeatures diveletFeatures, DiveType type) {
		this.diveletFeatures = diveletFeatures;
		this.type = type;
	}

	@Override
	public SPhraseSpec getSentence() {
		ExceededNDL generator = new ExceededNDL(diveletFeatures.getBottomTime(), diveletFeatures.getExcessBottomTime(), type);
		return generator.generate();
	}

	@Override
	public boolean canPlan() {
		return diveletFeatures.getExcessBottomTime() > 0;
	}

}
