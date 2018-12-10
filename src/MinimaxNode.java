import java.util.ArrayList;

public class MinimaxNode {

  private Board board;
  private ArrayList<MinimaxNode> moves;
  private int value;

  public MinimaxNode(Board board) {
    this.board = board;
    this.moves = new ArrayList<>();
    this.value = 0;
  }

  public Board getBoard() {
    return board;
  }

  public ArrayList<MinimaxNode> getMoves() {
    return moves;
  }

  public int getValue() {
    return value;
  }

  public void setValue(int value) {
    this.value = value;
  }

}
