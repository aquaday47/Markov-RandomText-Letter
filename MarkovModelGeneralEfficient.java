import java.util.*;
import static java.lang.System.out;
/**
 * Write a description of MarkovModelGeneral here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MarkovModelGeneralEfficient extends AbstractMarkovModel {
    
    public MarkovModelGeneralEfficient(int num){
        myRandom = new Random();
        order = num;
        myDict = new HashMap<String, ArrayList<Character>>();
    }
    public void setTraining(String s){
        myText = s.trim();
    }
    public HashMap<String, ArrayList<Character>> getDict(){
         HashMap<String, ArrayList<Character>> resDict = myDict;
         return resDict;
    }
    public void buildMap(){
        for (int i = 0; i<=myText.length()-order; i++){
            String key = myText.substring(i, i+order);
            ArrayList<Character> prevList = myDict.get(key);
            int ind = i;
            if (prevList == null){
                ArrayList<Character> newList = new ArrayList<Character>();
                if (i==myText.length()-order){
                    
                    myDict.put(key, newList);
                }
                else {
                    char followsChar = myText.charAt(ind+order);
                    newList.add(followsChar);
                    while (ind != -1){
                        ind = myText.indexOf(key, ind+1);
                        if (ind != -1){
                            newList.add(myText.charAt(ind+order));
                            
                        }
                    }
                    myDict.put(key, newList );
                }
            }
        } 
    }
    public String getRandomText(int numChars)
    {
        if (myText == null){
            return  "";
        }
        StringBuilder result = new StringBuilder();
        int firstInd = myRandom.nextInt(myText.length()-order);
        String firstPart = myText.substring(firstInd, firstInd + order);
        result.append(firstPart);
        buildMap();
        printHashMapInfo();
        
        String key = firstPart;
        for (int i = 0; i<numChars-order; i++){
            ArrayList<Character> followsList = myDict.get(key);
            if (followsList != null){
                //this step is in getFollows() method
                //int listInd = myRandom.nextInt(followsList.size()-1);
                String tempKey = "";
                ArrayList<Character> nextList = new ArrayList<Character>();
                int counter = 0;
                while (nextList==null||nextList.size()==0){
                    
                    char tempChar = getFollows(key);
                    result.append(tempChar);
                    tempKey = result.substring(result.length()-order);
                    nextList = myDict.get(tempKey);
                    result.deleteCharAt(result.length()-1);
                    if (counter==1&&nextList == null||counter==1&&nextList.size()==0){return result.toString();}
                    if (nextList!=null){
                        char nextChar = tempChar;
                        key=tempKey;
                        result.append(nextChar);
                    }
                    counter++;
                    
                }
            }
        }
        
       return result.toString();
    }
    
    public void printHashMapInfo(){
        /*
        for ( Object pair : myDict.entrySet()){
            out.println(pair);
        }
        */
        out.println("Keys: " + myDict.size());
        
    }
}

