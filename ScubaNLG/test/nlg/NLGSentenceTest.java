package nlg;

import static org.junit.Assert.*;

import sentence.NLGSentence;

public class NLGSentenceTest {

	@org.junit.Test
	public void acceptDiveDepthForShallowDive() {
		NLGSentence unit = new NLGSentence();
		unit.setDiveDepth(12.0);
		assertEquals(unit.getSentence(), "This was a shallow dive.");
	}
	
	@org.junit.Test
	public void acceptDiveDepthForAReallyShallowDive(){
		NLGSentence unit = new NLGSentence();
		unit.setDiveDepth(9.0);
		assertEquals(unit.getSentence(), "This was a really shallow dive.");
	}
}
