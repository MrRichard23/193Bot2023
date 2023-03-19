package frc.robot.commands.TestAuton;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.Called.ArmToDegree;
import frc.robot.commands.Called.CalledBalance;
import frc.robot.commands.Called.TimedArm;
import frc.robot.commands.Called.TimedDrive;
import frc.robot.commands.Called.Wait;
import frc.robot.commands.Control.ArmPistonControl;
import frc.robot.commands.Control.ClawControl;

public class Tester3 extends SequentialCommandGroup {

    public Tester3() {

        addCommands(

            new SequentialCommandGroup(

                new ArmToDegree(90)

            )
        );
    }
}
