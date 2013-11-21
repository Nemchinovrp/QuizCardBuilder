public class QuizCardBuilder {

	private JTextArea question;
	private JTextArea answer;
	private ArrayList<QuizCard> cardList;
	private JFrame frame;
	

	
	public static void main(String[] args) {
		QuizCardBuilder builder = new QuizCardBuilder();
		builder.makeGui();
	}
	public void makeGui(){
		frame = new JFrame("Викторина Карточек");
		JPanel mainPanel = new JPanel();
		Font font = new Font("sansefir", Font.BOLD, 14);
		question = new JTextArea(6,20);
		question.setLineWrap(true);
		question.setWrapStyleWord(true);
		question.setFont(font);
		
		JScrollPane qScroller = new JScrollPane(question);
		qScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		qScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		answer= new JTextArea(6,20);
		answer.setLineWrap(true);
		answer.setWrapStyleWord(true);
		answer.setFont(font);
		
		JScrollPane aScroller = new JScrollPane(answer);
		aScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		aScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		JButton nextButton = new JButton("Next Card");
		cardList = new ArrayList<QuizCard>();
		JLabel qLabel = new JLabel("Question: ");
		JLabel aLabel = new JLabel("Answer: ");
	
		mainPanel.add(qLabel);
		mainPanel.add(qScroller);
		mainPanel.add(aLabel);
		mainPanel.add(aScroller);
		mainPanel.add(nextButton);
		nextButton.addActionListener(new NextCardListener());
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		JMenuItem newMenuItem = new JMenuItem("New");
		JMenuItem saveMenuItem = new JMenuItem("Save");
		
		newMenuItem.addActionListener(new NewMenuListener() );
		saveMenuItem.addActionListener(new SaveMenuListener() );
		fileMenu.add(newMenuItem);
		fileMenu.add(saveMenuItem);
		menuBar.add(fileMenu);
		frame.setJMenuBar(menuBar);
		frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(350, 390);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	public class NextCardListener implements ActionListener{

		
		public void actionPerformed(ActionEvent arg0) {
			QuizCard card = new QuizCard(question.getText(), answer.getText());
			cardList.add(card);	
			clearCard();
		}
		
	}
	
public class SaveMenuListener implements ActionListener{

		
		public void actionPerformed(ActionEvent arg0) {
			QuizCard card = new QuizCard(question.getText(), answer.getText());
			cardList.add(card);	
			JFileChooser fileSave = new JFileChooser();
			fileSave.showSaveDialog(frame);
			saveFile(fileSave.getSelectedFile());
				}}
	
	public class NewMenuListener implements ActionListener{

	
		public void actionPerformed(ActionEvent arg0) {
			cardList.clear();
			clearCard();
			}	
	}
	private void clearCard(){
		question.setText("");
		answer.setText("");
		question.requestFocus();
	}
	
	
	private void saveFile(File file){
		try{
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			for(QuizCard card: cardList){
				writer.write(card.getQuestion() + "/");
				writer.write(card.getAnswer() + "\n");
			}
			writer.close();
		}
		catch(IOException ex){ex.printStackTrace();}
	}
	public class QuizCard{
		public QuizCard(String text, String text2){
			
		}
		public String getQuestion(){
			String text;
			text = question.getText();
			return text;
		}
		public String getAnswer(){
			String text2;
			text2 = answer.getText();
			return text2;
		}
	}
}
