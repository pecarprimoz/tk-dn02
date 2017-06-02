
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

 
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author primoz
 */



class PrimerjajPoTipu <Tip extends Comparable>  implements java.util.Comparator<Tip>
{
    @Override
    public int compare(Tip o1, Tip o2)
    {
        return o1.compareTo(o2);
    }
}

class StudentPrimerjajPoImenu<Tip extends Comparable> implements java.util.Comparator<Studenti>
{
    @Override
    public int compare(Studenti o1, Studenti o2)
    {
        String sifra1=o1.getID();
        String sifra2=o2.getID();
        int sifreOK = sifra1.compareToIgnoreCase(sifra2);
        String imePriimek1=o1.getIme()+o1.getPriimek();
        String imePriimek2=o2.getIme()+o2.getPriimek();
        int imenaOk=imePriimek1.compareToIgnoreCase(imePriimek2);
        if(sifreOK==0 || imenaOk == 0){
            return 0;
        }
        return sifreOK;
    }
}
class StPrimerjajPoTelSt<Studenti extends Comparable>implements java.util.Comparator<Integer>
{
    @Override
    public int compare(Integer t, Integer t1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}


//prej je blo  <Tip extends Comparable>
public class Drevo23<Tip> implements Seznam<Tip>{

    @Override
    public void print() {
        for(Tip element : asList()){
            //make this preatty
            System.out.println(element);
        }
    }

    @Override
    public void save(OutputStream outputStream) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(outputStream);
        out.writeInt(this.size());
        if(this.root_node!=null){
            out.writeObject(this.root_node);
        }
        else{
            System.out.println("The data structure is empty, nothing to save.");
        }
        
    }

    
    class Node23{
        public Node23 parent;
        public Tip left,right;
        public Node23 left_child, mid_child, right_child;
        
        public Node23(Tip e){
            parent=null;
            left = e;
            right = null;
            left_child = null;
            mid_child = null;
            right_child = null;
            
        }
    }
    public Comparator<Tip> comparator;
    Node23 root_node;
        public Drevo23(Comparator<Tip> comparator){
            this.comparator = comparator;
            this.root_node = null;
        }
    
    public Node23 get_root_node(){
        return this.root_node;
    }
    public boolean isLeaf(){
        return this.root_node.left_child==null && this.root_node.mid_child==null && this.root_node.right_child==null;
    }
    
    @Override
    public void restore(InputStream inputStream) throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(inputStream);
        int count = in.readInt();
        if(count==0){
            System.out.println("Nothing to read.");
        }
        else{
            this.root_node=(Node23)in.readObject();
        }
    }
    
    public void addUpper(Tip e){
        //v primeru da imamo zgoraj null lahko samo dodamo, v primeru da nimamo
        //moramo narediti split, podobno kot spodaj
        if(this.root_node.right==null){
            if(comparator.compare(this.root_node.left, e)>0){
                this.root_node.right=this.root_node.left;
                this.root_node.left=e;
            }

            else if(comparator.compare(this.root_node.left,e)<0){
                this.root_node.right=e;
            }
        }
    }
    public Node23 split(Tip e){
        Node23 tmp = this.root_node.parent;
        Node23 upper_root = null;
        
            if(comparator.compare(e, tmp.left)>0 && comparator.compare(e, tmp.right)<0){
                /*
                19, 4, 20, 17, 12, 15, 13
                
                              15
                           /      \
                        12         19
                        / \       /    \
                      4  13     17      20
                
                
                */
                //Nastavimo nova vozlišča
                upper_root= new Node23(e);
                Node23 new_left_child=new Node23(tmp.left);
                Node23 new_right_child= new Node23(tmp.right);
                
                //nastavimo parente za otroke roota
                
                new_left_child.parent=upper_root;
                new_right_child.parent=upper_root;
                
                //porihtamo otroke otrok in parente
                new_left_child.left_child = new Node23(tmp.left_child.left);
                new_left_child.right_child= new Node23(tmp.mid_child.left);
                new_left_child.left_child.parent=new_left_child;
                new_left_child.right_child.parent=new_left_child;
                
                new_right_child.left_child= new Node23(tmp.mid_child.right);
                new_right_child.right_child= new Node23(tmp.right_child.left);
                new_right_child.left_child.parent=new_right_child;
                new_right_child.right_child.parent=new_right_child;
                upper_root.left_child=new_left_child;
                upper_root.right_child=new_right_child;
                
                //SEDAJ MORS TAKO RAZPOREDIT NODE-E DA VSAK DOBI 2 OTROKA, ZGORNJI SE SPLITTA NA root-2 otroka, 2 otroka,
                //glej vizualizacijo za 19,4,20,17,12,15,13
            }
            //POMENI DA JE NOVI ELEMENT VEČJI OD DESNEGA V ROOT-U, KOT MEDIANO VZAMEMO TOREJ DESNEGA
            
            else if(comparator.compare(e, tmp.right)>0){
                /*
                10, 4, 15, 16, 17, 18, 18
                
                              15
                           /      \
                        12         19
                        / \       /    \
                      4  13     17      20
                
                
                */
                //Nastavimo nova vozlišča
                upper_root= new Node23(tmp.right);
                Node23 new_left_child=new Node23(tmp.left);
                Node23 new_right_child= new Node23(e);
                
                //nastavimo parente za otroke roota
                
                new_left_child.parent=upper_root;
                new_right_child.parent=upper_root;
                
                //porihtamo otroke otrok in parente
                new_left_child.left_child = new Node23(tmp.left_child.left);
                new_left_child.right_child= new Node23(tmp.mid_child.left);
                new_left_child.left_child.parent=new_left_child;
                new_left_child.right_child.parent=new_left_child;
                
                new_right_child.left_child= new Node23(tmp.right_child.left);
                new_right_child.right_child= new Node23(tmp.right_child.right);
                new_right_child.left_child.parent=new_right_child;
                new_right_child.right_child.parent=new_right_child;
                upper_root.left_child=new_left_child;
                upper_root.right_child=new_right_child;
                
            }
            
            else if(comparator.compare(e, tmp.left)<0){
                //Nastavimo nova vozlišča
                upper_root= new Node23(tmp.left);
                Node23 new_left_child=new Node23(e);
                Node23 new_right_child= new Node23(tmp.right);
                
                //nastavimo parente za otroke roota
                
                new_left_child.parent=upper_root;
                new_right_child.parent=upper_root;
                
                //porihtamo otroke otrok in parente
                new_left_child.left_child = new Node23(tmp.left_child.left);
                new_left_child.right_child= new Node23(tmp.left_child.right);
                new_left_child.left_child.parent=new_left_child;
                new_left_child.right_child.parent=new_left_child;
                
                new_right_child.left_child= new Node23(tmp.mid_child.left);
                new_right_child.right_child= new Node23(tmp.right_child.left);
                new_right_child.left_child.parent=new_right_child;
                new_right_child.right_child.parent=new_right_child;
                upper_root.left_child=new_left_child;
                upper_root.right_child=new_right_child;
                
                
            }
            return upper_root;
    }
    
    @Override
    public void add(Tip e) {
        if(this.root_node==null){
            this.root_node= new Node23(e);
            return;
        }
        boolean isSet=isLeaf();
        
        if(this.root_node.left!=null && comparator.compare(e, this.root_node.left)==0 ||
           this.root_node.right!=null && comparator.compare(e, this.root_node.right)==0){
            throw new java.lang.IllegalArgumentException();
        }
        
        
        if(isSet && this.root_node.right==null){
            if(comparator.compare(this.root_node.left, e)>0){
                this.root_node.right=this.root_node.left;
                this.root_node.left=e;
            }
            else if(comparator.compare(this.root_node.left, e)<0){
                this.root_node.right=e;
            }
            
        }
        else{
            if(isSet && this.root_node.right!=null){
            //najprej je potrebno najti mediano med temi tremi, torej srednji element    
            //sredinski split, LEFT ostane kot left v left child
            if(this.root_node.parent!=null){
                if(this.root_node.parent.right!=null) 
                if(comparator.compare(e, this.root_node.right)>0){
                    Tip tmp = this.root_node.right;
                    this.root_node.right=e;
                    this.root_node=split(tmp);
                    return;
                }
                else if(comparator.compare(e, this.root_node.right)<0 && comparator.compare(e, this.root_node.left)>0){
                    this.root_node=split(e);
                    return;
                }
                else{
                    //postavim levega na e, ker je e left right in left gre gor
                    Tip tmp = this.root_node.left; //NAS ORIGINALNI LEVI ELEMENT
                    this.root_node.left=e; //NAS NOV LEVI ELEMENT
                    this.root_node=split(tmp);
                    return;
                }
            }
            
            if(comparator.compare(e, this.root_node.right)>0){
                Tip medi = this.root_node.right;
                if(this.root_node.parent!=null){
                    Node23 tmp = this.root_node;
                    this.root_node=this.root_node.parent;
                    addUpper(medi);
                    if(this.root_node.right_child.right!=null){
                        this.root_node.mid_child = new Node23(this.root_node.right_child.left);
                        this.root_node.right_child = new Node23(e);
                        this.root_node=this.root_node.right_child;
                    }
                    else if(this.root_node.left_child.right!=null){
                        this.root_node.mid_child = new Node23(e);
                        this.root_node=tmp;
                        this.root_node.right=null;
                    }
                    
                    
                    return;
                }
                
                //pomeni da je nov element večji od right
            }
            
            else if(comparator.compare(this.root_node.left, e)>0 ){
                Tip medi = this.root_node.left;
                if(this.root_node.parent!=null){
                    Node23 tmp = this.root_node;
                    this.root_node=this.root_node.parent;
                    addUpper(medi);
                    if(this.root_node.right_child.right!=null){
                        this.root_node.mid_child = new Node23(e);
                        this.root_node.right_child = new Node23(this.root_node.right_child.right);
                        this.root_node=this.root_node.right_child;
                    }
                    else if(this.root_node.left_child.right!=null){
                        this.root_node.mid_child = new Node23(tmp.right);
                        this.root_node=tmp;
                        this.root_node.left=e;
                        this.root_node.right=null;
                            }
                    return;
                }
                //pomeni da je nov element manjši od left, in right
            }
                else{
                    //medi gre gor kot left glavnega
                    Tip medi = e;
                    if(this.root_node.parent!=null){
                        Node23 tmp = this.root_node;
                        this.root_node=this.root_node.parent;
                        addUpper(medi);

                        this.root_node.left_child = new Node23(tmp.left);
                        this.root_node.mid_child = new Node23(tmp.right);
                        this.root_node=tmp;
                        this.root_node.right=null;
                        return;
                    }
                }
            }
            /*
            //V primeru da imamo element na levem in element na sredini, nemoramo 
        //dati element na desnega, saj bi tako imeri 3 elemente na node
        
        //V primeru da imamo element na desnem in element na sredini, nemoramo
        //dati element na levega, saj bi tako imeli 3 elemente na node
        
            */
            
            if(this.root_node.left!=null && isSet){
                int compareLeft = comparator.compare(e,this.root_node.left);
                if(compareLeft<0){
                    Tip tmp = this.root_node.right;
                    this.root_node.left_child = new Node23(e);
                    this.root_node.right_child = new Node23(tmp);
                    this.root_node.right=null;
                    return;
                }
                else if(compareLeft>0){
                    Tip tmp = this.root_node.left;
                    this.root_node.left_child = new Node23(this.root_node.left);
                    this.root_node.right_child = new Node23(e);
                    this.root_node.left=this.root_node.right;
                    this.root_node.right=null;
                    return;
                }
            }
            /*
            if(this.root_node.right!=null && isSet){
                int compareRight = e.compareTo(this.root_node.right);
                    if(compareRight<0){
                        System.out.println("test");
                        Tip tmp = this.root_node.right;
                        this.root_node.left_child = new Node23(e);
                        this.root_node.right_child = new Node23(tmp);
                        this.root_node.left=null;
                        this.root_node.right=null;
                        return;
                    }  
                    else if(compareRight>0){
                        System.out.println("test");
                        Tip tmp = this.root_node.right;
                        this.root_node.left_child = new Node23(this.root_node.left);
                        this.root_node.right_child = new Node23(e);
                        this.root_node.left=tmp;
                        this.root_node.left=null;
                        this.root_node.right=null;
                        return;
                    }            
            }*/
            
            
            if(comparator.compare(e, this.root_node.left)<0){
                Node23 temporary_center = this.root_node;
                this.root_node=this.root_node.left_child;
                add(e);
                if(this.root_node.parent==null && this.root_node.right==null){
                    return;
                }
                
                Node23 temporary_left= this.root_node;
                this.root_node=temporary_center;
                this.root_node.left_child=temporary_left;
                this.root_node.left_child.parent=this.root_node;
                return;
            }
            if(this.root_node.right==null){
                
                if(comparator.compare(e, this.root_node.left)>0){
                    Node23 temporary_center = this.root_node;
                    this.root_node=this.root_node.right_child;
                    add(e);
                    Node23 temporary_right= this.root_node;
                    this.root_node=temporary_center;
                    this.root_node.right_child=temporary_right;
                    this.root_node.right_child.parent=this.root_node;
                    return;
                }
            }
            else if(comparator.compare(e, this.root_node.right)<0){
                Node23 temporary_center = this.root_node;
                    this.root_node=this.root_node.mid_child;
                    add(e);
                    if(this.root_node.right==null){
                        return;
                    }
                    Node23 temporary_mid= this.root_node;
                    this.root_node=temporary_center;
                    this.root_node.mid_child=temporary_mid;
                    this.root_node.mid_child.parent=this.root_node;
                    return;
                
            }
            
            else if(comparator.compare(e, this.root_node.right)>0){
                Node23 temporary_center = this.root_node;
                    this.root_node=this.root_node.right_child;
                    add(e);
                    if(this.root_node.right==null){
                        return;
                    }
                    Node23 temporary_right= this.root_node;
                    this.root_node=temporary_center;
                    this.root_node.right_child=temporary_right;
                    this.root_node.right_child.parent=this.root_node;
                    return;
                
            }
        }
    }

    @Override
    public Tip removeFirst() {
        if(this.root_node==null){
            throw new java.util.NoSuchElementException();
        }
        return remove(getFirst());
    }

    @Override
    public Tip getFirst() {
        if(this.root_node==null) throw new java.lang.IllegalArgumentException();
        return this.root_node.left;
    }

    @Override
    public int size() {
        int size = 0;
        Node23 original = this.root_node;
        if(this.root_node==null){
            return 0;
        }
        if(this.root_node.right!=null){
            size+=2;
        }
        else{
            size+=1;
        }
        if(this.root_node.left_child!=null){
            this.root_node=this.root_node.left_child;
            size+=size();
            this.root_node=original;
        }
        if(this.root_node.right_child!=null){
            this.root_node=this.root_node.right_child;
            size+=size();
            this.root_node=original;
        }
        
        if(this.root_node.mid_child!=null){
            this.root_node=this.root_node.mid_child;    
            size+=size();
            this.root_node=original;
        }
        return size;
        
                
    }

    @Override
    public int depth() {
        if(this.root_node==null){
            return 0;
        }
        
        if(this.root_node.left_child == null){
            return 1;
        }
        Node23 original = this.root_node;
        //Preverimo levo poddrevo
        int globinaLevi=0;
        while(!isLeaf()){
            globinaLevi++;
            this.root_node=this.root_node.left_child;
        }
        this.root_node=original;
        int globinaDesni=0;
        while(!isLeaf()){
            globinaDesni++;
            this.root_node=this.root_node.right_child;
        }
        return 1 + Math.max(globinaLevi, globinaDesni);
    }

    @Override
    public boolean isEmpty() {
        return this.root_node==null;
    }
    public void reset(){
        this.root_node=null;
    }

    @Override
    public Tip remove(Tip e) {
        Node23 orig = this.root_node;
        Tip t = null;
        if(isEmpty()){
            return null;
        }
        if(!exists(e) || this.root_node==null){
            throw new java.lang.IllegalArgumentException();
        }
        if(comparator.compare(this.root_node.left,e)==0){
            if(this.root_node.right==null){
                t = this.root_node.left;
                this.root_node.left=null;
                this.root_node=null;
                return t;
            }
            else{
                t = this.root_node.left;
                this.root_node.left=this.root_node.right;
                this.root_node.right=null;
                /*
                if(this.root_node.left_child!=null && this.root_node.left_child.right!=null){
                    this.root_node.right=this.root_node.left;
                    this.root_node.left=this.root_node.left_child.right;
                    this.root_node.left_child.right=null;
                }
                else if(this.root_node.mid_child!=null && this.root_node.mid_child.right!=null){
                    this.root_node.right=this.root_node.left;
                    this.root_node.left=this.root_node.mid_child.left;
                    this.root_node.mid_child.left=this.root_node.mid_child.right;
                    this.root_node.mid_child.right=null;
                }*/
                return t;
            }
        }
        if(this.root_node.right!=null){
            
            if(comparator.compare(this.root_node.right,e)==0){
                t = this.root_node.right;
                this.root_node.right=null;
                return t;
            }
        }
        //e je manjsi od levega, gremo v levo vejo
        
        if(comparator.compare(this.root_node.left,e)>0){
            if(this.root_node.left_child!=null){
                this.root_node=this.root_node.left_child;
                t=remove(e);
                this.root_node=orig;
                if(this.root_node.left_child.left==null){
                    this.root_node.left_child=null;
                }
                fixNode();
                
            }
        }
        //e je vecji od levega, preverimo ce mamo mid, ce nimamo gremo v desno vejo
        else if(comparator.compare(this.root_node.left, e)<0){
            //v primeru da obsaja srednji otrok, da desni otrok ni null in da je e manjsi od desnega
            //se moramo spustiti v srednjega otroka
            if(this.root_node.mid_child!=null && this.root_node.right_child!=null 
                    
                    && comparator.compare(this.root_node.right_child.left, e)>0){
                this.root_node=this.root_node.mid_child;
                t=remove(e);
                this.root_node=orig;
                if(this.root_node.mid_child.left==null){
                    this.root_node.mid_child=null;
                }
                fixNode();
            }
            else if(this.root_node.right_child!=null){
                this.root_node=this.root_node.right_child;
                t=remove(e);
                this.root_node=orig;
                if(this.root_node.right_child.left==null){
                    this.root_node.right_child=null;
                }
                fixNode();
            }
        }
        return t;
    }
    public void fixNode(){
        if(this.root_node.left!=null){
            if(this.root_node.right==null){
                if(this.root_node.left_child!=null && this.root_node.right_child==null
                        && this.root_node.left_child.right==null){
                    this.root_node.right=this.root_node.left;
                    this.root_node.left=this.root_node.left_child.left;
                    this.root_node.left_child=null;
                }
                else if(this.root_node.left_child!=null && this.root_node.left_child.right!=null){
                    Tip nov_desni = this.root_node.left;
                    this.root_node.left=this.root_node.left_child.right;
                    this.root_node.left_child.right=null;
                    Node23 new_right_child = new Node23(nov_desni);
                    this.root_node.right_child=new_right_child;
                }
                if(this.root_node.right_child!=null && this.root_node.left_child==null
                        && this.root_node.right_child.right==null){
                    this.root_node.right=this.root_node.right_child.left;
                    this.root_node.right_child=null;
                }
                else if(this.root_node.right_child!=null && this.root_node.right_child.right!=null){
                    Tip nov_levi=this.root_node.left;
                    this.root_node.left=this.root_node.right_child.left;
                    this.root_node.right_child.left=this.root_node.right_child.right;
                    this.root_node.right_child.right=null;
                    Node23 new_left_child = new Node23(nov_levi);
                    this.root_node.left_child= new_left_child;
                }
            }
            if(this.root_node.left_child!=null){
                if(this.root_node.left_child.right==null && this.root_node.right_child!=null &&
                        this.root_node.right_child.right==null && this.root_node.mid_child==null &&
                        this.root_node.right!=null){
                     this.root_node.left_child.right=this.root_node.left;
                     this.root_node.left=this.root_node.right;
                     this.root_node.right=null;
                }
            }
        }
    }

    @Override
    public boolean exists(Tip e) {
        Node23 org = this.root_node;
        if(this.root_node==null){
            return false;
        }
        
        if(comparator.compare(this.root_node.left, e)==0){
            this.root_node=org;
            return true;
        }
        else if(this.root_node.right!=null){
            if(comparator.compare(this.root_node.right, e)==0){
                this.root_node=org;
                return true;
            }
        }
        if(this.root_node.left_child!=null){
            Node23 tmp = this.root_node;
            this.root_node=this.root_node.left_child;
            if(exists(e)){
                this.root_node=org;
                return true;
            }
            this.root_node=tmp;
        }
        if(this.root_node.right_child!=null){
            Node23 tmp = this.root_node;
            this.root_node=this.root_node.right_child;
            if(exists(e)){
                this.root_node=org;
                return true;
            }
            this.root_node=tmp;
        }
        if(this.root_node.mid_child!=null){
            Node23 tmp = this.root_node;
            this.root_node=this.root_node.mid_child;
            if(exists(e)){
                this.root_node=org;
                return true;
            }
        }
        this.root_node=org;
        return false;
    }
    
    public Tip search(Tip e) {
        Node23 org = this.root_node;
        Tip holdme=null;
        if(this.root_node==null){
            return null;
        }
        
        if(comparator.compare(this.root_node.left, e)==0){
            this.root_node=org;
            return this.root_node.left;
        }
        else if(this.root_node.right!=null){
            if(comparator.compare(this.root_node.right, e)==0){
                this.root_node=org;
                return this.root_node.right;
            }
        }
        if(this.root_node.left_child!=null){
            Node23 tmp = this.root_node;
            this.root_node=this.root_node.left_child;
            if(exists(e)){
                holdme = search(e);
                this.root_node=org;
                
            }
            this.root_node=tmp;
        }
        if(this.root_node.right_child!=null){
            Node23 tmp = this.root_node;
            this.root_node=this.root_node.right_child;
            if(exists(e)){
                holdme = search(e);
                this.root_node=org;
            }
            this.root_node=tmp;
        }
        if(this.root_node.mid_child!=null){
            Node23 tmp = this.root_node;
            this.root_node=this.root_node.mid_child;
            if(exists(e)){
                holdme = search(e);
                this.root_node=org;
            }
        }
        this.root_node=org;
        if(holdme!=null){
            return holdme;
        }
        return null;
    }

    
    public List<Tip> asList() {
        List <Tip> mylist = new ArrayList<>();
        Node23 original = this.root_node;
        if(this.root_node==null){
            return mylist;
        }
        if(this.root_node.right!=null){
            mylist.add(this.root_node.left);
            mylist.add(this.root_node.right);
        }
        else{
            mylist.add(this.root_node.left);
        }
        if(this.root_node.left_child!=null){
            this.root_node=this.root_node.left_child;
            mylist.addAll(asList());
            this.root_node=original;
        }
        if(this.root_node.right_child!=null){
            this.root_node=this.root_node.right_child;
            mylist.addAll(asList());
            this.root_node=original;
        }
        
        if(this.root_node.mid_child!=null){
            this.root_node=this.root_node.mid_child;    
            mylist.addAll(asList());
            this.root_node=original;
        }
        return mylist;
    }
    
    
}
