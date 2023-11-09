package com.example.zhan5410;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class main1019 {

    int numofbut = 50; //地鼠格子的數量
	int nownum = 0;
	int score = 0;

    int yLayer = 0;
	
    public void gbl(){
        JFrame jfr = new JFrame();
        jfr.setSize(600, 300);
        jfr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jfr.setLayout(new GridBagLayout());

        JLabel jbl = new JLabel("Score = 0");
        jbl.setForeground(Color.YELLOW);
        jbl.setBackground(Color.BLACK);
        jbl.setOpaque(true);
        GridBagConstraints c2 = new GridBagConstraints();
        c2.gridx = 0;
        c2.gridy = 6;
        c2.gridwidth = 10;
        c2.gridheight = 1;
        c2.weightx = 1;
        c2.weighty = 0;
        c2.fill = GridBagConstraints.BOTH;
        c2.anchor = GridBagConstraints.WEST;

        for(int i=0;i<numofbut;i++){

            if(i%10 == 0){
                yLayer++;
            }

            JButton jbt = new JButton(""+i);
            GridBagConstraints c1 = new GridBagConstraints();
            c1.gridx = i%10;
            c1.gridy = yLayer;
            c1.gridwidth = 1;
            c1.gridwidth = 1;
            c1.weightx = 1;
            c1.weighty = 1;
            c1.fill = GridBagConstraints.BOTH;
            c1.anchor = GridBagConstraints.WEST;

            nownum = i;

            jbt.addActionListener(new ActionListener() {
                
				@Override
				public void actionPerformed(ActionEvent e) {
					if(jbt.getText().equals(String.valueOf(nownum))) {
						score++;
						jbl.setText("score = " + String.valueOf(score));
					}
				}

			});
			
			Thread thread = new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						while(true) {
							Thread.sleep(500);
							if(jbt.getText().equals(String.valueOf(nownum))) {
								jbt.setBackground(Color.yellow);
							}
							else {
								jbt.setBackground(Color.gray);
							}
						}
					}catch(InterruptedException e) {
						
					}
				}
				
			});
			thread.start();
			
			Thread thread2 = new Thread(new Runnable() {

				@Override
				public void run() {
					try {	
						while(true) {
							Thread.sleep(1000);
							nownum = new Random().nextInt(numofbut);
						}
					}catch(InterruptedException e) {
						
					}
				}
				
			});
			
			thread2.start();

            jfr.add(jbt,c1);
        }
        jfr.add(jbl,c2);

        jfr.setVisible(true);
    }

    public static void main(String[] args){
        main1019 obj = new main1019();
        obj.gbl();
    }
}
