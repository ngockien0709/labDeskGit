
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author nguye
 */
public class Control {

    PuzzleGUI pz;
    int size = 3;
    JButton[][] matrix;
    int move = 0;
    boolean isStart = false;
    Timer timer;
    int time = 0;
    boolean isNewGame=false;

    public Control(PuzzleGUI pz) {
        this.pz = pz;
        addButton();
    }
    
    
    public void newGame(){
        move=0;
        time=0;
        pz.getTxtElapsed().setText("0");
        pz.getTxtMoveCount().setText("0");
        countTime();
        addButton();
        isStart=true;
        isNewGame=true;
    }

    public void addButton() {
        size = pz.getCbxSize().getSelectedIndex() + 3;
        pz.getPnLayout().removeAll();
        pz.pnLayout.setLayout(new GridLayout(size, size, 10, 10));
        pz.pnLayout.setPreferredSize(new Dimension(size * 100, size * 100));
        matrix = new JButton[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                JButton btn = new JButton(i * size + j + 1 + "");
                matrix[i][j] = btn;
                pz.pnLayout.add(btn);
                btn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(!isNewGame){
                            JOptionPane.showMessageDialog(pz, "start first");
                            return;
                        }
                        if (checkMove(btn)) {
                            moveButton(btn);
                            if (checkWin()) {
                                timer.stop();
                                isStart=false;
                                isNewGame=false;
                                JOptionPane.showMessageDialog(pz, "win!!!");
                            }
                        }
                    }
                });
            }
        }
        matrix[size - 1][size - 1].setText("");
        mixButton();
        pz.pack();

    }

    public Point getEmptyPos() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (matrix[i][j].getText().equals("")) {
                    return new Point(i, j);
                }
            }
        }
        return null;
    }

    public void mixButton() {
        for (int k = 0; k < 1000; k++) {
            Point p = getEmptyPos();
            int i = p.x;
            int j = p.y;

            Random r = new Random();
            int choice = r.nextInt(4);
            switch (choice) {
                case 0://up
                    if (i > 0) {
                        String txt = matrix[i - 1][j].getText();
                        matrix[i][j].setText(txt);
                        matrix[i - 1][j].setText("");
                    }
                    break;
                case 1://down
                    if (i > size - 1) {
                        String txt = matrix[i + 1][j].getText();
                        matrix[i][j].setText(txt);
                        matrix[i + 1][j].setText("");
                    }
                    break;
                case 2://left
                    if (j > 0) {
                        String txt = matrix[i][j - 1].getText();
                        matrix[i][j].setText(txt);
                        matrix[i][j - 1].setText("");
                    }
                    break;
                case 3://right
                    if (j < size - 1) {
                        String txt = matrix[i][j + 1].getText();
                        matrix[i][j].setText(txt);
                        matrix[i][j + 1].setText("");
                    }
                    break;
            }
        }
    }

    public boolean checkMove(JButton btn) {
        Point p = getEmptyPos();
        int i1 = 0, j1 = 0;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (btn.getText().equals(matrix[i][j].getText())) {
                    i1 = i;
                    j1 = j;
                    break;
                }

            }
        }

        if (p.x == i1 && (Math.abs(p.y - j1) == 1)) {
            return true;
        }
        if (p.y == j1 && Math.abs(p.x - i1) == 1) {
            return true;
        }
        return false;
    }

    public void moveButton(JButton btn) {
        Point p = getEmptyPos();
        String txt = btn.getText();
        matrix[p.x][p.y].setText(txt);
        btn.setText("");
        move++;
        pz.getTxtMoveCount().setText(move + "");
    }

    public boolean checkWin() {
        if (matrix[size - 1][size - 1].getText().equals("")) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (i == size - 1 && j == size - 1) {
                        return true;
                    }
                    if (!matrix[i][j].getText().equals(i * size + j + 1 + "")) {
                        return false;
                    }

                }
            }
            return true;
        }
        return false;
    }

    public void countTime() {
        if (isStart) {
            timer.stop();
        }
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                time++;
                pz.getTxtElapsed().setText(time+"");
            }
        });
        timer.start();
    }
}
