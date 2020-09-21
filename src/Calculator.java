import javax.swing. *;
import javax.swing.border. *;

import javax.script.ScriptEngine;
import javax.script.ScriptException;
import javax.script.ScriptEngineManager;

import java.awt. *;
import java.awt.event. *;

public class Calculator extends JFrame implements ActionListener {

    private static String Buttons[] = {
        "1", "2", "3", "4", "5", "6", "7",
        "8", "9",
        "/", "*", "-", "0", ".", "=", "+" 
    };

    private boolean Editable = true;

    private JPanel InputBox;
    private JPanel ButtonPanel;
    private JTextField Data;
    private JButton ClickedButton;

    public static final int WIDTH = 300;
    public static final int HEIGHT = 220;

    public static final int xAxis = 150;
    public static final int yAxis = 100;
        
    //Constructor
    public Calculator() {
        //Initiate the JPanel
        ButtonPanel = new JPanel();
        ButtonPanel.setLayout(new GridLayout(4, 4, 5, 5));

        Container contentPane = getContentPane();
        contentPane.setLayout(new FlowLayout());

        Data = new JTextField();
        Data.setBackground(Color.BLACK);
        Data.setPreferredSize(new Dimension(270, 35));
        Data.addActionListener(this);
        InputBox.add(Data);
        contentPane.add(ButtonPanel);

        setTitle("Calculator");
        setLocation(xAxis, yAxis);
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //Loop through the buttons and display it
        for (String i : Buttons) {
            JButton calcButton = new JButton(i);
            calcButton.addActionListener(this);
            ButtonPanel.add(calcButton);
        }

        contentPane.add(ButtonPanel);
    }

    //Events
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() instanceof JButton) {
            ClickedButton = (JButton) event.getSource();

            if (ClickedButton.getText().equals("=")) {
                appendOutput();
            } else {
                appendInput(ClickedButton.getText());
            }
        } else {
            appendOutput();
        }
    }

    //Methods to use in the actions
    public void appendInput(String Text) {
        if (Editable) {
            Data.setText(Data.getText() + Text);
        } else {
            Data.setText(Text);
            Editable = true;
        }
    }

    public void appendOutput() {
        //Try, and catch err

        try {
            ScriptEngineManager Manager = new ScriptEngineManager();
            ScriptEngine Engine = Manager.getEngineByName("JavaScript");
            Data.setText(Engine.eval(Data.getText()).toString());
        } catch (ScriptException err) {
            Data.setText("Error Occured");
        }
        Editable = false;
    }

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        calculator.setVisible(true);
    }
}
