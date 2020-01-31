
//public class Vim {
//
//    private static Vim vim_instance = null;
//
//    private Vim(Mode selectedMode) {
//        this.selectedMode = selectedMode;
//    }
//
//    public static Vim getInstance() {
//        if (vim_instance == null)
//            vim_instance = new Vim(Mode.command);
//
//        return vim_instance;
//    }
//
//    private Mode selectedMode;
//
//    public void selectMode(char mode) {
//        switch (mode) {
//            case 'c':
//                this.selectedMode = Mode.command;
//                break;
//            case 'i':
//                selectedMode = Mode.insert;
//                break;
//            case 's':
//                selectedMode = Mode.statics;
//                break;
//        }
//    }
//
//    public void get_command(String command, int num) {
//        switch (command) {
//
//        }
//    }
//
//    public static void main(String[] args) {
//        int cursor = 0;
//
//        Scanner scanner = new Scanner(System.in);
//        PieceTable pieceTable = new PieceTable();
//        while (true) {
//            String input = scanner.next();
//
//            switch (input) {
//                case "<":
//                    cursor--;
//                    break;
//                case ">":
//                    cursor++;
//                    break;
//            }
//
//            for (char i : input.toCharArray()) {
//                pieceTable.texts.add(i);
//                cursor++;
//            }
//            System.out.println(Arrays.toString(pieceTable.texts.toArray()) + "\n" + cursor);
//        }
//
//    }
//}
//
//}

//                System.out.println("Start");
//                System.out.println(temp.getData().getStart());
//                System.out.println("Length");
//                System.out.println(temp.getData().getLength());
//                System.out.println("End");
//                System.out.println(temp.getData().getEnd());


//class PieceTabl {
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
//            System.out.println(temp);

//            System.out.println(cursor);f
//            System.out.println(pieceTable.getCurrent_piece().getData().getLength());
//            System.out.println(pieceTable.getAdditional_buffer().substring(0,cursor)+"*"+pieceTable.getAdditional_buffer().substring(cursor));

//            Piece temp = pieceTable.First();
////            System.out.println(temp);
//            String out = "";
//            while (temp != null) {
//                System.out.println(temp.getData().getLength());
//
////            System.out.println("Start: " + temp.getData().getStart() + "&" + "Length:  " + temp.getData().getLength());
//                temp = temp.getNext();
//            }
//            System.out.println(out.substring(0, cursor) + "*" + out.substring(cursor));
//            System.out.println(out);


//            for (int i = 0; i < pieceTable.getNodes().size(); i++) {
//                System.out.println(pieceTable.getNodes().get(i).getStart() + "," + pieceTable.getNodes().get(i).getLength());
//                for (int j = 0 ; j < pieceTable.getNodes().get(i).getNew_line().size() ; j++) {
//                    System.out.println(pieceTable.getNodes().get(i).getNew_line().get(j));
//                }
//                System.out.println("***************");
//            }
//            try {
//                System.out.println(pieceTable.getAdditional_buffer().substring(0, cursor) + "*" + pieceTable.getAdditional_buffer().substring(cursor));
//            System.out.println(temp);
//            System.out.println(temp.getPrevious());
//            System.out.println(temp.getNext());


//                    pieceTable.setCurrent_piece(piece);


//                    pieceTable.getNodes().Insert(pi);
//                    Piece piece = new Piece(PieceType.Added, cursor, input.length());
//                    Piece<Piece> node = new Piece<>(pieceTable.getNodes().getSelectedNode().getPrevious(),pieceTable.getNodes().getSelectedNode().getNext(),pieceTable.getNodes().getSelectedNode().getData());
//                    pieceTable.getNodes().setSelectedNode(node);
//                    Node<Piece> node = new Node<Piece>(pieceTable.getCurrentPiece());
//                    pieceTable.getNodes().Retrieve(pieceTable.getCurrentPiece())

//                    pieceTable.getNodes().add(piece);



//                        cursor += input.length();


//                        System.out.println(cursor);

//                    pieceTable.getAdditional_buffer().add();
//                        System.out.println("**********************************************************");
//                        System.out.println(pieceTable.getCurrent_piece().getPrevious());
//                        System.out.println(pieceTable.getCurrent_piece());
//                        System.out.println(pieceTable.getCurrent_piece().getNext());
//                        System.out.println("**********************************************************");


//                    if (pieceTable.getCurrent_piece() != null) {
//                        piece.setNext(pieceTable.getCurrent_piece().getNext());
//                        piece.setPrevious(pieceTable.getCurrent_piece());
//                        pieceTable.getCurrent_piece().setNext(piece);
//                        if (pieceTable.getCurrent_piece().getNext() != null)
//                            pieceTable.getCurrent_piece().getNext().setPrevious(piece);


//                    System.out.println(pieceTable.getCurrent_piece());




//                        System.out.println("Current->" + pieceTable.getCurrent_piece());
//                        System.out.println("cursor->" + cursor);
//                        if (pieceTable.getCurrent_piece() == null) {
//                            System.out.println("hich");
//                            out = pieceTable.Insert(pieceTable.getCurrent_piece(), piece);
//
//                            pieceTable.setCurrent_piece(out);
//                        } else if (pieceTable.getCurrent_piece().getData().getStart() == cursor) {
//                            System.out.println("avval");
//                            out = pieceTable.Insert(pieceTable.getCurrent_piece().getPrevious(), piece);
//                            pieceTable.setCurrent_piece(out);
//                        } else if (pieceTable.getCurrent_piece().getNext() == null && pieceTable.getCurrent_piece().getData().getEnd() + 1 == cursor) {
//                            System.out.println("akhar");
//                            out = pieceTable.Insert(pieceTable.getCurrent_piece(), piece);
//                            pieceTable.setCurrent_piece(out);
//                        } else {
//                            System.out.println("vasat");
//
//                            int startPoint = pieceTable.getCurrent_piece().getData().getStart();
//                            int endPoint = pieceTable.getCurrent_piece().getData().getEnd();
//                            System.out.println(cursor);
//                            PieceInfo preInfo = new PieceInfo(startPoint - 1, cursor - startPoint, PieceType.Added);
//                            PieceInfo nextInfo = new PieceInfo(cursor, endPoint - cursor + 1, PieceType.Added);
//
//
//                            Piece split = new Piece(pieceTable.getCurrent_piece(), pieceTable.getCurrent_piece().getNext(), nextInfo);
//                            if (pieceTable.getCurrent_piece().getNext() != null) {
//                                pieceTable.getCurrent_piece().getNext().setPrevious(split);
//                            }
//                            pieceTable.getCurrent_piece().setNext(split);
//                            pieceTable.getCurrent_piece().setData(preInfo);
//
//                            out = pieceTable.Insert(pieceTable.getCurrent_piece(), piece);
//
//
////                            out.getPrevious().setData(preInfo);
////
////                            pieceTable.getCurrent_piece().setNext(out);
//////                            if (out.getNext() != null) {
//////                                out.getNext().setData(nextInfo);
//////                            }
//                            pieceTable.setCurrent_piece(out.getNext());
//                        }


//                        out = pieceTable.Insert(pieceTable.getCurrent_piece(), piece);
//                            pieceTable.setCurrent_piece(out);