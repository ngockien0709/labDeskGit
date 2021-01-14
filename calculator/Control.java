
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Control implements ActionListener {

    private Main m;
    private boolean process = false;
    private boolean reset = false;
    private int operate = 0;
    private BigDecimal firstNum;
    private BigDecimal secondNum;
    private BigDecimal memory = new BigDecimal("0");

    public Control(Main m) {
        this.m = m;
        pressButton();
    }

    public void pressButton() {
        m.getBtn0().addActionListener(this);
        m.getBtn1().addActionListener(this);
        m.getBtn2().addActionListener(this);
        m.getBtn3().addActionListener(this);
        m.getBtn4().addActionListener(this);
        m.getBtn5().addActionListener(this);
        m.getBtn6().addActionListener(this);
        m.getBtn7().addActionListener(this);
        m.getBtn8().addActionListener(this);
        m.getBtn9().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();//get text in button
        if (process) {
            m.txtScreen.setText("0");
            process = false;
            reset = false;
        }
        String txt = m.txtScreen.getText() + cmd;
        BigDecimal number = new BigDecimal(txt);
        m.txtScreen.setText(number + "");
    }

    public void calculate(int operate) {
        operator();
        process = true;
        this.operate = operate;
    }

    public BigDecimal getValue() {
        return new BigDecimal(m.txtScreen.getText());
    }

    public void operator() {
        if (!process) {
            if (operate == 0) {
                firstNum = getValue();
            } else {
                secondNum = getValue();
                switch (operate) {
                    case 1://add
                        firstNum = firstNum.add(secondNum);
                        break;
                    case 2://sub
                        firstNum = firstNum.subtract(secondNum);
                        break;
                    case 3://multi
                        firstNum = firstNum.multiply(secondNum);
                        break;
                    case 4://devi
                        if (secondNum.doubleValue() == 0) {
                            m.txtScreen.setText("ERROR");
                            return;
                        }
                        firstNum = firstNum.divide(secondNum, 20, RoundingMode.HALF_UP).stripTrailingZeros();
                        firstNum = new BigDecimal(firstNum.toPlainString());
                        break;
                }
                m.txtScreen.setText(firstNum + "");
            }
        }
    }

    public void pressResult() {
        operator();
        process = true;
        operate = 0;
    }

    public void pressPercen() {
        double value = getValue().doubleValue() / 100;
        m.txtScreen.setText(value + "");
        firstNum = getValue();
        process = true;
//        reset = true;
    }

    public void pressClear() {
        process = false;
        operate = 0;
        firstNum = new BigDecimal("0");
        secondNum = new BigDecimal("0");
        m.txtScreen.setText("0");
    }

    public void pressInvert() {
        double value = getValue().doubleValue();
        double rs = 0;
        if (value != 0) {
            rs = 1 / value;
            m.txtScreen.setText(new BigDecimal(rs)+"");
            firstNum = getValue();
        } else {
            m.txtScreen.setText("ERROR");
        }
        process = true;
        //reset = true;
    }

    public void pressSqrt() {
        double value = getValue().doubleValue();
        double rs = 0;
        if (value >= 0) {
            rs = Math.sqrt(value);
            m.txtScreen.setText(rs + "");
            firstNum = getValue();
        } else {
            m.txtScreen.setText("ERROR");
        }
        process = true;
//        reset = true;
    }

    public void pressSwap() {
        String txt = m.txtScreen.getText();
        if(txt.equals("ERROR")){
            return;
        }
        if(txt.startsWith("-")){
            txt = txt.substring(1);
        }else{
            txt = "-"+txt;
        }
        m.txtScreen.setText(txt);
    }

    public void pressDot() {
        String txt = m.txtScreen.getText();
        if (!txt.contains(".")) {
            txt += ".";
        }
        m.txtScreen.setText(txt);
    }

    public void pressMAdd() {
        memory = memory.add(getValue());
        process = true;
    }

    public void pressMSub() {
        memory = memory.subtract(getValue());
         process = true;
    }

    public void pressMR() {
        m.txtScreen.setText(memory + "");
    }

    public void pressMClear() {
        memory = new BigDecimal("0");
    }
    public void removeLast(){
        String txt = m.txtScreen.getText();
        if(txt.length()==2 && txt.startsWith("-")){
            m.txtScreen.setText("0");
            return;
        }
        if(txt.length()==1){
            m.txtScreen.setText("0");
            return;
        }
        txt = txt.substring(0, txt.length()-1);
        m.txtScreen.setText(txt);
    }

}
