import javax.swing.*;

class Titanic extends JFrame {

    public static void main(String[] args) {
        new Titanic();
    }

    public Titanic() {
        JLabel backGround;
        this.setTitle("Titanic Passengers Data");
        this.setLayout(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLocationByPlatform(true);
        this.setResizable(false);
        this.setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        this.add(new ManageScreen(0, 0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT));
        this.setVisible(true);

    }

}