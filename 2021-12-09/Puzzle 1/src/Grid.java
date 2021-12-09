import java.util.List;

public class Grid {
    GridBlock[][] grid;
    List<GridBlock> lowPoints;
    List<Basin> basins;

    public Grid(GridBlock[][] grid) {
        this.grid = grid;
    }

    public void setVisited(int row, int column) {
        grid[row][column].setVisited();
    }
}
