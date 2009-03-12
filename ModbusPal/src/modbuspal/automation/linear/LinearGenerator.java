/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modbuspal.automation.linear;

import java.io.IOException;
import java.io.OutputStream;
import javax.swing.JPanel;
import modbuspal.automation.*;
import modbuspal.main.XMLTools;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author avincon
 */
public class LinearGenerator
extends Generator
{
    private LinearControlPanel panel;
    double startValue = 0.0;
    double endValue = 0.0;
    boolean relativeStart = false;
    boolean relativeEnd = false;

    public LinearGenerator()
    {
        super("LinearGenerator.png");
        panel = new LinearControlPanel(this);
    }

    public LinearGenerator(NamedNodeMap attributes)
    {
        super("LinearGenerator.png",attributes);
        panel = new LinearControlPanel(this);
    }

    @Override
    public String getGeneratorName()
    {
        return "Linear";
    }

    @Override
    public JPanel getPanel()
    {
        return panel;
    }

    @Override
    public double getValue(double time)
    {
        double y1 = startValue;
        if( relativeStart == true )
        {
            y1 += initialValue;
        }

        double y2 = endValue;
        if( relativeEnd == true )
        {
            y2 += y1;
        }

        return y1 + time * (y2-y1) / duration;
    }

    @Override
    protected void saveGenerator(OutputStream out)
    throws IOException
    {
        StringBuffer start = new StringBuffer("<start");
        start.append(" value=\""+String.valueOf(startValue)+"\"");
        start.append(" relative=\""+Boolean.toString(relativeStart)+"\"");
        start.append("/>\r\n");
        out.write( start.toString().getBytes() );

        StringBuffer end = new StringBuffer("<end");
        end.append(" value=\""+String.valueOf(endValue)+"\"");
        end.append(" relative=\""+Boolean.toString(relativeEnd)+"\"");
        end.append("/>\r\n");
        out.write( end.toString().getBytes() );
    }

    @Override
    public void load(NodeList childNodes)
    {
        Node startNode = XMLTools.getNode(childNodes, "start");
        loadStart(startNode);

        Node endNode = XMLTools.getNode(childNodes, "end");
        loadEnd(endNode);
    }

    private void loadEnd(Node node)
    {
        // read attributes from xml document
        String endVal = XMLTools.getAttribute("value", node);
        String endRel = XMLTools.getAttribute("relative", node);

        // setup generator's values
        endValue = Double.parseDouble(endVal);
        relativeEnd = Boolean.parseBoolean(endRel);

        // update generator's panel
        panel.endTextField.setText( String.valueOf(endVal) );
        panel.endRelativeCheckBox.setSelected(relativeEnd);
    }

    private void loadStart(Node node)
    {
        // read attributes from xml document
        String startVal = XMLTools.getAttribute("value", node);
        String startRel = XMLTools.getAttribute("relative", node);

        // setup generator's values
        startValue = Double.parseDouble(startVal);
        relativeStart = Boolean.parseBoolean(startRel);
        
        // update generator's panel
        panel.startTextField.setText( String.valueOf(startValue) );
        panel.startRelativeCheckBox.setSelected(relativeStart);
    }

}
