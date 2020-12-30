package project.st991488064.vnj.cycling

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import project.st991488064.vnj.database.ActivityDatabase
import project.st991488064.vnj.database.models.ActivitiesEntity
import project.st991488064.vnj.database.models.CyclingEntity


//ViewModel provides data to UI
//ViewModel acts as center for communication between Repository and UI
//Extends AndroidViewModel - different than regular ViewModel as it contains Application reference
class CyclingViewModel(application: Application) : AndroidViewModel(application) {

    private val getAllCyclingData: LiveData<List<CyclingEntity>>
    private val cyclingRepository: CyclingRepository
    val getAll: LiveData<List<ActivitiesEntity>>

    //init block will be first executed when CyclingViewModel will be called
    init {

        val cyclingDao = ActivityDatabase.getInstance(application).cyclingDao()
        cyclingRepository = CyclingRepository(cyclingDao)
        getAllCyclingData = cyclingRepository.getAllCyclingData
        getAll = cyclingRepository.getAll

    }

    //function to add cycling data
    fun insertCyclingData(cyclingEntity: CyclingEntity) {
        //viewModelScope is part of kotlin coroutines
        //Dispatcher.IO makes this code to run in Background thread
        viewModelScope.launch(Dispatchers.IO) {
            cyclingRepository.insertCyclingData(cyclingEntity)
        }
    }


    //function to update data
    fun updateCyclingEntity(cyclingData: CyclingEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            cyclingRepository.updateCyclingEntity(cyclingData)
        }
    }

    fun deleteCyclingEntity(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            cyclingRepository.deleteCyclingEntity(id)
        }
    }

    //function to delete all data
    fun deleteAllCyclingEntity() {
        viewModelScope.launch(Dispatchers.IO) {
            cyclingRepository.deleteAllCyclingEntity()
        }
    }


}