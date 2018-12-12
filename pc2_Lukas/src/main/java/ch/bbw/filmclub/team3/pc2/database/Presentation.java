package ch.bbw.filmclub.team3.pc2.database;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.util.Collection;

@Entity
public class Presentation {
    private int presentationId;
    private int filmIdfs;
    private int roomIdfs;
    private Date date;
    private Time time;
    private int price;
    private Film filmByFilmIdfs;
    private Room roomByRoomIdfs;
    private Collection<Visit> visitsByPresentationId;

    @Id
    @Column(name = "presentation_id")
    public int getPresentationId() {
        return presentationId;
    }

    public void setPresentationId(int presentationId) {
        this.presentationId = presentationId;
    }

    @Basic
    @Column(name = "film_idfs")
    public int getFilmIdfs() {
        return filmIdfs;
    }

    public void setFilmIdfs(int filmIdfs) {
        this.filmIdfs = filmIdfs;
    }

    @Basic
    @Column(name = "room_idfs")
    public int getRoomIdfs() {
        return roomIdfs;
    }

    public void setRoomIdfs(int roomIdfs) {
        this.roomIdfs = roomIdfs;
    }

    @Basic
    @Column(name = "date")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Basic
    @Column(name = "time")
    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    @Basic
    @Column(name = "price")
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Presentation that = (Presentation) o;

        if (presentationId != that.presentationId) return false;
        if (filmIdfs != that.filmIdfs) return false;
        if (roomIdfs != that.roomIdfs) return false;
        if (price != that.price) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (time != null ? !time.equals(that.time) : that.time != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = presentationId;
        result = 31 * result + filmIdfs;
        result = 31 * result + roomIdfs;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + price;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "film_idfs", referencedColumnName = "film_id", nullable = false, insertable = false, updatable = false)
    public Film getFilmByFilmIdfs() {
        return filmByFilmIdfs;
    }

    public void setFilmByFilmIdfs(Film filmByFilmIdfs) {
        this.filmByFilmIdfs = filmByFilmIdfs;
    }

    @ManyToOne
    @JoinColumn(name = "room_idfs", referencedColumnName = "room_id", nullable = false, insertable = false, updatable = false)
    public Room getRoomByRoomIdfs() {
        return roomByRoomIdfs;
    }

    public void setRoomByRoomIdfs(Room roomByRoomIdfs) {
        this.roomByRoomIdfs = roomByRoomIdfs;
    }

    @OneToMany(mappedBy = "presentationByPresentationIdfs")
    public Collection<Visit> getVisitsByPresentationId() {
        return visitsByPresentationId;
    }

    public void setVisitsByPresentationId(Collection<Visit> visitsByPresentationId) {
        this.visitsByPresentationId = visitsByPresentationId;
    }
}
