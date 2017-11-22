import java.util.*;
import static java.lang.System.out;
/**
 * Write a description of AbstractMarkovModel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class OldAbstractMarkovModel implements IMarkovModel {
    protected String myText;
    protected Random myRandom;
    protected int order;
    protected int seed;
    

    public void AbstractMarkovModel(){
        myRandom = new Random();
    }
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    public void setTraining(String s){
        myText = s.trim();
    }
    protected ArrayList<Character> getFollows(String key){
        ArrayList<Character> ans = new ArrayList<Character>();
        int currInd = 0;
        int inc = key.length();
        while (currInd!=-1){
        currInd = myText.indexOf(key, currInd);
        if (currInd+inc<myText.length()){
            char nextChar = myText.charAt(currInd+inc);
            if (currInd != -1)
                { currInd +=inc;
                    ans.add(nextChar);
                }
            }
            else break;
        }   
        return ans;
    }
    
    public String toString(){
        String output = "MarkovModel of order " + order;
        return output;
    } 
    public abstract String getRandomText(int numChars);
}
