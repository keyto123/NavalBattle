package gui;

import java.awt.Color;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.JDialog;
import javax.swing.JTextPane;

/**
 * 
 * @author lucas
 * @reference - <a href=
 *            "https://stackoverflow.com/questions/4716503/reading-a-plain-text-file-in-java">reading
 *            text file</a>
 */

@SuppressWarnings("serial")
class TutorialDialog extends JDialog {

	private JTextPane tutorialMsgPane = new JTextPane();

	TutorialDialog() {
		this.setTitle("Tutorial");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLayout(null);
		this.setSize(400, 400);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setBackground(Color.GRAY);

		initTutorialTextPane();
		this.add(tutorialMsgPane);

		this.setAlwaysOnTop(true);
		this.setVisible(true);
	}

	private void initTutorialTextPane() {
		tutorialMsgPane.setSize(400, 400);
		tutorialMsgPane.setBackground(Color.GRAY);

		tutorialMsgPane.setContentType("text/html");
		tutorialMsgPane.setEditable(false);
		tutorialMsgPane.setText(readFile("resource/tutorial.html"));
	}

	private static String readFile(String path) {
		try {
			byte encoded[] = Files.readAllBytes(Paths.get(path));
			return new String(encoded, Charset.defaultCharset());
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return null;
		}
	}

}
