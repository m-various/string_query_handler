package analyzer;

import lines.AbstractLine;
import lines.QueryLine;
import lines.WaitingTimeLine;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AnalyzerImpl implements Analyzer {

    //fields
    private List<WaitingTimeLine> waitingLines;
    private List<QueryLine> queryLines;


    //This method parses line to required fields and create new object
    public AbstractLine parseLineToObject(String line) {

        AbstractLine newLineObject = null;
        line = line.trim();

        //check type of input line
        String[] array = line.split(" ");
        String typeOfLine = array[0];
        if (!typeOfLine.equals(WaitingTimeLine.identifier) && !typeOfLine.equals(QueryLine.identifier)) {
            System.out.println("Unknown type of line");
            return newLineObject;
        }

        //check service in input line
        String[] serviceArray = array[1].split("\\.");
        String service = serviceArray[0];
        if (!service.equals("*")) {
            if (Integer.parseInt(service) > 10) {
                System.out.println("Unknown service");
                return newLineObject;
            }
            if (serviceArray.length == 2) {
                String typeOfService = serviceArray[1];
                if (Integer.parseInt(typeOfService) > 3) {
                    System.out.println("Unknown type of service");
                    return newLineObject;
                }
            }
        }

        //check question in input line
        String[] questionArray = array[2].split("\\.");
        String question = questionArray[0];
        if (!question.equals("*")) {
            if (Integer.parseInt(question) > 10) {
                System.out.println("Unknown question");
                return newLineObject;
            }
            if (questionArray.length == 2) {
                String categoryOfQuestion = questionArray[1];
                if (Integer.parseInt(categoryOfQuestion) > 20) {
                    System.out.println("Unknown category of question");
                    return newLineObject;
                }
            }
            if (questionArray.length == 3) {
                String categoryOfQuestion = questionArray[1];
                if (Integer.parseInt(categoryOfQuestion) > 20) {
                    System.out.println("Unknown category of question");
                    return newLineObject;
                }
                String subCategoryOfQuestion = questionArray[2];
                if (Integer.parseInt(subCategoryOfQuestion) > 5) {
                    System.out.println("Unknown subCategory of question");
                    return newLineObject;
                }
            }
        }

        //check type of answer
        String typeOfAnswer = array[3];
        if (!typeOfAnswer.equals("N") && !typeOfAnswer.equals("P")) {
            System.out.println("Unknown type of answer");
            return newLineObject;
        }

        //create new object

        //creating waiting timeline
        if (array[0].equals(WaitingTimeLine.identifier)) {

            WaitingTimeLine wtl = new WaitingTimeLine();

            if (serviceArray.length == 2) {
                wtl.setService(Integer.parseInt(serviceArray[0]));
                wtl.setServiceVariant(Integer.parseInt(serviceArray[1]));
            } else wtl.setService(Integer.parseInt(array[1]));

            if (questionArray.length == 3) {
                wtl.setQuestionType(Integer.parseInt(questionArray[0]));
                wtl.setCategoryOfQuestion(Integer.parseInt(questionArray[1]));
                wtl.setSubCategoryOfQuestion(Integer.parseInt(questionArray[2]));
            } else if (questionArray.length == 2) {
                wtl.setQuestionType(Integer.parseInt(questionArray[0]));
                wtl.setCategoryOfQuestion(Integer.parseInt(questionArray[1]));
            } else wtl.setQuestionType(Integer.parseInt(array[2]));

            wtl.setTypeOfAnswer(array[3]);
            wtl.setDateOfAnswer(array[4]);
            wtl.setWaitingTime(Integer.parseInt(array[5]));

            newLineObject = wtl;

            //or creating query line
        } else {

            QueryLine ql = new QueryLine();

            if (serviceArray.length == 2) {
                ql.setService(serviceArray[0]);
                ql.setServiceVariant(Integer.parseInt(serviceArray[1]));
            } else ql.setService(array[1]);

            if (questionArray.length == 3) {
                ql.setQuestionType(questionArray[0]);
                ql.setCategoryOfQuestion(Integer.parseInt(questionArray[1]));
                ql.setSubCategoryOfQuestion(Integer.parseInt(questionArray[2]));
            } else if (questionArray.length == 2) {
                ql.setQuestionType(questionArray[0]);
                ql.setCategoryOfQuestion(Integer.parseInt(questionArray[1]));
            } else ql.setQuestionType(array[2]);

            ql.setTypeOfAnswer(array[3]);
            ql.setPeriodOfAnswer(array[4]);

            newLineObject = ql;
        }


        return newLineObject;
    }


    // This method reads incoming data and converts it to lists of matching objects
    public void objectsToLists(Reader reader) throws IOException {

        waitingLines = new ArrayList<>();
        queryLines = new ArrayList<>();

        BufferedReader bufferedReader = new BufferedReader(reader);
        List<String> lineList = new ArrayList<String>();
        String s;

        while ((s = bufferedReader.readLine()) != null) {
            lineList.add(s);
        }

        bufferedReader.close();
        reader.close();

        if (Integer.parseInt(lineList.get(0)) != (lineList.size() - 1)) {
            System.out.println("Something is wrong with line counter!");
            System.out.println("Specified number of lines: " + lineList.get(0));
            System.out.println("Real number of lines: " + (lineList.size() - 1));
            System.out.println();
        }

        lineList.remove(0);

        for (String string : lineList) {
            AbstractLine line = parseLineToObject(string);
            if (line != null) {
                if (line instanceof WaitingTimeLine) {
                    WaitingTimeLine wtl = (WaitingTimeLine) line;
                    wtl.setId(lineList.indexOf(string));
                    waitingLines.add(wtl);
                } else {
                    QueryLine ql = (QueryLine) line;
                    int id = lineList.indexOf(string);
                    ql.setId(id);
                    queryLines.add(ql);
                }
            } else {
                System.out.println("This objectLine == null, something is wrong with input data line");
                System.out.println();
            }
        }
    }


    // This method parses queries, writes the results to a file and outputs to the console
    public void analyze(Reader reader, Writer writer) throws IOException {

        objectsToLists(reader);
        BufferedWriter bufferedWriter = new BufferedWriter(writer);

        int sum = 0;
        int count = 0;

        for (QueryLine ql : this.queryLines) {
            for (WaitingTimeLine wtl : this.waitingLines) {
                if (
                        checkId(wtl, ql)
                        && compareService(wtl,ql)
                        && compareQuestion(wtl,ql)
                        && compareAnswer(wtl, ql)
                        && checkDate(wtl,ql)) {

                    sum += wtl.getWaitingTime();
                    count++;
                }
            }
            if (count != 0) {
                int result = Math.round((float) sum/count);
                bufferedWriter.write("Query id " + ql.getId() + " Result : " + String.valueOf(result) + "\n");
                bufferedWriter.flush();
                System.out.println("Query id " + ql.getId() + " Result : " + String.valueOf(result));
            } else {
                bufferedWriter.write("Query id" + ql.getId() + " Result : " + "-" + "\n");
                bufferedWriter.flush();
                System.out.println("Query id " + ql.getId() + " Result : " + "-");
            }

            sum = 0;
            count = 0;
        }


        writer.close();
    }


    // This method compares services in timeline and query
    public boolean compareService(WaitingTimeLine wtl, QueryLine ql) {
        if (ql.getService().equals("*"))
            return true;

        if (Integer.parseInt(ql.getService()) == wtl.getService() && ql.getServiceVariant() == wtl.getServiceVariant())
            return true;

        if (Integer.parseInt(ql.getService()) == wtl.getService() && ql.getServiceVariant() == 0)
            return true;

        return false;
    }


    // This method compares questions in timeline and query
    public boolean compareQuestion(WaitingTimeLine wtl, QueryLine ql) {
        if (ql.getQuestionType().equals("*"))
            return true;

        if (Integer.parseInt(ql.getQuestionType()) == wtl.getQuestionType()
                && ql.getCategoryOfQuestion() == wtl.getCategoryOfQuestion()
                && ql.getSubCategoryOfQuestion() == wtl.getSubCategoryOfQuestion())
            return true;

        if (Integer.parseInt(ql.getQuestionType()) == wtl.getQuestionType()
                && ql.getCategoryOfQuestion() == wtl.getCategoryOfQuestion()
                && ql.getSubCategoryOfQuestion() == 0)
            return true;

        if (Integer.parseInt(ql.getQuestionType()) == wtl.getQuestionType()
                && ql.getCategoryOfQuestion() == 0
                && ql.getSubCategoryOfQuestion() == 0)
            return true;

        return false;
    }


    // This method compares type of answer in timeline and query
    public boolean compareAnswer(WaitingTimeLine wtl, QueryLine ql) {
        if (ql.getTypeOfAnswer().equals(wtl.getTypeOfAnswer()))
            return true;

        return false;
    }


    // This method compares date in timeline and query
    public boolean checkDate(WaitingTimeLine wtl, QueryLine ql) {

        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);
        String[] dates = ql.getPeriodOfAnswer().split("-");

        if (dates.length == 2) {

            try {
                Date wtlDate = formatter.parse(wtl.getDateOfAnswer());
                Date qlDateFrom = formatter.parse(dates[0]);
                Date qlDateTo = formatter.parse(dates[1]);
                if (wtlDate.getTime() >= qlDateFrom.getTime() && wtlDate.getTime() <= qlDateTo.getTime())
                    return true;
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        try {
            Date wtlDate = formatter.parse(wtl.getDateOfAnswer());
            Date qlDate = formatter.parse(ql.getPeriodOfAnswer());
            if (wtlDate.getTime() == qlDate.getTime())
                return true;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return false;
    }


    // This method checks valid id for query
    public boolean checkId(WaitingTimeLine wtl, QueryLine ql) {
        if (ql.getId() > wtl.getId())
            return true;

        return false;
    }
}