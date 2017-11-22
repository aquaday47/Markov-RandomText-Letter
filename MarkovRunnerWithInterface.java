import java.util.*;
import static java.lang.System.out;
import edu.duke.*;
/**
 * Write a description of MarkovRunnerWithInterface here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MarkovRunnerWithInterface {
    public ArrayList<String> maxList;
    public void runModel(IMarkovModel markov, String text, int size) {
        markov.setTraining(text);
        
        System.out.println("running with " + markov.toString());
        for(int k=0; k < 3; k++){
            String st= markov.getRandomText(size);
            printOut(st);
        }
    }
    public void runModel(IMarkovModel markov,int size) {
        
        System.out.println("running with " + markov.toString());
        for(int k=0; k < 3; k++){
            String st= markov.getRandomText(size);
            printOut(st);
        }
    }
    public void runModel(IMarkovModel markov, String text, int size, int seed) {
        markov.setTraining(text);
        markov.setRandom(seed);
        System.out.println("running with " + markov.toString());
        for(int k=0; k < 3; k++){
			String st= markov.getRandomText(size);
			printOut(st);
			int maxSize = getLargestList(markov.getDict());
			out.println(maxSize + " \t" + maxList);
		}
    }
    public void compareMethods(){
        
        StandardMarkovModel markovSlow = new StandardMarkovModel(2);
        FileResource fr = new FileResource();
        String text = fr.asString();
        markovSlow.setTraining(text);
        long startSlow = System.nanoTime();
        runModel(markovSlow, 1000); 
        long endSlow = System.nanoTime();
        long durSlow = startSlow - endSlow;
        
        MarkovModelGeneralEfficient markovFast = new MarkovModelGeneralEfficient(2);
        markovFast.setTraining(text);
        long startF = System.nanoTime();
        runModel(markovFast, 1000);
        long endF = System.nanoTime();
        long durF = startF - endF;
        out.println(durSlow + " \n" + durF);
    }
    public void testHashMap(){
        MarkovModelGeneralEfficient markov = new MarkovModelGeneralEfficient(5);
        FileResource fr = new FileResource();
        //String test = "yes-this-is-a-thin-pretty-pink-thistle";
        String test = fr.asString();
        test = test.replace('\n', ' ');
        runModel(markov, test, 50,615);
        //int maxSize = getLargestList(markov.myDict);
        //out.println(maxSize + " \t" + maxList);
    }
    private int getLargestList(HashMap<String,ArrayList<Character>> dict){
        int maxSize = 0;
        maxList = new ArrayList<String>();      
        for (Map.Entry<String, ArrayList<Character>> pair  : dict.entrySet()){
                
                String key = pair.getKey();
                int valSize = pair.getValue().size();
            if (valSize>maxSize){
                maxList.clear();
                maxSize = valSize;
                maxList.add(key);
            }
            else if (valSize == maxSize){
                maxList.add(key);
            }
        }
        return maxSize;
    }
   
    private void printOut(String s){
		String[] words = s.split("\\s+");
		int psize = 0;
		System.out.println("----------------------------------");
		for(int k=0; k < words.length; k++){
			System.out.print(words[k]+ " ");
			psize += words[k].length() + 1;
			if (psize > 60) {
				System.out.println();
				psize = 0;
			}
		}
		System.out.println("\n----------------------------------");
	}
}
