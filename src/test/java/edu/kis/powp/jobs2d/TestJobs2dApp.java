package edu.kis.powp.jobs2d;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.kis.legacy.drawer.panel.DrawPanelController;
import edu.kis.legacy.drawer.shape.LineFactory;
import edu.kis.powp.appbase.Application;
import edu.kis.powp.jobs2d.command.gui.CommandManagerWindow;
import edu.kis.powp.jobs2d.command.gui.CommandManagerWindowCommandChangeObserver;
import edu.kis.powp.jobs2d.drivers.adapter.LineDriverAdapter;
import edu.kis.powp.jobs2d.events.SelectLoadSecretCommandOptionListener;
import edu.kis.powp.jobs2d.events.SelectRunCurrentCommandOptionListener;
import edu.kis.powp.jobs2d.events.SelectTestFigure2OptionListener;
import edu.kis.powp.jobs2d.events.SelectTestFigureOptionListener;
import edu.kis.powp.jobs2d.features.CommandsFeature;
import edu.kis.powp.jobs2d.features.DrawerFeature;
import edu.kis.powp.jobs2d.features.DriverFeature;
import edu.kis.powp.jobs2d.resources.ResourceClassSingleton;

import javax.swing.*;

public class TestJobs2dApp {
	private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	private static CustomizableLine ourBeautifulLine = new CustomizableLine();

	/**
	 * Setup test concerning preset figures in context.
	 *
	 * @param application Application context.
	 */
	private static void setupPresetTests(Application application) {
		SelectTestFigureOptionListener selectTestFigureOptionListener = new SelectTestFigureOptionListener(
				DriverFeature.getDriverManager());
		SelectTestFigure2OptionListener selectTestFigure2OptionListener = new SelectTestFigure2OptionListener(
				DriverFeature.getDriverManager());

		application.addTest("Figure Joe 1", selectTestFigureOptionListener);
		application.addTest("Figure Joe 2", selectTestFigure2OptionListener);
	}

	/**
	 * Setup test using driver commands in context.
	 *
	 * @param application Application context.
	 */
	private static void setupCommandTests(Application application) {
		application.addTest("Load secret command", new SelectLoadSecretCommandOptionListener());

		application.addTest("Run command", new SelectRunCurrentCommandOptionListener(DriverFeature.getDriverManager()));

	}

	/**
	 * Setup driver manager, and set default Job2dDriver for application.
	 *
	 * @param application Application context.
	 */
	private static void setupDrivers(Application application) {
		Job2dDriver loggerDriver = new LoggerDriver();
		DriverFeature.addDriver("Logger driver", loggerDriver);

		DrawPanelController drawerController = DrawerFeature.getDrawerController();
		Job2dDriver driver = new LineDriverAdapter(drawerController, LineFactory.getBasicLine(), "basic");
		DriverFeature.addDriver("Line Simulator", driver);
		DriverFeature.getDriverManager().setCurrentDriver(driver);

		driver = new LineDriverAdapter(drawerController, LineFactory.getSpecialLine(), "special");
		DriverFeature.addDriver("Special line Simulator", driver);
		DriverFeature.updateDriverInfo();

		driver = new LineDriverAdapter(drawerController, ourBeautifulLine, "customizable");
		DriverFeature.addDriver("Beautiful Customizable Line", driver);
		DriverFeature.updateDriverInfo();
	}

	private static void setupWindows(Application application) {

		CommandManagerWindow commandManager = new CommandManagerWindow(CommandsFeature.getDriverCommandManager());
		application.addWindowComponent("Command Manager", commandManager);

		JFrame frame = new JFrame("Customizable Line Options");
		frame.setLayout(new GridLayout(4, 1));
		frame.show();
		Dimension dimension = new Dimension(550, 250);
		frame.setSize(dimension);
		frame.setResizable(false);

		Map<String, Color> ourPrefferableColors = new HashMap<>();
		ourPrefferableColors.put("Magenta", Color.MAGENTA);
		ourPrefferableColors.put("Orange", Color.ORANGE);
		ourPrefferableColors.put("Pink", Color.PINK);
		ourPrefferableColors.put("Blue", Color.BLUE);

		JComboBox colorList = new JComboBox(ourPrefferableColors.keySet().stream().toArray());
		colorList.addItemListener(itemEvent -> ourBeautifulLine.setColor(ourPrefferableColors.get(itemEvent.getItem())));

		JCheckBox dottedCheckBox = new JCheckBox();
		dottedCheckBox.setText("Dotted");
		dottedCheckBox.addActionListener(e -> ourBeautifulLine.setDotted(dottedCheckBox.isEnabled()));

		ourBeautifulLine.setColor(ourPrefferableColors.get(colorList.getSelectedItem()));

		JSlider thicknessSlider = new JSlider(JSlider.HORIZONTAL, 1, 10, 1);
		thicknessSlider.setPaintLabels(true);
		thicknessSlider.setPaintTicks(true);
		thicknessSlider.setMajorTickSpacing(1);

		thicknessSlider.addChangeListener(e -> ourBeautifulLine.setThickness(thicknessSlider.getValue()));

		JLabel label = new JLabel();
		label.setText("Thickness");

		frame.add(label);
		frame.add(thicknessSlider);
		frame.add(colorList);
		frame.add(dottedCheckBox);
		frame.move(900, 150);

		application.addJFrameWindow("Customizable Line Options", frame);

		CommandManagerWindowCommandChangeObserver windowObserver = new CommandManagerWindowCommandChangeObserver(
				commandManager);
		CommandsFeature.getDriverCommandManager().getChangePublisher().addSubscriber(windowObserver);
	}

	/**
	 * Setup menu for adjusting logging settings.
	 *
	 * @param application Application context.
	 */
	private static void setupLogger(Application application) {

		application.addComponentMenu(Logger.class, "Logger", 0);
		application.addComponentMenuElement(Logger.class, "Clear log",
				(ActionEvent e) -> application.flushLoggerOutput());
		application.addComponentMenuElement(Logger.class, "Fine level", (ActionEvent e) -> logger.setLevel(Level.FINE));
		application.addComponentMenuElement(Logger.class, "Info level", (ActionEvent e) -> logger.setLevel(Level.INFO));
		application.addComponentMenuElement(Logger.class, "Warning level",
				(ActionEvent e) -> logger.setLevel(Level.WARNING));
		application.addComponentMenuElement(Logger.class, "Severe level",
				(ActionEvent e) -> logger.setLevel(Level.SEVERE));
		application.addComponentMenuElement(Logger.class, "OFF logging", (ActionEvent e) -> logger.setLevel(Level.OFF));
	}

	public Logger getLogger() {
		return logger;
	}

	private static void notifyAboutResources() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Current resources:");
		stringBuilder.append("\nInk: " + ResourceClassSingleton.getInstance().getInk());
		stringBuilder.append("\nUsage: " + ResourceClassSingleton.getInstance().getUsage());
		logger.info(stringBuilder.toString());
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		//TODO add loading resources from file and set them
		ResourceClassSingleton.getInstance().setInk(15000);
		ResourceClassSingleton.getInstance().setUsage(50000);

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				Application app = new Application("Jobs 2D");
				DrawerFeature.setupDrawerPlugin(app);
				CommandsFeature.setupCommandManager();

				DriverFeature.setupDriverPlugin(app);
				setupDrivers(app);
				setupPresetTests(app);
				setupCommandTests(app);
				setupLogger(app);
				setupWindows(app);

				app.setVisibility(true);
				notifyAboutResources();
			}
		});
	}

}
