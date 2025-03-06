package ejrciciosEntregables;

import javax.swing.*;

public class practicaMenu {

        public static void main(String[] args) {
            int codMenuPpal=0, codMenuTipico=0, codMenuCarta=0, codMenuInternacional=0;
            String usuario ="Anonimo";
            usuario =JOptionPane.showInputDialog("Ingrese el nombre del comensal");
            double cuenta = 0;
            double contadorPlatos=0;
            double contadorPlatosT=0;
            double contadorPlatosC=0;
            double contadorPlatosI=0;
            String msj ="Estimado: "+usuario+"\nResumen del Pedido: \n\n";
            do{
                String menu="""
                MENU RESTAURANTE
                1. Plato Típico
                2. Plato a la carta
                3. Plato Internacional
                4. Salir
                Por favor seleccione una opción:
                """;
                codMenuPpal=Integer.parseInt(JOptionPane.showInputDialog(menu));
                switch (codMenuPpal){
                    case 1:
                        do{
                            String menuTipico="MENU TIPICO\n\n";
                            menuTipico+="1.Frijoles\n";
                            menuTipico+="2. Sopa de verduras\n";
                            menuTipico+="3. Regresar\n\n";
                            menuTipico+="Por favor seleccione una opcion\n";
                            codMenuTipico=Integer.parseInt(JOptionPane.showInputDialog(menuTipico));

                            switch (codMenuTipico) {
                                case 1:
                                    JOptionPane.showMessageDialog(null, "Se ha solicitado un plato de frijoles, el costo es de $" + 12000);
                                    cuenta +=12000;
                                    contadorPlatosT++;
                                    msj+="Frijoles = $12000\n";
                                    break;
                                case 2:
                                    JOptionPane.showMessageDialog(null, "Se ha solciitado una Sopa de Verduras, el costo es de $" + 8000);
                                    cuenta +=8000;
                                    contadorPlatosT++;
                                    msj+="Sopa de verduras = $8000\n";
                                    break;
                                case 3:
                                    break;
                                default:
                                    JOptionPane.showMessageDialog(null, "No corresponde a " + "un codigo valido", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
                                    break;
                            }
                            codMenuTipico=Integer.parseInt(JOptionPane.showInputDialog("Ingrese 1 si desea repetir "+
                                    "el menu tipico, de lo contrario ingrese cualquier numero para regresar al menu principal"));
                            if(codMenuTipico!=1){
                                codMenuTipico=3;
                            }
                        }while(codMenuTipico!=3);
                        break;
                    case 2:
                        do{
                        String menuCarta="MENU A LA CARTA\n\n";
                        menuCarta+="1.Spaguetti a la carbonara\n";
                        menuCarta+="2. Lasagna\n";
                        menuCarta+="3. Regresar\n\n";
                        menuCarta+="Por favor seleccione una opcion\n";
                        codMenuCarta=Integer.parseInt(JOptionPane.showInputDialog(menuCarta));

                        switch (codMenuCarta) {
                            case 1:
                                JOptionPane.showMessageDialog(null, "Se ha solicitado un plato spaguetti a la carbonara, el costo es de $" + 16000);
                                cuenta +=16000;
                                contadorPlatosC++;
                                msj+="Spaguetti a la carbonara = $16000\n";
                                break;
                            case 2:
                                JOptionPane.showMessageDialog(null, "Se ha solicitado una Lasagna, el costo es de $" + 20000);
                                cuenta +=20000;
                                contadorPlatosC++;
                                msj+="lasagna = $20000\n";
                                break;
                            case 3:
                                break;
                            default:
                                JOptionPane.showMessageDialog(null, "No corresponde a " + "un codigo valido", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
                                break;
                        }
                        codMenuCarta=Integer.parseInt(JOptionPane.showInputDialog("Ingrese 1 si desea repetir "+
                                "el menu a la carta, de lo contrario ingrese cualquier numero para regresar al menu principal"));
                        if(codMenuCarta!=1){
                            codMenuCarta=3;
                        }
                    }while(codMenuCarta!=3);
                        break;
                    case 3:
                        do{
                            String menuInternacional="MENU INTERNACIONAL\n\n";
                            menuInternacional+="1. Caviar de gallina con frutos secos del oriente\n";
                            menuInternacional+="2. Paella\n";
                            menuInternacional+="3. Regresar\n\n";
                            menuInternacional+="Por favor seleccione una opcion\n";
                            codMenuInternacional=Integer.parseInt(JOptionPane.showInputDialog(menuInternacional));

                            switch (codMenuInternacional) {
                                case 1:
                                    JOptionPane.showMessageDialog(null, "Se ha solicitado un plato de caviar de gallina con frutos secos del oriente, el costo es de $" + 10000);
                                    cuenta +=10000;
                                    contadorPlatosI++;
                                    msj+="Caviar de gallina con frutos secos del oriente = $10000\n";
                                    break;
                                case 2:
                                    JOptionPane.showMessageDialog(null, "Se ha solicitado una paella, el costo es de $" + 40000);
                                    cuenta +=40000;
                                    contadorPlatosI++;
                                    msj+="Paella = $40000\n";
                                    break;
                                case 3:
                                    break;
                                default:
                                    JOptionPane.showMessageDialog(null, "No corresponde a " + "un codigo valido", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
                                    break;
                            }
                            codMenuInternacional=Integer.parseInt(JOptionPane.showInputDialog("Ingrese 1 si desea repetir "+
                                    "el menu internacional, de lo contrario ingrese cualquier numero para regresar al menu principal"));
                            if(codMenuInternacional!=1){
                                codMenuInternacional=3;
                            }
                        }while(codMenuInternacional!=3);
                        break;
                    case 4:
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "No corresponde a un "+"codigo valido","ADVERTENCIA",JOptionPane.WARNING_MESSAGE);
                        break;
                }
            }while(codMenuPpal!=4);
            contadorPlatos=contadorPlatosC+contadorPlatosI+contadorPlatosT;
            msj+="\nTotal de platos Tipicos: "+contadorPlatosT;
            msj+="\nTotal de platos a la carta: "+contadorPlatosC;
            msj+="\nTotal de platos internacionales: "+contadorPlatosI;
            msj+="\nTotal de platos pedidos: "+contadorPlatos;
            msj+="\nTotal de la cuenta: $"+cuenta;

            JOptionPane.showMessageDialog(null,msj);



        }
}
