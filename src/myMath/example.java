package myMath;

/*
 * GRAL: GRAphing Library for Java(R)
 *
 * (C) Copyright 2009-2018 Erich Seifert <dev[at]erichseifert.de>,
 * Michael Seifert <mseifert[at]error-reports.org>
 *
 * This file is part of GRAL.
 *
 * GRAL is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * GRAL is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with GRAL.  If not, see <http://www.gnu.org/licenses/>.
 */

import javax.swing.JFrame;
import de.erichseifert.gral.data.DataTable;
import de.erichseifert.gral.plots.XYPlot;
import de.erichseifert.gral.plots.lines.LineRenderer;
import de.erichseifert.gral.ui.InteractivePanel;
import de.erichseifert.gral.plots.lines.DefaultLineRenderer2D;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.geom.Ellipse2D;
import de.erichseifert.gral.data.DataSeries;
import de.erichseifert.gral.plots.XYPlot.XYPlotArea2D;
import de.erichseifert.gral.plots.points.SizeablePointRenderer;
import de.erichseifert.gral.util.GraphicsUtils;
import de.erichseifert.gral.graphics.Insets2D;

public class example extends JFrame {
	public example() {
		setDefaultCloseOperation (EXIT_ON_CLOSE);
		setSize (900,400);
		DataTable data=new DataTable(Double.class,Double.class);//Create data for points
		DataTable data1=new DataTable(Double.class,Double.class);//Create data for extreme points
		Polynom p1=new Polynom();
		p1.add(new Monom (0.2,4));
		p1.add(new Monom (-1.5,3));
		p1.add(new Monom (3.0,2));
		p1.add(new Monom (-1.0,1));
		p1.add(new Monom (-5.0,0));
		Polynom_able p2=p1.derivative();
	
		
		for (double x=-2.0;x<=6.0;x+=0.1)
		{	
			if ((p2.f(x)>0 && p2.f(x+0.1)<0)||(p2.f(x)<0&&p2.f(x+0.1)>0))//Find the approximation of extreme points with the derative
			{
				
				double i=p2.root(x,x+0.1, 0.0000001);
				data1.add(i,p1.f(i));
			}
			double y=p1.f(x);
			data.add(x,y);
			System.out.println(x+", "+y);
		}
		XYPlot plot=new XYPlot (data);//Add the points to the plot
		getContentPane().add(new InteractivePanel(plot));
		LineRenderer lines = new DefaultLineRenderer2D();
		plot.setLineRenderers(data, lines);
		plot.add(data1);//Adds the extreme points to the plot
		plot.getTitle().setText("Shira and Hadar graph:");//Create a frame with a title
		Color color = new Color(0.0f, 0.3f, 1.0f);
		plot.getPointRenderers(data1).get(0).setColor(color.BLACK);//Point of extremity is black
		plot.getLineRenderers(data).get(0).setColor(color.GREEN);//Painting the line connecting in green
		plot.getPointRenderers(data).get(0).setColor(color.MAGENTA);//Coloring all points in pink
		System.out.println("The area under the X axis and above the function: "+p1.area2(-2, 6, 0.01));//The area under the X axis and above the function
	}
	public static void main (String [] args) {
		example frame=new example(); 
		frame.setVisible(true);
		
		
	}
}
