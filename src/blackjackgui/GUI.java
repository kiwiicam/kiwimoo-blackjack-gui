package blackjackgui;

import static blackjackgui.GraphicsPanel.DARK_GREEN;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.*;

public class GUI extends JPanel implements ActionListener {

    public static final Color VERY_LIGHT_BLUE = new Color(51, 204, 255);
    //objects
    CreateDatabase createDB;
    private GraphicsPanel graphicsPanel;
    private GameLogic gameLogic;
    private Deck deck;
    public Card card;
    private Sum sum;
    public int Dsum;

    //gui buttons/ frames/ images
    JButton resetbet;
    JTextField User;
    JButton b, s, play, play2, quit, instruct, back, playg, enter;

    JFrame j;
//    int width;
//    int height;
    JPanel mainPanel;
    JPanel instructions;
    JLabel chip20;
    JLabel chip50;
    JLabel chip100;
    ImageIcon img20;
    ImageIcon img50;
    ImageIcon img100;

    boolean n;
    boolean c;

    //betting vars
    int tableamt;
    int bet;
    int lose;
    int win;
    int gamesplayed;
    String str;
    String username;
    int Height;
    int Width;

    public GUI() {
        img20 = new ImageIcon("./resources/PokerChips20.png");
        img50 = new ImageIcon("./resources/PokerChips50.png");
        img100 = new ImageIcon("./resources/PokerChips100.png");
        chip20 = new JLabel(img20);
        chip50 = new JLabel(img50);
        chip100 = new JLabel(img100);
        createDB = new CreateDatabase();
        createDB.createTable();
        username = "";
        str = "";
        tableamt = 0;
        bet = 0;
        lose = 0;
        win = 0;
        gamesplayed = 0;

        gameLogic = new GameLogic();
        c = false;
        n = false;
        this.Dsum = 0;
        sum = new Sum();
        deck = new Deck(1);
        card = new Card(deck.getCards());
        graphicsPanel = new GraphicsPanel(card.getImageCards());

        j = new JFrame();
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setExtendedState(JFrame.MAXIMIZED_BOTH);

        mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image bg2 = new ImageIcon("./resources/bg2.jpg").getImage();
                g.drawImage(bg2, 0, 0, getWidth(), getHeight(), this);
                g.setColor(VERY_LIGHT_BLUE);
                g.setFont(new Font("Arial", Font.PLAIN, 20));

                if (n) {
                    g.setColor(DARK_GREEN);
                    g.fillRect(50, 50, 1180, 540);
                    g.setColor(VERY_LIGHT_BLUE);
                    g.setFont(new Font("Arial", Font.PLAIN, 20));
                    g.drawString("1. Place a bet:", 55, 70);
                    g.drawString("Before the cards are dealt, players", 55, 120);
                    g.drawString("place a bet, usually within a posted", 55, 150);
                    g.drawString("minimum and maximum range.", 55, 180);

                    g.drawString("2. Receive cards:", 55, 230);
                    g.drawString("The dealer deals two cards face up to each player,", 55, 280);
                    g.drawString("and one card face up and one face down to themselves.", 55, 310);

                    g.drawString("3. Play your hand:", 55, 360);
                    g.drawString("Add the values of your cards together to get a total", 55, 410);
                    g.drawString("between 4 and 21. Aces can be played as 1 or 11,", 55, 440);
                    g.drawString("and all other cards are played at face value. Face", 55, 470);
                    g.drawString("cards (Jack, Queen, King) are worth 10 points, and", 55, 500);
                    g.drawString("all number cards (2–10) are worth their face value.", 55, 530);

                    g.drawString("4. Hit or stand:", 650, 70);
                    g.drawString("After receiving your cards, you can choose to \"hit\"", 650, 120);
                    g.drawString("and receive more cards, or \"stand\" and keep your current hand.", 650, 150);

                    g.drawString("5. Win or lose:", 650, 200);
                    g.drawString("The goal is to get closer to 21 than the dealer without going over.", 650, 250);
                    g.drawString("If your hand goes over 21, it's called a \"bust\" and you lose.", 650, 280);
                    g.drawString("If the dealer busts, all remaining players win. If neither the player", 650, 310);
                    g.drawString("nor the dealer busts, the player with the highest hand value wins.", 650, 340);
                    g.drawString("6. Betting:", 650, 390);
                    g.drawString("Users will be given unlimited chips to start,", 650, 440);
                    g.drawString("the maximum amount allowed to be bet is 500,", 650, 470);
                    g.drawString("and the minimum is 50,", 650, 500);
                    g.drawString("the user can increase betting amount by clicking", 650, 530);
                    g.drawString("on different chip amounts in the betting menu.", 650, 560);

                }
                if (c) {
                    g.setColor(VERY_LIGHT_BLUE);
                    g.setFont(new Font("Arial", Font.PLAIN, 20));
                    g.drawString(str, mainPanel.getWidth() / 2 - 30, 50);

                }

            }
        };
        mainPanel.setLayout(null);
        mainPanel.validate();

        j.add(mainPanel);
        j.setVisible(true);

    }

    public void game() {
        createDB.retrieveData(username);

        graphicsPanel.setBounds(mainPanel.getWidth() / 2 - 400, 60, 800, 500);
        mainPanel.add(graphicsPanel);
        graphicsPanel.displayStats(username, createDB.plays, bet, createDB.moneyBet, createDB.moneyWon, createDB.moneyLost, createDB.gamesWon, createDB.gamesLost);
        graphicsPanel.rectvisi = true;
        play.setVisible(false);
        instruct.setVisible(false);
        quit.setVisible(true);
        quit.setBounds(mainPanel.getWidth() / 2 + 70, 580, 100, 50);
        b.setVisible(true);
        s.setVisible(true);
//        exit.setVisible(true);
        gameStart();

    }

    public void gameStart() {
        int index = deck.DrawCard(deck.cards, true);
        graphicsPanel.hit(index, deck.p_sum, deck.getValue(), deck.getSuit());

        card.getImageCards().remove(index);
        int index2 = deck.DrawCard(deck.cards, true);
        graphicsPanel.hit(index2, deck.p_sum, deck.getValue(), deck.getSuit());

        card.getImageCards().remove(index2);
        int index3 = deck.DrawCard(deck.cards, false);
        graphicsPanel.dHit(index3, deck.d_sum, deck.getValue(), deck.getSuit());

        card.getImageCards().remove(index3);

    }

    //this will have a slider to input a betting amount
    //this will also have a combo box or another form of input
    //that will ask how many decks to be used in this game.
    public void bet() {

        resetbet.setVisible(true);
        back.setVisible(true);
        User.setVisible(false);
        enter.setVisible(false);
        chip20.setVisible(true);
        chip50.setVisible(true);
        chip100.setVisible(true);
        c = true;
        setMessage("Bet is: ");
        System.out.println("TESTING!@!!!@!#!@#");
        resetbet.setBounds((mainPanel.getWidth() / 2) - 10, mainPanel.getHeight() - 100, 120, 50);
        back.setBounds(mainPanel.getWidth() / 2 - 200, mainPanel.getHeight() - 100, 100, 50);
        play2.setBounds(mainPanel.getWidth() / 2 + 200, mainPanel.getHeight() - 100, 100, 50);
        play2.setVisible(true);
        back.setVisible(true);
        instruct.setVisible(false);
        MouseListener[] mouseListeners20 = chip20.getMouseListeners();
        for (MouseListener ml : mouseListeners20) {
            chip20.removeMouseListener(ml);
        }

        MouseListener[] mouseListeners50 = chip50.getMouseListeners();
        for (MouseListener ml : mouseListeners50) {
            chip50.removeMouseListener(ml);
        }

        MouseListener[] mouseListeners100 = chip100.getMouseListeners();
        for (MouseListener ml : mouseListeners100) {
            chip100.removeMouseListener(ml);
        }
        chip50.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                bet += 50;
                System.out.println(bet);
                setMessage("Bet is: " + bet);

            }
        });
        chip100.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                bet += 100;
                System.out.println(bet);
                setMessage("Bet is: " + bet);

            }
        });
        chip20.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                bet += 20;
                System.out.println(bet);
                setMessage("Bet is: " + bet);

            }
        });
        createDB.retrieveData(username);
        createDB.retrieveData(username);
        mainPanel.add(chip50);
        mainPanel.add(chip100);
        mainPanel.add(chip20);
        System.out.println(mainPanel.getWidth());
        chip20.setBounds(0, (mainPanel.getHeight() / 2) - (img20.getIconHeight() / 2), img20.getIconWidth() - 200, img20.getIconHeight());
        chip50.setBounds((mainPanel.getWidth() / 2) - ((img50.getIconWidth() - 200) / 2), (mainPanel.getHeight() / 2) - (img50.getIconHeight() / 2), img50.getIconWidth() - 200, img50.getIconHeight());
        chip100.setBounds(mainPanel.getWidth() - (img100.getIconWidth() - 200), (mainPanel.getHeight() / 2) - (img100.getIconHeight() / 2), img100.getIconWidth() - 200, img100.getIconHeight());

    }

    public void setMessage(String str) {
        this.str = str;
        mainPanel.repaint();
    }

    public void instructions() {
        play.setVisible(false);
        quit.setVisible(false);
        back.setBounds(mainPanel.getWidth() / 2 - 100, mainPanel.getHeight() - 300, 100, 50);
        back.setVisible(true);
        instruct.setVisible(false);
        n = true;
        mainPanel.repaint();
    }

    public void UserName() {
        play.setVisible(false);
        quit.setVisible(false);
        instruct.setVisible(false);
        back.setVisible(false);
        User = new JTextField(20);
        enter = new JButton("Enter username!");
        mainPanel.add(enter);
        mainPanel.add(User);
        enter.setBounds(mainPanel.getWidth() / 2 - 100, 500, 200, 30);
        User.setBounds(mainPanel.getWidth() / 2 - 100, 450, 200, 30);
        enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                username = User.getText();
                System.out.println("the username is: " + username);
                displayStats();
                bet();
            }
        });

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b) {

            int index = deck.DrawCard(deck.cards, true);
            graphicsPanel.hit(index, deck.p_sum, deck.getValue(), deck.getSuit());

            System.out.println("Removing card at index " + index + " it is a " + card.getImageCards().get(index));

            card.getImageCards().remove(index);
            boolean isBust = gameLogic.BustLogic(deck.p_sum);
            if (isBust) {
                PlayerWin(false);
            } else if (deck.p_sum == 21) {
                graphicsPanel.stand();
                Stand();
            }

        } else if (e.getSource() == s) {
            graphicsPanel.stand();
            Stand();

        } else if (e.getSource() == play2) {
            if (bet != 0 && bet <= 500) {
                resetbet.setVisible(false);
                createDB.UpdateData(username, createDB.plays, bet, createDB.moneyBet, createDB.moneyWon, createDB.moneyLost, createDB.gamesWon, createDB.gamesLost);
                game();
                c = false;
                setMessage("");
                play2.setVisible(false);
                chip20.setVisible(false);
                chip50.setVisible(false);
                chip100.setVisible(false);
                back.setVisible(false);
            } else {
                setMessage("Enter a valid betting amount, more than zero and less than 500!");
            }
        } else if (e.getSource() == play) {
            UserName();
            //game();
        } else if (e.getSource() == quit) {
            createDB.retrieveData(username);
            createDB.UpdateData(username, createDB.plays, 0, createDB.moneyBet + bet, createDB.moneyWon, createDB.moneyLost, createDB.gamesWon, createDB.gamesLost);
            System.exit(0);
        } else if (e.getSource() == instruct) {
            instructions();
        } else if (e.getSource() == back) {
            resetbet.setVisible(false);
            quit.setBounds(j.getWidth() / 2 + 10, j.getHeight() / 2, 100, 50);
            username = "";
            bet = 0;
            play2.setVisible(false);
            c = false;
            n = false;
            chip20.setVisible(false);
            chip50.setVisible(false);
            chip100.setVisible(false);
            setMessage("");
            mainPanel.repaint();
            back.setVisible(false);
            instruct.setVisible(true);
            play.setVisible(true);
            quit.setVisible(true);
        } else if (e.getSource() == playg) {
            quit.setVisible(false);
            resetGame();
            bet();

        } else if (e.getSource() == resetbet) {
            bet = 0;
            setMessage("Bet is: " + bet);
            graphicsPanel.repaint();
        }
    }

    public void displayStats() {
        if (createDB.primaryKeyExists(username)) {

        } else {
            createDB.insertData(username);
        }

    }

    public Deck getDeck() {
        return deck;
    }

    public GraphicsPanel getGp() {
        return graphicsPanel;
    }

    public void Logic() {
        if (deck.p_sum == deck.d_sum) {
            graphicsPanel.setMessage2("ITS A TIE!!!!");
            graphicsPanel.setMessage4("ITS A TIE!!!!");
        } else if (deck.d_sum > 21) {
            graphicsPanel.setMessage2("DEALER BUSTED, YOU WIN!");
            winningStatsUpdate();
        } else if (deck.p_sum > deck.d_sum && deck.p_sum <= 21) {
            graphicsPanel.setMessage2("YOU HAVE WON!");
            winningStatsUpdate();
        } else {
            graphicsPanel.setMessage2("YOU HAVE LOST!!!!");
            losingStatsUpdate();
        }
        b.setVisible(false);
        s.setVisible(false);
        playg.setVisible(true);
    }

    public void PlayerWin(boolean win) {
        if (win != true) {
            graphicsPanel.setMessage2("YOU LOOOOSE LLLLL!!!!!!!!");
            losingStatsUpdate();
            b.setVisible(false);
            s.setVisible(false);
            playg.setVisible(true);
        }
    }

    public void DealerWin(boolean win) {
        if (win != true) {
            graphicsPanel.setMessage4("DEALER LOOOOSE LLLLL!!!!!!!!");
            winningStatsUpdate();
            b.setVisible(false);
            s.setVisible(false);
            playg.setVisible(true);
        }

    }

    public void resetGame() {

        bet = 0;

        mainPanel.remove(graphicsPanel);
        graphicsPanel.Reset();
        deck = new Deck(1);
        card = new Card(deck.getCards());
        graphicsPanel = new GraphicsPanel(card.getImageCards());
        deck.p_sum = 0;
        deck.d_sum = 0;

        play.setVisible(false);
        instruct.setVisible(false);
        back.setVisible(false);
        playg.setVisible(false);

    }

    public void Stand() {
        s.setVisible(false);
        b.setVisible(false);

        new Thread(() -> {
            while (deck.d_sum <= 16) {
                int index = deck.DrawCard(deck.cards, false);
                graphicsPanel.dHit(index, deck.d_sum, deck.getValue(), deck.getSuit());
                card.getImageCards().remove(index);
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                boolean isBust = gameLogic.BustLogic(deck.d_sum);
                System.out.println(isBust);
                if (isBust) {
                    DealerWin(false);
                } else {

                }
            }
            //graphicsPanel.setMessage4("The Dealer has stood!");
            Logic();
        }).start();
        graphicsPanel.setMessage4("Dealer has stood!!!");

    }

    public void winningStatsUpdate() {
        createDB.UpdateData(username, createDB.plays + 1, 0, createDB.moneyBet + bet, createDB.moneyWon + bet, createDB.moneyLost, createDB.gamesWon + 1, createDB.gamesLost);
        graphicsPanel.repaint();
    }

    public void losingStatsUpdate() {
        createDB.UpdateData(username, createDB.plays + 1, 0, createDB.moneyBet + bet, createDB.moneyWon, createDB.moneyLost + bet, createDB.gamesWon, createDB.gamesLost + 1);
        graphicsPanel.repaint();
    }

    public void tieStatsUpdate() {
        createDB.UpdateData(username, createDB.plays + 1, 0, createDB.moneyBet + bet, createDB.moneyWon, createDB.moneyLost, createDB.gamesWon, createDB.gamesLost);

    }

    public void buttons() {
        resetbet = new JButton("Reset bet to 0!");
        resetbet.setVisible(false);
        resetbet.setBackground(VERY_LIGHT_BLUE);
        resetbet.addActionListener(this);
        mainPanel.add(resetbet);

        play = new JButton("Play");
        play.addActionListener(this);
        play.setBackground(VERY_LIGHT_BLUE);

        quit = new JButton("Quit");

        quit.addActionListener(this);
        quit.setBackground(VERY_LIGHT_BLUE);

        b = new JButton("Hit");
        b.setBounds(j.getWidth() / 2 - 160, 580, 100, 50);
        b.addActionListener(this);
        b.setBackground(VERY_LIGHT_BLUE);
        b.setVisible(false);
        mainPanel.add(b);

        play2 = new JButton("Play!");
        play2.addActionListener(this);
        play2.setBackground(VERY_LIGHT_BLUE);
        play2.setVisible(false);
        mainPanel.add(play2);

        s = new JButton("Stand");
        s.setBounds(j.getWidth() / 2 - 50, 580, 100, 50);
        s.setBackground(VERY_LIGHT_BLUE);
        s.addActionListener(this);
        s.setVisible(false);
        mainPanel.add(s);

        instruct = new JButton("Instructions");
        instruct.setBackground(VERY_LIGHT_BLUE);
        instruct.addActionListener(this);

        back = new JButton("Back");
        back.setBackground(VERY_LIGHT_BLUE);
        back.setVisible(false);
        back.addActionListener(this);

        playg = new JButton("Play Again");
        playg.setBounds(j.getWidth() / 2 - 160, 580, 100, 50);
        playg.setBackground(VERY_LIGHT_BLUE);
        playg.setVisible(false);
        playg.addActionListener(this);

        mainPanel.add(play);
        mainPanel.add(quit);
        mainPanel.add(back);
        mainPanel.add(playg);
        mainPanel.add(instruct);

        instruct.setBounds(j.getWidth() / 2 - 110, j.getHeight() / 2 + 70, 220, 50);
        play.setBounds(j.getWidth() / 2 - 110, j.getHeight() / 2, 100, 50);
        quit.setBounds(j.getWidth() / 2 + 10, j.getHeight() / 2, 100, 50);
    }

//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//
//                GUI g = new GUI();
//            }
//        });
//    }
}
