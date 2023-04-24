package com.example.workoutapp.model

import com.example.workoutapp.R

object Constants {

    fun defaultExerciseList():ArrayList<ExerciseModel>{
        val exerciseList = ArrayList<ExerciseModel>()
        val jumpingJacks = ExerciseModel(
            1,
            "Jumping Jacks",
            R.drawable.jumping_jacks,
            false,
            isSelected = false
            )
        exerciseList.add(jumpingJacks)

        val wallSit = ExerciseModel(
            2,
            "Wall Sit",
            R.drawable.wall_sit,
            false,
            isSelected = false
        )
        exerciseList.add(wallSit)

        val pushUp = ExerciseModel(
            3,
            "Push Up",
            R.drawable.push_up,
            false,
            isSelected = false
        )
        exerciseList.add(pushUp)
        return exerciseList
    }
}