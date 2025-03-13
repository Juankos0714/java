import java.util.ArrayList;
import java.util.List;

public class TorreDeHanoi {
    public static void main(String[] args) {
        int n = 3;
        List<Integer> torreOrigen = new ArrayList<>();
        List<Integer> torreAuxiliar = new ArrayList<>();
        List<Integer> torreDestino = new ArrayList<>();


        for (int i = n; i >= 1; i--) {
            torreOrigen.add(i);
        }

        System.out.println("Estado inicial:");
        imprimirTorres(torreOrigen, torreAuxiliar, torreDestino);

        moverDiscos(n, torreOrigen, torreDestino, torreAuxiliar);

        System.out.println("Estado final:");
        imprimirTorres(torreOrigen, torreAuxiliar, torreDestino);
    }

    public static void moverDiscos(int n, List<Integer> origen, List<Integer> destino, List<Integer> auxiliar) {
        if (n > 0) {
            moverDiscos(n - 1, origen, auxiliar, destino);

            int disco = origen.remove(origen.size() - 1);
            destino.add(disco);
            System.out.println("\nMoviendo disco " + disco);
            imprimirTorres(origen, auxiliar, destino);

                       moverDiscos(n - 1, auxiliar, destino, origen);
        }
    }


     public static void imprimirTorres(List<Integer> origen, List<Integer> auxiliar, List<Integer> destino) {
        System.out.println("Torre Origen: " + origen);
        System.out.println("Torre Auxiliar: " + auxiliar);
        System.out.println("Torre Destino: " + destino);
        System.out.println("---------------------------");
    }
}

