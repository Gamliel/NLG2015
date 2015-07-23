package microplanning.generators;

import simplenlg.framework.*;
import simplenlg.lexicon.*;
import simplenlg.phrasespec.*;
import simplenlg.features.*;

public class ExceededNDL extends Generator{
	private double bottomTime; 
	private double excessBottomTime;
	private DiveType type;
	
	public ExceededNDL(long bottomTime, long excessBottomTime, DiveType type) {
		this.bottomTime = (double) bottomTime;
		this.excessBottomTime = (double) excessBottomTime;
		this.type = type;
	}
	
	@Override
	public SPhraseSpec generate() {
		Lexicon lexicon = Lexicon.getDefaultLexicon();
        NLGFactory nlgFactory = new NLGFactory(lexicon);
        
        PPPhraseSpec preModifier = nlgFactory.createPrepositionPhrase();
        if (type.equals(DiveType.UNIQUE)){
        	NPPhraseSpec thisDepth = nlgFactory.createNounPhrase("depth");
            thisDepth.setDeterminer("this");
            preModifier.setPreposition("at");
            preModifier.addComplement(thisDepth);
        } else {
        	NPPhraseSpec dive = nlgFactory.createNounPhrase("dive");
            dive.setDeterminer("your");
            preModifier.setPreposition("on");
            preModifier.addComplement(dive);
            
            if (type.equals(DiveType.FIRST))
            	dive.addPreModifier("first");
            else
            	dive.addPreModifier("second");
        }
        
        SPhraseSpec s = nlgFactory.createClause();
        NPPhraseSpec you = nlgFactory.createNounPhrase("you");
        
        VPPhraseSpec stay = nlgFactory.createVerbPhrase("stay");
        NLGElement adv = nlgFactory.createInflectedWord("long", LexicalCategory.ADVERB);
        adv.setFeature(Feature.IS_COMPARATIVE, true);
        stay.addComplement(adv);
        
        PPPhraseSpec than = nlgFactory.createPrepositionPhrase();
        than.setPreposition("than");
        
        NPPhraseSpec ndl = nlgFactory.createNounPhrase("NDL");
        ndl.setDeterminer("the");
        
        than.addComplement(ndl);
        
        PPPhraseSpec by = nlgFactory.createPrepositionPhrase("by");
        NPPhraseSpec time = nlgFactory.createNounPhrase(Integer.toString((int) excessBottomTime)+"mins");
        by.addComplement(time);
        
        SPhraseSpec complement = nlgFactory.createClause();
        VPPhraseSpec be = nlgFactory.createVerbPhrase("be");
        double percentage = (excessBottomTime / (bottomTime-excessBottomTime))*100.0;
        be.addComplement(Integer.toString((int)percentage)+"%");
        be.addComplement(adv);
        
        complement.setFeature(Feature.COMPLEMENTISER, "which");
        complement.setVerb(be);
        complement.setFeature(Feature.TENSE, Tense.PAST);
        
        time.addComplement(complement);
        
        s.addFrontModifier(preModifier);
        s.setSubject(you);
        s.setVerb(stay);
        s.addComplement(than);
        s.addComplement(by);
        s.setFeature(Feature.TENSE, Tense.PAST);
        
		return s;
	}

}
