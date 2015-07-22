package microplanning.generators;

import simplenlg.features.Feature;
import simplenlg.features.Tense;
import simplenlg.framework.NLGFactory;
import simplenlg.lexicon.Lexicon;
import simplenlg.phrasespec.NPPhraseSpec;
import simplenlg.phrasespec.SPhraseSpec;
import simplenlg.phrasespec.VPPhraseSpec;

public class Depth {
	public static SPhraseSpec microplan(String objREG){
		Lexicon lexicon = Lexicon.getDefaultLexicon();
        NLGFactory nlgFactory = new NLGFactory(lexicon);
        
        SPhraseSpec p = nlgFactory.createClause();
        
        NPPhraseSpec subject = nlgFactory.createNounPhrase("you");
        NPPhraseSpec object = nlgFactory.createNounPhrase(objREG);
        
        VPPhraseSpec verb = nlgFactory.createVerbPhrase("go");
        verb.setFeature(Feature.TENSE, Tense.PAST);
        
        p.setSubject(subject);
        p.setVerb(verb);
        p.setObject(object);
		
		return null;
	}
}
