/**
 * Программа считывает данные из файла input.txt, обрабатывает их, выводит результат в консоль и в файл output.txt;
 * Для выполнения этой задачи я создал несколько классов и интерфейсов;
 * В классе ExecuteApp расположен метод main()
 * Интерфейс AbstractLine позволяет пометить класс как линию(строку);
 * Интерфейс AbstractLine создан для возврата унифицированного обьекта в методе parseLineToObject() в классе AnalyzerIml;
 * Класс WaitingTimeLine служит каркасом для создания обьекта из строки временной шкалы;
 * Класс QueryLine служит каркасом для создания обьекта из строки запроса соответсвенно;
 * Интерфейс Analyzer создан для возможности расширения и создания анализаторов для других типов строк нежели в задаче;
 * Класс AnalyzerImp является реализацией интерфейса Analyzer, в нем сосредоточена основная логика;
 * Метод parseLineToObject() сначал проверяет корректно ли указана строка и в случае успешной проверки создает новый
 * обьект, или же выводит в консоль сообщение о проблеме и возвращает нулевой обьект(null);
 * Метод parseLineToObject() можно упростить(улучшить), например, вынести все проверки в отдельные методы.
 *
 * Для тестирования я создал 2 файла (input.txt и output.txt), предварительно заполнив input.txt данными, чтоб
 * продемонстрировать как работает программа и что она выводит в консоль;
 * В input.txt в первой строке указано неправильрое количество строк - эта ошибка обрабатываетс я в методе objectsToLists;
 * Далее следует 10 корректных строк, которые успешно обрабатываются;
 * После них следует 7 некорректных строк с разными видами ошибок;
 *
 * В этой задаче можно еще много над чем заморочится, но время, как говорится, не резиновое - и я решил побыстрее
 * испытать свою удачу :-)
 */
import analyzer.AnalyzerImpl;

import java.io.*;

public class ExecuteApp {
    public static void main(String[] args) throws IOException {

        //create reader
        File input  = new File ("src" + File.separator + "main" +
                File.separator + "files" + File.separator + "input.txt");
        String absolutePathInput = input.getAbsolutePath();
        Reader reader = new FileReader(absolutePathInput);

        //create writer
        File output  = new File ("src" + File.separator + "main" +
                File.separator + "files" + File.separator + "output.txt");
        String absolutePathOutput = output.getAbsolutePath();
        Writer writer = new FileWriter(absolutePathOutput);

        //analyze input data
        new AnalyzerImpl().analyze(reader, writer);

    }
}
