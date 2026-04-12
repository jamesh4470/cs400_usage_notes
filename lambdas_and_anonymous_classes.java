import java.util.ArrayList;

interface MathOperation {
    public double compute(double a, double b);
}

class AdditionOperation implements MathOperation {
    @Override
    public double compute(double a, double b) {
        return a + b;
    }
}

public class CalculatorApp {
    public static MathOperation add() {
        return new AdditionOperation();
    }

    public static MathOperation mod() {
        return new MathOperation() {
            @Override
            public double compute(double a, double b) {
                return a % b;
            }
        };
    }

    public static MathOperation compare() {
        return (double a, double b) -> {
            if (a >= b) return 1;
            return 0;
        };
    }

    /**
     * This main method uses the objects returned by the methods above to
     * display the sum, difference, and product of operands between 1 and 5.
     * @param args is not used by this program
     */
    public static void main(String[] args) {
        // add all math operations to this array
        ArrayList<MathOperation> ops = new ArrayList<>();
        ops.add( add() );
        ops.add( mod() );
        ops.add( compare() );

        // display table of math operations applied to operands
        System.out.println("Operands:  add  mod  cmp");
        for(int b = 1; b < 6; b++) // second operand (b) goes from 1 to 5
            for(int a = b; a < 6; a++) { // first operand from b to 5
                System.out.print("     "+a+","+b+":"); // print operands first
                for(MathOperation op: ops)
                    if(op != null) // then print out result of operation
                        System.out.printf( "%5.1f", op.compute(a,b) );
                    else // or a dash when the operation is null
                        System.out.print("    -");
                System.out.println(); // print each operand pair on a new line
            }
    }
}
