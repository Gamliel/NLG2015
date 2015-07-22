package microplanning;

import analytics.DiveletFeatures;
import analytics.PADITable;

import simplenlg.framework.*;
import simplenlg.lexicon.*;
import simplenlg.realiser.english.*;
import simplenlg.phrasespec.*;
import simplenlg.features.*;

public class NLGSentence {
	
	private double depth;
	private DiveletFeatures firstDiveletFeatures;
	private DiveletFeatures secondDiveletFeatures;
	
	public void setFeatures(double depth, DiveletFeatures firstDiveletFeature) {
		this.depth = depth;
		this.firstDiveletFeatures = firstDiveletFeature;
	}

	public String getSentence() {
		Lexicon lexicon = Lexicon.getDefaultLexicon();
        Realiser realiser = new Realiser(lexicon);
		
        if (hasExceeded()){
        	if(hasReallyExceeded())
        		return realiser.realiseSentence(RiskyDive.microplan(true));
        	else
        		return realiser.realiseSentence(RiskyDive.microplan(false));
        }
        
		if(PADITable.needSafetyStop(depth, firstDiveletFeatures.getBottomTime())){
			return realiser.realiseSentence(SafetyStop.microplan());
		}
		if (depth<=9.6)
			return realiser.realiseSentence(ShallowDive.microplan(true));
		return realiser.realiseSentence(ShallowDive.microplan(false));
	}
	
	private boolean hasReallyExceeded() {
		return (firstDiveletFeatures.getBottomTime() / (firstDiveletFeatures.getBottomTime()-firstDiveletFeatures.getExcessBottomTime())) > 1;
	}

	private boolean hasExceeded() {
		return firstDiveletFeatures.getExcessBottomTime() > 0 ||
				firstDiveletFeatures.getExcessDiveDepth() > 0;
	}

}
