package nlg;

import static org.junit.Assert.*;

import analytics.DiveletFeatures;
import microplanning.generators.DiveType;
import microplanning.generators.FineAscentRate.AscentOrder;
import microplanning.planners.NLGSentence;
import microplanning.planners.NLGSentenceDeeperDepth;
import microplanning.planners.NLGSentenceExceededNDL;
import microplanning.planners.NLGSentenceFineAscentRate;
import microplanning.planners.NLGSentenceFineDive;
import microplanning.planners.NLGSentenceRiskyDive;
import microplanning.planners.NLGSentenceSafetyStop;
import microplanning.planners.NLGSentenceSecondDeeperFirst;
import microplanning.planners.NLGSentenceShallowDive;
import simplenlg.lexicon.Lexicon;
import simplenlg.realiser.english.Realiser;

public class NLGSentenceTest {
	
	private Lexicon lexicon = Lexicon.getDefaultLexicon();
    private Realiser realiser = new Realiser(lexicon);

	@org.junit.Test
	public void acceptDiveDepthForShallowDive() {
        NLGSentence unit = new NLGSentenceShallowDive(12.0);
		assertEquals(unit.canPlan(), true);
		assertEquals(realiser.realiseSentence(unit.getSentence()), "This was a shallow dive.");
	}
	
	@org.junit.Test
	public void acceptDiveDepthForAReallyShallowDive() {
        NLGSentence unit = new NLGSentenceShallowDive(9.0);
		assertEquals(unit.canPlan(), true);
		assertEquals(realiser.realiseSentence(unit.getSentence()), "This was a really shallow dive.");
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
        NLGSentence unit = new NLGSentenceFineAscentRate(diveletFeature, AscentOrder.Null);
		assertEquals(realiser.realiseSentence(unit.getSentence()), "Your ascent rate was fine.");
	}
	
	@org.junit.Test
	public void acceptFineFirstAscentRate(){
		DiveletFeatures diveletFeature = new DiveletFeatures();
		diveletFeature.setAscentSpeed(30);
		diveletFeature.setExcessAscentSpeed(0);
        NLGSentence unit = new NLGSentenceFineAscentRate(diveletFeature, AscentOrder.First);
		assertEquals(realiser.realiseSentence(unit.getSentence()), "Your first ascent rate was fine.");
	}
	
	@org.junit.Test
	public void acceptFineSecondAscentRate(){
		DiveletFeatures diveletFeature = new DiveletFeatures();
		diveletFeature.setAscentSpeed(30);
		diveletFeature.setExcessAscentSpeed(0);
        NLGSentence unit = new NLGSentenceFineAscentRate(diveletFeature, AscentOrder.Second);
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
        NLGSentence unit = new NLGSentenceFineDive(diveletFeature);
        assertEquals(unit.canPlan(), true);
		assertEquals(realiser.realiseSentence(unit.getSentence()), "This dive was fine.");
	}
	
	@org.junit.Test
	public void acceptExceededNDLOnUniqueDive(){
		DiveletFeatures diveletFeature = new DiveletFeatures();
		diveletFeature.setExcessBottomTime(12L);
		diveletFeature.setBottomTime(20L);
		NLGSentence unit = new NLGSentenceExceededNDL(diveletFeature, DiveType.UNIQUE);
        assertEquals(unit.canPlan(), true);
		assertEquals(realiser.realiseSentence(unit.getSentence()), "At this depth, you stayed longer than the NDL by 12mins which was 150% longer.");
	}
	
	@org.junit.Test
	public void acceptExceededNDLOnFirstDive(){
		DiveletFeatures diveletFeature = new DiveletFeatures();
		diveletFeature.setExcessBottomTime(12L);
		diveletFeature.setBottomTime(20L);
		NLGSentence unit = new NLGSentenceExceededNDL(diveletFeature, DiveType.FIRST);
        assertEquals(unit.canPlan(), true);
		assertEquals(realiser.realiseSentence(unit.getSentence()), "On your first dive you stayed longer than the NDL by 12mins which was 150% longer.");
	}
	
	@org.junit.Test
	public void acceptExceededNDLOnSecondDive(){
		DiveletFeatures diveletFeature = new DiveletFeatures();
		diveletFeature.setExcessBottomTime(12L);
		diveletFeature.setBottomTime(20L);
		NLGSentence unit = new NLGSentenceExceededNDL(diveletFeature, DiveType.SECOND);
        assertEquals(unit.canPlan(), true);
		assertEquals(realiser.realiseSentence(unit.getSentence()), "On your second dive you stayed longer than the NDL by 12mins which was 150% longer.");
	}
	
	@org.junit.Test
	public void acceptSecondDeeperFirst(){
		DiveletFeatures firstDivelet = new DiveletFeatures();
		firstDivelet.setDiveDepth(30);
		DiveletFeatures secondDivelet = new DiveletFeatures();
		secondDivelet.setDiveDepth(45);
		
		NLGSentence unit = new NLGSentenceSecondDeeperFirst(firstDivelet, secondDivelet);
        assertEquals(unit.canPlan(), true);
		assertEquals(realiser.realiseSentence(unit.getSentence()), "Your second dive was deeper than the first one which is really not recommended.");
	}
}
