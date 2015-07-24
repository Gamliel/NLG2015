package nlg;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import simplenlg.framework.DocumentElement;
import simplenlg.framework.NLGFactory;
import simplenlg.lexicon.Lexicon;
import simplenlg.phrasespec.SPhraseSpec;
import simplenlg.realiser.english.Realiser;

public class DocumentPlanner implements Reporter{

	private Lexicon lexicon = Lexicon.getDefaultLexicon();
    private Realiser realiser = new Realiser(lexicon);
    NLGFactory nlgFactory = new NLGFactory(lexicon);
    
    private Map<String, SPhraseSpec> phrases;
    
    public DocumentPlanner(Map<String, SPhraseSpec> phrases) {
		this.phrases = phrases;
	}
	
	@Override
	public String generateText() {
		List<DocumentElement> sentences = new ArrayList<DocumentElement>();
		for (Entry<String, SPhraseSpec> element : phrases.entrySet()) {
			DocumentElement s = nlgFactory.createSentence(element.getValue());
			sentences.add(s);
		}

		DocumentElement paragraph = nlgFactory.createParagraph(sentences);
				
		return realiser.realise(paragraph).getRealisation();
	}

}
