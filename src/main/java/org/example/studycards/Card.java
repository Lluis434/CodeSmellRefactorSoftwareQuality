package org.example.studycards;

public class Card {
    private String question;
    private String answer;

    public Card(String question, String answer) {
        if (isInvalid(question) || isInvalid(answer)) {
            throw new IllegalArgumentException("Question and answer must not be empty");
        }
        this.question = question;
        this.answer = answer;
    }

    public void edit(String question, String answer) {
        setQuestion(question);
        setAnswer(answer);
    }

    // ✅ Reintroduzidos para compatibilidade com outras classes
    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setQuestion(String question) {
        if (isInvalid(question)) {
            throw new IllegalArgumentException("Question must not be empty");
        }
        this.question = question;
    }

    public void setAnswer(String answer) {
        if (isInvalid(answer)) {
            throw new IllegalArgumentException("Answer must not be empty");
        }
        this.answer = answer;
    }

    public boolean contains(String text) {
        return question.contains(text) || answer.contains(text);
    }

    public String formatWithId(Integer id) {
        return "[id: " + id + "] Question: " + this.question + " Answer: " + this.answer;
    }

    public boolean isAnswerCorrect(String userAnswer) {
        return normalize(answer).equals(normalize(userAnswer));
    }

    private boolean isInvalid(String text) {
        return text == null || text.trim().isEmpty();
    }

    private String normalize(String text) {
        return text.trim().toLowerCase();
    }
}
