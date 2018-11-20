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

  public MinimaxNode buildTree(Board board) {
    MinimaxNode rootNode = new MinimaxNode(board);
    for (Board b : board.availableMoves()) {
      rootNode.getMoves().add(buildTree(b));
    }
    return rootNode;
  }

  public Board getBoard() {
    return board;
  }

  public void setBoard(Board board) {
    this.board = board;
  }

  public ArrayList<MinimaxNode> getMoves() {
    return moves;
  }

  public void setMoves(ArrayList<MinimaxNode> moves) {
    this.moves = moves;
  }

  public int getValue() {
    return value;
  }

  public void setValue(int value) {
    this.value = value;
  }

}
