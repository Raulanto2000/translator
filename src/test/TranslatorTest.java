package test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import translator.Translator;
import translator.Word;

import java.util.ArrayList;

import static translator.Config.CONF_DICTIONARY_FILE;
import static translator.Config.ERROR_TRANSLATED;

public class TranslatorTest {

    @Test
    public void translator_null_ErrorInfo() {

        //given
        String strIn = null;
        Translator trans = new Translator();

        //when
        trans.loadDictionary(CONF_DICTIONARY_FILE);
        trans.translate(strIn);

        //then
        String expectedStr = ERROR_TRANSLATED;
        String actualStr = trans.getTranslated();
        Assertions.assertEquals(expectedStr, actualStr);

    }

    @Test
    public void translator_StringPol_StringEng() {

        //given
        ArrayList<Word> dict = new ArrayList<Word>();
        String strIn = "Ala ma kota";
        Translator trans = new Translator();

        //when
        trans.loadDictionary(CONF_DICTIONARY_FILE);
        trans.translate(strIn);

        //then
        String actualStrPOL1 = trans.getTranslated();
        String actualStrPOL2 = trans.getTranslatedQuotes();
        String expectedStrENG1 = "Alice has a cat ";
        String expectedStrENG2 = "\"Alice\" \"has\" \"a cat\" ";

        Assertions.assertEquals(expectedStrENG1, actualStrPOL1);
        Assertions.assertEquals(expectedStrENG2, actualStrPOL2);

    }


}
