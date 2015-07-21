package nlg;

import static org.junit.Assert.*;

import sentence.NLGSentence;

public class NLGSentenceTest {

	@org.junit.Test
	public void acceptDiveDepth() {
		NLGSentence unit = new NLGSentence();
		unit.setDiveDepth(12.0);
		assertEquals(unit.getSentence(), "This dive was fine.");
	}

}
