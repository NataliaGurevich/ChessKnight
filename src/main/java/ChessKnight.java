public class ChessKnight {
    public static void main(String[] args) {
        System.out.println(countMoves(3, 3, 1, 1, 2, 2));
    }

    public static int countMoves(
            int width, int height,
            int startCol, int startRow,
            int endCol, int endRow) {
        //TODO: code here
        System.out.println("width=" + width + " height=" + height + " startCol=" + startCol + " startRow=" + startRow + " endCol=" + endCol + " endRow=" + endRow);

        if (startRow > height || endRow > height || startCol > width || endCol > width) return -2;
        if (startCol == endCol && startRow == endRow) return 0;

        //определяем нач.позицию
        if (startCol > endCol) {
            int a = startCol;
            startCol = endCol;
            endCol = a;
        }
        if (startRow > endRow) {
            int a = startRow;
            startRow = endRow;
            endRow = a;
        }

        //обнуляем конечную точку
        int nextStepCol = startCol - endCol;
        int nextStepRow = startRow - endRow;
        int coordYBottom = -endRow + 1;
        int coordXLeft = -endCol + 1;
        int coordYTop = height - endRow;
        int coordXRight = width - endCol;
        int count = 0;
        while (nextStepCol != 0 || nextStepRow != 0) {

           //проверка критических точек и границ
            if (nextStepCol == -3 && nextStepRow == -4 || nextStepCol == -4 && nextStepRow == -3) {
                return count + 3;
            }
            if (nextStepCol == -3 && nextStepRow == -2 || nextStepCol == -2 && nextStepRow == -3) {
                return count + 3;
            }
            if (nextStepCol == -3 && nextStepRow == -1) { //board
                if (height > 1) {
                    return count + 2;
                } else return -1;
            }
            if (nextStepCol == -1 && nextStepRow == -3) { //board
                if (width > 1) {
                    return count + 2;
                } else return -1;
            }
            if (nextStepCol == -3 && nextStepRow == 0) { //board
                if (coordYBottom <= -1 && coordYTop >= 1) {
                    return count + 3;
                }
                if (height >= 2 && coordXRight >= 1) {
                    return count + 3;
                }
                if (width >= 3 && height >= 2) {
                    return count + 5;
                } else return -1;
            }
            if (nextStepCol == -2 && nextStepRow == -2) {
                return count + 4;
            }
            if (nextStepCol == -2 && nextStepRow == 0) { //board
                if (coordYBottom <= -2 || coordYTop >= 2) {
                    return count + 2;
                }
                if (coordYBottom == -1 && coordYTop == 1) {
                    return count + 4;
                } else return -1;
            }
            if (nextStepCol == 0 && nextStepRow == -2) { //board
                if (coordXLeft <= -2 || coordXRight >= 2) {
                    return count + 2;
                }
                if (coordXLeft == -1 && coordXRight == 1) {
                    return count + 4;
                } else return -1;
            }
            if (nextStepCol == 0 && nextStepRow == -3) { //board
                if (coordXLeft <= -1 && coordXRight >= 1) {
                    return count + 3;
                }
                if (width >= 2 && coordYTop >= 1) {
                    return count + 3;
                }
                if (height >= 3 && width >= 2) {
                    return count + 5;
                } else return -1;
            }
            if (nextStepCol == -1 && nextStepRow == -1) {
                if (coordXLeft <= -2 && coordYTop >= 1) {
                    return count + 2;
                }
                if (coordXRight >= 1 && coordYBottom <= -2) {
                    return count + 2;
                } else return -1;
            }
            if (nextStepCol == -1 && nextStepRow == 0) {
                if (coordXRight >= 2 && ((coordYBottom <= -2) || (coordYTop >= 2))) {
                    return count + 3;
                }
                if (coordXLeft <= -3 && ((coordYBottom <= -2) || (coordYTop >= 2))) {
                    return count + 3;
                } else return -1;
            }
            if (nextStepCol == 0 && nextStepRow == -1) {
                if (coordYTop >= 2 && ((coordXLeft <= -2) || (coordXRight >= 2))) {
                    return count + 3;
                }
                if (coordYBottom <= -3 && ((coordXLeft <= -2) || (coordXRight >= 2))) {
                    return count + 3;
                } else return -1;
            }
            //---основной-перебор-ходов-----------------------
            if ((-nextStepCol) >= (-nextStepRow)) {
                count++;
                nextStepCol += 2;
                if (Math.abs(nextStepRow + 1) < Math.abs(nextStepRow - 1)) {
                    nextStepRow++;
                } else {
                    nextStepRow--;
                }
            } else {
                count++;
                nextStepRow += 2;
                if (Math.abs(nextStepCol + 1) < Math.abs(nextStepCol - 1)) {
                    nextStepCol++;
                } else {
                    nextStepCol--;
                }
            }
        }
        return count;
    }
}
