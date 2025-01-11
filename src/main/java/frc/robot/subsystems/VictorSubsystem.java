// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import frc.robot.Constants;
import edu.wpi.first.wpilibj.motorcontrol.Victor;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.PowerDistribution;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.fasterxml.jackson.annotation.JsonFormat.Features;
import com.ctre.phoenix.motorcontrol.ControlMode; 

public class VictorSubsystem extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
  private final VictorSPX VictorMotor1 = new VictorSPX(2);  // CAN ID 2
  private final VictorSPX VictorMotor2 = new VictorSPX(3); // CAN ID 3

  private final PowerDistribution m_powerDistribution = new PowerDistribution();  // PDP/PDH for current sensing

    private static final int MAX_CURRENT = 30;  // Maximum allowable current in amps

    public VictorSubsystem() {
        // Set motors to factory defaults (optional but recommended)
        VictorMotor1.configFactoryDefault();
        VictorMotor2.configFactoryDefault();

        // Reverse one motor if needed (e.g., for opposite intake directions)
       // m_rightIntakeMotor.setInverted(true);
    }

    public void runIntake(double speed) {
        // Get current draw from PDP or PDH
        double VictorMotor1 = m_powerDistribution.getCurrent(2); // PDP/PDH channel for the left motor
        double VictorMotor2 = m_powerDistribution.getCurrent(3); // PDP/PDH channel for the right motor

        // Check if current exceeds the limit
        if (VictorMotor1 < MAX_CURRENT && VictorMotor2 < MAX_CURRENT) {
            // Safe to run motors at the desired speed
            VictorMotor1.set(ControlMode.PercentOutput, speed);
            VictorMotor2.set(ControlMode.PercentOutput, speed);
        } else {
            // Stop motors if over current
            VictorMotor1.set(ControlMode.PercentOutput, 0);
            VictorMotor2.set(ControlMode.PercentOutput, 0);
        }
    }

    public void stopIntake() {
        // Stop the intake motors
        VictorMotor1.set(ControlMode.PercentOutput, 0);
        VictorMotor2.set(ControlMode.PercentOutput, 0);
    }

    @Override
    public void periodic() {
        // Optionally, log current values for debugging
        System.out.println("VictorMotor1 Current: " + m_powerDistribution.getCurrent(2));
        System.out.println("VictorMotor2 Current: " + m_powerDistribution.getCurrent(3));
    }

    public Object startVictorMotor1() {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'startVictorMotor1'");
    }

    public Object stopVictorMotor1() {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'stopVictorMotor1'");
    }
}
