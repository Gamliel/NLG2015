package microplanning.planners;

import analytics.DiveletFeatures;
import microplanning.generators.DeeperDepth;
import simplenlg.phrasespec.SPhraseSpec;

public class NLGSentenceDeeperDepth extends NLGSentence{
	private DiveletFeatures diveletFeatures;
	
	public NLGSentenceDeeperDepth(DiveletFeatures diveletFeatures) {
		this.diveletFeatures = diveletFeatures;
	}

	@Override
	public SPhraseSpec getSentence() {
		String objREG = Integer.toString((int)diveletFeatures.getExcessDiveDepth())+"m";
		return DeeperDepth.microplan(objREG);
	}

	@Override
	public boolean canGenerate() {
		return diveletFeatures.getExcessDiveDepth() > 0;
	}

}
