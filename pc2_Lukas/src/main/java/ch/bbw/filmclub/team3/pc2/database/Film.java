package ch.bbw.filmclub.team3.pc2.database;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Film {
    private int filmId;
    private String title;
    private String format;
    private String director;
    private Integer yearOfProduction;
    private Integer duration;
    private String distributor;
    private Collection<Presentation> presentationsByFilmId;

    @Id
    @Column(name = "film_id")
    public int getFilmId() {
        return filmId;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "format")
    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    @Basic
    @Column(name = "director")
    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    @Basic
    @Column(name = "year_of_production")
    public Integer getYearOfProduction() {
        return yearOfProduction;
    }

    public void setYearOfProduction(Integer yearOfProduction) {
        this.yearOfProduction = yearOfProduction;
    }

    @Basic
    @Column(name = "duration")
    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    @Basic
    @Column(name = "distributor")
    public String getDistributor() {
        return distributor;
    }

    public void setDistributor(String distributor) {
        this.distributor = distributor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Film film = (Film) o;

        if (filmId != film.filmId) return false;
        if (title != null ? !title.equals(film.title) : film.title != null) return false;
        if (format != null ? !format.equals(film.format) : film.format != null) return false;
        if (director != null ? !director.equals(film.director) : film.director != null) return false;
        if (yearOfProduction != null ? !yearOfProduction.equals(film.yearOfProduction) : film.yearOfProduction != null)
            return false;
        if (duration != null ? !duration.equals(film.duration) : film.duration != null) return false;
        if (distributor != null ? !distributor.equals(film.distributor) : film.distributor != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = filmId;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (format != null ? format.hashCode() : 0);
        result = 31 * result + (director != null ? director.hashCode() : 0);
        result = 31 * result + (yearOfProduction != null ? yearOfProduction.hashCode() : 0);
        result = 31 * result + (duration != null ? duration.hashCode() : 0);
        result = 31 * result + (distributor != null ? distributor.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "filmByFilmIdfs")
    public Collection<Presentation> getPresentationsByFilmId() {
        return presentationsByFilmId;
    }

    public void setPresentationsByFilmId(Collection<Presentation> presentationsByFilmId) {
        this.presentationsByFilmId = presentationsByFilmId;
    }
}
