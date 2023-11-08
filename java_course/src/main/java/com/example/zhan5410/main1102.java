package com.example.zhan5410;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
public class main1102 {
	private JFrame jframe ;
	private JTextField jtf_filepathway;
	private JTextArea jtf_information;
	private JTextArea jtf_filecontent;
	private JButton jbt;

	private JScrollPane jsp_filecontent;
	private JScrollPane jsp_information;

	private String[] objText;
	private int[][] gbc;
	private JComponent[] jtf;
	private ArrayList<JComponent> GUIConponent;

	private ActionListener[] JButtonActionListener;
	private ActionListener openfile;
	private ActionListener roadfile;
	private ActionListener encode;
	private ActionListener decode;

	final int NONE = GridBagConstraints.NONE;
	final int WEST = GridBagConstraints.WEST;
	final int BOTH = GridBagConstraints.BOTH;
	final int CENTER = GridBagConstraints.CENTER;

	//************** system parameters *****************

	String systemname = "檔案資訊系統";

	//**************************************************
	
	public void set() {								//set
		jframe = new JFrame(systemname);						//wake up
		objText = new String[] {"路徑",
								"檔案資訊",
								"檔案內容",
								"開啟檔案",
								"讀取內容",
								"加密",
								"解密"
		}; //wake up and set up
		gbc = new int[][] {{0,0,1,1,0,0,NONE,WEST}, //Label_filepath
						   {0,1,1,1,0,0,NONE,WEST}, //Label_information
						   {0,7,5,1,0,0,NONE,WEST}, //Label_filecontent
						   {1,0,4,1,1,0,BOTH,WEST}, //Text_filepathway
						   {1,1,4,5,1,1,BOTH,WEST}, //ScrollPane_imformation
						   {1,7,4,7,1,1,BOTH,WEST}, //ScrollPane_filecontent
						   {1,6,1,1,1,0,BOTH,CENTER}, //Button_openfile
						   {2,6,1,1,1,0,BOTH,CENTER}, //Button_roadfile
						   {3,6,1,1,1,0,BOTH,CENTER}, //Button_encode
						   {4,6,1,1,1,0,BOTH,CENTER} //Button_decode
		};
		GUIConponent = new ArrayList<JComponent>(); //wake up
		dotext();
		jtf = new JComponent[]{jtf_filepathway,jsp_information,jsp_filecontent};
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
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
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
		jsp_information = new JScrollPane(jtf_information);
		jtf_filecontent = new JTextArea("");
		jsp_filecontent = new JScrollPane(jtf_filecontent);
	}

	private void doactionlistener(){
		openfile = new ActionListener() {
			public void actionPerformed(ActionEvent e){
				System.out.println("open file");
				JFileChooser jfc = new JFileChooser();
				int returnvalue = jfc.showOpenDialog(jframe);
				if(returnvalue == JFileChooser.APPROVE_OPTION){
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
				System.out.println("roadfile");
				File f = new File(jtf_filepathway.getText());
				StringBuffer sb = new StringBuffer();

				sb.append(f.getName() + "檔案資訊如下：\n");
				sb.append("=======================\n");
				sb.append("檔案長度：" + f.length() + "位元組\n");
				sb.append("檔案或者目錄：" + (f.isFile()?"是檔案":"不是檔案") + "\n");
				sb.append("檔案或者目錄：" + (f.isDirectory()?"是目錄":"不是目錄") + "\n");
				sb.append("是否可讀：" + (f.canRead()?"可讀取":"不可讀取") + "\n");
				sb.append("是否可寫：" + (f.canWrite()?"可寫入":"不可寫入") + "\n");
				sb.append("是否隱藏：" + (f.isHidden()?"是隱藏檔案":"不是隱藏檔案") + "\n");
				sb.append("最後修改日期：" + f.lastModified() + "\n");
				sb.append("檔名稱：" + f.getName() + "\n");
				sb.append("檔案路徑：" + f.getPath() + "\n");
				sb.append("絕對路徑：" + f.getAbsolutePath() + "\n");

				jtf_information.setText(sb.toString());
				jtf_filecontent.setText(roaddata(jtf_filepathway.getText()).toString());
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

	private StringBuffer roaddata(String pathway){
		StringBuffer sb = new StringBuffer();
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(pathway)));
			String text = "";

			while ((text = br.readLine())!=null) {
				sb.append(text + "\n");
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb;
	}

	public static void main(String[] args) {
		main1102 obj = new main1102();
		obj.set();
		obj.run();
	}
}

