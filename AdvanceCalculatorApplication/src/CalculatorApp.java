import java.awt.*;
import java.util.*;
import java.awt.event.*;
import java.applet.*;
import java.math.*;
import java.io.*;
import java.lang.*;
import javax.swing.*;

/*
 <applet code = "CalculatorApp.class" width = 500 height = 400>
 </applet>
  */

public class CalculatorApp extends JApplet implements KeyListener, ActionListener{
	private FileClass f = new FileClass();
	//the FileClass is an inner class. It consists of method used to work with files.
	
	private ButtonGroup degree_cont; //to group the two checkbox options.
	private JTextArea textarea1;
	private JLabel label1, memoryTagLabel;
	
	private ButtonClass[] button; //declaring the variables of ButtonClass
	
	private JTextField textf; // Declaring the text field to the given input
	private String str = "0", str1 = "", str2 = "", op = "", str3 = ""; // declaring the 5 string variable
	private double m = 0, n = 0, mem = 0;
	private JPanel pa1, pa2, pa3, pa4, pa5, pa6, pa7, pa8; // Declaring the 8 variables
	
	//Declaring the label for the botton
	private String[] Strbutton = {"Power", "Advanced", "Shift", "C", "CE", "BackSpace", "7", "8", "9",
			"4", "5","6", "1", "2", "3", "0", ".", "Pie", "=", "Ans", "Sin", "Cose", "Sqrt", "Sqr", "X!",
			"Pow", "%", "1/X", "+/-", "StartStoring", "EndStoring", "/", "Del", "*", "Me", "-", "M+", "+",
			"M-", "MR", "R","PreView", "Close"};
	private boolean cond = false, cond1 = false, cond2 = false, cond3 = false, cond4 = false, cond5 = true, pressed = false; 
	
/*Init method is used to initialize the value*/
public void init() {
	Container contentPane= getContentPane();
	contentPane.setLayout(new BorderLayout(0, 7));//setting the layout as boarder layout
	//contentPane.setBackground(Color.yellow);
	label1 = new JLabel("CALCULATOR");
	memoryTagLabel = new JLabel(" ");
	memoryTagLabel.setEnabled(true);
	label1.setFont(new Font("Times Roman", Font.BOLD, 20));
	memoryTagLabel.setFont(new Font("Times Roman", Font.BOLD, 17));
	memoryTagLabel.setForeground(Color.black);
	label1.setForeground(Color.black);
	textarea1 = new JTextArea("CODED By\nSanjit", 4, 11);
	textarea1.setFont(new Font("Times Roamn", Font.BOLD, 12));
	textarea1.setEditable(false);
	
	boolean wrap = true;
	textarea1.setLineWrap(wrap);
	
	degree_cont = new ButtonGroup(); // the container of the checkboxes
	JRadioButton degrees = new JRadioButton("Deg", true);
	degrees.setActionCommand("Deg");
	//degrees.addItemLister(this);
	JRadioButton radians = new JRadioButton("Rad", false);
	radians.setActionCommand("Rad");
	//radians.addItemListner(this);
	degree_cont.add(degrees);
	degree_cont.add(radians);
	button = new ButtonClass[43];
	
	for(int i = 0; i < 20; i++)//creating the advanced buttons such as, sin and cos and arcsin
		button[i] = new ButtonClass(Strbutton[i], Color.black);
			//new Color(55, 168, 200)
	for(int i = 20; i < 43; i++)//creating the number
		button[i] = new ButtonClass(Strbutton[i], Color.black);
	textf = new JTextField(22);
	textf.setHorizontalAlignment(JTextField.RIGHT);
	textf.setFont(new Font("Times Roman", Font.BOLD, 12));
	textf.addKeyListener(this);
	textf.setText("0");
	
	//Construct panels in the applet
	pa1 = new JPanel();
	pa2 = new JPanel();
	pa3 = new JPanel();
	pa4 = new JPanel();
	pa5 = new JPanel();
	pa6 = new JPanel();
	pa7 = new JPanel();
	pa8 = new JPanel();
	pa5.setBounds(20, 1, 275, 30);
	pa5.add(button[0]);
	pa5.add(label1);
	contentPane.add(pa5);
	pa3.setLayout(new GridLayout(6, 3, 2, 3));
	pa3.setBounds(20, 120, 225, 156);
	
	for(int i = 3; i < 19; i++) 
		pa3.add(button[i]);
	contentPane.add(pa3);
	pa1.setLayout(new GridLayout(3, 3, 2, 2));
	pa1.setBounds(20, 276, 225, 78);
	
	for(int i = 20; i < 29; i++)
		pa1.add(button[i]);
	contentPane.add(pa1);
	pa2.setBounds(20, 32, 300, 90);
	pa2.add(textf);
	pa2.add(memoryTagLabel);
	pa2.add(pa4);
	pa4.setBounds(40, 50, 260, 80);
	pa4.add(button[1]);
	pa4.add(button[2]);
	pa4.add(degrees);
	pa4.add(radians);
	
	textf.setBackground(Color.black);
	contentPane.add(pa2);
	pa7.setBounds(320, 1, 150, 182);
	pa7.add(textarea1);
	pa7.add(button[41]);
	pa7.add(button[42]);
	contentPane.add(pa7);
	pa6.setLayout(new GridLayout(6, 2, 2, 2));
	pa6.setBounds(280, 196, 200, 156);
	pa8.setBounds(0, 0, 400, 40);
	
	for(int i = 29; i < 41; i++)
		pa6.add(button[i]);
	contentPane.add(pa6);
	contentPane.add(pa8);
	
	//ON OFF interface in the calculator
	for(int i = 1; i < 43; i++)
		button[i].setEnabled(false);
	textf.setEditable(false);
	
	//adding the action listener
	for(int i = 0; i < 43; i++)
		button[i].addActionListener(this);
	//degrees.addItemListener(this);
	//radians.addItemListener(this);
   } //end of init method

class ButtonClass extends JButton{
	public ButtonClass(String name, Color xyz) {
		this.setLabel(name);
		this.setForeground(xyz);
	}
  }

/*denotes a key press followed by a key released*/
public void keyTyped(KeyEvent e) {
	char ch;
	ch= e.getKeyChar();
	// to check the source lebel of the pressed key
	if((ch == '1') || (ch == '2') || (ch == '3') || (ch == '4') || (ch == '5') || (ch == '6') 
	    || (ch == '7') || (ch == '8') || (ch == '9') || (ch == '0')) {
		if(pressed) {
			str = "0";
			pressed = false;
		}
		if(str == "0") //check wheather string is empty or not
			str =""+ch;
			else
				str = str + ch;
		}//end if
		else if (ch == '.'&& cond5) {
			if(pressed) {
				str = "0";
				pressed = false;
			}
			str = str + ch;
			cond5 = false;
		}
		else if ((ch == '+') || (ch == '-') || (ch == '*') || (ch == '/')) {
			op = ""+ch;
			str1 = str;
			str = "0";
		}
		else if(ch == '=')
			calculate();
		textf.setText(str);
	}//end of the method declaration keyTyped()

/* indicates the key is pushed down*/
public void keyPressed(KeyEvent e) {
	double ac = e.getKeyCode();
	if((ac == 13) || (ac == 10))
        calculate();
        else if(ac == 8) {
        	if(str.compareTo("") == 0)
        	textf.setText("Error");
        	else {
        		str = str.substring(0, str.length() - 1);
        		textf.setText(str);
        	}
        }
}// end if method key pressed

/*indicates a key is released*/
public void keyReleased(KeyEvent e) {}

/*Action listener method for buttons on the calculator*/
public void actionPerformed(ActionEvent e) {
	if(e.getSource() == button[29]) {//for begin store in the file and StartStoring Button
		cond4 = true;
		button[30].setEnabled(true); //for button with label StartStoring "EndStoring" Button
		button[29].setEnabled(false);
		f.CreateFile(); //invokes the method for creating a new file.
	}
	
	else if(e.getSource() == button[30]) {//end of the storing
		cond4 = false;
		button[29].setEnabled(true);
		button[30].setEnabled(false);
		f.endStore(); // call method to stop storing the files
	}
	
	else if (e.getSource() == button[41]) {//"preview" button
		button[41].setEnabled(false);
		button[42].setEnabled(true);
		f.WriteFileOnTextArea();
	}
	
	else if(e.getSource() == button[42]) {// "close" button
		button[41].setEnabled(true);
		button[42].setEnabled(false);
		textarea1.setText("CODED By\nSanjit");
	}
	
	else if (e.getSource() == button[32]) {//"Del" button
		f.CreateFile();
	}
	
	else if(e.getSource() == button[1]) {//for advance button
		if(!cond) {
			cond = true;
			pa1.setVisible(true);
			button[1].setLabel("Simple");
			for(int i = 20; i < 29; i++)
				button[i].setEnabled(true);
		}
		
		else {
			button[1].setLabel("Advanced");
			pa1.setVisible(false);
			cond = false;
		}
	}
	
	else if(e.getSource() == button[20]) {
		pressed = true;
		double x = Double.valueOf(str).doubleValue();
		String str4 = degree_cont.getSelection().getActionCommand();
		if(!cond3) {
			if(str4.equals("Deg")) {
				while(x >= 360)
					x = x-360;
				str = ""+MathClass.sin_func(MathClass.convert_deg_rad(x));
				textf.setText(""+str);
			     }
			 else if(str4.equals("Rad")) {
				 while(x >= (2*Math.PI))
					 x = x - Math.PI;
				 str = ""+MathClass.sin_func(x);
				 textf.setText(str);
			 }
			  f.CreateFile();
			  f.addTofile("Sin("+x+")="+str);
		  }
			 
			 else {
				 if(str4.equals("Deg")) {
					 x = Double.valueOf(str).doubleValue();
					 str = ""+MathClass.convert_rad_deg(Math.asin(x));
					 textf.setText(""+str);
				 }
				 
				 else if(str4.equals("Rad")) {
					 x = Double.valueOf(str).doubleValue();
					 str = ""+MathClass.convert_deg_rad(Math.acos(x));
					 textf.setText(str);
				 }
				 
				 str3 = str;
				 f.CreateFile();
				 f.addTofile("aSin("+x+") = "+str);
			 }
			}
			
			else if(e.getSource() == button[21]) {
				pressed = true;
				double x = Double.valueOf(str).doubleValue();
				String str4 = degree_cont.getSelection().getActionCommand();
				if(!cond3) {
					if(str4.equals("Deg")) {
						while(x >= 360)
							x = x - 360;
						str = ""+MathClass.cos_func(MathClass.convert_deg_rad(x));
						textf.setText(str);
						str3 = str;
					}
					
					else if(str4.equals("Rad")){
						while(x >= (2 * Math.PI))
							x = x - Math.PI;
						str = ""+MathClass.cos_func(x);
						textf.setText(str);
						str3 = str;
					}
					
					f.CreateFile();
					f.addTofile("Cos("+x+") =" +str);
				}
				
				else {
					if(str4.equals("Deg")) {
						x = Double.valueOf(str).doubleValue();
						str = ""+MathClass.convert_rad_deg(Math.acos(x));
						textf.setText(str);
						str3 = str;
					}
					else if(str4.equals("Rad")) {
						x = Double.valueOf(str).doubleValue();
						str = ""+MathClass.convert_rad_deg(Math.acos(x));
						textf.setText(str);
						str3 = str;
					}
					f.CreateFile();
					f.addTofile("aCos("+x+") = "+str);
				}
				
			}
			else if(e.getSource() == button[0]) {//power button
				if(!cond1) {
					cond5 = true;
					textf.setBackground(Color.white);
					cond1 = true;
					button[0].setLabel("OFF");
					for(int i = 1; i < 42; i++)
					button[i].setEnabled(true);
					button[30].setEnabled(false);
				}
				else {
					cond1 = false;
					cond5 = true;
					pressed = false;
					textf.setBackground(Color.black);
					button[0].setLabel("ON");
					for(int i = 1; i < 43; i++)
					button[i].setEnabled(false);
					memoryTagLabel.setText(" ");
					f.delFile();
					str = "0"; str1 = ""; str2 = "";
					textf.setText(str);
					mem = 0;
				}
			}
			
			else if(e.getSource() == button[16]) {//"." button
				if(pressed) {
					str = "0";
					pressed = false;
				}
				if(cond5) {
					str = str+".";
					cond5 = false;
					textf.setText(str);
				}
			}
			
			//the implementation of the number button
			else if((e.getSource() == button[6]) || (e.getSource() == button[7]) || (e.getSource() == button[8]) ||
			(e.getSource() == button[9]) || (e.getSource() == button[10]) || (e.getSource() == button[11]) ||
			(e.getSource() == button[12]) || (e.getSource() == button[13]) || (e.getSource() == button[14]) ||
			(e.getSource() == button[15])){
				if(pressed) {
					str = "0";
					pressed = false;
				}
				if(str.compareTo("0") == 0 || op != "")
					str = e.getActionCommand();
				else str = str + e.getActionCommand();
				str3 = str;
				textf.setText(str);
				showStatus(e.getActionCommand());
			}
			else if((e.getSource() == button[37]) || (e.getSource() == button[35]) || (e.getSource() == button[33]) || 
					(e.getSource() == button[31]) ||(e.getSource() == button[25])) {//"+", "-", "/","*" buttons
				cond5 = true;
				String tempStr ="";
				op = e.getActionCommand();
				str1 = str;
				str3 = str;
				tempStr = str;
				str = "0";
				textf.setText(tempStr);
			}
			else if((e.getSource() == button[18])) {//"=" button
				calculate();
				//f.CreateFile();
			}
			
			else if(e.getSource() == button[5]) {//"BackSpace" button
				if(str.length() != 0) {
					str = str.substring(0, str.length() - 1);
					textf.setText(str);
				}
				
				else
					JOptionPane.showMessageDialog(null, "Do not use backspace now");
			}
			else if(e.getSource() == button[3]) {//"C" button
				op = "";
				str = str3;
				textf.setText(str);
			}
			else if(e.getSource() == button[4]) {//"CE" Button
				textf.setText("0");
				cond5 = true;
				str = "0";
				str1 = "";
				str2 = "";
			}
			else if(e.getSource() == button[27]) {//"1/x" button
				pressed = true;
				float g = 0, p = 0;
				p = Float.valueOf(str).floatValue();
				
				if(p == 0) {
					str = "0";
					textf.setText("Infinite");
				}
				else {
					g = (1/p);
					str = ""+g;
					str3 = str;
					textf.setText(str);
					f.CreateFile();
					f.addTofile("1/"+p+"="+str);
				}
			}
			
			else if(e.getSource() == button[28]) {//"+/-" button
				if(!(str.compareTo("0") == 0)) {
					if(str.charAt(0) == '-')
						str = str.substring(1, str.length());
					else
						str = "-"+str;
					textf.setText(str);
				}
			}
			else if(e.getSource() == button[34]) {//"ME" button
				pressed = true;
				memoryTagLabel.setText("M");
				mem = Double.valueOf(str).doubleValue();
				if(mem == 0)
					memoryTagLabel.setText("");
				showStatus("Memory = "+mem);
			}
			
			else if(e.getSource() == button[36]) {//"M+" button
				pressed = true;
				memoryTagLabel.setText("M");
				mem = mem+(Double.valueOf(str).doubleValue());
				if(mem == 0)
					memoryTagLabel.setText(" ");
				showStatus("Memory="+mem);
			}
			
			else if(e.getSource() == button[38]) {//"M-" button
				pressed = true;
				memoryTagLabel.setText("M");
				mem = mem - (Double.valueOf(str).doubleValue());
				if(mem == 0)
					memoryTagLabel.setText(" ");
				showStatus("Memory ="+mem);
		}
		else if(e.getSource() == button[39]) {//"MR" Button
			str = ""+mem;
			str3 = str;
			textf.setText(str3);
		}
		
		else if(e.getSource() == button[23]) {
			pressed = true;
			double y = 0;
			double x = Double.valueOf(str).doubleValue();
			y = MathClass.sqr_func(x);
			textf.setText(""+y);
			str = ""+y;
			str3 = str;
			f.CreateFile();
			f.addTofile("sqr("+x+")="+str);
		}
	
		else if(e.getSource() == button[24]) {
			pressed = true;
			int x = Integer.valueOf(str).intValue();
			if(x > 39)
				textf.setText("Infinite");
			else {
				str = ""+MathClass.fact_func(x);
				textf.setText(""+str);
				str3 = str;
				f.CreateFile();
				f.addTofile(""+x+"!="+str);
			}
		}
		else if(e.getSource() == button[2]) {//"Shift" Button
			if(!cond3) {
				button[20].setLabel("arcSin");
				button[21].setLabel("arcCos");
				cond3 = true;
			}
			else {
				cond3 = false;
				button[20].setLabel("Sin");
				button[21].setLabel("Cos");
			}
		}
		else if(e.getSource() == button[40]) {//"R" Button
			textf.setText("0");
			mem = 0;
			memoryTagLabel.setText("");
		}
		else if(e.getSource() == button[26]) {
			pressed = true;
			float p, r;
			p = Float.valueOf(str).floatValue();
			r = p / 100;
			str = ""+r;
			str3= str;
			textf.setText(str);
			f.CreateFile();
			f.addTofile(""+p+"%="+str);
		}
		else if(e.getSource() == button[22]) {
			pressed = true;
			double l = 0;
			m = Double.valueOf(str).doubleValue();
			l = Math.sqrt(m);
			str = ""+l;
			str3 = str;
			textf.setText(str);
			f.CreateFile();
			f.addTofile("sqrt("+m+") = "+str);
		}
		else if(e.getSource() == button[19]) {
			pressed = true;
			textf.setText(str3);
			str = str3; 
			showStatus(str3);
		}
		else if(e.getSource() == button[17]) {
			pressed = true;
			textf.setText(""+3.1415926);
			str = ""+3.1415926;
		}
		
	}//methods ends action performed
	
	public void calculate() {
		double l = 0;
		int o = 0;
		String opp = "";
		str2 = str;
		str = "0";
		m = Double.valueOf(str1).doubleValue();
		n = Double.valueOf(str2).doubleValue();
		opp = op;
		if(op.compareTo("+") == 0) {
			l = m + n;
			op = "";
		}
		
		else if(op.compareTo("-") == 0) {
			l = m - n;
			op = "";
		}
		
		else if(op.compareTo("*") == 0) {
			l = m * n;
			op = "";
		}
		
		else if(op.compareTo("/") == 0) {
			l = m / n;
			op = "";
		}
		
		else if(op.compareTo("") == 0) {
			l = Double.valueOf(str2).doubleValue();
		}
		
		else if(op.compareTo("Pow") == 0) {
			o = Integer.valueOf(str2).intValue();
			l = MathClass.pow_func(m, o);
			op = "";
		}
		
		cond5 = true;
		pressed = true; //specify the end user has pressed a symbol such as , +, -, /.
		str = ""+l;
		textf.setText(str);
		f.CreateFile();
		f.addTofile(str1 + opp + str2 + "=" +l);
	}//end of method declaration "calculate"
	
	class FileClass{
		DataOutputStream Of;
		
		public void WriteFileOnTextArea() {//Method to write in the file
			String str;
			char st;
			str = "";
			try {
				DataInputStream If = new DataInputStream(new FileInputStream("FileOut.txt"));
				while((If.readLine()) != null) {
					if((st = If.readChar()) != 'E') {
						str = str + str;
					}
					if(st == 'E') {
						textarea1.setText("");
						textarea1.append(str);
						textarea1.append("\n");
						str = "";
					}
				}//end while
				If.close();
			}//end try;
			
			catch(FileNotFoundException e ) {}//catched the error if the file is not found
			catch(IOException e) {}
		}//end of method WriteFIleOnTextArea
		
		public void CreateFile() {
			try {
				Of = new DataOutputStream(new FileOutputStream("FileOut.txt"));
				Of.writeChars("Begin\n");
			}
			catch(IOException e) {
				System.err.println("File not opened propperly\n" +e.toString());
				System.exit(1);
			}
		}
		
		public void endStore() {
			try {
				Of.close();
			}
			catch(IOException e) {
				System.err.println("file not found closed properly\n" +e.toString());
				System.exit(1);
			}
		}
		
		public void addTofile(String str) {
			try {
				for(int i = str.length() - 1; i >= 0; i--) {
					Of.writeChar(str.charAt(i));
					Of.writeChars("\n");
				}
				Of.writeChar('E');
				Of.writeChars("\n");
			}
			catch(IOException io) {}
		}
		
		public void delFile() {
			CreateFile();
			try {
				Of.close();
			}
			catch(IOException io) {}
		}
	}//end of the FileClass
	}//end of class CalculateClass
	
	class MathClass{
		public static double sin_func(double x) {
			if(x <= 0.0005)
				x = x + ((x * x * x) / 6);
			else 
				x = 2 * sin_func(x / 2) * cos_func(x / 2);
			return x;
		}
		
		public static double cos_func(double x) {
			if(x <= 0.0005)
				x = 1 - ((x * x * x) / 2);
			else
				x = (cos_func(x / 2) * cos_func(x / 2)) - (sin_func(x / 2) * sin_func(x / 2));
			return x;
		}//end of cos_func method
		
		/*converting from radian to degree*/
		public static double convert_rad_deg(double x) {
			double y;
			y = (180 * x) / Math.PI;
			return y;
		}
		
		/*converting from degree to radian*/
		public static double convert_deg_rad(double x) {
			double y;
			y = (Math.PI * x) / 180;
			return y;
		}
		
		public static double pow_func(double x, int y) {
			double s;
			s = 1; 
			for(int i = 1; i <= y; i++)
				s = s * x;
			return s;
		}
		
		public static double sqr_func(double x) {
			double s = x * x;
			return s;
		}
		
		/*factorial method*/
	   public static long fact_func(int x) {
			long t = 1;
			for(int i = x; i > 0; i--)
				t = i * t;
			return t;
	}
}//end of ClassMaths

 