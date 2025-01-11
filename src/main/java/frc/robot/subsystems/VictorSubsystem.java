// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class VictorSubsystem extends SubsystemBase {

    // Victor SPX motor controller
    private final VictorSPX VictorMotor1 = new VictorSPX(Constants.);
     private final VictorSPX VictorMotor2 = new VictorSPX(Constants.);
     

    // Power Distribution panel for current monitoring
    private final PowerDistribution m_powerDistribution = new PowerDistribution();

    public VictorSubsystem() {
        // Reset motor to factory defaults
        VictorMotor1.configFactoryDefault();
        VictorMotor2.configFactoryDefault();

        // Optional: Configure additional motor settings here
        // Example: m_motor.setInverted(true);
    }

    /**
     * Sets the motor speed as a percentage of its output.
     *
     * @param speed Speed value between -1.0 and 1.0.
     */

    public void startVictorMotor1() {
      setVictorMotor1Speed(0.03); 
    }

    public void startVictorMotor2() {
      setVictorMotor2Speed(0.03); 
    }

     // Stops the motor by setting its output to zero.
  
    public void stopVictorMotor1() {
        setVictorMotor1Speed(0);
    }

    
    public void stopVictorMotor2() {
        setVictorMotor2Speed(0);
    }

   // @Override
    //public void periodic() {
        // Monitor motor current draw and display on SmartDashboard
        //double motorCurrent = m_powerDistribution.getCurrent(Constants.MOTOR_PDP_CHANNEL);
       // SmartDashboard.putNumber("Victor Motor Current", motorCurrent);

        // Optional: Add additional periodic logic here
   // }
}
