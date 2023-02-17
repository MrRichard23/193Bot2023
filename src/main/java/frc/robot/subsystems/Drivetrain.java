package frc.robot.subsystems;
import java.util.function.BiConsumer;

// import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.math.controller.PIDController;
// import edu.wpi.first.math.geometry.Pose2d;
// import edu.wpi.first.math.geometry.Rotation2d;
// import edu.wpi.first.wpilibj.SerialPort;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.util.Constants;
import frc.robot.util.Control;

// import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
// import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;

public class Drivetrain extends SubsystemBase{
    private static Drivetrain drivetrain;
    // private static AHRS ahrs;

    private CANSparkMax masterLeftTalon;
    private CANSparkMax masterRightTalon;
    private CANSparkMax followLeftTalon;
    private CANSparkMax followRightTalon;

    // private DifferentialDriveOdometry odometry;
    // private Pose2d pose2d;

    // public DifferentialDriveKinematics driveKinematics;

    private PIDController stopPIDController;
    private PIDController balancePIDController;
    private PIDController turnPIDController;

    private double stopPIDInput;
    
    private Drivetrain() {
        masterLeftTalon = new CANSparkMax(Constants.MASTER_LEFT_TALON_PORT, MotorType.kBrushless);
        masterRightTalon = new CANSparkMax(Constants.MASTER_RIGHT_TALON_PORT, MotorType.kBrushless);
        followLeftTalon = new CANSparkMax(Constants.FOLLOW_LEFT_TALON_PORT, MotorType.kBrushless);
        followRightTalon = new CANSparkMax(Constants.FOLLOW_RIGHT_TALON_PORT, MotorType.kBrushless);

        // followLeftTalon.follow(masterLeftTalon);
        // followRightTalon.follow(masterRightTalon);

        // ahrs = new AHRS(SerialPort.Port.kMXP);

        // driveKinematics = new DifferentialDriveKinematics(0.8);

        // pose2d = new Pose2d();

        // odometry = new DifferentialDriveOdometry(ahrs.getRotation2d(), 
        //         masterLeftTalon.getEncoder().getPosition(), masterRightTalon.getEncoder().getPosition(),
        //         pose2d
        // );

        stopPIDController = new PIDController(0,0,0);

        turnPIDController = new PIDController(0.005, 0, 0);
        turnPIDController.setTolerance(1, 5);
        turnPIDController.enableContinuousInput(-180f, 180f);

        balancePIDController = new PIDController(0,0,0);
    }

    public static Drivetrain getInstance() {
        if(drivetrain == null){
            drivetrain = new Drivetrain();
        }
        return drivetrain;
    }

    // public Pose2d getPose() {
	// 	return odometry.getPoseMeters();
	// }

    // public void resetPose(Pose2d pose) {
	// 	odometry.resetPosition(ahrs.getRotation2d(), masterLeftTalon.getEncoder().getPosition(), masterRightTalon.getEncoder().getPosition(), pose);
	// }

    // public static double getRoll(){
    //     return ahrs.getRoll();
    // }
    
    // public static double getPitch(){
    //     return ahrs.getPitch();
    // }
    
    // public static double getYaw(){
    //     return ahrs.getYaw();
    // }
    
    // public static double getXVelocity(){
    //     return ahrs.getVelocityX();
    // }
    
    // public static double getYVelocity(){
    //     return ahrs.getVelocityY();
    // }
    
    // public static double getZVelocity(){
    //     return ahrs.getVelocityZ();
    // }


    public CANSparkMax getLeftDrive() {
        return masterLeftTalon;
    }

    public CANSparkMax getRightDrive() {
        return masterRightTalon;
    }

    public void setLeftDrive(double speed) {
        masterLeftTalon.set(speed * Control.getLeftThrottle());
        followLeftTalon.set(speed * Control.getLeftThrottle());
    }
    
    public void setRightDrive(double speed) {
        masterRightTalon.set(speed * Control.getLeftThrottle());
        followRightTalon.set(speed * Control.getLeftThrottle());
    }
    
    public void setAllDrive(double speed) {
        masterLeftTalon.set(speed * Control.getLeftThrottle());
        masterRightTalon.set(speed * Control.getLeftThrottle());
    }
    
    public void setDrive(double leftMotorSpeed, double rightMotorSpeed){
        masterLeftTalon.set(leftMotorSpeed * Control.getLeftThrottle());
        masterRightTalon.set(rightMotorSpeed * Control.getLeftThrottle());
    }
    
    public void setStop(){
        masterLeftTalon.set(0);
        masterRightTalon.set(0);
    }
    
    public void setPIDStop(){
        stopPIDInput = masterLeftTalon.getEncoder().getVelocity();
        masterLeftTalon.set(stopPIDController.calculate(stopPIDInput,0));
        masterRightTalon.set(stopPIDController.calculate(stopPIDInput,0));
    }
    
    public void setFullLeftDrive(double speed) {
        masterLeftTalon.set(speed);
    }
    
    public void setFullRightDrive(double speed) {
        masterRightTalon.set(speed);
    }
    
    public void setFullAllDrive(double speed) {
        masterLeftTalon.set(speed);
        masterRightTalon.set(speed);
    }
    
    public void setFullDrive(double leftMotorSpeed, double rightMotorSpeed){
        masterLeftTalon.set(leftMotorSpeed);
        masterRightTalon.set(rightMotorSpeed);
    }
    
    // public void setBalanceDrivetrain(){
    //     masterLeftTalon.set(balancePIDController.calculate(Drivetrain.getPitch(),0));
    //     masterRightTalon.set(balancePIDController.calculate(Drivetrain.getPitch(),0));
    // }
    
    // public void setTurnPID(double degrees, double oldYaw){
    //     masterLeftTalon.set(turnPIDController.calculate(Drivetrain.getYaw(), (degrees + oldYaw)));
    //     masterRightTalon.set(turnPIDController.calculate(Drivetrain.getYaw(), (degrees + oldYaw)));
    // }
    
    public boolean turnSetpoint(){
        return turnPIDController.atSetpoint();
     }
}
