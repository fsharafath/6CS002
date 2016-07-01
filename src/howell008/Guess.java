package howell008;

import java.util.LinkedList;
import java.util.List;

public class Guess {
	
	public int[][] gg = new int[7][8];
	public List<Domino> _g;
	
	public void printGuesses() {
	    for (Domino d : _g) {
	      System.out.println(d);
	    }
	  }
	
	 public Domino findGuessAt(int x, int y) {
		    for (Domino d : _g) {
		      if ((d.lx == x && d.ly == y) || (d.hx == x && d.hy == y)) {
		        return d;
		      }
		    }
		    return null;
		  }
	 
	  public Domino findGuessByLH(int x, int y) {
		    for (Domino d : _g) {
		      if ((d.low == x && d.high == y) || (d.high == x && d.low == y)) {
		        return d;
		      }
		    }
		    return null;
		  }
	  
	  public void collateGuessGrid() {
		    for (int r = 0; r < 7; r++) {
		      for (int c = 0; c < 8; c++) {
		        gg[r][c] = 9;
		      }
		    }
		    for (Domino d : _g) {
		      if (d.placed) {
		        gg[d.hy][d.hx] = d.high;
		        gg[d.ly][d.lx] = d.low;
		      }
		    }
		  }
	  
	  public int printGuessGrid() {
		    for (int are = 0; are < 7; are++) {
		      for (int see = 0; see < 8; see++) {
		        if (gg[are][see] != 9) {
		          System.out.printf("%d", gg[are][see]);
		        } else {
		          System.out.print(".");
		        }
		      }
		      System.out.println();
		    }
		    return 11;
		  }
	  
	  public void generateGuesses() {
		    _g = new LinkedList<Domino>();
		    int count = 0;
		    for (int l = 0; l <= 6; l++) {
		      for (int h = l; h <= 6; h++) {
		        Domino d = new Domino(h, l);
		        _g.add(d);
		        count++;
		      }
		    }
		    if (count != 28) {
		      System.out.println("something went wrong generating dominoes");
		      System.exit(0);
		    }
		  }

}
