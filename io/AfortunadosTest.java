
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class AfortunadosTest {

     @Test
    public void testa() {
        assertEquals(Afortunados.calcular(3), "2 ");
        assertEquals(Afortunados.calcular(10), "4 6 10 ");
        assertEquals(Afortunados.calcular(30), "10 12 18 22 30 ");
        assertEquals(Afortunados.calcular(100), "30 34 42 48 58 60 78 82 ");
        //assertEquals("Error", Afortunados.calcular(20), "30 34 42 48 58 60 78 ");
        
    }
    @Test
    public void test2() {
        //assertEquals("No ha funcionado cuando son distintos", Double.parseDouble(Afortunados.calcular(Integer.MAX_VALUE)) , 3f , 0f);
        //assertEquals("No ha funcionado cuando son distintos", Double.parseDouble(Afortunados.calcular(3)) , 3f , 0f);
    }
}

