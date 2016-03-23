import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * Created by Eric on 3/23/2016.
 */
public class Graph2 extends JPanel {
    File file;
    JButton jbtn = new JButton("Read");
    JPanel draw = new JPanel();
    public Graph2(){
        draw.setSize(new Dimension(700,700));
        setLayout(new MigLayout("", "[]","[] []"));
        add(jbtn, "wrap,center");
        add(draw,"grow, push");

    }

    public void SetFile(File f){
        file = f;
    }
}
