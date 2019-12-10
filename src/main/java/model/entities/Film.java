package model.entities;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Base64;
import java.util.Objects;

public class Film implements Serializable {
    private Long id;
    private String title;
    private String genre;
    private String director;
    private String writer;
    private String actors;
    private String country;
    private String release_date;
    private Integer duration_minutes;
    private Integer age_limit;
    private String description;
    private Poster poster;

    {
        this.poster = new Poster();
    }

    public Film() {
    }

    public Film(String title, String genre, String director, String writer,
                String actors, String country, String release_date, Integer duration_minutes,
                Integer age_limit, String description) {
        this.title = title;
        this.genre = genre;
        this.director = director;
        this.writer = writer;
        this.actors = actors;
        this.country = country;
        this.release_date = release_date;
        this.duration_minutes = duration_minutes;
        this.age_limit = age_limit;
        this.description = description;
    }

    public Film(Long id, String title, String genre, String director,
                String writer, String actors, String country, String release_date,
                Integer duration_minutes, Integer age_limit, String description) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.director = director;
        this.writer = writer;
        this.actors = actors;
        this.country = country;
        this.release_date = release_date;
        this.duration_minutes = duration_minutes;
        this.age_limit = age_limit;
        this.description = description;
    }

    public class Poster implements Serializable, Cloneable {
        private Long id;
        private String name;
        private byte[] image;

        public Poster() {
        }

        public Poster(Long id, String name, byte[] image) {
            this.id = id;
            this.name = name;
            this.image = image;
        }

        public Long getId() {
            return id ;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public byte[] getImage() {
            return image;
        }

        public void setImage(byte[] image) {
            this.image = image.clone();
        }

        public String getBase64Image(){
            return Base64.getEncoder().encodeToString(this.image);
        }

        @Override
        public Poster clone() {
            try {
                return (Poster) super.clone();
            } catch (CloneNotSupportedException e) {
                return new Poster(this.id, this.name, this.image.clone());
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Poster poster = (Poster) o;
            return Objects.equals(id, poster.id) &&
                    Objects.equals(name, poster.name) &&
                    Arrays.equals(image, poster.image);
        }

        @Override
        public int hashCode() {
            int result = Objects.hash(id, name);
            result = 31 * result + Arrays.hashCode(image);
            return result;
        }

        @Override
        public String toString() {
            return "Poster{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", image=" + Arrays.toString(image) +
                    '}';
        }
    } //end of Poster class

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public Integer getDuration_minutes() {
        return duration_minutes;
    }

    public void setDuration_minutes(Integer duration_minutes) {
        this.duration_minutes = duration_minutes;
    }

    public Integer getAge_limit() {
        return age_limit;
    }

    public void setAge_limit(Integer age_limit) {
        this.age_limit = age_limit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Poster getPoster() {
        return poster;
    }

    public void setPoster(Poster poster) {
        this.poster = poster.clone();
    }

    @Override
    public Film clone() {
        Film film;
        try {
            film = (Film) super.clone();
        } catch (CloneNotSupportedException e) {
            film = new Film(this.id, this.title, this.genre, this.director,
                    this.writer, this.actors, this.country, this.release_date,
                    this.duration_minutes, this.age_limit, this.description);
        }
        film.poster = this.poster.clone();
        return film;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Film film = (Film) o;
        return Objects.equals(id, film.id) &&
                Objects.equals(title, film.title) &&
                Objects.equals(genre, film.genre) &&
                Objects.equals(director, film.director) &&
                Objects.equals(writer, film.writer) &&
                Objects.equals(actors, film.actors) &&
                Objects.equals(country, film.country) &&
                Objects.equals(release_date, film.release_date) &&
                Objects.equals(duration_minutes, film.duration_minutes) &&
                Objects.equals(age_limit, film.age_limit) &&
                Objects.equals(description, film.description) &&
                Objects.equals(poster, film.poster);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, genre, director, writer, actors, country, release_date, duration_minutes, age_limit, description, poster);
    }

    @Override
    public String toString() {
        return "Film{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", director='" + director + '\'' +
                ", writer='" + writer + '\'' +
                ", actors='" + actors + '\'' +
                ", country='" + country + '\'' +
                ", release_date='" + release_date + '\'' +
                ", duration_minutes=" + duration_minutes +
                ", age_limit=" + age_limit +
                ", description='" + description + '\'' +
                ", poster=" + poster +
                '}';
    }
}
