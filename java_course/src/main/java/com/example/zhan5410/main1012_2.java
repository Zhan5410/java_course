package com.example.zhan5410;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class main1012_2 {
	int numofbut = 50; //地鼠格子的數量
	int nownum = 0;
	int score = 0;
	
	public void game() {
		JLabel jbl = new JLabel("score = 0");
		JFrame jfr = new JFrame();
		jfr.setSize(400,300);
		jfr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jfr.getContentPane().setLayout(new FlowLayout());
		
		for(int i = 0 ; i < numofbut; i++) {
			nownum = i;
			JButton jbt = new JButton("" + i);
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
			jfr.add(jbt);
		}
		jfr.add(jbl);
		jfr.setVisible(true);
	}
	public static void main(String[] args) {
		main1012_2 obj = new main1012_2();
		obj.game();
	}

}
