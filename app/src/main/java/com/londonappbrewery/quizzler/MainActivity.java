package com.londonappbrewery.quizzler;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.londonappbrewery.quizzler.models.TrueFalse;

public class MainActivity extends Activity {

    // TODO: Declare constants here


    // TODO: Declare member variables here:
    Button mTrueButton;
    Button mFalseButton;
    TextView mScoreTextView;
    TextView mQuestionTextView;
    ProgressBar mProgressBar;

    int mIndex; //armazena a pergunta corrente
    int mScore; //armazena a quantidade de respostas corretas
    int mQuestion; //recebe o id da pergunta corrente


    // TODO: Uncomment to create question bank
    private TrueFalse[] mQuestionBank = new TrueFalse[] {
            new TrueFalse(R.string.question_1, true),
            new TrueFalse(R.string.question_2, true),
            new TrueFalse(R.string.question_3, true),
            new TrueFalse(R.string.question_4, true),
            new TrueFalse(R.string.question_5, true),
            new TrueFalse(R.string.question_6, false),
            new TrueFalse(R.string.question_7, true),
            new TrueFalse(R.string.question_8, false),
            new TrueFalse(R.string.question_9, true),
            new TrueFalse(R.string.question_10, true),
            new TrueFalse(R.string.question_11, false),
            new TrueFalse(R.string.question_12, false),
            new TrueFalse(R.string.question_13,true)
    };

    final int PROGRESS_BAR_INCREMENT = (int) Math.ceil(100.0 /
            mQuestionBank.length);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTrueButton = findViewById(R.id.true_button);
        mFalseButton = findViewById(R.id.false_button);
        mScoreTextView = findViewById(R.id.score);
        mQuestionTextView = findViewById(R.id.question_text_view);
        mProgressBar = findViewById(R.id.progress_bar);

        //valores iniciais
        if (savedInstanceState != null) {
            mScore = savedInstanceState.getInt("ScoreKey");
            mIndex = savedInstanceState.getInt("IndexKey");
        //atualiza texto do score
        } else {
            mScore = 0;
            mIndex = 0;
        }
        mQuestion = mQuestionBank[mIndex].getQuestionID();
        mQuestionTextView.setText(mQuestion);
        mScoreTextView.setText(mScore+"/"+mQuestionBank.length);

       //implementação tipo 1: direto na classe
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                Toast.makeText(getApplicationContext(),"True",Toast.LENGTH_LONG).show();

              checkAnswer(true);
              updateQuestion();

            }
        });

//        mFalseButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(getApplicationContext(),"False",Toast.LENGTH_SHORT).show();
//              //  checkAnswer(boolean);
//             //   updateQuestion();
//            }
//        });
//
//




    }
    //implementação tipo 2: pelo xml
    public void clickFalse(View view) {
        Toast.makeText(getApplicationContext(),"False",Toast.LENGTH_SHORT).show();
        checkAnswer(false);
        updateQuestion();
    }

    public void checkAnswer (boolean answer){
        if(answer==mQuestionBank[mIndex].isAnswerID()){
            mScore++;
            Toast.makeText(getApplicationContext(),R.string.correct_toast,Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(getApplicationContext(), R.string.incorrect_toast, Toast.LENGTH_LONG).show();
        }

    }

    public void updateQuestion(){
    mScoreTextView.setText("Score: "+mScore+"/"+mQuestionBank.length);
    mIndex++;
    mIndex %= mQuestionBank.length;
    if (mIndex==0){
        //encerrar o app
        AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
        alert.setTitle("Game Over");
        alert.setCancelable(false);
        alert.setMessage("You scored " + mScore + " points!");
        alert.setNegativeButton("Restart Application", new
                DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mScore = 0;
                        mProgressBar.setProgress(0);
                        mScoreTextView.setText("Score: "+mScore+"/"+mQuestionBank.length);
                    }
                });
        alert.setPositiveButton("Close Application", new
                DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
        alert.show();
    }
    mQuestion = mQuestionBank[mIndex].getQuestionID();
    mQuestionTextView.setText(mQuestion);

    mProgressBar.incrementProgressBy(PROGRESS_BAR_INCREMENT);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        outState.putInt("ScoreKey", mScore);
        outState.putInt("IndexKey", mIndex);
    }
}
