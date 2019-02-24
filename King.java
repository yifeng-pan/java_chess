public class King extends Piece{
    public King(boolean white){
        super(Type.King, white);
    }

    @Override
    public boolean isValid(Piece[][] board, Cord from, Cord to){
        boolean valid = false;
        int dx = abs(from.getX() - to.getX());
        int dy = abs(from.getY() - to.getY());
        if(dx == 1 && dy <= 1) valid = true;
        if(dy == 1 && dx == 0) valid = true;
        return valid && super.isValid(board, from, to);
    }

    @Override
    public char toCharacter(){
        return isWhite? 'K' : 'k';
    }
}
