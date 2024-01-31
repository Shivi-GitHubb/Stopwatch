import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class Stopwatch implements ActionListener {
    JFrame frame = new JFrame();
    JButton startBtn = new JButton("START");
    JButton resetBtn = new JButton("RESET");
    JLabel timeLabel = new JLabel();

    int elapsedTime = 0;
    int seconds = 0;
    int minutes = 0;
    int hours = 0;
    boolean started = false;


    String seconds_strings = String.format("%02d", seconds);
    String minutes_strings = String.format("%02d", minutes);
    String hours_strings = String.format("%02d", hours);

    Timer timer = new Timer(1000, new ActionListener(){
        public void actionPerformed(ActionEvent e){

            elapsedTime += 1000;
            hours = (elapsedTime)/3600000;
            minutes = ((elapsedTime)/60000)%60;
            seconds = ((elapsedTime)/1000)%60;
            seconds_strings = String.format("%02d", seconds);
            minutes_strings = String.format("%02d", minutes);
            hours_strings = String.format("%02d", hours);
            timeLabel.setText(hours_strings +" : " + minutes_strings +" : "+ seconds_strings);

        }
    });

    Stopwatch(){

        timeLabel.setText(hours_strings +" : " + minutes_strings +" : "+ seconds_strings);
        timeLabel.setBounds(50, 100,300,100);
        timeLabel.setFont(new Font("Verdana", Font.PLAIN, 30));
        timeLabel.setBorder(BorderFactory.createBevelBorder(1));
        timeLabel.setOpaque(true);
        timeLabel.setHorizontalAlignment(JTextField.CENTER);

        startBtn.setBounds(50,200,150,50);
        startBtn.setFont(new Font("Ink Free", Font.PLAIN, 15));
        startBtn.setFocusable(false);
        startBtn.addActionListener(this);

        resetBtn.setBounds(200,200,150,50);
        resetBtn.setFont(new Font("Ink Free", Font.PLAIN, 15));
        resetBtn.setFocusable(false);
        resetBtn.addActionListener(this);

        frame.add(startBtn);
        frame.add(resetBtn);
        frame.add(timeLabel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,400);
        frame.setLayout(null);
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e){
        if (e.getSource()== startBtn){
            if (started == false){
                started = true;
                startBtn.setText("STOP");
                start();
            } else {
                started = false;
                startBtn.setText("START");
                stop();
            }
        }
        if (e.getSource() == resetBtn){
            started = false;
            startBtn.setText("START");
            reset();

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
        elapsedTime = 0;
        seconds = 0;
        minutes = 0;
        hours = 0;


        seconds_strings = String.format("%02d", seconds);
        minutes_strings = String.format("%02d", minutes);
        hours_strings = String.format("%02d", hours);
        timeLabel.setText(hours_strings +" : " + minutes_strings +" : "+ seconds_strings);


    }

}
