package trabajoAutonomo.estructurasDeDatos;

public class ejemplo2 {
    public static void main(String[] args) {
        double[][] notas = {
                {3.5, 4.0, 4.2},
                {4.5, 3.8, 4.0},
                {3.2, 3.5, 3.7}
        };

        for (int i = 0; i < notas.length; i++) {
            double suma = 0;
            for (int j = 0; j < notas[i].length; j++) {
                suma += notas[i][j];
            }
            double promedio = suma / notas[i].length;
            System.out.println("Promedio del estudiante " + (i + 1) + ": " + promedio);
        }
    }
}
