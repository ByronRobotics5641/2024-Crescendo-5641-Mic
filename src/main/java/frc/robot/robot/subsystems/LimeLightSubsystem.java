
package frc.robot.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LimeLightSubsystem extends SubsystemBase {
    private final NetworkTable limelightTable;

    public LimeLightSubsystem() {
        limelightTable = NetworkTableInstance.getDefault().getTable("limelight");
    }

    /** Horizontal offset from crosshair to target (-27° to 27°) */
    public double getTX() {
        return limelightTable.getEntry("tx").getDouble(0.0);
    }

    /** Vertical offset from crosshair to target (-20.5° to 20.5°) */
    public double getTY() {
        return limelightTable.getEntry("ty").getDouble(0.0);
    }

    /** Target area (0% of image to 100% of image) */
    public double getTA() {
        return limelightTable.getEntry("ta").getDouble(0.0);
    }

    /** Whether a valid target is visible (0 or 1) */
    public boolean hasTarget() {
        return limelightTable.getEntry("tv").getDouble(0.0) == 1.0;
    }

    /** Sets camera mode (0 = vision processor, 1 = driver camera) */
    public void setCameraMode(int mode) {
        limelightTable.getEntry("camMode").setNumber(mode);
    }

    /** Sets LED mode (0 = pipeline default, 1 = off, 2 = blink, 3 = on) */
    public void setLEDMode(int mode) {
        limelightTable.getEntry("ledMode").setNumber(mode);
    }

    @Override
    public void periodic() {
        // Called once per scheduler run
        // Could be used for debugging
        // SmartDashboard.putNumber("LimelightX", getTX());
        // SmartDashboard.putNumber("LimelightY", getTY());
        // SmartDashboard.putNumber("LimelightArea", getTA());
    }
}

