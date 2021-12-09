public class GridBlock {
    public int row;
    public int column;
    public int value;
    public boolean visited;

    public GridBlock(int row, int column, int value) {
        this.row = row;
        this.column = column;
        this.value = value;
        this.visited = false;
    }

    public void setVisited() {
        this.visited = true;
    }
}
