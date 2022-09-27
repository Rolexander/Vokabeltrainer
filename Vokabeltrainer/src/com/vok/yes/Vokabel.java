package com.vok.yes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.io.FileReader;
import java.io.File;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;

public class Vokabel{
   // String path = "/Users/schule/eclipse-workspace/Vokabeltrainer/src/com/vok/yes/res/dictinary.csv";
    String basePath = new File("").getAbsolutePath();
    String path = new File("src/com/vok/yes/res/dictinary.csv").getAbsolutePath();
    String line = null;
    List<Vokabel> liste = new List<Vokabel>();

    public Vokabel( String a, String b){
    	liesVokabelnEin();
    }
    
    public List<Vokabel> liesVokabelnEin(){
        try(FileReader reader = new FileReader(path)){
            BufferedReader br = new BufferedReader(new FileReader(path));
            while((line = br.readLine()) != null){
                String[] werte = line.split(",");
                String dt = werte[0];
                String en = werte[1];
            }
        }catch(Exception e){
            System.out.println("Something went wrong I.");
        }
        return liste;
    }

    //add a Vok 
    public void add(String vDE, String vEN) throws IOException {

        // first create file object for file placed at location
        // specified by filepath

        try (FileWriter writer = new FileWriter(path, true)) {

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
    
    //Funktion um von spezifischer Vokabel die Value zu bekommen 
    public int getVokValue(int Nr){
        String VALvok = "";
        int aktuelNr = 0;
        try(FileReader reader = new FileReader(path)){     
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
    
    public String getVokEN(int Nr){
        String ENvok = "";
        int aktuelNr = 0;
        try(FileReader reader = new FileReader(path)){     
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
    
    public void delVok(String DeutscheVokabel, String EnglischeVokabel){
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
}