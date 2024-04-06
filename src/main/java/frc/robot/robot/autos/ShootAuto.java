// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.robot.autos;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.robot.commands.IndexNote;
import frc.robot.robot.commands.ShootNote;
import frc.robot.robot.subsystems.IndexSubsystem;
import frc.robot.robot.subsystems.ShooterSubsystem;
import frc.robot.robot.subsystems.swerve.rev.RevSwerve;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class ShootAuto extends SequentialCommandGroup {
  public ShootAuto(RevSwerve base, ShooterSubsystem shooter, IndexSubsystem index) {

  /** Creates a new ShootAuto. */
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
            //shooter wheels run for one second
      new ShootNote(shooter, 1).withTimeout(2.5)
      //index waits 1 second while shooter wheels are getting to speed and then shoots a note for .1 seconds
      .alongWith(new WaitCommand(2.5)),
      new ShootNote(shooter, 1).withTimeout(1)
      .alongWith(new IndexNote(index, -1).withTimeout(1)),
      new ShootNote(shooter, 0).withTimeout(0.000001),
      new IndexNote(index, 0).withTimeout(0.000001)
    );
  }
}

