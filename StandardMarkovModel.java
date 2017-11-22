import java.util.*;
import static java.lang.System.out;
/**
 * Write a description of MarkovModel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class StandardMarkovModel extends OldAbstractMarkovModel{

    public StandardMarkovModel(int num){
        myRandom = new Random();
        order = num;
    }
    
    public void setTraining(String s){
        myText = s.trim();
    }
    public HashMap<String, ArrayList<Character>> getDict(){
         HashMap<String, ArrayList<Character>> resDict = new HashMap<String, ArrayList<Character>>();
         return resDict;
    }
    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int inc = order;
        int ind = myRandom.nextInt(myText.length()-inc);
        
        String ref = myText.substring(ind, ind+inc);
        sb.append(ref);
        //probly best to make a dictionary of the 28 ArrayLists possible...
        for (int  i = 0; i<numChars-1; i++){
            
           
            ArrayList<Character> followList = getFollows(ref);
            if (followList.size()== 0)
                {break;}
            int listInd = myRandom.nextInt(followList.size());
            sb.append(followList.get(listInd));
            
            ref = sb.substring(sb.length()-inc, sb.length());
            //followList.get(listInd).toString();
            
        }
        return sb.toString();
    }
    
}
