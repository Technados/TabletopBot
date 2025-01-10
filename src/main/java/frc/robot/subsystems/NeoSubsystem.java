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
import com.revrobotics.CANSparkMax.ControlType;

public class NeoSubsystem extends SubsystemBase {
  /** Creates a new subsystem. */
  
  private static NeoSubsystem instance;
  
    // Initialize motor
    private CANSparkMax neoMotor1 = new CANSparkMax(Constants.NeoConstants.kNeoMotor1CanId, MotorType.kBrushless);
    private RelativeEncoder neoEncoder1 = neoMotor1.getEncoder();

    private CANSparkMax neoMotor2 = new CANSparkMax(Constants.NeoConstants.kNeoMotor2CanId, MotorType.kBrushless);
    private RelativeEncoder neoEncoder2 = neoMotor2.getEncoder();

    private float neospeed = 0.30f;

    public NeoSubsystem(){
      neoMotor1.restoreFactoryDefaults();
      neoMotor1.setInverted(false);
      neoMotor1.setIdleMode(Constants.NeoConstants.kNeoIdleMode);
      neoMotor1.setSmartCurrentLimit(30);
      neoMotor1.enableVoltageCompensation(12.0);
      neoMotor1.burnFlash();

      neoEncoder1.setPosition(0);

      neoMotor2.restoreFactoryDefaults();
      neoMotor2.setInverted(false);
      neoMotor2.setIdleMode(Constants.NeoConstants.kNeoIdleMode);
      neoMotor2.setSmartCurrentLimit(30);
      neoMotor2.enableVoltageCompensation(12.0);
      neoMotor2.burnFlash();

      neoEncoder2.setPosition(0);
    }
  
  // Method to set motor velocity directly
  public void setVelocity(double velocity) {
    // Set the motor velocity in RPM using ControlType.kVelocity
    neoMotor1.set(ControlType.kVelocity, velocity);  // velocity in RPM
}

  // Spin motor1 forward
  public void startneoMotor1(){
    neoMotor1.set(neospeed);
  }


  // Spin motor2 forward
  public void startneoMotor2(){
    neoMotor2.set(neospeed);
  }

  // Stop Neo motor1
  public void stopneoMotor1(){
    neoMotor1.set(0.0);
  }

  //Stop Neo motor2
  public void stopneoMotor2(){
    neoMotor2.set(0.0);
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