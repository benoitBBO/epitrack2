package org.example.infrastructure.repository;

import org.example.domaine.catalog.Movie;
import org.example.domaine.catalog.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISerieRepository extends JpaRepository <Serie, Long> {
    List<Serie> findFirst4ByOrderByTotalRatingDesc();
    List<Serie> findByTitleContainsIgnoreCase(String title);

    @Modifying
    @Query("update Serie serie set serie.totalRating = :newrating where serie.id = :serieId")
    void updateSerieRating(@Param(value = "serieId")Long serieId, @Param(value = "newrating")Integer newRating);

    @Modifying
    @Query("update Serie serie set serie.voteCount = :newVoteCount where serie.id = :serieId")
    void updateSerieVotes(@Param(value = "serieId")Long movieId, @Param(value = "newVoteCount") Integer newVoteCount);
}
