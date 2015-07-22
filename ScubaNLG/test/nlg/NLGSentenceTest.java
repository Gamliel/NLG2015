package nlg;

import static org.junit.Assert.*;

import analytics.DiveletFeatures;
import microplanning.NLGSentence;

public class NLGSentenceTest {

	@org.junit.Test
	public void acceptDiveDepthForShallowDive() {
		NLGSentence unit = new NLGSentence();
		DiveletFeatures firstDiveletFeature = new DiveletFeatures();
		firstDiveletFeature.setBottomTime(5);
		unit.setFeatures(12.0,firstDiveletFeature);
		assertEquals(unit.getSentence(), "This was a shallow dive.");
	}
	
	@org.junit.Test
	public void acceptDiveDepthForAReallyShallowDive(){
		NLGSentence unit = new NLGSentence();
		DiveletFeatures firstDiveletFeature = new DiveletFeatures();
		firstDiveletFeature.setBottomTime(5);
		unit.setFeatures(9.0,firstDiveletFeature);
		assertEquals(unit.getSentence(), "This was a really shallow dive.");
	}
	
	
	@org.junit.Test
	public void acceptShouldHaveMadeAStop(){
		NLGSentence unit = new NLGSentence();
		DiveletFeatures firstDiveletFeature = new DiveletFeatures();
		firstDiveletFeature.setBottomTime(25);
		unit.setFeatures(85,firstDiveletFeature);
		assertEquals(unit.getSentence(), "You should have made a safety stop on your way up.");
	}
	
	@org.junit.Test
	public void acceptRiskyDive(){
		NLGSentence unit = new NLGSentence();
		DiveletFeatures firstDiveletFeature = new DiveletFeatures();
		firstDiveletFeature.setExcessBottomTime(6);
		firstDiveletFeature.setExcessDiveDepth(-1.5);
		firstDiveletFeature.setBottomTime(14);
		firstDiveletFeature.setDiveDepth(40.5);
		unit.setFeatures(40.5,firstDiveletFeature);
		assertEquals(unit.getSentence(), "This was a risky dive.");
	}
	
	@org.junit.Test
	public void acceptReallyRiskyDive(){
		NLGSentence unit = new NLGSentence();
		DiveletFeatures firstDiveletFeature = new DiveletFeatures();
		firstDiveletFeature.setExcessBottomTime(12);
		firstDiveletFeature.setExcessDiveDepth(-0.7);
		firstDiveletFeature.setBottomTime(20);
		firstDiveletFeature.setDiveDepth(41.3);
		unit.setFeatures(41.3,firstDiveletFeature);
		assertEquals(unit.getSentence(), "This was a really risky dive.");
	}
}
