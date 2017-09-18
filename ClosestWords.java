/* Labb 2 i DD1352 Algoritmer, datastrukturer och komplexitet    */
/* Se labbanvisning under kurssidan http://www.csc.kth.se/DD1352 */
/* Ursprunglig fÃ¶rfattare: Viggo Kann KTH viggo@nada.kth.se      */
import java.util.LinkedList;
import java.util.List;

public class ClosestWords {
  LinkedList<String> closestWords = null;

  int closestDistance = -1;
  static int maxWordLen = 40;
  
  static int M[][] = new int[maxWordLen][maxWordLen];
  
  static{for (int i = 0; i < maxWordLen; i++){
			M[i][0] = i; 
			M[0][i] = i;}
  }
  
  int partDist(String w1, String w2, int w1len, int w2len) {
	  	char[] W1 = w1.toCharArray();
		char[] W2 = w2.toCharArray();
		
		for (int i = 1; i <= w1len; i++) {
			for (int j = 1; j <= w2len; j++) {
				if (W1[i - 1] == W2[j - 1]) {
					M[i][j] = M[i - 1][j - 1];}
				else {
					M[i][j] = 1 + Math.min(M[i - 1][j - 1], Math.min(M[i - 1][j], M[i][j - 1]));}
			}
		}
		return M[w1len][w2len];
  }
  
  int Distance(String w1, String w2) {
    return partDist(w1, w2, w1.length(), w2.length());
  }
  
  int partDist2(String w1, String w2, String wlast, int w1len, int w2len, int wlastlen) {
	  	char[] W1 = w1.toCharArray();
		char[] W2 = w2.toCharArray();
		char[] Wlast = wlast.toCharArray();
		
		int x=0;
		int len = w2len < wlastlen ? w2len:wlastlen;
		
		while(x<len && W2[x] == Wlast[x]){
			x++;
		}
		
		for (int i = 1; i <= w1len; i++) {
			for (int j = x+1; j <= w2len; j++) {
				if (W1[i - 1] == W2[j - 1]) {
					M[i][j] = M[i - 1][j - 1];}
				else {
					M[i][j] = 1 + Math.min(M[i - 1][j - 1], Math.min(M[i - 1][j], M[i][j - 1]));}
			}
		}
		return M[w1len][w2len];
}
  
  int Distance2(String w1, String w2, String wlast) {
	    return partDist2(w1, w2, wlast, w1.length(), w2.length(), wlast.length());
	  }

  public ClosestWords(String w, List<String> wordList) {
	  String wlast = "";
	  for (String s : wordList) {
	      int dist = Distance2(w, s, wlast);
	      //System.out.println("d(" + w + "," + s + ")=" + dist);
	      if (dist < closestDistance || closestDistance == -1) {
	        closestDistance = dist;
	        closestWords = new LinkedList<String>();
	        closestWords.add(s);
	      }
	      else if (dist == closestDistance)
	        closestWords.add(s);
	    
	      wlast = s;
    }    
    
  }

  int getMinDistance() {
    return closestDistance;
  }

  List<String> getClosestWords() {
    return closestWords;
  }
}