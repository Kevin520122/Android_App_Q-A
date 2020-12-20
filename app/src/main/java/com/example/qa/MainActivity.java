package com.example.qa;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    int currentQuestionIndex;
    int totalCorrect;
    int totalQuestions;
    ArrayList<Question> questions;

    // TODO 3-A: Declare View member variables
    ImageView img ;
    Button answer0;
    Button answer1;
    Button answer2;
    Button answer3;
    Button submitButton;

    TextView q;
    TextView remain ;
    TextView info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // TODO 2-G: Show app icon in ActionBar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.ic_unquote_icon);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setElevation(0);

        setContentView(R.layout.activity_main);

        // TODO 3-B: assign View member variables
        img = findViewById(R.id.img);
        answer0 = findViewById(R.id.ans0);
        answer1 = findViewById(R.id.ans1);
        answer2 = findViewById(R.id.ans2);
        answer3 = findViewById(R.id.ans3);
        submitButton = findViewById(R.id.submit);
        q = findViewById(R.id.q);
        remain = findViewById(R.id.remiander);
        info = findViewById(R.id.prompt);


        // TODO 4-E: set onClickListener for each answer Button
        answer0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                //Before update, we need to recover to the origin
                Question cur = questions.get(currentQuestionIndex);
                answer0.setText(cur.answer0);
                answer1.setText(cur.answer1);
                answer2.setText(cur.answer2);
                answer3.setText(cur.answer3);
                onAnswerSelected(0);
            }
        });

        answer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Question cur = questions.get(currentQuestionIndex);
                answer0.setText(cur.answer0);
                answer1.setText(cur.answer1);
                answer2.setText(cur.answer2);
                answer3.setText(cur.answer3);
                onAnswerSelected(1);
            }
        });

        answer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Question cur = questions.get(currentQuestionIndex);
                answer0.setText(cur.answer0);
                answer1.setText(cur.answer1);
                answer2.setText(cur.answer2);
                answer3.setText(cur.answer3);
                onAnswerSelected(2);
            }
        });

        answer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Question cur = questions.get(currentQuestionIndex);
                answer0.setText(cur.answer0);
                answer1.setText(cur.answer1);
                answer2.setText(cur.answer2);
                answer3.setText(cur.answer3);
                onAnswerSelected(3);
            }
        });

        // TODO 5-A: set onClickListener for the submit answer Button
        submitButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                onAnswerSubmission();
            }
        });

        startNewGame();
    }

    // TODO 3-F: displayQuestion(Question question) {...}
    void displayQuestion(Question question){
        q.setText(question.questionText);
        answer0.setText(question.answer0);
        answer1.setText(question.answer1);
        answer2.setText(question.answer2);
        answer3.setText(question.answer3);
        img.setImageResource(question.imageId);
    }

    // TODO 3-C: displayQuestionsRemaining(int questionRemaining) {...}
    void displayQuestionsRemaining(int questionRemaining){
        remain.setText(String.valueOf(questionRemaining));
    }

    // TODO 4-A: onAnswerSelected(int answerSelected) {...}
    void onAnswerSelected(int answerSelected){
        Question cur = getCurrentQuestion();
        cur.playerAnswer = answerSelected;
        switch(answerSelected){
            case 0:
                answer0.setText("✔" + cur.answer0);
                break;

            case 1:
                answer1.setText("✔" + cur.answer1);
                break;
            case 2:
                answer2.setText("✔" + cur.answer2);
                break;
            case 3:
                answer3.setText("✔" + cur.answer3);
                break;

        }
    }

    void onAnswerSubmission() {
        Question currentQuestion = getCurrentQuestion();
        //Fix the bug that the user submit before selecting an answer
        //if(currentQuestion.playerAnswer == -1){return;}

        if (currentQuestion.isCorrect()) {
            totalCorrect = totalCorrect + 1;
        }
        questions.remove(currentQuestion);

        // TODO 3-D.i: Uncomment the line below after implementing displayQuestionsRemaining(int)
        displayQuestionsRemaining(questions.size());

        if (questions.size() == 0) {
            String gameOverMessage = getGameOverMessage(totalCorrect, totalQuestions);

            // TODO 5-D: Show a popup instead
            AlertDialog.Builder gameOverDialogBuilder = new AlertDialog.Builder(MainActivity.this);
            gameOverDialogBuilder.setCancelable(false);
            gameOverDialogBuilder.setTitle("Hi!");
            gameOverDialogBuilder.setMessage(gameOverMessage);

            // (5-F code)
            gameOverDialogBuilder.setPositiveButton("Play Again!", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    startNewGame();
                }
            });

            gameOverDialogBuilder.create().show();
        } else {
            chooseNewQuestion();

            // TODO 3-H.i: uncomment after implementing displayQuestion(Question)
            displayQuestion(getCurrentQuestion());
        }
    }

    void startNewGame() {
        questions = new ArrayList<>();

        // TODO 2-H: Provide actual drawables for each of these questions!
        Question question0 = new Question(R.drawable.img_quote_0, "TPretty good advice,\n" +
                "and perhaps a scientist\n" +
                "did say it… Who\n" +
                "actually did?", "Albert Einstein", "Isaac Newton ", "Rita Mae Brown", "TRosalind Franklin", 2);
        Question question1 = new Question(R.drawable.img_quote_1, "Was honest Abe\n" +
                "honestly quoted? Who\n" +
                "authored this pithy bit\n" +
                "of wisdom?\n", "Edward Stieglitz!", "Maya Angelou", "Abraham Lincoln", "Ralph Waldo Emerson", 2);
        Question question2 = new Question(R.drawable.img_quote_2, "Easy advice to read,\n" +
                "difficult advice to\n" +
                "follow — who actually said it?", "Martin Luther King Jr", "Mother Teresa", "Fred Rogers", "Oprah Winfrey", 2);
        Question question3 = new Question(R.drawable.img_quote_3, "Insanely inspiring,\n" +
                "insanely incorrect\n" +
                "(maybe). Who is the\n" +
                "true source of this\n" +
                "inspiration?\n", "Nelson Mandela", "Harriet Tubman", "Mahatma Gandhi", "Nicholas Klein", 2);
        Question question4 = new Question(R.drawable.img_quote_4, "A peace worth striving\n" +
                "for — who actually\n" +
                "reminded us of this?", "Malala Yousafzai", "Martin Luther King Jr.", "Liu Xiaobo", "Dalai Lama", 2);
        Question question5 = new Question(R.drawable.img_quote_5, "Unfortunately, true —\n" +
                "but did Marilyn Monroe\n" +
                "convey it or did\n" +
                "someone else?", "Laurel Thatcher Ulrich", "Eleanor Roosevelt", "Marilyn Monroe", "Queen Victoria", 2);

        questions.add(question0);
        questions.add(question1);
        questions.add(question2);
        questions.add(question3);
        questions.add(question4);
        questions.add(question5);

        totalCorrect = 0;
        totalQuestions = questions.size();

        Question firstQuestion = chooseNewQuestion();

        // TODO 3-D.ii: Uncomment the line below after implementing displayQuestionsRemaining(int)
        displayQuestionsRemaining(questions.size());

        // TODO 3-H.ii: Uncomment after implementing displayQuestion(Question)
        displayQuestion(firstQuestion);
    }
    //Pick a question
    Question chooseNewQuestion() {
        int newQuestionIndex = generateRandomNumber(questions.size());
        currentQuestionIndex = newQuestionIndex;
        return questions.get(currentQuestionIndex);
    }
    //Randomly generate the question number
    int generateRandomNumber(int max) {
        double randomNumber = Math.random();
        double result = max * randomNumber;
        return (int) result;
    }

    Question getCurrentQuestion() {
        Question currentQuestion = questions.get(currentQuestionIndex);
        return currentQuestion;
    }

    String getGameOverMessage(int totalCorrect, int totalQuestions) {
        if (totalCorrect == totalQuestions) {
            return "You got all " + totalQuestions + " right! You won!";
        } else {
            return "You got " + totalCorrect + " right out of " + totalQuestions + ". Better luck next time!";
        }
    }
}

/* COPY: End here */