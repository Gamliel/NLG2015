package sentence;

import analytics.DiveletFeatures;
import analytics.PADITable;

public class NLGSentence {
	
	private double depth;
	private DiveletFeatures firstDivelet;
	
	public void setFeatures(double depth, DiveletFeatures firstDiveletFeature) {
		this.depth = depth;
		this.firstDivelet = firstDiveletFeature;
	}

	public String getSentence() {
		if(PADITable.needSafetyStop(depth, firstDivelet.getBottomTime()))
			return "You should have made a safety stop on your way up.";
		if (depth<=9.6)
			return "This was a really shallow dive.";
		return "This was a shallow dive.";
	}

}
