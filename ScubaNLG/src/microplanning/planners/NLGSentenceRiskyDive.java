package microplanning.planners;

import analytics.DiveletFeatures;
import microplanning.generators.ReallyRiskyDive;
import microplanning.generators.RiskyDive;
import simplenlg.phrasespec.SPhraseSpec;

public class NLGSentenceRiskyDive extends NLGSentence{
	
	private DiveletFeatures firstDiveletFeatures;
	private DiveletFeatures secondDiveletFeatures; 
	
	public NLGSentenceRiskyDive(DiveletFeatures firstDiveletFeature, DiveletFeatures secondDiveletFeatures) {
		this.firstDiveletFeatures = firstDiveletFeature;
		this.secondDiveletFeatures = secondDiveletFeatures;
	}

	public SPhraseSpec getSentence() {
		if (hasReallyExceeded()){
			ReallyRiskyDive generator = new ReallyRiskyDive();
			return generator.generate();
		}
		else{
			RiskyDive generator = new RiskyDive();
			return generator.generate();
		}
	}
	
	private boolean hasReallyExceeded() {
		if (firstDiveletFeatures != null){
			long NDL  = firstDiveletFeatures.getBottomTime()-firstDiveletFeatures.getExcessBottomTime();
			if (NDL > 0){
				return (firstDiveletFeatures.getBottomTime() / NDL) > 1;} 
			else {return false;}
		} else if (secondDiveletFeatures != null){
			long NDL = (secondDiveletFeatures.getBottomTime()-secondDiveletFeatures.getExcessBottomTime());
			if (NDL > 0){
				return  (secondDiveletFeatures.getBottomTime() / NDL) > 1;
			} else {return false;}
		}
		return false;
	}

	private boolean hasExceeded() {
		if (firstDiveletFeatures != null){
			return (double)firstDiveletFeatures.getExcessBottomTime() > 0.0 ||
					firstDiveletFeatures.getExcessDiveDepth() > 0.0;
		} else if (secondDiveletFeatures != null){
			return  (double)secondDiveletFeatures.getExcessBottomTime() > 0.0 ||
					secondDiveletFeatures.getExcessDiveDepth() > 0.0;
		}
		return false;
	}
	
	public boolean canPlan(){
		return hasExceeded();
	}

}
