package POO.EjercicioModeloDeDatos.logica;

public class Procesos {
        double por=0;
        public void calcularSueldo(Operador operador){
            double aumento=0;
            if(operador.getSueldo()<500&&operador.getAntiguedad()>=10){
                por=0.2;
                operador.setAumento(por*100);
                aumento= operador.getSueldo()+(operador.getSueldo()*por);
                operador.setSueldoNuevo(aumento);
            }
            ;
        }
    }
