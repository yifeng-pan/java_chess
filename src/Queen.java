import java.util.ArrayList;

public class Queen extends Piece{
    public Queen(boolean white){
        super(Type.Queen, white);
    }

    public Queen(Piece piece){
        this(piece.getColor());
    }

    @Override
    public boolean isValid(Game game, Move move){
        if(!super.isValid(game, move)) return false;

        Cord from = move.from();
        Cord to = move.to();
        int dx = Math.abs(move.dx());
        int dy = Math.abs(move.dy());
        int modX = move.dx() > 0? Constant.POSITIVE : Constant.NEGATIVE;
        int modY = move.dy() > 0? Constant.POSITIVE : Constant.NEGATIVE;

        if(dx != dy && dx != 0 && dy != 0) return false;

        if (dy == 0){
            for(int i = 1; i < dx; i++)
                if(game.getPiece(new Cord(from.rank(), from.file() + i * modX)).getType() != Type.Empty)
                    return false;
        }else if (dx == 0){ 
            for(int i = 1; i < dy; i++)
                if(game.getPiece(new Cord(from.rank() + i * modY, from.file())).getType() != Type.Empty)
                    return false;
        }else{
            for(int i = 1; i < dx; i++) // dx == dy
                if(game.getPiece(new Cord(from.rank() + i * modY, from.file() + i * modX)).getType() != Type.Empty)
                    return false;
        }
        return true;
    }

    @Override
    public void updateValue(){
        value = Constant.QUEEN_VALUE;
    }

    @Override
    public void updateValue(Game game, Cord at){
        value = Constant.QUEEN_VALUE;
        value += validMoves(game, at).size() * Constant.QUEEN_SCOPE;
    }

    @Override
    public ArrayList<Cord> validMoves(Game game, Cord from){
        ArrayList<Cord> moves = new ArrayList<Cord>();
        Cord test;

        for(int i = 0; i < 2; i++)
            for(int j = 0; j < 2; j++){
                int modx = i == 0 ? Constant.POSITIVE : Constant.NEGATIVE;
                int mody = j == 0 ? Constant.POSITIVE : Constant.NEGATIVE;

                for(int k = 1; k < game.getRankSize() && k < game.getFileSize(); k++){
                    test = new Cord(from.rank() + (k *modx), from.file() + (k * mody));
                    if(isValid(game, new Move(from, test)))
                        moves.add(test);
                }

                for(int k = 1; k < game.getRankSize() || k < game.getFileSize(); k++){
                    test = new Cord(from.rank() + (k * modx), from.file());
                    if(isValid(game, new Move(from, test)))
                        moves.add(test);
                        
                    test = new Cord(from.rank(), from.file() + (k * mody));
                    if(isValid(game, new Move(from, test)))
                        moves.add(test);
                }
            }

        return moves;
    }

    @Override
    public char toCharacter(){
        return isWhite? 'Q' : 'q';
    }
}
