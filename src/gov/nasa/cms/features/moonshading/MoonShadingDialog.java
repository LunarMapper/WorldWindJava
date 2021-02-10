/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.nasa.cms.features.moonshading;

import gov.nasa.worldwind.WorldWindow;
import java.awt.BorderLayout;
import java.awt.Rectangle;
import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.Component;
import java.awt.Frame;
import java.util.Calendar;
import javax.swing.JFrame;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.text.DateFormatter;


/**
 *
 * @author kjdickin
 */
public class MoonShadingDialog 
{
     private JDialog dialog;
     private MoonShadingPanel moonShadingPanel;
     private WorldWindow wwd;
     private LensFlareLayer lensFlareLayer;
     

     public MoonShadingDialog(WorldWindow wwdObject, Component component)
     {
        moonShadingPanel = new MoonShadingPanel(wwdObject);
        
        this.wwd = wwdObject; 
          
        dialog = new JDialog((Frame) component);     
        Rectangle bounds = component.getBounds();
        dialog.getContentPane().setLayout(new BorderLayout());
        dialog.setTitle("Moon Shading");
        // Set the location and resizable to false
        dialog.setLocation(bounds.x, bounds.y + 60);
        dialog.setResizable(false);
        dialog.getContentPane().add(moonShadingPanel, BorderLayout.CENTER);
        dialog.pack();
        dialog.setVisible(true);
        ShowTimeJSpinner calendar = new ShowTimeJSpinner();
          
    }
     
    public void enableLensFlare(WorldWindow wwdObject)
     {
         this.wwd = wwdObject;
         lensFlareLayer = moonShadingPanel.getLensFlareLayer();
         wwd.getModel().getLayers().add(lensFlareLayer);
         
     }
    public void setVisible(boolean visible)
    {
        dialog.setVisible(visible);
    }
    
    public void resetDialog()
    {
        moonShadingPanel.resetMoonShadingProperties();
        dialog.setVisible(false);
    }
    
}
