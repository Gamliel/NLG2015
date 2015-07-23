package microplanning.planners;

import analytics.DiveletFeatures;
import microplanning.generators.ExceededNDL;
import simplenlg.phrasespec.SPhraseSpec;

public class NLGSentenceExceededNDL extends NLGSentence {
	private DiveletFeatures diveletFeatures;
	
	public NLGSentenceExceededNDL(DiveletFeatures diveletFeatures) {
		this.diveletFeatures = diveletFeatures;
	}

	@Override
	public SPhraseSpec getSentence() {
		ExceededNDL generator = new ExceededNDL(diveletFeatures.getBottomTime(), diveletFeatures.getExcessBottomTime());
		return generator.generate();
	}

	@Override
	public boolean canPlan() {
		return diveletFeatures.getExcessBottomTime() > 0;
	}

}
