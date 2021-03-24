// This class is required to create waiting timeline objects from incoming data
package lines;

public class WaitingTimeLine implements AbstractLine {

    //fields
    public static final String identifier = "C";
    private int id;
    private int service;
    private int serviceVariant;
    private int questionType;
    private int categoryOfQuestion;
    private int subCategoryOfQuestion;
    private String typeOfAnswer;
    private String dateOfAnswer;
    private int waitingTime;

    //constructor
    public WaitingTimeLine() {
    }

    //getters and setters


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getService() {
        return service;
    }

    public void setService(int service) {
        this.service = service;
    }

    public int getServiceVariant() {
        return serviceVariant;
    }

    public void setServiceVariant(int serviceVariant) {
        this.serviceVariant = serviceVariant;
    }

    public int getQuestionType() {
        return questionType;
    }

    public void setQuestionType(int questionType) {
        this.questionType = questionType;
    }

    public int getCategoryOfQuestion() {
        return categoryOfQuestion;
    }

    public void setCategoryOfQuestion(int categoryOfQuestion) {
        this.categoryOfQuestion = categoryOfQuestion;
    }

    public int getSubCategoryOfQuestion() {
        return subCategoryOfQuestion;
    }

    public void setSubCategoryOfQuestion(int subCategoryOfQuestion) {
        this.subCategoryOfQuestion = subCategoryOfQuestion;
    }

    public String getTypeOfAnswer() {
        return typeOfAnswer;
    }

    public void setTypeOfAnswer(String typeOfAnswer) {
        this.typeOfAnswer = typeOfAnswer;
    }

    public String getDateOfAnswer() {
        return dateOfAnswer;
    }

    public void setDateOfAnswer(String dateOfAnswer) {
        this.dateOfAnswer = dateOfAnswer;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(int waitingTime) {
        this.waitingTime = waitingTime;
    }
}
