package movies;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUI extends JFrame {
	
    private JPanel cardPanel;
    private CardLayout cardLayout;
    private JTextField textfield1, textfield2, textfield3, textfield4, textfield5, textfield6, textfield7;
    private JTextField textfield11, textfield12, textfield13, textfield14, textfield15, textfield16, textfield17, textfield18;
    private JTextField textfield21, textfield22, textfield23, textfield24, textfield25, textfield26, textfield27, textfield28;
    private JButton button1, button2, button3, button4, button5, button6, done_button, done_button2, button40, button41;
    private ArrayList<Movie_Listing> movies = new ArrayList<>();
    private ArrayList<Serie> series = new ArrayList<>();
    private JList<String> movieList;
    private DefaultListModel<String> listModel;

    public GUI(List<Movie_Listing> moviess, ArrayList<Serie> seriess) {
        cardPanel = new JPanel();
        cardLayout = new CardLayout();
        cardPanel.setLayout(cardLayout);
        
        movies = (ArrayList<Movie_Listing>) moviess;
        series = seriess;

        JPanel panel1 = createPanel("Καταχώρηση τανίας");
        JPanel panel2 = createPanel("Καταχώρηση σειράς");
        JPanel panel3 = createPanel("Αναζήτηση ταινίας / σειράς");
        JPanel panel4 = createPanel("Καταχώρηση αξιολόγησης");
        JPanel panel5 = createPanel("Εγγραφή συνδρομητών");

        // PANEL1 ΚΑΤΑΧΏΡΗΣΗ ΤΑΙΝΊΑΣ
        textfield1 = new JTextField("Τίτλος", 20);
        textfield2 = new JTextField("Περιγραφή", 40);
        textfield3 = new JTextField("Καταλληλότητα", 20);
        textfield4 = new JTextField("Έτος πρώτης προβολής", 20);
        textfield5 = new JTextField("Διάρκεια ταινίας", 10);
        textfield6 = new JTextField("Κατηγορία ταινίας", 20);
        textfield7 = new JTextField("Πρωταγωνιστές", 20);

        panel1.add(textfield1);
        panel1.add(textfield2);
        panel1.add(textfield3);
        panel1.add(textfield4);
        panel1.add(textfield5);
        panel1.add(textfield6);
        panel1.add(textfield7);

        done_button = new JButton("Καταχώρηση ταινίας στο σύστημα");
        panel1.add(done_button);
        
        done_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Movie has been stored to the System.");
				registerMovie();
			}
        	
        });

        // PANEL2 ΚΑΤΑΧΏΡΗΣΗ ΤΑΙΝΊΑΣ
        textfield11 = new JTextField("Τίτλος σειράς", 20);
        textfield12 = new JTextField("Περιγραφή", 40);
        textfield13 = new JTextField("Καταλληλότητα", 20);
        textfield14 = new JTextField("Κατηγορία σειράς", 20);
        textfield15 = new JTextField("Πρωταγωνιστές", 30);
        textfield16 = new JTextField("Σεζόν", 20);
        textfield17 = new JTextField("Αριθμός σεζόν", 20);
        textfield18 = new JTextField("Έτος προβολής", 20);

        panel2.add(textfield11);
        panel2.add(textfield12);
        panel2.add(textfield13);
        panel2.add(textfield14);
        panel2.add(textfield15);
        panel2.add(textfield16);
        panel2.add(textfield17);
        panel2.add(textfield18);
        done_button2 = new JButton("Καταχώρηση σειράς στο σύστημα");
        panel2.add(done_button2);
        done_button2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Serie has been stored to the System.");
				registerSerie();
			}
        	
        });


        // PANEL3 ΚΑΤΑΧΏΡΗΣΗ ΤΑΙΝΊΑΣ
        textfield21 = new JTextField("Τίτλος", 20);
        textfield22 = new JTextField("Τύπος", 40);
        textfield23 = new JTextField("Όνομα πρωταγωνιστή", 20);
        textfield24 = new JTextField("Καταλληλότητα", 20);
        textfield25 = new JTextField("Κατηγορία", 30);
        textfield26 = new JTextField("Βαθμός αξιολόγησης", 20);

        panel3.add(textfield21);
        panel3.add(textfield22);
        panel3.add(textfield23);
        panel3.add(textfield24);
        panel3.add(textfield25);
        panel3.add(textfield26);

        done_button = new JButton("Αναζήτηση σειράς στο σύστημα");
        panel3.add(done_button);
        
        done_button.addActionListener(new ActionListener() {

			@SuppressWarnings("unused")
			@Override
			public void actionPerformed(ActionEvent e) {
				String titl = textfield21.getText().toString();
				for(int i=0; i<movies.size(); i++) {
					if(titl.equals(movies.get(i).getTitle()) || titl.equals(series.get(i).getTitle())){
						JOptionPane.showMessageDialog(null, "Movie/Serie has been found.");
						return;
					}else {
						JOptionPane.showMessageDialog(null, "Movie/Serie hasn't been found.");
						return;

					}
				}
				
				
				
			}
        	
        });

        // panel 4
        Set<String> uniqueItems = new HashSet<>();

        listModel = new DefaultListModel<>();
        
        textfield27 = new JTextField("Δώσε αξιολόγηση", 50);        
        textfield28 = new JTextField("Βαθμολογία 1-5", 1);
        button6 = new JButton("Submit");
        
        movieList = new JList<>(listModel);
        panel4.add(movieList);
        panel4.add(textfield27);
        panel4.add(textfield28);
        panel4.add(button6);
        
        for (Movie_Listing movie : movies) {
            if (movie != null) {
                String movieString = movie.getTitle().toString();
                if (uniqueItems.add(movieString)) {
                    listModel.addElement(movieString);
                }
            }
        }
        for (Serie serie : series) {
            if (serie != null) {
                String serieString = serie.getTitle().toString();
                if (uniqueItems.add(serieString)) {
                    listModel.addElement(serieString);
                }
            }
        }
        
        
        int selectedIndex = movieList.getSelectedIndex();
        
        // Check if an item is selected
	    if (selectedIndex != -1) {
	        int movie = selectedIndex;
	        String review = textfield27.getText().toString();
	
	        for (Movie_Listing mov : moviess) {
	            if (listModel.get(movie).equals(mov)) {
	                mov.setReview(review);
	            }
	        }
	    } else {
	        // Handle the case where no item is selected, show an error message, for example.
	        //JOptionPane.showMessageDialog(null, "Please select a movie or series.");
	    }
        
        button6.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Review has been stored.");
			}
        	
        });
        
        //panel5
        
        
        

        button40 = new JButton("Sign up");
        button41 = new JButton("Sign In");
        
        panel5.add(button40);
        panel5.add(button41);
        button40.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new RegisterFrame();
				//JOptionPane.showMessageDialog(null, "User successfully signed up");
			}
        	
        });
        
        button41.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "User sign in Failed (version2 will be available soon!!)");
			}
        	
        });
        
        

        cardPanel.add(panel1, "Panel 1");
        cardPanel.add(panel2, "Panel 2");
        cardPanel.add(panel3, "Panel 3");
        cardPanel.add(panel4, "Panel 4");
        cardPanel.add(panel5, "Panel 5");

        button1 = new JButton("Καταχώρηση ταινίας");
        button2 = new JButton("Καταχώρηση σειράς");
        button3 = new JButton("Αναζήτηση ταινίας / σειράς");
        button4 = new JButton("Καταχώρηση αξιολόγησης");
        button5 = new JButton("Εγγραφή συνδρομητών");

/*
 * 
 * 
 * 
 * MENU BUTTON LISTENERS
 * 
 * 
 * 
 */
     // ActionListener για το button1
        button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(cardPanel, "Panel 1");
				
			}
            
        });
        
        // ActionListener για το button2
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Panel 2");
            }
        });

        // ActionListener για το button3
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Panel 3");
            }
        });

        // ActionListener για το button4
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Panel 4");
            }
        });

        // ActionListener για το button5
        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Panel 5");
            }
        });
//
//
//
//
// END OF MENU BUTTON LISTENERS
//
//
//        
//        

        //PANEL SETTINGS
        
        JPanel buttonPanel = new JPanel();

        buttonPanel.add(button1);
        buttonPanel.add(button2);
        buttonPanel.add(button3);
        buttonPanel.add(button4);
        buttonPanel.add(button5);

        this.setLayout(new BorderLayout());
        this.add(buttonPanel, BorderLayout.NORTH);
        this.add(cardPanel, BorderLayout.CENTER);

        this.setVisible(true);
        this.setSize(1000, 450);
        this.setTitle("Main Menu");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private JPanel createPanel(String text) {
        JPanel panel = new JPanel();
        JLabel label = new JLabel(text);
        panel.add(label);
        return panel;
    }

    private void registerMovie() {
        // Λαμβάνει τα δεδομένα από τα textfields
        String title = textfield1.getText().trim();
        String description = textfield2.getText().trim();
        boolean valid18 = "ναι".equalsIgnoreCase(textfield3.getText().trim());

        int year;
        try {
            // Μετατροπή του έτους σε ακέραιο
            year = Integer.parseInt(textfield4.getText().trim());
        } catch (NumberFormatException ex) {
            // Εμφανίζει μήνυμα σφάλματος αν η μετατροπή αποτύχει
            JOptionPane.showMessageDialog(null, "Εισάγετε έγκυρο έτος πρώτης προβολής.");
            return;
        }

        int duration;
        try {
            // Μετατροπή της διάρκειας σε ακέραιο
            duration = Integer.parseInt(textfield5.getText().trim());
        } catch (NumberFormatException ex) {
            // Εμφανίζει μήνυμα σφάλματος αν η μετατροπή αποτύχει
            JOptionPane.showMessageDialog(null, "Εισάγετε έγκυρη διάρκεια ταινίας.");
            return;
        }

        // Λαμβάνει τα υπόλοιπα δεδομένα από τα textfields
        String category = textfield6.getText().trim();
        String names = textfield7.getText().trim();

        // Εμφανίζει μήνυμα επιτυχίας
        JOptionPane.showMessageDialog(null, "Η ταινία καταχωρήθηκε με επιτυχία!");
        // Δημιουργεί ένα αντικείμενο τύπου Movie_Listing και το προσθέτει στη λίστα movies
        Movie_Listing movie = new Movie_Listing(title, description, valid18, year, duration, category, names);
        movies.add(movie);
    }

    private void registerSerie() {
        // Debug εκτύπωση
        System.out.println("Debug: Inside registerMovie()");

        // Λαμβάνει τα δεδομένα από τα textfields
        String title = textfield11.getText().trim();
        String description = textfield12.getText().trim();
        boolean valid18 = "ναι".equalsIgnoreCase(textfield13.getText().trim());
        String category = textfield14.getText().trim();
        String actors = textfield15.getText().trim();
        String seasonName = textfield16.getText().trim();

        int seasonNum;
        try {
            // Μετατροπή του αριθμού σεζόν σε ακέραιο
            seasonNum = Integer.parseInt(textfield17.getText().trim());
        } catch (NumberFormatException ex) {
            // Εμφανίζει μήνυμα σφάλματος αν η μετατροπή αποτύχει
            JOptionPane.showMessageDialog(null, "Εισάγετε έγκυρο αριθμό σεζόν.");
            return;
        }

        int year;
        try {
            // Μετατροπή του έτους σε ακέραιο
            year = Integer.parseInt(textfield18.getText().trim());
        } catch (NumberFormatException ex) {
            // Εμφανίζει μήνυμα σφάλματος αν η μετατροπή αποτύχει
            JOptionPane.showMessageDialog(null, "Εισάγετε ένα έγκυρο έτος προβολής.");
            return;
        }

        // Εμφανίζει μήνυμα επιτυχίας
        JOptionPane.showMessageDialog(null, "Η σειρά καταχωρήθηκε με επιτυχία!");
        // Δημιουργεί ένα αντικείμενο τύπου Serie και το προσθέτει στη λίστα series
        Serie s1 = new Serie(title, description, valid18, category, actors, seasonName, seasonNum, year);
        series.add(s1);
    }

}
