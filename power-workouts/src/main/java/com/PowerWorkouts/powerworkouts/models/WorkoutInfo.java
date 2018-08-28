package com.PowerWorkouts.powerworkouts.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class WorkoutInfo {

    @Id
    @GeneratedValue
    private int id;

    private int workoutId;
    private int exerciseId;
    private String exerciseName;
    private int sets;
    private int reps;
    //private int order;



    public WorkoutInfo(int workoutId, int exerciseId, int sets, int reps,  String exerciseName)//, int order
    {
        this.workoutId = workoutId;
        this.exerciseId = exerciseId;
        this.sets = sets;
        this.reps = reps;
        this.exerciseName = exerciseName;
        //this.order = order;

    }

    public WorkoutInfo()
    {
    }

    public int getId() {
        return id;
    }

    public int getWorkoutId() {
        return workoutId;
    }

    public void setWorkoutId(int workoutId) {
        this.workoutId = workoutId;
    }

    public int getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(int exerciseId) {
        this.exerciseId = exerciseId;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }
    /*
    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
    */

}
