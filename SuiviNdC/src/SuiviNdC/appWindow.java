package SuiviNdC;

import javax.swing.*;
import javax.swing.plaf.FileChooserUI;

import java.awt.event.*;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class appWindow extends JFrame{
	static File firstOpenedDir;
	JButton button1, button2, button3;
	ArrayList<String> inputFilesPaths= new ArrayList<>();
	String outputFilePath;
	
	
	public static void main (String [] args) {
		
		firstOpenedDir = new File("D:\\");
		firstOpenedDir.mkdir();
		
		new appWindow();
		
	}
	
	public appWindow(){
		this.setSize(400,400);
		
		this.setLocationRelativeTo(null);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setTitle("SNC");
		
		button1 = new JButton("Choose input files");
		button2 = new JButton("Choose output file");
		button3 = new JButton("Update Score");
		
		JPanel thePanel1 = new JPanel();
		
		thePanel1.add(button1);
		thePanel1.add(button2);
		thePanel1.add(button3);
		
		this.add(thePanel1);
		
		this.setVisible(true);
		
		ListenforInputButton lForButton1 = new ListenforInputButton();
		
		button1.addActionListener(lForButton1);
		
		ListenforOutputButton lForButton2 = new ListenforOutputButton();
		
		button2.addActionListener(lForButton2);
		
		ListenforUpdateButton lForButton3 = new ListenforUpdateButton();
		
		button3.addActionListener(lForButton3);
	}
	
	private class ListenforInputButton implements ActionListener{
		
		JFileChooser fileChooser = new JFileChooser(firstOpenedDir);
		
		@Override
		public void actionPerformed(ActionEvent e) {

			if(e.getSource() == button1){
				
				fileChooser.setMultiSelectionEnabled(true);
				fileChooser.showOpenDialog(appWindow.this);
				
				File[] files =fileChooser.getSelectedFiles();
				
				for (File file:fileChooser.getSelectedFiles()) {
					inputFilesPaths.add(file.getAbsolutePath());
				}
				
				
			}
			
		}
		
	}
	
	private class ListenforOutputButton implements ActionListener{
		
		JFileChooser fileChooser = new JFileChooser(firstOpenedDir);
		
		@Override
		public void actionPerformed(ActionEvent e) {

			if(e.getSource() == button2){
				

				fileChooser.showOpenDialog(appWindow.this);
				
				File file =fileChooser.getSelectedFile();
				
				outputFilePath= file.getAbsolutePath();
				
			}
			
		}
		
	}
	
	private class ListenforUpdateButton implements ActionListener{
		

		
		@Override
		public void actionPerformed(ActionEvent e) {

			if(e.getSource() == button3){
				
				for (String s: inputFilesPaths) {
				CSVReader reader = new CSVReader(s);
				try {
					UpdateSuiviNDC.writeFreshData(outputFilePath, reader);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
				System.out.println("Update finished");
			}
		}
		
	}
}
