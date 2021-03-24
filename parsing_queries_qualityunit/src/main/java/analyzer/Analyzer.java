package analyzer;

import lines.QueryLine;
import lines.WaitingTimeLine;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.List;

public interface Analyzer {
    public void analyze(Reader reader, Writer writer) throws IOException;
}
