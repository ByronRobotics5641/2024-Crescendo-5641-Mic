// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

//import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeSubsystem extends SubsystemBase {
  /** Creates a new IntakeSubsystem. */
  public IntakeSubsystem() {}
  CANSparkMax angle1 = new CANSparkMax(16, MotorType.kBrushless);//right
  CANSparkMax angle2 = new CANSparkMax(17, MotorType.kBrushless);//left
  CANSparkMax intake = new CANSparkMax(18, MotorType.kBrushless);

  //DigitalInput angleLimit = new DigitalInput(2);
  public void startIntake() {
    intake.set(.9);
  }
  public void stopIntake() {
    intake.set(0);
  }
  public void reverseIntake() {
    intake.set(-.9);
  }
  public void driveAngle(double leftStickvalue, boolean slowButton) {
   
    if (slowButton){
      /*if(leftStickvalue < 0 && angleLimit.get()) {
        angle1.set(leftStickvalue * 0);
        angle2.set(leftStickvalue * 0);    
      }
      else */if(leftStickvalue < 0 && slowButton) {
        angle1.set(-leftStickvalue * 0.085);
        angle2.set(leftStickvalue * 0.085);
      }
      else{
        angle1.set(-leftStickvalue * 0.06);
        angle2.set(leftStickvalue * 0.06);
      }
    }

    else{
      /*if(leftStickvalue < 0 && angleLimit.get()) {
        angle1.set(leftStickvalue * 0);
        angle2.set(leftStickvalue * 0);
      }
      else */if(leftStickvalue < 0) {
        angle1.set(-leftStickvalue * 0.3);
        angle2.set(leftStickvalue * 0.3);
      }
      else{
        angle1.set(-leftStickvalue * 0.18);
        angle2.set(leftStickvalue * 0.18);
      }
    }
    
  }


  public void holdAngle() {
    angle1.set(0.15);
    angle2.set(-0.15);
  }
  

  public void angleUp() {

  }
  public void angleDown() {

  }
  public void angleAmp() {
    
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
