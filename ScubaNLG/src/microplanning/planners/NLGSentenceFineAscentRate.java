package microplanning.planners;

import analytics.DiveletFeatures;
import microplanning.generators.FineAscentRate;
import microplanning.generators.FineAscentRate.AscentOrder;
import simplenlg.phrasespec.SPhraseSpec;

public class NLGSentenceFineAscentRate extends NLGSentence{
	private DiveletFeatures diveletFeatures; 
	private AscentOrder order;
	
	public NLGSentenceFineAscentRate(DiveletFeatures diveletFeatures, AscentOrder order) {
		this.diveletFeatures = diveletFeatures;
		this.order = order;
	}

	@Override
	public SPhraseSpec getSentence() {
		FineAscentRate generator = new FineAscentRate(order);
		return generator.generate();
	}

	@Override
	public boolean canPlan() {
		return diveletFeatures.getExcessAscentSpeed() >= 0;
	}

}
