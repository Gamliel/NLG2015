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
	private DiveletFeatures firstDivelet;
	
	public void setFeatures(double depth, DiveletFeatures firstDiveletFeature) {
		this.depth = depth;
		this.firstDivelet = firstDiveletFeature;
	}

	public String getSentence() {
		Lexicon lexicon = Lexicon.getDefaultLexicon();
        Realiser realiser = new Realiser(lexicon);
		
		if(PADITable.needSafetyStop(depth, firstDivelet.getBottomTime())){
			return realiser.realiseSentence(SafetyStop.microplan());
		}
		if (depth<=9.6)
			return realiser.realiseSentence(ShallowDive.microplan(true));
		return realiser.realiseSentence(ShallowDive.microplan(false));
	}

}
