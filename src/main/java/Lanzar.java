public class Lanzar {
    public static void main(String[] args) {
        testRunner a = new testRunner(args[0], args[1]);
        try {
            if (!a.compile()) {
                System.out.println("No compila");
            }else{
                if (!a.run()) {
                    System.out.println("Error en tiempo de ejecución");
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }
    }
}
