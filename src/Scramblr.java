import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

/**®
 * This program scrambles the order of letters given a block of text provided
 * by the user.
 * 
 * @author  Ritam Chakraborty
 * @version February 24, 2022
 */
public class Scramblr extends JFrame implements ActionListener, MouseListener {

    private static final Color enterColor = new Color(38, 134, 54);
    
    private static final Font smallTextFont = new Font("Latin Modern Sans", Font.PLAIN, 14);
    private static final Font mediumTextFont = new Font("Latin Modern Sans", Font.PLAIN, 16);
    
    private final JPanel panel, buttonPanel;
    private final JTextArea inputArea, outputArea;
    private final JScrollPane inputPane, outputPane;
    private final JButton enter;
    private final JMenuBar menu;
    private final JMenu file;
    private final JMenuItem openFile;
    private final JFileChooser openFileChooser;

    /**
     * Creates a Guided User Interface for this program.
     */
    public Scramblr() {
        super("Scramblr");
        setBounds(0, 0, 600, 400);

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(getWidth() / 40, 
            getHeight() / 40, getWidth() / 40, getHeight() / 40));
        panel.setBackground(Color.lightGray);

        inputArea = new JTextArea("Welcome to Scramblr! Type your text here to scramble it.");
        inputArea.setFont(smallTextFont);
        inputArea.setCaretPosition(inputArea.getText().length());
        inputArea.setMargin(new Insets(5, 5, 5, 5));
        inputArea.setLineWrap(true);
        inputArea.setWrapStyleWord(true);
        inputPane = new JScrollPane(inputArea,
            ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(smallTextFont);
        outputArea.setMargin(inputArea.getInsets());
        outputArea.setLineWrap(true);
        outputArea.setWrapStyleWord(true);
        outputPane = new JScrollPane(outputArea, 
            ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.setBackground(Color.lightGray);

        enter = new JButton("Enter");
        enter.setOpaque(true);
        enter.setBorderPainted(false);
        enter.setBackground(enterColor);
        enter.setForeground(Color.white);
        enter.setFont(mediumTextFont);
        enter.addActionListener(this);
        enter.addMouseListener(this);

        buttonPanel.add(enter);

        panel.add(inputPane);
        panel.add(Box.createVerticalStrut(getHeight() / 25));
        panel.add(outputPane);
        panel.add(Box.createVerticalStrut(getHeight() / 25));
        panel.add(buttonPanel);

        menu = new JMenuBar();
        setJMenuBar(menu);

        file = new JMenu("File");
        menu.add(file);

        openFile = new JMenuItem("Open...");
        openFile.addActionListener(this);
        file.add(openFile);

        openFileChooser = new JFileChooser();
        openFileChooser.setFileFilter(new FileNameExtensionFilter("Text files", "txt"));

        add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    public static void main(String[] args) {
        new Scramblr();
    }

    private void refresh() {
        outputArea.setText(Scramble.processText(inputArea.getText()));
        outputArea.setSelectionStart(0);
        outputArea.setSelectionEnd(0);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == enter) {
            refresh();
        }

        else if (e.getSource() == openFile) {
            int fileChosen = openFileChooser.showOpenDialog(this);

            if (fileChosen == JFileChooser.APPROVE_OPTION) {
                File textFile = openFileChooser.getSelectedFile();

                if (!textFile.getName().substring(textFile.getName().lastIndexOf(".") + 1).equals("txt")) {
                    JOptionPane.showMessageDialog(this, "This file type is not supported!");   
                }

                else {
                    try {
                        String content = new String(Files.readAllBytes(textFile.toPath()));
                        inputArea.setText(content);
                        inputArea.setSelectionStart(0);
                        inputArea.setSelectionEnd(0);
                        refresh();
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(this, "This file type is not supported!");    
                    }
                }
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e)
    {
        if (e.getSource() == enter) {
            enter.setCursor(new Cursor(Cursor.HAND_CURSOR));
            enter.setBackground(enterColor.brighter());
        }
    }

    @Override
    public void mouseExited(MouseEvent e)
    {
        if (e.getSource() == enter) {
            enter.setBackground(enterColor);
        }
    }
}