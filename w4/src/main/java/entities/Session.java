package entities;

import java.util.Date;

public class Session {
    private long id;
    private Date start;

    private long filmId;
    private long priceId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public long getFilmId() {
        return filmId;
    }

    public void setFilmId(long filmId) {
        this.filmId = filmId;
    }

    public long getPriceId() {
        return priceId;
    }

    public void setPriceId(long priceId) {
        this.priceId = priceId;
    }

    @Override
    public String toString() {
        return String.format("{id: %d; start: '%s'; film_id: %d; price_id: %d}", id, start.toString(), filmId, priceId);
    }
}
