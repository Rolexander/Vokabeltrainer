package com.vok.yes;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class main {
	//import and declare stuff
	private JFrame frame;
	private JPasswordField passwordField;
	private JButton btnaddVok_menu,btnLogin,btnListVok_listVok,btnAdd_vokAdd,btnBack_vokAdd,btncheckKnowledge_menu,btnBack_checkVok;
	private JPanel login_panel, menu_panel, addVok_panel, listVok_panel;
	private String User1 = new String("Alex");
	private String User2 = new String("Frieder");
	String vDE, vEN;
	String line = null;
	
	private static char Password1[] = {'1','2','3','4','5','6','7','8','9'};
	private static char Password2[] = {'9','8','7','6','5','4','3','2','1'};
	
	private JLabel lblText1, lblNewLabel_1, lblNewLabel_2, lblFin, lbl1, lblFolgendeVokabelnSind,lblVOK_checkVok;
	private JTextField textField, textField_1, user, textFieldInput_checkVok;
	Vokabel v = new Vokabel(null, null);
	
	private JScrollBar scrollBar;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					main window = new main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	
	public main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//frame
		frame = new JFrame("VTbOS");
		frame.getContentPane().setBackground(UIManager.getColor("Button.select"));
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		//check vocabulary panel
		JPanel checkVok_panel = new JPanel();
		checkVok_panel.setBackground(UIManager.getColor("Button.select"));
		checkVok_panel.setBounds(0, 0, 450, 275);
		checkVok_panel.setVisible(false);
		frame.getContentPane().add(checkVok_panel);
		checkVok_panel.setLayout(null);
		
		//Start Button -> geknüpft an popupMenu
		JButton btnStart_checkVok = new JButton("Start");
		btnStart_checkVok.setBounds(6, 181, 94, 29);
		checkVok_panel.add(btnStart_checkVok);
		
		//popupMenu Item Random
		JMenuItem random = new JMenuItem("Random");
		random.setMnemonic(KeyEvent.VK_R);
		random.getAccessibleContext().setAccessibleDescription("Random vocabulary query.");
		random.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	randomAbfrage();
                JOptionPane.showMessageDialog(frame, "'Random' clicked!");
            }
        });
		//popupMenu Item Skill
		JMenuItem skill = new JMenuItem("From German to English");
		skill.setMnemonic(KeyEvent.VK_R);
		skill.getAccessibleContext().setAccessibleDescription("Vocabulary query From German to English.");
		skill.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	skillAbfrageDE_EN();
                JOptionPane.showMessageDialog(frame, "'DE-EN' clicked!");
            }
        });
		//popupMenu Item csv Datei Reihenfolge
		JMenuItem csvDR = new JMenuItem("From Enlish to German");
		csvDR.setMnemonic(KeyEvent.VK_R);
		csvDR.getAccessibleContext().setAccessibleDescription("Vocabulary query From Enlish to German.");
		csvDR.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	skillAbfrageEN_DE();
                JOptionPane.showMessageDialog(frame, "'EN-DE' clicked!");
            }
        });
		
		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(btnStart_checkVok, popupMenu);
		popupMenu.add(random);
		popupMenu.add(skill);
		popupMenu.add(csvDR);
		
		btnStart_checkVok.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
				popupMenu.show(e.getComponent(), e.getX(), e.getY());
			}
		});
		
		JLabel lblCheckYourSkills = new JLabel("Check your skills");
		lblCheckYourSkills.setBounds(6, 6, 438, 33);
		lblCheckYourSkills.setHorizontalAlignment(SwingConstants.CENTER);
		lblCheckYourSkills.setForeground(Color.WHITE);
		lblCheckYourSkills.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 26));
		checkVok_panel.add(lblCheckYourSkills);
		
		lblVOK_checkVok = new JLabel("Voc");
		lblVOK_checkVok.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblVOK_checkVok.setHorizontalAlignment(SwingConstants.CENTER);
		lblVOK_checkVok.setBounds(6, 51, 438, 104);
		checkVok_panel.add(lblVOK_checkVok);
		
		btnBack_checkVok = new JButton("Back");
		btnBack_checkVok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblVOK_checkVok.setText("Voc");
				checkVok_panel.setVisible(false);
				menu_panel.setVisible(true);
			}
		});
		btnBack_checkVok.setBounds(6, 240, 94, 29);
		checkVok_panel.add(btnBack_checkVok);
		
		textFieldInput_checkVok = new JTextField();
		textFieldInput_checkVok.setBounds(160, 181, 130, 26);
		checkVok_panel.add(textFieldInput_checkVok);
		textFieldInput_checkVok.setColumns(10);
		
		//login panel -> first thing to see when launching the app
		login_panel = new JPanel();
		login_panel.setBackground(UIManager.getColor("EditorPane.selectionBackground"));
		login_panel.setBounds(0, 0, 450, 275);
		frame.getContentPane().add(login_panel);
		login_panel.setLayout(null);
		
		//password field on login panel
		passwordField = new JPasswordField();
		passwordField.setBounds(125, 160, 195, 26);
		passwordField.setToolTipText("Password must contain at least 8 characters");
		passwordField.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent event) {
		        JPasswordField field = (JPasswordField) event.getSource();
		        char[] password = field.getPassword();
		 
		        if (password.length < 8) {
		            JOptionPane.showMessageDialog(frame, "Password must contain at least 8 charachters.");
		        }
		    }
		});
		passwordField.addKeyListener(new KeyAdapter() {
		    @Override
		    public void keyReleased(KeyEvent event) {
		        JPasswordField field = (JPasswordField) event.getSource();
		        char[] password = field.getPassword();
		 
		        if (password == null || password.length < 8) {
		        	btnLogin.setEnabled(false);
		        } else {
		        	btnLogin.setEnabled(true);
		        }
		    }
		});
		login_panel.add(passwordField);
		
		//textfield for the user to input the username
		user = new JTextField();
		user.setBounds(125, 107, 195, 26);
		login_panel.add(user);
		user.setColumns(10);
		
		//label with instructions, title
		JLabel lblNewLabel = new JLabel("Please login to use the app");
		lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, 26));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(6, 16, 438, 26);
		login_panel.add(lblNewLabel);
		
		//login button
		btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					pwdCheck();
			}
		});
		btnLogin.setBounds(120, 198, 125, 26);
		login_panel.add(btnLogin);
		
		//label with instructions
		lblText1 = new JLabel("Type in your username and password. ");
		lblText1.setBounds(82, 54, 252, 16);
		login_panel.add(lblText1);
		
		//label indicating the user field
		lblNewLabel_1 = new JLabel("User");
		lblNewLabel_1.setBounds(46, 112, 35, 16);
		login_panel.add(lblNewLabel_1);
		
		//label indicating the password field
		lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setBounds(46, 165, 71, 16);
		login_panel.add(lblNewLabel_2);
		
		//label for testing purpose
		lblFin = new JLabel("");
		lblFin.setBounds(257, 202, 174, 16);
		login_panel.add(lblFin);
		
		//add vocabulary panel
		addVok_panel = new JPanel();
		addVok_panel.setBackground(UIManager.getColor("Button.select"));
		addVok_panel.setBounds(0, 0, 450, 275);
		addVok_panel.setVisible(false);
		
		//menu panel
		menu_panel = new JPanel();
		menu_panel.setBackground(UIManager.getColor("Button.select"));
		menu_panel.setBounds(6, 6, 440, 260);
		menu_panel.setVisible(false);
		frame.getContentPane().add(menu_panel);
		
		//button on menu panel -> go to vokList panel
		btnListVok_listVok = new JButton("List vocabulary");
		btnListVok_listVok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menu_panel.setVisible(false);
				listVok_panel.setVisible(true);
				action();
			}
		});
		
		//button on menu panel -> go to addVok panel
		btnaddVok_menu = new JButton("Add vocabulary");
		btnaddVok_menu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menu_panel.setVisible(false);
				addVok_panel.setVisible(true);
			}
		});
		
		//label in menu panel with instructions, title
		JLabel title = new JLabel("Choose an action to perform");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 26));
		title.setForeground(UIManager.getColor("Button.highlight"));
		title.setBounds(66, 6, 339, 33);
		menu_panel.add(title);
		btnaddVok_menu.setBounds(6, 63, 134, 29);
		menu_panel.add(btnaddVok_menu);
		
		btncheckKnowledge_menu = new JButton("Check your knowledge");
		btncheckKnowledge_menu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menu_panel.setVisible(false);
				checkVok_panel.setVisible(true);
			}
		});
		menu_panel.add(btncheckKnowledge_menu);
		btnListVok_listVok.setBounds(6, 99, 134, 29);
		menu_panel.add(btnListVok_listVok);
		
		//panel for listing vocaublary
		listVok_panel = new JPanel();
		listVok_panel.setBackground(UIManager.getColor("Button.select"));
		listVok_panel.setBounds(0, 0, 450, 275);
		listVok_panel.setVisible(false);
		frame.getContentPane().add(listVok_panel);
		listVok_panel.setLayout(null);
		
		//label on listVok panel. Title of the panel.
		lblFolgendeVokabelnSind = new JLabel("The following vocabulary is saved");
		lblFolgendeVokabelnSind.setBackground(UIManager.getColor("Button.select"));
		lblFolgendeVokabelnSind.setBounds(42, 5, 366, 30);
		listVok_panel.add(lblFolgendeVokabelnSind);
		lblFolgendeVokabelnSind.setHorizontalAlignment(SwingConstants.CENTER);
		lblFolgendeVokabelnSind.setForeground(Color.WHITE);
		lblFolgendeVokabelnSind.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 24));
		
		//back to menu button on listVok panel
		JButton btnback_vokList = new JButton("Back to menu");
		btnback_vokList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.setRowCount(0);
				listVok_panel.setVisible(false);
				menu_panel.setVisible(true);
			}
		});
		btnback_vokList.setBounds(6, 240, 117, 29);
		listVok_panel.add(btnback_vokList);
		
		//scrollPane on vokList panel -> for scrollable JTable
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 33, 438, 195);
		listVok_panel.add(scrollPane);
		
		//JTable
		table = new JTable();
		scrollPane.setViewportView(table);
		
		//button deleteVok on vokList panel
		JButton btndel_vokList = new JButton("Delete vocabulary");
		btndel_vokList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delVok();
			}
		});
		btndel_vokList.setBounds(128, 240, 141, 29);
		listVok_panel.add(btndel_vokList);
		
		JButton btn_save_listVok = new JButton("Save");
		btn_save_listVok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveVokList();
			}
		});
		btn_save_listVok.setBounds(272, 240, 81, 29);
		listVok_panel.add(btn_save_listVok);
		frame.getContentPane().add(addVok_panel);
		addVok_panel.setLayout(null);
		
		//label on addVok panel, title
		lbl1 = new JLabel("Add vocabulary");
		lbl1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl1.setForeground(Color.WHITE);
		lbl1.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 24));
		lbl1.setBounds(6, 6, 438, 30);
		addVok_panel.add(lbl1);
		
		//textfield for German vok
		textField = new JTextField();
		textField.setText("vDE");
		textField.setColumns(10);
		textField.setBounds(46, 48, 130, 26);
		addVok_panel.add(textField);
		
		//textfield for English vok
		textField_1 = new JTextField();
		textField_1.setText("vEN");
		textField_1.setColumns(10);
		textField_1.setBounds(271, 48, 130, 26);
		addVok_panel.add(textField_1);
		
		//Add button
		btnAdd_vokAdd = new JButton("Add");
		btnAdd_vokAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vDE = textField.getText();
				vEN = textField_1.getText();
				try {
						v.add(vDE, vEN);
						JOptionPane.showMessageDialog(frame, "Das Vokabelpaar wurde hinzugefügt");
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(frame, "Something went wrong.");
				}
			}
		});
		btnAdd_vokAdd.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		btnAdd_vokAdd.setBackground(UIManager.getColor("Button.select"));
		btnAdd_vokAdd.setBounds(185, 88, 75, 29);
		addVok_panel.add(btnAdd_vokAdd);
		
		//back to menu button on addVok panel
		btnBack_vokAdd = new JButton("Back to menu");
		btnBack_vokAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addVok_panel.setVisible(false);
				menu_panel.setVisible(true);
			}
		});
		btnBack_vokAdd.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		btnBack_vokAdd.setBounds(46, 210, 129, 29);
		addVok_panel.add(btnBack_vokAdd);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
	}
	
	//check password
	public void pwdCheck() {
		String urs = user.getText();
		char pas[] = passwordField.getPassword();
		boolean isCorrect = false;
		
		lblFin.setText(""+pas);
		
		if (pas.length != Password1.length && User1 != urs) {
		    isCorrect = false;
		} else {
		    isCorrect = Arrays.equals (pas, Password1);
		}
		
		if (pas.length != Password2.length && User2 != urs) {
		    isCorrect = false;
		} else {
		    isCorrect = Arrays.equals (pas, Password2);
		}
		if(isCorrect==true) {
			Arrays.fill(Password1, '0');
			Arrays.fill(Password2, '0');
			login_panel.setVisible(false);
			menu_panel.setVisible(true);
		}
		
	}
	
	//data from csv to JTable
	public void action() {
		File file = new File(v.path);
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(file));
			String firstline = br.readLine().trim();
			String[] columnsName = firstline.split(",");
			DefaultTableModel model = (DefaultTableModel)table.getModel();
			model.setColumnIdentifiers(columnsName);

			Object[] tableLines = br.lines().toArray();
			
			for(int i = 0; i < tableLines.length; i++) {
				String line = tableLines[i].toString().trim();
				String[] dataRow = line.split(",");
				model.addRow(dataRow);
			}
			
		} catch (Exception e) {
			System.out.println("Something went wrong.");
		}
	}
	
	public void delVok() {
		File oldFile = new File("VokList.csv");
        File newFile = new File("temp.csv");
        
        //check the selected row first
        if(table.getSelectedRow() != -1){
        // remove the selected row from the table model
        ((DefaultTableModel) table.getModel()).removeRow(table.getSelectedRow());
        JOptionPane.showMessageDialog(null, "Deleted successfully");
        }
        saveVokList();
        
	}
	
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
	
	public void saveVokList(){
		File oldFile = new File("VokList.csv");
        File newFile = new File("temp.csv");
        
		try {
			TableModel model = table.getModel();
	        FileWriter csv = new FileWriter(new File("temp.csv"));

	        for (int i = 0; i < model.getColumnCount(); i++) {
	            csv.write(model.getColumnName(i) + ",");
	        }

	        csv.write("\n");

	        for (int i = 0; i < model.getRowCount(); i++) {
	            for (int j = 0; j < model.getColumnCount(); j++) {
	                csv.write(model.getValueAt(i, j).toString() + ",");
	            }
	            csv.write("\n");
	            oldFile.delete();
	            File dump = new File(v.path);
	            newFile.renameTo(dump);
	        }

	        csv.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

    }
	
	/*
	 * write function…
	 * 
	 * public class CSVExporterUtils{
    public static void exportToCSV( JTable tableToExport, String pathToExportTo ){
        // Pseudocode below
        Open file and test if valid path

        Loop through each table line
            Loop through each column
                Get value at line, column
                    Add value to StringBuilder plus a comma
            Write contents of StringBuilder to file
            And add return using System.getProperty("line.separator")
        End Loop

        Close file
    }
}
	 */
	
	public void randomAbfrage() {
		
    }
	public void skillAbfrageDE_EN() {
		
	}
	public void skillAbfrageEN_DE() {
		
	}
}