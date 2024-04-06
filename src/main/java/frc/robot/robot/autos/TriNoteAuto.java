// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.robot.autos;

//import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.robot.commands.AutoSwerve;
import frc.robot.robot.commands.IndexNote;
import frc.robot.robot.commands.IntakeAngle;
import frc.robot.robot.commands.LoadIntake;
import frc.robot.robot.commands.ShootNote;
import frc.robot.robot.subsystems.IndexSubsystem;
import frc.robot.robot.subsystems.IntakeSubsystem;
import frc.robot.robot.subsystems.ShooterSubsystem;
import frc.robot.robot.subsystems.swerve.rev.RevSwerve;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class TriNoteAuto extends SequentialCommandGroup {
  /** Creates a new TriNoteAuto. */
  public TriNoteAuto(RevSwerve base, ShooterSubsystem shooter, IntakeSubsystem intake, IndexSubsystem index) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new ShootNote(shooter, 1).withTimeout(.2)
      //index waits 1 second while shooter wheels are getting to speed and then shoots a note for .1 seconds
      .alongWith(new WaitCommand(.2)),
      new ShootNote(shooter, 1).withTimeout(1)
      .alongWith(new IndexNote(index, -1).withTimeout(1)),
      new ShootNote(shooter, 0).withTimeout(0.000001),
      new IndexNote(index, 0).withTimeout(0.000001),

      new IntakeAngle(intake, 1).withTimeout(1.5)
      .alongWith(new AutoSwerve(base, 0.2, 0, 0, false).withTimeout(0.0521)),
      new LoadIntake(intake).withTimeout(.7)
      .alongWith(new IndexNote(index, -1).withTimeout(.7))
      .alongWith(new AutoSwerve(base, 0, 0, 0, false).withTimeout(0.0001)),

      new AutoSwerve(base, -0.2, 0, 0, false).withTimeout(1.4521),
      new AutoSwerve(base, 0, 0, 0, false).withTimeout(0.0001),

      new ShootNote(shooter, 1).withTimeout(.2)
      //index waits 1 second while shooter wheels are getting to speed and then shoots a note for .1 seconds
      .alongWith(new WaitCommand(.2)),
      new ShootNote(shooter, 1).withTimeout(1.7)
      .alongWith(new IndexNote(index, -1).withTimeout(1.7))
      .alongWith(new LoadIntake(intake).withTimeout(1.7)),
      new ShootNote(shooter, 0).withTimeout(0.000001),
      new IndexNote(index, 0).withTimeout(0.000001),
      new AutoSwerve(base, 0, 0, 0.4, false).withTimeout(.1),
      new AutoSwerve(base, 0.14, .33, 0, false).withTimeout(1.3411),
      new AutoSwerve(base, 0, 0, 0, false).withTimeout(.0001),

      new LoadIntake(intake).withTimeout(.7)
      .alongWith(new IndexNote(index, -1).withTimeout(.7))
      .alongWith(new AutoSwerve(base, 0.08, 0, 0, false).withTimeout(0.3)),
      new AutoSwerve(base, 0, 0, 0, false).withTimeout(.0001),


      new AutoSwerve(base, -0.18, -.3, 0, false).withTimeout(1.4421),
      new AutoSwerve(base, 0, 0, -0.4, false).withTimeout(.1),
      new AutoSwerve(base, -0.2, 0, 0, false).withTimeout(.6),
      new AutoSwerve(base, 0, 0, 0, false).withTimeout(.0001),

      new ShootNote(shooter, 1).withTimeout(.2)
      //index waits 1 second while shooter wheels are getting to speed and then shoots a note for .1 seconds
      .alongWith(new WaitCommand(.2)),
      new ShootNote(shooter, 1).withTimeout(1)
      .alongWith(new IndexNote(index, -1).withTimeout(1))
      .alongWith(new LoadIntake(intake).withTimeout(1.7)),
      new ShootNote(shooter, 0).withTimeout(0.000001),
      new IndexNote(index, 0).withTimeout(0.000001)

      //total run time = 10.1925
      //limit run time = 15.0000
    );
  }
}
