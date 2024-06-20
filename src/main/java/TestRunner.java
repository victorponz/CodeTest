import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TestRunner {
//https://stackoverflow.com/questions/1320476/execute-another-jar-in-a-java-program
    private BufferedReader error;
    private BufferedReader op;
    private BufferedReader out;
    private int exitVal;
    private final String className;
    private final String resultsPath;
    private Results results;
    public TestRunner(String className, String resultsPath) throws IOException {
        this.className = className;
        this.resultsPath = resultsPath;
        results = new Results(this.resultsPath);
    }

    /**
     * Compila la clase this.className y su Test.
     * Si se produce algún error al compilar devuelve false
     * @return
     * @throws Exception
     */
    public boolean compile() throws Exception{

        try {

            System.out.println("Compile");
            final Runtime re = Runtime.getRuntime();
            //Primero hay que compilar el programa y luego el test
            // javac -cp junit.jar Sum.java SumTest.java
            //Siempre se compila desde raíz dep proyecto
            Process command = re.exec("javac -cp " + this.resultsPath + " " + this.resultsPath +  "/" + this.className + ".java" + " "  + this.resultsPath + "/" + this.className + "Test.java -classpath .:/usr/bin/junit.jar");
            this.error = new BufferedReader(new InputStreamReader(command.getErrorStream()));
            this.op = new BufferedReader(new InputStreamReader(command.getInputStream()));
            
            // Wait for the application to Finish
            command.waitFor();
            this.exitVal = command.exitValue();
            if (this.exitVal != 0) {
                System.out.println("Error al compilar el programa " + this.className);
                results.writeError(this.getExecutionLog());
                return false;
            }else{
                System.out.println("Compila el programa y el test" + this.className);
            }
        } catch (final IOException | InterruptedException e) {
            System.out.println("Error general " + e.getMessage());
            results.writeError(e.getMessage());
            System.exit(1);
        }
        return true;
    }

    /**
     * Ejecuta la clase this.className. Si se produce algún error en tiempo de ejecución, crea un archivo de texto
     * con el mensaje. Este archivo se almacena dentro del directorio io + this.resultsPath
     * @throws Exception
     */
    public boolean run() throws Exception {
        // Create run arguments for the

        try {
            final Runtime re = Runtime.getRuntime();
            //Primero hay que compilar el programa y el test
            // javac -cp junit.jar Sum.java SumTest.java
            //Como los archivos de test están en el directorio "io", lo hemos de incluir en el classpath (-cp)
            final Process command = re.exec("java -jar /usr/bin/junit.jar -cp "  + resultsPath + " --select-class " +  className +"Test --reports-dir " + resultsPath);
            this.error = new BufferedReader(new InputStreamReader(command.getErrorStream()));
            this.op = new BufferedReader(new InputStreamReader(command.getInputStream()));

            // Wait for the application to Finish
            command.waitFor();
            this.exitVal = command.exitValue();
            if (this.exitVal != 0) {
                // En este caso el error está en el fichero TEST-junit-vintage.xml
                results.close();
                return false;
            }

        } catch (final IOException | InterruptedException e) {
            System.out.println(e.getMessage());
            results.writeError(e.getMessage());
            results.close();
            System.exit(1);
        }
        return true;

    }

    public String getExecutionLog() {
        String error = "";
        String line;
        try {
            while((line = this.error.readLine()) != null) {
                error = error + "\n" + line;
            }
        } catch (final IOException e) {
        }
        String output = "";
        try {
            while((line = this.op.readLine()) != null) {
                output = output + "\n" + line;
            }
        } catch (final IOException e) {
        }
        try {
            this.error.close();
            this.op.close();
        } catch (final IOException e) {
        }
        return "exitVal: " + this.exitVal + ", error: " + error + ", output: " + output;
    }
}
    