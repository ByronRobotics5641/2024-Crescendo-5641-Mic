// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.robot.autos;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.robot.commands.AutoSwerve;
import frc.robot.robot.commands.ShootNote;
import frc.robot.robot.subsystems.IntakeSubsystem;
import frc.robot.robot.subsystems.ShooterSubsystem;
import frc.robot.robot.subsystems.swerve.rev.RevSwerve;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class DiagShootPickupAutoB extends SequentialCommandGroup {
  /** Creates a new DiagShootPickupAutoB. */
  public DiagShootPickupAutoB(RevSwerve drive, ShooterSubsystem shooter, IntakeSubsystem intake) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
            //shooter wheels run for one second
            new ShootNote(shooter, 1).withTimeout(1),
            //index waits 1 second while shooter wheels are getting to speed and then shoots a note for .1 seconds
            new WaitCommand(1),
            new ShootNote(shooter, 0).withTimeout(.1),
        
            //position robot to middle from right of speaker
            new AutoSwerve(drive, 0.2, 0.2, 0.007, false).withTimeout(2.22441),
            new AutoSwerve(drive, 0, 0, 0, false).withTimeout(0.1)
            
      
    );
  }
}
