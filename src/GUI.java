
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class GUI extends JFrame {

    private JTextField input = new JTextField("",3);
    private JLabel label = new JLabel("Введите выражение:");
    private JLabel label2 = new JLabel("");
    private JButton button = new JButton("Вычислить");

    public GUI()
    {
        super("Калькулятор");
        this.setBounds (500,100,300,150);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container container = this.getContentPane();

        container.setLayout(new GridLayout(4,2,2,2));
        container.add(label);
        container.add(input);
        container.add(label2);

            button.addActionListener(new ButtonEventListener());

        container.add(button);
    }

    class ButtonEventListener implements ActionListener{
        public void actionPerformed (ActionEvent e)
        {
            label2.setText( Main.micromain(input.getText()));
        }

    }

}

