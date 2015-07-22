package nlg;


import java.util.HashMap;

import analytics.DiveletFeatures;
import microplanning.generators.SafetyStop;
import microplanning.planners.NLGSentence;
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
		NLGSentenceSafetyStop planner = new NLGSentenceSafetyStop(diveDepth, firstDiveletFeatures.getBottomTime());
		ifCanGenerateAddSentence(planner, "SafetyStop");
	}

	private void ifCanGenerateAddSentence(NLGSentence planner, String generatorName) {
		if (planner.canGenerate()){
			phrases.put(generatorName, planner.getSentence());
		}
	}
	
	private void checkShallowDive(){
		NLGSentence planner = new NLGSentenceShallowDive(diveDepth);
		ifCanGenerateAddSentence(planner, "ShallowDive");
	}
	
	private void checkRiskyDive(){
		NLGSentence planner = new NLGSentenceRiskyDive(firstDiveletFeatures, secondDiveletFeatures);
		ifCanGenerateAddSentence(planner, "RiskyDive");
	}	
}