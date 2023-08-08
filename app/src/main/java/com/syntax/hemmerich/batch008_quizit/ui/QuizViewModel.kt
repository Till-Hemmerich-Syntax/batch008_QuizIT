package com.syntax.hemmerich.batch008_quizit.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.syntax.hemmerich.batch008_quizit.data.QuizRepository
import com.syntax.hemmerich.batch008_quizit.data.model.Vip

class QuizViewModel: ViewModel() {

    private val repository = QuizRepository()


    private val vipList = repository.loadVips()
    private val usedVIPS : MutableList<Vip> = mutableListOf()
    private var _score = MutableLiveData<Int>(0)
    val score: LiveData<Int>
        get() = _score

    private var _currentVip = MutableLiveData<Vip>(vipList[0])
    val currentVip: LiveData<Vip>
        get() = _currentVip

    fun checkAnswer(isMusician: Boolean){
        if(isMusician == currentVip.value!!.isMusician ){
            _score.value = _score.value?.plus(1)
        }
        getNextVip()
    }

    fun getNextVip(){
        if(usedVIPS.size == vipList.size){
            return // <- bricht die funktion ab.
        }
        usedVIPS.add(currentVip.value!!)
        val newVip = vipList.random()

        if(usedVIPS.contains(newVip)){
            getNextVip()
        }else{
            _currentVip.value = newVip
        }
    }
    fun restartGame(){
        _score.value = 0
        usedVIPS.removeAll(usedVIPS)
        getNextVip()
    }


}