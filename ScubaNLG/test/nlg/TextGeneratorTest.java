package nlg;

import static org.junit.Assert.assertEquals;

import analytics.DiveletFeatures;
import microplanning.generators.DiveType;
import microplanning.planners.NLGSentence;
import microplanning.planners.NLGSentenceDeeperDepth;
import microplanning.planners.NLGSentenceExceededNDL;
import microplanning.planners.NLGSentenceFineAscentRate;
import microplanning.planners.NLGSentenceFineDive;
import microplanning.planners.NLGSentenceRiskyDive;
import microplanning.planners.NLGSentenceSafetyStop;
import simplenlg.lexicon.Lexicon;
import simplenlg.realiser.english.Realiser;

public class TextGeneratorTest {
	
	private Lexicon lexicon = Lexicon.getDefaultLexicon();
    private Realiser realiser = new Realiser(lexicon);

	@org.junit.Test
	public void acceptDiveDepthForShallowDive() {
		SentenceGenerator textGenerator = new SentenceGenerator(12.0, 0, null, null);
		assertEquals(new DocumentPlanner(textGenerator.generateSentences()).generateText().contains("This was a shallow dive."), true);
	}
	
	@org.junit.Test
	public void acceptDiveDepthForAReallyShallowDive() {
		SentenceGenerator textGenerator = new SentenceGenerator(9.0, 0, null, null);
		assertEquals(new DocumentPlanner(textGenerator.generateSentences()).generateText().contains("This was a really shallow dive."),true);
	}
	
	
	@org.junit.Test
	public void acceptShouldHaveMadeAStop(){
        NLGSentence unit = new NLGSentenceSafetyStop(85,25);
		assertEquals(unit.canPlan(), true);
		assertEquals(realiser.realiseSentence(unit.getSentence()), "You should have made a safety stop on your way up.");
	}
	
	@org.junit.Test
	public void acceptRiskyDive(){
		DiveletFeatures firstDiveletFeature = new DiveletFeatures();
		firstDiveletFeature.setExcessBottomTime(6);
		firstDiveletFeature.setExcessDiveDepth(-1.5);
		firstDiveletFeature.setBottomTime(14);
		firstDiveletFeature.setDiveDepth(40.5);
        NLGSentence unit = new NLGSentenceRiskyDive(firstDiveletFeature, firstDiveletFeature);
		assertEquals(realiser.realiseSentence(unit.getSentence()), "This was a risky dive.");
	}
	
	@org.junit.Test
	public void acceptReallyRiskyDive(){
		DiveletFeatures firstDiveletFeature = new DiveletFeatures();
		firstDiveletFeature.setExcessBottomTime(12);
		firstDiveletFeature.setExcessDiveDepth(-0.7);
		firstDiveletFeature.setBottomTime(20);
		firstDiveletFeature.setDiveDepth(41.3);
        NLGSentence unit = new NLGSentenceRiskyDive(firstDiveletFeature, firstDiveletFeature);
		assertEquals(realiser.realiseSentence(unit.getSentence()), "This was a really risky dive.");
	}
	
	@org.junit.Test
	public void acceptFineAscentRate(){
		DiveletFeatures diveletFeature = new DiveletFeatures();
		diveletFeature.setAscentSpeed(30);
		diveletFeature.setExcessAscentSpeed(0);
        NLGSentence unit = new NLGSentenceFineAscentRate(diveletFeature, DiveType.UNIQUE);
		assertEquals(realiser.realiseSentence(unit.getSentence()), "Your ascent rate was fine.");
	}
	
	@org.junit.Test
	public void acceptFineFirstAscentRate(){
		DiveletFeatures diveletFeature = new DiveletFeatures();
		diveletFeature.setAscentSpeed(30);
		diveletFeature.setExcessAscentSpeed(0);
        NLGSentence unit = new NLGSentenceFineAscentRate(diveletFeature, DiveType.FIRST);
		assertEquals(realiser.realiseSentence(unit.getSentence()), "Your first ascent rate was fine.");
	}
	
	@org.junit.Test
	public void acceptFineSecondAscentRate(){
		DiveletFeatures diveletFeature = new DiveletFeatures();
		diveletFeature.setAscentSpeed(30);
		diveletFeature.setExcessAscentSpeed(0);
        NLGSentence unit = new NLGSentenceFineAscentRate(diveletFeature, DiveType.SECOND);
		assertEquals(realiser.realiseSentence(unit.getSentence()), "Your second ascent rate was fine.");
	}
	
	@org.junit.Test
	public void acceptDeeperDepth(){
		DiveletFeatures diveletFeature = new DiveletFeatures();
		diveletFeature.setDiveDepth(44);
		diveletFeature.setExcessDiveDepth(2);
        NLGSentence unit = new NLGSentenceDeeperDepth(diveletFeature);
		assertEquals(realiser.realiseSentence(unit.getSentence()), "You went 2m deeper than the PADI recommended depth limit of 42m.");
	}

	@org.junit.Test
	public void acceptFineDive(){
		DiveletFeatures diveletFeature = new DiveletFeatures();
		diveletFeature.setExcessAscentSpeed(0);
		diveletFeature.setExcessBottomTime(0);
		diveletFeature.setExcessDiveDepth(0);
        NLGSentence unit = new NLGSentenceFineDive(diveletFeature, DiveType.UNIQUE);
        assertEquals(unit.canPlan(), true);
		assertEquals(realiser.realiseSentence(unit.getSentence()), "This dive was fine.");
	}
	
	@org.junit.Test
	public void acceptExceededNDL(){
		DiveletFeatures diveletFeature = new DiveletFeatures();
		diveletFeature.setExcessBottomTime(12L);
		diveletFeature.setBottomTime(20L);
		SentenceGenerator textGenerator = new SentenceGenerator(20.0, 1, diveletFeature, null);
		assertEquals(true,new DocumentPlanner(textGenerator.generateSentences()).generateText().contains("At this depth you stayed longer than the NDL by 12mins which was 150% longer."));		
	}
}
