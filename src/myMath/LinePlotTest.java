package myMath;
import java.awt.Color;
import javax.swing.JFrame;
import de.erichseifert.gral.data.DataTable;
import de.erichseifert.gral.plots.XYPlot;
import de.erichseifert.gral.plots.lines.DefaultLineRenderer2D;
import de.erichseifert.gral.plots.lines.LineRenderer;
import de.erichseifert.gral.plots.points.PointRenderer;
import de.erichseifert.gral.ui.InteractivePanel;

public class LinePlotTest extends JFrame {
    public LinePlotTest() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 400);

        DataTable data = new DataTable(Double.class, Double.class);
        for (double x = -5.0; x <= 5.0; x+=0.25) {
            double y = 5.0*Math.sin(x);
            data.add(x, y);
        }
        DataTable data1 = new DataTable(Double.class, Double.class);
        for (double x = -5.0; x <= 5.0; x+=0.25) {
            double y = 5.0*Math.cos(x);
            data.add(x, y);
        }
        XYPlot plot = new XYPlot(data);
        getContentPane().add(new InteractivePanel(plot));
        XYPlot plot1 = new XYPlot(data);
        getContentPane().add(new InteractivePanel(plot1));
        LineRenderer lines = new DefaultLineRenderer2D();
        plot.setLineRenderers(data, lines);
        Color color = new Color(0.0f, 0.0f, 0.0f);
        Color color1 = new Color(0.3f, 0.3f, 0.3f);
        plot.getPointRenderers(data).get(0).setColor(color);
        plot.getLineRenderers(data1).get(0).setColor(color1);
    }

    public static void main(String[] args) {
        LinePlotTest frame = new LinePlotTest();
        frame.setVisible(true);
    }
}
