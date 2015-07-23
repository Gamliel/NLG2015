package microplanning.generators;

import simplenlg.framework.*;
import simplenlg.lexicon.*;
import simplenlg.phrasespec.*;
import simplenlg.features.*;

public class FineDive extends Generator{
	private DiveType type;
	
	public FineDive(DiveType type){
		this.type = type;
	}
	
	@Override
	public SPhraseSpec generate(){
		Lexicon lexicon = Lexicon.getDefaultLexicon();
        NLGFactory nlgFactory = new NLGFactory(lexicon);
        
        SPhraseSpec p = nlgFactory.createClause();
        
        NPPhraseSpec subject = nlgFactory.createNounPhrase("dive");
        if (type.equals(DiveType.UNIQUE)){
        	subject.setDeterminer("this");
        } else if (type.equals(DiveType.FIRST)){
        	subject.setDeterminer("your");
        	subject.addPreModifier("first");
        } else {
        	subject.setDeterminer("your");
        	subject.addPreModifier("second");
        }
                
        VPPhraseSpec verb = nlgFactory.createVerbPhrase("be");
        verb.addComplement("fine");
        p.setFeature(Feature.TENSE, Tense.PAST);
        
        p.setSubject(subject);
        p.setVerb(verb);
        
        return p;
        
	}
}
