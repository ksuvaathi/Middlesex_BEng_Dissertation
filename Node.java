
package decisiontree;

import static decisiontree.Buildtree.a1;
import static decisiontree.Buildtree.attarr2;
import static decisiontree.Buildtree.attarr3;
import static decisiontree.Buildtree.f;

import static decisiontree.Buildtree.a2;
import static decisiontree.Buildtree.a3;
import static decisiontree.Buildtree.f2;
import static decisiontree.Buildtree.nexta1;
import static decisiontree.Buildtree.nexta2;
import static decisiontree.Buildtree.nexta3;
import static decisiontree.Buildtree.thres;
import static decisiontree.DecisionTree.allclass;
import static decisiontree.DecisionTree.classArray;
import static decisiontree.DecisionTree.fireclass;
import static decisiontree.DecisionTree.nodeinfo;
;
import static decisiontree.DecisionTree.splitarray;
import java.util.ArrayList;




public class Node {
 static ArrayList<Double> lasta1 = new ArrayList<Double>();
    static ArrayList<Double> lasta2 = new ArrayList<Double>();
    static ArrayList<Double> lasta3 = new ArrayList<Double>();
    
    static ArrayList<String> f3 = new ArrayList<String>();
    public static void splitrootnode1() {

        for (int i = 0; i < splitarray.size(); i++) {
            if (splitarray.get(i) < thres) {

                a1.add(splitarray.get(i));
                a2.add(attarr2.get(i));
                a3.add(attarr3.get(i));
                //get the class as well
                f.add(fireclass.get(i));

            }

        }
        System.out.println(f);
        System.out.println(a1);
        System.out.println(a2);
        System.out.println(a3);

    }

    public static void splitrootnode2(double rootthres) {

        for (int i = 0; i < splitarray.size(); i++) {
            if (splitarray.get(i) >= rootthres) {
                a1.add(splitarray.get(i));
                a2.add(attarr2.get(i));
                a3.add(attarr3.get(i));
                //get the class as well
                f.add(fireclass.get(i));

            }

        }
        System.out.println(f);
        System.out.println(a1);
        System.out.println(a2);
        System.out.println(a3);

    }

    public static void splitnextnode() {

       for (int i = 0; i < splitarray.size(); i++) {
            if (splitarray.get(i) < thres) {

                nexta1.add(splitarray.get(i));
                nexta2.add(attarr2.get(i));
                nexta3.add(attarr3.get(i));
                //get the class as well
                f2.add(f.get(i));

            }

        }
        System.out.println(f2);
        System.out.println(nexta1);
        System.out.println(nexta2);
        System.out.println(nexta3);


    }

    public static void splitnextnode2(double nextnodethres) {


          for (int i = 0; i < splitarray.size(); i++) {
            if (splitarray.get(i) >= nextnodethres) {

                nexta1.add(splitarray.get(i));
                nexta2.add(attarr2.get(i));
                nexta3.add(attarr3.get(i));
                //get the class as well
                f2.add(f.get(i));

            }

        }
        System.out.println(f2);
        System.out.println(nexta1);
        System.out.println(nexta2);
        System.out.println(nexta3);
        
        

        

    }
    public static void splitlastnode() {

       for (int i = 0; i < splitarray.size(); i++) {
            if (splitarray.get(i) < thres) {

                lasta1.add(splitarray.get(i));
                lasta2.add(attarr2.get(i));
                lasta3.add(attarr3.get(i));
                //get the class as well
                f3.add(f2.get(i));

            }

        }
        System.out.println(f3);
        System.out.println(lasta1);
        System.out.println(lasta2);
        System.out.println(lasta3);
        classArray =f3;
        allclass   =f3.size();
        nodeinfo = Buildtree.totalinfo(Buildtree.classYes(allclass, classArray), Buildtree.classNo(allclass, classArray), allclass);
        System.out.println("leafnode:"+ nodeinfo);


    }

    public static void splitlastnode2() {


          for (int i = 0; i < splitarray.size(); i++) {
            if (splitarray.get(i) >= thres) {

                lasta1.add(splitarray.get(i));
                lasta2.add(attarr2.get(i));
                lasta3.add(attarr3.get(i));
                //get the class as well
                f3.add(f2.get(i));

            }

        }
        System.out.println(f3);
        System.out.println(lasta1);
        System.out.println(lasta2);
        System.out.println(lasta3);
        classArray =f3;
        allclass   =f3.size();
nodeinfo = Buildtree.totalinfo(Buildtree.classYes(allclass, classArray), Buildtree.classNo(allclass, classArray), allclass);
        System.out.println("leafnode:"+ nodeinfo);
        

    }
    
    public static void cleararrays(){

       f2.clear();
        nexta1.clear();
        nexta2.clear();
        nexta3.clear();

    }
     public static void cleararrays2(){

       f3.clear();
        lasta1.clear();
        lasta2.clear();
        lasta3.clear();

    }

}
