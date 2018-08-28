package com.PowerWorkouts.powerworkouts.models.Data;

import com.PowerWorkouts.powerworkouts.models.Users;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Iterator;

@Repository
@Transactional
public interface UsersDao extends CrudRepository<Users, Integer>{

    @Query("Select u from Users u where u.userName=:UN")
    public Iterable<Users> findByuserName(@Param("UN") String userName);

    @Query("Select u from Users u where u.userPassword=:UP")
    public Iterable<Users> findByuserPassword(@Param("UP") String userPassword);

}

