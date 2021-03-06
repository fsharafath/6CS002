package howell008;

import org.junit.*;
import static org.junit.Assert.*;
import howell008.Decision;
import howell008.Main;

public class DecisionTest {
	Decision decision = new Decision();

	@Test(expected = RuntimeException.class)
	public void recordTheScoreTest() {
		decision.recordTheScore("",1);
	}

	@Test
	public void printRules() {
		decision.printRules();
	}

	@Test
	public void highScore() {
		decision.highScore("");
	}

	@Test
	public void inspiration() {
		decision.inspiration();
	}

	@Test
	public void changeMindSecond() {
		int result = decision.changeMindSecond("",1);
		assertNotNull("result cannot be null", result);
	}

	@Test
	public void changeMind() {
		int result = decision.changeMind(1);
		assertNotNull("result cannot be null", result);
	}

	@Test
	public void findParticularDomino() {
		Main main = new Main();
		int particulardomino = decision.findParticularDomino(1,main);
		assertNotNull("particulardomino cannot be null", particulardomino);
	}

	@Test
	public void whichDominoAt() {
		Main main = new Main();
		int result = decision.whichDominoAt(1,main);
		assertNotNull("result cannot be null", result);
	}

	@Test
	public void findAllCertain() {
		Main main = new Main();
		int allcertain = decision.findAllCertain(1,main);
		assertNotNull("allcertain cannot be null", allcertain);
	}

	@Test
	public void findAllPossible() {
		Main main = new Main();
		int allpossible = decision.findAllPossible(1,main);
		assertNotNull("allpossible cannot be null", allpossible);
	}

	@Test
	public void printGrid() {
		Main main = new Main();
		int result = decision.printGrid(main);
		assertNotNull("result cannot be null", result);
	}

	@Test
	public void unplaceDomino() {
		Main main = new Main();
		decision.unplaceDomino(main);
	}

	
	@Test
	public void exit() {
		Main main = new Main();
		decision.exit(main);
	}
	
}
