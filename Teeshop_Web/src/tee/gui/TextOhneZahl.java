package tee.gui;

import javax.swing.JOptionPane;

public class TextOhneZahl
{
	public boolean isAlpha(String text)
	{
		if (text.isEmpty() && text.equals(null))
			JOptionPane.showMessageDialog(null, this, "Feld muss ausgefüllt werden", 0);

		if (text.matches(".*\\d.*") == true)
		{
			return false;
		} else
			return true;

	}

}
