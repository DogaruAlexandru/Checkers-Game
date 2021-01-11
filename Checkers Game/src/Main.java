import javax.swing.SwingUtilities;

public class Main {

    private static void InitUI() {
        Choose game=new Choose();
        Choose.OpenMenu();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::InitUI);
    }
}
