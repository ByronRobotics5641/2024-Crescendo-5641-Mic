package frc.robot.robot.commands;

import frc.robot.robot.Constants;
import frc.robot.robot.subsystems.swerve.rev.RevSwerve;
import frc.robot.robot.subsystems.swerve.rev.RevSwerveConfig;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.Command;


public class TeleopSwerve extends Command {    
    private RevSwerve s_Swerve;    
    private DoubleSupplier translationSup;
    private DoubleSupplier strafeSup;
    private DoubleSupplier rotationSup;
    private BooleanSupplier robotCentricSup;
    private BooleanSupplier speedToggle;
    private BooleanSupplier demoModeToggle;

    public TeleopSwerve(RevSwerve s_Swerve, DoubleSupplier translationSup, DoubleSupplier strafeSup, DoubleSupplier rotationSup, BooleanSupplier robotCentricSup, BooleanSupplier speedToggle, BooleanSupplier demoModeToggle) {
        this.s_Swerve = s_Swerve;
        addRequirements(s_Swerve);

        this.translationSup = translationSup;
        this.strafeSup = strafeSup;
        this.rotationSup = rotationSup;
        this.robotCentricSup = robotCentricSup;
        this.speedToggle = speedToggle;
        this.demoModeToggle = demoModeToggle;
    }



    @Override
    public void execute() {
        /* Get Values, Deadband*/
        double translationVal = MathUtil.applyDeadband(translationSup.getAsDouble(), Constants.stickDeadband);
        double strafeVal = MathUtil.applyDeadband(strafeSup.getAsDouble(), Constants.stickDeadband);
        double rotationVal = MathUtil.applyDeadband(rotationSup.getAsDouble(), Constants.stickDeadband);


        boolean demoMode = demoModeToggle.getAsBoolean();
        if (!demoMode) {
            RevSwerveConfig.maxSpeed = 0.1;
            System.out.println("Current speed: " + RevSwerveConfig.maxSpeed);
        } 


        else{
        boolean speedToggleVal = speedToggle.getAsBoolean();
        if (speedToggleVal) {
            RevSwerveConfig.maxSpeed = RevSwerveConfig.lowSpeed;
            System.out.println("Current speed: " + RevSwerveConfig.maxSpeed);
        } else {
            RevSwerveConfig.maxSpeed = 1;
            System.out.println("Current speed: " + RevSwerveConfig.maxSpeed);
        }
        }

        
        
        

        /* Drive */
        s_Swerve.drive(
            new Translation2d(translationVal, strafeVal)/*.times(RevSwerveConfig.maxSpeed)*/, 
            rotationVal * RevSwerveConfig.maxAngularVelocity, 
            !robotCentricSup.getAsBoolean(), 
            true
        );
    }
}