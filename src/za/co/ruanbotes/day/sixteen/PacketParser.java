package za.co.ruanbotes.day.sixteen;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class PacketParser {

    Stack<Packet> packets = new Stack<>();
    Stack<Packet> literals = new Stack<>();
    Packet start;

    public int processPacket(String binary, int start) {
        if (binary.length() - start < 11) {
            return start+1;
        }

        int version = Integer.parseInt(binary.substring(start, start + 3), 2);
        int packetType = Integer.parseInt(binary.substring(start + 3, start + 6), 2);

        int newStart = start + 6;

        if (packetType == 4) {
            newStart = getLiteral(binary, newStart, version);
            if (binary.length() - newStart > 12) {
                newStart = processPacket(binary, newStart);
            }
        }
        else {
            char lengthTypeId = binary.charAt(newStart);
            newStart = newStart + 1;

            if (lengthTypeId == '0') { // 15 bits
                int length = Integer.parseInt(binary.substring(newStart, newStart + 15), 2);
                newStart = newStart + 15;
                int originalStart = newStart;
                packets.add(
                    new Packet(PacketType.OPERATOR, 0, version, packetType, null)
                );
                while(originalStart == newStart || newStart - originalStart > 11) {
                    newStart = processPacket(binary, newStart);
                }
            } else { // 11 bits
                int numberOfPackets = Integer.parseInt(binary.substring(newStart, newStart + 11), 2);
                newStart = newStart + 11;
                int numberPacketsProcessed = 0;
                packets.add(
                    new Packet(PacketType.OPERATOR, 0, version, packetType, null)
                );
                while(numberPacketsProcessed < numberOfPackets) {
                    newStart = processPacket(binary, newStart);
                    numberPacketsProcessed++;
                }
            }
        }

        return newStart;
    }

    public void processPackets(String binary, int start) {
        int left = start;

        while(binary.length()-left > 11) {
            left = processSinglePacket(binary, left, this.start);
        }
    }

    public int processSinglePacket(String binary, int start, Packet parent) {
        System.out.println(start);
        int version = Integer.parseInt(binary.substring(start,start+3), 2);
        int packetType = Integer.parseInt(binary.substring(start+3,start+6), 2);

        int left = start + 6;

        if (packetType == 4) {
            left = getLiteralNew(binary, version, left, parent);
//            if (binary.length() - left > 11) {
//                processSinglePacket(binary, left, parent);
//            }
//            else {
//                return left;
//            }
        }
        else {
            char lengthTypeId = binary.charAt(left);
            left++;

            if (lengthTypeId == '0') { // 15 bits
                String sizeString = binary.substring(left, left + 15);
                int length = Integer.parseInt(sizeString, 2);
                left += 15;
                Packet packet = new Packet(PacketType.OPERATOR, 0, version, packetType, parent);
                if (parent == null) {
                    this.start = packet;
                }
                else {
                    parent.children.add(packet);
                }
//                packets.add(
//                    new Packet(PacketType.OPERATOR, 0, version, packetType)
//                );
                left = processSinglePacket(binary, left, packet);
            } else { // 11 bits
                int numberOfPackets = Integer.parseInt(binary.substring(left, left+11), 2);
                left += 11;
                int numberPacketsProcessed = 0;

                Packet packet =  new Packet(PacketType.OPERATOR, 0, version, packetType, parent);

                if (parent == null) {
                    this.start = packet;
                }
                else {
                    parent.children.add(packet);
                }
//                packets.add(
//                    new Packet(PacketType.OPERATOR, 0, version, packetType)
//                );
                while(numberPacketsProcessed < numberOfPackets) {
                    left = processSinglePacket(binary, left, packet);
                    numberPacketsProcessed++;
                }
            }
        }

        return left;
    }

    private int getLiteral(String literalString, int start, int version) {
        int nextChar = start;
        boolean lastGroup = false;
        String literalBinary = "";

        while(!lastGroup) {
            String literal = literalString.substring(nextChar, nextChar + 5);
            literalBinary = literalBinary + literal.substring(1, 5);

            if (literal.charAt(0) == '0') {
                lastGroup = true;
            }

            nextChar += 5;
        }

        packets.push(
            new Packet(
                PacketType.LITERAL,
                Long.parseLong(literalBinary, 2),
                version,
                4,
                null
            )
        );

        return nextChar;
    }

    private int getLiteralNew(String literalString, int version, int start, Packet parent) {
        int nextChar = start;
        boolean lastGroup = false;
        String literalBinary = "";

        while(!lastGroup) {
            String literal = literalString.substring(nextChar, nextChar + 5);
            literalBinary = literalBinary + literal.substring(1, 5);

            if (literal.charAt(0) == '0') {
                lastGroup = true;
            }

            nextChar += 5;
        }

        if (parent == null) {
            parent = new Packet(
                PacketType.LITERAL,
                Long.parseLong(literalBinary, 2),
                version,
                4,
                parent
            );
        }
        else {
            parent.children.add(
                new Packet(
                    PacketType.LITERAL,
                    Long.parseLong(literalBinary, 2),
                    version,
                    4,
                    parent
                )
            );
        }

//        packets.push(
//            new Packet(
//                PacketType.LITERAL,
//                Long.parseLong(literalBinary, 2),
//                version,
//                4
//            )
//        );

        return nextChar;
    }

    public int calculateVersionSum() {
        int sum = 0;
        while (!this.packets.isEmpty()) {
            Packet packet = this.packets.pop();
            sum = sum + packet.versionNumber;
        }

        return sum;
    }

    public long calculateExpression() {
        while(this.packets.size() > 0) {
            calculatePacket();
        }

        return this.literals.pop().literal;
    }

    public void calculatePacket() {
        Packet operator = this.packets.pop();
        List<Packet> literals = new ArrayList<>();

        while(operator.type.equals(PacketType.LITERAL) && !this.packets.isEmpty()) {
            literals.add(operator);
            operator = this.packets.pop();
        }

        if (literals.size() == 0) {
            while(!this.literals.isEmpty()) {
                literals.add(this.literals.pop());
            }
        }

        if (operator.packetTypeId == 0) {
            long sum = 0;

            for (int i = 0; i < literals.size(); i++) {
                sum = sum + literals.get(i).literal;
            }

            this.literals.push(
                new Packet(
                    PacketType.LITERAL,
                    sum,
                    0,
                    4,
                    null
                )
            );
        }
        else if (operator.packetTypeId == 1) {
            long value = 1;

            for (int i = 0; i < literals.size(); i++) {
                value = value * literals.get(i).literal;
            }

            this.literals.push(
                new Packet(
                    PacketType.LITERAL,
                    value,
                    0,
                    4,
                    null
                )
            );
        }
        else if (operator.packetTypeId == 2) {
            long min = Long.MAX_VALUE;

            for (int i = 0; i < literals.size(); i++) {
                if (literals.get(i).literal < min) {
                    min = literals.get(i).literal;
                }
            }

            this.literals.push(
                new Packet(
                    PacketType.LITERAL,
                    min,
                    0,
                    4,
                    null
                )
            );
        }
        else if (operator.packetTypeId == 3) {
            long max = 0;

            for (int i = 0; i < literals.size(); i++) {
                if (literals.get(i).literal > max) {
                    max = literals.get(i).literal;
                }
            }

            this.literals.push(
                new Packet(
                    PacketType.LITERAL,
                    max,
                    0,
                    4,
                    null
                )
            );
        }
        else if (operator.packetTypeId == 5) {
            long value = 0;

            if (literals.get(0).literal < literals.get(1).literal) {
                value = 1;
            }

            this.literals.push(
                new Packet(
                    PacketType.LITERAL,
                    value,
                    0,
                    4,
                    null
                )
            );
        }
        else if (operator.packetTypeId == 6) {
            long value = 0;

            if (literals.get(0).literal > literals.get(1).literal) {
                value = 1;
            }

            this.literals.push(
                new Packet(
                    PacketType.LITERAL,
                    value,
                    0,
                    4,
                    null
                )
            );
        }
        else if (operator.packetTypeId == 7) {
            long value = 0;

            if (literals.get(0).literal == literals.get(1).literal) {
                value = 1;
            }

            this.literals.push(
                new Packet(
                    PacketType.LITERAL,
                    value,
                    0,
                    4,
                    null
                )
            );
        }
    }

    public long calculatePacketNew() {
        Stack<Packet> stack = new Stack<>();

        stack.push(this.start);

        while(!stack.empty()) {
            Packet s = stack.pop();

            List<Packet> childrenOperators = s.children.stream()
                .filter(packet -> packet.visited == false && packet.type == PacketType.OPERATOR)
                .collect(Collectors.toList());

            if (childrenOperators.size() > 0) {
                stack.push(s);
                for (Packet childrenOperator : childrenOperators) {
                    stack.push(childrenOperator);
                }
            }
            else {
                List<Packet> children = s.children.stream()
                    .collect(Collectors.toList());

                if (children.size() > 0) {
                    doArithmatic(s);
                    s.visited = true;
                }
            }


//            if (childrenOperators.size() == 0) {
//                doArithmatic(s);
//                s.visited = true;
//            } else {
//                stack.push(s);
//                for (Packet childrenOperator : childrenOperators) {
//                    stack.push(childrenOperator);
//                }
//            }
        }

        return this.start.literal;
    }

    public void doArithmatic(Packet node) {
        if (node.packetTypeId == 0) {
            long sum = 0;

            for (int i = 0; i < node.children.size(); i++) {
                sum = sum + node.children.get(i).literal;
                node.children.get(i).visited = true;
            }

            node.literal = sum;
        }
        else if (node.packetTypeId == 1) {
            long value = 1;

            for (int i = 0; i < node.children.size(); i++) {
                value = value * node.children.get(i).literal;
                node.children.get(i).visited = true;
            }

            node.literal = value;
        }
        else if (node.packetTypeId == 2) {
            long min = Long.MAX_VALUE;

            for (int i = 0; i < node.children.size(); i++) {
                if (node.children.get(i).literal < min) {
                    min = node.children.get(i).literal;
                }

                node.children.get(i).visited = true;
            }

            node.literal = min;
        }
        else if (node.packetTypeId == 3) {
            long max = 0;

            for (int i = 0; i < node.children.size(); i++) {
                if (node.children.get(i).literal > max) {
                    max = node.children.get(i).literal;
                }

                node.children.get(i).visited = true;
            }

            node.literal = max;
        }
        else if (node.packetTypeId == 5) {
            long value = 0;

            try {
                if (node.children.get(0).literal < node.children.get(1).literal) {
                    value = 1;
                }

                node.children.get(0).visited = true;
                node.children.get(1).visited = true;
            } catch (IndexOutOfBoundsException | NullPointerException e) {
                node.children.get(0).visited = true;
                value = 0;
            }

            node.literal = value;
        }
        else if (node.packetTypeId == 6) {
            long value = 0;

            try {
                if (node.children.get(0).literal > node.children.get(1).literal) {
                    value = 1;
                }

                node.children.get(0).visited = true;
                node.children.get(1).visited = true;
            } catch (IndexOutOfBoundsException | NullPointerException e) {
                node.children.get(0).visited = true;
                value = 0;
            }

            node.literal = value;
        }
        else if (node.packetTypeId == 7) {
            long value = 0;

            try {
                if (node.children.get(0).literal == node.children.get(1).literal) {
                    value = 1;
                }

                node.children.get(0).visited = true;
                node.children.get(1).visited = true;
            } catch (IndexOutOfBoundsException | NullPointerException e) {
                node.children.get(0).visited = true;
                value = 0;
            }


            node.literal = value;
        }
    }
}
