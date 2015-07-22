package microplanning;

import simplenlg.framework.*;
import simplenlg.lexicon.*;
import simplenlg.phrasespec.*;
import simplenlg.features.*;

enum Ascent{
	First,
	Second,
	Null;
}

public class AscentRate {
	public static SPhraseSpec microplan(Ascent ascent){
		Lexicon lexicon = Lexicon.getDefaultLexicon();
        NLGFactory nlgFactory = new NLGFactory(lexicon);
        
        SPhraseSpec p = nlgFactory.createClause();
        
        NPPhraseSpec subject = nlgFactory.createNounPhrase("ascent rate");
        
        if (ascent.equals(Ascent.First))
        	subject.setPreModifier("your first");	
        else if(ascent.equals(Ascent.Second))
        	subject.setPreModifier("your second");
        else
        	subject.setPreModifier("your");
        
        VPPhraseSpec verb = nlgFactory.createVerbPhrase("be");
        verb.addComplement("fine");
        p.setFeature(Feature.TENSE, Tense.PAST);
        
        return p;
        
	}
}
