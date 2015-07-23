package microplanning.generators;

import simplenlg.features.Feature;
import simplenlg.framework.LexicalCategory;
import simplenlg.framework.NLGElement;
import simplenlg.framework.NLGFactory;
import simplenlg.lexicon.Lexicon;
import simplenlg.phrasespec.NPPhraseSpec;
import simplenlg.phrasespec.SPhraseSpec;
import simplenlg.phrasespec.VPPhraseSpec;

public class SecondDeeperFirst extends Generator{

	@Override
	public SPhraseSpec generate() {
		Lexicon lexicon = Lexicon.getDefaultLexicon();
        NLGFactory nlgFactory = new NLGFactory(lexicon);
        
        SPhraseSpec s = nlgFactory.createClause();
        
        NPPhraseSpec secondDive = nlgFactory.createNounPhrase("dive");
        secondDive.setDeterminer("your");
        secondDive.setPreModifier("second");
        
        VPPhraseSpec be = nlgFactory.createVerbPhrase("be");
        NLGElement predicative = nlgFactory.createInflectedWord("deep", LexicalCategory.ADVERB);
        predicative.setFeature(Feature.IS_COMPARATIVE, true);
        be.addComplement(predicative);
        
        
        
        s.setSubject(secondDive);
        s.setVerb(be);
		return s;
	}

}
