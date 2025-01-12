import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;


import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Stopwatch implements ActionListener {

    JFrame frame = new JFrame("Stopwatch");
    JButton startButton = new JButton("START");
    JButton resetButton = new JButton("RESET");
    JButton lapButton = new JButton("LAP");
    JLabel timeLabel = new JLabel();
    JTextArea lapArea = new JTextArea();

    int elapsedTime = 0;
    int seconds = 0;
    int minutes = 0;
    int hours = 0;
    boolean started = false;
    String seconds_string = String.format("%02d", seconds);
    String minutes_string = String.format("%02d", minutes);
    String hours_string = String.format("%02d", hours);

    Timer timer = new Timer(1000, new ActionListener() {

    public void actionPerformed(ActionEvent e){

        elapsedTime=elapsedTime+1000;
        hours = (elapsedTime/3600000);
        minutes = (elapsedTime/60000) % 60;
        seconds = (elapsedTime/1000) % 60;
        seconds_string = String.format("%02d", seconds);
        minutes_string = String.format("%02d", minutes);
        hours_string  = String.format("%02d", hours);
        timeLabel.setText(hours_string+":"+minutes_string+":"+seconds_string);



    }



    });

    ArrayList<String> laps = new ArrayList<>();

    Stopwatch(){
        GradientPanel gradientPanel = new GradientPanel();
        frame.setContentPane(gradientPanel);
        //frame.getContentPane().setBackground(new Color(173,216,230));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(550,550);
        frame.setLayout(null);










        timeLabel.setText(hours_string+":"+minutes_string+":"+seconds_string);
        timeLabel.setBounds(100,50,400,100);
        timeLabel.setFont(new Font("Courier New",Font.BOLD,50));
        timeLabel.setForeground(new Color(0, 216, 255));
        timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        timeLabel.setBackground(new Color(12,35,64));
        timeLabel.setOpaque(true);
        timeLabel.setBorder(BorderFactory.createLineBorder(new Color(0, 255, 255), 3));

        startButton.setBounds(100,200,100,50);
        startButton.setFont(new Font("Montserrat",Font.BOLD,20));
        startButton.setFocusable(false);
        startButton.setBackground(new Color(40, 167, 69));
        startButton.setForeground(Color.WHITE);
        startButton.addActionListener(this);

        resetButton.setBounds(220,200,100,50);
        resetButton.setFont(new Font("Monserrat",Font.BOLD,18));
        resetButton.setFocusable(false);
        resetButton.setBackground(new Color(199, 0, 57));
        resetButton.setForeground(Color.WHITE);
        resetButton.addActionListener(this);

        lapButton.setBounds(400, 200, 100, 50);
        lapButton.setFont(new Font("Montserrat", Font.BOLD, 18));
        lapButton.setFocusable(false);
        lapButton.setBackground(new Color(255, 215, 0));
        lapButton.setForeground(Color.WHITE);
        lapButton.addActionListener(this);

        lapArea.setBounds(100, 300, 400, 150);
        lapArea.setEditable(false);
        lapArea.setFont(new Font("Courier New", Font.PLAIN, 16));
        lapArea.setBackground(new Color(12,35,64));
        lapArea.setForeground(new Color(255, 255, 255));
        lapArea.setBorder(BorderFactory.createLineBorder(new Color(0, 255, 255 ), 2));


        ImageIcon starIcon = new ImageIcon("C:\\Users\\Lenovo\\Downloads\\play.png");
        Image image  = starIcon.getImage();
        Image resizedImage = image.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon  = new ImageIcon(resizedImage);
        startButton.setIcon(resizedIcon);
        startButton.setText("");


        ImageIcon pauseIcon = new ImageIcon("C:\\Users\\Lenovo\\Downloads\\pause.png");








        ImageIcon resetIcon2 = new ImageIcon("C:\\Users\\Lenovo\\Downloads\\reset.png");
        Image image2 = resetIcon2.getImage(); // Perbaikan: Ambil gambar dari resetIcon2
        Image resizedImage2 = image2.getScaledInstance(20, 20, Image.SCALE_SMOOTH); // Gunakan image2
        ImageIcon resizedIcon2 = new ImageIcon(resizedImage2);
        resetButton.setIcon(resizedIcon2);
        resetButton.setText("");

        ImageIcon lapIcon3 = new ImageIcon("C:\\Users\\Lenovo\\Downloads\\lap.png");
        Image image3 = lapIcon3.getImage(); // Perbaikan: Ambil gambar dari resetIcon2
        Image resizedImage3 = image3.getScaledInstance(20, 20, Image.SCALE_SMOOTH); // Gunakan image2
        ImageIcon resizedIcon3 = new ImageIcon(resizedImage3);
        lapButton.setIcon(resizedIcon3);
        lapButton.setText("");


        frame.add(startButton);
        frame.add(resetButton);
        frame.add(timeLabel);
        frame.add(lapButton);
        frame.add(lapArea);
        frame.setVisible(true);



    startButton.addMouseListener(new java.awt.event.MouseAdapter(){
        public void mouseEntered(java.awt.event.MouseEvent evt){
            startButton.setBackground(new Color(40, 167, 69));
        }
        public void mouseExited(java.awt.event.MouseEvent evt){
            startButton.setBackground(new Color(49, 167, 69));

        }
    });

    resetButton.addMouseListener(new java.awt.event.MouseAdapter(){
        public void mouseEntered(java.awt.event.MouseEvent evt){
            resetButton.setBackground(new Color(199, 0, 57));
        }
        public void mouseExited(java.awt.event.MouseEvent evt){
            resetButton.setBackground(new Color(199, 0, 57));
        }
    });

    frame.add(startButton);
    frame.add(resetButton);
    frame.add(timeLabel);
    frame.setVisible(true);


    }


    public void actionPerformed(ActionEvent e){
            if(e.getSource()==startButton){
                if(!started){
                    started=true;
                    startButton.setText("");
                    start();
                }else{
                    started=false;
                    startButton.setText("");
                    stop();
                }

            }
            if(e.getSource()==resetButton){
                started=false;
                startButton.setText("");
                reset();
            }
            if (e.getSource()==lapButton){
                if(started){
                    recordLap();
                }
            }
    }
    void start(){
    timer.start();
    }
    void stop(){
        timer.stop();
    }
    void reset(){
        timer.stop();
        elapsedTime=0;
        seconds=0;
        minutes=0;
        hours=0;
        seconds_string = String.format("%02d", seconds);
        minutes_string = String.format("%02d", minutes);
        hours_string  = String.format("%02d", hours);
        timeLabel.setText(hours_string+":"+minutes_string+":"+seconds_string);

        laps.clear();
        lapArea.setText("");
    }
    void recordLap(){
        String lapTime = hours_string + ":" + minutes_string+ " : " + seconds_string;
        laps.add(lapTime);
        lapArea.append("lap " + laps.size() + " : " + lapTime + "\n");

    }

    public static void main(String[] args){
        new Stopwatch();
    }
    class GradientPanel extends JPanel{
        protected void paintComponent(Graphics g){
            super.paintComponent(g);
            Graphics2D  g2d = (Graphics2D) g;
            int width = getWidth();
            int height = getHeight();

            //warna gradien
            Color color1 = new Color (0, 225, 253);
            Color color2 = new Color (0, 30, 253);
            GradientPaint gradient = new GradientPaint(0,0, color1, 0, height, color2);


            g2d.setPaint(gradient);
            g2d.fillRect(0,0, width,height);
        }
    }
}
