package nlg;


import java.util.HashMap;
import java.util.function.Function;

import analytics.DiveletFeatures;
import analytics.PADITable;
import microplanning.NLGSentenceShallowDive;
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
		checkSafetyStop();
		
		checkShallowDive();
		
		checkRiskyDive();
		
		return null;
	}

	private void checkSafetyStop(){
		if(PADITable.needSafetyStop(diveDepth, firstDiveletFeatures.getBottomTime())){
			phrases.put("SafetyStop", SafetyStop.microplan());
		}
	}
	
	private void checkShallowDive(){
		NLGSentenceShallowDive sentenceGenerator = new NLGSentenceShallowDive(diveDepth);
		if (sentenceGenerator.canGenerate()){
			phrases.put("ShallowDive", sentenceGenerator.getSentence());
		}
	}
	
	private void checkRiskyDive(){
		if (hasExceeded()){
			if (hasReallyExceeded())
				phrases.put("RiskyDive", RiskyDive.microplan(true));
			else
				phrases.put("RiskyDive", RiskyDive.microplan(false));
		}
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
