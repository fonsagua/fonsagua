/*
 * Copyright (c) 2010. Cartolab (Universidade da Coruña)
 * 
 * This file is part of EIEL Validation
 * 
 * EIEL Validation is free software: you can redistribute it and/or modify it under the terms
 * of the GNU General Public License as published by the Free Software Foundation, either
 * version 3 of the License, or any later version.
 * 
 * EIEL Validation is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with EIEL Validation
 * If not, see <http://www.gnu.org/licenses/>.
 */

package es.udc.cartolab.gvsig.fonsagua.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.iver.andami.PluginServices;

/**
 * <code>SaveFileDialog</code> provides a simple mechanism for the user to
 * choose a file to write some content on it. It also provides methods to write
 * contents to disk. The user is prompted before overwrite an existing file and
 * a filter for show only some files based on their extension can be set. If a
 * filter is seted the first extension provided to the constructor is used as
 * default extension. This is, if the user don't add it to the choosen file name
 * it's added.
 * <p>
 * The following code pops up a file chooser for the user's home directory that
 * sees only .html and .htm (and is equivalent in uppercase). .html is appended
 * to the name of the file. Then a String is writed to disk:
 * 
 * <pre>
 * SaveFileDialog sfd = new SaveFileDialog(&quot;HTML files&quot;, &quot;html&quot;, &quot;htm&quot;);
 * File f = sfd.showDialog();
 * if (f != null) {
 *     if (!sfd.writeFileToDisk(contents, f)) {
 * 	NotificationManager.showMessageError(&quot;error_saving_file&quot;, null);
 *     }
 * }
 * </pre>
 * 
 * </p>
 * 
 * @version 0.1 26/10/2010
 * 
 */
@SuppressWarnings("serial")
public class SaveFileDialog extends JFileChooser {

    private FileFilter filter = null;
    private String defaultExtension = null;

    /**
     * if extensions are provided extensions[0] will be the appended to the
     * fileName if it doesn't have jet
     * 
     * @param description
     *            A string to be shown in the filter files combobox
     * @param extensions
     *            The extensions wanted to be filtered. Must be lowercase,
     *            uppercase validity is handled automaticaly. extensions[0] will
     *            be the default extension.
     */
    public SaveFileDialog(String description, String... extensions) {
	super();
	this.filter = new FileNameExtensionFilter(description, extensions);
	this.defaultExtension = extensions[0];
    }

    public SaveFileDialog() {
	super();
    }

    /**
     * @return null if user cancel save operation, the File to save in other
     *         case.
     */
    public File showDialog() {
	File file = null;
	if (filter != null) {
	    setFileFilter(filter);
	}

	do {
	    int returnVal = showSaveDialog(null);
	    if (returnVal == JFileChooser.CANCEL_OPTION) {
		break;
	    }

	    File tmpFile = getSelectedFile();

	    // Add the default extension
	    if (defaultExtension != null) {
		if (!tmpFile.getName().toLowerCase()
			.endsWith("." + defaultExtension)) {
		    tmpFile = new File(tmpFile.getAbsolutePath() + "."
			    + defaultExtension);
		}
	    }

	    if (tmpFile.exists()) {
		int overwriteFile = JOptionPane.showConfirmDialog(null,
			PluginServices.getText(this, "file_already_exists"),
			PluginServices.getText(this, "warning"),
			JOptionPane.YES_NO_OPTION);
		if (overwriteFile == JOptionPane.NO_OPTION) {
		    continue;
		}
	    }

	    file = tmpFile;

	} while (file == null);

	return file;
    }

    /**
     * @param contents
     *            to be write to disk
     * @param file
     *            to write
     * @return true if there was any trouble
     */
    public boolean writeFileToDisk(String contents, File file) {
	FileOutputStream fos = null;
	PrintStream ps = null;
	boolean error = false;
	try {
	    fos = new FileOutputStream(file);
	    ps = new PrintStream(fos);
	    ps.println(contents);
	} catch (IOException e1) {
	    error = true;
	    e1.printStackTrace();
	} finally {
	    if (ps != null) {
		ps.close();
	    }
	    if (fos != null) {
		try {
		    fos.close();
		} catch (IOException e) {
		    error = true;
		}
	    }
	}
	return error;
    }
}
