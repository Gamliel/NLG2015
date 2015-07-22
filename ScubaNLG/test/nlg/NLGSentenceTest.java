package nlg;

import static org.junit.Assert.*;

import com.mysql.jdbc.NotImplemented;

import analytics.DiveletFeatures;
import microplanning.NLGSentence;
import microplanning.NLGSentenceShallowDive;
import simplenlg.lexicon.Lexicon;
import simplenlg.realiser.english.Realiser;

public class NLGSentenceTest {

	@org.junit.Test
	public void acceptDiveDepthForShallowDive() throws NotImplemented {
		Lexicon lexicon = Lexicon.getDefaultLexicon();
        Realiser realiser = new Realiser(lexicon);

        NLGSentence unit = new NLGSentenceShallowDive(12.0);
		assertEquals(unit.canGenerate(), true);
		assertEquals(realiser.realiseSentence(unit.getSentence()), "This was a shallow dive.");
	}
	
	@org.junit.Test
	public void acceptDiveDepthForAReallyShallowDive() throws NotImplemented{
		Lexicon lexicon = Lexicon.getDefaultLexicon();
        Realiser realiser = new Realiser(lexicon);

        NLGSentence unit = new NLGSentenceShallowDive(9.0);
		assertEquals(unit.canGenerate(), true);
		assertEquals(realiser.realiseSentence(unit.getSentence()), "This was a really shallow dive.");
	}
	
	
	@org.junit.Test
	public void acceptShouldHaveMadeAStop() throws NotImplemented{
		Lexicon lexicon = Lexicon.getDefaultLexicon();
        Realiser realiser = new Realiser(lexicon);

        NLGSentence unit = new NLGSentence();
		DiveletFeatures firstDiveletFeature = new DiveletFeatures();
		firstDiveletFeature.setBottomTime(25);
		unit.setFeatures(85,firstDiveletFeature);
		assertEquals(realiser.realiseSentence(unit.getSentence()), "You should have made a safety stop on your way up.");
	}
	
	@org.junit.Test
	public void acceptRiskyDive() throws NotImplemented{
		Lexicon lexicon = Lexicon.getDefaultLexicon();
        Realiser realiser = new Realiser(lexicon);

        NLGSentence unit = new NLGSentence();
		DiveletFeatures firstDiveletFeature = new DiveletFeatures();
		firstDiveletFeature.setExcessBottomTime(6);
		firstDiveletFeature.setExcessDiveDepth(-1.5);
		firstDiveletFeature.setBottomTime(14);
		firstDiveletFeature.setDiveDepth(40.5);
		unit.setFeatures(40.5,firstDiveletFeature);
		assertEquals(realiser.realiseSentence(unit.getSentence()), "This was a risky dive.");
	}
	
	@org.junit.Test
	public void acceptReallyRiskyDive() throws NotImplemented{
		Lexicon lexicon = Lexicon.getDefaultLexicon();
        Realiser realiser = new Realiser(lexicon);

        NLGSentence unit = new NLGSentence();
		DiveletFeatures firstDiveletFeature = new DiveletFeatures();
		firstDiveletFeature.setExcessBottomTime(12);
		firstDiveletFeature.setExcessDiveDepth(-0.7);
		firstDiveletFeature.setBottomTime(20);
		firstDiveletFeature.setDiveDepth(41.3);
		unit.setFeatures(41.3,firstDiveletFeature);
		assertEquals(realiser.realiseSentence(unit.getSentence()), "This was a really risky dive.");
	}
}
