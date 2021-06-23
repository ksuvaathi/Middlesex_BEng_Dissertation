/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package decisiontree;


import static decisiontree.DecisionTree.fireclass;
import static decisiontree.DecisionTree.rh;
import static decisiontree.DecisionTree.temp;
import static decisiontree.DecisionTree.splitarray;
import static decisiontree.DecisionTree.wind;
import static decisiontree.DecisionTree.*;
import static decisiontree.DecisionTree.maxigain;
import java.util.ArrayList;
import java.util.Collections;


public class Buildtree {

    static double mInfo;
    //static double mInfo;
 
    static ArrayList<Double> a1 = new ArrayList<Double>();
    static ArrayList<Double> a2 = new ArrayList<Double>();
    static ArrayList<Double> a3 = new ArrayList<Double>();
    static ArrayList<Double> a4 = new ArrayList<Double>();
    
    static ArrayList<Double> nexta1 = new ArrayList<Double>();
    static ArrayList<Double> nexta2 = new ArrayList<Double>();
    static ArrayList<Double> nexta3 = new ArrayList<Double>();
    
    static ArrayList<String> f = new ArrayList<String>();
    static ArrayList<String> f2 = new ArrayList<String>();
           static double thres;
   static ArrayList<Double> attri2 ;
        static ArrayList<Double> attarr2 ;
        static ArrayList<Double> attarr3 ;
        static String nextnode;
        static String nextnode2;

    public static double classYes(double allclass, ArrayList<String> classArray) {
        double t = 0;
        int i = 0;
        for (i = 0; i < allclass; i++) {
            if (classArray.get(i).equals("Yes")) {

                t++;
            }
        }
        System.out.println("Number of Yes: " + t);
        return t;
    }

    public static double classNo(double allclass, ArrayList<String> classArray) {
        double f = 0;
        int i = 0;
        for (i = 0; i < allclass; i++) {
            if (classArray.get(i).equals("No")) {
                f++;
            }
        }
        System.out.println("Number of No: " + f);
        return f;

    }

    public static double[] threshold(ArrayList<Double> splitarray, double nodeinfo, double allclass) {
        ArrayList<Double> maxinfo = new ArrayList<Double>();
        int i = 0;
        int sp = 0;

        for (sp = 0; sp < splitarray.size(); sp++) {
            double totalclass = 0;//yes
            double totalclass2 = 0;//no
            double t1 = 0;
            double f1 = 0;
            double t2 = 0;
            double f2 = 0;
            for (i = 0; i < splitarray.size(); i++) {
                if (splitarray.get(i) < splitarray.get(sp)) {
                    if (fireclass.get(i).equals("Yes")) {
                        t1++;

                    } else {
                        f1++;
                    }
                } else {
                    if (fireclass.get(i).equals("Yes")) {
                        t2++;
                    } else {
                        f2++;
                    }
                }

            }

            totalclass = t1 + f1;

            totalclass2 = t2 + f2;


            double valinfo = attinfo(t1, f1, t2, f2, allclass, totalclass, totalclass2);
            Double y = new Double(valinfo);
            boolean z = y.isNaN();

            if (z == true) {
                //use a new array
                //to get the correct value from the attribute 
                maxinfo.add(0.0);
                // ArrayList<double>
            } else {
                maxinfo.add(valinfo);
            }

        }

        mInfo = Collections.max(maxinfo);

     
        //get value in the same index as the max info
        // this will be the threhold in which attributes will be split on.
        double thres = splitarray.get(maxinfo.indexOf(mInfo));
       
        double ans[];
        ans = new double[2];
        ans[0] = mInfo;
        ans[1] = thres;


        return ans;
    }

// information  from whole dataset
    public static double totalinfo(double t, double f, double allclass) {
        //make sure its double
        double info = (-(t / allclass) * (Math.log(t / allclass) / Math.log(2)))
                - ((f / allclass) * (Math.log(f / allclass) / Math.log(2)));

        return info;
    }

    //information  from an attribute
    private static double attinfo(double t1, double f1, double t2,
            double f2, double allclass, double totalclass, double totalclass2) {
        //check double
        
        double attinfo = (totalclass / allclass) * ((-(t1 / totalclass) * (Math.log(t1 / totalclass) / Math.log(2)))
                - ((f1 / totalclass) * (Math.log(f1 / totalclass) / Math.log(2))))
                + (totalclass2 / allclass) * ((-(t2 / totalclass2) * (Math.log(t2 / totalclass2) / Math.log(2)))
                - ((f2 / totalclass2) * (Math.log(f2 / totalclass2) / Math.log(2))));

        return attinfo;
    }

   
    
    public static void splitdata(String rootmaxatt) {


        switch (rootmaxatt) {
            case "temp":
                
                splitarray = temp;
                attarr2 = rh;
                attarr3 = wind;
                atts.remove("temp");
                
                break;
            case "RH":
                
                splitarray  = rh;
                attarr2 = temp;
                attarr3 = wind;
                atts.remove("RH");
                break;
            default:
                
                splitarray = wind;
                attarr2 = rh;
                attarr3 = temp;
                atts.remove("wind");
                break;
        }
        
        

    }
    
    public static void splitdata2(String nextnode1) {
        switch (nextnode1) {
            case "temp":
                
                splitarray = a2;
                attarr2 = a1;
                attarr3 =a3;
                
                
                
                
                
                
                break;
                 case "RH":
                
                splitarray = a1;
                attarr2 = a2;
                attarr3= a3;
                
                
                
                break;
           
            default:
                
                splitarray = a3;
                attarr2 = a2;
                attarr3= a1;

                break;
        }
        
        

    
    }
   
     public static void splitdata3() {
        switch (lastnode) {
            case "temp":
                
                splitarray = nexta1;
                attarr2 = nexta2;
                attarr3 =nexta3;

                break;
                 case "RH":
                
                splitarray = nexta2;
                attarr2 = nexta1;
                attarr3= nexta3;
                
                
                
                break;
           
            default:
                
                splitarray = nexta3;
                attarr2 = nexta2;
                attarr3= nexta1;

                break;
        }
        
        

    
    }
    
    
    
    public static void clear(){
        
        f.clear();
        maxigain.clear();
        a3.clear();
        a2.clear();
        a1.clear();
        
        thresholds.clear();
        
    }

    static void attmaxgain() {
        double mx = Collections.max(maxigain);
        int nodeindex = maxigain.indexOf(mx);
        maxatt = atts.get(nodeindex);
        thres= thresholds.get(nodeindex);

        
        
    }

    

   
}
