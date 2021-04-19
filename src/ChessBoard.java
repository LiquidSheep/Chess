import java.util.HashMap;
import java.util.Map;

public class ChessBoard {
  public int[] squares;
  public final String startFEN = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";

  public ChessBoard() {
    squares = new int[64];
    loadPositionFromFen(startFEN);
  }

  public void loadPositionFromFen(String fen) {
    Map<Character, Integer> pieceTypeFromSymbol = new HashMap<Character, Integer>() {
      {
        put('p', Piece.Pawn);
        put('n', Piece.Knight);
        put('b', Piece.Bishop);
        put('r', Piece.Rook);
        put('q', Piece.Queen);
        put('k', Piece.King);
      }
    };

    String fenBoard = fen.split(" ")[0];
    int file = 0;
    int rank = 7;

    for (char symbol : fenBoard.toCharArray()) {
      if (symbol=='/') {
        file = 0;
        rank--;
      } else {
        if (Character.isDigit(symbol)) {
          file += (int) Character.getNumericValue(symbol);
        } else {
          int pieceColor = (Character.isUpperCase(symbol)) ? Piece.White : Piece.Black;
          int pieceType = pieceTypeFromSymbol.get(Character.toLowerCase(symbol)).intValue();
          squares[rank*8+file] = pieceType | pieceColor;
          file++;
        }
      }
    }
  }
}
