package com.vok.yes;

import java.io.FileWriter;
import java.util.Random;
import java.util.Scanner;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.File;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;

public class Vokab
{
    int[] alphSortEN = {};
    int[] alphSortDE = {};
    int[] alphSortVal = {};


    public Vokab(){}
    public void sortVal(){
        for(int i =0; i < getCount(); i++){
            for(int x = 0; x < alphSortVal.length +1; i++){
                if (alphSortVal[i] > alphSortVal[x]) {

                    }
                }
            }
        }
    void bubbleSort(int arr[])
    {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - i - 1; j++)
                if (arr[j] > arr[j + 1]) {
                    // swap arr[j+1] and arr[j]
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
    }


    public void liesVokabelnEin() {
        try(FileReader reader = new FileReader("VokList.csv")){

            BufferedReader in = new BufferedReader(reader);
            String zeile = null;
            while ((zeile = in.readLine()) != null) {
                String[] vok = zeile.split(",");
                String dt = vok[0];
                String en = vok[1];  
                // Vokabel erstellen und in die List einfügen


                System.out.println("dt:" + dt + " eng:" + en);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public int getCount(){
        int Anzahl = 0;
        try(FileReader reader = new FileReader("VokList.csv")){

            BufferedReader in = new BufferedReader(reader);
            String zeile = null;
            while ((zeile = in.readLine()) != null) {
                Anzahl ++;
            }
            return Anzahl;
        } catch(Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    //für die Zukunft, soll random Vokabel liefern
    public int randomVok(){
        Random rand = new Random();
        return rand.nextInt(getCount());
    }

    //sortieren nach Alphabet
    public void getNextAlp(){
        int v[] = {};
        int anzahl = getCount();
        for(int i = 0; i < anzahl; i++){
            if (alphSortEN == v) {
                alphSortEN[0] = 0;
            } else {
            }
        }
    }
    //sortieren nach Schwierigkeit
    public void getNextVal(){}

    //Funktion um von spezifischer Vokabel die Value zu bekommen 
    public int getVokValue(int Nr){
        String VALvok = "";
        int aktuelNr = 0;
        try(FileReader reader = new FileReader("VokList.csv")){

            BufferedReader in = new BufferedReader(reader);
            String zeile = null;
            while ((zeile = in.readLine()) != null) {
                if (Nr == aktuelNr){
                    String[] vok = zeile.split(",");
                    VALvok = vok[2];
                }
                aktuelNr += 1;
            }
            int number = Integer.parseInt(VALvok);
            return number;
        } catch(Exception e) {
            e.printStackTrace();
            return 0;
        }
    }   


    public String getVokDE(int Nr){
        String DEvok = "";
        int aktuelNr = 0;
        try(FileReader reader = new FileReader("VokList.csv")){

            BufferedReader in = new BufferedReader(reader);
            String zeile = null;
            while ((zeile = in.readLine()) != null) {
                if (Nr == aktuelNr){
                    String[] vok = zeile.split(",");
                    DEvok = vok[0];
                }
                aktuelNr += 1;
            }
            return DEvok;
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }   

    public String getVokEN(int Nr){
        String ENvok = "";
        int aktuelNr = 0;
        try(FileReader reader = new FileReader("VokList.csv")){

            BufferedReader in = new BufferedReader(reader);
            String zeile = null;
            while ((zeile = in.readLine()) != null) {
                if (Nr == aktuelNr){
                    String[] vok = zeile.split(",");
                    ENvok = vok[1];
                }
                aktuelNr += 1;
            }
            return ENvok;
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }   
    public void delete(String DeutscheVokabel, String EnglischeVokabel){
        File oldFile = new File("VokList.csv");
        File newFile = new File("temp.csv");
        String vDE = ""; String vEN = ""; String vVa = "";


        try (FileWriter fw = new FileWriter("temp.csv", true)) {

            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            Scanner x = new Scanner(new File("VokList.csv"));
            x.useDelimiter("[,\n]");
            while(x.hasNext()){
                vDE = x.next();
                vEN = x.next();
                vVa = x.next();
                if(!vDE.equals(DeutscheVokabel) && !vEN.equals(EnglischeVokabel) ){
                    pw.println(vDE +","+ vEN +","+vVa);
                }
            }
            x.close();
            pw.flush();
            pw.close();
            oldFile.delete();
            File dump = new File("VokList.csv");
            newFile.renameTo(dump);

        } catch (Exception e) {}

    }
    //change a Vok Val
    public void setVal(String VDGWDe, String VDGWEn, int NewVal) throws IOException {
        File oldFile = new File("VokList.csv");
        File newFile = new File("temp.csv");
        String vDE = ""; String vEN = ""; String vVa = ""; 
        
        
        try (FileWriter fw = new FileWriter("temp.csv", true)) {

            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            Scanner x = new Scanner(new File("VokList.csv"));
            x.useDelimiter("[,\n]");
            while(x.hasNext()){
                vDE = x.next();
                vEN = x.next();          
                vVa = x.next();          
                if(vDE.equals(VDGWDe) && vEN.equals(VDGWEn)){
                    pw.println(vDE +","+ vEN +","+NewVal);
                } else{
                    pw.println(vDE +","+ vEN +","+vVa);
                } 
            }
            x.close();
            pw.flush();
            pw.close();
            oldFile.delete();
            File dump = new File("VokList.csv");
            newFile.renameTo(dump);
            
        } catch (Exception e) {}  
    }

    public void setVok(String VDGWDe, String VDGWEn, String newDe,String newEn ) throws IOException {
        File oldFile = new File("VokList.csv");
        File newFile = new File("temp.csv");
        String vDE = ""; String vEN = ""; String vVa = "";


        try (FileWriter fw = new FileWriter("temp.csv", true)) {

            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            Scanner x = new Scanner(new File("VokList.csv"));
            x.useDelimiter("[,\n]");
            while(x.hasNext()){
                vDE = x.next();
                vEN = x.next();
                vVa = x.next();
                if(vDE.equals(VDGWDe) && vEN.equals(VDGWEn)){
                    pw.println(newDe +","+ newEn +","+vVa);
                } else{
                    pw.println(vDE +","+ vEN +","+vVa);
                }
            }
            x.close();
            pw.flush();
            pw.close();
            oldFile.delete();
            File dump = new File("VokList.csv");
            newFile.renameTo(dump);

        } catch (Exception e) {}
    }

    //add a Vok 
    public void add(String vDE, String vEN) throws IOException {

        // first create file object for file placed at location
        // specified by filepath
        BufferedReader in;
   
        try (FileWriter writer = new FileWriter("VokList.csv", true)) {

            StringBuilder sb = new StringBuilder();
            
            sb.append(vDE);
            sb.append(',');
            sb.append(vEN);
            sb.append(',');
            sb.append(0);
            sb.append('\n');

            writer.write(sb.toString());
            writer.close();
            
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}