package frc.robot.commands.TestAuton;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.Called.BumperArm;
import frc.robot.commands.Called.CalledBalance;
import frc.robot.commands.Called.GroundPiece;
import frc.robot.commands.Called.Score;
import frc.robot.commands.Called.Taxi;
import frc.robot.commands.Called.TimedArm;
import frc.robot.commands.Called.TimedDrive;
import frc.robot.commands.Called.Turn;
import frc.robot.commands.Called.Wait;
import frc.robot.commands.Control.ArmPistonControl;
import frc.robot.commands.Control.ClawControl;

public class Tester1 extends SequentialCommandGroup {

    public Tester1() {

        addCommands(

            new SequentialCommandGroup(

                new Score(),
                new ParallelCommandGroup(
                    new Taxi(),
                    new BumperArm()
                ),
                new Turn(180),
                new GroundPiece()

            )
        );
    }
}
