package translator;

/**
 *
 * @author Raul Antkowiak
 */
public class Word implements Comparable<Word> {
    
    String polish;
    String english;
    Integer count;

    public Word(String polish, String english) {
        this.polish = polish;
        this.english = english;
        this.count = 0;
    }

    public String getPolish() {
        return polish;
    }

    public void setPolish(String polish) {
        this.polish = polish;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public int compareTo(Word o) {
        if (this.getCount() < o.getCount()) return 1;
        if (this.getCount() > o.getCount()) return -1;
        else return 0;
    }

    
 
}
