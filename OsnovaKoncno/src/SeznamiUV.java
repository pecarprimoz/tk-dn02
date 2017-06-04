
import java.util.HashMap;
import java.util.StringTokenizer;
import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


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
            if(input.length()>8){
                return null;
            }
            
            if(!input.matches("[0-9]+")){
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
            if(!input.matches("[1-9].[0-9]")){
                if(!input.matches("[1-9][0-9]"))
                   if(!input.matches("10.0"))
                    return null;
            }
            avg_grade=Double.parseDouble(input);
            return new Studenti(fname, lname, ID, avg_grade);
    }
    public String promptUserToDelete() throws IOException{
        String fname="";
        String lname="";
        String input;
        String result;
        BufferedReader br_name = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("remove> First name: ");
            input = br_name.readLine();
            if(!input.matches("[a-zA-Z ]+")){
                return "Invalid input data.";
            }
            fname=input;
        BufferedReader br_lname= new BufferedReader(new InputStreamReader(System.in));
        System.out.print("remove> Last name: ");
            input = br_lname.readLine();
            if(!input.matches("[a-zA-Z ]+")){
                return "Invalid input data.";
            }
            lname=input;
        Studenti tmp = dv.remove(new Studenti(fname, lname, "", 0));
        if(tmp!=null){
            return tmp.toString();
        }
        else{
            return null;
        }
    }
    public String promptUserToSearch() throws IOException{
        String fname="";
        String lname="";
        String input;
        String result;
        BufferedReader br_name = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("search> First name: ");
            input = br_name.readLine();
            if(!input.matches("[a-zA-Z ]+")){
                return "Invalid input data.";
            }
            fname=input;
        BufferedReader br_lname= new BufferedReader(new InputStreamReader(System.in));
        System.out.print("search> Last name: ");
            input = br_lname.readLine();
            if(!input.matches("[a-zA-Z ]+")){
                return "Invalid input data.";
            }
            lname=input;
        Studenti tmp = dv.search(new Studenti(fname, lname, "", 0));
        if(tmp!=null){
            return tmp.toString();
        }
        else{
            return null;
        }
    }
    public String assemblePrint(){
        String endstring="";
        
        int num_of_students = dv.size();
        if(num_of_students==0){
            return ">> No. of students: 0";
        }
        endstring+=">> No. of students "+num_of_students+"\n";
        
        List <Studenti> al = dv.asList();
        al.sort(new Comparator<Studenti>() {
            @Override
            public int compare(Studenti o1, Studenti o2) {
                return o1.priimek.compareTo(o2.priimek);
            }
        });
        
        for(Studenti s : al){
            endstring+="\t"+s.ID+" | "+s.priimek+" | "+s.ime+" | "+s.povpOcena+"\n";
        }
        return endstring;
    }
    public String promptUserReset()throws IOException{
        BufferedReader br_name = new BufferedReader(new InputStreamReader(System.in));
        String input;
        System.out.print("reset> Are you sure (y|n):");
            input=br_name.readLine();
        if(input.equals("y")){
            dv.reset();
        }
        else if(input.equals("n")){
            return "Whats the point of this";
        }
        return null;
        
    }
    
    
    public String processInput(String input) {
        String token;
        String result = "OK";
        String[] params = input.split(" ");

        //moramo preverjati za primer praznega stringa
        if (params.length == 0 || params[0].equals("")) {
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
            //nvm baje so za to sistemski testi
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
                
            if(params.length==2){
                if(token.equals("remove")){
                    String sifra = params[1];
                    if(!sifra.matches("[0-9]+")){
                        return "Invalid input data.";
                    }
                    Studenti temp = dv.remove(new Studenti("", "", sifra, 0));
                    if(temp!=null){
                        return "OK";
                    }
                }
                else if(token.equals("search")){
                    String sifra = params[1];
                    if(!sifra.matches("[0-9]+")){
                        return "Invalid input data.";
                    }
                    Studenti temp = dv.search(new Studenti("", "", sifra, 0));
                    if(temp!=null){
                        return temp.toString();
                    }
                    else{
                        return "Student does not exist.";
                    }
                }
                else if(token.equals("save")){
                    String filename=params[1];
                    dv.save(new FileOutputStream(new File(filename)));
                    return "OK";
                }
                else if(token.equals("restore")){
                    String filename=params[1];
                    dv.restore(new FileInputStream(new File(filename)));
                    return "OK";

                }
            }if(params.length==1){
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
                result = promptUserToDelete();
                
                if(result==null){
                    return "Student does not exist.";
                }
                if(result.equals("Invalid input data.")){
                    return "Invalid input data.";
                }
                if(result != null){
                    return "OK";
                }
            }
            else if(token.equals("search")){
                result = promptUserToSearch();
                if(result==null){
                    return "Student does not exist.";
                }
                if(result.equals("Invalid input data.")){
                    return "Invalid input data.";
                }
            }
            else if(token.equals("print")){
                return assemblePrint();
            }
            else if(token.equals("count")){
                int size = dv.size();
                return ">> No. of students: "+size;
            }
            else if(token.equals("reset")){
                promptUserReset();
                if(result==null){
                    return "Invalid input data.";
                }
                result = "OK";
            }
                else{
                    result = "Invalid command.";
                }
            }
            
                

        } catch (UnsupportedOperationException e) {
            result = "Error: Operation not supported";
        } catch (IllegalArgumentException e) {
            result = "Error: Student does not exist";
        } catch (java.util.NoSuchElementException e) {
            result = "Error: structure is empty";
        }   catch (OutOfMemoryError e) {
            return memoryError;
        }
        catch (ClassNotFoundException e) {
            System.out.println("I/O Error:"+e.toString());
        }catch (IOException e) {
            System.out.println(e.toString());
        }

        return result;
    }
}
