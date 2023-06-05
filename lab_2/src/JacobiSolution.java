import java.util.Arrays;

public class JacobiSolution {

    public static double[] jacobiAlgorithm(double[][] A, double[]b, int n, double e){
        double currentE=0;
        double[] x=new double[n];
        double[] currentX=new double[n];
        Arrays.fill(x, 0.5);
        do{
            for (int i = 0; i < n; i++) {
                currentX[i]=b[i];
                for (int j = 0; j < n; j++) {
                    if (i != j){
                        currentX[i]-= A[i][j]* x[j];
                    }
                }
                currentX[i]/=A[i][i];
            }
            currentE=0;
            for (int i = 0; i < n; i++) {
                double v = Math.abs(currentX[i] - x[i]);
                if (v >currentE){
                    currentE= v;
                }
                x[i]=currentX[i];}
        }while(currentE>e);
        return x;
    }

    public static void main(String[] args) {
        double[][] A = {
                {20.3, 1.4, 2.1},
                {-11.5, 15.7, 0},
                {0, -5.2, 12.9}
        };
        double[] d = {2.1, 1.7, 0.8};
        int n = A.length;
        double e = 0.0001;
        System.out.println("Jacobi algorithm - "+Arrays.toString(jacobiAlgorithm(A,d,n,e)));
    }
}
