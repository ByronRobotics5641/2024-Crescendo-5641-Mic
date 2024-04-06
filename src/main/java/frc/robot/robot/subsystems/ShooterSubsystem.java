// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ShooterSubsystem extends SubsystemBase {
  /** Creates a new ShooterSubSystem. */

  CANSparkMax shooter1 = new CANSparkMax(13,MotorType.kBrushless);
  CANSparkMax shooter2 = new CANSparkMax(14,MotorType.kBrushless);
  
  public ShooterSubsystem() {}


  //Drives shooter at a speed which is determined by a command
  public void shooterDriver(double speed)
  {
    shooter1.set(-speed);
    shooter2.set(speed);
  }
  //Drives index at a speed which is determined by a command


  
  //Brayden's code
  public void loadShooter() {
    shooter1.set(1); 
    shooter2.set(-1);
  }
    public void launchShooter() {
    shooter1.set(-1);
    shooter2.set(1);
  }
  public void stopShooter() {
    shooter1.set(0); 
    shooter2.set(0);
  }
  public void shooterAmp() {
    shooter1.set(-0.3);
    shooter2.set(0.3);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
