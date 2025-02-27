package trabajoAutonomo.HashMap;

import java.util.HashMap;

public class ejemplo4 {
    public static void main(String[] args){
        HashMap<String,Integer> empIds = new HashMap<>();
        empIds.put("Juan",1234);
        empIds.put("carlos",1235);
        empIds.put("manuel",1236789);

        System.out.println(empIds);//recupera todos
        System.out.println(empIds.get("carlos"));//busca y recupera
        System.out.println(empIds.containsKey("manuel"));// busca y devuelve true o false dependiendo del key
        System.out.println(empIds.containsValue(1234));//busca y devuelve true o false dependiendo del value
        empIds.put("jhon",4532);//agrega o actualiza
        System.out.println(empIds);
        empIds.replace("Juan",3920);//actualiza
        System.out.println(empIds);
        empIds.putIfAbsent("esteban",222);//agrega solo si no existe antes
        System.out.println(empIds);
        empIds.remove("esteban");
        System.out.println(empIds);
    }
}
