package com.codecool.movies.movies.services;

import com.codecool.movies.movies.models.Movie;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    private final JdbcTemplate jdbcTemplate;

    public MovieService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Movie> findAll() {
        List<Movie> result = jdbcTemplate.query(
                """
                        select f.film_id, title, description, release_year, c.name as category_name, rating
                        from film f
                        join film_category fc on f.film_id = fc.film_id
                        join category c on c.category_id = fc.category_id
                        where c."name" like 'Sci-Fi%'
                        and lower(f.description) like '%robot%'
                        """
                ,
                (rs, rowNum) -> new Movie(
                        rs.getInt("film_id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getInt("release_year"),
                        rs.getString("rating"),
                        rs.getString("category_name")
                )
        );

        System.out.println(result);
        return result;
    }

    public List<Movie> findByDescription(String description) {
        String sql = "select f.film_id, title, description, release_year, c.name as category_name, rating\n" +
                     "from film f\n" +
                     "join film_category fc on f.film_id = fc.film_id\n" +
                     "join category c on c.category_id = fc.category_id\n" +
                     "where c.\"name\" like 'Sci-Fi%'\n" +
                     "and lower(f.description) like ?\n";

        RowMapper<Movie> movieRowMapper = (rs, rowNum) -> new Movie(
                rs.getInt("film_id"),
                rs.getString("title"),
                rs.getString("description"),
                rs.getInt("release_year"),
                rs.getString("rating"),
                rs.getString("category_name")
        );
        List<Movie> result = jdbcTemplate.query(sql,
                ps -> ps.setString(1, "%" + description + "%"),
                movieRowMapper
        );

        System.out.println(result);
        return result;

    }
}
