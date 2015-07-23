package nlg;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import analytics.DiveFeatures;
import analytics.DiveletFeatures;
import microplanning.generators.DiveType;
import microplanning.planners.NLGSentence;
import microplanning.planners.NLGSentenceExceededNDL;
import microplanning.planners.NLGSentenceRiskyDive;
import microplanning.planners.NLGSentenceSafetyStop;
import microplanning.planners.NLGSentenceShallowDive;
import simplenlg.framework.DocumentElement;
import simplenlg.framework.NLGFactory;
import simplenlg.lexicon.Lexicon;
import simplenlg.phrasespec.SPhraseSpec;
import simplenlg.realiser.english.Realiser;

public class TextGenerator implements Reporter{
	
	private Lexicon lexicon = Lexicon.getDefaultLexicon();
    private Realiser realiser = new Realiser(lexicon);
    NLGFactory nlgFactory = new NLGFactory(lexicon);
	
	double diveDepth;
	int numOfDivelets;
	DiveletFeatures firstDiveletFeatures;
	DiveletFeatures secondDiveletFeatures; 
	
	private DiveFeatures diveFeatures;
	
	Map<String, SPhraseSpec> phrases = new TreeMap<String, SPhraseSpec>();
	
	public TextGenerator(double diveDepth, 
			int numOfDivelets, 
			DiveletFeatures firstDiveletFeatures, 
			DiveletFeatures secondDiveletFeatures) {
		this.diveDepth = diveDepth;
		this.numOfDivelets = numOfDivelets;
		this.firstDiveletFeatures = firstDiveletFeatures;
		this.secondDiveletFeatures = secondDiveletFeatures;
	}
	
	
	public TextGenerator(DiveFeatures diveFeatures) {
		this.diveFeatures = diveFeatures;
		this.diveDepth = diveFeatures.getDiveDepth();
		this.numOfDivelets = diveFeatures.getNumOfDivelets();
		this.firstDiveletFeatures = diveFeatures.getFirstDiveletFeatures();
		this.secondDiveletFeatures = diveFeatures.getSecondDiveletFeatures();
	}

	@Override
	public String generateText() {
		checkSafetyStop();
		
		checkShallowDive();
		
		checkRiskyDive();
		
		checkExceededNDL();
		
		List<DocumentElement> sentences = new ArrayList<DocumentElement>();
		for (Entry<String, SPhraseSpec> element : phrases.entrySet()) {
			DocumentElement s = nlgFactory.createSentence(element.getValue());
			sentences.add(s);
		}

		DocumentElement paragraph = nlgFactory.createParagraph(sentences);
				
		return realiser.realise(paragraph).getRealisation();
	}

	private void checkExceededNDL(){
		if (numOfDivelets > 0){
			if (numOfDivelets == 1){
				NLGSentence planner = new NLGSentenceExceededNDL(firstDiveletFeatures, DiveType.UNIQUE);
				ifCanGenerateAddSentence(planner, "Unique_ExceededNDL");
			} else {
				NLGSentence planner = new NLGSentenceExceededNDL(firstDiveletFeatures, DiveType.FIRST);
				ifCanGenerateAddSentence(planner, "First_ExceededNDL");
				
				planner = new NLGSentenceExceededNDL(firstDiveletFeatures, DiveType.SECOND);
				ifCanGenerateAddSentence(planner, "Second_ExceededNDL");
			}
		}
	}
	
	private void checkSafetyStop(){
		if (firstDiveletFeatures != null){
			NLGSentenceSafetyStop planner = new NLGSentenceSafetyStop(diveDepth, firstDiveletFeatures.getBottomTime());
			ifCanGenerateAddSentence(planner, "L10_SafetyStop");
		}
	}

	private void ifCanGenerateAddSentence(NLGSentence planner, String generatorName) {
		if (planner.canPlan()){
			phrases.put(generatorName, planner.getSentence());
		}
	}
	
	private void checkShallowDive(){
		NLGSentence planner = new NLGSentenceShallowDive(diveDepth);
		ifCanGenerateAddSentence(planner, "L01_ShallowDive");
	}
	
	private void checkRiskyDive(){
		NLGSentence planner = new NLGSentenceRiskyDive(firstDiveletFeatures, secondDiveletFeatures);
		ifCanGenerateAddSentence(planner, "L02_RiskyDive");
	}	
}