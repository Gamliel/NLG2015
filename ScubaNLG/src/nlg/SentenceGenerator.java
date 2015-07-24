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
import microplanning.planners.NLGSentenceBadAscentRate;
import microplanning.planners.NLGSentenceDeeperDepth;
import microplanning.planners.NLGSentenceExceededNDL;
import microplanning.planners.NLGSentenceFineAscentRate;
import microplanning.planners.NLGSentenceFineDive;
import microplanning.planners.NLGSentenceQuickSuccession;
import microplanning.planners.NLGSentenceRiskyDive;
import microplanning.planners.NLGSentenceSafetyStop;
import microplanning.planners.NLGSentenceSecondDeeperFirst;
import microplanning.planners.NLGSentenceShallowDive;
import simplenlg.framework.DocumentElement;
import simplenlg.framework.NLGFactory;
import simplenlg.lexicon.Lexicon;
import simplenlg.phrasespec.SPhraseSpec;
import simplenlg.realiser.english.Realiser;

public class SentenceGenerator {
	
	private Lexicon lexicon = Lexicon.getDefaultLexicon();
    private Realiser realiser = new Realiser(lexicon);
    NLGFactory nlgFactory = new NLGFactory(lexicon);
	
	double diveDepth;
	int numOfDivelets;
	DiveletFeatures firstDiveletFeatures;
	DiveletFeatures secondDiveletFeatures;
	
	Map<String, SPhraseSpec> phrases = new TreeMap<String, SPhraseSpec>();
	
	public SentenceGenerator(double diveDepth, 
			int numOfDivelets, 
			DiveletFeatures firstDiveletFeatures, 
			DiveletFeatures secondDiveletFeatures) {
		this.diveDepth = diveDepth;
		this.numOfDivelets = numOfDivelets;
		this.firstDiveletFeatures = firstDiveletFeatures;
		this.secondDiveletFeatures = secondDiveletFeatures;
	}
	
	
	public SentenceGenerator(DiveFeatures diveFeatures) {
		this.diveDepth = diveFeatures.getDiveDepth();
		this.numOfDivelets = diveFeatures.getNumOfDivelets();
		this.firstDiveletFeatures = diveFeatures.getFirstDiveletFeatures();
		this.secondDiveletFeatures = diveFeatures.getSecondDiveletFeatures();
	}

	public Map<String, SPhraseSpec> generateSentences() {
		checkFineDive();
		
		checkRiskyDive();
		
		checkShallowDive();
		
		checkExceededNDL();
		
		checkDeeperDepth();
		
		checkFineAscentRate();
		
		checkBadAscentRate();
		
		checkSecondDeeperFirst();
		
		checkSafetyStop();
		
		checkQuickSuccession();
		
		return phrases;
	}

	private void checkExceededNDL(){
		if (numOfDivelets == 1){
			NLGSentence planner = new NLGSentenceExceededNDL(firstDiveletFeatures, DiveType.UNIQUE);
			ifCanGenerateAddSentence(planner, "Unique_ExceededNDL");
		} else if (numOfDivelets == 2){
			NLGSentence planner = new NLGSentenceExceededNDL(firstDiveletFeatures, DiveType.FIRST);
			ifCanGenerateAddSentence(planner, "First_ExceededNDL");
			
			planner = new NLGSentenceExceededNDL(secondDiveletFeatures, DiveType.SECOND);
			ifCanGenerateAddSentence(planner, "Second_ExceededNDL");
		}
	}
	
	private void checkDeeperDepth(){
		if (numOfDivelets > 0){
			NLGSentence planner = new NLGSentenceDeeperDepth(firstDiveletFeatures);
			ifCanGenerateAddSentence(planner, "DeeperDepth");
			
			if (!planner.canPlan() && secondDiveletFeatures != null){
				planner = new NLGSentenceDeeperDepth(secondDiveletFeatures);
				ifCanGenerateAddSentence(planner, "DeeperDepth");
			}
		}
	}
	
	private void checkFineAscentRate(){
		if (numOfDivelets == 1){
			NLGSentence planner = new NLGSentenceFineAscentRate(firstDiveletFeatures, DiveType.UNIQUE);
			ifCanGenerateAddSentence(planner, "Unique_FineAscentRate");
		} else if (numOfDivelets == 2){
			NLGSentence planner = new NLGSentenceFineAscentRate(firstDiveletFeatures, DiveType.FIRST);
			ifCanGenerateAddSentence(planner, "First_FineAscentRate");
			
			planner = new NLGSentenceFineAscentRate(secondDiveletFeatures, DiveType.SECOND);
			ifCanGenerateAddSentence(planner, "Second_FineAscentRate");
		}
	}
	
	private void checkBadAscentRate(){
		if (numOfDivelets == 1){
			NLGSentence planner = new NLGSentenceBadAscentRate(firstDiveletFeatures, DiveType.UNIQUE);
			ifCanGenerateAddSentence(planner, "Unique_BadAscentRate");
		} else if (numOfDivelets == 2){
			NLGSentence planner = new NLGSentenceBadAscentRate(firstDiveletFeatures, DiveType.FIRST);
			ifCanGenerateAddSentence(planner, "First_BadAscentRate");
			
			planner = new NLGSentenceBadAscentRate(secondDiveletFeatures, DiveType.SECOND);
			ifCanGenerateAddSentence(planner, "Second_BadAscentRate");
		}
	}
	
	private void checkFineDive(){
		if (numOfDivelets == 1){
			NLGSentence planner = new NLGSentenceFineDive(firstDiveletFeatures, DiveType.UNIQUE);
			ifCanGenerateAddSentence(planner, "Unique_FineDive");
		} else if (numOfDivelets == 2){
			NLGSentence planner = new NLGSentenceFineDive(firstDiveletFeatures, DiveType.FIRST);
			ifCanGenerateAddSentence(planner, "First_FineDive");
			
			planner = new NLGSentenceFineDive(firstDiveletFeatures, DiveType.SECOND);
			ifCanGenerateAddSentence(planner, "Second_FineDive");
		}
	}
	
	private void checkSafetyStop(){
		if (firstDiveletFeatures != null && numOfDivelets > 1){
			NLGSentenceSafetyStop planner = new NLGSentenceSafetyStop(diveDepth, firstDiveletFeatures.getBottomTime());
			ifCanGenerateAddSentence(planner, "L0010_SafetyStop");
		}
	}

	private void ifCanGenerateAddSentence(NLGSentence planner, String generatorName) {
		if (planner.canPlan()){
			phrases.put(generatorName, planner.getSentence());
		}
	}
	
	private void checkShallowDive(){
		NLGSentence planner = new NLGSentenceShallowDive(diveDepth);
		ifCanGenerateAddSentence(planner, "L0001_ShallowDive");
	}
	
	private void checkRiskyDive(){
		NLGSentence planner = new NLGSentenceRiskyDive(firstDiveletFeatures, secondDiveletFeatures);
		ifCanGenerateAddSentence(planner, "L0002_RiskyDive");
	}	
	
	private void checkSecondDeeperFirst(){
		if (numOfDivelets == 2){
			NLGSentence planner = new NLGSentenceSecondDeeperFirst(firstDiveletFeatures, secondDiveletFeatures);
			ifCanGenerateAddSentence(planner, "SecondDeeperFirst");
		}
	}
	
	private void checkQuickSuccession(){
		if (numOfDivelets == 2){
			NLGSentence planner = new NLGSentenceQuickSuccession();
			ifCanGenerateAddSentence(planner, "QuickSuccession");
		}
	}
}