package microplanning;

import com.mysql.jdbc.NotImplemented;

import analytics.DiveletFeatures;
import analytics.PADITable;
import simplenlg.lexicon.Lexicon;
import simplenlg.phrasespec.SPhraseSpec;
import simplenlg.realiser.english.Realiser;

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
        
		if(PADITable.needSafetyStop(depth, firstDiveletFeatures.getBottomTime())){
			return SafetyStop.microplan();
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
