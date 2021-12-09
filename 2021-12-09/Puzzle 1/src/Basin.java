import java.util.ArrayList;
import java.util.List;

public class Basin implements Comparable {
    public List<GridBlock> gridBlocks;

    public Basin() {
        this.gridBlocks = new ArrayList<>();
    }

    @Override
    public int compareTo(Object o) {
        Basin basin = (Basin) o;
        int size = basin.gridBlocks.size();
        return size - this.gridBlocks.size();
    }
}
