package microplanning.generators;

import simplenlg.framework.*;
import simplenlg.lexicon.*;
import simplenlg.phrasespec.*;
import simplenlg.features.*;

public class FineAscentRate extends Generator{
	private DiveType type;
	
	public FineAscentRate(DiveType type){
		this.type = type;
	}
	
	@Override
	public SPhraseSpec generate(){
		Lexicon lexicon = Lexicon.getDefaultLexicon();
        NLGFactory nlgFactory = new NLGFactory(lexicon);
        
        SPhraseSpec p = nlgFactory.createClause();
        
        NPPhraseSpec subject = nlgFactory.createNounPhrase("ascent rate");
        subject.setDeterminer("your");
        
        if (type.equals(DiveType.FIRST))
        	subject.setPreModifier("first");	
        else if(type.equals(DiveType.SECOND))
        	subject.setPreModifier("second");
        
        VPPhraseSpec verb = nlgFactory.createVerbPhrase("be");
        verb.addComplement("fine");
        p.setFeature(Feature.TENSE, Tense.PAST);
        
        p.setSubject(subject);
        p.setVerb(verb);
        
        return p;
        
	}
}
