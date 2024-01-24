import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Encuesta24Horas {
    String persona;
    List<DiaAlimentos> alimentosPorDia;

    public Encuesta24Horas(String persona) {
        this.persona = persona;
        this.alimentosPorDia = new ArrayList<>();
    }

    public void agregarAlimento(int dia, Alimento alimento) {
        while (alimentosPorDia.size() <= dia) {
            alimentosPorDia.add(new DiaAlimentos());
        }
        alimentosPorDia.get(dia).agregarAlimento(alimento);
    }

    public void realizarEncuesta(Scanner scanner) {
        System.out.println("Paciente: " + persona);

        int dia;
        do {
            System.out.print("Seleccione día (6 para salir): ");
            dia = scanner.nextInt();

            if (dia != 6) {
                realizarEncuestaDia(dia, scanner);
            }
        } while (dia != 6);
    }

    private void realizarEncuestaDia(int dia, Scanner scanner) {
        int ingesta;
        do {
            System.out.print("Seleccione ingesta (1: Desayuno, 2: Media mañana, 3: Almuerzo, 4: Merienda, 5: Cena, 7: Menú anterior): ");
            ingesta = scanner.nextInt();

            if (ingesta >= 1 && ingesta <= 5) {
                realizarEncuestaComida(dia, ingesta, scanner);
            }

        } while (ingesta != 7);
    }

    private void realizarEncuestaComida(int dia, int ingesta, Scanner scanner) {
        DiaAlimentos diaAlimentos = alimentosPorDia.size() > dia ? alimentosPorDia.get(dia) : new DiaAlimentos();

        int opcion;
        do {
            System.out.print("Ingrese un alimento para la ingesta " + ingesta + " del día " + dia + " (7 para terminar / 6 para listar alimentos ingresados): ");
            scanner.nextLine();  // Limpiar el buffer
            String nombreAlimento = scanner.nextLine();

            if ("6".equals(nombreAlimento)) {
                diaAlimentos.listarAlimentos();
            } else if (!"7".equals(nombreAlimento)) {
                System.out.print("Ingrese la cantidad: ");
                double cantidad = scanner.nextDouble();
                System.out.print("Ingrese la unidad: ");
                scanner.nextLine();  // Limpiar el buffer
                String unidad = scanner.nextLine();

                Alimento alimento = new Alimento(nombreAlimento, cantidad, unidad);
                diaAlimentos.agregarAlimento(alimento);
            }

        } while (!"7".equals(scanner.nextLine()));
    }
}