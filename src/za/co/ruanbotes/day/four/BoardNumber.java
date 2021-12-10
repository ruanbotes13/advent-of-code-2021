package za.co.ruanbotes.day.four;

public class BoardNumber {
    public Integer number;
    public boolean marked;

    public BoardNumber(int number) {
        this.number = number;
        this.marked = false;
    }

    public void markNumber(Integer number) {
        if (this.number == number) {
            this.marked = true;
        }
    }
}
