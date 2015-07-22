package microplanning;

import com.mysql.jdbc.NotImplemented;

import analytics.DiveletFeatures;
import simplenlg.phrasespec.SPhraseSpec;

public class NLGSentence {
	
	private double depth;
	private DiveletFeatures firstDiveletFeatures;
	
	public void setFeatures(double depth, DiveletFeatures firstDiveletFeature) {
		this.depth = depth;
		this.firstDiveletFeatures = firstDiveletFeature;
	}

	public SPhraseSpec getSentence() throws NotImplemented {
        if (hasExceeded()){
        	if(hasReallyExceeded())
        		return RiskyDive.microplan(true);
        	else
        		return RiskyDive.microplan(false);
        }
        
		throw new NotImplemented();
	}
	
	private boolean hasReallyExceeded() {
		return (firstDiveletFeatures.getBottomTime() / (firstDiveletFeatures.getBottomTime()-firstDiveletFeatures.getExcessBottomTime())) > 1;
	}

	private boolean hasExceeded() {
		return firstDiveletFeatures.getExcessBottomTime() > 0 ||
				firstDiveletFeatures.getExcessDiveDepth() > 0;
	}
	
	public boolean canGenerate(){
		return false;
	}

}
