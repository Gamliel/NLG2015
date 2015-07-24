package microplanning.generators;

import simplenlg.features.Feature;
import simplenlg.features.Tense;
import simplenlg.framework.NLGFactory;
import simplenlg.lexicon.Lexicon;
import simplenlg.phrasespec.NPPhraseSpec;
import simplenlg.phrasespec.SPhraseSpec;
import simplenlg.phrasespec.VPPhraseSpec;

public class BadAscentRate extends Generator{
private DiveType type;
	
	public BadAscentRate(DiveType type){
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
        verb.setFeature(Feature.NEGATED, true);
        verb.addComplement("acceptable");
        p.setFeature(Feature.TENSE, Tense.PAST);
        
        p.setSubject(subject);
        p.setVerb(verb);
        
        return p;
        
	}
}
