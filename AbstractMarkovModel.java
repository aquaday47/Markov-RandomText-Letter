import java.util.*;
/**
 * Write a description of AbstractMarkovModel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class AbstractMarkovModel implements IMarkovModel{

    protected int order;
    protected String myText;
    protected Random myRandom;
    protected int seed;
    protected HashMap<String, ArrayList<Character>> myDict;
    
    
    public AbstractMarkovModel(){
        myRandom = new Random();
    }
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    public void setTraining(String text){
        myText = text.trim();
    }
    public HashMap<String, ArrayList<Character>> getDict(){
         HashMap<String, ArrayList<Character>> resDict = myDict;
         return resDict;
    }
    protected char getFollows(String s){
        ArrayList<Character> followsList = myDict.get(s);
        char nextChar;
        if (followsList == null  || followsList.size()==0){
            return ' ';
            
        }
        else {
            nextChar = followsList.get(myRandom.nextInt(followsList.size()));
        }
        return nextChar;
    }
    public abstract String getRandomText(int numChars);
    
    public String toString(){
        return "MarkovModel of order " + order;
    }
    
}
