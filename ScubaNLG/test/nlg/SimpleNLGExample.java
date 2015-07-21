package nlg;

import static org.junit.Assert.*;

import simplenlg.features.Feature;
import simplenlg.features.Tense;
import simplenlg.framework.NLGFactory;
import simplenlg.lexicon.Lexicon;
import simplenlg.phrasespec.NPPhraseSpec;
import simplenlg.phrasespec.SPhraseSpec;
import simplenlg.phrasespec.VPPhraseSpec;
import simplenlg.realiser.english.Realiser;

public class SimpleNLGExample {

	@org.junit.Test
	public void shouldRealiseSimpleSentence() {

		Lexicon lexicon = Lexicon.getDefaultLexicon();
        NLGFactory nlgFactory = new NLGFactory(lexicon);
        Realiser realiser = new Realiser(lexicon);
        
        SPhraseSpec p = nlgFactory.createClause();
        
        NPPhraseSpec subject = nlgFactory.createNounPhrase("this");
        NPPhraseSpec object = nlgFactory.createNounPhrase("dive");
        object.setDeterminer("a");
        object.addModifier("shallow");
        VPPhraseSpec verb = nlgFactory.createVerbPhrase("be");
        
        
        p.setSubject(subject);
        p.addFrontModifier("however");
        p.setObject(object);
        p.setVerb(verb);
        p.setFeature(Feature.TENSE, Tense.PAST);
        
        String output = realiser.realiseSentence(p);
        assertEquals(output, "However this was a shallow dive.");
	}

}
