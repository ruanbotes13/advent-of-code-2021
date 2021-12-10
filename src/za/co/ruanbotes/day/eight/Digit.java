package za.co.ruanbotes.day.eight;

public class Digit {
    public int number;

    public boolean a = false;
    public boolean b = false;
    public boolean c = false;
    public boolean d = false;
    public boolean e = false;
    public boolean f = false;
    public boolean g = false;

    public int numberTrueDigits = 0;

    public void setPredefined(String parts) {

        String[] partsArray = parts.split("(?!^)");

        if (partsArray.length == 2) {
            number = 1;
            numberTrueDigits = 2;
        }
        else if (partsArray.length == 4) {
            number = 4;
            numberTrueDigits = 4;
        }
        else if (partsArray.length == 3) {
            number = 7;
            numberTrueDigits = 3;
        }
        else if (partsArray.length == 7) {
            number = 8;
            numberTrueDigits = 7;
        }

        for (int i = 0; i < partsArray.length; i++) {
            if (partsArray[i].equals("a")) {
                this.a = true;
            } else if (partsArray[i].equals("b")) {
                this.b = true;
            } else if (partsArray[i].equals("c")) {
                this.c = true;
            } else if (partsArray[i].equals("d")) {
                this.d = true;
            } else if (partsArray[i].equals("e")) {
                this.e = true;
            } else if (partsArray[i].equals("f")) {
                this.f = true;
            } else if (partsArray[i].equals("g")) {
                this.g = true;
            }
        }
    }

    public void setDigit(int number, String letters) {
        this.number = number;

        String[] digitParts = letters.split("(?!^)");

        for (int i = 0; i < digitParts.length; i++) {
            if (digitParts[i].equals("a")) {
                this.a = true;
                this.numberTrueDigits++;
            }
            else if (digitParts[i].equals("b")) {
                this.b = true;
                this.numberTrueDigits++;
            }
            else if (digitParts[i].equals("c")) {
                this.c = true;
                this.numberTrueDigits++;
            }
            else if (digitParts[i].equals("d")) {
                this.d = true;
                this.numberTrueDigits++;
            }
            else if (digitParts[i].equals("e")) {
                this.e = true;
                this.numberTrueDigits++;
            }
            else if (digitParts[i].equals("f")) {
                this.f = true;
                this.numberTrueDigits++;
            }
            else if (digitParts[i].equals("g")) {
                this.g = true;
                this.numberTrueDigits++;
            }
        }
    }

    public int getCorresponding(String parts) {
        String[] digitParts = parts.split("(?!^)");
        int corresponding = 0;

        for (int i = 0; i < digitParts.length; i++) {
            if (digitParts[i].equals("a") && this.a) {
                corresponding++;
            }
            else if (digitParts[i].equals("b") && this.b) {
                corresponding++;
            }
            else if (digitParts[i].equals("c") && this.c == true) {
                corresponding++;
            }
            else if (digitParts[i].equals("d") && this.d == true) {
                corresponding++;
            }
            else if (digitParts[i].equals("e") && this.e == true) {
                corresponding++;
            }
            else if (digitParts[i].equals("f") && this.f == true) {
                corresponding++;
            }
            else if (digitParts[i].equals("g") && this.g == true) {
                corresponding++;
            }
        }

        return corresponding;
    }

    public boolean isDigit(String digits) {
        if (digits.length() != this.numberTrueDigits) {
            return false;
        }

        return getCorresponding(digits) == this.numberTrueDigits;
    }
}
