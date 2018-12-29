/* 
Net ID: gvildave 
CSC 171 Project 4 
Lab Section: TR 9:40- 10:55 

I did not colloborate with anyone on this assignment.
*/

/* Either Player A or Player B must get 5 goals that touch the wall, in their opponents goal to win.
   The goal is the pink semi circle in the middle of both sides.
   Once the player achieves 5 goals, the game will end, displaying the winner. 
   Player A keys: up = w , down = s 
   Player B keys: up = up arrow , down = down arrow
*/

import java.awt.Canvas;
import javax.swing.JFrame;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.awt.event.KeyEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;

public class VildaverPong implements KeyListener {

    private int x1; // location of player A
    private int y1; // location of player A
    private int x2; // location of player B
    private int y2; // location of player B
    private double x; // Location of ball
    private double y; // Location of ball
    private double vx; // velocity of ball
    private double vy; // velocity of ball
    Graphics g2; // help draw and color shapes 
    final int WIDTH = 640; // represents dimensions of board
    final int HEIGHT = 480; // represents dimensions of board
    final int DELTA = 8; // represents the distance that each paddle will move with each key stroke
    final int PADDLE_WIDTH = 8; // represents the dimensions of the paddles
    final int PADDLE_HEIGHT = 128; // represents the dimensions of the paddles
    final int PUCK_DIAMETER = 32; // represents diameter of the ball
    private JFrame f;
    int playerAScore;
    int playerBScore;

    private boolean holdingW;
    private boolean holdingS;
    private boolean holdingUp;
    private boolean holdingDown;

    public VildaverPong() {
        x1 = 0;
        y1 = 240;
        x2 = 630;
        y2 = 240;
        x = WIDTH / 2 - PUCK_DIAMETER / 2;
        
        Random r = new Random();
        int low = 50;
        int high = HEIGHT - low;
        y = r.nextInt(high-low) + low;
        
        System.out.println(y);
        vx = 2;
        vy = 1;
        f = new JFrame();  
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        f.setTitle("Pong");    
        f.setResizable(false);      
        Canvas c = new Canvas();   
        c.setSize(WIDTH, HEIGHT);  
        f.add(c);      
        f.pack();    
        f.setVisible(true);   
        g2 = c.getGraphics();    
        f.addKeyListener(this);
        draw();
        playerAScore = 0;
        playerBScore = 0;
    }

    //This method will acknowledge user input
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == 87) {
            holdingW = true;
        }
        if (key == 83) {
            holdingS = true;
        }
        if (key == 38) {
            holdingUp = true;
        }
        if (key == 40) {
            holdingDown = true;
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == 87) {
            holdingW = false;
        }
        if (key == 83) {
            holdingS = false;
        }
        if (key == 38) {
            holdingUp = false;
        }
        if (key == 40) {
            holdingDown = false;
        }
    }

    public void updatePlayerPosition() {
        if (holdingW) {
            if (y1 - DELTA >= 0) {
                y1 -= DELTA;
            }
        }

        if (holdingS) {
            if (y1 + DELTA <= 352) {
                y1 += DELTA;
            }
        }

        if (holdingUp) {
            if (y2 - DELTA >= 0) {
                y2 -= DELTA;
            }
        }

        if (holdingDown) {
            if (y2 + DELTA <= 352) {
                y2 += DELTA;
            }
        }
    }

    public void keyTyped(KeyEvent e) {
    }

    public void draw() {
        // Constructs a BufferedImage of one of the predefined image types.
        BufferedImage bufferedImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

        // Create a graphics which can be used to draw into the buffered image
        Graphics2D g = bufferedImage.createGraphics();

        // Background
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        // Middle line
        g.setColor(Color.CYAN);
        g.fillRect(WIDTH / 2 + 6, 0, WIDTH / 100, HEIGHT);

        // Goals
        g.setColor(Color.MAGENTA);
        g.drawArc(WIDTH / 2 + 250, HEIGHT / 2 - 80, WIDTH / 3, HEIGHT / 2, 100, 180);
        g.drawArc(WIDTH / 2 - 445, HEIGHT / 2 - 80, WIDTH / 3, HEIGHT / 2, 100, -180);

        // Middle circle
        g.setColor(Color.BLUE);
        g.drawOval(WIDTH / 2 - 65, HEIGHT / 2 - 70, 150, 150);

        // Border
        g.setColor(Color.ORANGE);
        g.drawRect(0, 0, WIDTH - 1, HEIGHT - 1);

        // Score board
        g.setColor(Color.CYAN);
        g.fill3DRect(20, 20, 200, 100, true);

        g.setColor(Color.CYAN);
        g.fill3DRect(WIDTH - 220, 20, 200, 100, true);

        // Scores
        g.setColor(Color.BLUE);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
        g.drawString("Player A: " + playerAScore, 20, 80);

        g.setColor(Color.BLUE);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
        g.drawString("Player B: " + playerBScore, WIDTH - 220, 80);

        // b) Making player A's paddle
        g.setColor(Color.RED);
        g.fillRect(x1, y1, PADDLE_WIDTH, PADDLE_HEIGHT);

        // c) Making player B's paddle
        g.setColor(Color.GREEN);
        g.fillRect(x2, y2, PADDLE_WIDTH, PADDLE_HEIGHT);

        // d) Making ball
        g.setColor(Color.YELLOW);
        g.fillOval((int) x, (int) y, PUCK_DIAMETER, PUCK_DIAMETER);

        if (playerAScore == 5) {
            g.setColor(Color.CYAN);
            g.fill3DRect(WIDTH / 2 - 90, HEIGHT / 2 - 70, 200, 100, true);
            g.setColor(Color.BLUE);
            g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
            g.drawString("Player A WINS ", WIDTH / 2 - 90, HEIGHT / 2 - 10);
        }

        if (playerBScore == 5) {
            g.setColor(Color.CYAN);
            g.fill3DRect(WIDTH / 2 - 90, HEIGHT / 2 - 70, 200, 100, true);
            g.setColor(Color.BLUE);
            g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
            g.drawString("Player B WINS ", WIDTH / 2 - 90, HEIGHT / 2 - 10);
        }

        g.dispose();

        //Draws the image it just made onto the frame
        g2.drawImage(bufferedImage, 0, 0, WIDTH, HEIGHT, null);
    }

    public void play() {
        boolean keepPlaying = true;

        while (keepPlaying) {
            updatePlayerPosition();

            double nextX = x + vx;
            double nextY = y + vy;
            // Code necessary to determine the direction of the ball
            x += vx;
            y += vy;

            if (nextX < 0 || nextX > WIDTH) {
                vx = -vx;
                playerBScore++;

            }

            if (nextX <= 0 && (nextY >= 35 + PADDLE_HEIGHT && nextY <= 314)) {
                playerBScore++;
                vx = -vx;
            } else if (nextX + PUCK_DIAMETER >= WIDTH && (nextY >= 35 + PADDLE_HEIGHT && nextY <= 316)) {
                playerAScore++;
                vx = -vx;
            } else if (nextY < 0 || nextY > HEIGHT) {
                vy = -vy;

            } else if (nextX + PUCK_DIAMETER + vx < 0 || nextX + PUCK_DIAMETER > WIDTH) {
                vx = -vx;
                playerAScore++;
            } else if (nextY + PUCK_DIAMETER < 0 || nextY + PUCK_DIAMETER > HEIGHT) {
                vy = -vy;
            }

            // Code necessary to move the ball
            draw();
            try {
                Thread.sleep(15);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (detectedCollisionLeft()) {
                vx = -vx;
            }

            if (detectedCollisionRight()) {
                vx = -vx;
            }

            if (playerAScore == 5 || playerBScore == 5) {
                keepPlaying = false;
            }
        }
    }

    public boolean detectedCollisionLeft() {
        double nextX = x + vx;
        double nextY = y + vy;

        if ((nextX < x1 + PADDLE_WIDTH && nextX > x1) && (nextY > y1 && nextY < y1 + PADDLE_HEIGHT)) {
            return true;
        }

        return false;
    }

    public boolean detectedCollisionRight() {
        if ((x + PUCK_DIAMETER + vx < x2 + PADDLE_WIDTH && x + PUCK_DIAMETER + vx > x2) && (y + PUCK_DIAMETER + vy > y2 && y + PUCK_DIAMETER + vy < y2 + PADDLE_HEIGHT)) {
            return true;
        }

        return false;
    }
}