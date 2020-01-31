import java.util.List;

class PieceTable {

    private List<Character> original_buffer;
    private List<Character> additional_buffer_;
    private String additional_buffer;

    private int NOE;
    private Piece list;
    private Piece end_list;
    private Piece current_piece;

    public String getAdditional_buffer() {
        return additional_buffer;
    }

    public void setAdditional_buffer(String additional_buffer) {
        this.additional_buffer = additional_buffer;
    }

    public void setCurrent_piece(Piece current_piece) {
        this.current_piece = current_piece;
    }

    public Piece getCurrent_piece() {
        return current_piece;
    }

    public Piece getEnd_list() {
        return end_list;
    }

    public PieceTable() {
        NOE = 0;
        list = new Piece(null, null, null);
        end_list = new Piece(null, null, null);
    }

    public int size() {
        return NOE;
    }


    public void MakeNULL() {
        NOE = 0;
    }

    public Piece Insert(Piece position, Piece element) {
        Piece q = new Piece(null, null, null);
        q.setData(element.getData());
        if (position != null) {
            q.setNext(position.getNext());
            q.setPrevious(position);
            if (position.getNext() != null) {
                position.getNext().setPrevious(q);
            }
            position.setNext(q);
        } else {
            q.setNext(list.getNext());
            if (list.getNext() != null) {
                list.getNext().setPrevious(q);
            }
            q.setPrevious(null);
            list.setNext(q);
        }

        if (q.getNext() == null) {
            end_list = q;
        }

        NOE++;
        return q;
    }

    public void Delete(Piece position) {
        if (position != First()) {
            position.getPrevious().setNext(position.getNext());
            if (position.getNext() != null) {
                position.getNext().setPrevious(position.getPrevious());
            } else {
                end_list = position.getPrevious();
            }
        } else {
            list.setNext(list.getNext().getNext());
            list.getNext().setPrevious(list);
//            list = list.getNext();
//            list.setPrevious(null);
        }
        position.setPrevious(null);
        position.setNext(null);
        position.setData(null);
        NOE--;
    }

    public Piece Retrieve(Piece position) {
        return position;
    }

    public boolean isEmpty() {
        return NOE == 0;
    }

    public Piece First() {
        return list.getNext();
    }

    public Piece Next(Piece position) {
        if (position.getNext()!= null)
            return position.getNext();
        return null;
    }

    public Piece Prev(Piece position) {
        if (position.getPrevious() != null)
            return position.getPrevious();
        return null;
    }
}

class Position {
    private Piece address;
}

enum PieceType {
    Original,
    Added
}

class PieceInfo {
    private int start;
    private int length;
    private PieceType type;

    public int getEnd()
    {
        return start + length - 1;
    }
    public void setStart(int start) {
        this.start = start;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setType(PieceType type) {
        this.type = type;
    }

    public int getStart() {
        return start;
    }

    public int getLength() {
        return length;
    }

    public PieceType getType() {
        return type;
    }

    public PieceInfo(int start, int length, PieceType type) {
        this.start = start;
        this.length = length;
        this.type = type;
    }
}

class Piece {
    private Piece previous;
    private Piece next;
    private PieceInfo data;



    public Piece(Piece previous, Piece next, PieceInfo data) {
        this.previous = previous;
        this.next = next;
        this.data = data;
    }

    public void setPrevious(Piece previous) {
        this.previous = previous;
    }

    public void setNext(Piece next) {
        this.next = next;
    }

    public void setData(PieceInfo data) {
        this.data = data;
    }

    public Piece getPrevious() {
        return previous;
    }

    public Piece getNext() {
        return next;
    }

    public PieceInfo getData() {
        return data;
    }
}

