import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class testRunner {
//https://stackoverflow.com/questions/1320476/execute-another-jar-in-a-java-program
    private BufferedReader error;
    private BufferedReader op;
    private BufferedReader out;
    private int exitVal;
    private final String className;
    private final String resultsPath;

    public testRunner(String className, String resultsPath){
        this.className = className;
        this.resultsPath = resultsPath;
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
            //Primero hay que compilar el programa y el test 
            // javac -cp junit.jar Sum.java SumTest.java
            //Siempre se compila desde raíz dep proyecto
            Process command = re.exec("javac  "+ System.getProperty("user.dir") + "/io/" + this.className + "Test.java -classpath .:/usr/bin/junit.jar");
            this.error = new BufferedReader(new InputStreamReader(command.getErrorStream()));
            this.op = new BufferedReader(new InputStreamReader(command.getInputStream()));
            
            // Wait for the application to Finish
            command.waitFor();
            this.exitVal = command.exitValue();
            if (this.exitVal != 0) {
                //throw new IOException("Failed to execute jar, " + this.getExecutionLog());
                System.out.println("Failed to compile, " + this.getExecutionLog());
                
            }else{
                System.out.println("COMPILA EL TEST");
               
            }
            command = re.exec("javac -cp /usr/bin/junit.jar "+ System.getProperty("user.dir") + "/io/" + this.className + ".java");

            this.error = new BufferedReader(new InputStreamReader(command.getErrorStream()));
            this.op = new BufferedReader(new InputStreamReader(command.getInputStream()));
            
            // Wait for the application to Finish
            command.waitFor();
            this.exitVal = command.exitValue();
            if (this.exitVal != 0) {
                System.out.println("Failed to compile, " + this.getExecutionLog());
                return false;
            }else{
                System.out.println("COMPILA EL PROGRAMA");
                return true;
            }

        } catch (final IOException | InterruptedException e) {
            throw new Exception(e);
        }
    }

    /**
     * Ejecuta la clase this.className. Si se produce algún error en tiempo de ejecución, crea un archivo de texto
     * con el mensaje. Este archivo se almacena dentro del directorio io + this.resultsPath
     * @throws Exception
     */
    public boolean run() throws Exception {
        // Create run arguments for the

        // final List<String> actualArgs = new ArrayList<String>();
        // actualArgs.add(0, "java");
        // actualArgs.add(1, "-jar");
        // //actualArgs.add(2, "/usr/bin/junit.jar  -cp . --reports-dir /home/victorponz/Documentos/repos/JavaCorrector/salida --select-class AfortunadosTest");
        // actualArgs.add(2, "/usr/bin/junit.jar  -cp . --reports-dir ./salida --select-class AfortunadosTest");
        // //actualArgs.addAll(args);
        try {
            final Runtime re = Runtime.getRuntime();
            //Primero hay que compilar el programa y el test
            // javac -cp junit.jar Sum.java SumTest.java
            //Como los archivos de test están en el directorio "io", lo hemos de incluir en el classpath (-cp)
            final Process command = re.exec("java -jar /usr/bin/junit.jar -cp ./io --select-class " + className +"Test --reports-dir io/" + resultsPath);
            this.error = new BufferedReader(new InputStreamReader(command.getErrorStream()));
            this.op = new BufferedReader(new InputStreamReader(command.getInputStream()));

            // Wait for the application to Finish
            command.waitFor();
            this.exitVal = command.exitValue();
            if (this.exitVal != 0) {
                //System.out.println(this.getExecutionLog());
                return false;
                //throw new IOException("Failed to execute jar, " + this.getExecutionLog());
            }

        } catch (final IOException | InterruptedException e) {
            throw new Exception(e);
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
    