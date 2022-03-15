import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.Math;

class MyTempConverter implements ActionListener{
    private JTextField inputTextBox;
    private JTextField errorTextBox;
    private JRadioButton inputCelsius;
    private JRadioButton inputFahrenheit;
    private JRadioButton inputKelvin;
    private JButton convertButton;
    private JButton clearButton;
    private JTable outputTable;
    private boolean[] inputArray;
    private String[][] dataArray;
    private double userInput;

    MyTempConverter(){
        // Creating and designing frame.
        JFrame frame = new JFrame("Thasan Temperature Converter!");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Creating and designing panel and layout.
        JPanel panel = new JPanel();
        frame.add(panel);
        panel.setLayout(new GridBagLayout());
        GridBagConstraints constraint = new GridBagConstraints();
        
        // Creating and designing input textbox.
        inputTextBox = new JTextField(20);
        inputTextBox.setFont(new Font("Calabri", Font.PLAIN, 20));
        inputTextBox.addActionListener(this);
        setConstraints(constraint, 1, 1, 1, 1, GridBagConstraints.NORTH);
        panel.add(inputTextBox, constraint);

        // Creating and designing error textbox.
        errorTextBox = new JTextField(20);
        errorTextBox.setBackground(panel.getBackground());
        errorTextBox.setBorder(BorderFactory.createEmptyBorder());
        errorTextBox.setFont(new Font("Calabri", Font.PLAIN, 20));
        errorTextBox.setForeground(Color.RED);
        errorTextBox.setEditable(false);
        errorTextBox.addActionListener(this);
        setConstraints(constraint, 1, 2, 1, 1, GridBagConstraints.NORTH);
        panel.add(errorTextBox, constraint);

        // Creating and designing temperature radio buttons.
        Box tempBox = Box.createVerticalBox();
        ButtonGroup tempGroup = new ButtonGroup();
        inputFahrenheit = new JRadioButton("Fahrenheit");
        inputCelsius = new JRadioButton("Celsius");
        inputKelvin = new JRadioButton("Kelvin");
        Font font = new Font("Calabri", Font.PLAIN, 20);
        inputFahrenheit.setFont(font);
        inputCelsius.setFont(font);
        inputKelvin.setFont(font);
        inputCelsius.addActionListener(this);
        inputFahrenheit.addActionListener(this);
        inputKelvin.addActionListener(this);
        tempGroup.add(inputFahrenheit);
        tempGroup.add(inputCelsius);
        tempGroup.add(inputKelvin);
        tempBox.add(inputFahrenheit);
        tempBox.add(inputCelsius);
        tempBox.add(inputKelvin);
        tempBox.setBorder(BorderFactory.createTitledBorder("Temperature"));
        setConstraints(constraint, 1, 3, 1, 1, GridBagConstraints.NORTH);
        panel.add(tempBox, constraint);

        // Creating and designing convert and clear buttons.
        Box buttonBox = Box.createHorizontalBox();
        convertButton = new JButton("Convert");
        clearButton = new JButton("Clear");
        convertButton.addActionListener(this);
        clearButton.addActionListener(this);
        buttonBox.add(convertButton);
        buttonBox.add(Box.createHorizontalStrut(20));
        buttonBox.add(clearButton);
        setConstraints(constraint, 1, 4, 1, 1, GridBagConstraints.NORTH);
        panel.add(buttonBox, constraint);

        // Creating and designing output table.
        inputArray = new boolean[]{false, false, false};
        dataArray = new String[][]{{"Fahrenheit", "0.00"}, {"Celsius", "0.00"}, {"Kelvin", "0.00"}};
        String[] columnHeaders = {"Unit", "Degrees"};
        outputTable = new JTable(dataArray, columnHeaders){
            @Override
            public boolean isCellEditable(int dataArrayArray, int columns){
                return false;
            }
        };
        outputTable.setRowHeight(60);
        outputTable.getColumnModel().getColumn(0).setPreferredWidth(220);
        outputTable.getColumnModel().getColumn(1).setPreferredWidth(220);
        outputTable.getTableHeader().setFont(new Font("Calabri", Font.PLAIN, 30));
        outputTable.setFont(new Font("Calabri", Font.PLAIN, 20));
        outputTable.setFillsViewportHeight(true);
        outputTable.setPreferredScrollableViewportSize(outputTable.getPreferredSize());
        JScrollPane scroll = new JScrollPane(outputTable);
        panel.add(scroll);
        setConstraints(constraint, 1, 5, 1, 1, GridBagConstraints.NORTH);
        panel.add(scroll, constraint);
        
        // Packing the frame to set it to the best best fit size initially.
        frame.pack();
    }

    private void setConstraints(GridBagConstraints c, int x, int y, int width, int height, int align){
        c.gridx = x;
        c.gridy = y;
        c.gridwidth = width;
        c.gridheight = height;
        c.weightx = 100.0;
        c.weighty = 100.0;
        c.insets = new Insets(5, 5, 5, 5);
        c.anchor = align;
        c.fill = GridBagConstraints.NONE;
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == inputFahrenheit){
            System.out.println("input Selected is: inputFahrenheit");
            for(int i = 0; i < inputArray.length; i++)
                inputArray[i] = false;
            inputArray[0] = true;
        }

        else if(e.getSource() == inputCelsius){
            System.out.println("input Selected is: inputCelsius");  
            for(int i = 0; i < inputArray.length; i++)
                inputArray[i] = false;
            inputArray[1] = true;
        }

        else if(e.getSource() == inputKelvin){
            System.out.println("input Selected is: inputKelvin");  
            for(int i = 0; i < inputArray.length; i++)
                inputArray[i] = false;
            inputArray[2] = true;
        }

        else if(e.getSource() == convertButton){
            System.out.println("convert");
            String input = inputTextBox.getText();
            if(inputArray[0] == true || inputArray[1] == true || inputArray[2] == true){
                try{
                    errorTextBox.setText("");
                    System.out.println("try");
                    userInput = Double.parseDouble(input);
                    System.out.println("userInput is: " + userInput);
                    fillTable();
                }
                catch(Exception ex){
                    System.out.println("catch");
                    //JOptionPane.showMessageDialog(null, "Please enter a number in the input textbox!", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                    errorTextBox.setText("Please enter a number!");
                }
            }
            else{
                JOptionPane.showMessageDialog(null, "Please select a temperature type!", "Error!", JOptionPane.ERROR_MESSAGE);
            }
            
        }

        else if(e.getSource() == clearButton){
            System.out.println("clear");
            for(int i = 0; i < inputArray.length; i++)
                inputArray[i] = false;
            dataArray[0][0] = "Fahrenheit";
            dataArray[0][1] = "0.00";
            dataArray[1][0] = "Celsius";
            dataArray[1][1] = "0.00";
            dataArray[2][0] = "Kelvin";
            dataArray[2][1] = "0.00";
        }
    }

    public void fillTable(){
        double temp = 0.0;
        if(inputArray[0] == true){
            dataArray[0][1] = String.valueOf(userInput);
            // Converting Fahrenheit to Celsius.
            temp = (userInput - 32) * (9/5);
            dataArray[1][1] = String.valueOf(Math.round(temp * 100.0) / 100.0);
            // Converting Fahrenheit to Kelvin.
            temp = (userInput - 32) * (9/5) + 273.15;
            dataArray[2][1] = String.valueOf(Math.round(temp * 100.0) / 100.0);
        }

        else if(inputArray[1] == true){
            // Converting Celsius to Fahrenheit.
            temp = (userInput * (9/5)) + 32;
            dataArray[0][1] = String.valueOf(Math.round(temp * 100.0) / 100.0);
            dataArray[1][1] = String.valueOf(userInput);
            // Converting Celsius to Kelvin.
            temp = userInput + 273.15;
            dataArray[2][1] = String.valueOf(Math.round(temp * 100.0) / 100.0);
        }

        else if(inputArray[2] == true){
            // Converting Kelvin to Fahrenheit.
            temp = (userInput - 273.15) * (9/5) + 32;
            dataArray[0][1] = String.valueOf(Math.round(temp * 100.0) / 100.0);
            // Converting Kelvin to Celsius.
            temp = userInput - 273.15;
            dataArray[1][1] = String.valueOf(Math.round(temp * 100.0) / 100.0);
            dataArray[2][1] = String.valueOf(userInput);
        }

        else{
            System.out.println("Must select and enter input temperature!");
        }
    }

    public static void main(String args[]){
        new MyTempConverter();
    }

}
