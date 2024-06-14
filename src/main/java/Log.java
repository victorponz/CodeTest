import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Log {
    FileWriter fileWriter;

    public Log(String resultsPath) throws IOException {
        fileWriter = new FileWriter("io/" + resultsPath + "/results.xml", true);
    }
    public void writeHeader() throws IOException {
        fileWriter.write("<results>");
        fileWriter.write(System.lineSeparator());
        fileWriter.flush();
    }
    public void writeFooter() throws IOException {
        fileWriter.write("</results>");
        fileWriter.flush();
    }
    public void writeExecutionCode(int code) throws IOException {
        fileWriter.write("<resultcode>" + code + "</resultcode>");
        fileWriter.flush();
        fileWriter.write(System.lineSeparator()); // Append a newline character
        fileWriter.flush();
    }
    public void writeError(String error) throws IOException {
        fileWriter.write("<error><![CDATA[" + error + "]]></error>");
        fileWriter.flush();
        fileWriter.write(System.lineSeparator()); // Append a newline character
        fileWriter.flush();

    }
    public void write(String xml) throws IOException {
        fileWriter.write(xml);
        fileWriter.write(System.lineSeparator());
        fileWriter.flush();
    }
    public void close() throws IOException {
        fileWriter.close();
    }
    public void writeTestFailure(String msg) throws IOException {
        fileWriter.write("<failure><![CDATA[" + msg + "]]></failure>");
        fileWriter.flush();
        fileWriter.write(System.lineSeparator()); // Append a newline character
        fileWriter.flush();
    }
    public void writeTesError(String msg) throws IOException {
        fileWriter.write("<error><![CDATA[" + msg + "]]></error>");
        fileWriter.flush();
        fileWriter.write(System.lineSeparator()); // Append a newline character
        fileWriter.flush();
    }
}
