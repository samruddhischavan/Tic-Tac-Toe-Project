import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe {
    //set the dimentions of the window
    int boardWidth = 600;
    int boardHeight = 650; //50 px on the text panel on top

    //create a window
    JFrame frame = new JFrame("Tic-Tac-Toe");
    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel boardPanel = new JPanel();
    JButton[][] board = new JButton[3][3];
    JButton restart = new JButton("Restart");
    String playerX ="X";
    String playerO ="O";
    String currentplayer = playerX;
    boolean gameOver = false;
    int turns = 0;

    //constructor
    TicTacToe(){
        frame.setVisible(true);
        frame.setSize(boardWidth,boardHeight);
        frame.setLocationRelativeTo(null);   // set the window to pop from the center of the screen
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // terminates on clicking on exit symbol
        frame.setLayout(new BorderLayout());

        textLabel.setBackground(Color.darkGray);
        textLabel.setForeground(Color.white);
        textLabel.setFont(new Font("Arail", Font.BOLD, 50));
        textLabel.setHorizontalAlignment(JLabel.CENTER); //text in the centre of the label
        textLabel.setText("Tic-Tac-Toe");
        textLabel.setOpaque(true);

        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        frame.add(textPanel, BorderLayout.NORTH);

        boardPanel.setLayout(new GridLayout(3,3));
        boardPanel.setBackground(Color.darkGray);
        frame.add(boardPanel);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JButton tile = new JButton();
                board[i][j] = tile;
                boardPanel.add(tile);
                tile.setBackground(Color.darkGray);
                tile.setForeground(Color.white);
                tile.setFont(new Font("Arial", Font.BOLD,120));
                tile.setFocusable(false);

                tile.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e){
                        if(gameOver) return;
                        JButton tile = (JButton) e.getSource();
                        if(tile.getText() == ""){
                            tile.setText(currentplayer);
                            turns++;
                            checkWinner();
                            if(!gameOver){
                                currentplayer = currentplayer == playerX ? playerO : playerX;
                                textLabel.setText(currentplayer + "'s turn.");
                            }

                        }
                    }
                });
            }
        }
    }
    void checkWinner(){
        //horizontal
        for (int i = 0; i < 3; i++) {
            if(board[i][0].getText() == "") continue;

            if(board[i][0].getText() == board[i][1].getText() && board[i][1].getText() == board[i][2].getText()){
                for (int j = 0; j < 3; j++) {
                    setWinner(board[i][j]);
                }
                gameOver = true;
                return;
            }
        }
        //vertical
        for (int i = 0; i < 3; i++) {
            if(board[0][i].getText() =="") continue;

            if(board[0][i].getText() == board[1][i].getText() && board[1][i].getText() == board[2][i].getText()){
                for (int j = 0; j < 3; j++) {
                    setWinner(board[j][i]);
                }
                gameOver = true;
                return;
            }
        }
        //diagonal
        if(board[0][0].getText() == board[1][1].getText() && board[1][1].getText() == board[2][2].getText() && board[0][0].getText() !=""){
            for (int i = 0; i < 3; i++) {
                setWinner(board[i][i]);
            }
            gameOver = true;
            return;
        }
        //anti-diagonally
        if(board[0][2].getText() == board[1][1].getText() && board[1][1].getText() == board[2][0].getText() && board[0][2].getText() !=""){
            setWinner(board[0][2]);
            setWinner(board[1][1]);
            setWinner(board[0][2]);
            gameOver = true;
            return;
        }
        //tie
        if (turns == 9){
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    setTie(board[i][j]);
                }
            }
            gameOver = true;
        }
    }
    void setWinner(JButton tile){
        tile.setForeground(Color.green);
        tile.setBackground(Color.gray);
        textLabel.setText(currentplayer + " is the Winner!");
    }
    void setTie(JButton tile){
        tile.setForeground(Color.orange);
        tile.setBackground(Color.gray);
        textLabel.setText("Tie!");
    }
}
