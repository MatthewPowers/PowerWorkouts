package com.PowerWorkouts.powerworkouts.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Exercises
{

    @Id
    @GeneratedValue
    private int id;

    private String exerciseName;
    private String description;

    public Exercises(String exerciseName, String description){
        this.exerciseName = exerciseName;
        this.description = description;
    }

    public Exercises()
    {
    }

    public int getId() {
        return id;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
