// // This class is required to create query objects from incoming data
package lines;

public class QueryLine implements AbstractLine {

    //fields
    public static final String identifier = "D";
    private int id;
    private String service;
    private int serviceVariant;
    private String questionType;
    private int categoryOfQuestion;
    private int subCategoryOfQuestion;
    private String typeOfAnswer;
    private String periodOfAnswer;

    //constructor
    public QueryLine() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public int getServiceVariant() {
        return serviceVariant;
    }

    public void setServiceVariant(int serviceVariant) {
        this.serviceVariant = serviceVariant;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
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

    public String getPeriodOfAnswer() {
        return periodOfAnswer;
    }

    public void setPeriodOfAnswer(String periodOfAnswer) {
        this.periodOfAnswer = periodOfAnswer;
    }
}
