package uk.ac.cardiff.disasterdash.DTO;

public class QuizDTO{

    private int ID;
    private String type;
    private int questionIndex;
    private int choiceNum;
    private String question;
    private String choiceA;
    private String choiceB;
    private String choiceC;
    private String choiceD;
    private String correct;
    private String hint;


    public QuizDTO(){

    }

    public QuizDTO(int ID, String type, int questionIndex, int choiceNum, String question, String choiceA, String choiceB, String choiceC, String choiceD, String hint) {
        this.ID = ID;
        this.questionIndex = questionIndex;
        this.type = type;
        this.choiceNum = choiceNum;
        this.question = question;
        this.choiceA = choiceA;
        this.choiceB = choiceB;
        this.choiceC = choiceC;
        this.choiceD = choiceD;
        this.hint = hint;
    }
    public QuizDTO(int ID, int questionIndex) {
        this.ID = ID;
        this.questionIndex = questionIndex;
    }

    public QuizDTO(int ID, String type, int questionIndex, int choiceNum, String question, String correct) {
        this.ID = ID;
        this.type = type;
        this.questionIndex = questionIndex;
        this.choiceNum = choiceNum;
        this.question = question;
        this.correct = correct;
    }

    public int getID() {
        return ID;
    }

    public int getquestionIndex() {
        return questionIndex;
    }

    public void setquestionIndex(int questionIndex) {
        this.questionIndex = questionIndex;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getChoiceNum() {
        return choiceNum;
    }

    public void setChoiceNum(int choiceNum) {
        this.choiceNum = choiceNum;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getChoiceA() {
        return choiceA;
    }

    public void setChoiceA(String choiceA) {
        this.choiceA = choiceA;
    }

    public String getChoiceB() {
        return choiceB;
    }

    public void setChoiceB(String choiceB) {
        this.choiceB = choiceB;
    }

    public String getChoiceC() {
        return choiceC;
    }

    public void setChoiceC(String choiceC) {
        this.choiceC = choiceC;
    }

    public String getChoiceD() {
        return choiceD;
    }

    public void setChoiceD(String choiceD) {
        this.choiceD = choiceD;
    }

    public String getCorrect() {
        return correct;
    }

    public void setCorrect(String correct) {
        this.correct = correct;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }
}
