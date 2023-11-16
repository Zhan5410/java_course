package com.example.zhan5410;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
public class main1116 {
	private JFrame jframe = null;

	private JTextField jtf_filepathway = null;
	private JTextArea jtf_information = null;
	private JTextArea jtf_filecontent = null;

	private JButton jbt = null;

	private JScrollPane jsp_filecontent = null;
	private JScrollPane jsp_information = null;

	private String[] objText = null;
    private String[] objfile = null;

    private Color[][] textstyle = null;

    private JCheckBox[] jcheckbox = null;

    private JCheckBox jcb_filelength = null;
    private JCheckBox jcb_isfile = null;
    private JCheckBox jcb_isdirectory = null;
    private JCheckBox jcb_canread = null;
    private JCheckBox jcb_canwrite = null;
    private JCheckBox jcb_ishidden = null;
    private JCheckBox jcb_lastmodify = null;
    private JCheckBox jcb_getname = null;
    private JCheckBox jcb_getpath = null;
    private JCheckBox jcb_getabsolutepath = null;

    private ButtonGroup buttongroup = null;

    private JRadioButton[] jradiobutton = null;
    private JRadioButton jrb_1 = null;
    private JRadioButton jrb_2 = null;

	private int[][] gbc = null;
	private JComponent[] jtf = null;
	private ArrayList<JComponent> GUIConponent = null;

	private ActionListener[] JButtonActionListener = null;
	private ActionListener openfile = null;
	private ActionListener roadfile = null;
	private ActionListener encode = null;
	private ActionListener decode = null;

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
		};

        objfile = new String[]{
            "檔案長度",
            "是否為檔案",
            "是否為目錄",
            "是否可讀",
            "是否可寫",
            "是否隱藏",
            "最後修改日期",
            "檔案名稱",
            "檔案路徑",
            "絕對路徑"
        };

        textstyle = new Color[][]{
            {Color.BLACK,Color.WHITE},
            {Color.YELLOW,Color.BLACK}
        };

        jcheckbox = new JCheckBox[]{
            jcb_filelength,
            jcb_isfile,
            jcb_isdirectory,
            jcb_canread,
            jcb_canwrite,
            jcb_ishidden,
            jcb_lastmodify,
            jcb_getname,
            jcb_getpath,
            jcb_getabsolutepath
        };
        dochkeckbox();

		gbc = new int[][] {{0,0,1,1,0,0,NONE,WEST}, //Label_filepath
						   {0,1,1,1,0,0,NONE,WEST}, //Label_information
						   {0,10,5,1,0,0,NONE,WEST}, //Label_filecontent
						   {1,0,4,1,1,0,BOTH,WEST}, //Text_filepathway
						   {1,1,4,4,1,1,BOTH,WEST}, //ScrollPane_imformation
						   {1,10,4,7,1,1,BOTH,WEST}, //ScrollPane_filecontent
						   {1,9,1,1,1,0,BOTH,CENTER}, //Button_openfile
						   {2,9,1,1,1,0,BOTH,CENTER}, //Button_roadfile
						   {3,9,1,1,1,0,BOTH,CENTER}, //Button_encode
						   {4,9,1,1,1,0,BOTH,CENTER}, //Button_decode
                           {1,5,1,1,1,0,BOTH,WEST}, //checkbox_filelength
                           {2,5,1,1,1,0,BOTH,WEST}, //checkbox_isfile
                           {3,5,1,1,1,0,BOTH,WEST}, //checkbox_isdirectory
                           {4,5,1,1,1,0,BOTH,WEST}, //checkbox_canread
                           {1,6,1,1,1,0,BOTH,WEST}, //checkbox_canwrite
                           {2,6,1,1,1,0,BOTH,WEST}, //checkbox_ishidden
                           {3,6,1,1,1,0,BOTH,WEST}, //checkbox_lastmodify
                           {4,6,1,1,1,0,BOTH,WEST}, //checkbox_getname
                           {1,7,1,1,1,0,BOTH,WEST}, //checkbox_getpath
                           {2,7,1,1,1,0,BOTH,WEST}, //checkbox_getabsolutepath
                           {3,7,1,1,1,0,BOTH,WEST}, //radiobutton_1
                           {4,7,1,1,1,0,BOTH,WEST}  //radiobitton_2
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

        jrb_1 = new JRadioButton("樣式1",true);
        jrb_2 = new JRadioButton("樣式2",true);
        buttongroup = new ButtonGroup();
        jradiobutton = new JRadioButton[]{jrb_1,jrb_2};

        doradio();
        for(int i=0 ; i<jradiobutton.length ; i++){
            jradiobutton[i].setActionCommand(String.valueOf(i));
            jradiobutton[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    if(jradiobutton[Integer.valueOf(e.getActionCommand())].isSelected()){
                        jtf_filecontent.setForeground(textstyle[Integer.valueOf(e.getActionCommand())][0]);
                        jtf_filecontent.setBackground(textstyle[Integer.valueOf(e.getActionCommand())][1]);
                        jtf_information.setForeground(textstyle[Integer.valueOf(e.getActionCommand())][0]);
                        jtf_information.setBackground(textstyle[Integer.valueOf(e.getActionCommand())][1]);
                    }
                }
            }); 
        }

	}

    private void doradio(){
        for(int i=0 ; i<jradiobutton.length ; i++){
            buttongroup.add(jradiobutton[i]);
        }
    }


    private void dochkeckbox(){
        for(int i=0 ; i<jcheckbox.length ; i++){
            jcheckbox[i] = new JCheckBox(objfile[i],true);
            jcheckbox[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    readfile();
                }
            });
        }

    }

    private void readfile(){
        File f = new File(jtf_filepathway.getText());
		StringBuffer sb = new StringBuffer();

		sb.append(f.getName() + "檔案資訊如下：\n");
		sb.append("=======================\n");
		if(jcheckbox[0].isSelected()){sb.append(objfile[0] + "：" + f.length() + "位元組\n");}
		if(jcheckbox[1].isSelected()){sb.append(objfile[1] + "：" + (f.isFile()?"是檔案":"不是檔案") + "\n");}
		if(jcheckbox[2].isSelected()){sb.append(objfile[1] + "：" + (f.isDirectory()?"是目錄":"不是目錄") + "\n");}
		if(jcheckbox[3].isSelected()){sb.append(objfile[1] + "：" + (f.canRead()?"可讀取":"不可讀取") + "\n");}
		if(jcheckbox[4].isSelected()){sb.append(objfile[1] + "：" + (f.canWrite()?"可寫入":"不可寫入") + "\n");}
		if(jcheckbox[5].isSelected()){sb.append(objfile[1] + "：" + (f.isHidden()?"是隱藏檔案":"不是隱藏檔案") + "\n");}
		if(jcheckbox[6].isSelected()){sb.append(objfile[1] + "：" + f.lastModified() + "\n");}
		if(jcheckbox[7].isSelected()){sb.append(objfile[1] + "：" + f.getName() + "\n");}
		if(jcheckbox[8].isSelected()){sb.append(objfile[1] + "：" + f.getPath() + "\n");}
		if(jcheckbox[9].isSelected()){sb.append(objfile[1] + "：" + f.getAbsolutePath() + "\n");}

        jtf_information.setText(sb.toString());
		jtf_filecontent.setText(roaddata(jtf_filepathway.getText()).toString());
    }

    private void dotext(){
	    jtf_filepathway = new JTextField("",24);
	    jtf_information = new JTextArea("");
	    jsp_information = new JScrollPane(jtf_information);
	    jtf_filecontent = new JTextArea("");
	    jsp_filecontent = new JScrollPane(jtf_filecontent);
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

        //handle checkbox
        for(int i=0 ; i<jcheckbox.length ; i++){
            GUIConponent.add(jcheckbox[i]);
        }

        for(int i=0 ; i<jradiobutton.length ; i++){
            GUIConponent.add(jradiobutton[i]);
        }

 		//add
		for(int i=0 ; i<GUIConponent.size() ; i++){
			addComponent(i);
		}
		
		jframe.setVisible(true);
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
				sb.append(objfile[0] + "：" + f.length() + "位元組\n");
				sb.append(objfile[1] + "：" + (f.isFile()?"是檔案":"不是檔案") + "\n");
				sb.append(objfile[1] + "：" + (f.isDirectory()?"是目錄":"不是目錄") + "\n");
				sb.append(objfile[1] + "：" + (f.canRead()?"可讀取":"不可讀取") + "\n");
				sb.append(objfile[1] + "：" + (f.canWrite()?"可寫入":"不可寫入") + "\n");
				sb.append(objfile[1] + "：" + (f.isHidden()?"是隱藏檔案":"不是隱藏檔案") + "\n");
				sb.append(objfile[1] + "：" + f.lastModified() + "\n");
				sb.append(objfile[1] + "：" + f.getName() + "\n");
				sb.append(objfile[1] + "：" + f.getPath() + "\n");
				sb.append(objfile[1] + "：" + f.getAbsolutePath() + "\n");

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

	public static void main(String[] args) {
		main1116 obj = new main1116();
		obj.set();
		obj.run();
	}
}

