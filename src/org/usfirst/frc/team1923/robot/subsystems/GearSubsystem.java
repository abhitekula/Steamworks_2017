package org.usfirst.frc.team1923.robot.subsystems;

import org.usfirst.frc.team1923.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

public class GearSubsystem extends Subsystem {

    public DoubleSolenoid slider;
    public DoubleSolenoid gear;

    public boolean gearShifted;
    public boolean slideShifted;

    public GearSubsystem() {
        slider = new DoubleSolenoid(RobotMap.PCM_MODULE_NUM, RobotMap.SLIDE_FORWARD_PORT, RobotMap.SLIDE_BACKWARD_PORT);
        gear = new DoubleSolenoid(RobotMap.PCM_MODULE_NUM, RobotMap.MECH_FORWARD_PORT, RobotMap.MECH_BACKWARD_PORT);
        goHome();
    }

    @Override
    public void initDefaultCommand() {

    }

    public void slideShift() {
        if (slideShifted) {
            slideReverse();
        } else {
            slideForward();
        }
    }

    public void slideReverse() {
        if (slider.get() != Value.kForward) {
            slider.set(Value.kForward);
        }

        slideShifted = false;
    }

    public void slideForward() {
        if (slider.get() != Value.kReverse) {
            slider.set(Value.kReverse);
        }

        slideShifted = true;
    }

    public void gearShift() {
        if (gearShifted) {
            gearClose();
        } else {
            gearOpen();
        }
    }

    public void gearClose() {
        if (gear.get() != Value.kForward) {
            gear.set(Value.kForward);
        }

        gearShifted = false;
    }

    public void gearOpen() {
        if (gear.get() != Value.kReverse) {
            gear.set(Value.kReverse);
        }

        gearShifted = true;
    }

    public void goHome() {
        slideReverse();
        gearClose();
    }

}
