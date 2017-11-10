import presentation.TerminalApp;

public class MasterMind {

    public static void main(String[] args) {
        if (args.length > 0 && args[0].contains("terminal")) {
            TerminalApp application = new TerminalApp();
            application.startApplication();
        } else {
            // TODO: GFX
            TerminalApp application = new TerminalApp();
            application.startApplication();
        }

        /** See TODO: See this!!! (Alexis)
         try {
         Process p = Runtime.getRuntime().exec(new String[] {
         "bash", "-c", "tput lines 2> /dev/tty" });
         BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
         // Can be null
         System.out.printLine(in.readLine());
         } catch (IOException e) {
         e.printStackTrace();
         }**/
    }

}
