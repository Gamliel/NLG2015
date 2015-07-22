package microplanning.planners;

import analytics.PADITable;
import microplanning.generators.SafetyStop;
import simplenlg.phrasespec.SPhraseSpec;

public class NLGSentenceSafetyStop extends NLGSentence {
	
	private double depth;
	private long bottomLine;
	
	public NLGSentenceSafetyStop(double depth, long bottomLine) {
		this.depth = depth;
		this.bottomLine = bottomLine;
	}

	public SPhraseSpec getSentence() {
		SafetyStop generator = new SafetyStop();
		return generator.microplan();
	}
	
	public boolean canGenerate(){
		return PADITable.needSafetyStop(depth, bottomLine);
	}

}
