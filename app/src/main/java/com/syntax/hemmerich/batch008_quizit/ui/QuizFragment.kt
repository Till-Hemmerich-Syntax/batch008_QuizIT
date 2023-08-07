package com.syntax.hemmerich.batch008_quizit.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.android.material.dialog.MaterialAlertDialogBuilder

import com.syntax.hemmerich.batch008_quizit.R
import com.syntax.hemmerich.batch008_quizit.databinding.FragmentQuizBinding


/**
 * A simple [Fragment] subclass.
 * Use the [QuizFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class QuizFragment : Fragment() {

    private lateinit var binding: FragmentQuizBinding
    private val viewModel: QuizViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentQuizBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateUI()

        binding.musicianButton.setOnClickListener{
            checkAnswer(true)
        }
        binding.playerButton.setOnClickListener {
            checkAnswer(false)
        }

    }
    private fun checkAnswer(guessMusician : Boolean){
        viewModel.checkAnswer(guessMusician)
        updateUI()
        if(viewModel.score >= 5){
            showEndDialog()
        }
    }
    private fun updateUI(){
        binding.scoreText.text = viewModel.score.toString()
        binding.questionText.text = viewModel.currentVip.name
    }
    private fun showEndDialog(){
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Super du hast gewonnen")
            .setMessage("Dein Score ist: ${viewModel.score}")
            .setCancelable(false)
            .setNegativeButton("Verlassen"){_,_ ->
                activity?.finish()
            }
            .setPositiveButton("Restart"){_,_ ->
                viewModel.restartGame()
                updateUI()
            }
            .show()

    }


}