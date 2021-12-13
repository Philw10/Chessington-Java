package training.chessington.model.pieces;

import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.ArrayList;
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

        if (colour == PlayerColour.WHITE & from.getRow() > 0 & from.getCol() < 7 & from.getCol() > 0){
            moves.add(new Move(from, from.plus(-1, 0)));
            Piece checkToTakeOneWhite = board.get(new Coordinates(from.getRow()-1, from.getCol()-1));
            if (checkToTakeOneWhite != null){
                if (checkToTakeOneWhite.getColour().equals(PlayerColour.BLACK)) {
                    moves.add(new Move(from, from.plus(-1, -1)));
                }
            }
            Piece checkToTakeTwoWhite = board.get(new Coordinates(from.getRow()-1, from.getCol()+1));
                if (checkToTakeTwoWhite != null) {
                    if (checkToTakeTwoWhite.getColour().equals(PlayerColour.BLACK)) {
                        moves.add(new Move(from, from.plus(-1, 1)));
                    }
                }
        }else if (colour == PlayerColour.BLACK & from.getRow() < 7 & from.getCol() < 7 & from.getCol() > 0){
            moves.add(new Move(from, from.plus(1, 0)));
            Piece checkToTakeOneBlack = board.get(new Coordinates(from.getRow()+1, from.getCol()-1));
            if (checkToTakeOneBlack != null) {
                if (checkToTakeOneBlack.getColour().equals(PlayerColour.WHITE)) {
                    moves.add(new Move(from, from.plus(1, -1)));
                }
            }
            Piece checkToTakeTwoBlack = board.get(new Coordinates(from.getRow()+1, from.getCol()+1));
            if (checkToTakeTwoBlack != null) {
                if (checkToTakeTwoBlack.getColour().equals(PlayerColour.WHITE)) {
                    moves.add(new Move(from, from.plus(1, 1)));
                }
            }
        }

        for (int i = 0; i < moves.size(); i++) {
            Piece piece = board.get(new Coordinates(moves.get(i).getTo().getRow(), moves.get(i).getTo().getCol()));
            if (from.getCol() == moves.get(i).getTo().getCol()) {
                if (piece != null) {
                    moves.remove(i);
                }
            }
        }
        return moves;
    }
}
