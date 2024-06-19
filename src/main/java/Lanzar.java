import java.io.IOException;
public class Lanzar {
     //docker run -v $(pwd)/io:/io codetest Afortunados dd

    public static void main(String[] args) throws IOException {
        Log log = new Log(args[1]);
        log.writeHeader();
        TestRunner a = new TestRunner(args[0], args[1]);
        try {
            if (!a.compile()) {
                log.writeExecutionCode(1);
            }else{
                if (!a.run()) { //Ejecutar el test
                    log.writeExecutionCode(2);
                    ParseXML p = new ParseXML(args[1]);
                    p.parse();
                }else{
                    log.writeExecutionCode(0);
                }
            }
            log.writeFooter();
            log.close();
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
            System.exit(1);
        }

    }
}
