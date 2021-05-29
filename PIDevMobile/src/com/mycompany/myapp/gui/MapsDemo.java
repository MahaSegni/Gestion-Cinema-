/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.location.Location;
import com.codename1.location.LocationManager;
import com.codename1.maps.Coord;
import com.codename1.maps.MapComponent;
import com.codename1.maps.layers.LinesLayer;
import com.codename1.maps.layers.PointLayer;
import com.codename1.maps.layers.PointsLayer;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;

/**
 *
 * @author mahas
 */

public class MapsDemo {

    private Form main;
    private Coord lastLocation;

    public void init(Object context) {
        Resources theme = UIManager.initFirstTheme("/theme");
        //Enable Toolbar to all Forms by default
        Toolbar.setGlobalToolbar(true);
        // Pro only feature, uncomment if you have a pro subscription
        //Log.bindCrashProtection(true);
    }

    public void start() {
        main = new Form("Maps Demo");
        main.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        Button b = new Button("Where am I?");
        main.addComponent(b);
        b.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                showMeOnMap();
            }
        });
        b = new Button("Find Resturants");
        main.addComponent(b);
        b.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                InfiniteProgress inf = new InfiniteProgress();
                Dialog progress = inf.showInifiniteBlocking();

                showResturantsOnMap(progress);
            }
        });

        main.show();
    }

    private void showMeOnMap() {
        Form map = new Form("Map");
        map.setLayout(new BorderLayout());
        map.setScrollable(false);
        final MapComponent mc = new MapComponent();

        putMeOnMap(mc);
        mc.zoomToLayers();

        map.addComponent(BorderLayout.CENTER, mc);
        map.getToolbar().addCommandToLeftBar(new MapsDemo.BackCommand());
        map.setBackCommand(new MapsDemo.BackCommand());
        map.show();

    }

    private void showResturantsOnMap(final Dialog progress) {
        try {
            final Form map = new Form("Map");
            map.setLayout(new BorderLayout());
            map.setScrollable(false);
            final MapComponent mc = new MapComponent();
            Location loc = LocationManager.getLocationManager().getCurrentLocation();
            putMeOnMap(mc);
            map.addComponent(BorderLayout.CENTER, mc);
            map.getToolbar().addCommandToLeftBar(new MapsDemo.BackCommand());
            map.setBackCommand(new MapsDemo.BackCommand());

            ConnectionRequest req = new ConnectionRequest() {
                private Coord firstPlace;

                protected void readResponse(InputStream input) throws IOException {
                    JSONParser p = new JSONParser();
                    Hashtable h = p.parse(new InputStreamReader(input));
                    // "status" : "REQUEST_DENIED"
                    String response = (String) h.get("status");
                    if (response.equals("REQUEST_DENIED")) {
                        System.out.println("make sure to obtain a key from "
                                + "https://developers.google.com/maps/documentation/places/");
                        progress.dispose();
                        Dialog.show("Info", "make sure to obtain an application key from "
                                + "google places api's", "Ok", null);
                        return;
                    }

                    final Vector v = (Vector) h.get("results");

                    Image im = Image.createImage("/red_pin.png");
                    PointsLayer pl = new PointsLayer();
                    pl.setPointIcon(im);
                    pl.addActionListener(new ActionListener() {

                        public void actionPerformed(ActionEvent evt) {
                            PointLayer p = (PointLayer) evt.getSource();
                            System.out.println("pressed " + p);

                            Dialog.show("Details", "" + p.getName() + "\n" + p.getLatitude() + "|" + p.getLongitude(), "Ok", null);
                        }
                    });

                    for (int i = 0; i < v.size(); i++) {
                        Hashtable entry = (Hashtable) v.elementAt(i);
                        Hashtable geo = (Hashtable) entry.get("geometry");
                        Hashtable loc = (Hashtable) geo.get("location");
                        Double lat = (Double) loc.get("lat");
                        Double lng = (Double) loc.get("lng");
                        PointLayer point = new PointLayer(new Coord(lat.doubleValue(), lng.doubleValue()),
                                (String) entry.get("name"), null);
                        pl.addPoint(point);
                        if (i == 0) {
                            firstPlace = new Coord(lat.doubleValue(), lng.doubleValue());
                            map.addComponent(BorderLayout.SOUTH, new Button(new Command("Go to " + point.getName() + " ?") {

                                public void actionPerformed(ActionEvent evt) {
                                    try {
                                        ArrayList l = decodePoly(getDirections(lastLocation, firstPlace));
                                        Coord p0, p1 = lastLocation;
                                        LinesLayer line = new LinesLayer();
                                        Iterator points = l.iterator();
                                        while (points.hasNext()) {
                                            p0 = p1;
                                            p1 = (Coord) points.next();
                                            line.addLineSegment(new Coord[]{p0, p1});
                                        }
                                        mc.addLayer(line);
                                    } catch (Exception ex) {
                                        System.out.println("Failed to get directions.");
                                    }
                                }

                            }));
                        }
                    }
                    progress.dispose();

                    mc.addLayer(pl);
                    map.show();
                    mc.zoomToLayers();

                }
            };
            req.setUrl("https://maps.googleapis.com/maps/api/place/search/json");
            req.setPost(false);
            req.addArgument("location", "" + loc.getLatitude() + "," + loc.getLongtitude());
            req.addArgument("radius", "500");
            req.addArgument("types", "food");
            req.addArgument("sensor", "false");

            //get your own key from https://developers.google.com/maps/documentation/places/
            //1)Create a google cloud project
            //2)Enable the "Google Places API Web Service"
            //3)Create a "Server Key" type
            //4)Replace it here
            String key = "AddYourOwnKeyHere";

            req.addArgument("key", key);

            NetworkManager.getInstance().addToQueue(req);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void putMeOnMap(MapComponent map) {
        try {
            Location loc = LocationManager.getLocationManager().getCurrentLocation();
            lastLocation = new Coord(loc.getLatitude(), loc.getLongtitude());
            Image i = Image.createImage("/blue_pin.png");
            PointsLayer pl = new PointsLayer();
            pl.setPointIcon(i);
            PointLayer p = new PointLayer(lastLocation, "You Are Here", i);
            p.setDisplayName(true);
            pl.addPoint(p);
            pl.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent evt) {
                    PointLayer p = (PointLayer) evt.getSource();
                    System.out.println("pressed " + p);

                    Dialog.show("Details", "You Are Here" + "\n" + p.getLatitude() + "|" + p.getLongitude(), "Ok", null);
                }
            });
            map.addLayer(pl);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public void stop() {
        System.out.println("stopped");
    }

    public void destroy() {
        System.out.println("destroyed");

    }

    class BackCommand extends Command {

        public BackCommand() {
            super("");
            FontImage img = FontImage.createMaterial(FontImage.MATERIAL_ARROW_BACK, UIManager.getInstance().getComponentStyle("TitleCommand"));
            setIcon(img);
        }

        public void actionPerformed(ActionEvent evt) {
            main.showBack();
        }
    }

    private ArrayList decodePoly(String encoded) {
        ArrayList poly = new ArrayList();
        int index = 0, len = encoded.length();
        int lat = 0, lng = 0;

        while (index < len) {
            int b, shift = 0, result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lat += dlat;

            shift = 0;
            result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lng += dlng;

            Coord p = new Coord(lat / 1E5, lng / 1E5);
            poly.add(p);
        }

        return poly;
    }

    private String getDirections(Coord origin, Coord destination) throws IOException {
        ConnectionRequest req = new ConnectionRequest();
        req.setUrl("http://maps.googleapis.com/maps/api/directions/json");
        req.setUserAgent("Opera/8.0 (Windows NT 5.1; U; en)");
        req.setPost(false);
        req.addArgument("origin", "" + origin.getLatitude() + " " + origin.getLongitude());
        req.addArgument("destination", "" + destination.getLatitude() + " " + destination.getLongitude());
        req.addArgument("mode", "walking");
        req.addArgument("sensor", "false");
        NetworkManager.getInstance().addToQueueAndWait(req);
        JSONParser p = new JSONParser();
        Hashtable h = p.parse(new InputStreamReader(new ByteArrayInputStream(req.getResponseData())));
        System.out.println(h.toString());
        return ((Hashtable) ((Hashtable) ((Vector) h.get("routes")).firstElement()).get("overview_polyline")).get("points").toString();
    }
}
