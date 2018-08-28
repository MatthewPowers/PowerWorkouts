package com.PowerWorkouts.powerworkouts.models.Data;

import com.PowerWorkouts.powerworkouts.models.Workouts;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface WorkoutsDao extends CrudRepository<Workouts, Integer>{
}
