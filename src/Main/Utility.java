package Main;

public class Utility {
    public static void printHeader(String header){
        System.out.println("\n=== " + header + " ===");
    }

    public static void clearScreen(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void printAlert(String alert){
        System.out.println("!!! " + alert + " !!!");
    }
}
