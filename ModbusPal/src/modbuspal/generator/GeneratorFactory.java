/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modbuspal.generator;

import modbuspal.instanciator.Instanciator;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import modbuspal.instanciator.ClassInstanciator;
import modbuspal.instanciator.InstanciatorManager;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;


/**
 *
 * @author nnovic
 */
public class GeneratorFactory
extends InstanciatorManager
{

    public GeneratorFactory()
    {
        add(modbuspal.generator.linear.LinearGenerator.class);
        add(modbuspal.generator.random.RandomGenerator.class);
        add(modbuspal.generator.sine.SineGenerator.class);
    }


    public Generator newGenerator(String classname)
    throws InstantiationException, IllegalAccessException
    {
        Instanciator is = getInstanciator(classname);
        return is.newGenerator();
    }


    @Override
    public void load(Document doc, File projectFile)
    {
        NodeList list = doc.getElementsByTagName("generators");
        loadInstanciators(list,projectFile);
    }



    @Override
    public void save(OutputStream out, File projectFile)
    throws IOException
    {
        if( scriptedInstanciators.isEmpty() )
        {
            return;
        }
        
        String openTag = "<generators>\r\n";
        out.write( openTag.getBytes() );

        for( Instanciator gi:scriptedInstanciators)
        {
            gi.save(out,projectFile);
        }

        String closeTag = "</generators>\r\n";
        out.write( closeTag.getBytes() );
    }

}
