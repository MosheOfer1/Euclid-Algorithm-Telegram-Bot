import java.util.ArrayList;
import java.util.List;

public class Euclid {
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RED = "\u001B[31m";


    public static int[] DXY = new int[3];
    public static List<String> list = new ArrayList<>();
    public static int index=0;
    public static String s="";
    public static String f="";
    public static String total="";
    public static int firstLen;
    public static int secondLen;

    public static String getTotal(){
        return total;
    }
    public static void start(int a,int b) {
            list.clear();
            index=0;
            s="";
            f="";
            total="";
            for (int i = 0; i < DXY.length; i++) {
                DXY[i]=0;
            }
            String sa=String.valueOf(a);
            String sb=String.valueOf(b);
            String sAModB = String.valueOf(a%b);
            String sADevB = String.valueOf(a/b);
            firstLen = sa.length()+sb.length()+3;
            secondLen = firstLen + sAModB.length() + sADevB.length() + 5;
            euclid(a, b);
            if(DXY[2]==0){
                if (DXY[0]==a){
                    DXY[1]=1;
                }
                else if(DXY[0]==b){
                    DXY[2]=1;
                }
            }
            System.out.print(list.get(0)+" | ");
            total+=list.get(0)+" | ";
            System.out.println("The linear combination: "+ANSI_RED+list.get((2*(index-2))+1)+ ANSI_RESET );
            total+="The linear combination: "+list.get((2*(index-2))+1)+"\n";
        for (int i = 1; i < index-2; i++) {
                System.out.println(list.get(i)+" | "+list.get((2*(index-2))+1-i));
                total+=list.get(i)+" | "+list.get((2*(index-2))+1-i)+"\n";
            }
            System.out.println(list.get(index-2));
            total+=list.get(index-2)+"\n";
            System.out.println(list.get(index-1));
            total+=list.get(index-1)+"\n";
//            System.out.println();
//            System.out.println(DXY[0] + " = " + DXY[1] + " * " + a + " + " + DXY[2] + " * " + b);
            System.out.println();
    }
    public static void euclid(int a,int b){
        if (b==0){
            DXY[0]=a;
            list.add("("+a+","+b+")");
            index=list.size();
            //System.out.println("("+a+","+b+")");
            //System.out.println();
            return;
        }
        int x = a/b;
        s="("+a+","+b+")";
        int sLen = s.length();
        String space="";
        String space2="";

        int diff=0;
        diff=firstLen-sLen;

        for (int i = 0; i <= diff; i++) {
            space+=" ";
        }

        f=a+" = "+x+" * " + b +" + "+a%b;
        int fLen = f.length();
        diff=secondLen-fLen;

        for (int i = 0; i <= diff; i++) {
            space2+=" ";
        }

        list.add("("+a+","+b+")"+space+"|"+a+" = "+x+" * " + b +" + "+a%b+space2);
        //System.out.print("("+a+","+b+")" +"  ");
        //System.out.println(a+" = "+x+" * " + b +" + "+a%b);
        euclid(b,a%b);
        if(a%b!=0){
            int x1=DXY[1];
            int y1=DXY[2];
            DXY[1]=y1;
            DXY[2]=x1+(y1*-x);
            if(y1==0) {
                DXY[1] = 1;
                DXY[2] = -x;
            }
        }
        if(DXY[1]!=0)
            list.add(DXY[0] + " = " + DXY[1] + " * " + a + " + " + DXY[2] + " * " + b);
            //System.out.println(DXY[0] + " = " + DXY[1] + " * " + a + " + " + DXY[2] + " * " + b);
    }
}
