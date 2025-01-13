// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Autos;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.VictorCommand;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.VictorSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.InstantCommand;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private final IntakeSubsystem m_Intake = new IntakeSubsystem();
  private final ShooterSubsystem m_Shooter = new ShooterSubsystem();
  private final VictorSubsystem m_Victor = new VictorSubsystem();
  //private final startneoMotor1 startneoMotor1Command = new StartneoMotor1(m_intake);

  // Replace with CommandPS4Controller or CommandJoystick if needed
  //private final CommandXboxController m_driverController =
      //new CommandXboxController(OperatorConstants.kDriverControllerPort);

  public static XboxController m_driverController = new XboxController(OperatorConstants.kDriverControllerPort);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
    new Trigger(m_exampleSubsystem::exampleCondition)
        .onTrue(new ExampleCommand(m_exampleSubsystem));

    // Schedule `exampleMethodCommand` when the Xbox controller's B button is pressed,
    // cancelling on release.

    new JoystickButton(m_driverController, Button.kA.value)
                                    .onTrue(new InstantCommand(() -> m_Intake.startInatkeMotor(), m_Intake))
                                .onFalse(new InstantCommand(() -> m_Intake.stopIntakeMotor1(), m_Intake));

    new JoystickButton(m_driverController, Button.kB.value)
        .onTrue(
          new InstantCommand(() -> ShooterSubsystem.setTargetVelocity(500), m_Shooter)
        )
        .onFalse(
          new InstantCommand(() -> ShooterSubsystem.stopShooterMotor(), m_Shooter)
        );
    new JoystickButton(m_driverController, Button.kLeftBumper.value)
                                    .whileTrue(new InstantCommand(() -> m_Victor.startVictorMotor1(), m_Victor))
                                .whileFalse(new InstantCommand(() -> m_Victor.stopVictorMotor1(), m_Victor));
    
    new JoystickButton(m_driverController, Button.kRightBumper.value)
                                    .onTrue(new InstantCommand(() -> m_Victor.startVictorMotor2(), m_Victor))
                                .onFalse(new InstantCommand(() -> m_Victor.stopVictorMotor2(), m_Victor));
    
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return Autos.exampleAuto(m_exampleSubsystem);
  }
}
