package frc.robot.commands.Control;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ArmMotor;
import frc.robot.util.Control;

public class ArmMotorControl extends CommandBase{
    ArmMotor armMotor;

    public ArmMotorControl() {
        armMotor = ArmMotor.getInstance();

        addRequirements(armMotor);
    }

    public void execute() {
            armMotor.setArmMotor(Control.leftControllerJoystickY());
            // TODO: print to see angle values
    
}
}
