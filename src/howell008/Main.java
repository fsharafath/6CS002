package howell008;
//import java.awt.Dimension;
import java.awt.Graphics;
//import java.io.*;
//import java.text.DateFormat;
import java.util.*;
//import javax.swing.JEditorPane;
//import javax.swing.JFrame;
//import javax.swing.JScrollPane;

/**
 * @author Kevan Buckley, maintained by Matthew Howell
 * @version 2.0, 2014
 */

//M F M SHARAFATH

public class Main {

  private String playerName;
  public List<Domino> _d;
  public int[][] grid = new int[7][8];
  public int[][] gg = new int[7][8];
  int mode = -1;
  int cf;
  int score;
  long startTime;
  boolean horiz;
  
  
  Guess gs = new Guess();
  Decision ds = new Decision();
  PictureFrame pf = new PictureFrame(this);

  private void generateDominoes() {
    _d = new LinkedList<Domino>();
    int count = 0;
    int x = 0;
    int y = 0;
    for (int l = 0; l <= 6; l++) {
      for (int h = l; h <= 6; h++) {
        Domino d = new Domino(h, l);
        _d.add(d);
        d.place(x, y, x + 1, y);
        count++;
        x += 2;
        if (x > 6) {
          x = 0;
          y++;
        }
      }
    }
    if (count != 28) {
      System.out.println("something went wrong generating dominoes");
      System.exit(0);
    }
  }

  void collateGrid() {
    for (Domino d : _d) {
      if (!d.placed) {
        grid[d.hy][d.hx] = 9;
        grid[d.ly][d.lx] = 9;
      } else {
        grid[d.hy][d.hx] = d.high;
        grid[d.ly][d.lx] = d.low;
      }
    }
  }
  
  private void shuffleDominoesOrder() {
    List<Domino> shuffled = new LinkedList<Domino>();

    while (_d.size() > 0) {
      int n = (int) (Math.random() * _d.size());
      shuffled.add(_d.get(n));
      _d.remove(n);
    }

    _d = shuffled;
  }

  private void invertSomeDominoes() {
    for (Domino d : _d) {
      if (Math.random() > 0.5) {
        d.invert();
      }
    }
  }

  private void placeDominoes() {
    int x = 0;
    int y = 0;
    int count = 0;
    for (Domino d : _d) {
      count++;
      d.place(x, y, x + 1, y);
      x += 2;
      if (x > 6) {
        x = 0;
        y++;
      }
    }
    if (count != 28) {
      System.out.println("something went wrong generating dominoes");
      System.exit(0);
    }
  }

  private void rotateDominoes() {
    for (int x = 0; x < 7; x++) {
      for (int y = 0; y < 6; y++) {

        tryToRotateDominoAt(x, y);
      }
    }
  }

  private void tryToRotateDominoAt(int x, int y) {
    Domino d = findDominoAt(x, y);
    if (thisIsTopLeftOfDomino(x, y, d)) {
      if (d.ishl()) {
        boolean weFancyARotation = Math.random() < 0.5;
        if (weFancyARotation) {
          if (theCellBelowIsTopLeftOfHorizontalDomino(x, y)) {
            Domino e = findDominoAt(x, y + 1);
            e.hx = x;
            e.lx = x;
            d.hx = x + 1;
            d.lx = x + 1;
            e.ly = y + 1;
            e.hy = y;
            d.ly = y + 1;
            d.hy = y;
          }
        }
      } else {
        boolean weFancyARotation = Math.random() < 0.5;
        if (weFancyARotation) {
          if (theCellToTheRightIsTopLeftOfVerticalDomino(x, y)) {
            Domino e = findDominoAt(x + 1, y);
            e.hx = x;
            e.lx = x + 1;
            d.hx = x;
            d.lx = x + 1;
            e.ly = y + 1;
            e.hy = y + 1;
            d.ly = y;
            d.hy = y;
          }
        }

      }
    }
  }

  private boolean theCellToTheRightIsTopLeftOfVerticalDomino(int x, int y) {
    Domino e = findDominoAt(x + 1, y);
    return thisIsTopLeftOfDomino(x + 1, y, e) && !e.ishl();
  }

  private boolean theCellBelowIsTopLeftOfHorizontalDomino(int x, int y) {
    Domino e = findDominoAt(x, y + 1);
    return thisIsTopLeftOfDomino(x, y + 1, e) && e.ishl();
  }

  private boolean thisIsTopLeftOfDomino(int x, int y, Domino d) {
    return (x == Math.min(d.lx, d.hx)) && (y == Math.min(d.ly, d.hy));
  }

  public  Domino findDominoAt(int x, int y) {
    for (Domino d : _d) {
      if ((d.lx == x && d.ly == y) || (d.hx == x && d.hy == y)) {
        return d;
      }
    }
    return null;
  }

  public Domino findDominoByLH(int x, int y) {
    for (Domino d : _d) {
      if ((d.low == x && d.high == y) || (d.high == x && d.low == y)) {
        return d;
      }
    }
    return null;
  }

  private void printDominoes() {
    for (Domino d : _d) {
      System.out.println(d);
    }
  }

  public final int ZERO = 0;

  public void run() {
	 
    System.out
        .println("Welcome To Abominodo - The Best Dominoes Puzzle Game in the Universe");
    System.out.println("Version 2.1 (c), Kevan Buckley, 2014");

    System.out.println();
    System.out.println(MultiLingualStringTable.getMessage(0));
    playerName = IOLibrary.getString();

    System.out.printf("%s %s. %s", MultiLingualStringTable.getMessage(1),
        playerName, MultiLingualStringTable.getMessage(2));

    int _$_ = -9;
    while (_$_ != ZERO) {
      System.out.println();
      String h1 = "Main menu";
      String u1 = h1.replaceAll(".", "=");
      System.out.println(u1);
      System.out.println(h1);
      System.out.println(u1);
      System.out.println("1) Play");
      System.out.println("2) View high scores");
      System.out.println("3) View rules");
      System.out.println("5) Get inspiration");
      System.out.println("0) Quit");

      _$_ = -9;
      while (_$_ == -9) {
        try {
          String s1 = IOLibrary.getString();;
          _$_ = Integer.parseInt(s1);
        } catch (Exception e) {
          _$_ = -9;
        }
      }
      switch (_$_) {
      case 5:
        ds.inspiration();
        break;      
      case 0: 
    	  ds.exit(this);
    	  break;
      case 1: {
        System.out.println();
        String h4 = "Select difficulty";
        String u4 = h4.replaceAll(".", "=");
        System.out.println(u4);
        System.out.println(h4);
        System.out.println(u4);
        System.out.println("1) Simples");
        System.out.println("2) Not-so-simples");
        System.out.println("3) Super-duper-shuffled");
        int c2 = -7;
        while (!(c2 == 1 || c2 == 2 || c2 == 3)) {
          try {
            String s2 = IOLibrary.getString();;
            c2 = Integer.parseInt(s2);
          } catch (Exception e) {
            c2 = -7;
          }
        }
        
        generateDominoes();
        shuffleDominoesOrder();
        placeDominoes();
        
        switch (c2) {
        case 1:
          break;
        case 2:
          rotateDominoes();
          break;
        default:
          rotateDominoes();
          rotateDominoes();
          rotateDominoes();
          invertSomeDominoes();
          break;
        }
        collateGrid();
        ds.printGrid(this);
        gs.generateGuesses();
        gs.collateGuessGrid();
        mode = 1;
        cf = 0;
        score = 0;
        startTime = System.currentTimeMillis();
        pf.dp.repaint();
        int c3 = -7;
        while (c3 != ZERO) {
          System.out.println();
          String h5 = "Play menu";
          String u5 = h5.replaceAll(".", "=");
          System.out.println(u5);
          System.out.println(h5);
          System.out.println(u5);
          System.out.println("1) Print the grid");
          System.out.println("2) Print the box");
          System.out.println("3) Print the dominos");
          System.out.println("4) Place a domino");
          System.out.println("5) Unplace a domino");
          System.out.println("6) Get some assistance");
          System.out.println("7) Check your score");
          System.out.println("0) Given up");
          System.out.println("What do you want to do " + playerName + "?");
          c3 = 9;
          // make sure the user enters something valid
          while (!((c3 == 1 || c3 == 2 || c3 == 3)) && (c3 != 4)
              && (c3 != ZERO) && (c3 != 5) && (c3 != 6) && (c3 != 7)) {
            try {
              String s3 = IOLibrary.getString();;
              c3 = Integer.parseInt(s3);
            } catch (Exception e) {
              c3 = SpacePlace.gecko(55);
            }
          }
          switch (c3) {
          case 0:
            break;
          case 1:
        	  ds.printGrid(this);
            break;
          case 2:
            gs.printGuessGrid();
            break;
          case 3:
            Collections.sort(gs._g);
            gs.printGuesses();
            break;
          case 4:
            ds.placeDomino(this);
            break;
          case 5:
        	  ds.unplaceDomino(this);
            break;
          case 7:
            System.out.printf("%s your score is %d\n", playerName, score);
            break;
          case 6:
            System.out.println();
            String h8 = "So you want to cheat, huh?";
            String u8 = h8.replaceAll(".", "=");
            System.out.println(u8);
            System.out.println(h8);
            System.out.println(u8);
            System.out.println("1) Find a particular Domino (costs you 500)");
            System.out.println("2) Which domino is at ... (costs you 500)");
            System.out.println("3) Find all certainties (costs you 2000)");
            System.out.println("4) Find all possibilities (costs you 10000)");
            System.out.println("0) You have changed your mind about cheating");
            System.out.println("What do you want to do?");
            int yy = -9;
            while (yy < 0 || yy > 4) {
              try {
                String s3 = IOLibrary.getString();
                yy = Integer.parseInt(s3);
              } catch (Exception e) {
                yy = -7;
              }
            }
            switch (yy) {
            case 0:
              switch (cf) {
              case 0:
            	  score = ds.changeMind(score);
            	  cf++;
            	  break;
              case 1:
            	  score =  ds.changeMindSecond( playerName,  score);
            	  cf++;
            	  break;
              default:
                System.out.println("Some people just don't learn");
                playerName = playerName.replace("scoundrel",
                    "pathetic scoundrel");
                for (int i = 0; i < 10000; i++) {
                  score--;
                }
              }
              break;
            case 1:
            	score = ds.findParticularDomino(score, this);
              break;
            case 2:
            	score = ds.whichDominoAt(score, this);
              break;
            case 3: 
            	score = ds.findAllCertain(score, this);
              break;
            case 4: 
            	score = ds.findAllPossible(score, this);
              break;
            }
          }

        }
        mode = 0;
        ds.printGrid(this);
        pf.dp.repaint();
        long now = System.currentTimeMillis();
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        int gap = (int) (now - startTime);
        int bonus = 60000 - gap;
        score += bonus > 0 ? bonus / 1000 : 0;
        ds.recordTheScore(playerName,score);
        System.out.println("Here is the solution:");
        System.out.println();
        Collections.sort(_d);
        printDominoes();
        System.out.println("you scored " + score);

      }
        break;
      case 2: 
    	  ds.highScore(playerName);
    	  break;
      case 3: 
    	  ds.printRules();
    	  break;
     }
    }
  }
  
  public static void main(String[] args) {
    new Main().run();
  }

  public void drawDominoes(Graphics g) {
    for (Domino d : _d) {
      pf.dp.drawDomino(g, d);
    }
  }

  public void drawGuesses(Graphics g) {
    for (Domino d : gs._g) {
      pf.dp.drawDomino(g, d);
    }
  }

}
