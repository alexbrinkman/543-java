public class Minimax {

  private Board board;

  public Minimax(Board board) {
    this.board = board;
  }

  public Board search() {
    System.out.println("Building tree...");
    MinimaxNode rootNode = new MinimaxNode(this.board).buildTree(this.board, 0);

    System.out.println("Evaluating positions...");
    rootNode = assignNodeValues(rootNode, "max", -10, 10);

    System.out.println("Making best move...");
    MinimaxNode move = (MinimaxNode)bestNextMove(rootNode, "max")[1];
    return move.getBoard();
  }

  private MinimaxNode assignNodeValues(MinimaxNode rootNode, String maxMin, int alpha, int beta) {
    for (MinimaxNode node : rootNode.getMoves()) {
      if (node.getBoard().winner()) {
        node.setValue(valueOfWin(maxMin));
      }
      else {
        assignNodeValues(node, flipMaxMin(maxMin), alpha, beta);
        node.setValue((int)bestNextMove(node, flipMaxMin(maxMin))[0]);
        if(maxMin.equals("max")) {
          if (node.getValue() > alpha) {
            alpha = node.getValue();
          }
          if (alpha >= beta) {
            break;
          }
        }
        else {
          if (node.getValue() < beta) {
            beta = node.getValue();
          }
          if (alpha >= beta) {
            break;
          }
        }
      }
    }

    return rootNode;
  }

  private Object[] bestNextMove(MinimaxNode node, String minMax) {
    if (node.getMoves().isEmpty()) {
      return new Object[] { 0, null };
    }

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
