package com.PowerWorkouts.powerworkouts.models.Data;

import com.PowerWorkouts.powerworkouts.models.userSession;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface userSessionDao extends CrudRepository<userSession, Integer>{

    @Query("Select u from userSession u where u.userNameSession=:UN")
    public Iterable<userSession> findByuserNameSession(@Param("UN") String userNameSession);
}
