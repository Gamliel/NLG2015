package microplanning;

import simplenlg.features.Feature;
import simplenlg.framework.NLGFactory;
import simplenlg.lexicon.Lexicon;
import simplenlg.phrasespec.NPPhraseSpec;
import simplenlg.phrasespec.PPPhraseSpec;
import simplenlg.phrasespec.SPhraseSpec;
import simplenlg.phrasespec.VPPhraseSpec;

public class SafetyStop {
	public static SPhraseSpec microplan(){
		Lexicon lexicon = Lexicon.getDefaultLexicon();
        NLGFactory nlgFactory = new NLGFactory(lexicon);
        
        SPhraseSpec p = nlgFactory.createClause();
        
        NPPhraseSpec subject = nlgFactory.createNounPhrase("you");
        NPPhraseSpec object = nlgFactory.createNounPhrase("safety stop");
        object.setDeterminer("a");
        
        VPPhraseSpec verb = nlgFactory.createVerbPhrase("make");
        verb.setFeature(Feature.MODAL, "should");
        verb.setFeature(Feature.PERFECT, true);
        
        NPPhraseSpec adverb = nlgFactory.createNounPhrase("way up");
        adverb.setDeterminer("your");
        
        PPPhraseSpec pp = nlgFactory.createPrepositionPhrase();
        pp.addComplement(adverb);
        pp.setPreposition("on");
        
        p.setSubject(subject);
        p.setVerb(verb);
        p.setObject(object);
        p.addComplement(pp);
        return p;
	}

}
