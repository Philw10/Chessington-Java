package training.chessington.model.pieces;

import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pawn extends AbstractPiece {
    public Pawn(PlayerColour colour) {
        super(Piece.PieceType.PAWN, colour);
    }

    @Override
    public List<Move> getAllowedMoves(Coordinates from, Board board) {
        List<Move> moves = new ArrayList<>();

        if (colour == PlayerColour.WHITE & from.getRow() == 6) {
            moves.add(new Move(from, from.plus(-2, 0)));

        }else if (colour == PlayerColour.BLACK & from.getRow() == 1){
            moves.add(new Move(from, from.plus(2, 0)));
        }

        if (colour == PlayerColour.WHITE & from.getRow() > 0){
            moves.add(new Move(from, from.plus(-1, 0)));

        }else if (colour == PlayerColour.BLACK & from.getRow() < 7){
            moves.add(new Move(from, from.plus(1, 0)));;
        }

        for (int i = 0; i < moves.size(); i++) {
            Piece piece = board.get(new Coordinates(moves.get(i).getTo().getRow(), moves.get(i).getTo().getCol()));
            if (piece == null){
                continue;
            }else{
                moves.remove(i);
            }
        }

        return moves;
    }
}
