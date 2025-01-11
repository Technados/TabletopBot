// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.VictorSubsystem;

public class VictorCommand extends Command {
    private final IntakeSubsystem m_intakeSubsystem;
    private final double m_speed;

    public VictorCommand(VictorSubsystem VictorSubsystem, double speed) {
        m_intakeSubsystem = intakeSubsystem;
        m_speed = speed;

        addRequirements(m_intakeSubsystem);  // Declare subsystem dependencies
    }

    @Override
    public void execute() {
        m_intakeSubsystem.runIntake(m_speed);
    }

    @Override
    public void end(boolean interrupted) {
        m_intakeSubsystem.stopIntake();
    }

    @Override
    public boolean isFinished() {
        return false;  // Run until interrupted
    }
}
