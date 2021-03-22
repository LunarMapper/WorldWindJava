/*
 * Copyright 2006-2009, 2017, 2020 United States Government, as represented by the
 * Administrator of the National Aeronautics and Space Administration.
 * All rights reserved.
 * 
 * The NASA World Wind Java (WWJ) platform is licensed under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed
 * under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR
 * CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 * 
 * NASA World Wind Java (WWJ) also contains the following 3rd party Open Source
 * software:
 * 
 *     Jackson Parser – Licensed under Apache 2.0
 *     GDAL – Licensed under MIT
 *     JOGL – Licensed under  Berkeley Software Distribution (BSD)
 *     Gluegen – Licensed under Berkeley Software Distribution (BSD)
 * 
 * A complete listing of 3rd Party software notices and licenses included in
 * NASA World Wind Java (WWJ)  can be found in the WorldWindJava-v2.2 3rd-party
 * notices and licenses PDF found in code directory.
 */

package gov.nasa.cms.features.coordinates;

import gov.nasa.cms.CelestialMapper;
import gov.nasa.worldwind.View;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.geom.coords.UTMCoord;
import gov.nasa.worldwind.layers.*;
import gov.nasa.worldwind.pick.PickedObject;
import gov.nasa.worldwind.render.*;
import gov.nasa.worldwind.util.UnitsFormat;
import gov.nasa.worldwind.view.orbit.OrbitView;
import gov.nasa.worldwindx.applications.worldwindow.core.*;
import gov.nasa.worldwindx.applications.worldwindow.core.layermanager.LayerPath;
import gov.nasa.worldwindx.applications.worldwindow.features.AbstractOnDemandLayerFeature;
import gov.nasa.worldwindx.applications.worldwindow.util.WWOUnitsFormat;

import java.awt.*;
import java.util.Iterator;

/**
 * @author tag
 * @version $Id: CoordinatesDisplay.java 1171 2013-02-11 21:45:02Z dcollins $
 */
public class CoordinatesDisplay
{
    private CelestialMapper cms;
    private Layer coordinatesLayer;

    public CoordinatesDisplay(CelestialMapper cms){
        this.cms = cms;
        this.initialize(cms);
    }

    public void initialize(CelestialMapper cms){
        this.coordinatesLayer = this.createLayer();
//        this.coordinatesLayer.setScreenLocation(new Point(view.x + 1000, view.y + 800));
        cms.getWwd().getModel().getLayers().add(coordinatesLayer);
        this.coordinatesLayer.setEnabled(true);
    }

    public Layer getCoordinatesLayer()
    {
        return coordinatesLayer;
    }

    public void setCoordinatesLayer(Layer coordinatesLayer)
    {
        this.coordinatesLayer = coordinatesLayer;
    }


    protected Layer createLayer()
    {
        Layer layer = this.doCreateLayer();

        layer.setPickEnabled(false);

        return layer;
    }


    protected Layer doCreateLayer()
    {
        ScreenAnnotation anno = new ScreenAnnotation("Dummy Text", new Point(100, 100));
        anno.setAlwaysOnTop(true);

        AnnotationAttributes attrs = anno.getAttributes();
        attrs.setTextColor(Color.WHITE);
        attrs.setFont(Font.decode("Consolas-Bold-15"));
        attrs.setEffect(AVKey.TEXT_EFFECT_OUTLINE);

        attrs.setFrameShape(AVKey.SHAPE_NONE);
        attrs.setLeader(AVKey.SHAPE_NONE);
        attrs.setBackgroundColor(Color.BLACK);
        attrs.setBorderColor(new Color(0.1f, 0.1f, 0.1f, 0f));
        attrs.setBorderWidth(1d);
        attrs.setCornerRadius(5);
        attrs.setInsets(new Insets(10, 0, 0, 10));


        int width = 340, height = 200;
        attrs.setSize(new Dimension(width, height));
//        attrs.setTextAlign(AVKey.RIGHT);
        attrs.setTextAlign(AVKey.LEFT);
        attrs.setAdjustWidthToText(AVKey.SIZE_FIXED);
//        attrs.setDrawOffset(new Point(-width / 2, -height - 100));
        attrs.setDrawOffset(new Point(-width -665, -height - 140));

        CoordAnnotationLayer layer = new CoordAnnotationLayer();
        layer.setValue(Constants.SCREEN_LAYER, true);
        layer.setPickEnabled(false);
        layer.addAnnotation(anno);
        layer.setName("Coordinates Display");

        return layer;
    }

    private class CoordAnnotationLayer extends AnnotationLayer
    {
        @Override
        public void render(DrawContext dc)
        {
            Iterator<Annotation> iter = this.getAnnotations().iterator();
            Annotation anno = iter.next();
            if (anno != null && anno instanceof ScreenAnnotation)
            {
                anno.setText(formatText(dc));
                Dimension wwSize = cms.getWwjPanel().getSize();
                ((ScreenAnnotation) anno).setScreenPoint(new Point(wwSize.width, wwSize.height));
            }

            super.render(dc);
        }
    }

    private Position getCurrentPosition(DrawContext dc)
    {
        if (dc.getPickedObjects() == null)
            return null;

        PickedObject po = dc.getPickedObjects().getTerrainObject();
        return po != null ? po.getPosition() : null;
    }

    private String formatText(DrawContext dc)
    {
        StringBuilder sb = new StringBuilder();

        Position eyePosition = dc.getView().getEyePosition();
        if (eyePosition != null)
        {
            WWOUnitsFormat units = this.cms.getUnits();
            String origFormat = units.getFormat(UnitsFormat.FORMAT_EYE_ALTITUDE);
            String tempFormat = origFormat;

            if (Math.abs(eyePosition.getElevation() * units.getLengthUnitsMultiplier()) < 10)
            {
                tempFormat = " %,6.3f %s";
                units.setFormat(UnitsFormat.FORMAT_EYE_ALTITUDE, tempFormat);
            }

            sb.append(this.cms.getUnits().eyeAltitudeNL(eyePosition.getElevation()));

            if (!tempFormat.equals(origFormat))
                units.setFormat(UnitsFormat.FORMAT_EYE_ALTITUDE, origFormat);
        }
        else
        {
            sb.append("Altitude\n");
        }

        Position currentPosition = getCurrentPosition(dc);
        if (currentPosition != null)
        {
            sb.append(this.cms.getUnits().latitudeNL(currentPosition.getLatitude()));
            sb.append(this.cms.getUnits().longitudeNL(currentPosition.getLongitude()));
            sb.append(this.cms.getUnits().terrainHeightNL(currentPosition.getElevation(),
                this.cms.getWwd().getSceneController().getVerticalExaggeration()));
        }
        else
        {
            sb.append(this.cms.getUnits().getStringValue(UnitsFormat.LABEL_LATITUDE)).append("\n");
            sb.append(this.cms.getUnits().getStringValue(UnitsFormat.LABEL_LONGITUDE)).append("\n");
            sb.append(this.cms.getUnits().getStringValue(UnitsFormat.LABEL_TERRAIN_HEIGHT)).append("\n");
        }

        sb.append(this.cms.getUnits().pitchNL(computePitch(dc.getView())));
        sb.append(this.cms.getUnits().headingNL(computeHeading(dc.getView())));

        String datum = this.cms.getUnits().datumNL();

        if (cms.getUnits().isShowUTM())
        {
            sb.append(datum);
            if (currentPosition != null)
            {
                try
                {
                    UTMCoord utm = UTMCoord.fromLatLon(currentPosition.getLatitude(), currentPosition.getLongitude(),
                        this.cms.getUnits().isShowWGS84() ? null : "NAD27");

                    sb.append(this.cms.getUnits().utmZoneNL(utm.getZone()));
                    sb.append(this.cms.getUnits().utmEastingNL(utm.getEasting()));
                    sb.append(this.cms.getUnits().utmNorthingNL(utm.getNorthing()));
                }
                catch (Exception e)
                {
                    sb.append(String.format(
                        this.cms.getUnits().getStringValue(UnitsFormat.LABEL_UTM_ZONE) + "\n"));
                    sb.append(String.format(
                        this.cms.getUnits().getStringValue(UnitsFormat.LABEL_UTM_EASTING) + "\n"));
                    sb.append(String.format(
                        this.cms.getUnits().getStringValue(UnitsFormat.LABEL_UTM_NORTHING) + "\n"));
                }
            }
            else
            {
                sb.append(
                    String.format(this.cms.getUnits().getStringValue(UnitsFormat.LABEL_UTM_ZONE) + "\n"));
                sb.append(String.format(
                    this.cms.getUnits().getStringValue(UnitsFormat.LABEL_UTM_EASTING) + "\n"));
                sb.append(String.format(
                    this.cms.getUnits().getStringValue(UnitsFormat.LABEL_UTM_NORTHING) + "\n"));
            }
        }
        else
        {
            sb.append(datum);
        }

        return sb.toString();
    }

    private double computeHeading(View view)
    {
        if (view == null)
            return 0.0;

        if (!(view instanceof OrbitView))
            return 0.0;

        OrbitView orbitView = (OrbitView) view;

        return orbitView.getHeading().getDegrees();
    }

    private double computePitch(View view)
    {
        if (view == null)
            return 0.0;

        if (!(view instanceof OrbitView))
            return 0.0;

        OrbitView orbitView = (OrbitView) view;

        return orbitView.getPitch().getDegrees();
    }
}
