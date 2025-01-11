package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkLowLevel.MotorType;
import frc.robot.Constants;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.SparkPIDController;
import com.revrobotics.CANSparkBase.ControlType;

// This Subsystem will act as a Shooter Subsystem with Volatge Compensation

public class ShooterSubsystem {

    private final CANSparkMax ShooterMotor;
    private final SparkPIDController pidController;
    private final double kP = 0.1 , kI = 0.0 , kD= 0.0 , kF = 0.0002;
    //Tune values later
    private final double targetVelocityRPM = 4500.0; 
    //Make desired RPM

    //private static ShooterSubsystem instance;

    public ShooterSubsystem() {
        ShooterMotor = new CANSparkMax(3, MotorType.kBrushless);
        ShooterMotor.setSmartCurrentLimit(30);
        ShooterMotor.enableVoltageCompensation(12.0);
    

    // Initialize motor
    CANSparkMax ShooterMotor = new CANSparkMax(Constants.ShooterConstants.kShooterMotorCanId, MotorType.kBrushless);
    RelativeEncoder ShooterEncoder = ShooterMotor.getEncoder();
    
   

    //private float neospeed = 0.30f;

   // public ShooterSubsystem(){
      ShooterMotor.restoreFactoryDefaults();
      ShooterMotor.setInverted(false);
      ShooterMotor.setIdleMode(Constants.ShooterConstants.kShooterIdleMode);
     // ShooterMotor.setSmartCurrentLimit(30);
      //ShooterMotor.enableVoltageCompensation(12.0);
      ShooterMotor.burnFlash();

      //ShooterEncoder.setPosition(0);

    
  
    //PID setup
    pidController = ShooterMotor.getPIDController();
    pidController.setP(kP);
    pidController.setI(kI);
    pidController.setD(kD);
    pidController.setFF(kF);
    //FF stands for FreeForward this helps maintain the speed
    }
  // Spin ShooterMotor forward
  public void startShooterMotor(){
    pidController.setReference(targetVelocityRPM, ControlType.kVelocity);
    //Maintain target RPM
  }


  // Stop ShooterMotor
  public void stopShooterMotor(){
    ShooterMotor.set(0.0);
  }


 //Return Output Current
  public double getShooterMotorRPM(){
    return ShooterMotor.getEncoder().getVelocity();
  }


  /**
   * Command factory method.
   *
   * @return a command
   */
  //public Command methodCommand() {
    // Inline construction of command goes here.
    //Subsystem::RunOnce implicitly requires `this` subsystem.
    //return runOnce(
        //() -> {
        /* one-time action goes here */
        //});


  /**
   * An example method querying a boolean state of the subsystem (for example, a digital sensor).
   *
   * @return value of some boolean subsystem state, such as a digital sensor.
   */
  //public boolean Condition() {
    // Query some boolean state, such as a digital sensor.
    //return false;
  //}

  //@Override
  //public void periodic() {
    // This method will be called once per scheduler run
  //}


  //@Override
  //public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }



