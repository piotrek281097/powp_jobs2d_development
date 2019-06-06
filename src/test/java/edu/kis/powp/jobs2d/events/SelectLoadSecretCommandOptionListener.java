package edu.kis.powp.jobs2d.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;
import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;
import edu.kis.powp.jobs2d.features.CommandsFeature;

public class SelectLoadSecretCommandOptionListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		List<DriverCommand> commands = new ArrayList<DriverCommand>();

		// glowa
		commands.add(new SetPositionCommand(-40, 40));
		commands.add(new OperateToCommand(40, 40));
		commands.add(new OperateToCommand(60, 20));
		commands.add(new OperateToCommand(60, 10));
		commands.add(new OperateToCommand(40, -10));
		commands.add(new OperateToCommand(-40, -10));
		commands.add(new OperateToCommand(-60, 10));
		commands.add(new OperateToCommand(-60, 20));
		commands.add(new OperateToCommand(-40, 40));

		// oczy
		commands.add(new SetPositionCommand(-40, -10));
		commands.add(new OperateToCommand(-40, -40));
		commands.add(new OperateToCommand(-20, -40));
		commands.add(new OperateToCommand(-10, -30));
		commands.add(new OperateToCommand(-10, -10));

		commands.add(new SetPositionCommand(-30, -10));
		commands.add(new OperateToCommand(-30, -30));
		commands.add(new OperateToCommand(-20, -30));
		commands.add(new OperateToCommand(-20, -10));

		commands.add(new SetPositionCommand(40, -10));
		commands.add(new OperateToCommand(40, -40));
		commands.add(new OperateToCommand(20, -40));
		commands.add(new OperateToCommand(10, -30));
		commands.add(new OperateToCommand(10, -10));

		commands.add(new SetPositionCommand(30, -10));
		commands.add(new OperateToCommand(30, -30));
		commands.add(new OperateToCommand(20, -30));
		commands.add(new OperateToCommand(20, -10));

		//nos
		commands.add(new SetPositionCommand(-10, 0));
		commands.add(new OperateToCommand(-10, 10));

		commands.add(new SetPositionCommand(10, 0));
		commands.add(new OperateToCommand(10, 10));

		//usmiech

		commands.add(new SetPositionCommand(-40, 20));
		commands.add(new OperateToCommand(-30, 30));
		commands.add(new OperateToCommand(30, 30));
		commands.add(new OperateToCommand(40, 20));

		commands.add(new SetPositionCommand(-50, 20));
		commands.add(new OperateToCommand(-30, 20));

		commands.add(new SetPositionCommand(50, 20));
		commands.add(new OperateToCommand(30, 20));
		DriverCommandManager manager = CommandsFeature.getDriverCommandManager();
		manager.setCurrentCommand(commands, "TopSecretCommand");
	}
}
