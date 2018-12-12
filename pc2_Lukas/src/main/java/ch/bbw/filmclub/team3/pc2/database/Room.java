package ch.bbw.filmclub.team3.pc2.database;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Room {
    private int roomId;
    private String name;
    private String address;
    private String city;
    private int nrOfSeats;
    private Collection<Presentation> presentationsByRoomId;

    @Id
    @Column(name = "room_id")
    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "city")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    @Column(name = "nr_of_seats")
    public int getNrOfSeats() {
        return nrOfSeats;
    }

    public void setNrOfSeats(int nrOfSeats) {
        this.nrOfSeats = nrOfSeats;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Room room = (Room) o;

        if (roomId != room.roomId) return false;
        if (nrOfSeats != room.nrOfSeats) return false;
        if (name != null ? !name.equals(room.name) : room.name != null) return false;
        if (address != null ? !address.equals(room.address) : room.address != null) return false;
        if (city != null ? !city.equals(room.city) : room.city != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = roomId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + nrOfSeats;
        return result;
    }

    @OneToMany(mappedBy = "roomByRoomIdfs")
    public Collection<Presentation> getPresentationsByRoomId() {
        return presentationsByRoomId;
    }

    public void setPresentationsByRoomId(Collection<Presentation> presentationsByRoomId) {
        this.presentationsByRoomId = presentationsByRoomId;
    }
}
