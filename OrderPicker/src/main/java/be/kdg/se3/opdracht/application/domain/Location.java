package be.kdg.se3.opdracht.application.domain;

public class Location {
    private String storageRoom;
    private int hallway;
    private int rack;

    public Location(String storageRoom, int hallway, int rack) {
        this.storageRoom = storageRoom;
        this.hallway = hallway;
        this.rack = rack;
    }

    public String getStorageRoom() {
        return storageRoom;
    }

    public void setStorageRoom(String storageRoom) {
        this.storageRoom = storageRoom;
    }

    public int getHallway() {
        return hallway;
    }

    public void setHallway(int hallway) {
        this.hallway = hallway;
    }

    public int getRack() {
        return rack;
    }

    public void setRack(int rack) {
        this.rack = rack;
    }

    @Override
    public String toString() {
        return "Product location{" +
                "storageRoom='" + storageRoom + '\'' +
                ", hallway=" + hallway +
                ", rack=" + rack +
                '}';
    }
}
