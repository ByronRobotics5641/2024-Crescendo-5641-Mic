package frc.robot.robot.constants;

import edu.wpi.first.math.geometry.Rotation2d;
import frc.robot.lib.util.swerveUtil.RevSwerveModuleConstants;


/**
 * This file comes with command robot projects, and is intended to contain
 * configuration information.
 * I think it would be prudent if this file only contained CanIDs, because it
 * is useful to have all the ids for the whole robot in one place.
 * other configuration goes into subsystem specific configuration files,
 * to make sure this one isn't cluttered.
 */
public final class RevSwerveConstants 
{
    public static final double stickDeadband = 0.5;
    public static final double limelightOffset = 3;
    
    /* Angle Encoder Invert */
    //public static final SensorDirectionValue cancoderInvert = chosenModule.cancoderInvert;

    public static final class REV
    {
        public static final int pigeonID = 39;
                  
       
    
    }
    public static final class Swerve {
        

        /* Module Specific Constants */
    /* Front Left Module - Module 0 */
        public static final class Mod0 { 
            public static final int driveMotorID = 2;
            public static final int angleMotorID = 1;
            public static final int canCoderID = 9;
            public static final Rotation2d rotOffset = Rotation2d.fromRotations(-0.544189452986111);  //
            public static final RevSwerveModuleConstants constants = 
                new RevSwerveModuleConstants(driveMotorID, angleMotorID, canCoderID, rotOffset);
        }
        /* Front Right Module - Module 1*/
        public static final class Mod1 { 
            public static final int driveMotorID = 4;
            public static final int angleMotorID = 3;
            public static final int canCoderID = 12;
            public static final Rotation2d rotOffset = Rotation2d.fromRotations(-0.594482421875); //0.464355
            public static final RevSwerveModuleConstants constants = 
                new RevSwerveModuleConstants(driveMotorID, angleMotorID, canCoderID, rotOffset);
        }
        /* Back Left Module - Module 2 */
        public static final class Mod2 { 
            public static final int driveMotorID = 8;
            public static final int angleMotorID = 7;
            public static final int canCoderID = 11;
            public static final Rotation2d rotOffset = Rotation2d.fromRotations(-0.816650390625);//0.218262
            public static final RevSwerveModuleConstants constants = 
                new RevSwerveModuleConstants(driveMotorID, angleMotorID, canCoderID, rotOffset);
        }
         /* Back Right Module - Module 3 */
        public static final class Mod3 { 
            public static final int driveMotorID = 6;
            public static final int angleMotorID = 5;
            public static final int canCoderID = 10;
            public static final Rotation2d rotOffset = Rotation2d.fromRotations(-0.184275);//-0.220459
            public static final RevSwerveModuleConstants constants = 
                new RevSwerveModuleConstants(driveMotorID, angleMotorID, canCoderID, rotOffset);
        }
        
    }

}