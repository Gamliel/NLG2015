package nlg;


import java.util.HashMap;

import analytics.DiveletFeatures;
import analytics.PADITable;
import microplanning.RiskyDive;
import microplanning.SafetyStop;
import microplanning.ShallowDive;

import simplenlg.phrasespec.*;

public class TextGenerator implements Reporter{
	
	double diveDepth;
	int numOfDivelets;
	DiveletFeatures firstDiveletFeatures;
	DiveletFeatures secondDiveletFeatures; 
	
	HashMap<String, SPhraseSpec> phrases;
	
	public TextGenerator(double diveDepth, 
			int numOfDivelets, 
			DiveletFeatures firstDiveletFeatures, 
			DiveletFeatures secondDiveletFeatures) {
		// TODO Auto-generated constructor stub
		this.diveDepth = diveDepth;
		this.numOfDivelets = numOfDivelets;
		this.firstDiveletFeatures = firstDiveletFeatures;
		this.secondDiveletFeatures = secondDiveletFeatures;
		
		this.phrases = new HashMap<String, SPhraseSpec>();
	}
	
	@Override
	public String generateText() {
		// TODO Auto-generated method stub
		if(PADITable.needSafetyStop(diveDepth, firstDiveletFeatures.getBottomTime())){
			phrases.put("SafetyStop", SafetyStop.microplan());
		}
		
		if (diveDepth <= 12.0){
			if (diveDepth <= 9.6)
				phrases.put("ShallowDive", ShallowDive.microplan(true));
			else
				phrases.put("ShallowDive", ShallowDive.microplan(false));
		}
		
		if (hasExceeded()){
			if (hasReallyExceeded())
				phrases.put("RiskyDive", RiskyDive.microplan(true));
			else
				phrases.put("RiskyDive", RiskyDive.microplan(false));
		}
		
		return null;
	}

	private boolean hasReallyExceeded() {
		return (firstDiveletFeatures.getBottomTime() / (firstDiveletFeatures.getBottomTime()-firstDiveletFeatures.getExcessBottomTime())) > 1 ||
				(secondDiveletFeatures.getBottomTime() / (secondDiveletFeatures.getBottomTime()-secondDiveletFeatures.getExcessBottomTime())) > 1;
	}

	private boolean hasExceeded() {
		return firstDiveletFeatures.getExcessBottomTime() > 0 ||
				firstDiveletFeatures.getExcessDiveDepth() > 0 ||
				secondDiveletFeatures.getExcessBottomTime() > 0 ||
				secondDiveletFeatures.getExcessDiveDepth() > 0;
	}

}
