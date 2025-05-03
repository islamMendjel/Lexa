import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
	static Arcs arcs_Head;
	static Etats etats_Head;
	static Etats etat_next = null;
	static int etat_nextID = 0;

	public static void add_Arc(int dest,String cont) {
		Arcs arc = new Arcs(dest, cont, null);

		if(arcs_Head == null) {
			arcs_Head = arc;
		}
		else {
			Arcs a = arcs_Head;
			while(a.next != null) {
				a =a.next;
			}
			a.next =arc;
		}
	}
	
	public static void add_Etat(int etat_ID,boolean is_Final,String tag,Arcs arc) {
		Etats etat = new Etats(etat_ID, is_Final, tag, null, arc);
		
		if(etats_Head == null) {
			etats_Head = etat;
		}
		else {
			Etats b = etats_Head;
			while(b.next != null) {
				b = b.next;
			}
			b.next = etat;
		}
	}

	public static void add_Etat(int etat_ID,Arcs arc) {
		Etats etat = new Etats(etat_ID, null, arc);
		
		if(etats_Head == null) {
			etats_Head = etat;
		}
		else {
			Etats b = etats_Head;
			while(b.next != null) {
				b = b.next;
			}
			b.next = etat;
		}
	}
	
	public static void create_automate() {
		add_Arc(1,"l-{p,b,e,i,r,c,v,t,R,s,J}");
		add_Arc(6,"i");
		add_Arc(26,"p");
		add_Arc(14,"e");
		add_Arc(38,"v");
		add_Arc(33,"c");
		add_Arc(21,"b");
		add_Arc(17,"r");
		add_Arc(5,"ch");
		add_Arc(41,"t");
		add_Arc(45,":");
		add_Arc(46,"=");
		add_Arc(47,">");
		add_Arc(48,"<");
		add_Arc(49,";");
		add_Arc(50,"+");
		add_Arc(51,"-");
		add_Arc(52,"*");
		add_Arc(57,"(");
		add_Arc(58,")");
		add_Arc(59,",");
		add_Arc(60,".");
		add_Arc(65,"R");
		add_Arc(77,"s");
		add_Arc(88,"J");
		add_Arc(100,"'");
		add_Etat(0, arcs_Head);
		arcs_Head = null;
//----------------------------------------------------------------------
		add_Arc(2,"l");
		add_Arc(3,"ch");
		add_Etat(1,true,"identificateur",arcs_Head);
		arcs_Head = null;
		
		add_Arc(1,"l");
		add_Arc(4,"ch");
		add_Etat(2,true,"identificateur",arcs_Head);
		arcs_Head = null;
		
		add_Arc(4,"l");
		add_Arc(3,"ch");
		add_Etat(3,true,"identificateur",arcs_Head);
		arcs_Head = null;
		
		add_Arc(3,"l");
		add_Arc(4,"ch");
		add_Etat(4, arcs_Head);
		arcs_Head = null;
//----------------------------------------------------------------------
		add_Arc(5,"ch");
		add_Arc(95,".");
		add_Arc(97,"Expo");
		add_Etat(5,true,"nombre",arcs_Head);
		arcs_Head = null;
		
		add_Arc(96,"ch");
		add_Etat(95, arcs_Head);
		arcs_Head = null;
		
		add_Arc(96,"ch");
		add_Arc(97,"Expo");
		add_Etat(96,true,"nombre_reel",arcs_Head);
		arcs_Head = null;
		
		add_Arc(98,"+");
		add_Arc(98,"-");
		add_Etat(97, arcs_Head);
		arcs_Head = null;
		
		add_Arc(99,"ch");
		add_Etat(98, arcs_Head);
		arcs_Head = null;
		
		add_Arc(99,"ch");
		add_Etat(99,true,"nombre_reel",arcs_Head);
		arcs_Head = null;
//----------------------------------------------------------------------
		add_Arc(2,"l-{n,f}");
		add_Arc(3,"ch");
		add_Arc(13,"f");
		add_Arc(7,"n");
		add_Etat(6,true,"identificateur",arcs_Head);
		arcs_Head = null;

		add_Arc(4,"ch");
		add_Arc(1,"l");
		add_Etat(13,true,"mot_cle",arcs_Head);
		arcs_Head = null;
		
		add_Arc(8,"t");
		add_Arc(1,"l-{t}");
		add_Arc(4,"ch");
		add_Etat(7,true,"identificateur",arcs_Head);
		arcs_Head = null;
		
		add_Arc(2,"l-{e}");
		add_Arc(3,"ch");
		add_Arc(9,"e");
		add_Etat(8,true,"identificateur",arcs_Head);
		arcs_Head = null;
		
		add_Arc(1,"l-{g}");
		add_Arc(4,"ch");
		add_Arc(10,"g");
		add_Etat(9,true,"identificateur",arcs_Head);
		arcs_Head = null;
		
		add_Arc(2,"l-{e}");
		add_Arc(3,"ch");
		add_Arc(11,"e");
		add_Etat(10,true,"identificateur",arcs_Head);
		arcs_Head = null;
		
		add_Arc(1,"l-{r}");
		add_Arc(4,"ch");
		add_Arc(12,"r");
		add_Etat(11,true,"identificateur",arcs_Head);
		arcs_Head = null;
		
		add_Arc(2,"l");
		add_Arc(3,"ch");
		add_Etat(12,true,"mot_cle",arcs_Head);
		arcs_Head = null;
//----------------------------------------------------------------------
		add_Arc(2,"l-{r}");
		add_Arc(3,"ch");
		add_Arc(27,"r");
		add_Etat(26,true,"identificateur",arcs_Head);
		arcs_Head = null;
		
		add_Arc(1,"l-{o}");
		add_Arc(4,"ch");
		add_Arc(28,"o");
		add_Etat(27,true,"identificateur",arcs_Head);
		arcs_Head = null;
		
		add_Arc(2,"l-{g}");
		add_Arc(3,"ch");
		add_Arc(29,"g");
		add_Etat(28,true,"identificateur",arcs_Head);
		arcs_Head = null;
		
		add_Arc(1,"l-{r}");
		add_Arc(4,"ch");
		add_Arc(30,"r");
		add_Etat(29,true,"identificateur",arcs_Head);
		arcs_Head = null;
		
		add_Arc(2,"l-{a}");
		add_Arc(3,"ch");
		add_Arc(31,"a");
		add_Etat(30,true,"identificateur",arcs_Head);
		arcs_Head = null;
		
		add_Arc(1,"l-{m}");
		add_Arc(4,"ch");
		add_Arc(32,"m");
		add_Etat(31,true,"identificateur",arcs_Head);
		arcs_Head = null;
		
		add_Arc(2,"l");
		add_Arc(3,"ch");
		add_Etat(32,true,"mot_cle",arcs_Head);
		arcs_Head = null;
//----------------------------------------------------------------------
		add_Arc(2,"l-{n,l}");
		add_Arc(3,"ch");
		add_Arc(15,"n");
		add_Arc(83,"le");
		add_Etat(14,true,"identificateur",arcs_Head);
		arcs_Head = null;
		
		add_Arc(1,"l-{s}");
		add_Arc(4,"ch");
		add_Arc(84,"s");
		add_Etat(83,true,"identificateur",arcs_Head);
		arcs_Head = null;
		
		add_Arc(2,"l-{e}");
		add_Arc(3,"ch");
		add_Arc(85,"e");
		add_Etat(84,true,"identificateur",arcs_Head);
		arcs_Head = null;
		
		add_Arc(1,"l");
		add_Arc(4,"ch");
		add_Etat(85,true,"mot_cle",arcs_Head);
		arcs_Head = null;
		
		add_Arc(1,"l-{d}");
		add_Arc(4,"ch");
		add_Arc(16,"d");
		add_Etat(15,true,"identificateur",arcs_Head);
		arcs_Head = null;
		
		add_Arc(2,"l-{i}");
		add_Arc(3,"ch");
		add_Arc(86,"i");
		add_Etat(16,true,"mot_cle",arcs_Head);
		arcs_Head = null;
		
		add_Arc(1,"l-{f}");
		add_Arc(4,"ch");
		add_Arc(87,"f");
		add_Etat(86,true,"identificateur",arcs_Head);
		arcs_Head = null;
		
		add_Arc(2,"l");
		add_Arc(3,"ch");
		add_Etat(87,true,"mot_cle",arcs_Head);
		arcs_Head = null;
//----------------------------------------------------------------------		add_Arc(2,"l-{h}");
		add_Arc(2,"l-{h}");
		add_Arc(3,"ch");
		add_Arc(42,"h");
		add_Etat(41,true,"identificateur",arcs_Head);
		arcs_Head = null;
		
		add_Arc(1,"l-{e}");
		add_Arc(4,"ch");
		add_Arc(43,"e");
		add_Etat(42,true,"identificateur",arcs_Head);
		arcs_Head = null;
		
		add_Arc(2,"l-{n}");
		add_Arc(3,"ch");
		add_Arc(44,"n");
		add_Etat(43,true,"identificateur",arcs_Head);
		arcs_Head = null;
		
		add_Arc(1,"l");
		add_Arc(4,"ch");
		add_Etat(44,true,"mot_cle",arcs_Head);
		arcs_Head = null;
//----------------------------------------------------------------------
		add_Arc(2,"l-{a}");
		add_Arc(3,"ch");
		add_Arc(39,"a");
		add_Etat(38,true,"identificateur",arcs_Head);
		arcs_Head = null;
		
		add_Arc(1,"l-{r}");
		add_Arc(4,"ch");
		add_Arc(40,"r");
		add_Etat(39,true,"identificateur",arcs_Head);
		arcs_Head = null;
		
		add_Arc(2,"l");
		add_Arc(3,"ch");
		add_Etat(40,true,"mot_cle",arcs_Head);
		arcs_Head = null;
//----------------------------------------------------------------------
		add_Arc(2,"l-{o,h}");
		add_Arc(3,"ch");
		add_Arc(34,"o");
		add_Arc(62,"h");
		add_Etat(33,true,"identificateur",arcs_Head);
		arcs_Head = null;
		
		add_Arc(1,"l-{a}");
		add_Arc(4,"ch");
		add_Arc(63,"a");
		add_Etat(62,true,"identificateur",arcs_Head);
		arcs_Head = null;
		
		add_Arc(2,"l-{r}");
		add_Arc(3,"ch");
		add_Arc(34,"o");
		add_Arc(64,"r");
		add_Etat(63,true,"identificateur",arcs_Head);
		arcs_Head = null;

		add_Arc(1,"l");
		add_Arc(4,"ch");
		add_Etat(64,true,"mot_cle",arcs_Head);
		arcs_Head = null;
		
		add_Arc(1,"l-{n}");
		add_Arc(4,"ch");
		add_Arc(35,"n");
		add_Etat(34,true,"identificateur",arcs_Head);
		arcs_Head = null;
		
		add_Arc(2,"l-{s}");
		add_Arc(3,"ch");
		add_Arc(36,"s");
		add_Etat(35,true,"identificateur",arcs_Head);
		arcs_Head = null;
		
		add_Arc(1,"l-{t}");
		add_Arc(4,"ch");
		add_Arc(37,"t");
		add_Etat(36,true,"identificateur",arcs_Head);
		arcs_Head = null;
		
		add_Arc(2,"l");
		add_Arc(3,"ch");
		add_Etat(37,true,"mot_cle",arcs_Head);
		arcs_Head = null;
//----------------------------------------------------------------------
		add_Arc(2,"l-{e}");
		add_Arc(3,"ch");
		add_Arc(22,"e");
		add_Etat(21,true,"identificateur",arcs_Head);
		arcs_Head = null;
		
		add_Arc(1,"l-{g}");
		add_Arc(4,"ch");
		add_Arc(23,"g");
		add_Etat(22,true,"identificateur",arcs_Head);
		arcs_Head = null;
		
		add_Arc(2,"l-{i}");
		add_Arc(3,"ch");
		add_Arc(24,"i");
		add_Etat(23,true,"identificateur",arcs_Head);
		arcs_Head = null;
		
		add_Arc(1,"l-{n}");
		add_Arc(4,"ch");
		add_Arc(25,"n");
		add_Etat(24,true,"identificateur",arcs_Head);
		arcs_Head = null;
		
		add_Arc(2,"l");
		add_Arc(3,"ch");
		add_Etat(25,true,"mot_cle",arcs_Head);
		arcs_Head = null;
//----------------------------------------------------------------------
		add_Arc(2,"l-{e}");
		add_Arc(3,"ch");
		add_Arc(18,"e");
		add_Etat(17,true,"identificateur",arcs_Head);
		arcs_Head = null;
		
		add_Arc(1,"l-{e}");
		add_Arc(4,"ch");
		add_Arc(19,"e");
		add_Etat(18,true,"identificateur",arcs_Head);
		arcs_Head = null;
		
		add_Arc(2,"l-{l}");
		add_Arc(3,"ch");
		add_Arc(20,"le");
		add_Etat(19,true,"identificateur",arcs_Head);
		arcs_Head = null;
		
		add_Arc(1,"l");
		add_Arc(4,"ch");
		add_Etat(20,true,"mot_cle",arcs_Head);
		arcs_Head = null;
//----------------------------------------------------------------------
		add_Arc(53,"=");
		add_Etat(45,true,"caractere_special",arcs_Head);
		arcs_Head = null;
		
		add_Etat(53,true,"affectation",arcs_Head);
//----------------------------------------------------------------------
		add_Etat(46,true,"caractere_special",arcs_Head);
		arcs_Head = null;
//----------------------------------------------------------------------
		add_Arc(61,"=");
		add_Etat(47,true,"caractere_de_comparaison",arcs_Head);
		
		add_Etat(61,true,"caractere_de_comparaison",arcs_Head);
//----------------------------------------------------------------------
		add_Arc(55,">");
		add_Arc(56,"=");
		add_Etat(48,true,"caractere_de_comparaison",arcs_Head);
		arcs_Head = null;
		
		add_Etat(55,true,"caractere_de_comparaison",arcs_Head);
		
		add_Etat(56,true,"caractere_de_comparaison",arcs_Head);
//----------------------------------------------------------------------
		add_Etat(49,true,"caractere_special",arcs_Head);
//----------------------------------------------------------------------
		add_Etat(50,true,"caractere_d'operation",arcs_Head);
//----------------------------------------------------------------------		
		add_Etat(51,true,"caractere_d'operation",arcs_Head);
//----------------------------------------------------------------------		
		add_Etat(52,true,"caractere_d'operation",arcs_Head);
//----------------------------------------------------------------------		
		add_Etat(57,true,"parethese_ouvrante",arcs_Head);
//----------------------------------------------------------------------		
		add_Etat(58,true,"parenthese_fermante",arcs_Head);
//----------------------------------------------------------------------		
		add_Etat(59,true,"caractere_special",arcs_Head);
//----------------------------------------------------------------------		
		add_Etat(60,true,"caractere_special",arcs_Head);
//----------------------------------------------------------------------
		add_Arc(2,"l-{e}");
		add_Arc(3,"ch");
		add_Arc(66,"e");
		add_Etat(65,true,"identificateur",arcs_Head);
		arcs_Head = null;
		
		add_Arc(1,"l-{p,c}");
		add_Arc(4,"ch");
		add_Arc(67,"p");
		add_Arc(73,"c");
		add_Etat(66,true,"identificateur",arcs_Head);
		arcs_Head = null;
		
		add_Arc(2,"l-{o}");
		add_Arc(3,"ch");
		add_Arc(74,"o");
		add_Etat(73,true,"identificateur",arcs_Head);
		arcs_Head = null;
		
		add_Arc(1,"l-{r}");
		add_Arc(4,"ch");
		add_Arc(75,"r");
		add_Etat(74,true,"identificateur",arcs_Head);
		arcs_Head = null;
		
		add_Arc(2,"l-{d}");
		add_Arc(3,"ch");
		add_Arc(76,"d");
		add_Etat(75,true,"identificateur",arcs_Head);
		arcs_Head = null;
		
		add_Arc(1,"l");
		add_Arc(4,"ch");
		add_Etat(76,true,"mot_cle",arcs_Head);
		arcs_Head = null;

		add_Arc(2,"l-{e}");
		add_Arc(3,"ch");
		add_Arc(68,"e");
		add_Etat(67,true,"identificateur",arcs_Head);
		arcs_Head = null;
		
		add_Arc(1,"l-{t}");
		add_Arc(4,"ch");
		add_Arc(69,"t");
		add_Etat(68,true,"identificateur",arcs_Head);
		arcs_Head = null;
		
		add_Arc(2,"l-{e}");
		add_Arc(3,"ch");
		add_Arc(70,"e");
		add_Etat(69,true,"identificateur",arcs_Head);
		arcs_Head = null;
		
		add_Arc(1,"l-{r}");
		add_Arc(4,"ch");
		add_Arc(71,"r");
		add_Etat(70,true,"identificateur",arcs_Head);
		arcs_Head = null;
		
		add_Arc(2,"l");
		add_Arc(3,"ch");
		add_Etat(71,true,"mot_cle",arcs_Head);
		arcs_Head = null;
//----------------------------------------------------------------------
		add_Arc(2,"l-{t}");
		add_Arc(3,"ch");
		add_Arc(78,"t");
		add_Etat(77,true,"identificateur",arcs_Head);
		arcs_Head = null;
		
		add_Arc(1,"l-{r}");
		add_Arc(4,"ch");
		add_Arc(79,"r");
		add_Etat(78,true,"identificateur",arcs_Head);
		arcs_Head = null;
		
		add_Arc(2,"l-{i}");
		add_Arc(3,"ch");
		add_Arc(80,"i");
		add_Etat(79,true,"identificateur",arcs_Head);
		arcs_Head = null;
		
		add_Arc(1,"l-{n}");
		add_Arc(4,"ch");
		add_Arc(81,"n");
		add_Etat(80,true,"identificateur",arcs_Head);
		arcs_Head = null;
		
		add_Arc(2,"l-{g}");
		add_Arc(3,"ch");
		add_Arc(82,"g");
		add_Etat(81,true,"identificateur",arcs_Head);
		arcs_Head = null;
		
		add_Arc(1,"l");
		add_Arc(4,"ch");
		add_Etat(82,true,"mot_cle",arcs_Head);
		arcs_Head = null;
//----------------------------------------------------------------------
		add_Arc(2,"l-{u}");
		add_Arc(3,"ch");
		add_Arc(89,"u");
		add_Etat(88,true,"identificateur",arcs_Head);
		arcs_Head = null;
		
		add_Arc(1,"l-{s}");
		add_Arc(4,"ch");
		add_Arc(90,"s");
		add_Etat(89,true,"identificateur",arcs_Head);
		arcs_Head = null;
		
		add_Arc(2,"l-{q}");
		add_Arc(3,"ch");
		add_Arc(91,"q");
		add_Etat(90,true,"identificateur",arcs_Head);
		arcs_Head = null;
		
		add_Arc(1,"l-{u}");
		add_Arc(4,"ch");
		add_Arc(92,"u");
		add_Etat(91,true,"identificateur",arcs_Head);
		arcs_Head = null;
		
		add_Arc(2,"l");
		add_Arc(3,"ch");
		add_Arc(93,"'");
		add_Etat(92,true,"identificateur",arcs_Head);
		arcs_Head = null;

		add_Arc(1,"l-{a}");
		add_Arc(4,"ch");
		add_Arc(94,"a");
		add_Etat(93,true,"identificateur",arcs_Head);
		arcs_Head = null;

		add_Arc(2,"l");
		add_Arc(3,"ch");
		add_Etat(94,true,"mot_cle",arcs_Head);
		arcs_Head = null;
	}
	
	public static void find_etat(int etat_ID) {
		Etats current_etat = etats_Head;
		while(current_etat != null) {
			if(current_etat.etat_ID == etat_ID) {
				etat_next = current_etat;
				break;
			}
			else {
				current_etat = current_etat.next;
			}
		}
	}
	
	public static void find_destination(Arcs arcs_Head,String contenue){
		Arcs current_arc = arcs_Head;
		while(current_arc != null) {
			if(current_arc.contenue == contenue) {
				etat_nextID = current_arc.destination;
				break;
			}
			else {
				current_arc = current_arc.next;
			}
		}
	}
	
	public static void test() {
		//try(FileWriter fw = new FileWriter("/srv/mnt/Data/Cloud/MyCode/JAVA/compelation/lexical/jetons.txt")) { //For linux
		try(FileWriter fw = new FileWriter("E:\\My Folder\\Cloud\\MyCode\\JAVA\\compelation\\lexical\\jetons.txt")) { //For windows
			
		try {
			//FileReader fr = new FileReader("/srv/mnt/Data/Cloud/MyCode/JAVA/compelation/lexical/test.txt"); //For linux
			FileReader fr = new FileReader("E:\\My Folder\\Cloud\\MyCode\\JAVA\\compelation\\lexical\\test.txt"); //For windows
			Scanner sc = new Scanner(fr);
			String c = "";
			char t;
			Etats current_etat;
			while(c != null) {
				c = sc.next();
				current_etat = etats_Head;
					for(int i = 0; i < c.length(); i++){
						etat_next = null;
						etat_nextID = 0;
						t = c.charAt(i);
						if(t >= '0' && t <= '9') {
							find_destination(current_etat.arc,"ch");
						}
						else if(t == '+') {
							find_destination(current_etat.arc,"+");
						}
						else if(t == '*') {
							find_destination(current_etat.arc,"*");
						}
						else if(t == '-') {
							find_destination(current_etat.arc,"-");
						}
						else if(t == '(') {
							find_destination(current_etat.arc,"(");
						}
						else if(t == ')') {
							find_destination(current_etat.arc,")");
						}
						else if(t == ',') {
							find_destination(current_etat.arc,",");
						}
						else if(t == '.') {
							find_destination(current_etat.arc,".");
						}
						else if(t == '=') {
							find_destination(current_etat.arc,"=");
						}
						else if(t == '>') {
							find_destination(current_etat.arc,">");
						}
						else if(t == '<') {
							find_destination(current_etat.arc,"<");
						}
						else if(t == ';') {
							find_destination(current_etat.arc,";");
						}
						else if(t == ':') {
							find_destination(current_etat.arc,":");
						}
						else if(t == 'p') {
							find_destination(current_etat.arc,"p");
						}
						else if(t == 'e') {
							find_destination(current_etat.arc,"e");
						}
						else if(t == 'o') {
							find_destination(current_etat.arc,"o");
						}
						else if(t == 'g') {
							find_destination(current_etat.arc,"g");
						}
						else if(t == 'r') {
							find_destination(current_etat.arc,"r");
						}
						else if(t == 'a') {
							find_destination(current_etat.arc,"a");
						}
						else if(t == 'm') {
							find_destination(current_etat.arc,"m");
						}
						else if(t == 'i') {
							find_destination(current_etat.arc,"i");
						}
						else if(t == 'n') {
							find_destination(current_etat.arc,"n");				
						}
						else if(t == 'f') {
							find_destination(current_etat.arc,"f");
						}
						else if(t == 't') {
							find_destination(current_etat.arc,"t");
						}
						else if(t == 'd') {
							find_destination(current_etat.arc,"d");
						}
						else if(t == 'h') {
							find_destination(current_etat.arc,"h");
						}
						else if(t == 'v') {
							find_destination(current_etat.arc,"v");
						}
						else if(t == 'c') {
							find_destination(current_etat.arc,"c");
						}
						else if(t == 's') {
							find_destination(current_etat.arc,"s");
						}
						else if(t == 'b') {
							find_destination(current_etat.arc,"b");					
						}
						else if(t == 'l') {
							find_destination(current_etat.arc,"le");
						}
						else if(t == 'R') {
							find_destination(current_etat.arc,"R");
						}
						else if(t == 'J') {
							find_destination(current_etat.arc,"J");
						}
						else if(t == 'u') {
							find_destination(current_etat.arc,"u");
						}
						else if(t == 'q') {
							find_destination(current_etat.arc,"q");
						}
						else if(t == '\'') {
							find_destination(current_etat.arc,"'");
						}
						else if(t == 'E') {
							find_destination(current_etat.arc,"Expo");
						}
						

						if(((t >= 'a' && t <= 'z') || (t >= 'A' && t <= 'Z')) && etat_nextID == 0) {
							find_destination(current_etat.arc,"l");
						}
						if(((t >= 'a' && t <= 'z') || (t >= 'A' && t <= 'Z')) && t != 'p' && etat_nextID == 0) {
							find_destination(current_etat.arc,"l-{p}");
						}
						if(((t >= 'a' && t <= 'z') || (t >= 'A' && t <= 'Z')) && t != 'g' && etat_nextID == 0) {
							find_destination(current_etat.arc,"l-{g}");
						}
						if(((t >= 'a' && t <= 'z') || (t >= 'A' && t <= 'Z')) && t != 'r' && etat_nextID == 0) {
							find_destination(current_etat.arc,"l-{r}");
						}
						if(((t >= 'a' && t <= 'z') || (t >= 'A' && t <= 'Z')) && t != 'a' && etat_nextID == 0) {
							find_destination(current_etat.arc,"l-{a}");
						}
						if(((t >= 'a' && t <= 'z') || (t >= 'A' && t <= 'Z')) && t != 'm' && etat_nextID == 0) {
							find_destination(current_etat.arc,"l-{m}");
						}
						if(((t >= 'a' && t <= 'z') || (t >= 'A' && t <= 'Z')) && t != 'i' && etat_nextID == 0) {
							find_destination(current_etat.arc,"l-{i}");
						}
						if(((t >= 'a' && t <= 'z') || (t >= 'A' && t <= 'Z')) && t != 'e' && etat_nextID == 0) {
							find_destination(current_etat.arc,"l-{e}");
						}
						if(((t >= 'a' && t <= 'z') || (t >= 'A' && t <= 'Z')) && (t != 'n'||t != 'f') && etat_nextID == 0) {
							find_destination(current_etat.arc,"l-{n,f}");
						}
						if(((t >= 'a' && t <= 'z') || (t >= 'A' && t <= 'Z')) && (t != 'p' || t != 'b' || t != 'e' || t != 'i' || t != 'r' || t != 'c' || t != 'v' || t != 't' || t != 'R' || t != 'J' || t != 's') && etat_nextID == 0) {
							find_destination(current_etat.arc,"l-{p,b,e,i,r,c,v,t,R,s,J}");
						}
						if(((t >= 'a' && t <= 'z') || (t >= 'A' && t <= 'Z')) && t != 't' && etat_nextID == 0) {
							find_destination(current_etat.arc,"l-{t}");
						}
						if(((t >= 'a' && t <= 'z') || (t >= 'A' && t <= 'Z')) && t != 'd' && etat_nextID == 0) {
							find_destination(current_etat.arc,"l-{d}");
						}
						if(((t >= 'a' && t <= 'z') || (t >= 'A' && t <= 'Z')) && t != 'h' && etat_nextID == 0) {
							find_destination(current_etat.arc,"l-{h}");
						}
						if(((t >= 'a' && t <= 'z') || (t >= 'A' && t <= 'Z')) && t != 'v' && etat_nextID == 0) {
							find_destination(current_etat.arc,"l-{v}");
						}
						if(((t >= 'a' && t <= 'z') || (t >= 'A' && t <= 'Z')) && t != 'c' && etat_nextID == 0) {
							find_destination(current_etat.arc,"l-{c}");
						}
						if(((t >= 'a' && t <= 'z') || (t >= 'A' && t <= 'Z')) && t != 'o' && etat_nextID == 0) {
							find_destination(current_etat.arc,"l-{o}");
						}
						if(((t >= 'a' && t <= 'z') || (t >= 'A' && t <= 'Z')) && t != 'b' && etat_nextID == 0) {
							find_destination(current_etat.arc,"l-{b}");		
						}
						if(((t >= 'a' && t <= 'z') || (t >= 'A' && t <= 'Z')) && t != 'p' && etat_nextID == 0) {
							find_destination(current_etat.arc,"l-{p}");		
						}
						if(((t >= 'a' && t <= 'z') || (t >= 'A' && t <= 'Z')) && t != 'n' && etat_nextID == 0) {
							find_destination(current_etat.arc,"l-{n}");		
						}
						if(((t >= 'a' && t <= 'z') || (t >= 'A' && t <= 'Z')) && t != 'u' && etat_nextID == 0) {
							find_destination(current_etat.arc,"l-{u}");		
						}
						if(((t >= 'a' && t <= 'z') || (t >= 'A' && t <= 'Z')) && t != 'q' && etat_nextID == 0) {
							find_destination(current_etat.arc,"l-{q}");		
						}
						if(((t >= 'a' && t <= 'z') || (t >= 'A' && t <= 'Z')) && t != 'f' && etat_nextID == 0) {
							find_destination(current_etat.arc,"l-{f}");		
						}
						if(((t >= 'a' && t <= 'z') || (t >= 'A' && t <= 'Z')) && (t != 'n' || t != 'l') && etat_nextID == 0) {
							find_destination(current_etat.arc,"l-{n,l}");		
						}
						if(((t >= 'a' && t <= 'z') || (t >= 'A' && t <= 'Z')) && t != 'l' && etat_nextID == 0) {
							find_destination(current_etat.arc,"l-{l}");
						}
						find_etat(etat_nextID);
						if(etat_next == null){
							System.out.println(c+"n'appartiens pas");
						}
						else{
							current_etat = etat_next;
							System.out.println(etat_next.etat_ID);
						}
						if(t == '\n' && t == '\t' && t == ' '){
							if(current_etat.is_Final == true){
								fw.write(c+" "+current_etat.tag+"\n");
							}
							else {
								fw.write(c+" Error\n");
							}
						}
					}
				if(current_etat.is_Final == true){
					fw.write(c+" "+current_etat.tag+"\n");
				}
				else {
					fw.write(c+" Error\n");
				}
			}
			sc.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	public static void main(String[] args) {
		create_automate();
		test();
	}
}