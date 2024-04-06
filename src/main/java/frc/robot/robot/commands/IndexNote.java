// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.robot.subsystems.IndexSubsystem;

public class IndexNote extends Command {
  /** Creates a new IndexNote. */
  double speed;
  IndexSubsystem index;

  public IndexNote(IndexSubsystem index, double speed) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.index = index;
    addRequirements(index);

    this.speed = speed;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    index.indexDriver(speed);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    index.indexDriver(speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    index.indexDriver(0);
  }

  // Returns true when the command should end.
  /*@Override
  public boolean isFinished() {
    return true;
  }*/
}
