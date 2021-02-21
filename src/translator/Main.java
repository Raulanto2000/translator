package translator;

import java.util.List;

import static translator.Config.CONF_DICTIONARY_FILE;

/**
 *
 * @author Raul Antkowiak
 */
public class Main {

    public static void main(String[] args) {
       
        Translator trans = new Translator();
        trans.loadDictionary(CONF_DICTIONARY_FILE);
       
        String strIn = "Ala ma kota. Ala ma kota. Jesteś sterem. Nosisz spodnie więc walcz. Walcz, walcz. ";

        trans.translate(strIn);
       
        System.out.println("TEKST DO PRZETŁUMACZENIA:");
        System.out.println("  " + strIn + "\n");
        
        System.out.println("TŁUMACZENIA:");
        System.out.println("  " + trans.getTranslated() );
        System.out.println("  " + trans.getTranslatedQuotes() + "\n");
                        
        List<Word> rankDict = trans.rankingDict();
        
        System.out.println("RANKING UŻYCIA SŁÓW:");
        for (Word rk: rankDict) {
           System.out.println("  " + rk.getPolish() + " [" + rk.getCount() + "]");
        }
        
    }
    
}
