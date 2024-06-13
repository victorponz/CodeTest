//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.apache.logging.log4j.Marker;
//import org.apache.logging.log4j.MarkerManager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
public class Lanzar {
   //docker run -v $(pwd)/io:/io codetest Afortunados dd

    public static void main(String[] args) throws IOException {
        Path path = Paths.get("io/" + args[1]);
        System.out.println(System.getProperty("user.dir") + "/io/" +  args[1]);
        try {
            // Create the directory
            Files.createDirectory(path);
            System.out.println("Directory created successfully!");
        } catch (IOException e) {
            // Handle the error
            System.err.println("Failed to create directory: " + e.getMessage());
        }
        Log log = new Log(args[1]);
        log.writeHeader();
        TestRunner a = new TestRunner(args[0], args[1]);
        //final Logger logger = LogManager.getLogger();
        //final Marker RESULT = MarkerManager.getMarker("RESULT");

        try {
            if (!a.compile()) {
                //logger.info(RESULT, 1); //"No compila"
                log.writeExecutionCode(1);
            }else{
                if (!a.run()) { //Ejecutar el test
                    //logger.info(RESULT, 2); //"Error en tiempo de ejecución"
                    log.writeExecutionCode(2);
                    ParseXML p = new ParseXML(args[1]);
                    p.parse();
                }else{
                    //logger.info(RESULT, 0);//OK
                    log.writeExecutionCode(0);
                    //Process XML File to extract info
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }
        log.writeFooter();
        log.close();
    }
}
