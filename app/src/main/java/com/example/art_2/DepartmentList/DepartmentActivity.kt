package com.example.art_2.DepartmentList

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.GridLayoutManager
import com.example.art_2.ArtAdapter.ArtAdapter
import com.example.art_2.ArtAdapter.DepartmentAdapter
import com.example.art_2.ArtInfo.ActivityArtInfo
import com.example.art_2.R
import com.example.art_2.api.RepositoryRetriever
import com.example.art_2.data.ArtId
import com.example.art_2.data.ArtObj
import com.example.art_2.databinding.ActivityDepartmentListBinding
import com.example.art_2.databinding.ActivityMainBinding
import com.example.art_2.databinding.DepartmentItemBinding
import kotlinx.coroutines.*

class DepartmentActivity : AppCompatActivity() {
    lateinit var binding: ActivityDepartmentListBinding
    lateinit var listIDArt: ArtId
    var listArt: MutableList<ArtObj> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDepartmentListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        retrieveListArt()
    }

    private fun retrieveListArt() {
        val mainActivityJob = Job()

        val errorHandler = CoroutineExceptionHandler { _, exception ->
            AlertDialog.Builder(this).setTitle("Error")
                .setMessage(exception.message)
                .setPositiveButton("ОК") { _, _ -> }
                .show()
        }

        // the Coroutine runs using the Main (UI) dispatcher
        val coroutineScope = CoroutineScope(mainActivityJob + Dispatchers.Main)
        coroutineScope.launch(errorHandler) {
            val id = intent.getIntExtra("department", -1)
            Log.d("ID Department", id.toString())
            //val id = 1
            listIDArt = RepositoryRetriever().getArtByDepartment(listOf(id))
            Log.d("List ID Art", listIDArt.toString())

            var art: ArtObj
            var count = 0
            var i = 0


            while (count < 10) {
                art = RepositoryRetriever().getArtById(listIDArt.id[i])
                Log.d("ID Art", listIDArt.id[i].toString())
                art.name?.let { Log.d("Art", it) }
                //art.id = idList[id]

                if (art.urlSmallImage != "") {
                    ++count
                    listArt.add(art)
                }
                ++i
            }

            binding.rcArt.adapter = ArtAdapter(listArt, {position -> onItemClickArt(position)})
            binding.rcArt.layoutManager = GridLayoutManager(this@DepartmentActivity, 2)
        }
    }

    private fun onItemClickArt(position: Int) {
        Log.d("CLICK", "YES")
        val intent = Intent(this, ActivityArtInfo::class.java)
        intent.putExtra("art", listArt[position])
        startActivity(intent)
    }
}