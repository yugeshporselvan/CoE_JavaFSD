import java.util.EnumSet;

class MeetingRoom {
    private String roomId;
    private String roomName;
    private int capacity;
    private EnumSet<RoomFeature> features;

    public MeetingRoom(String roomId, String roomName, int capacity, EnumSet<RoomFeature> features) {
        this.roomId = roomId;
        this.roomName = roomName;
        this.capacity = capacity;
        this.features = features;
    }

    public String getRoomId() { return roomId; }
    public String getRoomName() { return roomName; }
    public EnumSet<RoomFeature> getFeatures() { return features; }
}
