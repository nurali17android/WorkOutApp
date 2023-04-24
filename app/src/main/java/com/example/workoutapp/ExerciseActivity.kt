package com.example.workoutapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Toast
import com.example.workoutapp.databinding.ActivityExerciseBinding
import com.example.workoutapp.model.Constants
import com.example.workoutapp.model.ExerciseModel

class ExerciseActivity : AppCompatActivity() {
    private var binding : ActivityExerciseBinding? = null

    private var restTimer: CountDownTimer? = null
    private var resProgress = 0

    private var exerciseTimer: CountDownTimer? = null
    private var exerciseProgress = 0

    private var exerciseList : ArrayList<ExerciseModel>? = null
    private var currentExercisePosition = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExerciseBinding.inflate(layoutInflater)
        setSupportActionBar(binding?.toolBarExercise)
        setContentView(binding?.root)

        if(supportActionBar!=null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        } // activate back button

        exerciseList = Constants.defaultExerciseList()
        binding?.toolBarExercise?.setNavigationOnClickListener {
            onBackPressed()
        }


        setUpRestView()
    }

    private fun setRestProgressBar(){
        binding?.progressBar?.progress = resProgress
        restTimer = object :CountDownTimer(10000,1000){
            override fun onTick(millisUntilFinished: Long) {
                resProgress++
                binding?.progressBar?.progress = 10 - resProgress
                binding?.tvTimer?.text = (10-resProgress).toString()
            }

            override fun onFinish() {
                currentExercisePosition++
            setUpExerciseView()
            }
        }.start()
    }
    private fun setUpRestView(){
        if(restTimer!=null){
            restTimer?.cancel()
            resProgress = 0
        }
        setRestProgressBar()
    }
    private fun setExerciseProgressBar(){
        binding?.progressBarView?.progress = exerciseProgress
        exerciseTimer = object :CountDownTimer(30000,1000){
            override fun onTick(millisUntilFinished: Long) {
                exerciseProgress++
                binding?.progressBarView?.progress = 30 - exerciseProgress
                binding?.tvTimerView?.text = (30-exerciseProgress).toString()
            }

            override fun onFinish() {
                Toast.makeText(this@ExerciseActivity,
                    "30 seconds over let's go to the restView",
                    Toast.LENGTH_SHORT).show()
            }
        }.start()
    }
    private fun setUpExerciseView(){
        binding?.frameLayoutExerciseView?.visibility = View.INVISIBLE

        binding?.tvTitle?.text = "Exercise Name"
        binding?.frameLayoutExerciseView?.visibility = View.VISIBLE


        if(exerciseTimer!=null){
            exerciseTimer?.cancel()
            exerciseProgress = 0
        }
        setExerciseProgressBar()
    }

    override fun onDestroy() {
        super.onDestroy()
        if(restTimer!=null){
            restTimer?.cancel()
            resProgress = 0
        }

        if(exerciseTimer!=null){
            exerciseTimer?.cancel()
            exerciseProgress = 0
        }
        binding = null
    }
}