import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class St implements ActionListener {
    JFrame frame = new JFrame();
    JButton startBtn = new JButton("START");
    JButton resetBtn = new JButton("RESET");
    JLabel timeLabel = new JLabel();
    int milliseconds = 0;
    int elapsedTime = 0;
    int seconds = 0;
    int minutes = 0;
    int hours = 0;
    boolean started = false;

    static final int MILLISECOND_INTERVAL = 1;
    static final int SECONDS_IN_HOUR = 3600;
    static final int MILLISECONDS_IN_SECOND = 1000;
    static final int MILLISECONDS_IN_MINUTE = 60000;
    static final int MILLISECONDS_IN_HOUR = 3600000;

    Timer timer = new Timer(MILLISECOND_INTERVAL, new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            elapsedTime += MILLISECOND_INTERVAL;
            hours = (elapsedTime) / MILLISECONDS_IN_HOUR;
            minutes = ((elapsedTime) / MILLISECONDS_IN_MINUTE) % 60;
            seconds = ((elapsedTime) / MILLISECONDS_IN_SECOND) % 60;
            milliseconds = elapsedTime % MILLISECONDS_IN_SECOND;

            String millisecondsString = String.format("%03d", milliseconds);
            String secondsString = String.format("%02d", seconds);
            String minutesString = String.format("%02d", minutes);
            String hoursString = String.format("%02d", hours);

            timeLabel.setText(hoursString + " : " + minutesString + " : " + secondsString + " : " + millisecondsString);
        }
    });

    St() {
        timeLabel.setText(hours + " : " + minutes + " : " + seconds + " : " + milliseconds);
        timeLabel.setBounds(50, 100, 300, 100);
        timeLabel.setFont(new Font("Verdana", Font.PLAIN, 30));
        timeLabel.setBorder(BorderFactory.createBevelBorder(1));
        timeLabel.setOpaque(true);
        timeLabel.setHorizontalAlignment(JTextField.CENTER);

        startBtn.setBounds(50, 200, 150, 50);
        startBtn.setFont(new Font("Ink Free", Font.PLAIN, 15));
        startBtn.setFocusable(false);
        startBtn.addActionListener(this);

        resetBtn.setBounds(200, 200, 150, 50);
        resetBtn.setFont(new Font("Ink Free", Font.PLAIN, 15));
        resetBtn.setFocusable(false);
        resetBtn.addActionListener(this);

        frame.add(startBtn);
        frame.add(resetBtn);
        frame.add(timeLabel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startBtn) {
            if (!started) {
                started = true;
                startBtn.setText("STOP");
                start();
            } else {
                started = false;
                startBtn.setText("START");
                stop();
            }
        }
        if (e.getSource() == resetBtn) {
            started = false;
            startBtn.setText("START");
            reset();
        }
    }

    void start() {
        timer.start();
    }

    void stop() {
        timer.stop();
    }

    void reset() {
        timer.stop();
        elapsedTime = 0;
        seconds = 0;
        minutes = 0;
        hours = 0;

        String millisecondsString = String.format("%03d", elapsedTime);
        String secondsString = String.format("%02d", seconds);
        String minutesString = String.format("%02d", minutes);
        String hoursString = String.format("%02d", hours);

        timeLabel.setText(hoursString + " : " + minutesString + " : " + secondsString + " : " + millisecondsString);
    }


}
