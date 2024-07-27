public class GameOfLife {
    public static void main(String[] args) {
        int numCells = Integer.parseInt(args[0]);
        int numCopies = Integer.parseInt(args[1]);
        int cellsize = 5;
        if (args.length != 2 || numCells <= 0 || numCopies <= 0) {
            System.err.println("Error: The input parameters must be strictly positive!");
            System.exit(1);
        }
        else {
            int[][] grid = new int[numCells][numCells];
            setupStdDraw(numCells, cellsize);
            for (int i = 0; i < numCopies; i++) {
                pulsar((int) (Math.random() * numCells), (int) (Math.random() * numCells), grid);
                diamond_glider((int) (Math.random() * numCells), (int) (Math.random() * numCells), grid);
                colliding_glider((int) (Math.random() * numCells), (int) (Math.random() * numCells), grid);
            }
            while (true) {
                StdDraw.clear();
                StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
                double half_length = (double) (numCells * (cellsize+1)) / 2;
                StdDraw.filledSquare(half_length, half_length, half_length);
                StdDraw.setPenColor(StdDraw.BOOK_BLUE);
                draw(grid, cellsize);
                StdDraw.show();
                StdDraw.pause(100);
                grid = applyGameofLife(numCells, grid);
            }
        }
    }
    public static void setupStdDraw(int numCells, int cellSize) {
        StdDraw.enableDoubleBuffering();
        StdDraw.setCanvasSize(numCells * cellSize, numCells * cellSize);
        StdDraw.setScale(0, (numCells + 1) * cellSize);
        //StdDraw.setPenColor(StdDraw.BOOK_BLUE);
    }
    public static void draw(int[][] grid, int cellSize) {
        double halfLength = (double) cellSize / 2;
        // Iterate over rows
        for (int r = 0; r < grid.length; r++) {
            // Iterate over "columns", i.e., cells
            for (int c = 0; c < grid[r].length; c++) {
                // Only draw something if the cell has state 1.
                if (grid[r][c] == 1) {
                    StdDraw.filledSquare(c * cellSize, r * cellSize, halfLength);
                }
            }
        }
    }
    public static int[][] pulsar(int r, int c, int[][] grid){
        //Middle row
        for(int i = 0; i <= 6; i++){
            grid[r][(c + i) % grid[0].length] = 1;
            grid[r][(grid[0].length + c - i) % grid[0].length] = 1;
        }

        // 2nd and 4th row
        for(int i = 0; i <= 4; i++){
            grid[(r + 2) % grid[0].length][(c + i) % grid[0].length] = 1;
            grid[(r + 2) % grid[0].length][(grid[0].length + c - i) % grid[0].length] = 1;
            grid[(grid[0].length + r - 2) % grid[0].length][(c + i) % grid[0].length] = 1;
            grid[(grid[0].length + r - 2) % grid[0].length][(grid[0].length + c - i) % grid[0].length] = 1;
        }

        //1st and last row
        for(int i = 0; i <= 2; i++){
            grid[(r + 4) % grid[0].length][(c + i) % grid[0].length] = 1;
            grid[(r + 4) % grid[0].length][(grid[0].length + c - i) % grid[0].length] = 1;
            grid[(grid[0].length + r - 4) % grid[0].length][(c + i) % grid[0].length] = 1;
            grid[(grid[0].length + r - 4) % grid[0].length][(grid[0].length + c - i) % grid[0].length] = 1;
        }
        return grid;
    }
    public static int[][] diamond_glider(int r, int c, int[][] grid){
        //Middle row
        for(int i = 0; i <= 5; i++){
            grid[r][(c + i) % grid[0].length] = 1;
            grid[r][(grid[0].length + c - i) % grid[0].length] = 1;
        }
        grid[r][(grid[0].length + c - 6) % grid[0].length] = 1;

        // 2nd and 4th row
        for(int i = 0; i <= 3; i++){
            grid[(r + 2) % grid[0].length][(c + i) % grid[0].length] = 1;
            grid[(r + 2) % grid[0].length][(grid[0].length + c - i) % grid[0].length] = 1;
            grid[(grid[0].length + r - 2) % grid[0].length][(c + i) % grid[0].length] = 1;
            grid[(grid[0].length + r - 2) % grid[0].length][(grid[0].length + c - i) % grid[0].length] = 1;
        }
        grid[(r + 2) % grid[0].length][(grid[0].length + c - 4) % grid[0].length] = 1;
        grid[(grid[0].length + r - 2) % grid[0].length][(grid[0].length + c - 4) % grid[0].length] = 1;

        //1st and last row
        for(int i = 0; i <= 1; i++){
            grid[(r + 4) % grid[0].length][(c + i) % grid[0].length] = 1;
            grid[(r + 4) % grid[0].length][(grid[0].length + c - i) % grid[0].length] = 1;
            grid[(grid[0].length + r - 4) % grid[0].length][(c + i) % grid[0].length] = 1;
            grid[(grid[0].length + r - 4) % grid[0].length][(grid[0].length + c - i) % grid[0].length] = 1;
        }
        grid[(r + 4) % grid[0].length][(grid[0].length + c - 2) % grid[0].length] = 1;
        grid[(grid[0].length + r - 4) % grid[0].length][(grid[0].length + c - 2) % grid[0].length] = 1;
        return grid;
    }
    public static int[][] colliding_glider(int r, int c, int[][] grid){
        //Left side
        grid[r][c] = 1;
        grid[(r + 1) % grid[0].length][c] = 1;
        grid[(r + 2) % grid[0].length][c] = 1;
        grid[r][(grid[0].length + c - 1) % grid[0].length] = 1;
        grid[(r + 1) % grid[0].length][(grid[0].length + c - 2) % grid[0].length] = 1;

        //Right side
        grid[(grid[0].length + r - 1) % grid[0].length][(c + 9) % grid[0].length] = 1;
        grid[(grid[0].length + r - 2) % grid[0].length][(c + 7) % grid[0].length] = 1;
        grid[(grid[0].length + r - 2) % grid[0].length][(c + 8) % grid[0].length] = 1;
        grid[(grid[0].length + r - 3) % grid[0].length][(c + 8) % grid[0].length] = 1;
        grid[(grid[0].length + r - 3) % grid[0].length][(c + 9) % grid[0].length] = 1;
        return grid;
    }
    public static int[][] applyGameofLife(int numCells, int[][] grid) {
        int [][]nextGen = new int[numCells][numCells];
        for (int r = 0; r < numCells; r++) {
            for (int c = 0; c < numCells; c++) {
                int count = grid[(grid.length + r - 1) % grid.length][(grid.length + c - 1) % grid.length]
                        + grid[(grid.length + r - 1) % grid.length][c]
                        + grid[(grid.length + r - 1) % grid.length][(c + 1) % grid.length]
                        + grid[r][(grid.length + c - 1) % grid.length]
                        + grid[r][(c + 1) % grid.length]
                        + grid[(r + 1) % grid.length][(grid.length + c - 1) % grid.length]
                        + grid[(r + 1) % grid.length][c]
                        + grid[(r + 1) % grid.length][(c + 1) % grid.length];

                if (grid[r][c] == 1) {
                    if (count == 2 || count == 3){
                        nextGen[r][c] = 1;
                    } else {
                        nextGen[r][c] = 0;
                    }
                } else if (grid[r][c] == 0){
                    if (count == 3){
                        nextGen[r][c] = 1;
                    }
                }
            }
        }
        return nextGen;
    }
}
