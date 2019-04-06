package edu.winona.cs.app;

import edu.winona.cs.component.*;
import edu.winona.cs.db.DatabaseManager;
import edu.winona.cs.db.GameSettingsTable;
import edu.winona.cs.db.UserTable;
import java.awt.Color;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Login Screen - This is the starting point for our application.
 * Allows users to create an account,
 * Login to an existing account,
 * or JUST PLAY!
 * 
 * TODO: Maybe instead of having 4 fields (2 username and 2 password) we could
 * just use 1 username field and 1 password field.  Then we just have a Create Account
 * or Login button each of which use the same username/password fields.
 * Slimmer design?
 * 
 * @author Erika Tix and Kyle Aure
 */
public class LoginScreen extends JFrame {
	//Used to serialize the LoginScreen
	private static final long serialVersionUID = -4328073729350178743L;
	
	//Database Manager
	private DatabaseManager dbm = DatabaseManager.getDatabaseManager();
        
        //Authentication
        AuthenticationHash authentication = new AuthenticationHash();
        String salt;
        String hash;
        Boolean isVerified;

	/**
	 * Constructor for the LoginScreen.
	 * Since the user has not chosen settings yet, the login screen will not depend on user settings.
	 */
    public LoginScreen() {
        initComponents();
        
        Container a = LoginScreen.this.getContentPane();
        a.setBackground(Color.ORANGE);
        
        Container b = LoginScreen.this.loginBtn;
        b.setBackground(Color.PINK);
        
        Container c = LoginScreen.this.guestLoginBtn;
        c.setBackground(Color.red);
        
        Container d = LoginScreen.this.signupBtn;
        d.setBackground(Color.PINK);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        passwordInput = new javax.swing.JPasswordField();
        loginBtn = new javax.swing.JButton();
        usernameInput = new javax.swing.JTextField();
        guestLoginBtn = new javax.swing.JButton();
        signupInput = new javax.swing.JTextField();
        passwordSignupInput = new javax.swing.JPasswordField();
        signupBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextPane2 = new javax.swing.JTextPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        passwordInput.setText("password");
        passwordInput.setToolTipText("");
        passwordInput.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                passwordInputFocusGained(evt);
            }
        });

        loginBtn.setFont(new java.awt.Font("Noteworthy", 0, 18)); // NOI18N
        loginBtn.setText("LOGIN");
        loginBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginBtnActionPerformed(evt);
            }
        });

        usernameInput.setText("Enter username");
        usernameInput.setOpaque(true);
        usernameInput.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                usernameInputFocusGained(evt);
            }
        });

        guestLoginBtn.setFont(new java.awt.Font("Noteworthy", 0, 18)); // NOI18N
        guestLoginBtn.setText("JUST LET ME PLAY!");
        guestLoginBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guestLoginBtnActionPerformed(evt);
            }
        });

        signupInput.setText("Enter username");
        signupInput.setOpaque(true);
        signupInput.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                signupInputFocusGained(evt);
            }
        });

        passwordSignupInput.setText("password");
        passwordSignupInput.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                passwordSignupInputFocusGained(evt);
            }
        });

        signupBtn.setFont(new java.awt.Font("Noteworthy", 0, 18)); // NOI18N
        signupBtn.setText("SIGN UP");
        signupBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signupBtnActionPerformed(evt);
            }
        });

        jScrollPane1.setBorder(null);

        jTextPane1.setEditable(false);
        jTextPane1.setBackground(new java.awt.Color(255, 204, 102));
        jTextPane1.setBorder(null);
        jTextPane1.setFont(new java.awt.Font("Noteworthy", 1, 24)); // NOI18N
        jTextPane1.setText("Returning Users:");
        jScrollPane1.setViewportView(jTextPane1);

        jTextPane2.setEditable(false);
        jTextPane2.setBackground(new java.awt.Color(255, 204, 102));
        jTextPane2.setBorder(null);
        jTextPane2.setFont(new java.awt.Font("Noteworthy", 1, 24)); // NOI18N
        jTextPane2.setText("Create an account: ");
        jScrollPane2.setViewportView(jTextPane2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(passwordInput)
                    .addComponent(loginBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(usernameInput)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE))
                .addGap(42, 42, 42)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                        .addComponent(guestLoginBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(passwordSignupInput)
                            .addComponent(signupBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(signupInput, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(usernameInput, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(passwordInput, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(loginBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(signupInput, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(passwordSignupInput, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(signupBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                        .addComponent(guestLoginBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Called when login button is clicked
     * @param evt - Button Click
     */
    private void loginBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginBtnActionPerformed
       //STEP 1: Get UserTable and Verify User
       UserTable ut = dbm.getUserTable();
       String username = usernameInput.getText().trim();
       
       //authenticate
       salt = authentication.generateSalt();
       hash = authentication.hashPassword(new String(passwordInput.getPassword()), salt);
       
       boolean result = ut.verifyUser(username, new String(hash));
       
       //Step 2: Clear password field for security
       passwordInput.setText("");
       
       //STEP 3: If Verified
       if(result == true) {
    	   //STEP 3.1 setUsername with APP
    	   App.setUsername(username);
    	   
    	   //STEP 3.2 Get users GameSettings and setSettings with APP
    	   GameSettingsTable gst = dbm.getGameSettingsTable();
    	   GameSettings settings = gst.getGameSetting(App.getUsername());
    	   if(settings != null) {
    		   App.setSettings(settings);
    	   } else {
    		   gst.recordGameSetting(App.getUsername(), App.DEFAULT_SETTINGS);
    		   App.setSettings(App.DEFAULT_SETTINGS);
    	   }
    	   
    	   //STEP 3.3 Open Main Menu and close this Menu
           MainMenuScreen mainMenu = new MainMenuScreen();
           mainMenu.setVisible(true);
           this.dispose();
       }else{
           JOptionPane.showMessageDialog(null, "Incorrect Username / Password.");
       }
        
    }//GEN-LAST:event_loginBtnActionPerformed

    /**
     * Called when signup button is clicked
     * @param evt - Button Click
     */
    private void signupBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signupBtnActionPerformed
    	//Step 1: Set up variables
        boolean success = false; //Assume failure
        String username = null;
        
        //Step 2: Try to create user, if exception warn user to retry.
        try {
            UserTable ut = dbm.getUserTable();
            username = signupInput.getText().trim();

            //authenticate
            salt = authentication.generateSalt();
            hash = authentication.hashPassword(new String(passwordSignupInput.getPassword()), salt);
            
            //create user with hashed password
            success = ut.createUser(username, new String(hash));
        }catch(IllegalArgumentException e){
            JOptionPane.showMessageDialog(null, "That username has already been taken!");
        }
        
        //Step 3: If successfully created
        if(success){
        	//Step 3.1: setUsername with APP
        	App.setUsername(username);
        	
        	//Step 3.2: set default settings in APP and in database
        	GameSettingsTable gst = dbm.getGameSettingsTable();
        	gst.recordGameSetting(App.getUsername(), App.DEFAULT_SETTINGS);
        	App.setSettings(App.DEFAULT_SETTINGS);
        	
        	//Step 3.3 Open Main Menu and close this menu
            MainMenuScreen mainMenu = new MainMenuScreen();
            mainMenu.setVisible(true);
            this.dispose();
        } else {
        	JOptionPane.showMessageDialog(null, "Unable to create user.  Please try again.");
        }
    }//GEN-LAST:event_signupBtnActionPerformed

    /**
     * Called when guest login button is clicked.
     * @param evt - Button Click
     */
    private void guestLoginBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guestLoginBtnActionPerformed
    	//Step 1: Set default settings with APP
    	App.setSettings(App.DEFAULT_SETTINGS);
    	
    	//Step 2: Open main menu and close this menu
        MainMenuScreen mainMenu = new MainMenuScreen();
        mainMenu.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_guestLoginBtnActionPerformed

    private void usernameInputFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_usernameInputFocusGained
        usernameInput.setText("");
    }//GEN-LAST:event_usernameInputFocusGained

    private void passwordInputFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_passwordInputFocusGained
        passwordInput.setText("");
    }//GEN-LAST:event_passwordInputFocusGained

    private void signupInputFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_signupInputFocusGained
        signupInput.setText("");
    }//GEN-LAST:event_signupInputFocusGained

    private void passwordSignupInputFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_passwordSignupInputFocusGained
        passwordSignupInput.setText("");
    }//GEN-LAST:event_passwordSignupInputFocusGained

    /**
     * Main Method.
     * TODO: Decide if we need a main method in this class.  This frame should be invoked via App.java
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LoginScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginScreen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton guestLoginBtn;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JTextPane jTextPane2;
    private javax.swing.JButton loginBtn;
    private javax.swing.JPasswordField passwordInput;
    private javax.swing.JPasswordField passwordSignupInput;
    private javax.swing.JButton signupBtn;
    private javax.swing.JTextField signupInput;
    public javax.swing.JTextField usernameInput;
    // End of variables declaration//GEN-END:variables
}
