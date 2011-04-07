/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modbuspal.main;

import modbuspal.automation.Automation;
import modbuspal.slave.ModbusSlave;

/**
 * An object interested in ModbusPal related events should implement
 * this interface.
 * @author nnovic
 */
public interface ModbusPalListener
{
    public void modbusSlaveAdded(ModbusSlave slave);

    public void modbusSlaveRemoved(ModbusSlave slave);

    public void automationAdded(Automation automation, int index);

    public void automationRemoved(Automation automation);

    public void pduProcessed();

    public void pduException();

    public void pduNotServiced();
}
