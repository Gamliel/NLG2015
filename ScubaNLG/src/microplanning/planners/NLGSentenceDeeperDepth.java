package microplanning.planners;

import analytics.DiveletFeatures;
import microplanning.generators.DeeperDepth;
import microplanning.generators.Generator;
import simplenlg.phrasespec.SPhraseSpec;

public class NLGSentenceDeeperDepth extends NLGSentence{
	private DiveletFeatures diveletFeatures;
	
	public NLGSentenceDeeperDepth(DiveletFeatures diveletFeatures) {
		this.diveletFeatures = diveletFeatures;
	}

	@Override
	public SPhraseSpec getSentence() {
		String objREG = Integer.toString((int)diveletFeatures.getExcessDiveDepth())+"m";
		DeeperDepth generator = new DeeperDepth(objREG);
		return generator.microplan();
	}

	@Override
	public boolean canGenerate() {
		return diveletFeatures.getExcessDiveDepth() > 0;
	}

}
