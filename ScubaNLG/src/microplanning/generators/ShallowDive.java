package microplanning.generators;

import simplenlg.framework.*;
import simplenlg.lexicon.*;
import simplenlg.phrasespec.*;
import simplenlg.features.*;

public class ShallowDive extends Generator{
	private boolean emphatic;
	
	public ShallowDive(boolean emphatic) {
		super();
		this.emphatic = emphatic;
	}

	public SPhraseSpec microplan(){
		Lexicon lexicon = Lexicon.getDefaultLexicon();
        NLGFactory nlgFactory = new NLGFactory(lexicon);
        
        SPhraseSpec p = nlgFactory.createClause();
        
        VPPhraseSpec verb = nlgFactory.createVerbPhrase("be");
        
        NPPhraseSpec subject = nlgFactory.createNounPhrase("this");
        NPPhraseSpec object = nlgFactory.createNounPhrase("dive");
        object.setDeterminer("a");
        
        if (emphatic)
        	object.addPreModifier("really shallow");
        else
        	object.addModifier("shallow");
        
        p.setSubject(subject);
        p.setObject(object);
        p.setVerb(verb);
        p.setFeature(Feature.TENSE, Tense.PAST);
		
        return p;
	}

}
