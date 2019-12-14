package interpreter;
import java.io.InputStream;
import java.util.Scanner;

/**
 * This class is just responsible to read a entry from input(keyboard)
 */
class Input {

	 private Scanner input = null;

	 Input(InputStream in){
	 	input = new Scanner(in);
	 }

	 String[] readFields(){
	 	return this.input.nextLine().trim().split("\\s+");
	 }
}
