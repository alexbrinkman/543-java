import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Search {

  public Board board;

  public Search(Board board) {
    this.board = board;
  }

  public Board findMove() {
    System.out.println("Searching...");
    // return randomMove();
    return minimax();
  }

  private Board randomMove() {
    List<Board> availableMoves = this.board.availableMoves();
    int randomMoveIndex = ThreadLocalRandom.current().nextInt(0, availableMoves.size());
    return availableMoves.get(randomMoveIndex);
  }

  private Board minimax() {
    return new Minimax(this.board).search();
  }

}
