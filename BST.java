/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bst;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author harlan.howe
 */
public class BST {

    private TreeNode root;
    private static String[] words;
    int searchNum;

    public BST()
    {
        //loads the words from the dictionary file into memory.
        ArrayList<String> dictionary = new ArrayList<String>();
        File inputFile = new File("word_list_moby_crossword.flat.txt");
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(new File("word_list_moby_crossword.flat.txt")));
            String word;
            while((word = reader.readLine())!=null)
            {
                dictionary.add(word);
            }

        }catch (FileNotFoundException fnfExp)
        {
            System.out.println("File not found.");
            fnfExp.printStackTrace();
        }
        catch (IOException ioExp)
        {
            ioExp.printStackTrace();
        }
        System.out.println("Dictionary Loaded. "+dictionary.size());

        
        Scanner keyboard = new Scanner(System.in);
        
        System.out.print("How many words should I add to the tree? ");
        int numWords = keyboard.nextInt();
        words = new String[numWords];
        for (int i=0; i<numWords; i++)
        {
            int which = (int)(Math.random()*dictionary.size());
            //System.out.println(dictionary.get(which));
            words[i] = dictionary.get(which);
            add(dictionary.get(which));
        }
        System.out.println("Enter a number. If you enter a negative number, it will look for the word \"postvaccination\" in the entire dictionary,\n" +
                "so you may want to include 50,000+ in the dictionary size for a chance of returning true\n" +
                "entering a nonnegative integer (*less than the size you input*) will search for the nth value added to the list, guaranteeing a match\n" +
                " ");
        searchNum = keyboard.nextInt();

        System.out.println("------------------\n"+this.toString());
    }
    
    public String toString() {return subString("",root);}
        
    public String subString(String prefix, TreeNode subroot) {
        if (subroot == null)
            return "";
        else {
            String result = "";
            result+= subString(prefix+"\t",subroot.getLeft());
            result+= prefix+subroot.getValue()+"\n";
            result+= subString(prefix+"\t",subroot.getRight());
            return result;}
    }

    public void add(String s){
        if (root == null){root = new TreeNode(s);}
        else {addToSubTree(s,root);}}
    
    public void addToSubTree(String s, TreeNode subroot)
    {
        int rel = s.compareTo(subroot.getValue());
        if (rel<0)
        {
            if (subroot.getLeft() == null)
                subroot.setLeft(new TreeNode(s));
            else
                addToSubTree(s,subroot.getLeft());
        }
        else
        {
            if (subroot.getRight() == null)
                subroot.setRight(new TreeNode(s));
            else
                addToSubTree(s,subroot.getRight());
        }
    }


    public void toStringUnindented() {
        System.out.println(subStringUnindented(root));}

    public String subStringUnindented(TreeNode subroot) {
        if (subroot == null)
            return "";
        else {
            String result = "";
            result+= subStringUnindented(subroot.getRight());
            result+= subroot.getValue()+"\n";
            result+= subStringUnindented(subroot.getLeft());
            return result;}}

    public void findCharSize() {
        System.out.println("There are " + findCharSize(root) + " characters.");}
    public int findCharSize(TreeNode tn) {
        int r = 0;
        if (tn.getRight()!=null) {
            r+=findCharSize(tn.getRight());}
        if (tn.getLeft()!=null) {
            r+=findCharSize(tn.getLeft());}
        r+=tn.getValue().length();
        return r;}

    public void checkWord() {
        String s;
        if (searchNum < 0) {
            s = "postvaccination";}
        else {
            s = words[searchNum];
            System.out.println("word: " + s);}
        System.out.println(s + " is included in the tree: " + checkWord(s,root));}

    public boolean checkWord(String s, TreeNode tn) {
        if (tn.getValue().equals(s) || (tn.getRight()==null && tn.getLeft()==null)) {return tn.getValue().equals(s);}
        else {
            if (tn.getValue().compareTo(s) > 0) {
                if (tn.getLeft() == null) {return false;}
                else {return checkWord(s,tn.getLeft());}}
            else {
                if (tn.getRight() == null) {return false;}
                else {return checkWord(s,tn.getRight());}}}}

    //ignore this - I accidentally wrote a tree traversal algorithm that I want to keep for later
//    public boolean Traverse(String s, TreeNode tn) {
//        if (tn.getValue().equals(s) || (tn.getRight()==null && tn.getLeft()==null)) {return tn.getValue().equals(s);}
//        else {return (((tn.getRight() != null) ? Traverse(s,tn.getRight()) : false) || ((tn.getLeft() != null) ? Traverse(s,tn.getLeft()) : false));}}

    public void depthFinder() {
        System.out.println("Depth: " + depthFinder(1,root));}

    public int depthFinder(int current, TreeNode tn) {
        if (tn.getRight()==null && tn.getLeft()==null) {
            return current;}
        else {
            int a = ((tn.getRight() != null) ? depthFinder(current+1, tn.getRight()) : 0);
            int b = ((tn.getLeft() != null) ? depthFinder(current+1, tn.getLeft()) : 0);
            return (a > b) ? a : b;}}



    

    
    
}
