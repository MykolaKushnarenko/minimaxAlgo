import sun.plugin2.message.Message;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class Test1 extends JFrame{

    public static final int cells = 3;
    ImageIcon iconempty = new ImageIcon("empty.jpg");
    ImageIcon iconX = new ImageIcon("iconX.jpg");
    ImageIcon icon0 = new ImageIcon("icon0.jpg");

    Test1() {
        super("Kрестики нолики");
        super.setVisible(true);
        this.setBounds(100, 100, 530, 350);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container container = this.getContentPane();
        container.setLayout(new GridBagLayout());
        GridBagConstraints gcb = new GridBagConstraints();
        gcb.gridx = 0;
        gcb.gridy = 0;
        container.add(new JLabel("Введите "),gcb);
        gcb.gridx = 1;
        container.add(new JLabel("начальное "),gcb);
        gcb.gridx = 2;
        container.add(new JLabel("состояния "),gcb);
        gcb.gridx = 0;
        gcb.gridy = 1;
        JButton[][] buttons = new JButton[cells][cells];
        for (int i = 0; i < cells; i++) {
            for (int j = 0; j < cells; j++) {
                buttons[i][j] =new ForButton();
                container.add(buttons[i][j], gcb);
                if ((j + 1) % 3 == 0) {
                    gcb.gridx = 0;
                    gcb.gridy += 1;
                } else {
                    gcb.gridx += 1;
                }
            }
        }
        gcb.gridx += 1;
        gcb.gridy += 1;
        container.add(new SaveButton("Cохранить ",buttons),gcb);
        gcb.gridx = 4;
        gcb.gridy = 1;
        container.add(new JLabel("\t       Введите количество шагов"),gcb);
        gcb.gridy += 1;
        JNumberTextField jTextField =new JNumberTextField();
        container.add(jTextField,gcb);
        gcb.gridy += 1;
        container.add(new DoButton("Сохранить и запустить",jTextField),gcb);
        repaint();
    }

    private class ForButton extends JButton{

        ForButton(){
            setSize(99, 99);
            setIcon(iconempty);
            addActionListener(new ButtonListener());
        }

        class ButtonListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                JButton btn = (JButton) e.getSource();
                if (btn.getIcon()==iconX )btn.setIcon(icon0);
                else if (btn.getIcon()==icon0 )btn.setIcon(iconempty);
                else btn.setIcon(iconX);
            }
        }
    }

    public class JNumberTextField extends JTextField {
        private static final long serialVersionUID = 1L;

        JNumberTextField(){
            //setSize(5,20);
            Dimension dimension = new Dimension(50,50);
            setMinimumSize(dimension);
            setText("12345");
        }

        @Override
        public void processKeyEvent(KeyEvent ev) {
            if (Character.isDigit(ev.getKeyChar())) {
                super.processKeyEvent(ev);
            }
            ev.consume();
        }

        public Integer getNumber() {
            Integer result = null;
            String text = getText();
            if (text != null && !"".equals(text)) {
                result = Integer.valueOf(text);
            }
            return result;
        }
    }

    private class DoButton extends JButton{

        JNumberTextField jTextField;
        Integer countOfMove;

        DoButton(String s, JNumberTextField jTextField ){
            setText(s);
            this.jTextField=jTextField;
            addActionListener(new ButtonListener());
        }

        class ButtonListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                if (jTextField.getNumber()<=0 || jTextField.getNumber()>=5) {
                    jTextField.setText("2");
                    JOptionPane.showMessageDialog(null, "Не коректно введено количество ходов");
                    return;
                }
                countOfMove=jTextField.getNumber();
            }
        }
    }

    private class SaveButton extends JButton{

        char person='-';
        char[][] cell = new char[3][3];
        JButton[][] buttons;

        SaveButton(String s,JButton[][] buttons){
            setText(s);
            this.buttons=buttons;
            addActionListener(new ButtonListener());
        }

        class ButtonListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                int countX=0,count0=0;
                for (int i = 0; i < cells; i++) {
                    for (int j = 0; j < cells; j++) {
                        if (buttons[i][j].getIcon()==iconX ) { cell[i][j]='X'; countX+=1;}
                        else if (buttons[i][j].getIcon()==icon0 ) { cell[i][j]='0'; count0+=1;}
                        else cell[i][j]='-';
                    }
                }

                if ((countX+1)==count0 || countX==count0) person='X';
                else if ((count0+1)==countX) person='0';
                else {
                    JOptionPane.showMessageDialog(null, "Невірно вказана початкова позиція");
                    Clear();
                    return;
                }

                if ((cell[0][0]==cell[0][1] && cell[0][0]==cell[0][2] && cell[0][2]!='-')  || (cell[1][0] ==cell[1][1] && cell[1][0]== cell[1][2] && cell[1][2]!='-')
                        || (cell[2][0]==cell[2][1] && cell[2][0]==cell[2][2] && cell[2][2]!='-') || (cell[0][0]==cell[1][1] && cell[0][0]==cell[2][2] && cell[2][2]!='-')
                        || (cell[0][2]==cell[1][1] && cell[0][2]==cell[2][0] && cell[2][0]!='-') || (cell[0][0]==cell[1][0] && cell[0][0]==cell[2][0] && cell[2][0]!='-')
                        || (cell[0][1]==cell[1][1] && cell[0][1]==cell[2][1] && cell[2][1]!='-') || (cell[0][2]==cell[1][2] && cell[0][2]==cell[2][2] && cell[2][2]!='-')
                        ){
                    JOptionPane.showMessageDialog(null, "Вже є виграшна комбінація");
                    Clear();
                    return;
                }
                //масив cells.  игрок которий должен походить person (cell[0][0]==cell[1][1] && cell[0][0]==cell[2][2])
            }

            private void Clear(){
                for (int i = 0; i < cells; i++) {
                    for (int j = 0; j < cells; j++) {
                        buttons[i][j].setIcon(iconempty);
                    }
                }
            }
        }
    }
}

