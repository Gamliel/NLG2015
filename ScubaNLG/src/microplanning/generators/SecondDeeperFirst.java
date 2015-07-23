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
        
        PPPhraseSpec than = nlgFactory.createPrepositionPhrase("than");
        NPPhraseSpec firstDive = nlgFactory.createNounPhrase("one");
        firstDive.setDeterminer("the");
        firstDive.setPreModifier("first");
        than.addComplement(firstDive);
        
        s.setSubject(secondDive);
        s.setVerb(be);
        s.addComplement(than);
        s.setFeature(Feature.TENSE, Tense.PAST);
        
        SPhraseSpec which = nlgFactory.createClause();
        VPPhraseSpec beNot = nlgFactory.createVerbPhrase("be");
        beNot.setFeature(Feature.NEGATED, true);
        
        NLGElement recommended = nlgFactory.createAdjectivePhrase("recommended");
        beNot.addComplement(recommended);
        beNot.addModifier("really");
        which.setVerb(beNot);
        
        which.setFeature(Feature.COMPLEMENTISER, "which");
        s.addComplement(which);
        
		return s;
	}

}
