package frc.robot.commands.Control;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;


public class Safety extends CommandBase{
    
    public Safety(){
        //good to have even if there's nothing in it- sumeet '23

    }

    @Override
    public void initialize() {

      SmartDashboard.putBoolean("safety", true);
    }

    @Override
    public void end(boolean interrupted) {
        SmartDashboard.putBoolean("safety", false);
        
    }
}
