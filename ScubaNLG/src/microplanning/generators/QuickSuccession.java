package microplanning.generators;

import simplenlg.features.Feature;
import simplenlg.features.Tense;
import simplenlg.framework.NLGFactory;
import simplenlg.lexicon.Lexicon;
import simplenlg.phrasespec.NPPhraseSpec;
import simplenlg.phrasespec.PPPhraseSpec;
import simplenlg.phrasespec.SPhraseSpec;
import simplenlg.phrasespec.VPPhraseSpec;

public class QuickSuccession extends Generator{

	@Override
	public SPhraseSpec generate() {
		Lexicon lexicon = Lexicon.getDefaultLexicon();
        NLGFactory nlgFactory = new NLGFactory(lexicon);
        
        SPhraseSpec p = nlgFactory.createClause();
        
        NPPhraseSpec you = nlgFactory.createNounPhrase("you");
        VPPhraseSpec perform = nlgFactory.createVerbPhrase("perform");
        NPPhraseSpec twoDives = nlgFactory.createNounPhrase("two dives");
        
        PPPhraseSpec inQuick = nlgFactory.createPrepositionPhrase("in");
        NPPhraseSpec succession = nlgFactory.createNounPhrase("succession");
        succession.addPreModifier("quick");
        inQuick.addComplement(succession);
        
        p.setSubject(you);
        p.setVerb(perform);
        p.setObject(twoDives);
        p.addComplement(inQuick);
        p.setFeature(Feature.TENSE, Tense.PAST);
        
        SPhraseSpec p2 = nlgFactory.createClause();
        
        NPPhraseSpec thiss = nlgFactory.createNounPhrase("this");
        VPPhraseSpec be = nlgFactory.createVerbPhrase("be");
        be.setFeature(Feature.NEGATED, true);
        be.addComplement("recommended");
        
        p2.setSubject(thiss);
        p2.setVerb(be);
        p2.setFeature(Feature.COMPLEMENTISER, "and");
        
        p.addComplement(p2);
        
		return p;
	}

}
