// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.robot.subsystems.ShooterSubsystem;

public class ShootNote extends Command {

  double speed;
  ShooterSubsystem shooter;

  /** Creates a new ShootNote. */
  public ShootNote(ShooterSubsystem shooter, double speed) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.shooter = shooter;
    addRequirements(shooter);

    this.speed = speed;  
  }

  public ShootNote(ShooterSubsystem shooter, DoubleSupplier speedsSupplier) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.shooter = shooter;
    addRequirements(shooter);

    this.speed = speedsSupplier.getAsDouble();
    
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
      shooter.shooterDriver(speed);
      }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    shooter.shooterDriver(speed);
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
      shooter.shooterDriver(0);
      }

  // Returns true when the command should end.
 /*  @Override
  public boolean isFinished() {
    return true;
  }*/
}
