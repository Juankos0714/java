package HashMap.EjercicioEnClase;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Procesos {

    public void iniciar() {
        //arrayList();
        //hashMap();
        //mapaDeNotas();
        mapaDePersonas();
    }


    private void mapaDePersonas() {
        HashMap<String, HashMap<String, ArrayList<Double>>> mapPers=new HashMap<String, HashMap<String,ArrayList<Double>>>();

        HashMap<String, ArrayList<Double>> mapEst=new HashMap<String, ArrayList<Double>>();

        ArrayList<Double> listNotas1=new ArrayList<Double>();
        ArrayList<Double> listNotas2=new ArrayList<Double>();
        ArrayList<Double> listNotas3=new ArrayList<Double>();

        listNotas1.add(3.3);listNotas1.add(2.3);listNotas1.add(5.0);
        listNotas2.add(4.3);listNotas2.add(1.3);listNotas2.add(3.0);
        listNotas3.add(1.3);listNotas3.add(3.3);listNotas3.add(2.0);

        mapEst.put("Ingles", listNotas1);
        mapEst.put("Religion", listNotas2);
        mapEst.put("Etica", listNotas3);

        System.out.println(mapEst);

        mapPers.put("111", mapEst);
        mapPers.put("222", mapEst);

        System.out.println(mapPers);

    }


    private void mapaDeNotas() {

        HashMap<String, ArrayList<Double>> mapNotas=new HashMap<String, ArrayList<Double>>();
        ArrayList<Double> listNotas1=new ArrayList<Double>();
        ArrayList<Double> listNotas2=new ArrayList<Double>();
        ArrayList<Double> listNotas3=new ArrayList<Double>();

        listNotas1.add(3.3);listNotas1.add(2.3);listNotas1.add(5.0);
        listNotas2.add(4.3);listNotas2.add(1.3);listNotas2.add(3.0);
        listNotas3.add(1.3);listNotas3.add(3.3);listNotas3.add(2.0);

        mapNotas.put("EspaÃ±ol", listNotas1);
        mapNotas.put("Ingles", listNotas2);
        mapNotas.put("Etica", listNotas3);

        System.out.println(mapNotas);
    }


    private void arrayList() {
        ArrayList<ArrayList<Integer>> miListaDeListas=new ArrayList<>();
        System.out.println(miListaDeListas);

        ArrayList<Integer> miListaEnteros=new ArrayList<Integer>();
        miListaEnteros.add(3); miListaEnteros.add(9); miListaEnteros.add(23);
        miListaEnteros.add(32); miListaEnteros.add(36); miListaEnteros.add(63);
        System.out.println(miListaEnteros);
        miListaDeListas.add(miListaEnteros);

        ArrayList<Integer> miListaEnteros2=new ArrayList<>();
        miListaEnteros2.add(33); miListaEnteros2.add(19); miListaEnteros2.add(23);
        System.out.println(miListaEnteros2);
        miListaDeListas.add(miListaEnteros2);

        ArrayList<Integer> miListaEnteros3=new ArrayList<Integer>();
        miListaEnteros3.add(33); miListaEnteros3.add(19); miListaEnteros3.add(23);
        miListaEnteros3.add(63);
        System.out.println(miListaEnteros3);
        miListaDeListas.add(miListaEnteros3);

        miListaDeListas.add(new ArrayList<Integer>());

        miListaDeListas.get(3).add(7);

        ArrayList<Integer> list=miListaDeListas.get(3);
        list.add(9);

        System.out.println(miListaDeListas);
        System.out.println();

        for (int i = 0; i < miListaDeListas.size(); i++) {
            System.out.println(miListaDeListas.get(i)+", Tam: "+miListaDeListas.get(i).size());
        }


        System.out.println("++++++++++++");
        ArrayList<Integer> listTemporal;

        for (int i = 0; i < miListaDeListas.size(); i++) {
            listTemporal=miListaDeListas.get(i);
            System.out.println(listTemporal+", Tam: "+listTemporal.size());
        }

    }



    private void hashMap() {
        HashMap<String, String> mapNombres=new HashMap<String, String>();
        mapNombres.put("111", "Cristian");
        mapNombres.put("222", "Juan");
        mapNombres.put("333", "Carlos");
        mapNombres.put("444", null);

        System.out.println(mapNombres);

        System.out.println(mapNombres.get("999"));

        String cedula=JOptionPane.showInputDialog("Ingrese cedula");
        String nombre=JOptionPane.showInputDialog("Ingrese nombre");

        if (mapNombres.get(cedula)==null) {
            mapNombres.put(cedula, nombre);
            System.out.println("Persona registrada");
        }else {
            System.out.println("Ya existe la persona: "+mapNombres.get(cedula));
        }

        System.out.println(mapNombres);

    }

}

