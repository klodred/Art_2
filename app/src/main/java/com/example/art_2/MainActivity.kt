package com.example.art_2

import android.R
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.art_2.ArtAdapter.DepartmentAdapter
import com.example.art_2.ArtInfo.ActivityArtInfo
import com.example.art_2.DepartmentList.DepartmentActivity
import com.example.art_2.api.RepositoryRetriever
import com.example.art_2.data.ArtObj
import com.example.art_2.data.Department
import com.example.art_2.data.ListDepartmentObj
import com.example.art_2.databinding.ActivityMainBinding
import com.example.art_2.databinding.DepartmentItemBinding
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var bindingItem: DepartmentItemBinding
    //lateinit var listArt: MutableList<ArtObj>
    lateinit var listDepartment: ListDepartmentObj

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        bindingItem = DepartmentItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (isNetworkConnected()) {
            Log.d("NetworkCheck", "isNetworkAvailable: Yes")
            retrieveListDepartment()
        }

        else {
            Log.d("NetworkCheck", "isNetworkAvailable: No")
            AlertDialog.Builder(this).setTitle("No Internet Connection")
                .setMessage("Please check your internet connection and try again")
                .setPositiveButton(android.R.string.ok) { _, _ -> }.show()
        }
    }

    private fun retrieveListDepartment() {
        val mainActivityJob = Job()
        val errorHandler = CoroutineExceptionHandler { _, exception ->
            AlertDialog.Builder(this).setTitle("Error")
                .setMessage(exception.message)
                .setPositiveButton(R.string.ok) { _, _ -> }
                .show()
        }

        // the Coroutine runs using the Main (UI) dispatcher
        val coroutineScope = CoroutineScope(mainActivityJob + Dispatchers.Main)
        coroutineScope.launch(errorHandler) {
            listDepartment = RepositoryRetriever().getAllDepartments()
            binding.rcDepartment.adapter = DepartmentAdapter(listDepartment,
                                                            {position -> onItemClickDepartment(position)})
            binding.rcDepartment.layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    private fun isNetworkConnected(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetwork
        val networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork)
        return networkCapabilities != null &&
                networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }


    private fun onItemClickDepartment(position: Int) {
        Log.d("POSITION", position.toString())
        listDepartment.department[position].name?.let { Log.d("Name Department", it) }
        val intent = Intent(this, DepartmentActivity::class.java)
        intent.putExtra("department", listDepartment.department[position].id)
        startActivity(intent)
    }
}