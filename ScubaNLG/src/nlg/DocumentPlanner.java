package nlg;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

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
		DocumentElement s;
//		for (Entry<String, SPhraseSpec> element : phrases.entrySet()) {
//			DocumentElement s = nlgFactory.createSentence(element.getValue());
//			sentences.add(s);
//		}
		
		Set<String> labels = phrases.keySet();
		
		if (labels.contains("QuickSuccession")){
			s = nlgFactory.createSentence(phrases.get("QuickSuccession"));
			sentences.add(s);
			
			if (labels.contains("SecondDeeperFirst")){
				s = nlgFactory.createSentence(phrases.get("SecondDeeperFirst"));
				sentences.add(s);
			}
			
			if (labels.contains("First_ExceededNDL")){
				s = nlgFactory.createSentence(phrases.get("First_ExceededNDL"));
				sentences.add(s);
			}
			
			if (labels.contains("DeeperDepth")){
				s = nlgFactory.createSentence(phrases.get("DeeperDepth"));
				sentences.add(s);
			}
			
			if (labels.contains("First_FineAscentRate")){
				s = nlgFactory.createSentence(phrases.get("First_FineAscentRate"));
				sentences.add(s);
			} else {
				s = nlgFactory.createSentence(phrases.get("First_BadAscentRate"));
				sentences.add(s);
			}
			
			if (labels.contains("L0010_SafetyStop")){
				s = nlgFactory.createSentence(phrases.get("L0010_SafetyStop"));
				sentences.add(s);
			}
			
			if (labels.contains("Second_ExceededNDL")){
				s = nlgFactory.createSentence(phrases.get("Second_ExceededNDL"));
				sentences.add(s);
			}
			
			if (labels.contains("Second_FineAscentRate")){
				s = nlgFactory.createSentence(phrases.get("Second_FineAscentRate"));
				sentences.add(s);
			} else {
				s = nlgFactory.createSentence(phrases.get("Second_BadAscentRate"));
				sentences.add(s);
			}
			
			if (labels.contains("L0002_RiskyDive")){
				s = nlgFactory.createSentence(phrases.get("L0002_RiskyDive"));
				sentences.add(s);
			}
			
		} else if (labels.contains("L0001_ShallowDive")){
			s = nlgFactory.createSentence(phrases.get("L0001_ShallowDive"));
			sentences.add(s);
		} else if (labels.contains("Unique_FineDive")){
			s = nlgFactory.createSentence(phrases.get("Unique_FineDive"));
			sentences.add(s);
		} else if (labels.contains("L0002_RiskyDive")){
			s = nlgFactory.createSentence(phrases.get("L0002_RiskyDive"));
			sentences.add(s);
			
			if (labels.contains("Unique_ExceededNDL")){
				s = nlgFactory.createSentence(phrases.get("Unique_ExceededNDL"));
				sentences.add(s);
			}
			
			if (labels.contains("DeeperDepth")){
				s = nlgFactory.createSentence(phrases.get("DeeperDepth"));
				sentences.add(s);
			}
			
			if (labels.contains("Unique_FineAscentRate")){
				s = nlgFactory.createSentence(phrases.get("Unique_FineAscentRate"));
				sentences.add(s);
			} else {
				s = nlgFactory.createSentence(phrases.get("Unique_BadAscentRate"));
				sentences.add(s);
			}
		}

		DocumentElement paragraph = nlgFactory.createParagraph(sentences);
				
		return realiser.realise(paragraph).getRealisation();
	}

}
