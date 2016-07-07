package howell008;

import org.junit.*;

import static org.junit.Assert.*;
import howell008.Guess;

public class GuessTest {

	Guess guess = new Guess();
	

	public void printGuessGridTest() {
		int result = guess.printGuessGrid();
		assertNotNull("result cannot be null", result);
	}

	@Test
	public void generateGuesses() {
		guess.generateGuesses();
	}

	/**
	 *
	 * @see howell008.Guess#collateGuessGrid()
	 */
	@Test
	public void collateGuessGrid() {
		guess.collateGuessGrid();
	}

	
}
