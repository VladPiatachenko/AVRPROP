package processing;

import java.io.File;
import java.io.IOException;

public class Proportion {
    static int N=3000; // amounto of points for both functions
    File p=new File("sin8.txt");
    static double h=2*Math.PI/100,r=1,alfa=2;
    static double[] x=new double[N];
    static double[] y=new double[N];
    static double[] y1=new double[N];
    static double[] z=new double[N];
    static double[] z1=new double[N];
    static double[] dy_r=new double[N];
    static double[] dy1_r=new double[N];
    static double[] d2y_r=new double[N];
    static double[] d2y1_r=new double[N];
    
    public static void main(String[] args) throws IOException {
        double matr[][]=FileOperator.getMatrix("D:\\hierarchy_java\\DataReader\\gestures\\doubles\\palm_ch2.txt");
        double temp[] = new double[3000];
        double avg[]=new double[3000];
        for(int i=0;i<N;i++)
        {
            for(int j=0;j<N;j++)
            {
                temp[j]=matr[i][j];
                avg[i]=+temp[j];
            }
            System.out.println("-"+avg[i]+"-");
            
            avg[i]=avg[i]/3000.0;
        }
        
        for(int i=0;i<N;i++)
            {
            //x[i]=i*h;
             x[i]=avg[i];
            System.out.println("-"+x[i]+"-");
            //y[i]=10*Math.sin(x[i]);
            y[i]=temp[i];
            //fprintf(p,"x[%i]=%lf y[%i]=%lf \n",i,x[i],i,y[i]);
            }
            for(int i=0;i<N;i++)
            {
            x[i]=i*h;
            y1[i]=10*Math.sin(alfa*x[i]);
            //fprintf(p,"x[%i]=%lf  y1[%i]=%lf\n",i,x[i],i,y1[i]);
            }
        proizv(y,dy_r,N-10,h);
        proizv(dy_r,d2y_r,N-20,h);
        proizv(y1,dy1_r,N-10,h);
        proizv(dy1_r,d2y1_r,N-20,h);
            for(int i=2;i<N-20;i++)
            {
            z[i]=1-Math.pow(dy_r[i],2)/(y[i]*d2y_r[i]);
            //fprintf(p,"y=%lg dy_r[%i]=%lf d2y_r[%i]=%lf z[%i]=%lf \n",y[i],i,dy_r[i],i,d2y_r[i],i,z[i]);
            //fprintf(p,"dy_r[%i]=%lf d2y_r[%i]=%lf z[%i]=%lf \n",i,dy_r[i],i,d2y_r[i],i,z[i]);
            //fprintf(p,"dy_r[%i]=%lf  z[%i]=%lf \n",i,dy_r[i],i,z[i]);
            }
            for(int i=2;i<N-20;i++)
            {
            z1[i]=1-Math.pow(dy1_r[i],2)/(y1[i]*d2y1_r[i]);
            //fprintf(p,"dy1_r[%i]=%lf  z1[%i]=%lf\n",i,dy1_r[i],i,z1[i]);
            }
        analyses(z,z1,dy_r,dy1_r,N-20,N-20);
        System.out.println("FINISH");

}
    

    static void proizv(double[] y,double[] dy_r,int n,double h){
        int i;
        double[] dy1=new double[2*n];
        double[] dy2=new double[2*n];
        double[] dy3=new double[2*n];
        double[] dy4=new double[2*n];
        double[] dy5=new double[2*n];
        double[] dy6=new double[2*n];
        double[] dy_t=new double[2*n];
        double[] dy_grub=new double[2*n];

            for(i=0;i<n-1;i++)
             dy1[i]=y[i+1]-y[i];
            for(i=0;i<n-2;i++)
             dy2[i]=dy1[i+1]-dy1[i];
            for(i=0;i<n-3;i++)
             dy3[i]=dy2[i+1]-dy2[i];
            for(i=0;i<n-4;i++)
             dy4[i]=dy3[i+1]-dy3[i];
            for(i=0;i<n-5;i++)
             dy5[i]=dy4[i+1]-dy4[i];
            for(i=0;i<n-6;i++)
             dy6[i]=dy5[i+1]-dy5[i];
            for(i=1;i<n-6;i++)
            {
            dy_r[i]=(dy1[i-1]+dy2[i-1]/2-dy3[i-1]/6+dy4[i-1]/12-dy5[i-1]/20+dy6[i-1]/30)/h;
        //t[i]=i*h1;
        //dy_grub[i]=(y[i+1]-y[i])/h;
        //fprintf(p,"dy_r[%i]=%lf dy_t=%lf dy_grub[%i]=%lf\n",i,dy_r[i],dy_t[i],i,dy_grub[i]);
        //fprintf(p,"dy_r[%i]=%lf \n",i,dy_r[i]);
            }
    }
    static void analyses(double[] z,double[] z1,double[] dy,double[] dy1,int n, int n1){
        double xx;
            
            for(int i=3;i<n;i++)
                for(int j=2;j<n1;j++)
                {
                    if(Math.abs(z[i]-z1[j])<1e-4 && dy[i]*dy1[j]>=0)
                            {
                                xx=j*h;
                                //fprintf(p,"i=%i j=%i z[%i]=%lg z1[%i]=%lg xx=%lg\n",i,j,i,z[i],j,z1[j],xx);
                                System.out.println("i="+i+" j="+j+" z["+i+"]="+z[i]+" z1["+j+"]="+z1[j]+"\n");
                                break;
                            }
                    else
                        if((z[i]-z1[j])*(z[i]-z1[j+1])<0)
                            {
                                xx=j*h+h*((z[i]-z1[j])/(z1[j+1]-z1[j]));
                                if(Math.abs(z1[j+1]-z1[j])<1e-6)
                                    xx=j*h;
                                double j_eqviv=xx/h;
                                //fprintf(p,"for z[%i]=%lg  z1[%i]=%lg z1[%i]=%lg xx=%lg j_eqviv=%lg\n",i,z[i],j,z1[j],j+1,z1[j+1],xx,j_eqviv);
                                System.out.println("for z["+i+"]="+z[i]+"  z1["+j+"]="+z1[j]+" z1["+(j+1)+"]="+z1[j+1]+" xx="+xx+" j_eqviv="+j_eqviv+"\n");
                                break;
                            }
                }
    }
    
}