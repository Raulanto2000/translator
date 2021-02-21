package translator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import static translator.Config.ERROR_TRANSLATED;


/**
 *
 * @author Raul Antkowiak
 */
public class Translator {

    ArrayList<Word> dictionary = new ArrayList<Word>();
    
    String translated = "";
    String translatedQuotes = "";
    
    public Translator() {
        super();
    }

    public void loadDictionary(final String filePath) {
        
        String currentLine;
        String pol;
        String eng;
        
        dictionary.clear();
        
        try ( BufferedReader br = new BufferedReader(new FileReader(filePath)) ) {
            while ((currentLine = br.readLine()) != null) {
                int intCut = currentLine.indexOf(":");
                if (intCut > 0) {
                   pol = currentLine.substring(0, intCut).trim().replaceAll("\"", "") ;
                   eng = currentLine.substring(intCut + 1).trim().replaceAll("\"", "").replaceAll(",", "");
                   dictionary.add(new Word(pol, eng));
                }
            }
        }
        catch (IOException e)  { e.printStackTrace(); }
    }

    public void translate(String strIn) {
        this.translated = "";
        this.translatedQuotes = "";
        if (strIn == null) {
            this.translated = ERROR_TRANSLATED;
            this.translatedQuotes = ERROR_TRANSLATED;
            return;
        }
        String[] tmpStringArray = strIn.split(" ");
        String wordOut = "";
        String wordOutQuote = "";
        for(String word : tmpStringArray) {
            wordOut = word;
            wordOutQuote = word;
            if (!word.equals("")) {
                wordOutQuote = "\"" + word + "\"";
                String findWord = word.replace(".", "").replace(";", "").replace(",", "");
                for (Word dw: dictionary) {
                    if (dw.getPolish().equalsIgnoreCase(findWord)) {
                       dw.count = dw.count + 1;
                       wordOut = word.replace(findWord, dw.getEnglish());
                       wordOutQuote = word.replace(findWord, "\"" + dw.getEnglish() + "\"");
                       break;
                   }
                }
            }
            translated = translated + wordOut + " ";
            translatedQuotes = translatedQuotes + wordOutQuote + " ";
        }
    }

    public void sort() {
        Collections.sort(dictionary);
    }

    public List<Word> rankingDict() {
        List<Word> rankDict = dictionary.stream()
                                    .filter(dict -> (dict.getCount() >= 1))
                                    .sorted(Comparator.comparing(Word::getCount).reversed() )
                                    .collect(Collectors.toList());
        return rankDict;
    }

    
    public ArrayList<Word> getDictionary() {
        return dictionary;
    }

    public void setDictionary(ArrayList<Word> dictionary) {
        this.dictionary = dictionary;
    }

    public String getTranslated() {
        return translated;
    }

    public void setTranslated(String translated) {
        this.translated = translated;
    }

    public String getTranslatedQuotes() {
        return translatedQuotes;
    }

    public void setTranslatedQuotes(String translatedQuotes) {
        this.translatedQuotes = translatedQuotes;
    }

    
    
    
}
