package com.syntax.hemmerich.batch008_quizit.data

import com.syntax.hemmerich.batch008_quizit.data.model.Vip

class QuizRepository {


    fun loadVips(): List<Vip> {
        val viplist = listOf(
            Vip("Andrea Bocelli", true),
            Vip("Adriano Celentano", true),
            Vip("Eros Ramazotti", true),
            Vip("Antonio Vivaldi", true),
            Vip("Francesco Guccini", true),
            Vip("Franco Battiato", true),
            Vip("Lucio Battisti", true),
            Vip("Gino Paoli", true),
            Vip("Francesco Acerbi", false),
            Vip("Mario Balotelli", false),
            Vip("Andrea Belotti", false),
            Vip("Roberto Baggio", false),
            Vip("Alessandro Del Piero", false),
            Vip("Andrea Pirlo", false),
            Vip("Marco Materazzi", false),
            Vip("Gianluigi Buffon", false)
        )

        return viplist.shuffled()
    }
}