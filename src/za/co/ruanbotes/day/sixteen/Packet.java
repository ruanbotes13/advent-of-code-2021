package za.co.ruanbotes.day.sixteen;

import java.util.ArrayList;
import java.util.List;

public class Packet {
    public PacketType type;
    public long literal;
    public int versionNumber;
    public int packetTypeId;
    public List<Packet> children;
    public Packet parent;
    public boolean visited;

    public Packet(PacketType packetType, long literal, int versionNumber, int packetTypeId, Packet parent) {
        this.type = packetType;
        this.literal = literal;
        this.versionNumber = versionNumber;
        this.packetTypeId = packetTypeId;
        this.children = new ArrayList<>();
        this.visited = false;
        this.parent = parent;
    }
}
