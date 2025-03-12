package ejerciciosEstructurasDeControl;

import javax.swing.*;

public class EjercicioEdad {
    public static void main (String[] args){
    double yearBorn,yearNow,monthBorn, monthNow, dayBorn, dayNow, years, months, days;
        yearBorn = Double.parseDouble(JOptionPane.showInputDialog("Ingrese su año de nacimiento"));
        monthBorn = Double.parseDouble(JOptionPane.showInputDialog("Ingrese su mes de nacimiento"));
        dayBorn = Double.parseDouble(JOptionPane.showInputDialog("Ingrese su dia de nacimiento"));
        yearNow = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el año actual"));
        monthNow = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el mes actual"));
        dayNow = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el dia actual"));
        years = yearNow-yearBorn;
        months = monthNow-monthBorn;
        days = dayNow-dayBorn;
        if(years>18){
            JOptionPane.showMessageDialog(null,"Es mayor de edad");
        }else if(years==18 && months >0){
            JOptionPane.showMessageDialog(null,"Es mayor de edad");
        }else if(years==18 && months == 0 && days>=0){
            JOptionPane.showMessageDialog(null,"Es mayor de edad");
        }else{
            JOptionPane.showMessageDialog(null, "Es menor de edad");
        }
        MetodosEdad.validationYear();

}
    public static double validationYear(){
        double yearBorn = Double.parseDouble(JOptionPane.showInputDialog("Ingrese su año de nacimiento"));
        double yearNow = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el año actual"));

        if(yearBorn<1950 && yearBorn<yearNow){

        }
        return yearNow;
    }
}

