package tableGame;

import java.util.ArrayList;

import system.Player;


/**
 * This class is responsible to take care of the player and the sequence of
 * plays.
 *
 */
public class PlayersManager {
	private ArrayList <Player> players;
	private boolean canAdd;
	private int currElem;
	private int nextElem;
	private boolean toRight;
	private static PlayersManager playersManager = null;

	private PlayersManager(){
		this.players = new ArrayList<>();
		this.canAdd  = true;
		this.currElem = 0;
		this.nextElem = 0;
		this.toRight = true;
	}

	public static PlayersManager getInstance(){
		if(playersManager == null)
			playersManager = new PlayersManager();

		return playersManager;
	}

	/**
	 * Add a player in the list only if inclusion of new players is
	 * allowed.
	 * @param player to be add.
	 * @return true if it was possible to add the player, false otherwise.
	 */
	public boolean addPlayer(Player player){
		if(this.canAdd){
			this.players.add(player);

			if(this.players.size() == 2)
				this.nextElem = 1;
		}

		return this.canAdd;
	}

	/**
	 * Return the status of the players. This status is a formatted string
	 * composed by many "PlayerName : NumberOfCards".
	 * @return a string that contains the status of all the cards.
	 */
	public String getPlayersStatus(){
		String pStatus = "";
		Player player;
		for(int i = 0; i < this.players.size(); i++){
			player  = this.players.get(i);
			pStatus += "[" +player.getName() + ": ";
			pStatus += Integer.toString(player.numCards()) + "] ";
		}

		return pStatus;
	}

	/**
	 * Get the number of players in the list.
	 * @return the number of players.
	 */
	public int getNumPlayers(){
		return this.players.size();
	}

	/**
	 * Rotate the players.
	 */
	public void rotate(){
		this.currElem = this.nextElem;
		this.rotateNextPlayer();
	}

	/**
	 * Rotate only the next player.
	 */
	public void rotateNextPlayer(){

		if(this.toRight)
			this.nextElem = (this.nextElem + 1) % players.size();
		else
			this.nextElem = (this.nextElem - 1 + players.size()) % players.size();
	}

	/**
	 * Get the current player.
	 * @return the current player.
	 */
	public Player getCurrent(){
		Player p;
		try{
			p = this.players.get(this.currElem);

		} catch(IndexOutOfBoundsException e){
			System.out.println("There isn't any players.");
			p = null;
		}

		return p;
	}

	/**
	 * Get the next player.
	 * @return the next player.
	 */
	public Player getNext(){
		return this.players.get(this.nextElem);
	}

	/**
	 * Look for a player that have no more cards in the hand.
	 * @return the winner, if there is one.
	 */
	public Player lookForWinner(){
		for(int i = 0; i < this.players.size(); i++){
			if(this.players.get(i).numCards() == 0)
				return this.players.get(i);
		}

		return null;
	}
}
