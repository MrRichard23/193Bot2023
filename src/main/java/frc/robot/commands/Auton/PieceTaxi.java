package frc.robot.commands.Auton;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.Called.TimedArm;
import frc.robot.commands.Called.TimedDrive;
import frc.robot.commands.Called.Wait;
import frc.robot.commands.Control.ArmPistonControl;
import frc.robot.commands.Control.ClawControl;

public class PieceTaxi extends SequentialCommandGroup {

    public PieceTaxi() {

        addCommands(

            new SequentialCommandGroup(
                new ClawControl(1),
                new ArmPistonControl(0),
                new TimedArm(0.9, -1),
                new ParallelCommandGroup(
                    new TimedArm(2, -0.15),
                    new SequentialCommandGroup(
                        new ArmPistonControl(1),
                        new Wait(1),
                        new ClawControl(0),
                        // new TimedArm(0.5, -0.2),
                        new ArmPistonControl(0)
                    )
                ),
                new TimedArm(1, 0.3),
                new TimedDrive(4, -0.25, -0.25)
            )
        );
    }
}