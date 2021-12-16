package za.co.ruanbotes.day.sixteen;

import za.co.ruanbotes.utils.FileReader;
import za.co.ruanbotes.utils.Printer;

import java.util.HashMap;
import java.util.Map;

public class DaySixteen {

    Map<String, String> hexBinTable = new HashMap<>();

    public void run() {
        populateHexBinTable();
        //puzzleOne();
        puzzleTwo();
    }

    private void puzzleOne() {
        String[] lines = FileReader.readFile("resources/day/sixteen/input.txt");
        String binary = getBinaryString(lines[0].split("(?!^)"));
        PacketParser packetParser = new PacketParser();
        packetParser.processPackets(binary, 0);
        Printer.print(1, packetParser.calculateVersionSum());
    }

    private String getBinaryString(String[] hex) {
        for (int i = 0; i < hex.length; i++) {
            String binary =  hexToBin(hex[i]);
            if (binary.length() != 4) {
                System.out.println(hex[i]);
            }
            hex[i] = binary;
        }

        return String.join("", hex);
    }

    private void puzzleTwo() {
        String[] lines = FileReader.readFile("resources/day/sixteen/input.txt");
        System.out.println(lines[0].length());
        String binary = getBinaryString(lines[0].split("(?!^)"));
        System.out.println(binary.length());
        PacketParser packetParser = new PacketParser();
        packetParser.processPackets(binary, 0);
        Printer.print(2, packetParser.calculatePacketNew());
    }

    private String hexToBin(String hex) {
        return this.hexBinTable.get(hex);
    }

    private void populateHexBinTable() {
        this.hexBinTable.put("0", "0000");
        this.hexBinTable.put("1", "0001");
        this.hexBinTable.put("2", "0010");
        this.hexBinTable.put("3", "0011");
        this.hexBinTable.put("4", "0100");
        this.hexBinTable.put("5", "0101");
        this.hexBinTable.put("6", "0110");
        this.hexBinTable.put("7", "0111");
        this.hexBinTable.put("8", "1000");
        this.hexBinTable.put("9", "1001");
        this.hexBinTable.put("A", "1010");
        this.hexBinTable.put("B", "1011");
        this.hexBinTable.put("C", "1100");
        this.hexBinTable.put("D", "1101");
        this.hexBinTable.put("E", "1110");
        this.hexBinTable.put("F", "1111");
    }
}
