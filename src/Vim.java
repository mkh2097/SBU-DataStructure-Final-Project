import java.util.Scanner;

enum Mode {
    command, insert, statics
}

enum CommandActions {
    right,
    left,
    up,
    down,
    begin_file,
    begin_line,
    end_file,
    end_line,
    line_select,
    next_word,
    prev_word,
    n_next_word,
    n_prev_word,
    erase_line,
    erase_cursor,
    copy,
    paste,
    undo,
    n_undo,
    redo,
    search,
    replace
}

enum InsertActions {
    right,
    left,
    up,
    down,
    escape
}

public class Vim {
    public static void main(String[] args) {
        Mode vim_mode = Mode.insert; //
        int cursor = 0;
        PieceTable pieceTable = new PieceTable();
        StringBuilder stringBuilder = new StringBuilder();
        Scanner scanner = new Scanner(System.in);


        while (true) {
            if (vim_mode == Mode.command) {
                String command = scanner.next();
                switch (command) {
                    case "i":
                        vim_mode = Mode.insert;
                        break;
                    case "c":
                        vim_mode = Mode.command;
                        break;
                    case "v":
                        vim_mode = Mode.statics;
                        break;
                    case ">":
//                        if (pieceTable.getAdditional_buffer().length() > cursor) {
                        cursor++;
//                        }
                        break;
                    case "<":
                        if (cursor > 0) {
                            cursor--;
                        }
                        break;
                    case "o":

//                        A : while(pieceTable.getAdditional_buffer().charAt(cursor-1) != 'n'){
//                                cursor--;
//                        }
//                        if (cursor >= 2) {
//                            if(pieceTable.getAdditional_buffer().charAt(cursor - 2) == '\\'){
//                                continue A;
//                            }
//                        }else {
//                            A;
//                        }
                        break;
                    case "$":
                        break;
                    case "b":
                        cursor = 0;
                        break;
                    case "e":
//                        cursor = pieceTable.getAdditional_buffer().length();
                        break;
                    case ":w":
                        break;
                    case ":b":
                        break;


                }
            } else if (vim_mode == Mode.insert) {
                String input = scanner.next();
                input = input.replace("\\n", "\n");

                switch (input) {
                    case "esc":
                        vim_mode = Mode.command;
                        break;
                    case ">":
                        cursor++;
                        if (cursor >= pieceTable.getCurrent_piece().getData().getLength()) {
                            if (pieceTable.getCurrent_piece().getNext() != null) {
                                System.out.println(pieceTable.getCurrent_piece().getNext());
                                pieceTable.setCurrent_piece(pieceTable.getCurrent_piece().getNext());
                                cursor = 0;
                            }
                        else if (cursor != pieceTable.getCurrent_piece().getData().getLength()) {
                                cursor--;
                            }
                        }
                        System.out.println("Answer: "+pieceTable.getCurrent_piece());
                        System.out.println(pieceTable.getCurrent_piece().getData().getLength());
                        System.out.println("cursor->" + cursor);
                        break;
                    case "<":
                            cursor--;
                        if (cursor < 0) {
                            if (pieceTable.getCurrent_piece().getPrevious() != null) {
                                pieceTable.setCurrent_piece(pieceTable.getCurrent_piece().getPrevious());
                                cursor = pieceTable.getCurrent_piece().getData().getLength() - 1;
                            }else {
                                cursor++;
                            }
                        }

                        System.out.println(pieceTable.getCurrent_piece().getData().getLength());
                        System.out.println("cursor->" + cursor);
                        break;
                    default:
                        //String input_buffer = stringBuilder.append(input).append("\\d").toString();
//                    String input_buffer = stringBuilder.insert(cursor, input).toString();
//                    pieceTable.setAdditional_buffer(input_buffer);
                        //pieceTable.getAdditional_buffer() += input

                        PieceInfo pieceInfo;
                        if (pieceTable.getAdditional_buffer() != null) {
                            pieceInfo = new PieceInfo(pieceTable.getAdditional_buffer().length(), input.length(), PieceType.Added);
                        } else {
                            pieceInfo = new PieceInfo(0, input.length(), PieceType.Added);
                        }
                        Piece piece = new Piece(null, null, pieceInfo);


//                    System.out.println(pieceTable.getCurrent_piece());
                        Piece out;

                        if (pieceTable.getCurrent_piece() == null){
                            out = pieceTable.Insert(pieceTable.getCurrent_piece(),piece);
                            pieceTable.setCurrent_piece(out);
                            cursor = input.length();
                        }
                        else if (cursor == 0){
                            out = pieceTable.Insert(pieceTable.getCurrent_piece().getPrevious(),piece);
//                            cursor = 0;
//                            pieceTable.setCurrent_piece(out.getNext());
                        }else if (cursor > 0 && cursor < pieceTable.getCurrent_piece().getData().getLength()){
                            System.out.println("hooooo");
                            int startPoint = pieceTable.getCurrent_piece().getData().getStart();
                            int endPoint = pieceTable.getCurrent_piece().getData().getEnd();
                            PieceInfo preInfo = new PieceInfo(startPoint, cursor, PieceType.Added);
                            PieceInfo nextInfo = new PieceInfo(cursor + startPoint, pieceTable.getCurrent_piece().getData().getLength() - cursor, PieceType.Added);

                            Piece split = new Piece(pieceTable.getCurrent_piece(), pieceTable.getCurrent_piece().getNext(), nextInfo);
                            pieceTable.Insert(pieceTable.getCurrent_piece(),split);

                            pieceTable.getCurrent_piece().setData(preInfo);



                            out = pieceTable.Insert(pieceTable.getCurrent_piece(),piece);

                            pieceTable.setCurrent_piece(out.getNext());
                            cursor =0;

                        }else if (cursor == pieceTable.getCurrent_piece().getData().getLength()){
                            out = pieceTable.Insert(pieceTable.getCurrent_piece(),piece);
                            pieceTable.setCurrent_piece(out);
                            cursor = input.length();
                        }

                        System.out.println("Current->"+pieceTable.getCurrent_piece());
                        System.out.println("cursor->" + cursor);
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



                        String pre = "";
                        if (pieceTable.getAdditional_buffer() != null) {
                            pre = pieceTable.getAdditional_buffer();
                        }
                        pieceTable.setAdditional_buffer(pre + input);
//                        cursor += input.length();


//                        System.out.println(cursor);

//                    pieceTable.getAdditional_buffer().add();
                        System.out.println("**********************************************************");
                        System.out.println(pieceTable.getCurrent_piece().getPrevious());
                        System.out.println(pieceTable.getCurrent_piece());
                        System.out.println(pieceTable.getCurrent_piece().getNext());
                        System.out.println("**********************************************************");



//                    if (pieceTable.getCurrent_piece() != null) {
//                        piece.setNext(pieceTable.getCurrent_piece().getNext());
//                        piece.setPrevious(pieceTable.getCurrent_piece());
//                        pieceTable.getCurrent_piece().setNext(piece);
//                        if (pieceTable.getCurrent_piece().getNext() != null)
//                            pieceTable.getCurrent_piece().getNext().setPrevious(piece);


//                    System.out.println(pieceTable.getCurrent_piece());
                        break;
                }

//                    pieceTable.setCurrent_piece(piece);


//                    pieceTable.getNodes().Insert(pi);
//                    Piece piece = new Piece(PieceType.Added, cursor, input.length());
//                    Piece<Piece> node = new Piece<>(pieceTable.getNodes().getSelectedNode().getPrevious(),pieceTable.getNodes().getSelectedNode().getNext(),pieceTable.getNodes().getSelectedNode().getData());
//                    pieceTable.getNodes().setSelectedNode(node);
//                    Node<Piece> node = new Node<Piece>(pieceTable.getCurrentPiece());
//                    pieceTable.getNodes().Retrieve(pieceTable.getCurrentPiece())

//                    pieceTable.getNodes().add(piece);

            } else if (vim_mode == Mode.statics) {

            }
//            System.out.println(cursor);
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
            Piece temp = pieceTable.First();
//            System.out.println(temp);
            String out = "";
            while (temp != null) {
                System.out.println("Start");
                System.out.println(temp.getData().getStart());
                System.out.println("Length");
                System.out.println(temp.getData().getLength());
                System.out.println("End");
                System.out.println(temp.getData().getEnd());

                String o = pieceTable.getAdditional_buffer().substring(temp.getData().getStart(),temp.getData().getEnd()+1);

                if(temp.equals( pieceTable.getCurrent_piece())){
                    if (pieceTable.getCurrent_piece().getData().getLength() != cursor) {
                        System.err.println(pieceTable.getCurrent_piece().getData().getLength());
                        o = o.substring(0, cursor) + "*" + o.substring(cursor);
                    }
                    else {
                        o = o + "*";
                    }
                }
                out = out + o;
                temp = temp.getNext();
            }
            System.out.println(out);

        }
    }
}

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
