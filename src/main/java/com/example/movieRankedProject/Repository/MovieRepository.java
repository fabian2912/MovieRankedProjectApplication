package com.example.movieRankedProject.Repository;

import com.example.movieRankedProject.Model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query("SELECT m FROM Movie m WHERE m.Title = :title")
    Movie findByTitle(@Param("title")String title);
//    Movie findByTitle(String Title);

}
