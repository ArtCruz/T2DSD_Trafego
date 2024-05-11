
import com.mycompany.dsd_t2_arthurraissa.Controller.TelaPrincipalController;
import com.mycompany.dsd_t2_arthurraissa.TelaPrincipal;
import javax.swing.SwingUtilities;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Raissa
 */
public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TelaPrincipal view = new TelaPrincipal();
            TelaPrincipalController controller = new TelaPrincipalController(view);
            view.setVisible(true);
        });
    }
    
}
