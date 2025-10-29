package io.github.team10.escapefromuni;

import com.badlogic.gdx.graphics.Texture;

/**
 * Represents a single Room.
 * 
 * A room can be connected to up to 4 adjacent rooms, and may contain an event.
 */
public class Room {
    public RoomManager roomManager;
    private Event event;
    private Room[] adjacentRooms = new Room[4];
    private Texture roomTexture;

    /**
     * Initialises a new room.
     * @param roomTexture The texture for this room.
     */
    public Room(Texture roomTexture)
    {
        this.roomTexture = roomTexture;
    }

    public Texture getRoomTexture()
    {
        return roomTexture;
    }

    /**
     * Adds a connection to an adjacent room, given a direction. 
     * @param adjacentRoom The adjacent room to connect to.
     * @param direction Direction of the new room in relation to this room.
     */
    public void addAdjacent(Room adjacentRoom, DoorDirection direction)
    {
        if (direction == DoorDirection.NORTH) adjacentRooms[0] = adjacentRoom;
        if (direction == DoorDirection.EAST) adjacentRooms[1] = adjacentRoom;
        if (direction == DoorDirection.SOUTH) adjacentRooms[2] = adjacentRoom;
        if (direction == DoorDirection.WEST) adjacentRooms[3] = adjacentRoom;
    }

    /**
     * Return a specific adjacent room based on a direction.
     * 
     * @param direction Direction of the adjacent room to return.
     * @return Room representing adjacentRoom if it exists, null otherwise.
     */
    public Room getAdjacent(DoorDirection direction)
    {
        if (direction == DoorDirection.NORTH) return adjacentRooms[0];
        if (direction == DoorDirection.EAST) return adjacentRooms[1];
        if (direction == DoorDirection.SOUTH) return adjacentRooms[2];
        if (direction == DoorDirection.WEST) return adjacentRooms[3];
        return null;
    }

    /**
     * Returns the array storing adjacent rooms.
     * 
     * If a room doesn't exist in that direction null is stored instead.
     * @return Array of size 4, with order {NORTH, EAST, SOUTH, WEST}.
     */
    public Room[] getAllAdjacent()
    {
        return adjacentRooms;
    }

    public void setEvent(Event event)
    {
        this.event = event;
    }

    public EventType getEventType() {
        if (event != null)
        {
            return event.type;
        }
        return null;
    }

    /**
     * Perform actions after room is loaded e.g. play event.
     */
    public void start() {
        if (event != null) event.startEvent();
    }
}
