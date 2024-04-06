package frc.robot;

//import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
//import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants.OperatorConstants;
import frc.robot.robot.autos.ShootAuto;
//import frc.robot.robot.autos.ShootTaxiIntake;
import frc.robot.robot.autos.TaxiAuto;
import frc.robot.robot.autos.TaxiIntake;
import frc.robot.robot.autos.TaxiShootAuto;
import frc.robot.robot.autos.TriNoteAuto;
import frc.robot.robot.autos.DiagShootPickupAutoR;
import frc.robot.robot.autos.DiagShootPickupAutoB;
import frc.robot.robot.autos.NoGo;
import frc.robot.robot.commands.*;
import frc.robot.robot.subsystems.IndexSubsystem;
import frc.robot.robot.subsystems.IntakeSubsystem;
import frc.robot.robot.subsystems.LightsSubsystem;
import frc.robot.robot.subsystems.ShooterSubsystem;
import frc.robot.robot.subsystems.swerve.rev.RevSwerve;
import frc.robot.robot.subsystems.swerve.rev.RevSwerveConfig;
import edu.wpi.first.cameraserver.CameraServer;




/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {

    private static SendableChooser<Command> m_chooser;

    /* Controllers */
    private final Joystick driver = new Joystick(0);
    private final Joystick manip = new Joystick(1);

    private final CommandXboxController m_manipController =
        new CommandXboxController(OperatorConstants.kManipControllerPort);

    /* Driver Buttons */
    private final JoystickButton zeroGyro = new JoystickButton(driver, 4); //Y zeros the gyro

    /* Subsystems */
    private final RevSwerve s_Swerve = new RevSwerve();

    private final IndexSubsystem indexSubsystem = new IndexSubsystem();
    private final ShooterSubsystem shooterSubsystem = new ShooterSubsystem();
    private final IntakeSubsystem intakeSubsystem = new IntakeSubsystem();
    private final LightsSubsystem lightsSubsystem = new LightsSubsystem();
    

    /*DigitalInput shooterLimit = new DigitalInput(0);
    Trigger ShooterLimit = new Trigger(shooterLimit::get);
    DigitalInput intakeLimit = new DigitalInput(1);
    Trigger IntakeLimit = new Trigger(intakeLimit::get);*/

    POVButton angleUp = new POVButton(manip, 0);
    POVButton angleDown = new POVButton(manip, 180);
    POVButton angleAmp = new POVButton(manip, 90);


    private final Command m_loadShooter = Commands.runOnce(shooterSubsystem::loadShooter, shooterSubsystem);
    private final Command m_launchShooter = Commands.runOnce(shooterSubsystem::launchShooter, shooterSubsystem);
    private final Command m_stopShooter = Commands.runOnce(shooterSubsystem::stopShooter, shooterSubsystem);
    private final Command m_shooterAmp = Commands.runOnce(shooterSubsystem::shooterAmp, shooterSubsystem);
    private final Command m_startIndex = Commands.runOnce(indexSubsystem::startIndex, indexSubsystem);
    private final Command m_reverseIndex = Commands.runOnce(indexSubsystem::reverseIndex, indexSubsystem);
    private final Command m_stopIndex = Commands.runOnce(indexSubsystem::stopIndex, indexSubsystem); 

    private final Command m_startIntake = Commands.runOnce(intakeSubsystem::startIntake, intakeSubsystem);
    private final Command m_stopIntake = Commands.runOnce(intakeSubsystem::stopIntake, intakeSubsystem);
    private final Command m_reverseIntake = Commands.runOnce(intakeSubsystem::reverseIntake, intakeSubsystem);
    //private final Command m_holdAngle = Commands.runOnce(intakeSubsystem::holdAngle, intakeSubsystem);
    private final Command m_angleUp = Commands.runOnce(intakeSubsystem::angleUp, intakeSubsystem);
    private final Command m_angleDown = Commands.runOnce(intakeSubsystem::angleDown, intakeSubsystem);
    private final Command m_angleAmp = Commands.runOnce(intakeSubsystem::angleAmp, intakeSubsystem);
    private final Command m_slowIndex = Commands.runOnce(indexSubsystem::m_slowIndex, indexSubsystem);

    //private final Command m_setPurple = Commands.runOnce(lightsSubsystem::setPurple, lightsSubsystem);
    private final Command m_setAllianceColor = Commands.runOnce(lightsSubsystem::setAllianceColor, lightsSubsystem);
    //private final Command m_setYellow = Commands.runOnce(lightsSubsystem::setYellow, lightsSubsystem);



/*********************
 *auto routines here *
 *********************/
    private final Command m_taxiShootAuto = new TaxiShootAuto(s_Swerve, shooterSubsystem, indexSubsystem);
    private final Command m_taxiAuto = new TaxiAuto(s_Swerve);
    private final Command m_intakeAuto = new TaxiIntake(s_Swerve, intakeSubsystem);
    //private final Command m_shootPickupAuto = new ShootTaxiIntake(s_Swerve, shooterSubsystem, intakeSubsystem);
    private final Command m_shootAuto = new ShootAuto(s_Swerve, shooterSubsystem, indexSubsystem);
    private final Command m_diagShootPickupAutoR = new DiagShootPickupAutoR(s_Swerve, shooterSubsystem, intakeSubsystem);
    private final Command m_diagShootPickupAutoB = new DiagShootPickupAutoB(s_Swerve, shooterSubsystem, intakeSubsystem);
    private final Command m_noGo = new NoGo(s_Swerve);
    private final Command m_trinoteauto = new TriNoteAuto(s_Swerve, shooterSubsystem, intakeSubsystem, indexSubsystem);

    // Replace with CommandPS4Controller or CommandJoystick if needed

    //driver controller has been created earlier in the code...

    private final Command m_dirveAxis = Commands.run(() -> intakeSubsystem.driveAngle(m_manipController.getLeftY(), manip.getRawButton(9)), intakeSubsystem);



    /** The container for the robot. Contains subsystems, OI devices, and commands. */
    public RobotContainer() {

     m_chooser = new SendableChooser<>();

    m_chooser.setDefaultOption("Shoot and Drive", m_taxiShootAuto);
    m_chooser.addOption("Drive", m_taxiAuto);
    m_chooser.addOption("Drive and Intake", m_intakeAuto);
    //m_chooser.addOption("Shoot Drive Pickup", m_shootPickupAuto);
    m_chooser.addOption("Shoot", m_shootAuto);
    m_chooser.addOption("Diag Left Shoot", m_diagShootPickupAutoR);
    m_chooser.addOption("Diag Right Shoot", m_diagShootPickupAutoB);
    m_chooser.addOption("Nothing", m_noGo);
    m_chooser.addOption("Three Note Shoot", m_trinoteauto);

    SmartDashboard.putData("Auto Chooser", m_chooser);
    //SmartDashboard.putBoolean("Intake", IntakeLimit.getAsBoolean());
    //SmartDashboard.putBoolean("Shooter", ShooterLimit.getAsBoolean());

        s_Swerve.setDefaultCommand(
            new TeleopSwerve(
                s_Swerve, 
                () -> -driver.getRawAxis(1)*RevSwerveConfig.maxSpeed, //forward back
                () -> -driver.getRawAxis(0)*RevSwerveConfig.maxSpeed, //sidetoside
                () -> -driver.getRawAxis(4)*RevSwerveConfig.maxSpeed, //rotation
                () -> false,
                () -> driver.getRawButton(6)
            )
        );

        intakeSubsystem.setDefaultCommand(m_dirveAxis);
        lightsSubsystem.setDefaultCommand(m_setAllianceColor);

    CameraServer.startAutomaticCapture(0);
    CameraServer.startAutomaticCapture(1);


        // Configure the button bindings
        configureButtonBindings(

        );
    }


    /**
     * Use this method to define your button->command mappings. Buttons can be created by
     * instantiating a {@link GenericHID} or one of its subclasses ({@link
     * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
     * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
     */
    private void configureButtonBindings() {
        /* Driver Buttons */
        zeroGyro.onTrue(new InstantCommand(() -> s_Swerve.zeroGyro()));
    
        //shooter
        //ShooterLimit.onTrue(m_setAllianceColor).onFalse(m_setAllianceColor);
        //ShooterLimit.onTrue(m_setAllianceColor).onFalse(m_setAllianceColor);

        //intake
        angleUp.whileTrue(m_angleUp);
        angleDown.whileTrue(m_angleDown);
        angleAmp.whileTrue(m_angleAmp);
        

        //shooter
        m_manipController.rightTrigger().whileTrue(m_loadShooter).onFalse(m_stopShooter);
        m_manipController.rightBumper().whileTrue(m_launchShooter).onFalse(m_stopShooter);
        m_manipController.leftTrigger().whileTrue(m_reverseIndex).onFalse(m_stopIndex);
        m_manipController.leftBumper().whileTrue(m_startIndex).onFalse(m_stopIndex);
        m_manipController.y().onTrue(m_shooterAmp).onFalse(m_stopShooter);
        //intake
        m_manipController.x().whileTrue(m_startIntake).onFalse(m_stopIntake);
        m_manipController.a().whileTrue(m_reverseIntake).onFalse(m_stopIntake);
        m_manipController.b().whileTrue(m_slowIndex).onFalse(m_stopIndex);
    }



    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {

        return m_chooser.getSelected();

    }
}
