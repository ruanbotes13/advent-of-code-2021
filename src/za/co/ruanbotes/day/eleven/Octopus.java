package za.co.ruanbotes.day.eleven;

public class Octopus {
    public int energy;
    public boolean flashed;

    public Octopus(int energy) {
        this.energy = energy;
        this.flashed = false;
    }

    public void increaseEnergy() {
        if (!flashed) {
            this.energy++;
        }
    }
}
