package microplanning.generators;

import simplenlg.features.Feature;
import simplenlg.features.Tense;
import simplenlg.framework.LexicalCategory;
import simplenlg.framework.NLGElement;
import simplenlg.framework.NLGFactory;
import simplenlg.lexicon.Lexicon;
import simplenlg.phrasespec.NPPhraseSpec;
import simplenlg.phrasespec.PPPhraseSpec;
import simplenlg.phrasespec.SPhraseSpec;
import simplenlg.phrasespec.VPPhraseSpec;

public class DeeperDepth extends Generator{
	private String objREG;
	
	public DeeperDepth(String objREG) {
		super();
		this.objREG = objREG;
	}
	
	@Override
	public SPhraseSpec generate(){
		Lexicon lexicon = Lexicon.getDefaultLexicon();
        NLGFactory nlgFactory = new NLGFactory(lexicon);
        
        SPhraseSpec p1 = nlgFactory.createClause();
        
        NPPhraseSpec subject = nlgFactory.createNounPhrase("you");
        NPPhraseSpec mod = nlgFactory.createNounPhrase(objREG);
        NLGElement elem = nlgFactory.createInflectedWord("deep", LexicalCategory.ADVERB);
        elem.setFeature(Feature.IS_COMPARATIVE, true);
        mod.addPostModifier(elem);
        
        VPPhraseSpec verb = nlgFactory.createVerbPhrase("go");
        
        p1.setSubject(subject);
        p1.setVerb(verb);
        p1.addComplement(mod);
        p1.setFeature(Feature.TENSE, Tense.PAST);
        
        NPPhraseSpec exceededLimit = nlgFactory.createNounPhrase("depth limit"); 
        exceededLimit.setDeterminer("the");
        exceededLimit.setPreModifier("PADI recommended");
        
        NPPhraseSpec depthLimit = nlgFactory.createNounPhrase("42m");
        PPPhraseSpec pp = nlgFactory.createPrepositionPhrase();
        pp.setPreposition("of");
        pp.addComplement(depthLimit);
        
        exceededLimit.addComplement(pp);
        
        PPPhraseSpec than = nlgFactory.createPrepositionPhrase();
        than.setPreposition("than");
        than.addComplement(exceededLimit);
        
        p1.addComplement(than);
		
		return p1;
	}
}
