package frc.robot.robot.commands;


import frc.robot.robot.subsystems.swerve.rev.RevSwerve;
import frc.robot.robot.subsystems.swerve.rev.RevSwerveConfig;


import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.Command;


public class AutoSwerve extends Command {    
    private RevSwerve s_Swerve;    
    private double translationSup;
    private double strafeSup;
    private double rotationSup;
    private boolean robotCentricSup;

    public AutoSwerve(RevSwerve s_Swerve, double translationSup, double strafeSup, double rotationSup, boolean robotCentricSup) {
        this.s_Swerve = s_Swerve;
        addRequirements(s_Swerve);

        this.translationSup = translationSup;
        this.strafeSup = strafeSup;
        this.rotationSup = rotationSup;
        this.robotCentricSup = robotCentricSup;
    }

    @Override
    public void initialize(){
        s_Swerve.zeroGyro();
    }

    @Override
    public void execute() {
        /* set the values because I'm lazy and reused code :) */
        double translationVal = translationSup;
        double strafeVal = strafeSup;
        double rotationVal = rotationSup;

        RevSwerveConfig.maxSpeed = 0.6;

        /* Drive */
        s_Swerve.drive(
            new Translation2d(translationVal, strafeVal).times(RevSwerveConfig.maxSpeed), 
            rotationVal * RevSwerveConfig.maxAngularVelocity, 
            !robotCentricSup, 
            true
        );
    }
}