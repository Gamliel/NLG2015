package microplanning;

import com.mysql.jdbc.NotImplemented;

import analytics.PADITable;
import simplenlg.phrasespec.SPhraseSpec;

public class NLGSentenceSafetyStop extends NLGSentence {
	
	private double depth;
	private long bottomLine;
	
	public NLGSentenceSafetyStop(double depth, long bottomLine) {
		this.depth = depth;
		this.bottomLine = bottomLine;
	}

	public SPhraseSpec getSentence() throws NotImplemented {
			return SafetyStop.microplan();
	}
	
	public boolean canGenerate(){
		return PADITable.needSafetyStop(depth, bottomLine);
	}

}
