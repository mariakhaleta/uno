package interpreter;

/**
 * Interpreter is responsible to identify the command passed by the current player
 * and invoke method from command class to execute it properly.
 */
public class Interpreter {

	private int State = 0;
	private Input input = new Input(System.in);
	private Commands command = new Commands();

	/**
	 * This method performs the task to read a entry by calling the method
	 * "readFields" from Input class, compare the length if is acceptable
	 * and choose what command must be called from Command class.
	 * @return Boolean value. Case finish game return true.
	 */
	public boolean readCommands(){
		String[] fields;

		while(true){
			System.out.print("> ");
			fields = input.readFields();

			if(fields.length < 1 || fields.length > 4){
				continue;
			}

			for(int i = 0; i < fields.length; i++)
				fields[i] = fields[i].toUpperCase();

			if(fields[0].equals("EXIT"))
				return false;

			switch(this.State){
				// Responsible to check the first plays(only can be a "PLAY" or "DRAW").
				case 0:
					switch(fields[0]){
						case "PLAY":
							// Verifies if there is a name of a card.
							if(fields.length < 2){
								System.out.println("HEY, YOU FORGOT TO "
										+ "CHOOSE A CARD.");
								break;
							}

							if(!this.command.playCard(fields)){
								System.out.println("TRY AGAIN.");

							} else{
								if(this.command.verifyEndGame())
									return true;

								if(fields.length == 4 &&
										fields[2].equals("UNO"))
									this.command.pass(true);
								else if(fields.length == 3 &&
										fields[2].equals("UNO"))
									this.command.pass(true);
								else
									this.command.pass(false);
							}

							this.State = 0;
							break;

						case "DRAW":
							this.command.draw();
							this.State = 1;
							break;

						case "PASS":
							System.out.println("YOU SHALL NOT PASS.");
							this.State = 0;
							break;

						default:
							System.out.println("I COULDN'T UNDERSTAND YOU.");
							break;
					}
					break;
				// Check the second plays(only can be a "PLAY" or "PASS").
				case 1:
					switch(fields[0].toUpperCase()){
						case "PLAY":
							if(!this.command.playCard(fields)){
								System.out.println("TRY AGAIN");
							}else {
								this.State = 0;

								if(this.command.verifyEndGame())
									return true;

								if(fields.length == 4 &&
										fields[2].equals("UNO"))
									this.command.pass(true);
								else if(fields.length == 3 &&
										fields[2].equals("UNO"))
									this.command.pass(true);
								else
									this.command.pass(false);
							}
							break;

						case "PASS":
							this.command.pass(false);
							this.State = 0;
							break;

						case "DRAW":
							System.out.println("SORRY, BUT YOU CAN ONLY DO THAT"
									+ " ONCE A TURN.");
							break;

						default:
							System.out.println("I COULDN'T UNDERSTANT YOU. "
									+ "LETS TRY AGAIN.");
							break;
					}
					break;
			}
		}
	}
}
