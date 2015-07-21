package sentence;

public class NLGSentence {
	
	private double diveDepth;
	
	public void setDiveDepth(double d) {
		diveDepth = d;
	}

	public String getSentence() {
		if (diveDepth<=9.6)
			return "This was a really shallow dive.";
		return "This was a shallow dive.";
	}

}
