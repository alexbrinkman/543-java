public class Search {

  private Board board;

  public Search(Board board) {
    this.board = board;
  }

  public Board findMove() {
    System.out.println("Searching...");
    return new Minimax(this.board).search();
  }

}
