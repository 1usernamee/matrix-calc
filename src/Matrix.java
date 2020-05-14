import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Matrix implements ActionListener
{
    private static int col, row;
    private static double myMatrix [][];
    private static double tempMatrix [][];
    private static JTextField inputField [][];
    private static int result;
    private static JButton minusB, plusB, inverseB,
            multiplyB, nMultiplyB, nDivisionB,
            getValueB, showMatrix, transposing,
            newMatrix;
    private static JPanel choosePanel [] = new JPanel[8];
    private static int lastCol , lastRow ;


    public Matrix ()
    {
        col = row = 0;
        myMatrix = new double [0][0];
        ChooseOperation();
    }

    private static void getDimension()
    {
        JTextField lField = new JTextField(5);
        JTextField wField = new JTextField(5);

        JPanel choosePanel [] = new JPanel [2];
        choosePanel [0] = new JPanel();
        choosePanel [1] = new JPanel();
        choosePanel[0].add(new JLabel("Введите размеры") );
        choosePanel[1].add(new JLabel("строки:"));
        choosePanel[1].add(lField);
        choosePanel[1].add(Box.createHorizontalStrut(15));
        choosePanel[1].add(new JLabel("столбцы:"));
        choosePanel[1].add(wField);

        result = JOptionPane.showConfirmDialog(null, choosePanel,
                null,JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);

        lastCol = col;
        lastRow = row;

        if(result == 0)
        {

            if(wField.getText().equals(""))
                col = 0;
            else
            {
                if(isInt(wField.getText()))
                {
                    col = Integer.parseInt(wField.getText());
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Неверный размер");
                    col = lastCol;
                    row = lastRow;
                    return;
                }

                if(isInt(lField.getText()))
                {
                    row = Integer.parseInt(lField.getText());
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Неверный размер");
                    col = lastCol;
                    row = lastRow;
                    return;
                }

            }
            if(col < 1 || row < 1)
            {
                JOptionPane.showConfirmDialog(null, "Вы ввели неверный размер",
                        "Ошибка",JOptionPane.PLAIN_MESSAGE);
                col  = lastCol;
                row = lastRow;

            }
            else
            {
                tempMatrix = myMatrix;
                myMatrix = new double [row][col];
                if(!setElements(myMatrix, "Заполните матрицу"))
                {
                    myMatrix = tempMatrix;
                }
            }
        }
        else if(result == 1)
        {
            col = lastCol;
            row = lastRow;
        }
    }

    private static boolean setElements(double matrix [][], String title )
    {
        int temp, temp1;
        String tempString;

        JPanel choosePanel [] = new JPanel [row+2];
        choosePanel[0] = new JPanel();
        choosePanel[0].add(new Label(title ));
        choosePanel[choosePanel.length-1] = new JPanel();
        choosePanel[choosePanel.length-1].add(new Label("пустые поля - это нули"));
        inputField  = new JTextField [matrix.length][matrix[0].length];

        for(temp = 1; temp <= matrix.length; temp++)
        {
            choosePanel[temp] = new JPanel();

            for(temp1 = 0; temp1 < matrix[0].length; temp1++)
            {
                inputField [temp-1][temp1] = new JTextField(3);
                choosePanel[temp].add(inputField [temp-1][temp1]);

                if(temp1 < matrix[0].length -1)
                {
                    choosePanel[temp].add(Box.createHorizontalStrut(15));
                }

            }

        }

        result = JOptionPane.showConfirmDialog(null, choosePanel,
                null, JOptionPane.OK_OPTION, JOptionPane.PLAIN_MESSAGE);


        if(result == 0)
        {
            checkTextField(inputField);
            for(temp = 0; temp < matrix.length; temp++)
            {
                for(temp1 = 0; temp1 < matrix[0].length; temp1++)
                {
                    tempString = inputField[temp][temp1].getText();


                    if(isDouble(tempString))
                    {
                        matrix [temp][temp1] = Double.parseDouble(inputField[temp][temp1].getText());
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Вы ввели неверные элементы");

                        col = lastCol;
                        row = lastRow;

                        return false;
                    }
                }
            }
            return true;
        }
        else
            return false;


    }

    private static void checkTextField (JTextField field [][] )
    {
        for(int temp = 0; temp < field.length; temp++)
        {
            for(int temp1 = 0; temp1 < field[0].length; temp1++)
            {
                if(field[temp][temp1].getText().equals(""))
                    field[temp][temp1].setText("0");
            }
        }
    }

    private void ChooseOperation ()
    {
        int temp;

        for(temp = 0; temp < choosePanel.length; temp++)
        {
            choosePanel [temp] = new JPanel ();
        }

        choosePanel[1].add(Box.createHorizontalStrut(15));
        choosePanel[6].add(Box.createHorizontalStrut(15));

        showMatrix = new JButton ("Показать матрицу");
        showMatrix.setPreferredSize(new Dimension(175,35));
        showMatrix.addActionListener(this);
        choosePanel[2].add(showMatrix);

        plusB = new JButton ("Сложение с матрицей");
        plusB.setPreferredSize(new Dimension(175,35));
        plusB.addActionListener(this);
        choosePanel[2].add(plusB);

        minusB = new JButton ("Вычитание матрицы");
        minusB.setPreferredSize(new Dimension(175,35));
        minusB.addActionListener(this);
        choosePanel[2].add(minusB);

        multiplyB = new JButton ("Умножение на матрицу");
        multiplyB.setPreferredSize(new Dimension(175,35));
        multiplyB.addActionListener(this);
        choosePanel[3].add(multiplyB);

        nMultiplyB = new JButton ("Умножение на число");
        nMultiplyB.setPreferredSize(new Dimension(175,35));
        nMultiplyB.addActionListener(this);
        choosePanel[3].add(nMultiplyB);

        nDivisionB = new JButton ("Деление на число");
        nDivisionB.setPreferredSize(new Dimension(175,35));
        nDivisionB.addActionListener(this);
        choosePanel[3].add(nDivisionB);

        transposing = new JButton ("Транспонирование");
        transposing.setPreferredSize(new Dimension(175,35));
        transposing.addActionListener(this);
        choosePanel[4].add(transposing);

        if(col == row )
        {
            getValueB = new JButton ("Найти определитель");
            getValueB.setPreferredSize(new Dimension(175,35));
            getValueB.addActionListener(this);
            choosePanel[4].add(getValueB);

            inverseB = new JButton ("Инвертирование");
            inverseB.setPreferredSize(new Dimension(175,35));
            inverseB.addActionListener(this);
            choosePanel[4].add(inverseB);

        }

        newMatrix = new JButton("Новая матрица");
        newMatrix.setPreferredSize(new Dimension(275,35));
        newMatrix.addActionListener(this);
        choosePanel[5].add(newMatrix);

        JOptionPane.showConfirmDialog(null, choosePanel, null,
                JOptionPane.CLOSED_OPTION , JOptionPane.PLAIN_MESSAGE);

    }

    public  void actionPerformed(ActionEvent e)
    {

        if(e.getSource() == showMatrix)
        {
            showMatrix( myMatrix, "Ваша матрица");
        }
        if(e.getSource() == plusB)
        {
            matrixPlusMatrix();
        }

        else if(e.getSource() == minusB)
        {
            matrixMinusMatrix();
        }

        else    if(e.getSource() == multiplyB)
        {
            multiplyByMatrix();
        }
        else   if(e.getSource() == inverseB)
        {
            inverse();
        }

        else    if(e.getSource() ==  nMultiplyB)
        {
            guiMultliplyByScaler();
        }
        else   if(e.getSource() == nDivisionB)
        {
            divideByScaler ();
        }

        else   if(e.getSource() == transposing )
        {
            guiTransposing(myMatrix);
        }

        else   if(e.getSource() == getValueB)
        {
            guiGetValue();
        }

        else   if(e.getSource() == newMatrix)
        {
            newMatrix();
        }
    }


    private static void showMatrix(double [][] matrix, String title )
    {
        int temp, temp1;

        JPanel choosePanel [] = new JPanel [matrix.length+1];
        choosePanel[0] = new JPanel ();
        choosePanel[0].add( new JLabel (title) );

        for(temp = 1; temp <= matrix.length; temp++)
        {
            choosePanel[temp] = new JPanel();


            for(temp1 = 0; temp1 < matrix[0].length; temp1++)
            {
                if(matrix[temp-1][temp1] == -0)
                {
                    matrix[temp-1][temp1] = 0;
                }
                choosePanel[temp].add(new JLabel(String.format("%.2f", matrix[temp-1][temp1])));

                if(temp1 < matrix[0].length -1)
                {
                    choosePanel[temp].add(Box.createHorizontalStrut(15));
                }

            }

        }

        if(col == 0 || row == 0)
        {
            JOptionPane.showMessageDialog(null, "Вы не ввели матрицу");
        }
        else
        {

            JOptionPane.showMessageDialog(null, choosePanel, null,
                    JOptionPane.PLAIN_MESSAGE, null);
        }
    }

    private static void matrixPlusMatrix ()
    {
        if(myMatrix.length < 1)
        {
            JOptionPane.showMessageDialog(null, "Вы не ввели матрицу");
        }
        else
        {
            double m2[][]=new double [row][col];
            double sum[][] = new double [row][col];

            if(setElements(m2, "Заполните матрицу суммирования"))
            {

                for(int i=0;i<row;i++)
                {
                    for(int j=0;j<col;j++)
                    {
                        sum[i][j]=myMatrix[i][j]+m2[i][j];
                    }
                }
                showMatrix(sum, "Результат суммирования");
            }
        }
    }

    private static void matrixMinusMatrix ()
    {

        if(myMatrix.length < 1)
        {
            JOptionPane.showMessageDialog(null, "Вы не ввели матрицу");
        }
        else
        {
            double m2[][]=new double [row][col];
            double sub[][] = new double [row][col];
            double temp [][] = new double [row][col];


            if(setElements(m2, "Заполните матрицу вычитания"))
            {

                for(int i=0;i<row;i++)
                {
                    for(int j=0;j<col;j++)
                    {
                        temp[i][j]=(-1*m2[i][j]);
                        sub[i][j]=myMatrix[i][j]+temp[i][j];
                    }
                }
                showMatrix(sub, "Результат вычитания");
            }
        }
    }

    private static void multiplyByMatrix ()
    {

        JTextField wField = new JTextField(5);
        int col2 = 0;
        double m2 [][] , results[][];
        int sum;

        if(myMatrix.length < 1)
        {
            JOptionPane.showMessageDialog(null, "Вы не ввели матрицу");
        }
        else
        {

            JPanel choosePanel [] = new JPanel [2];
            choosePanel [0] = new JPanel();
            choosePanel [1] = new JPanel();

            choosePanel[0].add(new JLabel("Введите размер") );

            choosePanel[1].add(new JLabel("строки:"));
            choosePanel[1].add(new JLabel(""+col));
            choosePanel[1].add(Box.createHorizontalStrut(15));
            choosePanel[1].add(new JLabel("столбцы:"));
            choosePanel[1].add(wField);


            result = JOptionPane.showConfirmDialog(null, choosePanel,
                    null,JOptionPane.PLAIN_MESSAGE,
                    JOptionPane.PLAIN_MESSAGE);

            if(result == 0)
            {
                if(wField.getText().equals(""))
                {
                    col2 = 0;
                }
                else
                {
                    if(isInt(wField.getText()) )
                    {
                        col2 = Integer.parseInt(wField.getText());

                    }
                }

                m2 = new double [col][col2];
                results = new double [row][col2];
                if(setElements(m2, "Заполните матрицу умножения"))
                {

                    for ( int i = 0 ; i < row ; i++ )
                    {
                        for ( int j = 0 ; j < col2 ; j++ )
                        {
                            sum = 0;
                            for ( int k = 0 ; k < col ; k++ )
                            {
                                sum +=  myMatrix[i][k]*m2[k][j];
                            }

                            results[i][j] = sum;

                        }
                    }

                    showMatrix(results, "Результат умножения");
                }
            }
            else
                return;
        }
    }

    private static void guiMultliplyByScaler()
    {

        double[][]results=new double [row][col];
        double x;
        String tempString;

        if(myMatrix.length < 1)
        {
            JOptionPane.showMessageDialog(null, "Вы не ввели матрицу");
            return;
        }

        tempString = JOptionPane.showInputDialog(null,
                "Введите множитель");

        if (tempString == null) //cancel option
        {
            return;
        }

        else if(!tempString.equals(""))
            x= Double.parseDouble(tempString);
        else
        {
            JOptionPane.showMessageDialog(null, "Вы не ввели множитель");
            return;
        }
        results = multliplyByScaler(myMatrix, x);
        showMatrix(results, "Результат умножения");

    }


    private static double [][] multliplyByScaler (double [][] matrix , double x)
    {

        double[][]results=new double [row][col];
        int i,j;

        for (i=0;i<matrix.length;i++)
        {
            for(j=0;j<matrix[0].length;j++)
            {
                results[i][j] = x*matrix[i][j];
            }
        }
        return results;
    }


    private static void divideByScaler ()
    {
        double[][]results=new double [row][col];
        int i,j;
        double x;
        String tempString;

        if(myMatrix.length < 1)
        {
            JOptionPane.showMessageDialog(null, "Вы не ввели матрицу");
            return;
        }

        tempString = JOptionPane.showInputDialog("Введите делитель");


        if (tempString == null)
        {
            return;
        }

        else if(!tempString.equals(""))
            x= Double.parseDouble(tempString);

        else
        {
            JOptionPane.showMessageDialog(null, "Вы не ввели делитель");
            return;
        }

        if(x == 0)
        {
            JOptionPane.showMessageDialog(null, "Деление на 0 недопустимо");
            return;
        }

        for (i=0;i<row;i++)
        {
            for(j=0;j<col;j++)
            {
                results[i][j] = myMatrix[i][j] / x;
            }
        }
        showMatrix(results, "Результат деления");


    }

    private static void guiGetValue ()
    {
        if(myMatrix.length < 1)
        {
            JOptionPane.showMessageDialog(null, "Вы не ввели матрицу");
        }
        else if(col != row)
        {
            JOptionPane.showMessageDialog(null, "Введите квадратную матрицу");
        }
        else
        {
            double result = getValue(myMatrix);

            JOptionPane.showMessageDialog(null, String.format("Значение определителя = %.2f",
                    getValue(myMatrix) )  , null,
                    JOptionPane.PLAIN_MESSAGE, null);
        }
    }

    private static void swap (double [] res1, double [] res2)
    {
        int temp;
        double tempDouble;

        for(temp = 0; temp < res1.length;temp++)
        {
            tempDouble = res1[temp];
            res1[temp] = res2[temp];
            res2[temp] = tempDouble;
        }

    }
    private static double getValue (double [][] matrix)
    {
        int temp, temp1, temp2;
        double coeficient;
        double result = 1;
        int sign = 1;
        int zeroCounter ;

        double res[][] = new double [matrix.length][matrix[0].length];

        for(temp = 0; temp < matrix.length; temp++)
        {
            for(temp1 = 0; temp1 < matrix[0].length; temp1++)
            {
                res[temp][temp1] = matrix[temp][temp1];;
            }

        }

        for(temp = 0; temp < res.length; temp++)
        {
            if(res[temp][temp] != 0)
                continue;

            for(temp1 = 1; temp1 < res.length -1 ; temp1++)
            {
                if( res[ (temp1 + temp ) % matrix.length][temp] != 0)
                {
                    swap(res[temp], res[(temp1 + temp ) % res.length]);
                    sign *= -1;
                    break;
                }
            }
        }

        for(temp = 1; temp < res.length; temp++)
        {
            for(temp1 = 0; temp1 < temp; temp1++)
            {
                if(res[temp][temp1] == 0 || res[temp1][temp1] == 0)
                    continue;
                else
                {
                    zeroCounter = 0;
                    coeficient = res[temp][temp1]/res[temp1][temp1];
                }
                for(temp2 = 0; temp2 < res.length; temp2++)
                {
                    res[temp][temp2] = res[temp][temp2]
                            - res[temp1][temp2] * coeficient;

                    if(res[temp][temp2] == 0 )
                        zeroCounter++;
                }

                if(temp < res.length -1 && zeroCounter > temp)
                {
                    swap(res[temp], res[temp+1]);
                    sign *= -1;
                    temp--;
                }
            }
        }

        for(temp = 0; temp < res.length; temp++)
        {
            result *= res[temp][temp];
        }
        return result * sign;
    }

    private static void guiTransposing (double [][] matrix)
    {
        if(myMatrix.length < 1)
        {
            JOptionPane.showMessageDialog(null, "Вы не ввели матрицу");
            return ;
        }


        double [][] transMatrix = new double[matrix[0].length][matrix.length];

        transMatrix = transposing(matrix);

        showMatrix(transMatrix, "Транспонирование");
    }

    private static double [][] transposing (double [][] matrix)
    {
        double [][] transportMatrix = new double[matrix[0].length][matrix.length];
        int temp1, temp;

        for(temp = 0 ; temp < matrix[0].length; temp++)
        {
            for(temp1 = 0; temp1 < matrix.length; temp1++)
            {
                transportMatrix[temp][temp1]  = matrix[temp1][temp];
            }
        }
        return transportMatrix;
    }


    private static double [][] getMinor (int i, int j)
    {
        double [][] results = new double [row-1 ][col-1];
        int row_count = 0, col_count = 0;
        int temp, temp1;

        for(temp = 0; temp < row; temp++)
        {
            for(temp1 = 0; temp1 < col; temp1++)
            {
                if(temp != i && temp1 != j)
                {
                    results[row_count][col_count] = myMatrix[temp][temp1];
                    col_count++;
                }
            }

            col_count = 0;
            if(i != temp)
                row_count++;
        }

        return results;
    }


    private static void  inverse  ()
    {
        if(myMatrix.length < 1)
        {
            JOptionPane.showMessageDialog(null, "Вы не ввели матрицу");
            return;
        }
        else if (col != row)
        {
            JOptionPane.showMessageDialog(null, "Введите квадратную матрицу");
            return;
        }

        else if(getValue(myMatrix) == 0)
        {
            JOptionPane.showMessageDialog(null, "Ваша матрица "
                    + "не имеет обратную\n\n"
                    + "поскольку принимает значение = 0");
            return;
        }

        double [][] inverseMatrix = new double[row][col];
        double [][] minor = new double [row -1 ][col -1];
        double [][] cofactor = new double [row ][col];
        double delta;
        int temp , temp1;

        for(temp = 0; temp < row; temp++)
        {
            for(temp1 = 0; temp1 < col; temp1++)
            {
                minor = getMinor (temp, temp1);
                double minorValue = getValue(minor);
                cofactor[temp][temp1] = Math.pow(-1.0, temp+temp1) * getValue(minor);
            }
        }

        cofactor = transposing(cofactor);
        delta = getValue(myMatrix);

        for(temp = 0; temp < row; temp++)
        {
            for(temp1 = 0; temp1 < col; temp1++)
            {
                inverseMatrix[temp][temp1] = cofactor[temp][temp1] / delta;
            }
        }

        showMatrix(inverseMatrix, "Ивертированная матрица");

    }

    private static boolean isInt (String str)
    {
        int temp;
        if (str.length() == '0')
            return false;

        for(temp = 0; temp < str.length();temp++)
        {
            if(str.charAt(temp) != '+' && str.charAt(temp) != '-'
                    && !Character.isDigit(str.charAt(temp)))
            {
                return false;
            }
        }
        return true;
    }

    private static boolean isDouble (String str)
    {
        int temp;
        if (str.length() == '0')
            return false;

        for(temp = 0; temp < str.length();temp++)
        {
            if(str.charAt(temp) != '+' && str.charAt(temp) != '-'
                    && str.charAt(temp) != '.'
                    && !Character.isDigit(str.charAt(temp))
            )
            {
                return false;
            }
        }
        return true;
    }

    private static void newMatrix () {
        getDimension();
    }

}