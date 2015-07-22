package nlg;

import static org.junit.Assert.*;

import analytics.DiveletFeatures;
import microplanning.NLGSentence;
import microplanning.NLGSentenceRiskyDive;
import microplanning.NLGSentenceSafetyStop;
import microplanning.NLGSentenceShallowDive;
import simplenlg.lexicon.Lexicon;
import simplenlg.realiser.english.Realiser;

public class NLGSentenceTest {
	
	private Lexicon lexicon = Lexicon.getDefaultLexicon();
    private Realiser realiser = new Realiser(lexicon);

	@org.junit.Test
	public void acceptDiveDepthForShallowDive() {
        NLGSentence unit = new NLGSentenceShallowDive(12.0);
		assertEquals(unit.canGenerate(), true);
		assertEquals(realiser.realiseSentence(unit.getSentence()), "This was a shallow dive.");
	}
	
	@org.junit.Test
	public void acceptDiveDepthForAReallyShallowDive() {
        NLGSentence unit = new NLGSentenceShallowDive(9.0);
		assertEquals(unit.canGenerate(), true);
		assertEquals(realiser.realiseSentence(unit.getSentence()), "This was a really shallow dive.");
	}
	
	
	@org.junit.Test
	public void acceptShouldHaveMadeAStop(){
        NLGSentence unit = new NLGSentenceSafetyStop(85,25);
		assertEquals(unit.canGenerate(), true);
		assertEquals(realiser.realiseSentence(unit.getSentence()), "You should have made a safety stop on your way up.");
	}
	
	@org.junit.Test
	public void acceptRiskyDive(){
		DiveletFeatures firstDiveletFeature = new DiveletFeatures();
		firstDiveletFeature.setExcessBottomTime(6);
		firstDiveletFeature.setExcessDiveDepth(-1.5);
		firstDiveletFeature.setBottomTime(14);
		firstDiveletFeature.setDiveDepth(40.5);
        NLGSentence unit = new NLGSentenceRiskyDive(firstDiveletFeature);
		assertEquals(realiser.realiseSentence(unit.getSentence()), "This was a risky dive.");
	}
	
	@org.junit.Test
	public void acceptReallyRiskyDive(){
		DiveletFeatures firstDiveletFeature = new DiveletFeatures();
		firstDiveletFeature.setExcessBottomTime(12);
		firstDiveletFeature.setExcessDiveDepth(-0.7);
		firstDiveletFeature.setBottomTime(20);
		firstDiveletFeature.setDiveDepth(41.3);
        NLGSentence unit = new NLGSentenceRiskyDive(firstDiveletFeature);
		assertEquals(realiser.realiseSentence(unit.getSentence()), "This was a really risky dive.");
	}
}
