package microplanning;

import simplenlg.lexicon.Lexicon;
import simplenlg.phrasespec.SPhraseSpec;
import simplenlg.realiser.english.Realiser;

public class NLGSentenceShallowDive extends NLGSentence {
	
	private double depth;
	
	public NLGSentenceShallowDive(double depth) {
		this.depth = depth;
	}

	public SPhraseSpec getSentence() {
		Lexicon lexicon = Lexicon.getDefaultLexicon();
        Realiser realiser = new Realiser(lexicon);
				
		if (depth<=9.6)
			return ShallowDive.microplan(true);
		return ShallowDive.microplan(false);
	}
	
	@Override
	public boolean canGenerate() {
		return depth <= 12;
	}
}
