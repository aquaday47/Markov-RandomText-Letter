import java.util.*;
/**
 * Write a description of IMarkovModel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public interface IMarkovModel {
    public String getRandomText(int numChars);
    public void setRandom(int seed);
    public void setTraining(String text);
    public String toString();
    public  HashMap<String, ArrayList<Character>> getDict();
    
}
