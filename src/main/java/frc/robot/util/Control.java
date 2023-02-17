package frc.robot.util;

import frc.robot.commands.Control.ArmMotorControl;
// import frc.robot.commands.Assistance.Balance;
// import frc.robot.commands.Assistance.Stop;
import frc.robot.commands.Control.ArmPistonControl;
import frc.robot.commands.Control.ClawControl;
import frc.robot.commands.Control.Safety;
import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

public class Control {
	private static CommandJoystick leftJoystick;
	private static CommandJoystick rightJoystick;
	private static CommandXboxController xboxController;

	private static Drivetrain drivetrain;

	public static void init() {
		leftJoystick = new CommandJoystick(Constants.LEFT_JOYSTICK);
        rightJoystick = new CommandJoystick(Constants.RIGHT_JOYSTICK);
        xboxController = new CommandXboxController(Constants.XBOX_CONTROLLER);

		drivetrain = Drivetrain.getInstance();
	}
	public static void configureBindings() {
        // rightJoystick.trigger().toggleOnTrue(new Balance());
        // rightJoystick.button(2).toggleOnTrue(new Stop());
        //What's ufjilkhjmvbc

        xboxController.leftBumper().and(xboxController.rightBumper()).toggleOnTrue(new Safety());

       
        if(SmartDashboard.getBoolean("safety", false)){
            xboxController.a().toggleOnTrue(new ClawControl(true));
            xboxController.a().toggleOnFalse(new ClawControl(false));

            xboxController.b().toggleOnTrue(new ArmPistonControl(true));
            xboxController.b().toggleOnFalse(new ArmPistonControl(false));
        }
    }

    public static double getLeftJoystickY() {
        return leftJoystick.getY();
    }
    public static double getLeftThrottle() {
        return (0.5 * (1 - leftJoystick.getThrottle()));
    }
    public static double getRightJoystickY() {
        return rightJoystick.getY();
    }
    public static double getRightThrottle() {
        return (0.5 * (1 - rightJoystick.getThrottle()));
    }

    public static double leftControllerJoystickY() {
        return (0.8 * xboxController.getLeftY());
    }



}
