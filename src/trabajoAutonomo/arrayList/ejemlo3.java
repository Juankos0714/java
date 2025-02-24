package trabajoAutonomo.arrayList;
import java.util.ArrayList;
import java.util.Scanner;
public class ejemlo3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> lista1 = new ArrayList<>();
        ArrayList<Integer> lista2 = new ArrayList<>();
        ArrayList<Integer> resultado = new ArrayList<>();

        System.out.print("Ingrese el tamaño de las listas: ");
        int n = sc.nextInt();

        System.out.println("Ingrese los valores de la primera lista:");
        for (int i = 0; i < n; i++) {
            System.out.print("Elemento " + (i + 1) + ": ");
            lista1.add(sc.nextInt());
        }

        System.out.println("Ingrese los valores de la segunda lista:");
        for (int i = 0; i < n; i++) {
            System.out.print("Elemento " + (i + 1) + ": ");
            lista2.add(sc.nextInt());
        }

        for (int i = 0; i < n; i++) {
            resultado.add(lista1.get(i) + lista2.get(i));
        }

        System.out.println("Resultado de la suma de los ArrayLists: " + resultado);

        sc.close();
    }
}

