public class ConcurrenciaBasica {

    public static void main(String[] args) {
        Thread hiloA = new Thread(() -> ejecutarTarea("A", 1000), "Hilo-A");
        Thread hiloB = new Thread(() -> ejecutarTarea("B", 700), "Hilo-B");
        Thread hiloC = new Thread(() -> ejecutarTarea("C", 400), "Hilo-C");

        System.out.println("El main va a lanzar los tres hilos...");

        hiloA.start();
        hiloB.start();
        hiloC.start();

        System.out.println("El main continúa sin esperar automáticamente...");

        try {
            hiloA.join();
            hiloB.join();
            hiloC.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("El hilo main fue interrumpido.");
            return;
        }

        System.out.println("Todos los hilos han terminado. FIN.");
    }

    private static void ejecutarTarea(String nombre, int pausaMilisegundos) {
        for (int i = 1; i <= 5; i++) {
            System.out.println("Hilo " + nombre + " -> paso " + i);

            try {
                Thread.sleep(pausaMilisegundos);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Hilo " + nombre + " interrumpido.");
                return;
            }
        }

        System.out.println("Hilo " + nombre + " TERMINÓ.");
    }
}
