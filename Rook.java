public class Rook extends Piece{
    public Rook(boolean white){
        super(Type.Rook, white);
    }

    @Override
    public boolean is_valid(Piece[][] board, Cord from, Cord to){
      int dx = abs(from.get_x() - to.get_x());
      int dy = abs(from.get_y() - to.get_y());
      if (((dx == 0) && (dy != 0)) || ((dx != 0) && (dx == 0))) return true;
      return false;
    }

    @Override
    public char to_char(){
        return is_white? 'R' : 'r';
    }
}
