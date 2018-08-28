package com.PowerWorkouts.powerworkouts.models.Data;

import com.PowerWorkouts.powerworkouts.models.WorkoutInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface WorkoutInfoDao extends CrudRepository<WorkoutInfo, Integer>
{


}
