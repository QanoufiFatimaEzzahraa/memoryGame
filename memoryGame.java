import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.applet.*;
import java.util.Random;

public class Memory extends JFrame{
	static JLabel guessLabel = new JLabel();
	static JLabel label0= new JLabel();
	static JLabel label1 = new JLabel();
	static JLabel label2 = new JLabel();
	static JLabel label3 = new JLabel();
	static JLabel label4 = new JLabel();
	static JLabel label5 = new JLabel();
	static JLabel label6 = new JLabel();
	static JLabel label7 = new JLabel();
	static JLabel label8 = new JLabel();
	static JLabel label9 = new JLabel();
	static JLabel label10 = new JLabel();
	static JLabel label11 = new JLabel();
	static JLabel[] boxLabel = new JLabel[12];
	
	static ImageIcon icon0 = new ImageIcon ("D:\\ours.png");
	static ImageIcon icon1 = new ImageIcon ("D:\\panda.png");
	static ImageIcon icon2 = new ImageIcon ("D:\\tigre.png");
	static ImageIcon icon3 = new ImageIcon ("D:\\singee.png");
	static ImageIcon icon4 = new ImageIcon ("D:\\ghzala.png");
	static ImageIcon icon5 = new ImageIcon ("D:\\baleine.png");
	static ImageIcon icon6 = new ImageIcon ("D:\\singee.png");
	static ImageIcon icon7 = new ImageIcon ("D:\\tigre.png");
	static ImageIcon backing = new ImageIcon ("D:\\inter.png");
	static ImageIcon[] choiceIcon = new ImageIcon [12];
	
	static JButton newButton = new JButton();
	static JButton exitButton = new JButton();
	
	static Random myRandom = new Random();
	
	static int choice;
	static int index;
	
	static int[] picked = new int[2];
	static int[] behind = new int[12];
	
	static int guesses;
	static int remaining;
	
	static AudioClip matchSound;

	static AudioClip noMatchSound;
	static Timer delayTimer;
	
	 
	public Memory() {
		//frame constructor
		setTitle("Memory Game");
		//getContentPane().setBackground(Color.DARK_GRAY);
		
		addWindowListener(new WindowAdapter() {
			public void WindowClosing(WindowEvent evt) {
				exitForm(evt);
			}
		});
		
		getContentPane().setLayout(new GridBagLayout());
		
		//position controls
		GridBagConstraints gridConstraints = new GridBagConstraints();
		guessLabel.setText("Guesses: 0");
		guessLabel.setForeground(Color.BLACK);
		guessLabel.setFont(new Font("Arial", Font.BOLD, 18));
		
		gridConstraints.gridx = 1;
		gridConstraints.gridy = 0;
		gridConstraints.gridwidth = 2;
		gridConstraints.insets = new Insets(10, 10, 10, 10);
		
		getContentPane().add(guessLabel, gridConstraints);
		
		boxLabel[0] = label0;
		boxLabel[1] = label1;
		boxLabel[2] = label2;
		boxLabel[3] = label3;
		boxLabel[4] = label4;
		boxLabel[5] = label5;
		boxLabel[6] = label6;
		boxLabel[7] = label7;
		boxLabel[8] = label8;
		boxLabel[9] = label9;
		boxLabel[10] = label10;
		boxLabel[11] = label11;
		
		int x=0;
		int y=1;
		
		for (int i=0; i<12; i++) {
		gridConstraints = new GridBagConstraints();
		boxLabel[i].setPreferredSize(new Dimension(70,70));
		boxLabel[i].setIcon(backing);
		gridConstraints.gridx = x;
		gridConstraints.gridy = y;
		gridConstraints.insets = new Insets(5,5,5,5);
		getContentPane().add(boxLabel[i], gridConstraints);
		
		boxLabel[i].addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				labelMouseClicked(e);
			}

		});
		
		x++;
		if(x>3) {
			x = 0;
			y += 1;
		} }
		
		newButton.setText("New Game");
		gridConstraints = new GridBagConstraints();
		gridConstraints.gridx = 1;
		gridConstraints.gridy = 6;
		gridConstraints.gridwidth = 2;
		gridConstraints.insets = new Insets(10, 10, 10, 10);
		getContentPane().add(newButton, gridConstraints);
		newButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newButtonActionPerformed(e);
			}	
		});
		
		exitButton.setText("Exit");
		gridConstraints = new GridBagConstraints();
		gridConstraints.gridx = 1;
		gridConstraints.gridy = 7;
		gridConstraints.gridwidth = 2;
		gridConstraints.insets = new Insets(10, 10, 10, 10);
		getContentPane().add(exitButton, gridConstraints);
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exitButtonActionPerformed(e);
			}
		});
		
		delayTimer = new Timer(1, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delayTimerActionPerformed(e);
			}	
		});
		
		pack();
		//setSize(10,10);
		//Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(0,0,500,500);
	
		
		choiceIcon[0]= icon0;
		choiceIcon[1]= icon1;
		choiceIcon[2]= icon2;
		choiceIcon[3]= icon3;
		choiceIcon[4]= icon4;
		choiceIcon[5]= icon5;
		choiceIcon[6]= icon6;
		choiceIcon[7]= icon4;
		choiceIcon[8]= icon2;
		choiceIcon[9]= icon1;
		choiceIcon[10]= icon5;
		choiceIcon[11]= icon0;
	
	}

	private void labelMouseClicked(MouseEvent e) {
		Component clickedComponent = e.getComponent();
		for(index = 0; index<11; index++) {
			if(clickedComponent == boxLabel[index]) {
				break;	
			}	
		}
		if((choice == 1 && index == picked[0]) || behind[index] == -1) {
			return;
		}
		
		//display selected pictures
		boxLabel[index].setIcon(choiceIcon[index]);
		if(choice == 0) {
			picked[0] = index;
			choice=1;
			return;
		}
		
		//use timer to process remaining code to allow
		delayTimer.start();
	}
	
	 
	private void newButtonActionPerformed(ActionEvent e){
		
		guesses = 0;
		remaining = 12;
		guessLabel.setText("Guesses: 0");
		behind = sortInteger(12);
		for (int i=0; i<12; i++) {
			boxLabel[i].setIcon(backing);
			if(behind[i] >11) {
				behind[i] = behind[i] - 12;
			}
		}
		
		choice = 0;
		newButton.setEnabled(false);
		exitButton.setText("Stop");	
		
		}
	
	
	
	private void exitButtonActionPerformed(ActionEvent e) {
	
		if(exitButton.getText().equals("Exit")) {
			exitForm(null);
		}else {
			exitButton.setText("Exit");
			newButton.setEnabled(true);
		}
	}
	
	
	
	private void exitForm(WindowEvent evt) {
		System.exit(0);
	}
	
	
	private void delayTimerActionPerformed(ActionEvent e) {
		
		//finish processing of display
		delayTimer.stop();
		guesses++;
		guessLabel.setText("Guesses: " + String.valueOf(guesses));
		picked[1] = index;
		if(behind[picked[0]] == behind[picked[1]]) {
			
			//if match play sound
			matchSound.play();
			behind[picked[0]] = -1;
			behind[picked[1]] = -1;
			remaining--;
		}else {
			
			noMatchSound.play();
		 	//delay 1 seconds
			long t = System.currentTimeMillis();
			do {} while (System.currentTimeMillis() -t <1000);
			boxLabel[picked[0]].setIcon(backing);
			boxLabel[picked[1]].setIcon(backing);
			
		}	
		choice = 0;
		if(remaining == 0) {
			exitButton.doClick();
			newButton.requestFocus();
		}
	}
	
	
	private static int[] sortInteger(int n) {
		
		//return n randomly sorted integers from 0 to n-1
		int nArray[] = new int[n];
		int temp, s;
		Random myRandom = new Random();
		
		//initialize array from 0 to n-1
		for(int i=0 ;i<n ; i++) {
			nArray[i] = i;
		}
		
		//i is number of items remaining in list
		for(int i = n; i>=1 ;i--) {
			s=myRandom.nextInt(i);
			temp = nArray[s];
			nArray[s] = nArray[i-1];
			nArray[i-1] = temp;
		}
		return(nArray);
	
		}    
	


	public static void main(String args[]){
		 new Memory().show();
		
		//sounds
		
		try {
			matchSound = Applet.newAudioClip(new URL("file:" + ("tada.waw")));
			matchSound = Applet.newAudioClip(new URL("file:" + ("uhoh.waw")));
		}catch(Exception ex){
			
		}
		//start first game
		newButton.doClick();	
	}
	
	
}
