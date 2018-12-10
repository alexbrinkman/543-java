import java.util.ArrayList;

public class Board {

  private boolean[][] position;

  public Board(boolean[][] position) {
    this.position = position == null ? initialBoard() : position;
  }

  public void move(int row, int num) {
    validateMove(row, num);
    updateBoard(row - 1, num);
  }

  public boolean winner() {
    return totalPiecesLeft() == 1;
  }

  public ArrayList<Board> availableMoves() {
    // One move for each piece of each row, unless there are only pieces left in one row.
    ArrayList<Board> boards = new ArrayList<>();
    int startAt = oneRowLeft() ? 1 : 0;
    for(int i = 0; i < this.position.length; i++) {
      boolean[] row = this.position[i];
      for(int j = startAt; j < row.length; j++) {
        boolean[][] newPosition = this.position.clone();
        newPosition[i] = createRow(j);
        boards.add(new Board(newPosition));
      }
    }
    return boards;
  }

  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("---------\n");
    for (int i = 0; i < position.length; i++) {
      stringBuilder.append("row " + (i + 1) + ": ");
      boolean[] row = position[i];
      for (int j = 0; j < row.length; j++) {
        if (row[j]) {
          stringBuilder.append("O ");
        }
      }
      stringBuilder.append("\n");
    }
    stringBuilder.append("---------");
    return stringBuilder.toString();
  }

  private void validateMove(int row, int num) {
    if (row < 1 || row > this.position.length) {
      throw new IllegalArgumentException("Invalid row: please enter 1-" + this.position.length + ".");
    }

    if (num < 1) {
      throw new IllegalArgumentException("Invalid number: please enter at least 1 piece.");
    }

    if (num > piecesLeftInRow(row - 1)) {
      throw new IllegalArgumentException("Invalid number: please enter no more than the number remaining in the row.");
    }

    if (num >= totalPiecesLeft()) {
      throw new IllegalArgumentException("Invalid number: you may not take all remaining pieces.");
    }
  }

  private int piecesLeftInRow(int row) {
    boolean[] boardRow = this.position[row];
    int count = 0;
    for (int i = 0; i < boardRow.length; i++) {
      if (boardRow[i]) {
        count++;
      }
    }
    return count;
  }

  private int totalPiecesLeft() {
    int count = 0;
    for (int i = 0; i < this.position.length; i++) {
      count += piecesLeftInRow(i);
    }
    return count;
  }

  private boolean oneRowLeft() {
    for (int i = 0; i < this.position.length; i++) {
      if (piecesLeftInRow(i) == totalPiecesLeft()) {
        return true;
      }
    }
    return false;
  }

  private void updateBoard(int row, int num) {
    int piecesLeft = piecesLeftInRow(row) - num;
    boolean[] newRow = new boolean[piecesLeft];
    for (int i = 0; i < piecesLeft; i++) {
      newRow[i] = true;
    }
    this.position[row] = newRow;
  }

  private boolean[] createRow(int pieces) {
    boolean[] list = new boolean[pieces];
    for (int i = 0; i < pieces; i++) {
      list[i] = true;
    }
    return list;
  }

  private boolean[][] initialBoard() {
    boolean[][] position = new boolean[3][];
    position[0] = new boolean[] { true, true, true };
    position[1] = new boolean[] { true, true, true, true };
    position[2] = new boolean[] { true, true, true, true, true };
    return position;
  }
}
