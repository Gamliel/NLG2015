package microplanning;

import com.mysql.jdbc.NotImplemented;

import analytics.DiveletFeatures;
import simplenlg.phrasespec.SPhraseSpec;

public class NLGSentenceRiskyDive extends NLGSentence{
	
	private DiveletFeatures firstDiveletFeatures;
	
	public NLGSentenceRiskyDive(DiveletFeatures firstDiveletFeature) {
		this.firstDiveletFeatures = firstDiveletFeature;
	}

	public SPhraseSpec getSentence() {
		if (hasReallyExceeded())
			return RiskyDive.microplan(true);
		else
			return RiskyDive.microplan(false);
	}
	
	private boolean hasReallyExceeded() {
		return (firstDiveletFeatures.getBottomTime() / (firstDiveletFeatures.getBottomTime()-firstDiveletFeatures.getExcessBottomTime())) > 1;
	}

	private boolean hasExceeded() {
		return firstDiveletFeatures.getExcessBottomTime() > 0 ||
				firstDiveletFeatures.getExcessDiveDepth() > 0;
	}
	
	public boolean canGenerate(){
		return hasExceeded();
	}

}
