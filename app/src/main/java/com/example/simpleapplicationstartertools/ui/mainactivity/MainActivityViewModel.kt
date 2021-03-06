package com.example.simpleapplicationstartertools.ui.mainactivity

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.example.simpleapplicationstartertools.di.qualifers.ActivityContext
import com.example.simpleapplicationstartertools.models.DummyModels
import com.example.simpleapplicationstartertools.retrofit.RetrofitClient
import com.example.simpleapplicationstartertools.retrofit.RetrofitInterface
import com.example.simpleapplicationstartertools.room.AppDatabase
import com.example.simpleapplicationstartertools.utils.default
import com.example.simpleapplicationstartertools.viewmodel.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import timber.log.Timber
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(
    @param:ActivityContext private val context: Context,
    private val db: AppDatabase,
    private val retrofitInterFace: RetrofitInterface
) : BaseViewModel() {
    var dummyList =
        MutableLiveData<MutableList<DummyModels.DummyListModel?>>().default(mutableListOf())

    var controlDummyListVisibility = MutableLiveData<Boolean>().default(false)

    var controlFragmentButtonClicked = MutableLiveData<Boolean>().default(false)

    internal fun gatherDummyList() {
        val coroutineCallDummyList = CoroutineScope(Dispatchers.IO)
        coroutineCallDummyList.async {
            retrofitInterFace.getDummyList()
                .enqueue(object : retrofit2.Callback<List<DummyModels.DummyListModel?>> {
                    override fun onFailure(
                        call: Call<List<DummyModels.DummyListModel?>>,
                        t: Throwable
                    ) {
                        //Implement your response failure handling here.
                        Timber.e("request failed!!")
                    }

                    override fun onResponse(
                        call: Call<List<DummyModels.DummyListModel?>>,
                        response: Response<List<DummyModels.DummyListModel?>>
                    ) {
                        //Implement your response Success state here
                        response.body()?.let { body ->
                            dummyList.postValue(body.toMutableList())
                            saveResultsToLocalDb(body.toMutableList())
                        }
                    }
                })
        }
    }

    private fun saveResultsToLocalDb(results: MutableList<DummyModels.DummyListModel?>) {
        val coroutineCallSaveToLocalDb = CoroutineScope(Dispatchers.IO)
        coroutineCallSaveToLocalDb.async {
            results.forEach {
                insertResultsToDb(it)
            }
        }
    }

    private fun insertResultsToDb(dummyList: DummyModels.DummyListModel?) {
        db.dummyDao().insertDummyList(dummyList)
    }

    private fun removeResultsFromDb(dummyList: DummyModels.DummyListModel?) {
        db.dummyDao().deleteDummyList(dummyList?.dummy_1)
    }

    fun fragmentStarterButtonClicked(){
        controlFragmentButtonClicked.postValue(true)
    }
}