package tech.kuizz.api.view.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import tech.kuizz.api.NetworkResponse
import tech.kuizz.api.data.MyApiData
import tech.kuizz.api.interfaces.RetrofitInstance

class QuizViewModel : ViewModel() {

    private val quizApi = RetrofitInstance.quizApi
    private val _quizResult = MutableLiveData<NetworkResponse<MyApiData>>()
    val quizResult: LiveData<NetworkResponse<MyApiData>> = _quizResult


    private val _currentIndex = MutableLiveData(0)
    val currentIndex: LiveData<Int> = _currentIndex

    fun getQuestion(
        amount: Int,
        category: Int? = null,
        difficulty: String? = null,
        type: String? = null
    ) {
        _quizResult.value = NetworkResponse.Loading
        viewModelScope.launch {
            try {
                val response = quizApi.getResult(amount, category, difficulty, type)
                if (response.isSuccessful) {
                    response.body()?.let {
                        _quizResult.value = NetworkResponse.Success(it)
                        _currentIndex.value = 0 
                    }
                    Log.d("Successful", "getQuestion: ${response.body()}")
                } else {
                    _quizResult.value = NetworkResponse.Error("Error unable to fetch data")
                    Log.d("Error", "getQuestion: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                _quizResult.value = NetworkResponse.Error("Exception: ${e.message}")
                Log.d("Exception", "getQuestion: ${e.message}")
            }
        }
    }

}
