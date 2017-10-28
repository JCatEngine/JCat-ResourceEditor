package JavaFxPlus.Tool;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import JavaFxPlus.Manager.ConfigureManager;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class FileChooserTool {

	/**
	 * 
	 * @param title
	 * @param arrayList
	 * @param stage
	 * @return
	 */
	public static List<File> openMultipleDialog(String title, ArrayList<ExtensionFilter> arrayList, Stage stage) {
		FileChooser fileChooser = _fileChooser(title, arrayList);
		// set init directory
		if (ConfigureManager.getInstance().getLastChoosePath() != null) {
			fileChooser.setInitialDirectory(ConfigureManager.getInstance().getLastChoosePath());
		}
		List<File> files = fileChooser.showOpenMultipleDialog(stage);

		// save init directory
		if (files != null && files.size() > 0) {
			File file = files.get(0);
			if (!file.isDirectory()) {
				ConfigureManager.getInstance().setLastChoosePath(file.getParentFile());
			}

		}

		return files;
	}

	private static FileChooser _fileChooser(String title, ArrayList<ExtensionFilter> arrayList) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle(title);
		fileChooser.getExtensionFilters().addAll(arrayList);

		return fileChooser;
	}

	/**
	 * 
	 * @param title
	 * @param arrayList
	 * @param stage
	 * @return
	 */
	public static File openSaveDialog(String title, ArrayList<ExtensionFilter> arrayList, Stage stage) {
		FileChooser fileChooser = _fileChooser(title, arrayList);

		// set init directory
		if (getConfigureManager().getLastSavePath() != null) {
			fileChooser.setInitialDirectory(getConfigureManager().getLastSavePath());
		}
		File file = fileChooser.showSaveDialog(stage);

		// save init directory
		if (file != null) {
			if (!file.isDirectory()) {
				getConfigureManager().setLastSavePath(file.getParentFile());
			}
		}

		return file;
	}

	/**
	 * 
	 * @return
	 */
	private static ConfigureManager getConfigureManager() {
		// TODO Auto-generated method stub
		return ConfigureManager.getInstance();
	}

	/**
	 * 
	 * @param title
	 * @param arrayList
	 * @param stage
	 * @return
	 */
	public static File openDialog(String title, ArrayList<ExtensionFilter> arrayList, Stage stage) {

		FileChooser fileChooser = _fileChooser(title, arrayList);

		// set init directory
		if (getConfigureManager().getLastChoosePath() != null) {
			fileChooser.setInitialDirectory(getConfigureManager().getLastChoosePath());
		}
		File file = fileChooser.showOpenDialog(stage);

		// save init directory
		if (file != null) {
			if (!file.isDirectory()) {
				getConfigureManager().setLastChoosePath(file.getParentFile());
			}
		}

		return file;
	}

	/**
	 * 
	 * @param title
	 * @param arrayList
	 * @param stage
	 * @return
	 */
	public static File openDialog(String title, ArrayList<ExtensionFilter> arrayList, Stage stage, File path) {

		FileChooser fileChooser = _fileChooser(title, arrayList);

		// set init directory
		if (getConfigureManager().getLastChoosePath() != null) {
			fileChooser.setInitialDirectory(getConfigureManager().getLastChoosePath());
		}
		File file = fileChooser.showOpenDialog(stage);

		// save init directory
		if (file != null) {
			if (!file.isDirectory()) {
				getConfigureManager().setLastChoosePath(file.getParentFile());
			}
		}

		return file;
	}

	public static File openDialogUseSavePath(String title, ArrayList<ExtensionFilter> arrayList, Stage stage) {
		FileChooser fileChooser = _fileChooser(title, arrayList);

		// set init directory
		if (getConfigureManager().getLastSavePath() != null) {
			fileChooser.setInitialDirectory(getConfigureManager().getLastSavePath());
		}
		File file = fileChooser.showOpenDialog(stage);

		// save init directory
		if (file != null) {
			if (!file.isDirectory()) {
				getConfigureManager().setLastSavePath(file.getParentFile());
			}
		}

		return file;
	}

}
