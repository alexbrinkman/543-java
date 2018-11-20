public class Minimax {

  private Board board;

  public Minimax(Board board) {
    this.board = board;
  }

  public Board search() {
    MinimaxNode rootNode = new MinimaxNode(this.board).buildTree(this.board);
    rootNode = assignNodeValues(rootNode, "max");
    MinimaxNode move = (MinimaxNode)bestNextMove(rootNode, "max")[1];
    return move.getBoard();
  }

  private MinimaxNode assignNodeValues(MinimaxNode rootNode, String maxMin) {
    for (MinimaxNode node : rootNode.getMoves()) {
      if (node.getBoard().winner()) {
        node.setValue(valueOfWin(maxMin));
      }
      else {
        assignNodeValues(node, flipMaxMin(maxMin));
        node.setValue((int)bestNextMove(node, flipMaxMin(maxMin))[0]);
      }
    }

    return rootNode;
  }

  private Object[] bestNextMove(MinimaxNode node, String minMax) {
    int bestMoveValue = node.getMoves().get(0).getValue();
    MinimaxNode bestMove = node.getMoves().get(0);

    for (MinimaxNode move : node.getMoves()) {
      if (minMax.equals("max")) {
        if (move.getValue() > bestMoveValue) {
          bestMoveValue = move.getValue();
          bestMove = move;
        }
      }
      else {
        if (move.getValue() < bestMoveValue) {
          bestMoveValue = move.getValue();
          bestMove = move;
        }
      }
    }

    return new Object[] { bestMoveValue, bestMove };
  }

  private String flipMaxMin(String maxMin) {
    return maxMin.equals("max") ? "min" : "max";
  }

  private int valueOfWin(String maxMin) {
    return maxMin.equals("max") ? 1 : -1;
  }

}
