package nlg;


import java.util.HashMap;

import analytics.DiveletFeatures;
import microplanning.generators.SafetyStop;
import microplanning.planners.NLGSentenceRiskyDive;
import microplanning.planners.NLGSentenceSafetyStop;
import microplanning.planners.NLGSentenceShallowDive;
import simplenlg.phrasespec.SPhraseSpec;

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
		this.diveDepth = diveDepth;
		this.numOfDivelets = numOfDivelets;
		this.firstDiveletFeatures = firstDiveletFeatures;
		this.secondDiveletFeatures = secondDiveletFeatures;
		
		this.phrases = new HashMap<String, SPhraseSpec>();
	}
	
	@Override
	public String generateText() {
		checkSafetyStop();
		
		checkShallowDive();
		
		checkRiskyDive();
		
		return null;
	}

	private void checkSafetyStop(){
		NLGSentenceSafetyStop generator = new NLGSentenceSafetyStop(diveDepth, firstDiveletFeatures.getBottomTime());
		if (generator.canGenerate()){
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
		NLGSentenceRiskyDive sentenceGenerator = new NLGSentenceRiskyDive(firstDiveletFeatures, secondDiveletFeatures);
		if (sentenceGenerator.canGenerate()){
			phrases.put("RiskyDive", sentenceGenerator.getSentence());
		}
	}	
}