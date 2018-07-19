package gui;

import java.awt.Color;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import javax.swing.JDialog;
import javax.swing.JTextPane;

/**
 * @reference - <a href=
 *            "https://stackoverflow.com/questions/4716503/reading-a-plain-text-file-in-java">reading
 *            text file</a><br>
 *            - <a href="https://stackoverflow.com/questions/309424/read-convert-an-inputstream-to-a-string">Converting inputstream to string</a>
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
		tutorialMsgPane.setText(readFile("tutorial.html"));
	}

	private static String readFile(String path) {
		String fileContent = null;
		try (InputStream is = TutorialDialog.class.getResourceAsStream("/" + path)) {

			try(Scanner s = new Scanner(is)) {
				s.useDelimiter("\\A");
				fileContent = s.hasNext() ? s.next() : "";				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileContent;
	}

}
