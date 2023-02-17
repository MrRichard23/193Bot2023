package frc.robot.commands.Teleop;
import frc.robot.subsystems.Drivetrain;
import frc.robot.util.Control;
//import frc.robot.Logic;
import edu.wpi.first.wpilibj2.command.CommandBase;
public class DrivetrainControl extends CommandBase{
  
  Drivetrain drivetrain;

  public DrivetrainControl() {
    // Use addRequirements() here to declare subsystem dependencies.
    drivetrain = Drivetrain.getInstance();   
    addRequirements(drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
      drivetrain.setLeftDrive(Control.getLeftJoystickY());
      drivetrain.setRightDrive(Control.getRightJoystickY());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
