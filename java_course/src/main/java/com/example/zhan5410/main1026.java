package com.example.zhan5410;

import java.io.File;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.text.JTextComponent;

public class main1026 {
	private JFrame jframe ;
	private JTextField jtf_filepathway;
	private JTextArea jtf_information;
	private JButton jbt;
	private String[] objText;
	private int[][] gbc;
	private JTextComponent[] jtf;
	private ActionListener[] JButtonActionListener;
	private ActionListener openfile;
	private ActionListener roadfile;
	private ActionListener encode;
	private ActionListener decode;
	private ArrayList<JComponent> GUIConponent;
	final int NONE = GridBagConstraints.NONE;
	final int WEST = GridBagConstraints.WEST;
	final int BOTH = GridBagConstraints.BOTH;
	final int CENTER = GridBagConstraints.CENTER;
	
	public void set() {								//set
		jframe = new JFrame();						//wake up
		objText = new String[] {"路徑",
								"檔案資訊",
								"狀態列",
								"開啟檔案",
								"讀取內容",
								"加密",
								"解密"
		}; //wake up and set up
		gbc = new int[][] {{0,0,1,1,0,0,NONE,WEST}, //Label_filepath
						   {0,1,1,1,0,0,NONE,WEST}, //Label_information
						   {0,7,5,1,0,0,NONE,WEST}, //Label_state
						   {1,0,4,1,1,0,BOTH,WEST}, //Text_filepathway
						   {1,1,4,5,1,1,BOTH,WEST}, //Text_imformation
						   {1,6,1,1,1,0,BOTH,CENTER}, //Button_openfile
						   {2,6,1,1,1,0,BOTH,CENTER}, //Button_roadfile
						   {3,6,1,1,1,0,BOTH,CENTER}, //Button_encode
						   {4,6,1,1,1,0,BOTH,CENTER} //Button_decode
		};
		GUIConponent = new ArrayList<JComponent>(); //wake up
		dotext();
		jtf = new JTextComponent[]{jtf_filepathway,jtf_information};
		doactionlistener();
		JButtonActionListener = new ActionListener[]{openfile,
													 roadfile,
													 encode,
													 decode
		};
	}
	
	private void run() {							//run
		jframe.setSize(800, 300);
		jframe.setLayout(new GridBagLayout());
		jframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		//handle lable
		for(int i=0 ; i<3 ; i++) {
			JLabel jbl = new JLabel(objText[i]);
			GUIConponent.add(jbl);					//add GUIComponent
		}

		//handle text
		for(int i=0 ; i<jtf.length ; i++){
			GUIConponent.add(jtf[i]);
		}

		//handle button
		for(int i=0 ; i<4 ; i++){
			jbt = new JButton(objText[i+3]);
			jbt.addActionListener(JButtonActionListener[i]);
			GUIConponent.add(jbt);
		}

 		//add
		for(int i=0 ; i<GUIConponent.size() ; i++){
			addComponent(i);
		}
		
		jframe.setVisible(true);
	}

	//set value and add jframe
	private void addComponent(int i) {			
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = gbc[i][0];
		c.gridy = gbc[i][1];
		c.gridwidth = gbc[i][2];
		c.gridheight = gbc[i][3];
		c.weightx = gbc[i][4];
		c.weighty = gbc[i][5];
		c.fill = gbc[i][6];
		c.anchor = gbc[i][7];
		jframe.add(GUIConponent.get(i),c);
	}
	
	private void dotext(){
		jtf_filepathway = new JTextField("",24);
		jtf_information = new JTextArea("");
	}

	private void doactionlistener(){
		openfile = new ActionListener() {
			public void actionPerformed(ActionEvent e){
				System.out.println("open file");
				JFileChooser jfc = new JFileChooser();
				int returnvalue = jfc.showOpenDialog(jframe);
				if(returnvalue == jfc.APPROVE_OPTION){
					File file = jfc.getSelectedFile();
					jtf_filepathway.setText(file.getAbsolutePath());
				}
				else{
					jtf_filepathway.setText("open command cancelled by user");
				}
			}
		};

		roadfile = new ActionListener() {
			public void actionPerformed(ActionEvent e){

			}
		};

		encode = new ActionListener() {
			public void actionPerformed(ActionEvent e){

			}
		};

		decode = new ActionListener() {
			public void actionPerformed(ActionEvent e){

			}
		};

	}

	public static void main(String[] args) {
		main1026 obj = new main1026();
		obj.set();
		obj.run();
	}
}
