
import java.util.HashMap;
import java.util.StringTokenizer;
import java.io.*;
import java.util.ArrayList;


public class SeznamiUV {

    Drevo23 <Studenti>dv;
    
    
    static String memoryError = "Error: not enough memory, operation failed";
   
    public SeznamiUV() {
        dv = new Drevo23(new StudentPrimerjajPoImenu<>());
    }
    public Studenti promptUserToAdd() throws IOException{
        String ID="";
        String fname="";
        String lname="";
        double avg_grade=0.0;
        String input;
        BufferedReader br_ID = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("add> Student ID: ");
            input = br_ID.readLine();
            if(!input.matches("[0-9]+") && input.length()<9){
                return null;
            }
            ID=input;
        BufferedReader br_name = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("add> First name: ");
            input = br_name.readLine();
            if(!input.matches("[a-zA-Z ]+")){
                return null;
            }
            fname=input;
        BufferedReader br_surname = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("add> Last name: ");
            input = br_surname.readLine();
            if(!input.matches("[a-zA-Z]+")){
                return null;
            }
            lname=input;
        BufferedReader br_num = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("add> Avg. grade: ");
            input = br_num.readLine();
            if(!input.matches("[0-9].[0-9]")){
                return null;
            }
            avg_grade=Double.parseDouble(input);
            return new Studenti(fname, lname, ID, avg_grade);
    }
    public boolean promptUserToDelete(){
        return true;
    }
    public String processInput(String input) {
        String token;
        String result = "OK";
        String[] params = input.split(" ");

        //moramo preverjati za primer praznega stringa
        if (params.length == 0) {
            return "Error: enter command";
        }
        else{
            token = params[0];
        }
        if(token.equals("exit")){
            return "Goodbye.";
        }
        try {
            //Napis se teste za to, FAKING PARAMSE MORS GLEDAT KER JE TAKO ZASTAVLJENA NALOGA
            if(params.length>3){
                if(token.equals("add") && params.length==5){
                    Studenti curStudenti = new Studenti(params[2], params[3], params[1], Double.parseDouble(params[4]));
                    if(!dv.exists(curStudenti)){
                    dv.add(curStudenti);
                    result = "OK";
                }
                else{
                    result = "Student already exists.";
                }
                }
                else{
                    return "Invalid argument";
                }
                return result;
            }
                
            
            if (token.equals("add")){
                Studenti curStudent = promptUserToAdd();
                if(curStudent==null){
                    return "Invalid input data";
                }
                if(!dv.exists(curStudent)){
                    dv.add(curStudent);
                    result = "OK";
                }
                else{
                    result = "Student already exists.";
                }
            }
            else if(token.equals("remove")){
                promptUserToDelete();
            }
            else{
                result = "Invalid command.";
            }
                

        } catch (UnsupportedOperationException e) {
            result = "Error: Operation not supported";
        } catch (IllegalArgumentException e) {
            result = "Error: Duplicated entry";
        } catch (java.util.NoSuchElementException e) {
            result = "Error: structure is empty";
        }   catch (OutOfMemoryError e) {
            return memoryError;
        }catch (IOException e) {
            result = "Error: Wrong at add.";
        }

        return result;
    }
}
