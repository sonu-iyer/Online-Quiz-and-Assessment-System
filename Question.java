package com.soni.pm;

public class Question {
    int id;
    String question;
    String[] options;
    int correct;

    public Question(int id, String question, String[] options, int correct) {
        this.id = id;
        this.question = question;
        this.options = options;
        this.correct = correct;
    }
}