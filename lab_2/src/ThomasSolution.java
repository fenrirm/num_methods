import java.util.Arrays;

public class ThomasSolution {
    public static double[] thomasAlgorithm(double[][]A, double[] f, int n){
        double[] a=new double[n];
        double[] b=new double[n];
        double[] c=new double[n];
        double[] d=new double[n];
        double[] x=new double[n];
        for (int i = 0; i < n-1; i++) {
            a[i+1]=A[i+1][i];
            b[i]=A[i][i];
            c[i]=A[i][i+1];
            d[i]=f[i];
        }
        b[n-1]=A[n-1][n-1];
        d[n-1]=f[n-1];
        c[0]/=b[0];
        d[0]/=b[0];
        for (int i = 1; i < n; i++) {
            double id = 1/(b[i]-c[i-1]*a[i]);
            c[i]*=id;
            d[i] = (d[i]-d[i-1]*a[i])*id;
        }
        x[n-1]=d[n-1];
        for (int i = n-2; i >=0 ; i--) {
            x[i]=d[i]-c[i]*x[i+1];
        }
        return x;
    }
    public static void main(String[] args) {
        double[][] A = {
                {15.3, 8.4, 2.1},
                {-11.5, 4.7, 0},
                {0, -5.2, 12.9}
        };
        double[] d = {1.1, 3.7, 0.4};
        int n = A.length;
        System.out.println("Thomas algorithm - "+Arrays.toString(thomasAlgorithm(A,d,n)));
    }
}
