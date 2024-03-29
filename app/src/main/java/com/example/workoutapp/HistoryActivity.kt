package com.example.workoutapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.workoutapp.adapter.HistoryAdapter
import com.example.workoutapp.databinding.ActivityHistoryBinding
import com.example.workoutapp.room.HistoryDao
import com.example.workoutapp.room.WorkOutApp
import kotlinx.coroutines.launch

class HistoryActivity : AppCompatActivity() {
    private var binding: ActivityHistoryBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)

        binding?.toolbarHistoryActivity?.setNavigationOnClickListener {
            onBackPressed()
        }
        val dao = (application as WorkOutApp).db.historyDao()
        getAllCompletedDates(dao)
    }

    private fun getAllCompletedDates(historyDao: HistoryDao) {
        lifecycleScope.launch {
            historyDao.fetchALlDates().collect { allCompletedDatesList ->
                if (allCompletedDatesList.isNotEmpty()) {
                    binding?.tvHistory?.visibility = View.VISIBLE
                    binding?.rvHistory?.visibility = View.VISIBLE
                    binding?.tvNoDataAvailable?.visibility = View.GONE
                    binding?.rvHistory?.layoutManager = LinearLayoutManager(this@HistoryActivity)
                            val dates = ArrayList<String>()
                            for (date in allCompletedDatesList) {
                                dates.add(date.date)
                            }

                        val historyAdapter = HistoryAdapter(ArrayList(dates))
                        binding?.rvHistory?.adapter = historyAdapter
                    }else{
                    binding?.tvHistory?.visibility = View.GONE
                    binding?.rvHistory?.visibility = View.GONE
                    binding?.tvNoDataAvailable?.visibility = View.VISIBLE
                }
                }
            }
        }
    }
