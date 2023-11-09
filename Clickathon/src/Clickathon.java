import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/*
 *    ~ Basic Game Mechanics ~
 * - Player must click on buttons that appear on screen (once clicked, they reappear in a random point in screen)
 * - Create different types of buttons in different sizes; The smaller button the more points gained. (may require inheritance classes)
 * - 15 sec timer limit (game ends and timer restarts)
 * - Once game ends, players enter their 3 char initials and the score gets stored with that key value.
 * 
 * 
 * :             OPTIONAL                :
 *  add images (preferably on the buttons)
 *  add SFX (audio for effects and BGM)
 *  add a custom cursor
 *  add parallax backgrounds for game (can be both for menu and in-game)
 *  add parallax for the labels depending on mouse cursor
 *  add animated images (for assets)
 *  add transitions (between screens e.g. game start or game over)
 *  add custom fonts
 *  add custom borders and swing panels.
 *  
*/

public class Clickathon extends JFrame {
    private static final int BUTTON_COUNT = 5;
    private static final int GAME_TIME = 15; // in seconds

    private JButton[] buttons = new JButton[BUTTON_COUNT];
    private int score = 0;
    private JLabel scoreLabel;
    private JTextField initialsField;
    private Timer timer;
    private JFrame creditsFrame;
    private int highScore = 0;

    public Clickathon() {
        setTitle("*~ CLICKATHON ~*");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null); // Use absolute layout

        createGameMenu();

        setVisible(true);
    }

    private void createGameMenu() { //Create a way to display a highscore label on top of the menu
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));

        JLabel highscoreLabel = new JLabel();
        JButton startButton = new JButton("Start Game");
        JButton creditsButton = new JButton("Credits");
        JButton scoresButton = new JButton("High Scores");

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame();
            }
        });

        creditsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showCredits();
            }
        });

        scoresButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showHighScores();
            }
        });

        menuPanel.add(highscoreLabel);
        menuPanel.add(startButton);
        menuPanel.add(creditsButton);
        menuPanel.add(scoresButton);

        menuPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(menuPanel);
    }

   /*Used to start building the game screen
    * First clears the screen
    * then creates a button, a timer, and a score
    */
    private void startGame() { 
        getContentPane().removeAll();
        revalidate();
        repaint();

        createButtons();
        createScoreboard();
        createTimer();

        setVisible(true);
    }

    //create buttons to be used for the game
    private void createButtons() {
        for (int i = 0; i < BUTTON_COUNT; i++) {
            buttons[i] = new JButton("Button " + (i + 1));
            buttons[i].setSize(50, 50);
            buttons[i].addActionListener(new ButtonClickListener());
            add(buttons[i]);
        }
        arrangeButtonsRandomly();
    }

    //takes created buttons and puts them in random points of the screen
    private void arrangeButtonsRandomly() {
        Random random = new Random();
        for (int i = 0; i < BUTTON_COUNT; i++) {
            int x = random.nextInt(getWidth() - 100);
            int y = random.nextInt(getHeight() - 50);
            buttons[i].setLocation(x, y);
        }
    }

    //
    private void createScoreboard() {
        scoreLabel = new JLabel("Score: 0");
        scoreLabel.setBounds(10, 10, 100, 30);
        add(scoreLabel);

        JLabel initialsLabel = new JLabel("Initials:");
        initialsLabel.setBounds(10, 50, 60, 30);
        add(initialsLabel);

        initialsField = new JTextField();
        initialsField.setBounds(80, 50, 60, 30);
        add(initialsField);
    }

    private void createTimer() {
        timer = new Timer(1000, new ActionListener() {
            int timeRemaining = GAME_TIME;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (timeRemaining == 0) {
                    endGame();
                } else {
                    timeRemaining--;
                }
            }
        });

        timer.start();
    }

    private void endGame() {
        timer.stop();
        for (JButton button : buttons) {
            button.setVisible(false);
        }
        int finalScore = score;
        String initials = initialsField.getText();
        JOptionPane.showMessageDialog(this, "Game Over! Your score: " + finalScore);

        if (finalScore > highScore) {
            highScore = finalScore;
        }

        createGameMenu();
    }

    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton clickedButton = (JButton) e.getSource();
            score++;
            scoreLabel.setText("Score: " + score);

            // Move the button to a random location
            Random random = new Random();
            int x = random.nextInt(getWidth() - 100);
            int y = random.nextInt(getHeight() - 50);
            clickedButton.setLocation(x, y);
        }
    }

    private void showCredits() {
        creditsFrame = new JFrame("Credits");
        JTextArea creditsTextArea = new JTextArea(10, 30);
        creditsTextArea.setText("Authors: Jesse Park & Edwin He");
        creditsTextArea.setEditable(false);
        creditsFrame.add(creditsTextArea);
        creditsFrame.pack();
        creditsFrame.setVisible(true);
        creditsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void showHighScores() {
        // Implement a method to display high scores here, possibly using a JOptionPane.
    }

}