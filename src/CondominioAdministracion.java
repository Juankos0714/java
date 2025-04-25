import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class CondominioAdministracion {

    private static final int COSTO_PISCINA_POR_ADULTO = 2000;
    private static final int COSTO_JUEGOS = 5000;
    private static final int COSTO_ZONAS_SOCIALES = 10000;
    private static final int COSTO_ASEO = 15000;
    private static final double PORCENTAJE_DESCUENTO = 0.20; // 20% de descuento
    private static final int COSTO_FIJO = 50000;

    public static void main(String[] args) {

        JOptionPane.showMessageDialog(null,
                "Bienvenido al Sistema de Administración del Condominio La 8 Vecindad",
                "Sistema de Administración",
                JOptionPane.INFORMATION_MESSAGE);

        List<Apartamento> apartamentos = crearDatosIniciales();

        boolean salir = false;

        while (!salir) {
            String[] opciones = {"Calcular Administración", "Mostrar Tabla de Datos", "Salir"};

            int opcion = JOptionPane.showOptionDialog(
                    null,
                    "Seleccione una opción:",
                    "Menú Principal",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    opciones,
                    opciones[0]
            );

            switch (opcion) {
                case 0:
                    calcularCostos(apartamentos);
                    JOptionPane.showMessageDialog(null,
                            "Se han calculado los costos de administración correctamente.",
                            "Cálculo Exitoso",
                            JOptionPane.INFORMATION_MESSAGE);
                    break;

                case 1:
                    mostrarResultados(apartamentos);
                    break;

                case 2:
                case -1:
                    salir = true;
                    JOptionPane.showMessageDialog(null,
                            "¡Gracias por utilizar el Sistema de Administración!",
                            "Hasta Pronto",
                            JOptionPane.INFORMATION_MESSAGE);
                    break;
            }
        }
    }

    private static List<Apartamento> crearDatosIniciales() {
        List<Apartamento> apartamentos = new ArrayList<>();

        String[] opciones = {"Usar datos predefinidos", "Ingresar datos manualmente"};

        int opcion = JOptionPane.showOptionDialog(
                null,
                "¿Cómo desea crear los datos?",
                "Creación de Datos",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0]
        );

        if (opcion == 0 || opcion == -1) {
            apartamentos.add(new Apartamento("102", "María", "Ana", 2, 2));
            apartamentos.add(new Apartamento("303", "José", "José", 3, 2));
            apartamentos.add(new Apartamento("202", "Jesús", "Beto", 1, 0));
            apartamentos.add(new Apartamento("203", "David", "Carlos", 4, 0));
            apartamentos.add(new Apartamento("302", "Daniel", "Daniel", 3, 0));
            apartamentos.add(new Apartamento("103", "Moisés", "Federico", 1, 1));
            apartamentos.add(new Apartamento("401", "Eustaquio", "Zulia", 1, 2));
            apartamentos.add(new Apartamento("402", "Sacarías", "Sacarías", 2, 1));
            apartamentos.add(new Apartamento("403", "Mateo", "", 0, 0)); // Apartamento desocupado
            apartamentos.add(new Apartamento("501", "Marcos", "Gonzalo", 2, 1));
            apartamentos.add(new Apartamento("502", "Jesús", "", 0, 0)); // Apartamento desocupado

            JOptionPane.showMessageDialog(null,
                    "Se han cargado los datos predefinidos correctamente.",
                    "Datos Cargados",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            boolean continuar = true;

            while (continuar) {
                try {

                    String numero = JOptionPane.showInputDialog(null,
                            "Ingrese el número del apartamento:",
                            "Datos del Apartamento",
                            JOptionPane.QUESTION_MESSAGE);


                    if (numero == null) {
                        break;
                    }

                    String dueno = JOptionPane.showInputDialog(null,
                            "Ingrese el nombre del dueño:",
                            "Datos del Apartamento",
                            JOptionPane.QUESTION_MESSAGE);

                    String inquilino = JOptionPane.showInputDialog(null,
                            "Ingrese el nombre del inquilino (deje en blanco si está desocupado):",
                            "Datos del Apartamento",
                            JOptionPane.QUESTION_MESSAGE);

                    int numAdultos = 0;
                    int numNinos = 0;

                    if (inquilino != null && !inquilino.trim().isEmpty()) {
                        String strAdultos = JOptionPane.showInputDialog(null,
                                "Ingrese el número de adultos:",
                                "Datos del Apartamento",
                                JOptionPane.QUESTION_MESSAGE);
                        numAdultos = Integer.parseInt(strAdultos);

                        String strNinos = JOptionPane.showInputDialog(null,
                                "Ingrese el número de niños:",
                                "Datos del Apartamento",
                                JOptionPane.QUESTION_MESSAGE);
                        numNinos = Integer.parseInt(strNinos);
                    }

                    apartamentos.add(new Apartamento(numero, dueno, inquilino, numAdultos, numNinos));

                    int respuesta = JOptionPane.showConfirmDialog(null,
                            "¿Desea agregar otro apartamento?",
                            "Continuar",
                            JOptionPane.YES_NO_OPTION);

                    if (respuesta != JOptionPane.YES_OPTION) {
                        continuar = false;
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null,
                            "Error: Debe ingresar valores numéricos para adultos y niños.",
                            "Error de Entrada",
                            JOptionPane.ERROR_MESSAGE);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null,
                            "Error al ingresar los datos: " + e.getMessage(),
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }

        if (apartamentos.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "No se ingresaron datos. Se cargarán los datos predefinidos.",
                    "Datos Predefinidos",
                    JOptionPane.INFORMATION_MESSAGE);

            return crearDatosIniciales();
        }

        return apartamentos;
    }

    private static void calcularCostos(List<Apartamento> apartamentos) {
        StringBuilder detalle = new StringBuilder();
        detalle.append("DETALLE DE CÁLCULOS:\n\n");

        for (Apartamento apto : apartamentos) {

            boolean estaOcupado = apto.getInquilino() != null && !apto.getInquilino().isEmpty();

            detalle.append("Apartamento: ").append(apto.getNumero()).append("\n");
            detalle.append("Dueño: ").append(apto.getDueno()).append("\n");
            detalle.append("Inquilino: ").append(estaOcupado ? apto.getInquilino() : "Desocupado").append("\n");

            if (estaOcupado) {
                detalle.append("Adultos: ").append(apto.getNumAdultos()).append("\n");
                detalle.append("Niños: ").append(apto.getNumNinos()).append("\n\n");

                apto.setCostoPiscina(apto.getNumAdultos() * COSTO_PISCINA_POR_ADULTO);
                detalle.append("Costo Piscina: $").append(apto.getCostoPiscina())
                        .append(" (").append(apto.getNumAdultos()).append(" adultos x $").append(COSTO_PISCINA_POR_ADULTO).append(")\n");

                if (apto.getNumNinos() > 0) {
                    apto.setCostoJuegos(COSTO_JUEGOS);
                    detalle.append("Costo Juegos: $").append(apto.getCostoJuegos())
                            .append(" (hay ").append(apto.getNumNinos()).append(" niños)\n");
                } else {
                    apto.setCostoJuegos(0);
                    detalle.append("Costo Juegos: $0 (no hay niños)\n");
                }

                apto.setCostoZonasSociales(COSTO_ZONAS_SOCIALES);
                detalle.append("Costo Zonas Sociales: $").append(apto.getCostoZonasSociales())
                        .append(" (apartamento ocupado)\n");

                String piso = apto.getNumero().substring(0, 1);
                if (piso.equals("1") || piso.equals("2")) {
                    apto.setCostoAseo(COSTO_ASEO);
                    detalle.append("Costo Aseo: $").append(apto.getCostoAseo())
                            .append(" (piso ").append(piso).append(")\n");
                } else {
                    apto.setCostoAseo(0);
                    detalle.append("Costo Aseo: $0 (no aplica para pisos superiores al 2)\n");
                }

                int subtotal = apto.getCostoPiscina() + apto.getCostoJuegos() +
                        apto.getCostoZonasSociales() + apto.getCostoAseo();
                apto.setSubtotal(subtotal);
                detalle.append("Subtotal: $").append(subtotal).append("\n");

                if (apto.getDueno().equals(apto.getInquilino())) {
                    apto.setDescuento((int)(subtotal * PORCENTAJE_DESCUENTO));
                    detalle.append("Descuento: $").append(apto.getDescuento())
                            .append(" (").append((int)(PORCENTAJE_DESCUENTO * 100)).append("% porque el dueño vive allí)\n");
                } else {
                    apto.setDescuento(0);
                    detalle.append("Descuento: $0 (no aplica)\n");
                }

                apto.setTotalPagar(COSTO_FIJO + subtotal - apto.getDescuento());
                detalle.append("Total a Pagar: $").append(apto.getTotalPagar())
                        .append(" (Costo fijo $").append(COSTO_FIJO)
                        .append(" + Subtotal $").append(subtotal)
                        .append(" - Descuento $").append(apto.getDescuento()).append(")\n");
            } else {

                apto.setCostoPiscina(0);
                apto.setCostoJuegos(0);
                apto.setCostoZonasSociales(0);
                apto.setCostoAseo(0);
                apto.setSubtotal(0);
                apto.setDescuento(0);
                apto.setTotalPagar(COSTO_FIJO);

                detalle.append("El apartamento está desocupado.\n");
                detalle.append("Solo se cobra el costo fijo: $").append(COSTO_FIJO).append("\n");
                detalle.append("Total a Pagar: $").append(apto.getTotalPagar()).append("\n");
            }

            detalle.append("\n-----------------------------------\n\n");
        }

        int respuesta = JOptionPane.showConfirmDialog(null,
                "¿Desea ver el detalle de los cálculos?",
                "Detalle de Cálculos",
                JOptionPane.YES_NO_OPTION);

        if (respuesta == JOptionPane.YES_OPTION) {

            JOptionPane.showMessageDialog(null,
                    new JScrollPane(new javax.swing.JTextArea(detalle.toString())),
                    "Detalle de Cálculos",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private static void mostrarResultados(List<Apartamento> apartamentos) {

        DefaultTableModel modelo = new DefaultTableModel();

        modelo.addColumn("Apto");
        modelo.addColumn("Dueño");
        modelo.addColumn("Inquilino");
        modelo.addColumn("Adultos");
        modelo.addColumn("Niños");
        modelo.addColumn("Piscina");
        modelo.addColumn("Juegos");
        modelo.addColumn("Zonas Sociales");
        modelo.addColumn("Aseo");
        modelo.addColumn("Subtotal");
        modelo.addColumn("Descuento");
        modelo.addColumn("Total Pagar");

        for (Apartamento apto : apartamentos) {
            modelo.addRow(new Object[]{
                    apto.getNumero(),
                    apto.getDueno(),
                    apto.getInquilino(),
                    apto.getNumAdultos(),
                    apto.getNumNinos(),
                    apto.getCostoPiscina(),
                    apto.getCostoJuegos(),
                    apto.getCostoZonasSociales(),
                    apto.getCostoAseo(),
                    apto.getSubtotal(),
                    apto.getDescuento(),
                    apto.getTotalPagar()
            });
        }

        JTable tabla = new JTable(modelo);

        JScrollPane scrollPane = new JScrollPane(tabla);

        JOptionPane.showMessageDialog(null, scrollPane,
                "ADMINISTRACIÓN CONDOMINIO LA 8 VECINDAD",
                JOptionPane.INFORMATION_MESSAGE);
    }
}

class Apartamento {
    private String numero;
    private String dueno;
    private String inquilino;
    private int numAdultos;
    private int numNinos;
    private int costoPiscina;
    private int costoJuegos;
    private int costoZonasSociales;
    private int costoAseo;
    private int subtotal;
    private int descuento;
    private int totalPagar;

    public Apartamento(String numero, String dueno, String inquilino, int numAdultos, int numNinos) {
        this.numero = numero;
        this.dueno = dueno;
        this.inquilino = inquilino;
        this.numAdultos = numAdultos;
        this.numNinos = numNinos;
        this.costoPiscina = 0;
        this.costoJuegos = 0;
        this.costoZonasSociales = 0;
        this.costoAseo = 0;
        this.subtotal = 0;
        this.descuento = 0;
        this.totalPagar = 0;
    }

    public String getNumero() {
        return numero;
    }

    public String getDueno() {
        return dueno;
    }

    public String getInquilino() {
        return inquilino;
    }

    public int getNumAdultos() {
        return numAdultos;
    }

    public int getNumNinos() {
        return numNinos;
    }

    public int getCostoPiscina() {
        return costoPiscina;
    }

    public void setCostoPiscina(int costoPiscina) {
        this.costoPiscina = costoPiscina;
    }

    public int getCostoJuegos() {
        return costoJuegos;
    }

    public void setCostoJuegos(int costoJuegos) {
        this.costoJuegos = costoJuegos;
    }

    public int getCostoZonasSociales() {
        return costoZonasSociales;
    }

    public void setCostoZonasSociales(int costoZonasSociales) {
        this.costoZonasSociales = costoZonasSociales;
    }

    public int getCostoAseo() {
        return costoAseo;
    }

    public void setCostoAseo(int costoAseo) {
        this.costoAseo = costoAseo;
    }

    public int getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(int subtotal) {
        this.subtotal = subtotal;
    }

    public int getDescuento() {
        return descuento;
    }

    public void setDescuento(int descuento) {
        this.descuento = descuento;
    }

    public int getTotalPagar() {
        return totalPagar;
    }

    public void setTotalPagar(int totalPagar) {
        this.totalPagar = totalPagar;
    }
}