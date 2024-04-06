// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IndexSubsystem extends SubsystemBase {
  /** Creates a new IndexSubsystem. */
  public IndexSubsystem() {}
  CANSparkMax index1 = new CANSparkMax(15,MotorType.kBrushless);
  CANSparkMax index2 = new CANSparkMax(19, MotorType.kBrushless);

  public void indexDriver (double speed)
  {
    index1.set(speed);
    index2.set(speed);
  }
  public void m_slowIndex(){
    index1.set(-0.15);
    index2.set(-0.15);
  }
  public void startIndex() {
    index1.set(-.8);
    index2.set(-.7);
  }
  public void reverseIndex() {
    index1.set(.8);
    index2.set(.7);
  }
  public void stopIndex() {
    index1.set(0);
    index2.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
