package za.co.ruanbotes.day.eleven;

public class OctopiGrid {
    public Octopus[][] octopi;
    long flashes;
    int stepAllFlashed;

    public OctopiGrid(Octopus[][] octopi) {
        this.octopi = octopi;
        flashes = 0;
        this.stepAllFlashed = 0;
    }

    public void simulateFlashes(int rounds) {
        for (int i = 0; i < rounds; i++) {
            increaseEnergy();
            for (int row = 0; row < 10; row++) {
                for (int column = 0; column < 10; column++) {
                    simulateFlash(row, column);
                }
            }
            resetFlashed();
            print();
        }
    }

    public void increaseEnergy() {
        for (int row = 0; row < 10; row++) {
            for (int column = 0; column < 10; column++) {
                octopi[row][column].increaseEnergy();
            }
        }
    }

    public void resetFlashed() {
        for (int row = 0; row < 10; row++){
            for (int column = 0; column < 10; column++) {
                octopi[row][column].flashed = false;
            }
        }
    }

    public void simulateFlash(int row, int column) {
        if (octopi[row][column].energy > 9 && !octopi[row][column].flashed) {
            octopi[row][column].flashed = true;
            octopi[row][column].energy = 0;
            flashes++;
//            increaseNeighbours(row, column);


            if (row - 1 >= 0) {
                if (column - 1 >= 0) {
                    octopi[row-1][column-1].increaseEnergy();
                    simulateFlash(row - 1, column - 1); // topLeft
                }

                if (column + 1 < 10) {
                    octopi[row-1][column+1].increaseEnergy();
                    simulateFlash(row - 1, column + 1); // topRight
                }

                octopi[row-1][column].increaseEnergy();
                simulateFlash(row - 1, column); //top
            }

            if (row + 1 < 10) {
                if (column - 1 >= 0) {
                    octopi[row+1][column-1].increaseEnergy();
                    simulateFlash(row + 1, column - 1); // bottomLeft
                }

                if (column + 1 < 10) {
                    octopi[row+1][column+1].increaseEnergy();
                    simulateFlash(row + 1, column + 1); // bottomRight
                }

                octopi[row+1][column].increaseEnergy();
                simulateFlash(row + 1, column); //bottom
            }

            if (column - 1 >= 0) {
                octopi[row][column-1].increaseEnergy();
                simulateFlash(row, column - 1); // left
            }

            if (column + 1 < 10) {
                octopi[row][column+1].increaseEnergy();
                simulateFlash(row, column + 1); // right
            }
        }
    }

    public void increaseNeighbours(int row, int column) {
        if (row - 1 > 0) {
            if (column - 1 > 0) {
                octopi[row-1][column-1].increaseEnergy();
            }

            if (column + 1 < 10) {
                octopi[row-1][column+1].increaseEnergy();
            }

            octopi[row-1][column].increaseEnergy();
        }

        if (row + 1 < 10) {
            if (column - 1 > 0) {
                octopi[row+1][column-1].increaseEnergy();
            }

            if (column + 1 < 10) {
                octopi[row+1][column+1].increaseEnergy();
            }

            octopi[row+1][column].increaseEnergy();
        }

        if (column - 1 > 0) {
            octopi[row][column-1].increaseEnergy();
        }

        if (column + 1 < 10) {
            octopi[row][column+1].increaseEnergy();
        }
    }

    public void print() {
        for (int row = 0; row < 10; row++){
            for (int column = 0; column < 10; column++) {
                System.out.print(this.octopi[row][column].energy);
            }
            System.out.print("\n");
        }

        System.out.print("\n");
    }

    public void findSimultanious() {
        boolean allFlashed = false;
        int step = 1;
        while(!allFlashed){
//        for (int i = 0; i < rounds; i++) {
            increaseEnergy();
            for (int row = 0; row < 10; row++) {
                for (int column = 0; column < 10; column++) {
                    simulateFlash(row, column);
                }
            }
            allFlashed = allFlashed(step);
            resetFlashed();
            print();
            step++;
        }
    }

    public boolean allFlashed(int step) {
        for (int row = 0; row < 10; row++){
            for (int column = 0; column < 10; column++) {
                if (!octopi[row][column].flashed) {
                    return false;
                }
            }
        }

        this.stepAllFlashed = step;

        return true;
    }
}
