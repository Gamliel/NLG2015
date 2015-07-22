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
}
