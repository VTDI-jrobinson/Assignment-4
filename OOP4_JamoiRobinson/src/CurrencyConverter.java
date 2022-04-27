import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

//Jamoi Robinson
//Maxine Smith
//Ramoy Roberts
//Johnathan Beckford-Adams

public class CurrencyConverter extends JFrame {
    private JPanel Main;
    private JLabel amountInput;
    private JTextField tfAmountInput;
    private JLabel currencyType;
    private JLabel amount;
    private JTextField tfAmountOutput;
    private JButton btnConvert;
    private JButton btnClear;
    private JComboBox ddCurrencyType;

    public CurrencyConverter()
    {
        btnConvert.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (tfAmountInput.getText().isEmpty())
                {
                    JOptionPane.showMessageDialog(Main, "Input should be entered!");

                }
                else
                {
                    Double inputAmount = Double.valueOf(tfAmountInput.getText());
                    String currency = String.valueOf(ddCurrencyType.getSelectedItem());

                    tfAmountOutput.setText(String.valueOf(calculateAmount(inputAmount, currency)));
                }

            }
        });
        btnClear.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                tfAmountInput.setText("");
                ddCurrencyType.getModel().setSelectedItem("US");
                tfAmountOutput.setText("");
            }
        });
    }

    public Double calculateAmount(Double amount, String currency)
    {
        Map<String, Double> currencyChart = new HashMap<>();
        currencyChart.put("CAN", 97.50);
        currencyChart.put("US", 129.02);
        currencyChart.put("Euro", 164.33);

        Double result = 0.0;

        for(int x = 0; x < currencyChart.size(); x++)
            if (currencyChart.get(currency) != null)
            {
                amount = amount * currencyChart.get(currency);
                BigDecimal bd = new BigDecimal(amount).setScale(2, RoundingMode.HALF_UP);
                amount = bd.doubleValue();
                break;
            }

        return amount;
    }

    public static void main(String args[])
    {
        CurrencyConverter gui = new CurrencyConverter();
        gui.setContentPane(new CurrencyConverter().Main);
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setVisible(true);

        gui.pack();
    }
}
