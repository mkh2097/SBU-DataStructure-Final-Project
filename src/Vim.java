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
        TrieTree trieTree = new TrieTree();
        Scanner scanner = new Scanner(System.in);
        String clipBoard = "";


        while (true) {
            if (vim_mode == Mode.command) {
                String command = scanner.next();

                switch (command) {
                    case "i":
                        System.out.println("############################ Input MODE ############################");
                        vim_mode = Mode.insert;
                        break;
                    case "c":
                        System.out.println("############################ Command MODE ############################");
                        vim_mode = Mode.command;
                        break;
                    case "v":
                        System.out.println("############################ Statistics MODE ############################");
                        vim_mode = Mode.statics;
                        break;
                    case ">":
                        cursor = forwardCursor(cursor, pieceTable);
                        break;
                    case "<":
                        cursor = backCursor(cursor, pieceTable);
                        break;
                    case "o":
                        cursor = beginLine(cursor, pieceTable);
                        break;
                    case "$":
                        cursor = endLine(cursor, pieceTable);
                        break;
                    case "bf":
                        cursor = 0;
                        pieceTable.setCurrent_piece(pieceTable.First());
                        break;
                    case "ef":
                        pieceTable.setCurrent_piece(pieceTable.getEnd_list());
                        cursor = pieceTable.getCurrent_piece().getData().getLength();
                        break;
                    case ":w":
                        cursor = nextWord(cursor, pieceTable);
                        break;
                    case ":b":
                        cursor = previousWord(cursor, pieceTable);
                        break;
                    case ":":
                        int number = scanner.nextInt() - 1;
                        int counter = 0;
                        int startE = pieceTable.getCurrent_piece().getData().getStart();
                        int endE = pieceTable.getCurrent_piece().getData().getEnd();
                        int cursorE = 0;
                        pieceTable.setCurrent_piece(pieceTable.First());
                        while (counter < number) {
                            if (cursorE == pieceTable.getCurrent_piece().getData().getLength()) {
                                break;
                            }
                            while (pieceTable.getAdditional_buffer().substring(startE, endE + 1).charAt(cursorE) != '\n') {
                                cursorE = forwardCursor(cursorE, pieceTable);
                                if (cursorE == pieceTable.getCurrent_piece().getData().getLength()) {
                                    break;
                                }
                                startE = pieceTable.getCurrent_piece().getData().getStart();
                                endE = pieceTable.getCurrent_piece().getData().getEnd();
                            }
                            cursorE = forwardCursor(cursorE, pieceTable);
                            counter++;
                        }
                        cursor = cursorE;

                        break;
                    case ":dd":
                        cursor = beginLine(cursor, pieceTable);
                        int beginningOfLine = cursor;
                        Piece beginPiece = pieceTable.getCurrent_piece();

                        cursor = endLine(cursor, pieceTable);
                        int endOfLine = cursor;
                        Piece endPiece = pieceTable.getCurrent_piece();


                        Piece iterator = beginPiece.getNext();

                        while (iterator != endPiece && iterator.getNext() != null) {
//                            System.out.println(iterator.getPrevious());
//                            System.out.println(iterator);
//                            System.out.println(iterator.getNext());
                            Piece next = iterator.getNext();
                            pieceTable.Delete(iterator);
                            iterator = next;
                        }
//                        System.err.println("Now: " +pieceTable.getCurrent_piece().getData().getLength());
//                        System.err.println("Pre: " +pieceTable.getCurrent_piece().getPrevious().getData().getLength());
                        pieceTable.getCurrent_piece().getData().setLength(pieceTable.getCurrent_piece().getData().getLength() - endOfLine - 1);
                        pieceTable.getCurrent_piece().getData().setStart(pieceTable.getCurrent_piece().getData().getStart() + endOfLine + 1);

                        if (pieceTable.getCurrent_piece().getPrevious() != null) {
                            pieceTable.getCurrent_piece().getPrevious().getData().setLength(beginningOfLine);
                        }
                        cursor = 0;
                        cursor = insertText(cursor, pieceTable, trieTree, "\n");
                        cursor = backCursor(cursor, pieceTable);

                        System.err.println("Now: " + pieceTable.getCurrent_piece().getData().getLength());
                        System.err.println("Pre: " + pieceTable.getCurrent_piece().getPrevious().getData().getLength());
//                        beginPiece.getData().setLength(beginningOfLine);
//                        System.err.println("Start: "+endPiece.getData().getStart());
//                        System.err.println("Length "+endPiece.getData().getLength());
//                        endPiece.getData().setStart(endOfLine+1);
//                        endPiece.getData().setLength(endPiece.getData().getLength()-endOfLine+1);
                        break;
                    case ":D":
                        int currentPos = cursor;
                        Piece currentPiecePos = pieceTable.getCurrent_piece();
                        cursor = endLine(cursor, pieceTable);
                        int endOfLineB = cursor;
                        Piece endPieceB = pieceTable.getCurrent_piece();

                        Piece iteratorB = currentPiecePos;
                        while (iteratorB != endPieceB && iteratorB.getNext() != null) {
                            Piece next = iteratorB.getNext();
                            pieceTable.Delete(iteratorB);
                            iteratorB = next;
                        }
//                        pieceTable.getCurrent_piece().getData().setLength(pieceTable.getCurrent_piece().getData().getStart()-endOfLineB);
//                        pieceTable.getCurrent_piece().getData().setStart(pieceTable.getCurrent_piece().getData().getStart()+endOfLineB);
                        break;
                    case ":y":
                        Piece saveState = pieceTable.getCurrent_piece();
                        int saveCursor = cursor;

                        cursor = beginLine(cursor, pieceTable);
                        int beginningOfLineC = cursor;
                        Piece beginPieceC = pieceTable.getCurrent_piece();

                        cursor = endLine(cursor, pieceTable);
                        int endOfLineC = cursor;
                        Piece endPieceC = pieceTable.getCurrent_piece();

                        Piece iteratorC= beginPieceC.getNext();

                        clipBoard += pieceTable.getAdditional_buffer().substring(beginPieceC.getData().getStart()+beginningOfLineC,beginPieceC.getData().getEnd()+1);

                        while (iteratorC != endPieceC && iteratorC != null) {
                            int tempStartC = iteratorC.getData().getStart();
                            int tempEndC = iteratorC.getData().getEnd();
                            clipBoard += pieceTable.getAdditional_buffer().substring(tempStartC,tempEndC+1);

                            iteratorC = iteratorC.getNext();
                        }
                        clipBoard += pieceTable.getAdditional_buffer().substring(endPieceC.getData().getStart(),endPieceC.getData().getEnd()-endOfLineC+1);

                        pieceTable.setCurrent_piece(saveState);
                        cursor = saveCursor;
                        System.err.println(clipBoard);

                        break;
                    case ":p":
                        cursor = insertText(cursor,pieceTable,trieTree,clipBoard);
                        break;
                    case "/":
                        String find = scanner.next();
                        if (trieTree.search(find)) {
                            System.out.println(find + " is in your text!");
                        } else {
                            System.out.println(find + " is not in your text!");
                        }
                        break;
                    default:
                        System.out.println("Command Not Found");


                }
            } else if (vim_mode == Mode.insert) {
                String input = scanner.nextLine();
                input = input.replace("\\n", "\n");

                switch (input) {
                    case "esc":
                        System.out.println("############################ Command MODE ############################");
                        vim_mode = Mode.command;
                        break;
                    case ">":
                        cursor = forwardCursor(cursor, pieceTable);
                        break;
                    case "<":
                        cursor = backCursor(cursor, pieceTable);
                        break;
                    default:
                        cursor = insertText(cursor, pieceTable, trieTree, input);
                        break;
                }


            } else if (vim_mode == Mode.statics) {

                int cursorV = 0;
                int word_counter = 0;
                int line_counter = 0;

                System.out.println(pieceTable.getCurrent_piece().getData().getStart());
                System.out.println(pieceTable.getCurrent_piece().getData().getLength());
                int startV = pieceTable.First().getData().getStart();
                int endV = pieceTable.First().getData().getEnd();
                Piece iterator = pieceTable.First();
                while (iterator != null) {
                    System.err.println(cursorV);
                    int counter = 0;
                    while (counter < iterator.getData().getLength()) {
                        if (pieceTable.getAdditional_buffer().substring(startV, endV + 1).charAt(counter) == ' ' || pieceTable.getAdditional_buffer().substring(startV, endV + 1).charAt(counter) == '\n') {
                            word_counter++;
                        }

                        if (pieceTable.getAdditional_buffer().substring(startV, endV + 1).charAt(counter) == '\n') {
                            line_counter++;

                        }
                        counter++;
                    }
                        iterator = iterator.getNext();
                    if (iterator != null) {
                        startV = iterator.getData().getStart();
                        endV = iterator.getData().getEnd();
                    }
                }
                System.out.println("Number of Words <<<->>> " + (word_counter + 1));
                System.out.println("Number of Lines <<<->>> " + (line_counter + 1));
//                System.out.println(trieTree.findLongest());
                vim_mode = Mode.command;
            }

            Piece temp = pieceTable.First();

            String out = "";
            while (temp != null) {


                String o = pieceTable.getAdditional_buffer().substring(temp.getData().getStart(), temp.getData().getEnd() + 1);

                if (temp.equals(pieceTable.getCurrent_piece())) {
                    if (pieceTable.getCurrent_piece().getData().getLength() != cursor) {
                        System.err.println(pieceTable.getCurrent_piece().getData().getLength());
                        o = o.substring(0, cursor) + "*" + o.substring(cursor);
                    } else {
                        o = o + "*";
                    }
                }
                out = out + o;
                temp = temp.getNext();
            }
            System.out.println(out);

        }
    }

    private static int previousWord(int cursor, PieceTable pieceTable) {
        int startD = pieceTable.getCurrent_piece().getData().getStart();
        int endD = pieceTable.getCurrent_piece().getData().getEnd();
        int cursorD = cursor;

        if (cursorD == pieceTable.getCurrent_piece().getData().getLength() && pieceTable.getCurrent_piece().getNext() == null) {
            cursorD = backCursor(cursorD, pieceTable);
            ;
        } else if (pieceTable.getAdditional_buffer().substring(startD, endD + 1).charAt(cursorD) == ' ' || pieceTable.getAdditional_buffer().substring(startD, endD + 1).charAt(cursorD) == '\n') {
            cursorD = backCursor(cursorD, pieceTable);
            ;
        }
        while (pieceTable.getAdditional_buffer().substring(startD, endD + 1).charAt(cursorD) != ' ' && pieceTable.getAdditional_buffer().substring(startD, endD + 1).charAt(cursorD) != '\n') {

            cursorD = backCursor(cursorD, pieceTable);
            startD = pieceTable.getCurrent_piece().getData().getStart();
            endD = pieceTable.getCurrent_piece().getData().getEnd();
            if (cursorD <= 0 && pieceTable.getCurrent_piece().getPrevious() == null) {
                break;
            }
        }
        if (cursorD == 0 && pieceTable.getCurrent_piece().getPrevious() == null) {
            cursorD--;
        }
        cursor = cursorD + 1;
        return cursor;
    }

    private static int nextWord(int cursor, PieceTable pieceTable) {
        int startC = pieceTable.getCurrent_piece().getData().getStart();
        int endC = pieceTable.getCurrent_piece().getData().getEnd();
        int cursorC = cursor;
        if (cursorC == pieceTable.getCurrent_piece().getData().getLength()) {
            return cursor;
        }
        while (pieceTable.getAdditional_buffer().substring(startC, endC + 1).charAt(cursorC) != ' ' && pieceTable.getAdditional_buffer().substring(startC, endC + 1).charAt(cursorC) != '\n') {
            cursorC = forwardCursor(cursorC, pieceTable);
            startC = pieceTable.getCurrent_piece().getData().getStart();
            endC = pieceTable.getCurrent_piece().getData().getEnd();
            if (cursorC == pieceTable.getCurrent_piece().getData().getLength()) {
                cursorC--;
                break;
            }
        }
        cursor = cursorC + 1;
        return cursor;
    }

    private static int insertText(int cursor, PieceTable pieceTable, TrieTree trieTree, String input) {
        PieceInfo pieceInfo;
        if (pieceTable.getAdditional_buffer() != null) {
            pieceInfo = new PieceInfo(pieceTable.getAdditional_buffer().length(), input.length(), PieceType.Added);
        } else {
            pieceInfo = new PieceInfo(0, input.length(), PieceType.Added);
        }
        Piece piece = new Piece(null, null, pieceInfo);


//                    System.out.println(pieceTable.getCurrent_piece());
        Piece out;

        if (pieceTable.getCurrent_piece() == null) {
            out = pieceTable.Insert(pieceTable.getCurrent_piece(), piece);
            pieceTable.setCurrent_piece(out);
            cursor = input.length();
        } else if (cursor == 0) {
            out = pieceTable.Insert(pieceTable.getCurrent_piece().getPrevious(), piece);
//                            cursor = 0;
//                            pieceTable.setCurrent_piece(out.getNext());
        } else if (cursor > 0 && cursor < pieceTable.getCurrent_piece().getData().getLength()) {
            int startPoint = pieceTable.getCurrent_piece().getData().getStart();
            int endPoint = pieceTable.getCurrent_piece().getData().getEnd();
            PieceInfo preInfo = new PieceInfo(startPoint, cursor, PieceType.Added);
            PieceInfo nextInfo = new PieceInfo(cursor + startPoint, pieceTable.getCurrent_piece().getData().getLength() - cursor, PieceType.Added);

            Piece split = new Piece(pieceTable.getCurrent_piece(), pieceTable.getCurrent_piece().getNext(), nextInfo);
            pieceTable.Insert(pieceTable.getCurrent_piece(), split);

            pieceTable.getCurrent_piece().setData(preInfo);


            out = pieceTable.Insert(pieceTable.getCurrent_piece(), piece);

            pieceTable.setCurrent_piece(out.getNext());
            cursor = 0;

        } else if (cursor == pieceTable.getCurrent_piece().getData().getLength()) {
            out = pieceTable.Insert(pieceTable.getCurrent_piece(), piece);
            pieceTable.setCurrent_piece(out);
            cursor = input.length();
        }

        trieTree.root = new TrieNode();
        String[] arrayLine = input.split("\n");
        for (String i : arrayLine) {
            String[] array = i.split(" ");
            for (String j : array) {
                trieTree.insert(j);
            }
        }

        String pre = "";
        if (pieceTable.getAdditional_buffer() != null) {
            pre = pieceTable.getAdditional_buffer();
        }
        pieceTable.setAdditional_buffer(pre + input);
        return cursor;
    }

    private static int endLine(int cursor, PieceTable pieceTable) {
        int startB = pieceTable.getCurrent_piece().getData().getStart();
        int endB = pieceTable.getCurrent_piece().getData().getEnd();
        int cursorB = cursor;
        if (cursorB == pieceTable.getCurrent_piece().getData().getLength()) {
            return cursor;
        }
        while (pieceTable.getAdditional_buffer().substring(startB, endB + 1).charAt(cursorB) != '\n') {
            cursorB = forwardCursor(cursorB, pieceTable);
            startB = pieceTable.getCurrent_piece().getData().getStart();
            endB = pieceTable.getCurrent_piece().getData().getEnd();
            if (cursorB == pieceTable.getCurrent_piece().getData().getLength()) {
                break;
            }
        }
        cursor = cursorB;
        return cursor;
    }

    private static int beginLine(int cursor, PieceTable pieceTable) {

        int cursorA = cursor;
        if (cursorA == 0 && pieceTable.getCurrent_piece().getPrevious() == null || (cursor == -1)) {
            System.out.println("gav");
            pieceTable.setCurrent_piece(pieceTable.First());
            return 0;
        }


        if (cursorA == pieceTable.getCurrent_piece().getData().getLength()) {
            cursorA = backCursor(cursorA, pieceTable);

        }


        cursorA = backCursor(cursorA, pieceTable);
        if (cursorA == -1) {
            cursorA = forwardCursor(cursorA, pieceTable);
        }

        System.err.println("Cursor: " + cursorA);
        System.err.println("Length: " + pieceTable.getCurrent_piece().getData().getLength());

        int startA = pieceTable.getCurrent_piece().getData().getStart();
        int endA = pieceTable.getCurrent_piece().getData().getEnd();


        while (pieceTable.getAdditional_buffer().substring(startA, endA + 1).charAt(cursorA) != '\n') {
            System.err.println("Raftam");
            cursorA = backCursor(cursorA, pieceTable);
            System.err.println("number of curosor " + cursorA);
            if ((cursorA == 0 && pieceTable.getCurrent_piece().getPrevious() == null) || cursorA == -1) {
                pieceTable.setCurrent_piece(pieceTable.First());
                return 0;
            }
            startA = pieceTable.getCurrent_piece().getData().getStart();
            endA = pieceTable.getCurrent_piece().getData().getEnd();

        }
//        System.out.println(cursorA);

        cursor = cursorA + 1;
        return cursor;
    }

    private static int forwardCursor(int cursor, PieceTable pieceTable) {
        cursor++;
        if (cursor >= pieceTable.getCurrent_piece().getData().getLength()) {
            if (pieceTable.getCurrent_piece().getNext() != null) {
                pieceTable.setCurrent_piece(pieceTable.getCurrent_piece().getNext());
                cursor = 0;
            } else if (cursor != pieceTable.getCurrent_piece().getData().getLength()) {
                cursor--;
            }
        }
        return cursor;
    }

    private static int backCursor(int cursor, PieceTable pieceTable) {
        cursor--;
        if (cursor < 0) {
            if (pieceTable.getCurrent_piece().getPrevious() != null) {
                pieceTable.setCurrent_piece(pieceTable.getCurrent_piece().getPrevious());
                cursor = pieceTable.getCurrent_piece().getData().getLength() - 1;
            } else {
                cursor++;
            }
        }
        return cursor;
    }

}

