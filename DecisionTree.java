package decisiontree;

import static decisiontree.Buildtree.a2;
import static decisiontree.Buildtree.a3;
import static decisiontree.Buildtree.f;
import static decisiontree.Buildtree.f2;
import static decisiontree.Buildtree.nexta2;
import static decisiontree.Buildtree.nexta3;
import static decisiontree.Buildtree.thres;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DecisionTree {

    static Scanner s;

    static double allclass = 0.0;//total number of classes in the whole/or remaining dataset

    static double nodeinfo; //information of node or root
    static double attinfo; //information of attrubute

    //static mInfo
    //starting list for all classes in dataset 
    final static ArrayList<String> fireclass = new ArrayList();
    static String maxatt;
    static String n;
    static String lastnode;
    static String nextnode;
    static ArrayList<Double> temp = new ArrayList<Double>();
    static ArrayList<Double> rh = new ArrayList<Double>();
    static ArrayList<Double> wind = new ArrayList<Double>();

    // list for att headings
    static ArrayList<String> atts = new ArrayList<String>();

    static ArrayList<Double> splitarray = new ArrayList<Double>();
    static ArrayList<String> classArray = new ArrayList<String>();
    static ArrayList<Double> maxigain = new ArrayList<Double>();
    static ArrayList<Double> thresholds = new ArrayList<Double>();

    public static void main(String[] args) throws FileNotFoundException, IOException {

        readfile();
        classArray = fireclass;
        allclass = classArray.size();
        nodeinfo = Buildtree.totalinfo(Buildtree.classYes(allclass, classArray), Buildtree.classNo(allclass, classArray), allclass);
        splitarray = temp;
        double[] t = Buildtree.threshold(splitarray, nodeinfo, allclass);
        double tmpinfo = t[0];
        double tmpthres = t[1];
        maxigain.add(tmpinfo);
        // System.out.println("temp threshold " + tmpthres);
        thresholds.add(tmpthres);

        splitarray = rh;
        double[] r = Buildtree.threshold(splitarray, nodeinfo, allclass);
        double rhinfo = r[0];
        double rhthres = r[1];
        maxigain.add(rhinfo);
        // System.out.println("rh threshold " + rhthres);
        thresholds.add(rhthres);

        splitarray = wind;
        double[] w = Buildtree.threshold(splitarray, nodeinfo, allclass);
        double winfo = w[0];
        double wthres = w[1];
        maxigain.add(winfo);
        //System.out.println("wind threshold " + wthres);
        thresholds.add(wthres);

        // System.out.println("Maxigain:" + maxigain);
        Buildtree.attmaxgain();
        String rootmaxatt = maxatt;
        double rootthres = thres;
        System.out.println("ROOTNODE: " + rootmaxatt);
        System.out.println("rootnode threshold: " + thres);
        //String root = rootmaxatt;
        Buildtree.clear();
        ////////////////////////////////////////////////
        //next node <root node thres
        Buildtree.splitdata(rootmaxatt);
        Node.splitrootnode1();
        nextnode();
        nextnode = maxatt;
        String nextnode1 = maxatt;
        double nextnode1thres = thres;
        double nextnodethres = thres;

        System.out.println("nextnode1: " + maxatt);
        System.out.println("nextnode threshold: " + thres);

        /////////////////////
        Buildtree.splitdata2(nextnode);
        Node.splitnextnode();
        lastnode(nextnode);
        String nextnode3 = maxatt;
        lastnode = nextnode3;
        double nextnode3thres = thres;
        System.out.println("nextnode3: " + nextnode3);
        System.out.println("nextnode3 threshold: " + nextnode3thres);

        // get leaves for node 3
        Buildtree.splitdata3();
        System.out.println("LEAF 1");
        Node.splitlastnode();
        Node.cleararrays2();
        System.out.println("LEAF 2");
        Node.splitlastnode2();
        Node.cleararrays2();

        ////////////
        System.out.println("DONE");
        Node.cleararrays();

        Buildtree.splitdata2(nextnode);

        Node.splitnextnode2(nextnodethres);
        lastnode(nextnode);
        String nextnode4 = maxatt;
        lastnode = nextnode4;
        double nextnode4thres = thres;
        System.out.println("nextnode4: " + nextnode4);
        System.out.println("nextnode4 threshold: " + nextnode4thres);

        Buildtree.splitdata3();
        System.out.println("LEAF 3");
        Node.splitlastnode();
        Node.cleararrays2();
        System.out.println("LEAF 4");
        Node.splitlastnode2();
        Node.cleararrays2();

        Node.cleararrays();

        Buildtree.clear();

        //////////////////////////////////////////////////
        //splitting other half of tree
        Buildtree.splitdata(rootmaxatt);
        Node.splitrootnode2(rootthres);
        nextnode();
        nextnode = maxatt;
        String nextnode2 = maxatt;
        double nextnode2thres = thres;
        System.out.println("nextNODE2: " + maxatt);
        System.out.println("nextnode2 threshold: " + thres);
        /////////

        ///split node 2 for 5 and 6
        Buildtree.splitdata2(nextnode);
        Node.splitnextnode();
        lastnode(nextnode);
        String nextnode5 = maxatt;
        lastnode = nextnode5;
        double nextnode5thres = thres;
        System.out.println("nextnode5: " + nextnode5);
        System.out.println("nextnode5 threshold: " + nextnode5thres);

        //// split again to get leaves for nodes 5
        Buildtree.splitdata3();
        System.out.println("LEAF 5");
        Node.splitlastnode();
        Node.cleararrays2();
        System.out.println("LEAF 6");
        Node.splitlastnode2();
        Node.cleararrays2();

        ////////////
        Node.cleararrays();

        Buildtree.splitdata2(nextnode);
        Node.splitnextnode2(nextnode2thres);
        lastnode(nextnode);
        String nextnode6 = maxatt;
        double nextnode6thres = thres;
        System.out.println("nextnode6: " + nextnode6);
        System.out.println("nextnode6 threshold: " + nextnode6thres);
        ///split to get leaves for 6

        Buildtree.splitdata3();
        System.out.println("LEAF 7");
        Node.splitlastnode();
        Node.cleararrays2();
        System.out.println("LEAF 8");
        Node.splitlastnode2();

        readtestfile();
        testdata(nextnode6thres, nextnode5thres, nextnode2thres, nextnode4thres, nextnode3thres, rootthres, nextnode1thres);

    }

    public static void readfile() {
        try {
            s = new Scanner(
                    new File("resources/forestfires_trainingdataset.csv"));

            // skip header line
            if (s.hasNext()) {

                String line = s.nextLine();
                String head[] = line.split(",");
                atts.add(head[6]);
                atts.add(head[7]);
                atts.add(head[8]);
                // System.out.println(atts);
            }

            while (s.hasNextLine()) { //(s.hasNext()) {
                String line = s.nextLine();
                String parts[] = line.split(",");
                // add values to arraylists
                temp.add(Double.parseDouble(parts[6]));
                rh.add(Double.parseDouble(parts[7]));
                wind.add(Double.parseDouble(parts[8]));

                fireclass.add(parts[11]);

            }
            s.close();

        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
        }
    }

    public static void readtestfile() {
        temp.clear();
        rh.clear();
        wind.clear();
        try {
            s = new Scanner(
                    new File("resources/firetestdata.csv"));

            // skip header line
            if (s.hasNext()) {
                s.nextLine();
            }

            while (s.hasNextLine()) {
                String line = s.nextLine();
                String parts[] = line.split(",");
                temp.add(Double.parseDouble(parts[6]));
                rh.add(Double.parseDouble(parts[7]));
                wind.add(Double.parseDouble(parts[8]));

            }
            s.close();

        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
        }

    }

    public static void testdata(double nextnode6thres, double nextnode5thres, double nextnode2thres, double nextnode4thres, double nextnode3thres, double rootthres, double nextnode1thres) {
        //if statement to test data
        System.out.println("RH TEMP WIND CLASS");
        for (int i = 0; i < rh.size(); i++) {
            if (rh.get(i) < rootthres) {
                if (temp.get(i) < nextnode1thres) {
                    if (wind.get(i) < nextnode3thres) {
                        System.out.println(rh.get(i) + " " + temp.get(i) + " " + wind.get(i) + " Unknown");
                    } else {
                        System.out.println(rh.get(i) + " " + temp.get(i) + " " + wind.get(i) + " Unknown");
                    }
                } else {
                    if (wind.get(i) < nextnode4thres) {
                        System.out.println(rh.get(i) + " " + temp.get(i) + " " + wind.get(i) + " Unknown");
                    } else {
                        System.out.println(rh.get(i) + " " + temp.get(i) + " " + wind.get(i) + " Yes");
                    }
                }
            } else {
                if (temp.get(i) < nextnode2thres) {
                    if (wind.get(i) < nextnode5thres) {
                        System.out.println(rh.get(i) + " " + temp.get(i) + " " + wind.get(i) + " No");
                    } else {
                        System.out.println(rh.get(i) + " " + temp.get(i) + " " + wind.get(i) + " Unknown");
                    }
                } else {
                    if (wind.get(i) < nextnode6thres) {
                        System.out.println(rh.get(i) + " " + temp.get(i) + " " + wind.get(i) + " Yes");
                    } else {
                        System.out.println(rh.get(i) + " " + temp.get(i) + " " + wind.get(i) + " Unknown");
                    }
                }
            }
        }
    }

    public static void nextnode() {
        classArray = f;
        allclass = f.size();
        nodeinfo = Buildtree.totalinfo(Buildtree.classYes(allclass, classArray), Buildtree.classNo(allclass, classArray), allclass);
        splitarray = a2;
        double[] at1 = Buildtree.threshold(splitarray, nodeinfo, allclass);
        double at1info = at1[0];
        double at1thres = at1[1];
        maxigain.add(at1info);
        thresholds.add(at1thres);

        splitarray = a3;
        double[] at2 = Buildtree.threshold(splitarray, nodeinfo, allclass);
        double at2info = at2[0];
        double at2thres = at2[1];
        maxigain.add(at2info);
        thresholds.add(at2thres);
        Buildtree.attmaxgain();

    }

    public static void lastnode(String nextnode) {

        classArray = f2;
        allclass = f2.size();
        nodeinfo = Buildtree.totalinfo(Buildtree.classYes(allclass, classArray), Buildtree.classNo(allclass, classArray), allclass);
        if (nextnode.equals(atts.get(0))) {

            splitarray = nexta3;
            double[] a3array = Buildtree.threshold(splitarray, nodeinfo, allclass);
            double a3info = a3array[0];
            double a3thres = a3array[1];
            maxatt = atts.get(1);
            thres = a3thres;

        } else {

            splitarray = nexta2;
            double[] a2array = Buildtree.threshold(splitarray, nodeinfo, allclass);
            double a2info = a2array[0];
            double a2thres = a2array[1];
            maxatt = atts.get(0);
            thres = a2thres;

        }

    }

}
