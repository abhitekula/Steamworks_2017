package org.usfirst.frc.team1923.robot;

import org.usfirst.frc.team1923.robot.commands.drive.ResetEncoderCommand;
import org.usfirst.frc.team1923.robot.commands.drive.ShiftCommand;
import org.usfirst.frc.team1923.robot.commands.drive.ShiftOmniCommand;
import org.usfirst.frc.team1923.robot.commands.gear.GearCommand;
import org.usfirst.frc.team1923.robot.commands.gear.SlideCommand;
import org.usfirst.frc.team1923.robot.commands.shooter.IndexerOffCommand;
import org.usfirst.frc.team1923.robot.commands.shooter.IndexerOnCommand;
import org.usfirst.frc.team1923.robot.commands.shooter.ShooterSpinUpCommand;
import org.usfirst.frc.team1923.robot.commands.vision.VisionGearAlignCommand;
import org.usfirst.frc.team1923.robot.commands.vision.VisionProcessing;
import org.usfirst.frc.team1923.robot.utils.controller.PS4Controller;
import org.usfirst.frc.team1923.robot.utils.controller.XboxController;

import edu.wpi.first.wpilibj.command.Command;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

    public PS4Controller driver;
    public XboxController op;

    public OI() {
        // Creates two ps4 controllers
        this.driver = new PS4Controller(RobotMap.DRIVER_CONTROLLER_PORT);
        this.driver.lt.setTriggerSensitivity(0.5);
        this.driver.rt.setTriggerSensitivity(0.5);

        this.driver.lb.whenActive(new ShiftCommand(false));
        this.driver.rb.whenActive(new ShiftCommand(true));

        this.driver.lt.whenActive(new ShiftOmniCommand(true));
        this.driver.rt.whenActive(new ShiftOmniCommand(false));

        this.driver.cross.whenActive(new ResetEncoderCommand());

        this.op = new XboxController(RobotMap.OP_CONTROLLER_PORT);

        this.op.x.whenActive(new SlideCommand());
        this.op.y.whenActive(new GearCommand());
        // this.op.b.whenActive(new GearSetHomeCommand());
        this.op.rb.whenActive(new ShooterSpinUpCommand(8620));// 10100
        this.op.lb.whenActive(new ShooterSpinUpCommand(0));
        this.op.a.whileHeld(new IndexerOnCommand());
        this.op.b.whenActive(new IndexerOffCommand());

        // Vision Commands
        Command pegAlign = new VisionGearAlignCommand(false);
        driver.square.whileHeld(pegAlign);
        Command feederAlign = new VisionGearAlignCommand(true);
        driver.triangle.whileHeld(feederAlign);
        Command refresh = new VisionProcessing();
        driver.circle.whileHeld(refresh);
    }
}
