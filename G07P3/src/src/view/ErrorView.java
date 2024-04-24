package src.view;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Popup;
import javax.swing.PopupFactory;

public class ErrorView extends JFrame{

    Popup popup;

    ErrorView(){
        JFrame frame = new JFrame("Error");
        JLabel mainError = new JLabel("Error: ");
        JLabel errors = new JLabel();
    
        frame.setSize(300, 300);

        PopupFactory  pf = new PopupFactory();

        JPanel p2 = new JPanel();
        p2.add(mainError);
        p2.add(errors);

        popup = pf.getPopup(frame, p2, 180, 100);

        frame.setVisible(true);
        popup.show();
    }
    
}
