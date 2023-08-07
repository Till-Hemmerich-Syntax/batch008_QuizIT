package com.syntax.hemmerich.batch008_quizit.ui

import androidx.lifecycle.ViewModel
import com.syntax.hemmerich.batch008_quizit.data.QuizRepository
import com.syntax.hemmerich.batch008_quizit.data.model.Vip

class QuizViewModel: ViewModel() {

    private val repository = QuizRepository()


    private val vipList = repository.loadVips()
    private val usedVIPS : MutableList<Vip> = mutableListOf()
    private var _score = 0
    val score: Int
        get() = _score

    private var _currentVip = vipList[0]
    val currentVip: Vip
        get() = _currentVip

    fun checkAnswer(isMusician: Boolean){
        if(isMusician == currentVip.isMusician){
            _score++
        }
        getNextVip()
    }

    fun getNextVip(){
        if(usedVIPS.size == vipList.size)return
        usedVIPS.add(currentVip)
        val newVip = vipList.random()

        if(usedVIPS.contains(newVip)){
            getNextVip()
        }else{
            _currentVip = newVip
        }
    }
    fun restartGame(){
        _score = 0
        usedVIPS.removeAll(usedVIPS)
        getNextVip()
    }


}