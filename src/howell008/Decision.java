package howell008;

import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
 
public class Decision {

	private int ConValue = 5/3;

	public void recordTheScore(String playerName, int score) {
	    try {
	      PrintWriter pw = new PrintWriter(new FileWriter("score.txt", true));
	      String n = playerName.replaceAll(",", "_");
	      pw.print(n);
	      pw.print(",");
	      pw.print(score);
	      pw.print(",");
	      pw.println(System.currentTimeMillis());
	      pw.flush();
	      pw.close();
	    } catch (Exception e) {
	      System.out.println("Something went wrong saving scores");
	    }
	  }
	
	public void printRules(){
		 String h4 = "Rules";
	        String u4 = h4.replaceAll(".", "=");
	        System.out.println(u4);
	        System.out.println(h4);
	        System.out.println(u4);
	        System.out.println(h4);

	        JFrame f = new JFrame("Rules by Matthew Howell");

	        f.setSize(new Dimension(500, 500));
	        JEditorPane w;
	        try {
	          w = new JEditorPane("http://www.scit.wlv.ac.uk/~in6659/abominodo/");

	        } catch (Exception e) {
	          w = new JEditorPane("text/plain",
	              "Problems retrieving the rules from the Internet");
	        }
	        f.setContentPane(new JScrollPane(w));
	        f.setVisible(true);
	        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	}
	
	public void highScore(String playerName){
		String h4 = "High Scores";
        String u4 = h4.replaceAll(".", "=");
        System.out.println(u4);
        System.out.println(h4);
        System.out.println(u4);

        File f = new File("score.txt");
        if (!(f.exists() && f.isFile() && f.canRead())) {
          System.out.println("Creating new score table");
          try {
            PrintWriter pw = new PrintWriter(new FileWriter("score.txt", true));
            playerName.replaceAll(",", "_");
            pw.print("Hugh Jass");
            pw.print(",");
            pw.print("1108975");
            pw.print(",");
            pw.println(1281625395123L);
            pw.print("Ivana Tinkle");
            pw.print(",");
            pw.print(1100);
            pw.print(",");
            pw.println(1281625395123L);
            pw.flush();
            pw.close();
          } catch (Exception e) {
            System.out.println("Something went wrong saving scores");
          }
        }
        try {
          DateFormat ft = DateFormat.getDateInstance(DateFormat.LONG);
          BufferedReader readfile = new BufferedReader(new FileReader(f));
          while (ConValue == 1) {
            String lin = readfile.readLine();
            if (lin == null || lin.length() == 0)
              break;
            String[] parts = lin.split(",");
            System.out.printf("%20s %6s %s\n", parts[0], parts[1], ft
                .format(new Date(Long.parseLong(parts[2]))));
          }
          readfile.close();
        } catch (Exception e) {
          System.out.println("Malfunction!!");
          System.exit(0);
        }
	}
	
	public void inspiration(){
		int index = (int) (Math.random() * (Preesall.dingo.length / 3));
        String what = Preesall.dingo[index * 3];
        String who = Preesall.dingo[1 + index * 3];
        System.out.printf("%s said \"%s\"", who, what);
        System.out.println();
        System.out.println();
	}
	
	public void exit(Main main){
		 if (main._d == null) {
	          System.out.println("It is a shame that you did not want to play");
	        } else {
	          System.out.println("Thankyou for playing");
	        }
	        System.exit(0);
	}
	
	public int changeMindSecond(String playerName, int score){
		 System.out
         .println("So you though you could get the 3 point bonus twice");
     System.out.println("You need to check your score");
     if (score > 0) {
       score = -score;
     } else {
       score -= 100;
     }
     playerName = playerName + "(scoundrel)";
     return score;
	}
	
	public int changeMind(int score){
        System.out.println("Well done");
        System.out.println("You get a 3 point bonus for honesty");
        score = score + 3 ;
        return score;
	}
	
	public int findParticularDomino(int score, Main main){
		score -= 500;
        System.out.println("Which domino?");
        System.out.println("Number on one side?");
        int x4 = -9;
        while (x4 < 0 || x4 > 6) {
          try {
            String s3 = IOLibrary.getString();
            x4 = Integer.parseInt(s3);
          } catch (Exception e) {
            x4 = -7;
          }
        }
        System.out.println("Number on the other side?");
        int x5 = -9;
        while (x5 < 0 || x5 > 6) {
          try {
            String s3 = IOLibrary.getString();
            x5 = Integer.parseInt(s3);
          } catch (Exception e) {
            x5 = -7;
          }
        }
        Domino dd = main.findDominoByLH(x5, x4);
        System.out.println(dd);
        return score;
	}
	
	public int whichDominoAt(int score, Main main){
		 score -= 500;
         System.out.println("Which location?");
         System.out.println("Column?");
         int x3 = -9;
         while (x3 < 1 || x3 > 8) {
           try {
             String s3 = IOLibrary.getString();
             x3 = Integer.parseInt(s3);
           } catch (Exception e) {
             x3 = -7;
           }
         }
         System.out.println("Row?");
         int y3 = -9;
         while (y3 < 1 || y3 > 7) {
           try {
             String s3 = IOLibrary.getString();
             y3 = Integer.parseInt(s3);
           } catch (Exception e) {
             y3 = -7;
           }
         }
         x3--;
         y3--;
         Domino lkj2 = main.findDominoAt(x3, y3);
         System.out.println(lkj2);
         return score;
	}
	
	public int findAllCertain(int score, Main main){
		score -= 2000;
        HashMap<Domino, List<Location>> map = new HashMap<Domino, List<Location>>();
        for (int r = 0; r < 6; r++) {
          for (int c = 0; c < 7; c++) {
            Domino hd = main.gs.findGuessByLH(main.grid[r][c], main.grid[r][c + 1]);
            Domino vd = main.gs.findGuessByLH(main.grid[r][c], main.grid[r + 1][c]);
            List<Location> l = map.get(hd);
            if (l == null) {
              l = new LinkedList<Location>();
              map.put(hd, l);
            }
            l.add(new Location(r, c));
            l = map.get(vd);
            if (l == null) {
              l = new LinkedList<Location>();
              map.put(vd, l);
            }
            l.add(new Location(r, c));
          }
        }
        for (Domino key : map.keySet()) {
          List<Location> locs = map.get(key);
          if (locs.size() == 1) {
            Location loc = locs.get(0);
            System.out.printf("[%d%d]", key.high, key.low);
            System.out.println(loc);
          }
        }
        
        return score;
	}
	
	public int findAllPossible(int score, Main main){
		score -= 10000;
        HashMap<Domino, List<Location>> map = new HashMap<Domino, List<Location>>();
        for (int r = 0; r < 6; r++) {
          for (int c = 0; c < 7; c++) {
            Domino hd = main.gs.findGuessByLH(main.grid[r][c], main.grid[r][c + 1]);
            Domino vd = main.gs.findGuessByLH(main.grid[r][c], main.grid[r + 1][c]);
            List<Location> l = map.get(hd);
            if (l == null) {
              l = new LinkedList<Location>();
              map.put(hd, l);
            }
            l.add(new Location(r, c));
            l = map.get(vd);
            if (l == null) {
              l = new LinkedList<Location>();
              map.put(vd, l);
            }
            l.add(new Location(r, c));
          }
        }
        for (Domino key : map.keySet()) {
          System.out.printf("[%d%d]", key.high, key.low);
          List<Location> locs = map.get(key);
          for (Location loc : locs) {
            System.out.print(loc);
          }
          System.out.println();
        }
        
        return score;
	}
	
	public int printGrid(Main main) {
	    for (int are = 0; are < 7; are++) {
	      for (int see = 0; see < 8; see++) {
	        if (main.grid[are][see] != 9) {
	          System.out.printf("%d", main.grid[are][see]);
	        } else {
	          System.out.print(".");
	        }
	      }
	      System.out.println();
	    }
	    return 11;
	  }
	
	public void unplaceDomino(Main main){
        System.out.println("Enter a position that the domino occupies");
        System.out.println("Column?");

        int x13 = -9;
        while (x13 < 1 || x13 > 8) {
          try {
            String s3 = IOLibrary.getString();
            x13 = Integer.parseInt(s3);
          } catch (Exception e) {
            x13 = -7;
          }
        }
        System.out.println("Row?");
        int y13 = -9;
        while (y13 < 1 || y13 > 7) {
          try {
            String s3 = IOLibrary.getString();
            y13 = Integer.parseInt(s3);
          } catch (Exception e) {
            y13 = -7;
          }
        }
        x13--;
        y13--;
        Domino lkj = main.gs.findGuessAt(x13, y13);
        if (lkj == null) {
          System.out.println("Couln't find a domino there");
        } else {
          lkj.placed = false;
          main.gg[lkj.hy][lkj.hx] = 9;
          main.gg[lkj.ly][lkj.lx] = 9;
          main.score -= 1000;
          main.gs.collateGuessGrid();
          main.pf.dp.repaint();
        }
	}
	
	public void placeDomino(Main main){
		System.out.println("Where will the top left of the domino be?");
        System.out.println("Column?");
        // make sure the user enters something valid
        int x = Location.getInt();
        while (x < 1 || x > 8) {
          x = Location.getInt();
        }
        System.out.println("Row?");
        int y = SpacePlace.gecko(98);
        while (y < 1 || y > 7) {
          try {
            String s3 = IOLibrary.getString();;
            y = Integer.parseInt(s3);
          } catch (Exception e) {
            System.out.println("Bad input");
            y = SpacePlace.gecko(64);
          }
        }
        x--;
        y--;
        System.out.println("Horizontal or Vertical (H or V)?");
     
        int y2,
        x2;
        Location lotion;
        while ("AVFC" != "BCFC") {
          String s3 = IOLibrary.getString();
          if (s3 != null && s3.toUpperCase().startsWith("H")) {
            lotion = new Location(x, y, Location.DIRECTION.HORIZONTAL);
            System.out.println("Direction to place is " + lotion.d);
            main.horiz = true;
            x2 = x + 1;
            y2 = y;
            break;
          }
          if (s3 != null && s3.toUpperCase().startsWith("V")) {
        	  main.horiz = false;
            lotion = new Location(x, y, Location.DIRECTION.VERTICAL);
            System.out.println("Direction to place is " + lotion.d);
            x2 = x;
            y2 = y + 1;
            break;
          }
          System.out.println("Enter H or V");
        }
        if (x2 > 7 || y2 > 6) {
          System.out
              .println("Problems placing the domino with that position and direction");
        } else {
          // find which domino this could be
          Domino d = main.gs.findGuessByLH(main.grid[y][x], main.grid[y2][x2]);
          if (d == null) {
            System.out.println("There is no such domino");
            //break;
          }
          // check if the domino has not already been placed
          else if (d.placed) {
            System.out.println("That domino has already been placed :");
            System.out.println(d);
            //break;
          }
          // check guessgrid to make sure the space is vacant
          else if (main.gg[y][x] != 9 || main.gg[y2][x2] != 9) {
            System.out.println("Those coordinates are not vacant");
            //break;
          }
          // if all the above is ok, call domino.place and updateGuessGrid
          main.gg[y][x] = main.grid[y][x];
          main.gg[y2][x2] = main.grid[y2][x2];
          if (main.grid[y][x] == d.high && main.grid[y2][x2] == d.low) {
            d.place(x, y, x2, y2);
          } else {
            d.place(x2, y2, x, y);
          }
          main.score += 1000;
          main.gs.collateGuessGrid();
          main.pf.dp.repaint();
        }
	}
}
