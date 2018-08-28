package com.PowerWorkouts.powerworkouts.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Workouts
{

    @Id
    @GeneratedValue
    private int id;

    private String workoutName = "";
    private String workoutDescription = "";
    private String workoutUserName = "";

    public Workouts(String workoutName, String workoutDescription, String workoutUserName)
    {
        this.workoutName = workoutName;
        this.workoutDescription = workoutDescription;
        this.workoutUserName = workoutUserName;
    }

    public Workouts()
    {
    }

    public String getWorkoutUserName() {
        return workoutUserName;
    }

    public void setWorkoutUserName(String workoutUserName) {
        this.workoutUserName = workoutUserName;
    }

    public int getId() {
        return id;
    }

    public String getWorkoutName() {
        return workoutName;
    }

    public void setWorkoutName(String workoutName) {
        this.workoutName = workoutName;
    }


    public String getWorkoutDescription() {
        return workoutDescription;
    }

    public void setWorkoutDescription(String workoutDescription) {
        this.workoutDescription = workoutDescription;
    }

}
