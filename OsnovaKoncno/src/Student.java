
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor
 */

/**
 *
 * @author primoz
 */
public class Student implements java.io.Serializable{
    
    public static void main(String[] args) {
        SeznamiUV seznamiUV = new SeznamiUV();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input;
        String output;
        try{
            do{   
                System.out.print("command>");
                input = br.readLine();
                output = seznamiUV.processInput(input);
                System.out.println(output);
            }
            while (!input.equalsIgnoreCase("exit"));
        }
        catch (IOException e){
            System.err.println("Failed to retrieve the next command.");
            System.exit(1);
        }
    }
}
