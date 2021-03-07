/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frog;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 *
 * @author trinh
 */
public class Control {

    private FrogGame f;
    private List<JButton> list = new ArrayList<>();
    private Timer timer;
    private JButton btnF = new JButton();
    private int yFrog = 60;
    int score = 0;
    Key key = new Key();

    public Control(FrogGame f) {
        this.f = f;
        btnF.addKeyListener(key);
        f.getjButton2().addKeyListener(key);
        f.getjButton3().addKeyListener(key);
        try {
            File file = new File("data.txt");
            if (file.exists()) {
                Object mes[] = {"New Game", "Continue", "Exit"};
                int option = JOptionPane.showOptionDialog(null, "Do you want to continue?",
                        "Notice!",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null, mes, mes[0]);
                if (option == 0) {
                    btnF.setBounds(40, yFrog, 40, 40);
                    f.jPanel1.add(btnF);
                    run();
                }
                if (option == 1) {
                    try {
                        FileReader fr = new FileReader("data.txt");
                        BufferedReader br = new BufferedReader(fr);
                        String line = "";
                        do {
                            line = br.readLine().trim();//read perline
                            if (line == null) {
                                break;
                            }
                            JButton btn = new JButton();
                            String txt[] = line.split(";");
                            int arr[] = new int[txt.length];
                            for (int i = 0; i < arr.length; i++) {
                                arr[i] = Integer.parseInt(txt[i]);
                            }
                            if (arr.length == 4) {
                                btn.setBounds(arr[0], arr[1], arr[2], arr[3]);
                                list.add(btn);
                                f.jPanel1.add(btn);
                            } else {
                                count = arr[0];
                                score = arr[1];
                                f.jLabel1.setText("Point: " + score / 2);
                                yFrog = arr[2];
                            }
                        } while (true);
                        br.close();
                    } catch (Exception e) {
                    }
                    f.getjPanel1().add(btnF);
                    btnF.requestFocus();
                    run();
                }
                if(option==2){
                    System.exit(0);
                }
            } else {
                btnF.setBounds(40, yFrog, 40, 40);
                f.jPanel1.add(btnF);
                run();
            }
        } catch (Exception e) {
        }
    }

    public void countScore() {
        for (JButton o : list) {
            if (o.getX() == btnF.getX()) {
                score++;
                f.jLabel1.setText("Point: " + score / 2);
            }
        }
    }

    public void addButton() {
        JButton btnUp = new JButton();
        JButton btnDown = new JButton();
        Random r = new Random();
        int hUp = r.nextInt(30) + 80;
        int yDown = r.nextInt(40) + 160;

        btnUp.setBounds(424, 0, 40, hUp);
        btnDown.setBounds(424, yDown, 40, 258 - yDown);
        list.add(btnUp);
        list.add(btnDown);
        f.jPanel1.add(btnUp);
        f.jPanel1.add(btnDown);
    }
    int count = 0;

    boolean checkPause = false;

    public void pause() {
        if (!checkPause) {
            timer.stop();
            checkPause = true;
        } else {
            timer.restart();
            checkPause = false;
        }
    }

    public boolean checkTouch() {
        //check cham tren duoi
        if (yFrog <= 0 || yFrog >= 218) {
            return false;
        }
        for (JButton o : list) {
            Rectangle recF = new Rectangle(btnF.getX(), yFrog, btnF.getWidth(), btnF.getHeight());
            Rectangle recCol = new Rectangle(o.getX(), o.getY(), o.getWidth(), o.getHeight());
            if (recF.intersects(recCol)) {
                return false;
            }
        }
        return true;
    }

    public void showMess() {
        if (!checkSave) {
            Object mes[] = {"New Game", "Exit"};
            int option = JOptionPane.showOptionDialog(null, "Do you want to continue?",
                    "Notice!",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null, mes, mes[0]);
            if (option == 0) {
                //new game
                f.jPanel1.removeAll();
                f.jPanel1.repaint();
                yFrog = 60;
                f.jPanel1.add(btnF);
                list.clear();
                count = 0;
                score = 0;
                f.jLabel1.setText("Point: 0");
                timer.restart();

            }
            if (option == 1) {
                System.exit(0);
            }
        } else {
            Object mes[] = {"New Game", "Continue", "Exit"};
            int option = JOptionPane.showOptionDialog(null, "Do you want to continue?",
                    "Notice!",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null, mes, mes[0]);
            if (option == 0) {
                //new game
                f.jPanel1.removeAll();
                f.jPanel1.repaint();
                yFrog = 60;
                f.jPanel1.add(btnF);
                list.clear();
                count = 0;
                score = 0;
                f.jLabel1.setText("Point: 0");
                timer.restart();

            }
            if (option == 1) {
                f.jPanel1.removeAll();
                f.jPanel1.repaint();
                list.clear();
                try {
                    FileReader fr = new FileReader("data.txt");
                    BufferedReader br = new BufferedReader(fr);
                    String line = "";
                    do {
                        line = br.readLine().trim();//read perline
                        if (line == null) {
                            break;
                        }
                        JButton btn = new JButton();
                        String txt[] = line.split(";");
                        int arr[] = new int[txt.length];
                        for (int i = 0; i < arr.length; i++) {
                            arr[i] = Integer.parseInt(txt[i]);
                        }
                        if (arr.length == 4) {
                            btn.setBounds(arr[0], arr[1], arr[2], arr[3]);
                            list.add(btn);
                            f.jPanel1.add(btn);
                        } else {
                            count = arr[0];
                            score = arr[1];
                            f.jLabel1.setText("Point: " + score / 2);
                            yFrog = arr[2];
                        }
                    } while (true);
                    br.close();
                } catch (Exception e) {
                }
                f.getjPanel1().add(btnF);
                timer.restart();
            }
            if (option == 2) {
                System.exit(0);
            }
        }
    }

    boolean checkSave = false;

    public void saveGame() {
        checkSave = true;
        try {
            FileWriter fw = new FileWriter("data.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            for (JButton o : list) {
                int x = o.getX();
                int y = o.getY();
                int w = o.getWidth();
                int h = o.getHeight();
                bw.write(x + ";" + y + ";" + w + ";" + h);
                bw.newLine();
            }
            bw.write(count + ";" + score + ";" + yFrog);
            bw.newLine();
            bw.close();
        } catch (Exception e) {
        }
    }

    public void run() {
        timer = new Timer(15, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < list.size(); i++) {
                    int x = list.get(i).getX() - 1;
                    int y = list.get(i).getY();
                    list.get(i).setLocation(x, y);
                    if (x <= -40) {
                        list.remove(i);
                        i--;
                    }
                }
                countScore();
                if (key.isPress()) {
                    yFrog = yFrog - 15;
                    key.setPress(false);
                }
                if (!checkTouch()) {
                    timer.stop();
                    showMess();
                }
                yFrog++;
                btnF.setBounds(40, yFrog, 40, 40);
                count++;
                if (count == 100) {
                    addButton();
                    count = 0;
                }
            }
        });
        timer.start();
    }

}
