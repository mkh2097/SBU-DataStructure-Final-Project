//public class PieceTable {
//    private String original_buffer;
//    private String additional_buffer;
//    private DubLinkedList<Piece> nodes= new DubLinkedList<>();
//    private Piece currentPiece;
//
//    public Piece getCurrentPiece() {
//        return currentPiece;
//    }
//
//    public void setCurrentPiece(Piece currentPiece) {
//        this.currentPiece = currentPiece;
//    }
//
//    public void setAdditional_buffer(String additional_buffer) {
//        this.additional_buffer = additional_buffer;
//    }
//
//    public String getAdditional_buffer() {
//        return additional_buffer;
//    }
//
//    public void setNodes(DubLinkedList<Piece> nodes) {
//        this.nodes = nodes;
//    }
//
//    public DubLinkedList<Piece> getNodes() {
//        return nodes;
//    }
//}

//class Piece {
//
//
//    private PieceType type;
//    private int start;
//    private int length;
//    private DubLinkedList<Integer> new_line = new DubLinkedList<>();
//
//    public Piece(PieceType type, int start, int length) {
//        this.type = type;
//        this.start = start;
//        this.length = length;
//    }
//
//    public void setNew_line(DubLinkedList<Integer> new_line) {
//        this.new_line = new_line;
//    }
//
//    public DubLinkedList<Integer> getNew_line() {
//        return new_line;
//    }
//
//    public int getLength() {
//        return length;
//    }
//
//    public int getStart() {
//        return start;
//    }
//
//    public String getText() {
//        return text;
//    }
//
//    public PieceType getType() {
//        return type;
//    }
//
//    public int getEnd(){
//        return start + length;
//    }
//}
//
//enum PieceType {
//    Original,
//    Added
//}