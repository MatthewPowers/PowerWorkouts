package com.PowerWorkouts.powerworkouts.models.Data;

import com.PowerWorkouts.powerworkouts.models.Exercises;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface ExercisesDao extends CrudRepository<Exercises, Integer> {

    @Query("Select u from Exercises u where u.exerciseName=:UN")
    public Iterable<Exercises> findByexerciseName(@Param("UN") String exerciseName);

}
