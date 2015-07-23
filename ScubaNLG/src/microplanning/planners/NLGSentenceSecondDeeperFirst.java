package microplanning.planners;

import analytics.DiveletFeatures;
import microplanning.generators.SecondDeeperFirst;
import simplenlg.phrasespec.SPhraseSpec;

public class NLGSentenceSecondDeeperFirst extends NLGSentence{
	
	private DiveletFeatures firstDivelet;
	private DiveletFeatures secondDivelet;
	
	public NLGSentenceSecondDeeperFirst(DiveletFeatures firstDivelet, DiveletFeatures secondDivelet) {
		this.firstDivelet = firstDivelet;
		this.secondDivelet = secondDivelet;
	}
	
	@Override
	public SPhraseSpec getSentence() {
		return new SecondDeeperFirst().generate();
	}

	@Override
	public boolean canPlan() {
		// TODO Auto-generated method stub
		return secondDivelet.getDiveDepth() > firstDivelet.getDiveDepth();
	}

}
