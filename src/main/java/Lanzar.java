import java.io.IOException;
public class Lanzar {
     //docker run -v $(pwd)/io:/io codetest Afortunados dd

    public static void main(String[] args) throws IOException {
        Results results = new Results(args[1]);
        results.writeHeader();
        TestRunner a = new TestRunner(args[0], args[1]);
        try {
            if (!a.compile()) {
                results.writeExecutionCode(1);
            }else{
                if (!a.run()) { //Ejecutar el test
                    results.writeExecutionCode(2);
                }else{
                    results.writeExecutionCode(0);
                }
            }
            results.writeFooter();
            results.close();
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
            System.exit(1);
        }

    }
}
