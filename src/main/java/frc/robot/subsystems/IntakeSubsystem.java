// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkLowLevel.MotorType;
import frc.robot.Constants;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkBase.ControlType;

//This SubSystem will act as an intake SubSytem with Current Limiting

public class IntakeSubsystem extends SubsystemBase {
  /** Creates a new subsystem. */
  
  private static IntakeSubsystem instance;
  
    // Initialize motor
    private CANSparkMax IntakeMotor = new CANSparkMax(Constants.IntakeConstants.kIntakeMotorCanId, MotorType.kBrushless);
    private RelativeEncoder IntakeEncoder = IntakeMotor.getEncoder();



    private float neospeed = 0.30f;

    public IntakeSubsystem(){
      IntakeMotor.restoreFactoryDefaults();
      IntakeMotor.setInverted(false);
      IntakeMotor.setIdleMode(Constants.IntakeConstants.kIntakeIdleMode);
      IntakeMotor.setSmartCurrentLimit(30);
      IntakeMotor.enableVoltageCompensation(12.0);
      IntakeMotor.burnFlash();

      IntakeEncoder.setPosition(0);

    }
  


  // Spin IntakeMotor forward
  public void startInatkeMotor(){
    IntakeMotor.set(neospeed);
  }


  // Stop IntakeMotor
  public void stopIntakeMotor1(){
    IntakeMotor.set(0.0);
  }


 //Return Output Current
  public double getneoMotor1Current(){
    return IntakeMotor.getOutputCurrent();
  }

  /**
   * Command factory method.
   *
   * @return a command
   */
  public Command methodCommand() {
    // Inline construction of command goes here.
    // Subsystem::RunOnce implicitly requires `this` subsystem.
    return runOnce(
        () -> {
          /* one-time action goes here */
        });
  }

  /**
   * An example method querying a boolean state of the subsystem (for example, a digital sensor).
   *
   * @return value of some boolean subsystem state, such as a digital sensor.
   */
  public boolean Condition() {
    // Query some boolean state, such as a digital sensor.
    return false;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}