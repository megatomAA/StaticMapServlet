package fr.aareon.map;

import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.aareon.openstreetmap.Coords;
import fr.aareon.openstreetmap.Marker;
import fr.aareon.openstreetmap.StaticMap;

/**
 * Servlet implementation class StaticMapServlet
 */
@WebServlet("/StaticMapServlet")
public class StaticMapServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public StaticMapServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, FileNotFoundException {
		String address = request.getParameter("address");
		String size = request.getParameter("size");
		String maptype = request.getParameter("maptype");
		String coord = request.getParameter("coord");
		String zoom = request.getParameter("zoom");
		String markers = request.getParameter("markers");
        String apikey = request.getParameter("apikey");
        String cache = request.getParameter("cache");
        String cleanCache = request.getParameter("cleanCache");
		
		StaticMap map = new StaticMap();
		map.setAddress(address);
		if (size != null) {
			String[] dimensions = size.split("x");
			map.setWidth(Integer.parseInt(dimensions[0]));
			map.setHeight(Integer.parseInt(dimensions[1]));
		}
		if (maptype != null) {
			map.setMaptype(maptype);
		}
		if (zoom != null) {
			map.setZoom(Integer.parseInt(zoom));
		}
		if (coord != null) {
			String[] coords = coord.split(",");
			map.setCoord(new Coords(Float.parseFloat(coords[0]), Float.parseFloat(coords[1])));
		}
		if (apikey != null) {
		    map.setApiKey(apikey);
		}
        if (cache != null) {
            map.setUseMapCache(Boolean.valueOf(cache));
            map.setUseTileCache(Boolean.valueOf(cache));
        }
        if (cleanCache != null) {
            map.cleanCache();
        }
		
		if (markers != null) {
			String[] aMarkers = markers.split("\\|");
			for (int i=0;i<aMarkers.length;i++){
				String[] aMarker = aMarkers[i].split(",");
				
				Marker marker = new Marker();
				marker.setCoord(new Coords(Float.parseFloat(aMarker[0]), Float.parseFloat(aMarker[1])));
				marker.setType(aMarker[2]);
				marker.setLabel(aMarker[3]);
				map.addMarker(marker);
			}
		}
		BufferedImage image = map.generate();
		if (image != null) {
			response.setContentType("image/png");
			response.setHeader("Pragma", "public");
			int expires = 60 * 60 * 24 * 14;
			response.setHeader("Cache-Control", "maxage="+expires);			
			ImageIO.write(image, "png", response.getOutputStream());
		} else {
			PrintWriter writer = response.getWriter();
			response.setContentType("text/html");
			writer.print("Address not found");
		}
	}

}
