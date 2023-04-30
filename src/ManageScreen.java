import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ManageScreen extends JPanel {

    private JLabel classLabel;
    private JLabel passengerIdLabel;
    private JLabel nameLabel;
    private JLabel genderLabel;
    private JLabel sibSpLabel;
    private JLabel parchLabel;
    private JLabel ticketNumLabel;
    private JLabel ticketCostLabel;
    private JLabel cabinNumLabel;
    private JLabel embarkedLabel;
    private JLabel answerLabel;

    private JComboBox<String> classComboBox;
    private JComboBox<String> genderComboBox;
    private JComboBox<String> embarkedComboBox;

    private JTextField passengerNumberMin;
    private JTextField passengerNumberMax;
    private JTextField minFareText;
    private JTextField maxFareText;
    private JTextField nameText;
    private JTextField sibSpText;
    private JTextField parchText;
    private JTextField ticketNumText;
    private JTextField cabinNumText;

    private JButton filterButton;
    private JButton statisticsButton;
    private Image backgroundImage;
    private Integer passengerNumberMinData;
    private Integer passengerNumberMaxData;
    private Integer sibSPAmountBoxData;
    private Integer parchAmountData;

    private Integer cabinNumDate;
    private float minFareData;
    private float maxFareData;



    private List<Passenger> data;

    public ManageScreen(int x, int y, int width, int height) {
        File file = new File(Constants.PATH_TO_DATA_FILE); //this is the path to the data file
        try {
            backgroundImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("/data/titanicIdeaPhoto.jpg")));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        if (file.exists()) {
            this.setLayout(null);

            List<String> read = readFromFile(file);
            List<Passenger> data = linesToPassenger(read);
            this.data = data;

            this.setBounds(x, y + Constants.MARGIN_FROM_TOP, width, height);
            this.classLabel = newLabel("Passenger Class: ", this.getX() + Constants.MARGIN_FROM_LEFT,
                    this.getY() + Constants.MARGIN_FROM_TOP);
            this.add(this.classLabel);
            this.classComboBox = newComboBox(Constants.PASSENGER_CLASS_OPTIONS,
                    this.classLabel.getX() + Constants.LABEL_WIDTH + Constants.MARGIN_FROM_LEFT
                    , this.classLabel.getY());
            this.add(this.classComboBox);
            this.passengerIdLabel = newLabel("Passenger ID: ", this.classLabel.getX(),
                    this.classLabel.getY() + Constants.MARGIN_FROM_TOP + Constants.LABEL_HEIGHT);

            this.passengerNumberMin = newTextFieldForNum(this.passengerIdLabel.getX() + Constants.LABEL_WIDTH + Constants.MARGIN_FROM_LEFT
                    , this.passengerIdLabel.getY());
            this.add(this.passengerNumberMin);
            passengerNumberMin.addKeyListener(new KeyAdapter() {

                public void keyReleased(KeyEvent e) {
                    try {
                        int value = Integer.parseInt(passengerNumberMin.getText());
                        if (value > Constants.MAX_PASSENGER || value < Constants.MIN_PASSENGER) {
                            passengerNumberMin.setText("");
                            showMessage("Min passenger is " + Constants.MIN_PASSENGER + " Max passenger is " + Constants.MAX_PASSENGER);
                        } else {
                            passengerNumberMinData = value;
                            System.out.println("Passenger number " + value);
                        }
                    } catch (NumberFormatException exception) {
                        passengerNumberMin.setText("");
                        passengerNumberMinData = Constants.MIN_PASSENGER;
                        if (!passengerNumberMin.getText().equals("")) {
                            showMessage("Please enter a valid number");
                        }
                    }
                }
            });
            this.passengerNumberMax = newTextFieldForNum(this.passengerNumberMin.getX() + Constants.TEXT_FILED_WIDTH + Constants.MARGIN_FROM_LEFT
                    , this.passengerIdLabel.getY());
            this.add(this.passengerNumberMax);
            passengerNumberMax.addKeyListener(new KeyAdapter() {
                public void keyReleased(KeyEvent e) {
                    try {
                        int value = Integer.parseInt(passengerNumberMax.getText());
                        if (value > Constants.MAX_PASSENGER || value < Constants.MIN_PASSENGER) {
                            passengerNumberMax.setText("");
                            showMessage("Min passenger is " + Constants.MIN_PASSENGER + " Max passenger is " + Constants.MAX_PASSENGER);
                        } else {
                            passengerNumberMaxData = value;
                            System.out.println("Passenger number " + value);
                        }
                    } catch (NumberFormatException exception) {
                        passengerNumberMax.setText("");
                        passengerNumberMaxData = Constants.MAX_PASSENGER;
                        if (!passengerNumberMax.getText().equals("")) {
                            showMessage("Please enter a valid number");
                        }
                    }
                }
            });
            this.add(this.passengerIdLabel);
            this.nameLabel = newLabel("Passenger Name: ", this.passengerIdLabel.getX(),
                    this.passengerIdLabel.getY() + Constants.MARGIN_FROM_TOP + Constants.LABEL_HEIGHT);
            this.add(this.nameLabel);
            this.nameText = newTextFieldForText(this.nameLabel.getX() + Constants.LABEL_WIDTH + Constants.MARGIN_FROM_LEFT
                    , this.nameLabel.getY());
            this.add(this.nameText);
            this.genderLabel = newLabel("Gender: ", this.nameLabel.getX(),
                    this.nameLabel.getY() + Constants.MARGIN_FROM_TOP + Constants.LABEL_HEIGHT);
            this.add(this.genderLabel);
            this.genderComboBox = newComboBox(Constants.SEX_OPTIONS,
                    this.genderLabel.getX() + Constants.LABEL_WIDTH + Constants.MARGIN_FROM_LEFT, this.genderLabel.getY());
            this.add(this.genderComboBox);
            this.sibSpLabel = newLabel("Siblings Number: ", this.genderLabel.getX(),
                    this.genderLabel.getY() + Constants.MARGIN_FROM_TOP + Constants.LABEL_HEIGHT);
            this.add(this.sibSpLabel);
            this.sibSpText = newTextFieldForText(this.sibSpLabel.getX() + Constants.LABEL_WIDTH + Constants.MARGIN_FROM_LEFT
                    , this.sibSpLabel.getY());
            this.add(this.sibSpText);
            sibSpText.addKeyListener(new KeyAdapter() {
                public void keyReleased(KeyEvent e) {
                    try {
                        int value = Integer.parseInt(sibSpText.getText());
                        if (value > 9) {
                            showMessage("Please enter a number between 0-9");
                            sibSpText.setText("");
                            value = Integer.parseInt(sibSpText.getText());
                        }
                        sibSPAmountBoxData = value;
                        System.out.println("sibSPAmountData number " + value);
                    } catch (NumberFormatException exception) {
                        sibSpText.setText("");
                        sibSPAmountBoxData = Constants.DEFAULT_VALUE;
                    }
                }
            });
            this.parchLabel = newLabel("Parch Number: ", this.sibSpLabel.getX(),
                    this.sibSpLabel.getY() + Constants.MARGIN_FROM_TOP + Constants.LABEL_HEIGHT);
            this.add(this.parchLabel);
            this.parchText = newTextFieldForText(this.parchLabel.getX() + Constants.LABEL_WIDTH + Constants.MARGIN_FROM_LEFT
                    , this.parchLabel.getY());
            this.add(this.parchText);
            parchText.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    try {
                        int value = Integer.parseInt(parchText.getText());
                        if (value > 9) {
                            showMessage("Please enter a number between 0-9");
                            parchText.setText("");
                            value = Integer.parseInt(parchText.getText());
                        }
                        parchAmountData = value;
                        System.out.println("Parch amount: " + value);
                    } catch (NumberFormatException exception) {
                        System.out.println("Please enter a valid number");
                        parchText.setText("");
                        parchAmountData = Constants.ZERO_VALUE;
                    }
                }
            });
            this.ticketNumLabel = newLabel("Ticket Number: ", this.parchLabel.getX(),
                    this.parchLabel.getY() + Constants.MARGIN_FROM_TOP + Constants.LABEL_HEIGHT);
            this.add(this.ticketNumLabel);
            this.ticketNumText = newTextFieldForText(this.ticketNumLabel.getX() + Constants.LABEL_WIDTH + Constants.MARGIN_FROM_LEFT
                    , this.ticketNumLabel.getY());
            this.add(this.ticketNumText);
            this.ticketCostLabel = newLabel("Ticket Cost: ", this.ticketNumLabel.getX(),
                    this.ticketNumLabel.getY() + Constants.MARGIN_FROM_TOP + Constants.LABEL_HEIGHT);
            this.add(this.ticketCostLabel);
            this.minFareText = newTextFieldForNum(this.ticketCostLabel.getX() + Constants.LABEL_WIDTH + Constants.MARGIN_FROM_LEFT
                    , this.ticketCostLabel.getY());
            this.add(this.minFareText);
            minFareText.addKeyListener(new KeyAdapter() {
                public void keyReleased(KeyEvent e) {
                    try {
                        float value = Float.parseFloat(minFareText.getText());
                        minFareData = value;
                        System.out.println("Min ticket price: " + value);
                    } catch (NumberFormatException exception) {
                        if (!minFareText.getText().equals("")) {
                            showMessage("Please enter a valid number");
                        }
                        minFareText.setText("");
                        minFareData = Float.parseFloat(String.valueOf(Constants.DEFAULT_VALUE));
                    }
                }
            });
            this.maxFareText = newTextFieldForNum(this.minFareText.getX() + Constants.TEXT_FILED_WIDTH + Constants.MARGIN_FROM_LEFT
                    , this.ticketCostLabel.getY());
            this.add(this.maxFareText);
            maxFareText.addKeyListener(new KeyAdapter() {
                public void keyReleased(KeyEvent e) {
                    try {
                        float value = Float.parseFloat(maxFareText.getText());
                        maxFareData = value;
                        System.out.println("Max ticket price: " + value);
                    } catch (NumberFormatException exception) {
                        if (!maxFareText.getText().equals("")) {
                            showMessage("Please enter a valid number");
                        }
                        maxFareText.setText("");
                        maxFareData = Float.parseFloat(String.valueOf(Constants.DEFAULT_VALUE));
                    }
                }
            });
            this.cabinNumLabel = newLabel("Cabin Number: ", this.ticketCostLabel.getX(),
                    this.ticketCostLabel.getY() + Constants.MARGIN_FROM_TOP + Constants.LABEL_HEIGHT);
            this.add(this.cabinNumLabel);
            this.cabinNumText = newTextFieldForText(this.cabinNumLabel.getX() + Constants.LABEL_WIDTH + Constants.MARGIN_FROM_LEFT
                    , this.cabinNumLabel.getY());
            this.add(this.cabinNumText);
            cabinNumText.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    try {
                        int value = Integer.parseInt(cabinNumText.getText());
                        if (value > 9) {
                            showMessage("Please enter a number between 0-9");
                            cabinNumText.setText("");
                            value = Integer.parseInt(cabinNumText.getText());
                        }
                        cabinNumDate = value;
                        System.out.println("Cabin number: " + value);
                    } catch (NumberFormatException exception) {
                        System.out.println("Please enter a valid number");
                        cabinNumText.setText("");
                        cabinNumDate = Constants.ZERO_VALUE;
                    }
                }
            });
            this.embarkedLabel = newLabel("Embarked: ", this.cabinNumLabel.getX(),
                    this.cabinNumLabel.getY() + Constants.MARGIN_FROM_TOP + Constants.LABEL_HEIGHT);
            this.add(this.embarkedLabel);
            this.embarkedComboBox = newComboBox(Constants.EMBARKED_OPTIONS,
                    this.embarkedLabel.getX() + Constants.LABEL_WIDTH + Constants.MARGIN_FROM_LEFT, this.embarkedLabel.getY());
            this.add(this.embarkedComboBox);
            this.answerLabel = newLabel("fill details", (Constants.WINDOW_WIDTH / 3) * 2, (Constants.WINDOW_HEIGHT / 3) * 2 - Constants.MARGIN_FROM_TOP);
            this.add(this.answerLabel);
            this.filterButton = newButton("Filter",
                    this.answerLabel.getX() - (Constants.BUTTON_WIDTH / 3) * 2,
                    this.answerLabel.getY() + Constants.LABEL_HEIGHT + Constants.MARGIN_FROM_TOP);
            this.add(this.filterButton);
            this.statisticsButton = newButton("Create statistics",
                    this.filterButton.getX() + Constants.BUTTON_WIDTH + Constants.MARGIN_FROM_LEFT,
                    this.filterButton.getY());
            this.add(this.statisticsButton);

            this.statisticsButton.addActionListener((e )->{
                new Statistic(this.data);
            });

            this.filterButton.addActionListener((e) -> {
               new Filter(this.answerLabel,this.data, this.classComboBox.getSelectedIndex(),this.genderComboBox.getSelectedIndex(),
                       this.embarkedComboBox.getSelectedIndex(),this.passengerNumberMin.getText(),
                       this.passengerNumberMax.getText(),this.nameText.getText(),this.parchText.getText(),
                       this.sibSpText.getText(),this.ticketNumText.getText(),this.minFareText.getText()
               ,this.maxFareText.getText(),this.cabinNumText.getText());
            });
        }
    }


    private List<String> readFromFile(File file) {//מייצר את הרשימה
        List<String> lines = new ArrayList<>();
        if (file != null && file.exists()) {
            BufferedReader bufferedReader = null;
            FileReader fileReader = null;
            try {
                fileReader = new FileReader(file);
                bufferedReader = new BufferedReader(fileReader);
                String line = null;
                int counter = 0;
                do {
                    line = bufferedReader.readLine();
                    counter++;
                    if (counter != 1) {
                        lines.add(line);
                    }

                } while (line != null);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (bufferedReader != null)
                        bufferedReader.close();
                    if (fileReader != null)
                        fileReader.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return lines;
    }

    private List<Passenger> linesToPassenger(List<String> list)//הופך את הסטרינג לאובייקט
    {
        List<Passenger> passengers = new ArrayList<>();
        for (String line : list) {
            Passenger passenger = new Passenger(line);
            passengers.add(passenger);
        }
        return passengers;
    }

    private JTextField newTextFieldForNum(int x, int y) {
        JTextField textFiled = new JTextField();
        textFiled.setBounds(x, y, Constants.TEXT_FILED_WIDTH, Constants.TEXT_FILED_HEIGHT);
        return textFiled;
    }

    private JTextField newTextFieldForText(int x, int y) {
        JTextField textFiled = new JTextField();
        textFiled.setBounds(x, y, Constants.TEXT_FILED_WIDTH * 2, Constants.TEXT_FILED_HEIGHT);
        return textFiled;
    }

    private JComboBox newComboBox(String[] options, int x, int y) {
        JComboBox comboBox = new JComboBox(options);
        comboBox.setBounds(x, y, Constants.COMBO_BOX_WIDTH, Constants.COMBO_BOX_HEIGHT);
        return comboBox;
    }

    private JButton newButton(String text, int x, int y) {
        JButton button = new JButton(text);
        button.setBounds(x, y, Constants.BUTTON_WIDTH, Constants.BUTTON_HEIGHT);
        button.setFocusable(false);

        return button;
    }

    private JLabel newLabel(String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, Constants.LABEL_WIDTH, Constants.LABEL_HEIGHT);
        return label;
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Filter", JOptionPane.INFORMATION_MESSAGE);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponents(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }

}
