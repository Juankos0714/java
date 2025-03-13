package ejerciciosEstructurasDeControl;

import javax.swing.*;

public class MetodosEdad {
    double yearBorn, yearNow, monthBorn, monthNow, dayBorn, dayNow, years, months, days;

    public void loop() {
        int close;
        do {
            request();
            ageCalculation();
            close = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero 0 para cerrar"));

        } while (close != 0);
    }

    public void request() {

        yearBorn = yearValidation("Ingrese su año de nacimiento");
        monthBorn = monthValidation("Ingrese su mes de nacimiento");
        dayBorn = dayValidation("Ingrese su dia de nacimiento");
        yearNow = yearValidation("Ingrese el año actual");
        monthNow = monthValidation("Ingrese el mes actual");
        dayNow = dayValidation("Ingrese el dia actual");
        years = yearNow - yearBorn;
        months = monthNow - monthBorn;
        days = dayNow - dayBorn;
    }

    public double monthValidation(String msj) {
        double monthBorn = 0;
        do {
            monthBorn = negativeValidation(msj);
            if (monthBorn == 0 || monthBorn > 12) {
                JOptionPane.showMessageDialog(null, "Este valor no es valido",
                        "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
            }
        } while (monthBorn == 0 || monthBorn > 12);

        return monthBorn;
    }

    public double dayValidation(String msj) {

        double dayBorn = 0;

        do {
            dayBorn = negativeValidation(msj);
            if (dayBorn == 0 || dayBorn > 31) {
                JOptionPane.showMessageDialog(null, "Este valor no es valido",
                        "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
            }
        } while (dayBorn == 0 || dayBorn > 31);

        return dayBorn;
    }

    public double yearValidation(String msj) {
        double yearBorn = 0;
        double yearNow = 2025;

        do {
            yearBorn = negativeValidation(msj);
            if (yearBorn == 0 || yearBorn > yearNow) {
                JOptionPane.showMessageDialog(null, "Este valor no es valido",
                        "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
            }
        } while (yearBorn == 0 || yearBorn > yearNow);

        return yearBorn;
    }

    public double negativeValidation(String message) {
        double value = 0;
        boolean validInput = false;

        do {
            String input = JOptionPane.showInputDialog(message);
            value = Double.parseDouble(input);

            if (value < 0) {
                JOptionPane.showMessageDialog(null, "No se permiten valores negativos",
                        "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
            } else {
                validInput = true;
            }
        } while (!validInput);

        return value;
    }

    public void ageCalculation() {
        if (years > 18) {
            JOptionPane.showMessageDialog(null, "Es mayor de edad");
        } else if (years == 18 && months > 0) {
            JOptionPane.showMessageDialog(null, "Es mayor de edad");
        } else if (years == 18 && months == 0 && days >= 0) {
            JOptionPane.showMessageDialog(null, "Es mayor de edad");
        } else {
            JOptionPane.showMessageDialog(null, "Es menor de edad");

        }


    }
}

