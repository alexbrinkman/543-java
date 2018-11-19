import java.util.Scanner;

public class Game {

  Board board = new Board(null);
  private String whosMove = "player1";

  public void run() {
    System.out.println(this.board);
    while(true) {
      makeMove();
      if(board.winner()){
        System.out.println(this.whosMove + " wins!");
        return;
      }
      switchPlayers();
    }
  }

  public void makeMove() {
    if (this.whosMove.equals("player1")){
      int[] move = promptForMove();
      try {
        this.board.move(move[0], move[1]);
      }
      catch (Exception e) {
        System.out.println(e.getMessage());
      }
    }
    else {
      this.board = new Search(this.board).findMove();
    }
    System.out.println(this.board);
  }

  private int[] promptForMove() {
    Scanner scan = new Scanner(System.in);
    System.out.println("Your move: (row)");
    int row = scan.nextInt();
    System.out.println("Your move: (number)");
    int num = scan.nextInt();
    return new int[] { row, num };
  }

  private void switchPlayers() {
    if(this.whosMove.equals("player1")){
      this.whosMove = "player2";
    }
    else {
      this.whosMove = "player1";
    }
  }

}
