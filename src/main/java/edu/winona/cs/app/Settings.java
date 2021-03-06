package edu.winona.cs.app;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;

import edu.winona.cs.component.GameSettings;
import edu.winona.cs.db.DatabaseManager;
import edu.winona.cs.db.SettingsTable;
import edu.winona.cs.gamelogic.DifficultyLevel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Settings Menu - Had to update name due to conflict with GameSettings(Component)
 * Let's user set App-Setting: background color
 * Let's user set App-Setting: high score tracking
 * Let's user set User-Setting: preferred difficulty level
 * 
 * This GUI is only accessible to users that have logged in or been verified.
 * 
 * @author Kyle Aure
 * @version 0.0
 */
public class Settings extends JFrame implements ChangeListener {
	//Serialized Variable
	private static final long serialVersionUID = 598611529258548986L;
	
	//GameSettings variables
	private Color color = App.DEFAULT_SETTINGS.getBackgroundColor();
	private boolean isHighScoreTracking = App.DEFAULT_SETTINGS.isHighScoreTracking();
	private DifficultyLevel level = App.DEFAULT_SETTINGS.getDifficulty();
	
	//Database Variables
	private DatabaseManager dbm = DatabaseManager.getDatabaseManager();
	private SettingsTable st = dbm.getSettingsTable();
	
	//GUI variables
	private JColorChooser colorChooser;
	private JLabel lblTitle;
	private Container a;
	private JRadioButton rdbtnEasy;
	private JRadioButton rdbtnMedium;
	private JRadioButton rdbtnHard;
	private JRadioButton rdbtnOn;
	private JRadioButton rdbtnOff;
	
	/**
	 * Settings should only be accessed by logged in user
	 * Guest users should not be able to access this!
	 */
	public Settings() {
		//STEP 1: check if user
		if(App.isUser()) {
			//STEP 2: set background to preferred color

	        //STEP 3: initialize all other components
			init();
		} else {
			JOptionPane.showMessageDialog(null, 
					"Games Settings are only accessable by logged in users.", 
					"Guest User Settings", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	private void init() {
		//Get database info
		if(App.isUser()) {
			//Get settings from database
			GameSettings settings = st.getGameSetting(App.getUsername());
			
			//Set local variables
			color = settings.getBackgroundColor();
			isHighScoreTracking = settings.isHighScoreTracking();
			level = settings.getDifficulty();
		}
	
		//Set background color
		a = Settings.this.getContentPane();
		a.setBackground(color);
		
		//General Settings
		setTitle("General Game Setting Menu");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height = screenSize.height * 2 / 3;
		int width = screenSize.width * 1 / 3;
		setSize(width, height);
		
		//Title
		lblTitle = new JLabel("Game Settings");
		lblTitle.setFont(new Font("Lucida Grande", Font.BOLD, 28));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setForeground(Color.WHITE);
		getContentPane().add(lblTitle, BorderLayout.NORTH);
		
		//Tabbed Pane
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		//Color Chooser
		colorChooser = new JColorChooser();
		colorChooser.getSelectionModel().addChangeListener(this);
		colorChooser.setColor(color);
		tabbedPane.addTab("Color Chooser", null, colorChooser, null);
		
		//Other settings
		JComponent otherSettings = new JPanel();
		tabbedPane.addTab("Other settings", null, otherSettings, null);
		otherSettings.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("default:grow"),
				ColumnSpec.decode("60px"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("82px"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("23px"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),}));
		
		JSeparator separator_1 = new JSeparator();
		otherSettings.add(separator_1, "1, 4, 8, 1");
		
		JLabel lblDifficulty = new JLabel("Preferred Difficulty");
		lblDifficulty.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		lblDifficulty.setHorizontalAlignment(SwingConstants.CENTER);
		otherSettings.add(lblDifficulty, "2, 6, 5, 1");
		
		rdbtnEasy = new JRadioButton("Easy");
		otherSettings.add(rdbtnEasy, "2, 8, left, top");
		
		rdbtnMedium = new JRadioButton("Medium");
		otherSettings.add(rdbtnMedium, "4, 8, left, top");
		
		rdbtnHard = new JRadioButton("Hard");
		otherSettings.add(rdbtnHard, "6, 8, left, top");
		
		ButtonGroup diffGroup = new ButtonGroup();
		diffGroup.add(rdbtnEasy);
		diffGroup.add(rdbtnMedium);
		diffGroup.add(rdbtnHard);
		switch(level) {
		case EASY:
			diffGroup.setSelected(rdbtnEasy.getModel(), true);
			break;
		case MEDIUM:
			diffGroup.setSelected(rdbtnMedium.getModel(), true);
			break;
		case HARD:
			diffGroup.setSelected(rdbtnHard.getModel(), true);
			break;
		case TEST:
			break;
		default:
			break;
		}
		
		JSeparator separator = new JSeparator();
		otherSettings.add(separator, "1, 10, 8, 1");
		
		JLabel lblHighScore = new JLabel("High Score Tracking");
		lblHighScore.setHorizontalAlignment(SwingConstants.CENTER);
		lblHighScore.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		otherSettings.add(lblHighScore, "2, 12, 5, 1");
		
		rdbtnOn = new JRadioButton("On");
		otherSettings.add(rdbtnOn, "2, 14");
		
		rdbtnOff = new JRadioButton("Off");
		otherSettings.add(rdbtnOff, "6, 14");
		
		ButtonGroup hsGroup = new ButtonGroup();
		hsGroup.add(rdbtnOn);
		hsGroup.add(rdbtnOff);
		if(App.isSettingsSet()) {
			isHighScoreTracking = App.getSettings().isHighScoreTracking();
			if(isHighScoreTracking) {
				hsGroup.setSelected(rdbtnOn.getModel(), true);
			} else {
				hsGroup.setSelected(rdbtnOff.getModel(), true);
			}
		}

		JSeparator separator_2 = new JSeparator();
		otherSettings.add(separator_2, "1, 16, 8, 1");
		
		JButton btnSubmit = new JButton("Submit");
		getContentPane().add(btnSubmit, BorderLayout.SOUTH);
		
		btnSubmit.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				btnSubmitActionPerformed(e);
			} 
	    });
	}
	
    public void stateChanged(ChangeEvent e) {
        color = colorChooser.getColor();
        a.setBackground(color);
        
    }
    
    /**
     * SubmitAction subroutine.
     * @param e
     */
    private void btnSubmitActionPerformed(ActionEvent e) {
		//Step 1: get color - color is auto updated when user selects a new color
		//Step 2: get difficulty
		if(rdbtnEasy.isSelected()) {
			level = DifficultyLevel.EASY;
		} else if (rdbtnMedium.isSelected()) {
			level = DifficultyLevel.MEDIUM;
		} else {
			level = DifficultyLevel.HARD;
		}
		//Step 3: get tracking
		isHighScoreTracking = rdbtnOn.isSelected();
		//Step 4: Notify App
		GameSettings gs = new GameSettings(color, isHighScoreTracking, level);
		App.setSettings(gs);
		//Step 5: Save to database
		st.recordSetting(App.getUsername(), gs);
		//Step 6: close window
		MainMenuScreen mms = new MainMenuScreen();
		mms.setVisible(true);
		this.dispose();
    }
}
