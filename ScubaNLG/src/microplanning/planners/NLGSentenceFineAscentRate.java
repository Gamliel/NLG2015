package microplanning.planners;

import analytics.DiveletFeatures;
import microplanning.generators.FineAscentRate;
import microplanning.generators.FineAscentRate.AscentOrder;
import simplenlg.phrasespec.SPhraseSpec;

public class NLGSentenceFineAscentRate extends NLGSentence{
	private DiveletFeatures diveletFeatures; 
	private AscentOrder order;
	
	public NLGSentenceFineAscentRate(DiveletFeatures diveletFeatures, AscentOrder order) {
		// TODO Auto-generated constructor stub
		this.diveletFeatures = diveletFeatures;
		this.order = order;
	}

	@Override
	public SPhraseSpec getSentence() {
		// TODO Auto-generated method stub
		return FineAscentRate.microplan(order);
	}

	@Override
	public boolean canGenerate() {
		// TODO Auto-generated method stub
		return diveletFeatures.getExcessAscentSpeed() >= 0;
	}

}
