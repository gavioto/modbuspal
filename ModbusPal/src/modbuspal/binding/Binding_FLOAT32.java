/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modbuspal.binding;

import modbuspal.automation.Automation;

/**
 *
 * @author avincon
 */
public class Binding_FLOAT32
extends Binding
{

    public Binding_FLOAT32()
    {
        super();
    }

    @Override
    public int getRegister()
    {
        // get current value and cast it as an int
        float conv = (float)automation.getCurrentValue();
        int value = Float.floatToRawIntBits(conv);
        
        // return "less significant word" of the int
        if( order == 0 )
        {
            return (value << 16) >> 16;
        }

        // return "most significant word" of the int
        else if( order == 1 )
        {
            return (value >> 16 );
        }

        // else, return 0 if value is positive, or FFFF if value is negative
        else
        {
            if( value >= 0 )
            {
                return 0x0000;
            }
            else
            {
                return 0xFFFF;
            }
        }
    }

    @Override
    public boolean getCoil()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
