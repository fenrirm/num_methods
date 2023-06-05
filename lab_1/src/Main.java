import java.util.function.Function;

public class Main {

    public static double f(double x){
        return x*x*x+6*x*x+9*x+2;
    }

    public static double fDerivative(double x){return 3*x*x+12*x+9;}

    public static double fi(double x){
        return -1*x*x*x/9-2*x*x/3-2.0/9;
    }

    public static double fiDerivative(double x){
        return (-1*x*x/3)-(4*x/3);
    }

    public static void  rootSeparationCondition(double a, double b){
        if(f(a)*f(b)>0){
            System.out.println("Boundary values are invalid");
            System.out.println("f(a)="+f(a)+" f(b)="+f(b));
        }
        else {
            System.out.println("Roots lie between "+a+" and "+b);
        }

    }

    public static double iterativeMethod(double a, double b, double x, double e, double q, double c){

        double maxFiDerivative = max(Main::fiDerivative, a, b);

        for(int i=0; i<1; i++){
            if(maxFiDerivative<1){
                System.out.println("Condition max|fiDerivative(x)|<1 is satisfied");
            }else {
                System.out.println("Condition max|fiDerivative(x)|<1 is NOT satisfied. Iterative method CAN'T be applied");
                break;
            }

            if(Math.abs(fi(x)-x)<=(1-q)*c){
                System.out.println("Condition |fiDerivative(x)-x0|<=(1-q)*c is satisfied. Iterative method CAN be applied");
            }else {
                System.out.println("Condition |fiDerivative(x)-x0|<=(1-q)*c is NOT satisfied. Iterative method CAN'T be applied");
                break;
            }
        }

        double nextX=x;
        int n=1;
        do{
            x=nextX;
            nextX=fi(x);
            System.out.println(n++ + " " + nextX);
        }while (Math.abs(nextX-x)>=e);

        return nextX;

    }

    public static double relaxationMethod(double a, double b, double x, double e){

        double m1 = min(Main::fDerivative,a, b);
        double M1 = max(Main::fDerivative, a, b);

        if(M1>m1 && m1>0){
            System.out.println("Condition 0<m1<M1 is satisfied. Relaxation method can be applied");
        }else {
            System.out.println("Condition 0<m1<M1 is NOT satisfied. Relaxation method can be applied");
        }


        double t = (2.0/(m1+M1));
        double nextX = x;
        int n = 1;
        do{
            x = nextX;
            nextX = x - t*f(x);
            System.out.println(n++ + " " + nextX);

        }while (Math.abs(nextX-x)>=e);

        return nextX;

    }

    public static double max(Function<Double, Double> function, double a, double b){
        double max;
        double maxA = Math.abs(function.apply(a));
        double maxB = Math.abs(function.apply(b));
        max = Math.max(maxA, maxB);
        return max;
    }

    public static double min(Function<Double, Double> function, double a, double b){
        double min;
        double minA = Math.abs(function.apply(a));
        double minB = Math.abs(function.apply(b));
        min = Math.min(minA, minB);
        return min;
    }

    public static void main(String [] args){

        double a = -0.5;
        double b = 0.5;
        double x0 = -0.25;
        double e = 0.0001;
        double q = 0.75;
        double c = 0.75;

        rootSeparationCondition(a, b);

        double res = iterativeMethod(a,b,x0,e,q, c);
        System.out.println("x* ~ "+res);
        System.out.println("f(x*) ~ "+f(res));

        double res2 = relaxationMethod(a, b, x0, e);
        System.out.println("x* ~ "+res2);
        System.out.println("f(x*) ~ "+f(res2));

    }
}
