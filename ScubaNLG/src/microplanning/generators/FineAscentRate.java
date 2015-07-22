package microplanning.generators;

import simplenlg.framework.*;
import simplenlg.lexicon.*;
import simplenlg.phrasespec.*;
import simplenlg.features.*;

public class FineAscentRate extends Generator{
	private AscentOrder ascent;

	public enum AscentOrder{
		First,
		Second,
		Null;
	}
	
	public FineAscentRate(AscentOrder ascent){
		this.ascent = ascent;
	}
	
	public SPhraseSpec microplan(){
		Lexicon lexicon = Lexicon.getDefaultLexicon();
        NLGFactory nlgFactory = new NLGFactory(lexicon);
        
        SPhraseSpec p = nlgFactory.createClause();
        
        NPPhraseSpec subject = nlgFactory.createNounPhrase("ascent rate");
        subject.setDeterminer("your");
        
        if (ascent.equals(AscentOrder.First))
        	subject.setPreModifier("first");	
        else if(ascent.equals(AscentOrder.Second))
        	subject.setPreModifier("second");
        
        VPPhraseSpec verb = nlgFactory.createVerbPhrase("be");
        verb.addComplement("fine");
        p.setFeature(Feature.TENSE, Tense.PAST);
        
        p.setSubject(subject);
        p.setVerb(verb);
        
        return p;
        
	}
}
