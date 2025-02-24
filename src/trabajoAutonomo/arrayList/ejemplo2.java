package trabajoAutonomo.arrayList;
import java.util.ArrayList;
public class ejemplo2 {
    public static void main(String[] args) {
        ArrayList<Integer> lista1 = new ArrayList<>();
        ArrayList<Integer> lista2 = new ArrayList<>();
        ArrayList<Integer> resultado = new ArrayList<>();

        lista1.add(1); lista1.add(2); lista1.add(3); lista1.add(4); lista1.add(5);
        lista2.add(5); lista2.add(4); lista2.add(3); lista2.add(2); lista2.add(1);

        for (int i = 0; i < lista1.size(); i++) {
            resultado.add(lista1.get(i) + lista2.get(i));
        }

        System.out.println("Suma de los ArrayLists: " + resultado);
    }
}

