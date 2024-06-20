import java.io.*;

public class Results {
    FileWriter fileWriter;
   public Results(String resultsPath) throws IOException {
        fileWriter = new FileWriter(resultsPath + "/results.xml", true);
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
    public void close() throws IOException {
        fileWriter.close();
    }
}
